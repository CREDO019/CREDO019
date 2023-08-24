package com.google.android.gms.internal.p015authapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-auth@@19.2.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zbk */
/* loaded from: classes2.dex */
final class zbk extends zbd {
    private final BaseImplementation.ResultHolder<Status> zba;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zbk(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zba = resultHolder;
    }

    @Override // com.google.android.gms.internal.p015authapi.zbd, com.google.android.gms.internal.p015authapi.zbs
    public final void zbc(Status status) {
        this.zba.setResult(status);
    }
}
