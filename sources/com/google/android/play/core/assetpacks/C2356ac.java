package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: com.google.android.play.core.assetpacks.ac */
/* loaded from: classes3.dex */
final class C2356ac extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ List f386a;

    /* renamed from: b */
    final /* synthetic */ C2682i f387b;

    /* renamed from: c */
    final /* synthetic */ C2371ar f388c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2356ac(C2371ar c2371ar, C2682i c2682i, List list, C2682i c2682i2) {
        super(c2682i);
        this.f388c = c2371ar;
        this.f386a = list;
        this.f387b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        Bundle m1014e;
        ArrayList m1027a = C2371ar.m1027a((Collection) this.f386a);
        try {
            c2504ap = this.f388c.f435e;
            str = this.f388c.f433c;
            m1014e = C2371ar.m1014e();
            ((InterfaceC2572s) c2504ap.m796b()).mo667b(str, m1027a, m1014e, new BinderC2364ak(this.f388c, this.f387b, (byte[]) null));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "cancelDownloads(%s)", this.f386a);
        }
    }
}
