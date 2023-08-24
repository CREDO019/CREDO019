package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.splitinstall.ag */
/* loaded from: classes3.dex */
public final class C2617ag extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ List f957a;

    /* renamed from: b */
    final /* synthetic */ C2682i f958b;

    /* renamed from: c */
    final /* synthetic */ C2632av f959c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2617ag(C2632av c2632av, C2682i c2682i, List list, C2682i c2682i2) {
        super(c2682i);
        this.f959c = c2632av;
        this.f957a = list;
        this.f958b = c2682i2;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        try {
            str = this.f959c.f979d;
            this.f959c.f978a.m796b().mo728c(str, C2632av.m549a((Collection) this.f957a), C2632av.m546b(), new BinderC2624an(this.f959c, this.f958b));
        } catch (RemoteException e) {
            c2494af = C2632av.f976b;
            c2494af.m807a(e, "deferredInstall(%s)", this.f957a);
            this.f958b.m455b((Exception) new RuntimeException(e));
        }
    }
}
