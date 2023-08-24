package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.appopen.AppOpenAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbda extends zzbdh {
    private final AppOpenAd.AppOpenAdLoadCallback zza;
    private final String zzb;

    public zzbda(AppOpenAd.AppOpenAdLoadCallback appOpenAdLoadCallback, String str) {
        this.zza = appOpenAdLoadCallback;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.ads.zzbdi
    public final void zzb(int r1) {
    }

    @Override // com.google.android.gms.internal.ads.zzbdi
    public final void zzc(com.google.android.gms.ads.internal.client.zze zzeVar) {
        if (this.zza != null) {
            this.zza.onAdFailedToLoad(zzeVar.zzb());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdi
    public final void zzd(zzbdf zzbdfVar) {
        if (this.zza != null) {
            this.zza.onAdLoaded(new zzbdb(zzbdfVar, this.zzb));
        }
    }
}
