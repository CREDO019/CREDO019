package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezc implements zzeoe {
    private final Context zza;
    private final Executor zzb;
    private final zzcok zzc;
    private final zzeno zzd;
    private final zzens zze;
    private final ViewGroup zzf;
    private zzbjt zzg;
    private final zzdfn zzh;
    private final zzfje zzi;
    private final zzdht zzj;
    private final zzfdl zzk;
    private zzfyx zzl;

    public zzezc(Context context, Executor executor, com.google.android.gms.ads.internal.client.zzq zzqVar, zzcok zzcokVar, zzeno zzenoVar, zzens zzensVar, zzfdl zzfdlVar, zzdht zzdhtVar) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzcokVar;
        this.zzd = zzenoVar;
        this.zze = zzensVar;
        this.zzk = zzfdlVar;
        this.zzh = zzcokVar.zzf();
        this.zzi = zzcokVar.zzy();
        this.zzf = new FrameLayout(context);
        this.zzj = zzdhtVar;
        zzfdlVar.zzr(zzqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zza() {
        zzfyx zzfyxVar = this.zzl;
        return (zzfyxVar == null || zzfyxVar.isDone()) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzeoe
    public final boolean zzb(com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzeoc zzeocVar, zzeod zzeodVar) throws RemoteException {
        zzcxx zzj;
        zzfjc zzfjcVar;
        if (str == null) {
            com.google.android.gms.ads.internal.util.zze.zzg("Ad unit ID should not be null for banner ad.");
            this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeyy
                @Override // java.lang.Runnable
                public final void run() {
                    zzezc.this.zzm();
                }
            });
            return false;
        } else if (zza()) {
            return false;
        } else {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue() && zzlVar.zzf) {
                this.zzc.zzk().zzl(true);
            }
            zzfdl zzfdlVar = this.zzk;
            zzfdlVar.zzs(str);
            zzfdlVar.zzE(zzlVar);
            zzfdn zzG = zzfdlVar.zzG();
            zzfir zzb = zzfiq.zzb(this.zza, zzfjb.zzf(zzG), 3, zzlVar);
            if (((Boolean) zzbkt.zzc.zze()).booleanValue() && this.zzk.zzg().zzk) {
                zzeno zzenoVar = this.zzd;
                if (zzenoVar != null) {
                    zzenoVar.zza(zzfem.zzd(7, null, null));
                }
                return false;
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgT)).booleanValue()) {
                zzcxw zze = this.zzc.zze();
                zzdci zzdciVar = new zzdci();
                zzdciVar.zzc(this.zza);
                zzdciVar.zzf(zzG);
                zze.zzi(zzdciVar.zzg());
                zzdii zzdiiVar = new zzdii();
                zzdiiVar.zzj(this.zzd, this.zzb);
                zzdiiVar.zzk(this.zzd, this.zzb);
                zze.zzf(zzdiiVar.zzn());
                zze.zze(new zzely(this.zzg));
                zze.zzd(new zzdmw(zzdoz.zza, null));
                zze.zzg(new zzcyu(this.zzh, this.zzj));
                zze.zzc(new zzcwx(this.zzf));
                zzj = zze.zzj();
            } else {
                zzcxw zze2 = this.zzc.zze();
                zzdci zzdciVar2 = new zzdci();
                zzdciVar2.zzc(this.zza);
                zzdciVar2.zzf(zzG);
                zze2.zzi(zzdciVar2.zzg());
                zzdii zzdiiVar2 = new zzdii();
                zzdiiVar2.zzj(this.zzd, this.zzb);
                zzdiiVar2.zza(this.zzd, this.zzb);
                zzdiiVar2.zza(this.zze, this.zzb);
                zzdiiVar2.zzl(this.zzd, this.zzb);
                zzdiiVar2.zzd(this.zzd, this.zzb);
                zzdiiVar2.zze(this.zzd, this.zzb);
                zzdiiVar2.zzf(this.zzd, this.zzb);
                zzdiiVar2.zzb(this.zzd, this.zzb);
                zzdiiVar2.zzk(this.zzd, this.zzb);
                zzdiiVar2.zzi(this.zzd, this.zzb);
                zze2.zzf(zzdiiVar2.zzn());
                zze2.zze(new zzely(this.zzg));
                zze2.zzd(new zzdmw(zzdoz.zza, null));
                zze2.zzg(new zzcyu(this.zzh, this.zzj));
                zze2.zzc(new zzcwx(this.zzf));
                zzj = zze2.zzj();
            }
            zzcxx zzcxxVar = zzj;
            if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
                zzfjc zzj2 = zzcxxVar.zzj();
                zzj2.zzh(3);
                zzj2.zzb(zzlVar.zzp);
                zzfjcVar = zzj2;
            } else {
                zzfjcVar = null;
            }
            zzdaf zzd = zzcxxVar.zzd();
            zzfyx zzh = zzd.zzh(zzd.zzi());
            this.zzl = zzh;
            zzfyo.zzr(zzh, new zzezb(this, zzeodVar, zzfjcVar, zzb, zzcxxVar), this.zzb);
            return true;
        }
    }

    public final ViewGroup zzd() {
        return this.zzf;
    }

    public final zzfdl zzi() {
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzm() {
        this.zzd.zza(zzfem.zzd(6, null, null));
    }

    public final void zzn() {
        this.zzh.zzd(this.zzj.zzc());
    }

    public final void zzo(com.google.android.gms.ads.internal.client.zzbc zzbcVar) {
        this.zze.zza(zzbcVar);
    }

    public final void zzp(zzdfo zzdfoVar) {
        this.zzh.zzj(zzdfoVar, this.zzb);
    }

    public final void zzq(zzbjt zzbjtVar) {
        this.zzg = zzbjtVar;
    }

    public final boolean zzr() {
        ViewParent parent = this.zzf.getParent();
        if (parent instanceof View) {
            View view = (View) parent;
            com.google.android.gms.ads.internal.zzt.zzq();
            return com.google.android.gms.ads.internal.util.zzs.zzS(view, view.getContext());
        }
        return false;
    }
}
