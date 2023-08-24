package com.google.android.play.core.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.IInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.internal.aj */
/* loaded from: classes3.dex */
public final class C2498aj extends AbstractRunnableC2495ag {

    /* renamed from: a */
    final /* synthetic */ C2504ap f810a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2498aj(C2504ap c2504ap) {
        this.f810a = c2504ap;
    }

    @Override // com.google.android.play.core.internal.AbstractRunnableC2495ag
    /* renamed from: a */
    public final void mo565a() {
        IInterface iInterface;
        C2494af c2494af;
        Context context;
        ServiceConnection serviceConnection;
        iInterface = this.f810a.f826l;
        if (iInterface != null) {
            c2494af = this.f810a.f817c;
            c2494af.m805c("Unbind from service.", new Object[0]);
            context = this.f810a.f816b;
            serviceConnection = this.f810a.f825k;
            context.unbindService(serviceConnection);
            this.f810a.f820f = false;
            this.f810a.f826l = null;
            this.f810a.f825k = null;
        }
    }
}
