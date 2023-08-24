package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.ah */
/* loaded from: classes3.dex */
final class C2361ah extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ int f408a;

    /* renamed from: b */
    final /* synthetic */ C2682i f409b;

    /* renamed from: c */
    final /* synthetic */ C2371ar f410c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2361ah(C2371ar c2371ar, C2682i c2682i, int r3, C2682i c2682i2) {
        super(c2682i);
        this.f410c = c2371ar;
        this.f408a = r3;
        this.f409b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        Bundle m1020c;
        Bundle m1014e;
        try {
            c2504ap = this.f410c.f435e;
            str = this.f410c.f433c;
            m1020c = C2371ar.m1020c(this.f408a);
            m1014e = C2371ar.m1014e();
            ((InterfaceC2572s) c2504ap.m796b()).mo666c(str, m1020c, m1014e, new BinderC2364ak(this.f410c, this.f409b, (int[]) null));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "notifySessionFailed", new Object[0]);
        }
    }
}
