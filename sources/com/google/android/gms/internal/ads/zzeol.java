package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeol implements zzeoe {
    private final zzfdl zza;
    private final zzcok zzb;
    private final Context zzc;
    private final zzeob zzd;
    private final zzfje zze;
    private zzczq zzf;

    public zzeol(zzcok zzcokVar, Context context, zzeob zzeobVar, zzfdl zzfdlVar) {
        this.zzb = zzcokVar;
        this.zzc = context;
        this.zzd = zzeobVar;
        this.zza = zzfdlVar;
        this.zze = zzcokVar.zzy();
        zzfdlVar.zzu(zzeobVar.zzd());
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zza() {
        zzczq zzczqVar = this.zzf;
        return zzczqVar != null && zzczqVar.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zzb(com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzeoc zzeocVar, zzeod zzeodVar) throws RemoteException {
        zzfjc zzfjcVar;
        com.google.android.gms.ads.internal.zzt.zzq();
        if (com.google.android.gms.ads.internal.util.zzs.zzD(this.zzc) && zzlVar.zzs == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Failed to load the ad because app ID is missing.");
            this.zzb.zzA().execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeog
                @Override // java.lang.Runnable
                public final void run() {
                    zzeol.this.zzf();
                }
            });
            return false;
        } else if (str == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Ad unit ID should not be null for NativeAdLoader.");
            this.zzb.zzA().execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeoh
                @Override // java.lang.Runnable
                public final void run() {
                    zzeol.this.zzg();
                }
            });
            return false;
        } else {
            zzfeh.zza(this.zzc, zzlVar.zzf);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue() && zzlVar.zzf) {
                this.zzb.zzk().zzl(true);
            }
            int r11 = ((zzeof) zzeocVar).zza;
            zzfdl zzfdlVar = this.zza;
            zzfdlVar.zzE(zzlVar);
            zzfdlVar.zzz(r11);
            zzfdn zzG = zzfdlVar.zzG();
            zzfir zzb = zzfiq.zzb(this.zzc, zzfjb.zzf(zzG), 8, zzlVar);
            com.google.android.gms.ads.internal.client.zzbz zzbzVar = zzG.zzn;
            if (zzbzVar != null) {
                this.zzd.zzd().zzi(zzbzVar);
            }
            zzdna zzh = this.zzb.zzh();
            zzdci zzdciVar = new zzdci();
            zzdciVar.zzc(this.zzc);
            zzdciVar.zzf(zzG);
            zzh.zzf(zzdciVar.zzg());
            zzdii zzdiiVar = new zzdii();
            zzdiiVar.zzk(this.zzd.zzd(), this.zzb.zzA());
            zzh.zze(zzdiiVar.zzn());
            zzh.zzd(this.zzd.zzc());
            zzh.zzc(new zzcwx(null));
            zzdnb zzg = zzh.zzg();
            if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
                zzfjc zzf = zzg.zzf();
                zzf.zzh(8);
                zzf.zzb(zzlVar.zzp);
                zzfjcVar = zzf;
            } else {
                zzfjcVar = null;
            }
            this.zzb.zzw().zzc(1);
            zzfyy zzfyyVar = zzcha.zza;
            zzguz.zzb(zzfyyVar);
            ScheduledExecutorService zzB = this.zzb.zzB();
            zzdaf zza = zzg.zza();
            zzczq zzczqVar = new zzczq(zzfyyVar, zzB, zza.zzh(zza.zzi()));
            this.zzf = zzczqVar;
            zzczqVar.zze(new zzeok(this, zzeodVar, zzfjcVar, zzb, zzg));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf() {
        this.zzd.zza().zza(zzfem.zzd(4, null, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg() {
        this.zzd.zza().zza(zzfem.zzd(6, null, null));
    }
}
