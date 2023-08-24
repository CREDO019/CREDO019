package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.nativead.NativeAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbym extends zzbnn {
    private final NativeAd.OnNativeAdLoadedListener zza;

    public zzbym(NativeAd.OnNativeAdLoadedListener onNativeAdLoadedListener) {
        this.zza = onNativeAdLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzbno
    public final void zze(zzbnx zzbnxVar) {
        this.zza.onNativeAdLoaded(new zzbyf(zzbnxVar));
    }
}
