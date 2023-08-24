package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.C2494af;
import com.google.android.play.core.tasks.C2682i;

/* renamed from: com.google.android.play.core.review.g */
/* loaded from: classes3.dex */
final class BinderC2590g extends BinderC2589f<ReviewInfo> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC2590g(C2591h c2591h, C2682i c2682i) {
        super(c2591h, new C2494af("OnRequestInstallCallback"), c2682i);
    }

    @Override // com.google.android.play.core.review.BinderC2589f, com.google.android.play.core.internal.InterfaceC2492ad
    /* renamed from: a */
    public final void mo622a(Bundle bundle) throws RemoteException {
        super.mo622a(bundle);
        this.f891b.m454b((C2682i<T>) ReviewInfo.m624a((PendingIntent) bundle.get("confirmation_intent")));
    }
}
