package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.aj */
/* loaded from: classes3.dex */
public final class C2363aj extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ C2682i f417a;

    /* renamed from: b */
    final /* synthetic */ C2371ar f418b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2363aj(C2371ar c2371ar, C2682i c2682i, C2682i c2682i2) {
        super(c2682i);
        this.f418b = c2371ar;
        this.f417a = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        Bundle m1014e;
        try {
            c2504ap = this.f418b.f436f;
            str = this.f418b.f433c;
            m1014e = C2371ar.m1014e();
            ((InterfaceC2572s) c2504ap.m796b()).mo668b(str, m1014e, new BinderC2367an(this.f418b, this.f417a));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "keepAlive", new Object[0]);
        }
    }
}
