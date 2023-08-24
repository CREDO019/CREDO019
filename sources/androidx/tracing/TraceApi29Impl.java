package androidx.tracing;

/* loaded from: classes.dex */
final class TraceApi29Impl {
    private TraceApi29Impl() {
    }

    public static void beginAsyncSection(String str, int r1) {
        android.os.Trace.beginAsyncSection(str, r1);
    }

    public static void endAsyncSection(String str, int r1) {
        android.os.Trace.endAsyncSection(str, r1);
    }

    public static void setCounter(String str, int r3) {
        android.os.Trace.setCounter(str, r3);
    }
}
