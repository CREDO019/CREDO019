package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.C2134Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
final class zaq implements PendingResultUtil.ResultConverter {
    final /* synthetic */ C2134Response zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaq(C2134Response c2134Response) {
        this.zaa = c2134Response;
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* bridge */ /* synthetic */ Object convert(Result result) {
        this.zaa.setResult(result);
        return this.zaa;
    }
}
