package org.apache.logging.log4j.message;

/* loaded from: classes5.dex */
interface ThreadInformation {
    void printStack(StringBuilder sb, StackTraceElement[] stackTraceElementArr);

    void printThreadInfo(StringBuilder sb);
}
