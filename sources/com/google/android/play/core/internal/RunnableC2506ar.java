package com.google.android.play.core.internal;

import android.util.Log;
import com.google.android.play.core.splitinstall.InterfaceC2639d;
import java.util.List;

/* renamed from: com.google.android.play.core.internal.ar */
/* loaded from: classes3.dex */
final class RunnableC2506ar implements Runnable {

    /* renamed from: a */
    final /* synthetic */ List f827a;

    /* renamed from: b */
    final /* synthetic */ InterfaceC2639d f828b;

    /* renamed from: c */
    final /* synthetic */ C2507as f829c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2506ar(C2507as c2507as, List list, InterfaceC2639d interfaceC2639d) {
        this.f829c = c2507as;
        this.f827a = list;
        this.f828b = interfaceC2639d;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C2508at c2508at;
        try {
            c2508at = this.f829c.f832c;
            if (c2508at.m777a(this.f827a)) {
                C2507as.m782a(this.f829c, this.f828b);
            } else {
                C2507as.m781a(this.f829c, this.f827a, this.f828b);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Error checking verified files.", e);
            this.f828b.mo482a(-11);
        }
    }
}
