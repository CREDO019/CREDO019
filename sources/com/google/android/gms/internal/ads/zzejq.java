package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.ViewGroup;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejq extends zzejp {
    private final zzcok zza;
    private final zzdci zzb;
    private final zzely zzc;
    private final zzdik zzd;
    private final zzdmw zze;
    private final zzdfn zzf;
    private final ViewGroup zzg;
    private final zzdht zzh;

    public zzejq(zzcok zzcokVar, zzdci zzdciVar, zzely zzelyVar, zzdik zzdikVar, zzdmw zzdmwVar, zzdfn zzdfnVar, ViewGroup viewGroup, zzdht zzdhtVar) {
        this.zza = zzcokVar;
        this.zzb = zzdciVar;
        this.zzc = zzelyVar;
        this.zzd = zzdikVar;
        this.zze = zzdmwVar;
        this.zzf = zzdfnVar;
        this.zzg = viewGroup;
        this.zzh = zzdhtVar;
    }

    @Override // com.google.android.gms.internal.ads.zzejp
    protected final zzfyx zzc(zzfdn zzfdnVar, Bundle bundle) {
        zzcxw zze = this.zza.zze();
        zzdci zzdciVar = this.zzb;
        zzdciVar.zzf(zzfdnVar);
        zzdciVar.zzd(bundle);
        zze.zzi(zzdciVar.zzg());
        zze.zzf(this.zzd);
        zze.zze(this.zzc);
        zze.zzd(this.zze);
        zze.zzg(new zzcyu(this.zzf, this.zzh));
        zze.zzc(new zzcwx(this.zzg));
        zzdaf zzd = zze.zzj().zzd();
        return zzd.zzh(zzd.zzi());
    }
}
