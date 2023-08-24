package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.ai */
/* loaded from: classes3.dex */
final class C2362ai extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ int f411a;

    /* renamed from: b */
    final /* synthetic */ String f412b;

    /* renamed from: c */
    final /* synthetic */ String f413c;

    /* renamed from: d */
    final /* synthetic */ int f414d;

    /* renamed from: e */
    final /* synthetic */ C2682i f415e;

    /* renamed from: f */
    final /* synthetic */ C2371ar f416f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2362ai(C2371ar c2371ar, C2682i c2682i, int r3, String str, String str2, int r6, C2682i c2682i2) {
        super(c2682i);
        this.f416f = c2371ar;
        this.f411a = r3;
        this.f412b = str;
        this.f413c = str2;
        this.f414d = r6;
        this.f415e = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        Bundle m1014e;
        try {
            c2504ap = this.f416f.f435e;
            str = this.f416f.f433c;
            Bundle m1018c = C2371ar.m1018c(this.f411a, this.f412b, this.f413c, this.f414d);
            m1014e = C2371ar.m1014e();
            ((InterfaceC2572s) c2504ap.m796b()).mo664d(str, m1018c, m1014e, new BinderC2365al(this.f416f, this.f415e));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m806b("getChunkFileDescriptor(%s, %s, %d, session=%d)", this.f412b, this.f413c, Integer.valueOf(this.f414d), Integer.valueOf(this.f411a));
            this.f415e.m455b((Exception) new RuntimeException(e));
        }
    }
}
