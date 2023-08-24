package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.query.UpdateImpressionUrlsCallback;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbzs extends zzbzo {
    final /* synthetic */ UpdateImpressionUrlsCallback zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbzs(zzbzx zzbzxVar, UpdateImpressionUrlsCallback updateImpressionUrlsCallback) {
        this.zza = updateImpressionUrlsCallback;
    }

    @Override // com.google.android.gms.internal.ads.zzbzp
    public final void zze(String str) {
        this.zza.onFailure(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbzp
    public final void zzf(List list) {
        this.zza.onSuccess(list);
    }
}
