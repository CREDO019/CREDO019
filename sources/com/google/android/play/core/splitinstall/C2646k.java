package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.splitcompat.C2608p;

/* renamed from: com.google.android.play.core.splitinstall.k */
/* loaded from: classes3.dex */
public final class C2646k {

    /* renamed from: a */
    private static InterfaceC2648m f1004a;

    private C2646k() {
    }

    /* renamed from: a */
    public static synchronized InterfaceC2648m m525a(Context context) {
        InterfaceC2648m interfaceC2648m;
        synchronized (C2646k.class) {
            if (f1004a == null) {
                C2637b c2637b = new C2637b(null);
                c2637b.m531a(new C2672y(C2608p.m576a(context)));
                f1004a = c2637b.m532a();
            }
            interfaceC2648m = f1004a;
        }
        return interfaceC2648m;
    }
}
