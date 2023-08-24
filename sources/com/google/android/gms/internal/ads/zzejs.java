package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejs extends zzejp {
    private final zzcok zza;
    private final zzdci zzb;
    private final zzely zzc;
    private final zzdik zzd;

    public zzejs(zzcok zzcokVar, zzdci zzdciVar, zzely zzelyVar, zzdik zzdikVar) {
        this.zza = zzcokVar;
        this.zzb = zzdciVar;
        this.zzc = zzelyVar;
        this.zzd = zzdikVar;
    }

    @Override // com.google.android.gms.internal.ads.zzejp
    protected final zzfyx zzc(zzfdn zzfdnVar, Bundle bundle) {
        zzdme zzg = this.zza.zzg();
        zzdci zzdciVar = this.zzb;
        zzdciVar.zzf(zzfdnVar);
        zzdciVar.zzd(bundle);
        zzg.zze(zzdciVar.zzg());
        zzg.zzd(this.zzd);
        zzg.zzc(this.zzc);
        zzdaf zza = zzg.zzf().zza();
        return zza.zzh(zza.zzi());
    }
}
