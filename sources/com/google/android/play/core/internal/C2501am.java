package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;
import java.util.List;

/* renamed from: com.google.android.play.core.internal.am */
/* loaded from: classes3.dex */
final class C2501am extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ IBinder f811a;

    /* renamed from: b */
    final /* synthetic */ ServiceConnectionC2503ao f812b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2501am(ServiceConnectionC2503ao serviceConnectionC2503ao, IBinder iBinder) {
        this.f812b = serviceConnectionC2503ao;
        this.f811a = iBinder;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    public final void mo565a() {
        InterfaceC2500al interfaceC2500al;
        List<Runnable> list;
        List list2;
        C2504ap c2504ap = this.f812b.f814a;
        interfaceC2500al = c2504ap.f822h;
        c2504ap.f826l = (IInterface) interfaceC2500al.mo566a(this.f811a);
        C2504ap.m788f(this.f812b.f814a);
        this.f812b.f814a.f820f = false;
        list = this.f812b.f814a.f819e;
        for (Runnable runnable : list) {
            runnable.run();
        }
        list2 = this.f812b.f814a.f819e;
        list2.clear();
    }
}
