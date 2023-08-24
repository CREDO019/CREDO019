package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.ag */
/* loaded from: classes3.dex */
public final class C2360ag extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ int f403a;

    /* renamed from: b */
    final /* synthetic */ String f404b;

    /* renamed from: c */
    final /* synthetic */ C2682i f405c;

    /* renamed from: d */
    final /* synthetic */ int f406d;

    /* renamed from: e */
    final /* synthetic */ C2371ar f407e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2360ag(C2371ar c2371ar, C2682i c2682i, int r3, String str, C2682i c2682i2, int r6) {
        super(c2682i);
        this.f407e = c2371ar;
        this.f403a = r3;
        this.f404b = str;
        this.f405c = c2682i2;
        this.f406d = r6;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        Bundle m1019c;
        Bundle m1014e;
        try {
            c2504ap = this.f407e.f435e;
            str = this.f407e.f433c;
            m1019c = C2371ar.m1019c(this.f403a, this.f404b);
            m1014e = C2371ar.m1014e();
            ((InterfaceC2572s) c2504ap.m796b()).mo669b(str, m1019c, m1014e, new BinderC2368ao(this.f407e, this.f405c, this.f403a, this.f404b, this.f406d));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "notifyModuleCompleted", new Object[0]);
        }
    }
}
