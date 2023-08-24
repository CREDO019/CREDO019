package com.google.android.play.core.internal;

import java.io.PrintStream;

/* renamed from: com.google.android.play.core.internal.ci */
/* loaded from: classes3.dex */
public final class C2551ci {

    /* renamed from: a */
    static final AbstractC2545cc f853a;

    static {
        AbstractC2545cc c2549cg;
        Integer num = null;
        try {
            try {
                num = (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
            } catch (Exception e) {
                System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
                e.printStackTrace(System.err);
            }
            c2549cg = (num == null || num.intValue() < 19) ? !Boolean.getBoolean("com.google.devtools.build.android.desugar.runtime.twr_disable_mimic") ? new C2548cf() : new C2549cg() : new C2550ch();
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            String name = C2549cg.class.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 133);
            sb.append("An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy ");
            sb.append(name);
            sb.append("will be used. The error is: ");
            printStream.println(sb.toString());
            th.printStackTrace(System.err);
            c2549cg = new C2549cg();
        }
        f853a = c2549cg;
        if (num == null) {
            return;
        }
        num.intValue();
    }

    /* renamed from: a */
    public static void m714a(Throwable th, Throwable th2) {
        f853a.mo715a(th, th2);
    }
}
