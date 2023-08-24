package org.apache.logging.log4j.message;

import java.lang.Thread;
import java.lang.management.LockInfo;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import kotlin.text.Typography;

/* loaded from: classes5.dex */
class ExtendedThreadInformation implements ThreadInformation {
    private final ThreadInfo threadInfo;

    public ExtendedThreadInformation(ThreadInfo threadInfo) {
        this.threadInfo = threadInfo;
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printThreadInfo(StringBuilder sb) {
        sb.append(Typography.quote);
        sb.append(this.threadInfo.getThreadName());
        sb.append(Typography.quote);
        sb.append(" Id=");
        sb.append(this.threadInfo.getThreadId());
        sb.append(' ');
        formatState(sb, this.threadInfo);
        if (this.threadInfo.isSuspended()) {
            sb.append(" (suspended)");
        }
        if (this.threadInfo.isInNative()) {
            sb.append(" (in native)");
        }
        sb.append('\n');
    }

    @Override // org.apache.logging.log4j.message.ThreadInformation
    public void printStack(StringBuilder sb, StackTraceElement[] stackTraceElementArr) {
        MonitorInfo[] lockedMonitors;
        int r3 = 0;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append("\tat ");
            sb.append(stackTraceElement.toString());
            sb.append('\n');
            if (r3 == 0 && this.threadInfo.getLockInfo() != null) {
                int r5 = C50881.$SwitchMap$java$lang$Thread$State[this.threadInfo.getThreadState().ordinal()];
                if (r5 == 1) {
                    sb.append("\t-  blocked on ");
                    formatLock(sb, this.threadInfo.getLockInfo());
                    sb.append('\n');
                } else if (r5 == 2) {
                    sb.append("\t-  waiting on ");
                    formatLock(sb, this.threadInfo.getLockInfo());
                    sb.append('\n');
                } else if (r5 == 3) {
                    sb.append("\t-  waiting on ");
                    formatLock(sb, this.threadInfo.getLockInfo());
                    sb.append('\n');
                }
            }
            for (MonitorInfo monitorInfo : this.threadInfo.getLockedMonitors()) {
                if (monitorInfo.getLockedStackDepth() == r3) {
                    sb.append("\t-  locked ");
                    formatLock(sb, monitorInfo);
                    sb.append('\n');
                }
            }
            r3++;
        }
        LockInfo[] lockedSynchronizers = this.threadInfo.getLockedSynchronizers();
        if (lockedSynchronizers.length > 0) {
            sb.append("\n\tNumber of locked synchronizers = ");
            sb.append(lockedSynchronizers.length);
            sb.append('\n');
            for (LockInfo lockInfo : lockedSynchronizers) {
                sb.append("\t- ");
                formatLock(sb, lockInfo);
                sb.append('\n');
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.logging.log4j.message.ExtendedThreadInformation$1 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C50881 {
        static final /* synthetic */ int[] $SwitchMap$java$lang$Thread$State;

        static {
            int[] r0 = new int[Thread.State.values().length];
            $SwitchMap$java$lang$Thread$State = r0;
            try {
                r0[Thread.State.BLOCKED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$lang$Thread$State[Thread.State.WAITING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$lang$Thread$State[Thread.State.TIMED_WAITING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void formatLock(StringBuilder sb, LockInfo lockInfo) {
        sb.append(Typography.less);
        sb.append(lockInfo.getIdentityHashCode());
        sb.append("> (a ");
        sb.append(lockInfo.getClassName());
        sb.append(')');
    }

    private void formatState(StringBuilder sb, ThreadInfo threadInfo) {
        Thread.State threadState = threadInfo.getThreadState();
        sb.append(threadState);
        int r0 = C50881.$SwitchMap$java$lang$Thread$State[threadState.ordinal()];
        if (r0 == 1) {
            sb.append(" (on object monitor owned by \"");
            sb.append(threadInfo.getLockOwnerName());
            sb.append("\" Id=");
            sb.append(threadInfo.getLockOwnerId());
            sb.append(')');
        } else if (r0 == 2) {
            StackTraceElement stackTraceElement = threadInfo.getStackTrace()[0];
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (className.equals("java.lang.Object") && methodName.equals("wait")) {
                sb.append(" (on object monitor");
                if (threadInfo.getLockOwnerName() != null) {
                    sb.append(" owned by \"");
                    sb.append(threadInfo.getLockOwnerName());
                    sb.append("\" Id=");
                    sb.append(threadInfo.getLockOwnerId());
                }
                sb.append(')');
            } else if (className.equals("java.lang.Thread") && methodName.equals("join")) {
                sb.append(" (on completion of thread ");
                sb.append(threadInfo.getLockOwnerId());
                sb.append(')');
            } else {
                sb.append(" (parking for lock");
                if (threadInfo.getLockOwnerName() != null) {
                    sb.append(" owned by \"");
                    sb.append(threadInfo.getLockOwnerName());
                    sb.append("\" Id=");
                    sb.append(threadInfo.getLockOwnerId());
                }
                sb.append(')');
            }
        } else if (r0 != 3) {
        } else {
            StackTraceElement stackTraceElement2 = threadInfo.getStackTrace()[0];
            String className2 = stackTraceElement2.getClassName();
            String methodName2 = stackTraceElement2.getMethodName();
            if (className2.equals("java.lang.Object") && methodName2.equals("wait")) {
                sb.append(" (on object monitor");
                if (threadInfo.getLockOwnerName() != null) {
                    sb.append(" owned by \"");
                    sb.append(threadInfo.getLockOwnerName());
                    sb.append("\" Id=");
                    sb.append(threadInfo.getLockOwnerId());
                }
                sb.append(')');
            } else if (className2.equals("java.lang.Thread") && methodName2.equals("sleep")) {
                sb.append(" (sleeping)");
            } else if (className2.equals("java.lang.Thread") && methodName2.equals("join")) {
                sb.append(" (on completion of thread ");
                sb.append(threadInfo.getLockOwnerId());
                sb.append(')');
            } else {
                sb.append(" (parking for lock");
                if (threadInfo.getLockOwnerName() != null) {
                    sb.append(" owned by \"");
                    sb.append(threadInfo.getLockOwnerName());
                    sb.append("\" Id=");
                    sb.append(threadInfo.getLockOwnerId());
                }
                sb.append(')');
            }
        }
    }
}
