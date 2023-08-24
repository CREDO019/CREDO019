package com.google.android.gms.internal.ads;

import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezb implements zzfyk {
    final /* synthetic */ zzeod zza;
    final /* synthetic */ zzfjc zzb;
    final /* synthetic */ zzfir zzc;
    final /* synthetic */ zzcxx zzd;
    final /* synthetic */ zzezc zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzezb(zzezc zzezcVar, zzeod zzeodVar, zzfjc zzfjcVar, zzfir zzfirVar, zzcxx zzcxxVar) {
        this.zze = zzezcVar;
        this.zza = zzeodVar;
        this.zzb = zzfjcVar;
        this.zzc = zzfirVar;
        this.zzd = zzcxxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzdfn zzdfnVar;
        zzdht zzdhtVar;
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        Executor executor;
        final com.google.android.gms.ads.internal.client.zze zza = this.zzd.zzd().zza(th);
        synchronized (this.zze) {
            this.zze.zzl = null;
            this.zzd.zzf().zza(zza);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgT)).booleanValue()) {
                executor = this.zze.zzb;
                executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeza
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzeno zzenoVar;
                        zzezb zzezbVar = zzezb.this;
                        com.google.android.gms.ads.internal.client.zze zzeVar = zza;
                        zzenoVar = zzezbVar.zze.zzd;
                        zzenoVar.zza(zzeVar);
                    }
                });
            }
            zzezc zzezcVar = this.zze;
            zzdfnVar = zzezcVar.zzh;
            zzdhtVar = zzezcVar.zzj;
            zzdfnVar.zzd(zzdhtVar.zzc());
            zzfeh.zzb(zza.zza, th, "BannerAdLoader.onFailure");
            this.zza.zza();
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzc(zza);
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(false);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zzi;
                zzfir zzfirVar2 = this.zzc;
                zzfirVar2.zza(zza);
                zzfirVar2.zze(false);
                zzfjeVar.zzb(zzfirVar2.zzj());
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        zzdfn zzdfnVar;
        zzfje zzfjeVar;
        zzfjc zzfjcVar;
        Executor executor;
        final zzeno zzenoVar;
        zzeno zzenoVar2;
        zzens zzensVar;
        zzcxa zzcxaVar = (zzcxa) obj;
        synchronized (this.zze) {
            this.zze.zzl = null;
            viewGroup = this.zze.zzf;
            viewGroup.removeAllViews();
            if (zzcxaVar.zzc() != null) {
                ViewParent parent = zzcxaVar.zzc().getParent();
                if (parent instanceof ViewGroup) {
                    String zzg = zzcxaVar.zzl() != null ? zzcxaVar.zzl().zzg() : "";
                    com.google.android.gms.ads.internal.util.zze.zzj("Banner view provided from " + zzg + " already has a parent view. Removing its old parent.");
                    ((ViewGroup) parent).removeView(zzcxaVar.zzc());
                }
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgT)).booleanValue()) {
                zzdhc zzn = zzcxaVar.zzn();
                zzenoVar2 = this.zze.zzd;
                zzn.zza(zzenoVar2);
                zzensVar = this.zze.zze;
                zzn.zzc(zzensVar);
            }
            viewGroup2 = this.zze.zzf;
            viewGroup2.addView(zzcxaVar.zzc());
            this.zza.zzb(zzcxaVar);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgT)).booleanValue()) {
                zzezc zzezcVar = this.zze;
                executor = zzezcVar.zzb;
                zzenoVar = zzezcVar.zzd;
                zzenoVar.getClass();
                executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeyz
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzeno.this.zzn();
                    }
                });
            }
            zzdfnVar = this.zze.zzh;
            zzdfnVar.zzd(zzcxaVar.zza());
            if (((Boolean) zzbkh.zzc.zze()).booleanValue() && (zzfjcVar = this.zzb) != null) {
                zzfjcVar.zzf(zzcxaVar.zzp().zzb);
                zzfjcVar.zze(zzcxaVar.zzl().zzg());
                zzfir zzfirVar = this.zzc;
                zzfirVar.zze(true);
                zzfjcVar.zza(zzfirVar);
                zzfjcVar.zzg();
            } else {
                zzfjeVar = this.zze.zzi;
                zzfir zzfirVar2 = this.zzc;
                zzfirVar2.zzb(zzcxaVar.zzp().zzb);
                zzfirVar2.zzc(zzcxaVar.zzl().zzg());
                zzfirVar2.zze(true);
                zzfjeVar.zzb(zzfirVar2.zzj());
            }
        }
    }
}
