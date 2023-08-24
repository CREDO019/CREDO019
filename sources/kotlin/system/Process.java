package kotlin.system;

import kotlin.Metadata;

@Metadata(m184d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b¨\u0006\u0004"}, m183d2 = {"exitProcess", "", "status", "", "kotlin-stdlib"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlin.system.ProcessKt */
/* loaded from: classes5.dex */
public final class Process {
    private static final Void exitProcess(int r1) {
        System.exit(r1);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
