package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfcf implements zzeoe {
    private final Context zza;
    private final Executor zzb;
    private final zzcok zzc;
    private final zzfbv zzd;
    private final zzfah zze;
    private final zzfdf zzf;
    private final zzfje zzg;
    private final zzfdl zzh;
    private zzfyx zzi;

    public zzfcf(Context context, Executor executor, zzcok zzcokVar, zzfah zzfahVar, zzfbv zzfbvVar, zzfdl zzfdlVar, zzfdf zzfdfVar) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzcokVar;
        this.zze = zzfahVar;
        this.zzd = zzfbvVar;
        this.zzh = zzfdlVar;
        this.zzf = zzfdfVar;
        this.zzg = zzcokVar.zzy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzdue zzk(zzfaf zzfafVar) {
        zzfce zzfceVar = (zzfce) zzfafVar;
        zzdue zzi = this.zzc.zzi();
        zzdci zzdciVar = new zzdci();
        zzdciVar.zzc(this.zza);
        zzdciVar.zzf(zzfceVar.zza);
        String str = zzfceVar.zzb;
        zzdciVar.zze(this.zzf);
        zzi.zzd(zzdciVar.zzg());
        zzi.zzc(new zzdii().zzn());
        return zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zza() {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zzb(com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzeoc zzeocVar, zzeod zzeodVar) throws RemoteException {
        zzfjc zzfjcVar;
        zzcbx zzcbxVar = new zzcbx(zzlVar, str);
        if (zzcbxVar.zzb == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Ad unit ID should not be null for rewarded video ad.");
            this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfby
                @Override // java.lang.Runnable
                public final void run() {
                    zzfcf.this.zzi();
                }
            });
        } else {
            zzfyx zzfyxVar = this.zzi;
            if (zzfyxVar == null || zzfyxVar.isDone()) {
                if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
                    zzfah zzfahVar = this.zze;
                    if (zzfahVar.zzd() != null) {
                        zzfjc zzh = ((zzduf) zzfahVar.zzd()).zzh();
                        zzh.zzh(5);
                        zzh.zzb(zzcbxVar.zza.zzp);
                        zzfjcVar = zzh;
                        zzfeh.zza(this.zza, zzcbxVar.zza.zzf);
                        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue() && zzcbxVar.zza.zzf) {
                            this.zzc.zzk().zzl(true);
                        }
                        zzfdl zzfdlVar = this.zzh;
                        zzfdlVar.zzs(zzcbxVar.zzb);
                        zzfdlVar.zzr(com.google.android.gms.ads.internal.client.zzq.zzd());
                        zzfdlVar.zzE(zzcbxVar.zza);
                        zzfdn zzG = zzfdlVar.zzG();
                        zzfir zzb = zzfiq.zzb(this.zza, zzfjb.zzf(zzG), 5, zzcbxVar.zza);
                        zzfce zzfceVar = new zzfce(null);
                        zzfceVar.zza = zzG;
                        zzfceVar.zzb = null;
                        zzfyx zzc = this.zze.zzc(new zzfai(zzfceVar, null), new zzfag() { // from class: com.google.android.gms.internal.ads.zzfbz
                            @Override // com.google.android.gms.internal.ads.zzfag
                            public final zzdcg zza(zzfaf zzfafVar) {
                                zzdue zzk;
                                zzk = zzfcf.this.zzk(zzfafVar);
                                return zzk;
                            }
                        }, null);
                        this.zzi = zzc;
                        zzfyo.zzr(zzc, new zzfcc(this, zzeodVar, zzfjcVar, zzb, zzfceVar), this.zzb);
                        return true;
                    }
                }
                zzfjcVar = null;
                zzfeh.zza(this.zza, zzcbxVar.zza.zzf);
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue()) {
                    this.zzc.zzk().zzl(true);
                }
                zzfdl zzfdlVar2 = this.zzh;
                zzfdlVar2.zzs(zzcbxVar.zzb);
                zzfdlVar2.zzr(com.google.android.gms.ads.internal.client.zzq.zzd());
                zzfdlVar2.zzE(zzcbxVar.zza);
                zzfdn zzG2 = zzfdlVar2.zzG();
                zzfir zzb2 = zzfiq.zzb(this.zza, zzfjb.zzf(zzG2), 5, zzcbxVar.zza);
                zzfce zzfceVar2 = new zzfce(null);
                zzfceVar2.zza = zzG2;
                zzfceVar2.zzb = null;
                zzfyx zzc2 = this.zze.zzc(new zzfai(zzfceVar2, null), new zzfag() { // from class: com.google.android.gms.internal.ads.zzfbz
                    @Override // com.google.android.gms.internal.ads.zzfag
                    public final zzdcg zza(zzfaf zzfafVar) {
                        zzdue zzk;
                        zzk = zzfcf.this.zzk(zzfafVar);
                        return zzk;
                    }
                }, null);
                this.zzi = zzc2;
                zzfyo.zzr(zzc2, new zzfcc(this, zzeodVar, zzfjcVar, zzb2, zzfceVar2), this.zzb);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzi() {
        this.zzd.zza(zzfem.zzd(6, null, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(int r2) {
        this.zzh.zzo().zza(r2);
    }
}
