package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.splitcompat.C2608p;

/* renamed from: com.google.android.play.core.appupdate.w */
/* loaded from: classes3.dex */
public final /* synthetic */ class C2350w {

    /* renamed from: a */
    private static C2352y f362a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized C2352y m1048a(Context context) {
        C2352y c2352y;
        synchronized (C2350w.class) {
            if (f362a == null) {
                C2351x c2351x = new C2351x(null);
                c2351x.m1046a(new C2334g(C2608p.m576a(context)));
                f362a = c2351x.m1047a();
            }
            c2352y = f362a;
        }
        return c2352y;
    }
}
