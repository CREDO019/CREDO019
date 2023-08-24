package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.aj */
/* loaded from: classes3.dex */
public final class C2620aj extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ int f966a;

    /* renamed from: b */
    final /* synthetic */ C2682i f967b;

    /* renamed from: c */
    final /* synthetic */ C2632av f968c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2620aj(C2632av c2632av, C2682i c2682i, int r3, C2682i c2682i2) {
        super(c2682i);
        this.f968c = c2632av;
        this.f966a = r3;
        this.f967b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        try {
            str = this.f968c.f979d;
            this.f968c.f978a.m796b().mo732a(str, this.f966a, new BinderC2628ar(this.f968c, this.f967b));
        } catch (RemoteException e) {
            c2494af = C2632av.f976b;
            c2494af.m807a(e, "getSessionState(%d)", Integer.valueOf(this.f966a));
            this.f967b.m455b((Exception) new RuntimeException(e));
        }
    }
}
