package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractRunnableC2495ag;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.appupdate.l */
/* loaded from: classes3.dex */
public final class C2339l extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ C2682i f312a;

    /* renamed from: b */
    final /* synthetic */ String f313b;

    /* renamed from: c */
    final /* synthetic */ C2343p f314c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2339l(C2343p c2343p, C2682i c2682i, C2682i c2682i2, String str) {
        super(c2682i);
        this.f314c = c2343p;
        this.f312a = c2682i2;
        this.f313b = str;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    protected final void mo565a() {
        C2494af c2494af;
        String str;
        Bundle m1060d;
        try {
            str = this.f314c.f323d;
            m1060d = C2343p.m1060d();
            this.f314c.f322a.m796b().mo676b(str, m1060d, new BinderC2341n(this.f314c, this.f312a));
        } catch (RemoteException e) {
            c2494af = C2343p.f320b;
            c2494af.m807a(e, "completeUpdate(%s)", this.f313b);
            this.f312a.m455b((Exception) new RuntimeException(e));
        }
    }
}
