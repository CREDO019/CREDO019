package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeok implements zzfyk {
    final /* synthetic */ zzeod zza;
    final /* synthetic */ zzfjc zzb;
    final /* synthetic */ zzfir zzc;
    final /* synthetic */ zzdnb zzd;
    final /* synthetic */ zzeol zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeok(zzeol zzeolVar, zzeod zzeodVar, zzfjc zzfjcVar, zzfir zzfirVar, zzdnb zzdnbVar) {
        this.zze = zzeolVar;
        this.zza = zzeodVar;
        this.zzb = zzfjcVar;
        this.zzc = zzfirVar;
        this.zzd = zzdnbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzcok zzcokVar;
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        final com.google.android.gms.ads.internal.client.zze zza = this.zzd.zza().zza(th);
        this.zzd.zzb().zza(zza);
        zzcokVar = this.zze.zzb;
        zzcokVar.zzA().execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeoj
            @Override // java.lang.Runnable
            public final void run() {
                zzeob zzeobVar;
                zzeok zzeokVar = zzeok.this;
                com.google.android.gms.ads.internal.client.zze zzeVar = zza;
                zzeobVar = zzeokVar.zze.zzd;
                zzeobVar.zza().zza(zzeVar);
            }
        });
        zzfeh.zzb(zza.zza, th, "NativeAdLoader.onFailure");
        this.zza.zza();
        if (!((Boolean) zzbkh.zzc.zze()).booleanValue() || (zzfjcVar = this.zzb) == null) {
            zzfjeVar = this.zze.zze;
            zzfir zzfirVar = this.zzc;
            zzfirVar.zza(zza);
            zzfirVar.zze(false);
            zzfjeVar.zzb(zzfirVar.zzj());
            return;
        }
        zzfjcVar.zzc(zza);
        zzfir zzfirVar2 = this.zzc;
        zzfirVar2.zze(false);
        zzfjcVar.zza(zzfirVar2);
        zzfjcVar.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzeob zzeobVar;
        zzcok zzcokVar;
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        zzczc zzczcVar = (zzczc) obj;
        synchronized (this.zze) {
            zzdhc zzn = zzczcVar.zzn();
            zzeobVar = this.zze.zzd;
            zzn.zza(zzeobVar.zzd());
            this.zza.zzb(zzczcVar);
            zzcokVar = this.zze.zzb;
            zzcokVar.zzA().execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeoi
                @Override // java.lang.Runnable
                public final void run() {
                    zzeob zzeobVar2;
                    zzeobVar2 = zzeok.this.zze.zzd;
                    zzeobVar2.zzb().zzn();
                }
            });
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzf(zzczcVar.zzp().zzb);
                zzfjcVar.zze(zzczcVar.zzl().zzg());
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(true);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zze;
                zzfir zzfirVar2 = this.zzc;
                zzfirVar2.zzb(zzczcVar.zzp().zzb);
                zzfirVar2.zzc(zzczcVar.zzl().zzg());
                zzfirVar2.zze(true);
                zzfjeVar.zzb(zzfirVar2.zzj());
            }
        }
    }
}
