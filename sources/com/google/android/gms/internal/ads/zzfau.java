package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfau implements zzfyk {
    final /* synthetic */ zzeod zza;
    final /* synthetic */ zzfjc zzb;
    final /* synthetic */ zzfir zzc;
    final /* synthetic */ zzdmf zzd;
    final /* synthetic */ zzfav zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfau(zzfav zzfavVar, zzeod zzeodVar, zzfjc zzfjcVar, zzfir zzfirVar, zzdmf zzdmfVar) {
        this.zze = zzfavVar;
        this.zza = zzeodVar;
        this.zzb = zzfjcVar;
        this.zzc = zzfirVar;
        this.zzd = zzdmfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        Executor executor;
        Executor executor2;
        final com.google.android.gms.ads.internal.client.zze zza = this.zzd.zza().zza(th);
        synchronized (this.zze) {
            this.zze.zzi = null;
            this.zzd.zzb().zza(zza);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgV)).booleanValue()) {
                executor = this.zze.zzb;
                executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfas
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzeno zzenoVar;
                        zzfau zzfauVar = zzfau.this;
                        com.google.android.gms.ads.internal.client.zze zzeVar = zza;
                        zzenoVar = zzfauVar.zze.zzd;
                        zzenoVar.zza(zzeVar);
                    }
                });
                executor2 = this.zze.zzb;
                executor2.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfat
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzfbv zzfbvVar;
                        zzfau zzfauVar = zzfau.this;
                        com.google.android.gms.ads.internal.client.zze zzeVar = zza;
                        zzfbvVar = zzfauVar.zze.zze;
                        zzfbvVar.zza(zzeVar);
                    }
                });
            }
            zzfeh.zzb(zza.zza, th, "InterstitialAdLoader.onFailure");
            this.zza.zza();
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzc(zza);
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(false);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zzg;
                zzfir zzfirVar2 = this.zzc;
                zzfirVar2.zza(zza);
                zzfirVar2.zze(false);
                zzfjeVar.zzb(zzfirVar2.zzj());
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        Executor executor;
        Executor executor2;
        zzeno zzenoVar;
        zzfbv zzfbvVar;
        zzdle zzdleVar = (zzdle) obj;
        synchronized (this.zze) {
            this.zze.zzi = null;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgV)).booleanValue()) {
                zzdhc zzn = zzdleVar.zzn();
                zzenoVar = this.zze.zzd;
                zzn.zza(zzenoVar);
                zzfbvVar = this.zze.zze;
                zzn.zzd(zzfbvVar);
            }
            this.zza.zzb(zzdleVar);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgV)).booleanValue()) {
                executor = this.zze.zzb;
                executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfaq
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzeno zzenoVar2;
                        zzenoVar2 = zzfau.this.zze.zzd;
                        zzenoVar2.zzn();
                    }
                });
                executor2 = this.zze.zzb;
                executor2.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfar
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzfbv zzfbvVar2;
                        zzfbvVar2 = zzfau.this.zze.zze;
                        zzfbvVar2.zzn();
                    }
                });
            }
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzf(zzdleVar.zzp().zzb);
                zzfjcVar.zze(zzdleVar.zzl().zzg());
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(true);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zzg;
                zzfir zzfirVar2 = this.zzc;
                zzfirVar2.zzb(zzdleVar.zzp().zzb);
                zzfirVar2.zzc(zzdleVar.zzl().zzg());
                zzfirVar2.zze(true);
                zzfjeVar.zzb(zzfirVar2.zzj());
            }
        }
    }
}
