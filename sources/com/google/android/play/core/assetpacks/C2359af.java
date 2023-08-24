package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.assetpacks.af */
/* loaded from: classes3.dex */
final class C2359af extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ int f397a;

    /* renamed from: b */
    final /* synthetic */ String f398b;

    /* renamed from: c */
    final /* synthetic */ String f399c;

    /* renamed from: d */
    final /* synthetic */ int f400d;

    /* renamed from: e */
    final /* synthetic */ C2682i f401e;

    /* renamed from: f */
    final /* synthetic */ C2371ar f402f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2359af(C2371ar c2371ar, C2682i c2682i, int r3, String str, String str2, int r6, C2682i c2682i2) {
        super(c2682i);
        this.f402f = c2371ar;
        this.f397a = r3;
        this.f398b = str;
        this.f399c = str2;
        this.f400d = r6;
        this.f401e = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        Bundle m1014e;
        try {
            c2504ap = this.f402f.f435e;
            str = this.f402f.f433c;
            Bundle m1018c = C2371ar.m1018c(this.f397a, this.f398b, this.f399c, this.f400d);
            m1014e = C2371ar.m1014e();
            ((InterfaceC2572s) c2504ap.m796b()).mo672a(str, m1018c, m1014e, new BinderC2364ak(this.f402f, this.f401e, (char[]) null));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "notifyChunkTransferred", new Object[0]);
        }
    }
}
