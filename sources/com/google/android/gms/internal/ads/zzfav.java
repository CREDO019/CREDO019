package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfav implements zzeoe {
    private final Context zza;
    private final Executor zzb;
    private final zzcok zzc;
    private final zzeno zzd;
    private final zzfbv zze;
    private zzbjt zzf;
    private final zzfje zzg;
    private final zzfdl zzh;
    private zzfyx zzi;

    public zzfav(Context context, Executor executor, zzcok zzcokVar, zzeno zzenoVar, zzfbv zzfbvVar, zzfdl zzfdlVar) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzcokVar;
        this.zzd = zzenoVar;
        this.zzh = zzfdlVar;
        this.zze = zzfbvVar;
        this.zzg = zzcokVar.zzy();
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zza() {
        zzfyx zzfyxVar = this.zzi;
        return (zzfyxVar == null || zzfyxVar.isDone()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zzb(com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzeoc zzeocVar, zzeod zzeodVar) {
        zzdmf zzf;
        zzfjc zzfjcVar;
        if (str == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Ad unit ID should not be null for interstitial ad.");
            this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfap
                @Override // java.lang.Runnable
                public final void run() {
                    zzfav.this.zzh();
                }
            });
            return false;
        } else if (zza()) {
            return false;
        } else {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue() && zzlVar.zzf) {
                this.zzc.zzk().zzl(true);
            }
            com.google.android.gms.ads.internal.client.zzq zzqVar = ((zzfao) zzeocVar).zza;
            zzfdl zzfdlVar = this.zzh;
            zzfdlVar.zzs(str);
            zzfdlVar.zzr(zzqVar);
            zzfdlVar.zzE(zzlVar);
            zzfdn zzG = zzfdlVar.zzG();
            zzfir zzb = zzfiq.zzb(this.zza, zzfjb.zzf(zzG), 4, zzlVar);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgV)).booleanValue()) {
                zzdme zzg = this.zzc.zzg();
                zzdci zzdciVar = new zzdci();
                zzdciVar.zzc(this.zza);
                zzdciVar.zzf(zzG);
                zzg.zze(zzdciVar.zzg());
                zzdii zzdiiVar = new zzdii();
                zzdiiVar.zzj(this.zzd, this.zzb);
                zzdiiVar.zzk(this.zzd, this.zzb);
                zzg.zzd(zzdiiVar.zzn());
                zzg.zzc(new zzely(this.zzf));
                zzf = zzg.zzf();
            } else {
                zzdii zzdiiVar2 = new zzdii();
                zzfbv zzfbvVar = this.zze;
                if (zzfbvVar != null) {
                    zzdiiVar2.zze(zzfbvVar, this.zzb);
                    zzdiiVar2.zzf(this.zze, this.zzb);
                    zzdiiVar2.zzb(this.zze, this.zzb);
                }
                zzdme zzg2 = this.zzc.zzg();
                zzdci zzdciVar2 = new zzdci();
                zzdciVar2.zzc(this.zza);
                zzdciVar2.zzf(zzG);
                zzg2.zze(zzdciVar2.zzg());
                zzdiiVar2.zzj(this.zzd, this.zzb);
                zzdiiVar2.zze(this.zzd, this.zzb);
                zzdiiVar2.zzf(this.zzd, this.zzb);
                zzdiiVar2.zzb(this.zzd, this.zzb);
                zzdiiVar2.zza(this.zzd, this.zzb);
                zzdiiVar2.zzl(this.zzd, this.zzb);
                zzdiiVar2.zzk(this.zzd, this.zzb);
                zzdiiVar2.zzi(this.zzd, this.zzb);
                zzdiiVar2.zzc(this.zzd, this.zzb);
                zzg2.zzd(zzdiiVar2.zzn());
                zzg2.zzc(new zzely(this.zzf));
                zzf = zzg2.zzf();
            }
            zzdmf zzdmfVar = zzf;
            if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
                zzfjc zzf2 = zzdmfVar.zzf();
                zzf2.zzh(4);
                zzf2.zzb(zzlVar.zzp);
                zzfjcVar = zzf2;
            } else {
                zzfjcVar = null;
            }
            zzdaf zza = zzdmfVar.zza();
            zzfyx zzh = zza.zzh(zza.zzi());
            this.zzi = zzh;
            zzfyo.zzr(zzh, new zzfau(this, zzeodVar, zzfjcVar, zzb, zzdmfVar), this.zzb);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzh() {
        this.zzd.zza(zzfem.zzd(6, null, null));
    }

    public final void zzi(zzbjt zzbjtVar) {
        this.zzf = zzbjtVar;
    }
}
