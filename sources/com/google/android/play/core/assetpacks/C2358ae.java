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
import java.util.Map;

/* renamed from: com.google.android.play.core.assetpacks.ae */
/* loaded from: classes3.dex */
final class C2358ae extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ List f392a;

    /* renamed from: b */
    final /* synthetic */ Map f393b;

    /* renamed from: c */
    final /* synthetic */ C2682i f394c;

    /* renamed from: d */
    final /* synthetic */ InterfaceC2379az f395d;

    /* renamed from: e */
    final /* synthetic */ C2371ar f396e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2358ae(C2371ar c2371ar, C2682i c2682i, List list, Map map, C2682i c2682i2, InterfaceC2379az interfaceC2379az) {
        super(c2682i);
        this.f396e = c2371ar;
        this.f392a = list;
        this.f393b = map;
        this.f394c = c2682i2;
        this.f395d = interfaceC2379az;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        C2406bz c2406bz;
        ArrayList m1027a = C2371ar.m1027a((Collection) this.f392a);
        try {
            c2504ap = this.f396e.f435e;
            str = this.f396e.f433c;
            Bundle m1022b = C2371ar.m1022b(this.f393b);
            C2371ar c2371ar = this.f396e;
            C2682i c2682i = this.f394c;
            c2406bz = c2371ar.f434d;
            ((InterfaceC2572s) c2504ap.m796b()).mo665c(str, m1027a, m1022b, new BinderC2369ap(c2371ar, c2682i, c2406bz, this.f395d));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "getPackStates(%s)", this.f392a);
            this.f394c.m455b((Exception) new RuntimeException(e));
        }
    }
}
