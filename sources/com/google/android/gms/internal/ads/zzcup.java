package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcup implements zzddb {
    private final zzfcv zza;
    private final zzfde zzb;
    private final zzfjq zzc;
    private final zzfju zzd;

    public zzcup(zzfde zzfdeVar, zzfju zzfjuVar, zzfjq zzfjqVar) {
        this.zzb = zzfdeVar;
        this.zzd = zzfjuVar;
        this.zzc = zzfjqVar;
        this.zza = zzfdeVar.zzb.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        this.zzd.zzd(this.zzc.zzc(this.zzb, null, this.zza.zza));
    }
}
