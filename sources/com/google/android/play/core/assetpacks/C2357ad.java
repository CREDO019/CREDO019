package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.internal.C2504ap;
import com.google.android.play.core.internal.InterfaceC2572s;
import com.google.android.play.core.tasks.C2682i;
import java.util.Map;

/* renamed from: com.google.android.play.core.assetpacks.ad */
/* loaded from: classes3.dex */
final class C2357ad extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ Map f389a;

    /* renamed from: b */
    final /* synthetic */ C2682i f390b;

    /* renamed from: c */
    final /* synthetic */ C2371ar f391c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2357ad(C2371ar c2371ar, C2682i c2682i, Map map, C2682i c2682i2) {
        super(c2682i);
        this.f391c = c2371ar;
        this.f389a = map;
        this.f390b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        try {
            c2504ap = this.f391c.f435e;
            str = this.f391c.f433c;
            ((InterfaceC2572s) c2504ap.m796b()).mo671a(str, C2371ar.m1022b(this.f389a), new BinderC2366am(this.f391c, this.f390b));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "syncPacks", new Object[0]);
            this.f390b.m455b((Exception) new RuntimeException(e));
        }
    }
}
