package org.apache.logging.log4j.message;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes5.dex */
public class ThreadDumpMessage implements Message {
    private static final ThreadInfoFactory FACTORY;
    private static final long serialVersionUID = -1103400781608841088L;
    private String formattedMessage;
    private volatile Map<ThreadInformation, StackTraceElement[]> threads;
    private final String title;

    /* loaded from: classes5.dex */
    private interface ThreadInfoFactory {
        Map<ThreadInformation, StackTraceElement[]> createThreadInfo();
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return null;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return null;
    }

    static {
        Method[] methods = ThreadInfo.class.getMethods();
        int length = methods.length;
        boolean z = false;
        int r3 = 0;
        while (true) {
            if (r3 >= length) {
                z = true;
                break;
            } else if (methods[r3].getName().equals("getLockInfo")) {
                break;
            } else {
                r3++;
            }
        }
        FACTORY = z ? new BasicThreadInfoFactory() : new ExtendedThreadInfoFactory();
    }

    public ThreadDumpMessage(String str) {
        this.title = str == null ? "" : str;
        this.threads = FACTORY.createThreadInfo();
    }

    private ThreadDumpMessage(String str, String str2) {
        this.formattedMessage = str;
        this.title = str2 == null ? "" : str2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ThreadDumpMessage[");
        if (this.title.length() > 0) {
            sb.append("Title=\"");
            sb.append(this.title);
            sb.append(Typography.quote);
        }
        sb.append(']');
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        String str = this.formattedMessage;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(this.title);
        if (this.title.length() > 0) {
            sb.append('\n');
        }
        for (Map.Entry<ThreadInformation, StackTraceElement[]> entry : this.threads.entrySet()) {
            ThreadInformation key = entry.getKey();
            key.printThreadInfo(sb);
            key.printStack(sb, entry.getValue());
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        String str = this.title;
        return str == null ? "" : str;
    }

    protected Object writeReplace() {
        return new ThreadDumpMessageProxy(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    /* loaded from: classes5.dex */
    private static class ThreadDumpMessageProxy implements Serializable {
        private static final long serialVersionUID = -3476620450287648269L;
        private final String formattedMsg;
        private final String title;

        public ThreadDumpMessageProxy(ThreadDumpMessage threadDumpMessage) {
            this.formattedMsg = threadDumpMessage.getFormattedMessage();
            this.title = threadDumpMessage.title;
        }

        protected Object readResolve() {
            return new ThreadDumpMessage(this.formattedMsg, this.title);
        }
    }

    /* loaded from: classes5.dex */
    private static class BasicThreadInfoFactory implements ThreadInfoFactory {
        private BasicThreadInfoFactory() {
        }

        @Override // org.apache.logging.log4j.message.ThreadDumpMessage.ThreadInfoFactory
        public Map<ThreadInformation, StackTraceElement[]> createThreadInfo() {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            HashMap hashMap = new HashMap(allStackTraces.size());
            for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                hashMap.put(new BasicThreadInformation(entry.getKey()), entry.getValue());
            }
            return hashMap;
        }
    }

    /* loaded from: classes5.dex */
    private static class ExtendedThreadInfoFactory implements ThreadInfoFactory {
        private ExtendedThreadInfoFactory() {
        }

        @Override // org.apache.logging.log4j.message.ThreadDumpMessage.ThreadInfoFactory
        public Map<ThreadInformation, StackTraceElement[]> createThreadInfo() {
            ThreadInfo[] dumpAllThreads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
            HashMap hashMap = new HashMap(dumpAllThreads.length);
            for (ThreadInfo threadInfo : dumpAllThreads) {
                hashMap.put(new ExtendedThreadInformation(threadInfo), threadInfo.getStackTrace());
            }
            return hashMap;
        }
    }
}
