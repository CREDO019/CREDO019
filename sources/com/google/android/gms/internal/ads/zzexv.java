package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzexv implements zzfyk {
    final /* synthetic */ zzeod zza;
    final /* synthetic */ zzfjc zzb;
    final /* synthetic */ zzfir zzc;
    final /* synthetic */ zzexx zzd;
    final /* synthetic */ zzexy zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzexv(zzexy zzexyVar, zzeod zzeodVar, zzfjc zzfjcVar, zzfir zzfirVar, zzexx zzexxVar) {
        this.zze = zzexyVar;
        this.zza = zzeodVar;
        this.zzb = zzfjcVar;
        this.zzc = zzfirVar;
        this.zzd = zzexxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzfah zzfahVar;
        final com.google.android.gms.ads.internal.client.zze zza;
        zzeyo zzeyoVar;
        zzdcg zzm;
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        Executor executor;
        zzfahVar = this.zze.zze;
        zzcwi zzcwiVar = (zzcwi) zzfahVar.zzd();
        if (zzcwiVar == null) {
            zza = zzfem.zzb(th, null);
        } else {
            zza = zzcwiVar.zzb().zza(th);
        }
        synchronized (this.zze) {
            this.zze.zzj = null;
            if (zzcwiVar != null) {
                zzcwiVar.zzc().zza(zza);
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgU)).booleanValue()) {
                    executor = this.zze.zzc;
                    executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzexu
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzeyo zzeyoVar2;
                            zzexv zzexvVar = zzexv.this;
                            com.google.android.gms.ads.internal.client.zze zzeVar = zza;
                            zzeyoVar2 = zzexvVar.zze.zzd;
                            zzeyoVar2.zza(zzeVar);
                        }
                    });
                }
            } else {
                zzeyoVar = this.zze.zzd;
                zzeyoVar.zza(zza);
                zzm = this.zze.zzm(this.zzd);
                ((zzcwi) zzm.zzh()).zzb().zzc().zzd();
            }
            zzfeh.zzb(zza.zza, th, "AppOpenAdLoader.onFailure");
            this.zza.zza();
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzc(zza);
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(false);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zzh;
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
        zzeyo zzeyoVar;
        zzczc zzczcVar = (zzczc) obj;
        synchronized (this.zze) {
            this.zze.zzj = null;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgU)).booleanValue()) {
                zzdhc zzn = zzczcVar.zzn();
                zzeyoVar = this.zze.zzd;
                zzn.zzb(zzeyoVar);
            }
            this.zza.zzb(zzczcVar);
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzf(zzczcVar.zzp().zzb);
                zzfjcVar.zze(zzczcVar.zzl().zzg());
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(true);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zzh;
                zzfir zzfirVar2 = this.zzc;
                zzfirVar2.zzb(zzczcVar.zzp().zzb);
                zzfirVar2.zzc(zzczcVar.zzl().zzg());
                zzfirVar2.zze(true);
                zzfjeVar.zzb(zzfirVar2.zzj());
            }
        }
    }
}
