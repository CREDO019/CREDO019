package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcdc extends zzccp {
    private final RewardedInterstitialAdLoadCallback zza;
    private final zzcdd zzb;

    public zzcdc(RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback, zzcdd zzcddVar) {
        this.zza = rewardedInterstitialAdLoadCallback;
        this.zzb = zzcddVar;
    }

    @Override // com.google.android.gms.internal.ads.zzccq
    public final void zze(int r1) {
    }

    @Override // com.google.android.gms.internal.ads.zzccq
    public final void zzf(com.google.android.gms.ads.internal.client.zze zzeVar) {
        RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback = this.zza;
        if (rewardedInterstitialAdLoadCallback != null) {
            rewardedInterstitialAdLoadCallback.onAdFailedToLoad(zzeVar.zzb());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccq
    public final void zzg() {
        zzcdd zzcddVar;
        RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback = this.zza;
        if (rewardedInterstitialAdLoadCallback == null || (zzcddVar = this.zzb) == null) {
            return;
        }
        rewardedInterstitialAdLoadCallback.onAdLoaded(zzcddVar);
    }
}
