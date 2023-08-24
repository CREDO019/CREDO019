package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.appupdate.k */
/* loaded from: classes3.dex */
public final class C2338k extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ String f309a;

    /* renamed from: b */
    final /* synthetic */ C2682i f310b;

    /* renamed from: c */
    final /* synthetic */ C2343p f311c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2338k(C2343p c2343p, C2682i c2682i, String str, C2682i c2682i2) {
        super(c2682i);
        this.f311c = c2343p;
        this.f309a = str;
        this.f310b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        try {
            str = this.f311c.f323d;
            this.f311c.f322a.m796b().mo677a(str, C2343p.m1065a(this.f311c, this.f309a), new BinderC2342o(this.f311c, this.f310b, this.f309a));
        } catch (RemoteException e) {
            c2494af = C2343p.f320b;
            c2494af.m807a(e, "requestUpdateInfo(%s)", this.f309a);
            this.f310b.m455b((Exception) new RuntimeException(e));
        }
    }
}
