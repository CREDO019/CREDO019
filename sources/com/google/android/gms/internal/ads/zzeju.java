package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeju extends zzejp {
    private final zzcok zza;
    private final zzdci zzb;
    private final zzdik zzc;

    public zzeju(zzcok zzcokVar, zzdci zzdciVar, zzdik zzdikVar) {
        this.zza = zzcokVar;
        this.zzb = zzdciVar;
        this.zzc = zzdikVar;
    }

    @Override // com.google.android.gms.internal.ads.zzejp
    protected final zzfyx zzc(zzfdn zzfdnVar, Bundle bundle) {
        zzdue zzi = this.zza.zzi();
        zzdci zzdciVar = this.zzb;
        zzdciVar.zzf(zzfdnVar);
        zzdciVar.zzd(bundle);
        zzi.zzd(zzdciVar.zzg());
        zzi.zzc(this.zzc);
        zzdaf zzb = zzi.zze().zzb();
        return zzb.zzh(zzb.zzi());
    }
}
