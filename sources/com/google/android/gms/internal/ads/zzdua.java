package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdua extends zzczc {
    private final Context zzc;
    private final WeakReference zzd;
    private final zzdmn zze;
    private final zzdju zzf;
    private final zzddl zzg;
    private final zzdes zzh;
    private final zzczw zzi;
    private final zzccg zzj;
    private final zzfmq zzk;
    private final zzfdg zzl;
    private boolean zzm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdua(zzczb zzczbVar, Context context, zzcmn zzcmnVar, zzdmn zzdmnVar, zzdju zzdjuVar, zzddl zzddlVar, zzdes zzdesVar, zzczw zzczwVar, zzfcs zzfcsVar, zzfmq zzfmqVar, zzfdg zzfdgVar) {
        super(zzczbVar);
        this.zzm = false;
        this.zzc = context;
        this.zze = zzdmnVar;
        this.zzd = new WeakReference(zzcmnVar);
        this.zzf = zzdjuVar;
        this.zzg = zzddlVar;
        this.zzh = zzdesVar;
        this.zzi = zzczwVar;
        this.zzk = zzfmqVar;
        zzccc zzcccVar = zzfcsVar.zzm;
        this.zzj = new zzcda(zzcccVar != null ? zzcccVar.zza : "", zzcccVar != null ? zzcccVar.zzb : 1);
        this.zzl = zzfdgVar;
    }

    public final void finalize() throws Throwable {
        try {
            final zzcmn zzcmnVar = (zzcmn) this.zzd.get();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfL)).booleanValue()) {
                if (!this.zzm && zzcmnVar != null) {
                    zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdtz
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzcmn.this.destroy();
                        }
                    });
                }
            } else if (zzcmnVar != null) {
                zzcmnVar.destroy();
            }
        } finally {
            super.finalize();
        }
    }

    public final Bundle zza() {
        return this.zzh.zzb();
    }

    public final zzccg zzc() {
        return this.zzj;
    }

    public final zzfdg zzd() {
        return this.zzl;
    }

    public final boolean zze() {
        return this.zzi.zzg();
    }

    public final boolean zzf() {
        return this.zzm;
    }

    public final boolean zzg() {
        zzcmn zzcmnVar = (zzcmn) this.zzd.get();
        return (zzcmnVar == null || zzcmnVar.zzaD()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.content.Context] */
    public final boolean zzh(boolean z, Activity activity) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzay)).booleanValue()) {
            com.google.android.gms.ads.internal.zzt.zzq();
            if (com.google.android.gms.ads.internal.util.zzs.zzC(this.zzc)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Rewarded ads that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit https://googlemobileadssdk.page.link/admob-interstitial-policies");
                this.zzg.zzb();
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaz)).booleanValue()) {
                    this.zzk.zza(this.zza.zzb.zzb.zzb);
                }
                return false;
            }
        }
        if (this.zzm) {
            com.google.android.gms.ads.internal.util.zze.zzj("The rewarded ad have been showed.");
            this.zzg.zza(zzfem.zzd(10, null, null));
            return false;
        }
        this.zzm = true;
        this.zzf.zzb();
        Activity activity2 = activity;
        if (activity == null) {
            activity2 = this.zzc;
        }
        try {
            this.zze.zza(z, activity2, this.zzg);
            this.zzf.zza();
            return true;
        } catch (zzdmm e) {
            this.zzg.zzc(e);
            return false;
        }
    }
}
