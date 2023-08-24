package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejn extends zzejp {
    private final zzcok zza;
    private final zzdmw zzb;
    private final zzdci zzc;
    private final zzdik zzd;

    public zzejn(zzcok zzcokVar, zzdmw zzdmwVar, zzdci zzdciVar, zzdik zzdikVar) {
        this.zza = zzcokVar;
        this.zzb = zzdmwVar;
        this.zzc = zzdciVar;
        this.zzd = zzdikVar;
    }

    @Override // com.google.android.gms.internal.ads.zzejp
    protected final zzfyx zzc(zzfdn zzfdnVar, Bundle bundle) {
        zzdna zzh = this.zza.zzh();
        zzdci zzdciVar = this.zzc;
        zzdciVar.zzf(zzfdnVar);
        zzdciVar.zzd(bundle);
        zzh.zzf(zzdciVar.zzg());
        zzh.zze(this.zzd);
        zzh.zzd(this.zzb);
        zzh.zzc(new zzcwx(null));
        zzdaf zza = zzh.zzg().zza();
        return zza.zzh(zza.zzi());
    }
}
