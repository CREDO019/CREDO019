package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.ak */
/* loaded from: classes3.dex */
public final class C2621ak extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ C2682i f969a;

    /* renamed from: b */
    final /* synthetic */ C2632av f970b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2621ak(C2632av c2632av, C2682i c2682i, C2682i c2682i2) {
        super(c2682i);
        this.f970b = c2632av;
        this.f969a = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        try {
            str = this.f970b.f979d;
            this.f970b.f978a.m796b().mo731a(str, new BinderC2629as(this.f970b, this.f969a));
        } catch (RemoteException e) {
            c2494af = C2632av.f976b;
            c2494af.m807a(e, "getSessionStates", new Object[0]);
            this.f969a.m455b((Exception) new RuntimeException(e));
        }
    }
}
