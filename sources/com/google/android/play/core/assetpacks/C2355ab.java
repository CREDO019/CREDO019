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

/* renamed from: com.google.android.play.core.assetpacks.ab */
/* loaded from: classes3.dex */
final class C2355ab extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ List f381a;

    /* renamed from: b */
    final /* synthetic */ Map f382b;

    /* renamed from: c */
    final /* synthetic */ C2682i f383c;

    /* renamed from: d */
    final /* synthetic */ List f384d;

    /* renamed from: e */
    final /* synthetic */ C2371ar f385e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2355ab(C2371ar c2371ar, C2682i c2682i, List list, Map map, C2682i c2682i2, List list2) {
        super(c2682i);
        this.f385e = c2371ar;
        this.f381a = list;
        this.f382b = map;
        this.f383c = c2682i2;
        this.f384d = list2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        C2504ap c2504ap;
        String str;
        C2406bz c2406bz;
        ArrayList m1027a = C2371ar.m1027a((Collection) this.f381a);
        try {
            c2504ap = this.f385e.f435e;
            str = this.f385e.f433c;
            Bundle m1022b = C2371ar.m1022b(this.f382b);
            C2371ar c2371ar = this.f385e;
            C2682i c2682i = this.f383c;
            c2406bz = c2371ar.f434d;
            ((InterfaceC2572s) c2504ap.m796b()).mo670a(str, m1027a, m1022b, new BinderC2370aq(c2371ar, c2682i, c2406bz, this.f384d));
        } catch (RemoteException e) {
            c2494af = C2371ar.f431a;
            c2494af.m807a(e, "startDownload(%s)", this.f381a);
            this.f383c.m455b((Exception) new RuntimeException(e));
        }
    }
}
