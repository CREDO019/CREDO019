package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzboi extends zzbnn {
    private final UnifiedNativeAd.OnUnifiedNativeAdLoadedListener zza;

    public zzboi(UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
        this.zza = onUnifiedNativeAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzbno
    public final void zze(zzbnx zzbnxVar) {
        this.zza.onUnifiedNativeAdLoaded(new zzbny(zzbnxVar));
    }
}
