package com.google.android.gms.internal.ads;

import io.invertase.googlemobileads.ReactNativeGoogleMobileAdsEvent;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeef implements zzdft, zzdem, zzddb {
    private final zzfhy zza;
    private final zzfhz zzb;
    private final zzcga zzc;

    public zzeef(zzfhy zzfhyVar, zzfhz zzfhzVar, zzcga zzcgaVar) {
        this.zza = zzfhyVar;
        this.zzb = zzfhzVar;
        this.zzc = zzcgaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzfhy zzfhyVar = this.zza;
        zzfhyVar.zza("action", "ftl");
        zzfhyVar.zza("ftl", String.valueOf(zzeVar.zza));
        zzfhyVar.zza("ed", zzeVar.zzc);
        this.zzb.zzb(this.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
        this.zza.zzh(zzfdeVar, this.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
        this.zza.zzi(zzcbaVar.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        zzfhz zzfhzVar = this.zzb;
        zzfhy zzfhyVar = this.zza;
        zzfhyVar.zza("action", ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_LOADED);
        zzfhzVar.zzb(zzfhyVar);
    }
}
