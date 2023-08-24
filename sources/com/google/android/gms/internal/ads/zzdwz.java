package com.google.android.gms.internal.ads;

import io.invertase.googlemobileads.ReactNativeGoogleMobileAdsEvent;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdwz implements zzdft, zzdem, zzddb {
    private final zzdxj zza;
    private final zzdxt zzb;

    public zzdwz(zzdxj zzdxjVar, zzdxt zzdxtVar) {
        this.zza = zzdxjVar;
        this.zzb = zzdxtVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        this.zza.zza().put("action", "ftl");
        this.zza.zza().put("ftl", String.valueOf(zzeVar.zza));
        this.zza.zza().put("ed", zzeVar.zzc);
        this.zzb.zze(this.zza.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
        this.zza.zzb(zzfdeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
        this.zza.zzc(zzcbaVar.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        this.zza.zza().put("action", ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_LOADED);
        this.zzb.zze(this.zza.zza());
    }
}
