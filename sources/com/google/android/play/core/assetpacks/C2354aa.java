package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.aa */
/* loaded from: classes3.dex */
final class C2354aa extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ String f378a;

    /* renamed from: b */
    final /* synthetic */ C2682i f379b;

    /* renamed from: c */
    final /* synthetic */ C2371ar f380c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2354aa(C2371ar c2371ar, C2682i c2682i, String str, C2682i c2682i2) {
        super(c2682i);
        this.f380c = c2371ar;
        this.f378a = str;
        this.f379b = c2682i2;
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
            c2504ap = this.f380c.f435e;
            str = this.f380c.f433c;
            m1019c = C2371ar.m1019c(0, this.f378a);
            m1014e = C2371ar.m1014e();
            ((InterfaceC2572s) c2504ap.m796b()).mo663e(str, m1019c, m1014e, new BinderC2364ak(this.f380c, this.f379b, (short[]) null));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "removePack(%s)", this.f378a);
        }
    }
}
