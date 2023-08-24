package org.apache.logging.log4j.message;

import java.lang.Thread;
import kotlin.text.Typography;

/* loaded from: classes5.dex */
class BasicThreadInformation implements ThreadInformation {
    private static final int HASH_MULTIPLIER = 31;
    private static final int HASH_SHIFT = 32;

    /* renamed from: id */
    private final long f1582id;
    private final boolean isAlive;
    private final boolean isDaemon;
    private final String longName;
    private final String name;
    private final int priority;
    private final Thread.State state;
    private final String threadGroupName;

    public BasicThreadInformation(Thread thread) {
        this.f1582id = thread.getId();
        this.name = thread.getName();
        this.longName = thread.toString();
        this.state = thread.getState();
        this.priority = thread.getPriority();
        this.isAlive = thread.isAlive();
        this.isDaemon = thread.isDaemon();
        ThreadGroup threadGroup = thread.getThreadGroup();
        this.threadGroupName = threadGroup == null ? null : threadGroup.getName();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BasicThreadInformation basicThreadInformation = (BasicThreadInformation) obj;
        if (this.f1582id != basicThreadInformation.f1582id) {
            return false;
        }
        String str = this.name;
        String str2 = basicThreadInformation.name;
        return str == null ? str2 == null : str.equals(str2);
    }

    public int hashCode() {
        long j = this.f1582id;
        int r1 = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.name;
        return r1 + (str != null ? str.hashCode() : 0);
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printThreadInfo(StringBuilder sb) {
        sb.append(Typography.quote);
        sb.append(this.name);
        sb.append("\" ");
        if (this.isDaemon) {
            sb.append("daemon ");
        }
        sb.append("prio=");
        sb.append(this.priority);
        sb.append(" tid=");
        sb.append(this.f1582id);
        sb.append(' ');
        if (this.threadGroupName != null) {
            sb.append("group=\"");
            sb.append(this.threadGroupName);
            sb.append(Typography.quote);
        }
        sb.append('\n');
        sb.append("\tThread state: ");
        sb.append(this.state.name());
        sb.append('\n');
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printStack(StringBuilder sb, StackTraceElement[] stackTraceElementArr) {
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
    }
}
