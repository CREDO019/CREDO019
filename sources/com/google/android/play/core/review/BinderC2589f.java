package com.google.android.play.core.review;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.AbstractBinderC2491ac;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.review.f */
/* loaded from: classes3.dex */
class BinderC2589f<T> extends AbstractBinderC2491ac {

    /* renamed from: a */
    final C2494af f890a;

    /* renamed from: b */
    final C2682i<T> f891b;

    /* renamed from: c */
    final /* synthetic */ C2591h f892c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2589f(C2591h c2591h, C2494af c2494af, C2682i<T> c2682i) {
        this.f892c = c2591h;
        this.f890a = c2494af;
        this.f891b = c2682i;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2492ad
    /* renamed from: a */
    public void mo622a(Bundle bundle) throws RemoteException {
        this.f892c.f894a.m801a();
        this.f890a.m805c("onGetLaunchReviewFlowInfo", new Object[0]);
    }
}
