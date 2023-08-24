package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.al */
/* loaded from: classes3.dex */
public final class C2622al extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ int f971a;

    /* renamed from: b */
    final /* synthetic */ C2682i f972b;

    /* renamed from: c */
    final /* synthetic */ C2632av f973c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2622al(C2632av c2632av, C2682i c2682i, int r3, C2682i c2682i2) {
        super(c2682i);
        this.f973c = c2632av;
        this.f971a = r3;
        this.f972b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        try {
            str = this.f973c.f979d;
            this.f973c.f978a.m796b().mo733a(str, this.f971a, C2632av.m546b(), new BinderC2623am(this.f973c, this.f972b));
        } catch (RemoteException e) {
            c2494af = C2632av.f976b;
            c2494af.m807a(e, "cancelInstall(%d)", Integer.valueOf(this.f971a));
            this.f972b.m455b((Exception) new RuntimeException(e));
        }
    }
}
