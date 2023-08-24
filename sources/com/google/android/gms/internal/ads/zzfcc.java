package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfcc implements zzfyk {
    final /* synthetic */ zzeod zza;
    final /* synthetic */ zzfjc zzb;
    final /* synthetic */ zzfir zzc;
    final /* synthetic */ zzfce zzd;
    final /* synthetic */ zzfcf zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfcc(zzfcf zzfcfVar, zzeod zzeodVar, zzfjc zzfjcVar, zzfir zzfirVar, zzfce zzfceVar) {
        this.zze = zzfcfVar;
        this.zza = zzeodVar;
        this.zzb = zzfjcVar;
        this.zzc = zzfirVar;
        this.zzd = zzfceVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzfah zzfahVar;
        final com.google.android.gms.ads.internal.client.zze zza;
        zzfbv zzfbvVar;
        zzdue zzk;
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        Executor executor;
        zzfahVar = this.zze.zze;
        zzduf zzdufVar = (zzduf) zzfahVar.zzd();
        if (zzdufVar == null) {
            zza = zzfem.zzb(th, null);
        } else {
            zza = zzdufVar.zzb().zza(th);
        }
        synchronized (this.zze) {
            if (zzdufVar != null) {
                zzdufVar.zza().zza(zza);
                executor = this.zze.zzb;
                executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfcb
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzfbv zzfbvVar2;
                        zzfcc zzfccVar = zzfcc.this;
                        com.google.android.gms.ads.internal.client.zze zzeVar = zza;
                        zzfbvVar2 = zzfccVar.zze.zzd;
                        zzfbvVar2.zza(zzeVar);
                    }
                });
            } else {
                zzfbvVar = this.zze.zzd;
                zzfbvVar.zza(zza);
                zzk = this.zze.zzk(this.zzd);
                zzk.zze().zzb().zzc().zzd();
            }
            zzfeh.zzb(zza.zza, th, "RewardedAdLoader.onFailure");
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
        zzfbv zzfbvVar;
        Executor executor;
        final zzfbv zzfbvVar2;
        zzfbv zzfbvVar3;
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        zzdua zzduaVar = (zzdua) obj;
        synchronized (this.zze) {
            zzdhc zzn = zzduaVar.zzn();
            zzfbvVar = this.zze.zzd;
            zzn.zzd(zzfbvVar);
            this.zza.zzb(zzduaVar);
            zzfcf zzfcfVar = this.zze;
            executor = zzfcfVar.zzb;
            zzfbvVar2 = zzfcfVar.zzd;
            zzfbvVar2.getClass();
            executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfca
                @Override // java.lang.Runnable
                public final void run() {
                    zzfbv.this.zzn();
                }
            });
            zzfbvVar3 = this.zze.zzd;
            zzfbvVar3.zzv();
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzf(zzduaVar.zzp().zzb);
                zzfjcVar.zze(zzduaVar.zzl().zzg());
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(true);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zzg;
                zzfir zzfirVar2 = this.zzc;
                zzfirVar2.zzb(zzduaVar.zzp().zzb);
                zzfirVar2.zzc(zzduaVar.zzl().zzg());
                zzfirVar2.zze(true);
                zzfjeVar.zzb(zzfirVar2.zzj());
            }
        }
    }
}
