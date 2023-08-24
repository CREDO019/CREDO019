package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdle extends zzczc {
    private final Context zzc;
    private final WeakReference zzd;
    private final zzdju zze;
    private final zzdmn zzf;
    private final zzczw zzg;
    private final zzfmq zzh;
    private final zzddl zzi;
    private boolean zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdle(zzczb zzczbVar, Context context, @Nullable zzcmn zzcmnVar, zzdju zzdjuVar, zzdmn zzdmnVar, zzczw zzczwVar, zzfmq zzfmqVar, zzddl zzddlVar) {
        super(zzczbVar);
        this.zzj = false;
        this.zzc = context;
        this.zzd = new WeakReference(zzcmnVar);
        this.zze = zzdjuVar;
        this.zzf = zzdmnVar;
        this.zzg = zzczwVar;
        this.zzh = zzfmqVar;
        this.zzi = zzddlVar;
    }

    public final void finalize() throws Throwable {
        try {
            final zzcmn zzcmnVar = (zzcmn) this.zzd.get();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfL)).booleanValue()) {
                if (!this.zzj && zzcmnVar != null) {
                    zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdld
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

    public final boolean zza() {
        return this.zzg.zzg();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.content.Context] */
    public final boolean zzc(boolean z, @Nullable Activity activity) {
        this.zze.zzb();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzay)).booleanValue()) {
            com.google.android.gms.ads.internal.zzt.zzq();
            if (com.google.android.gms.ads.internal.util.zzs.zzC(this.zzc)) {
                com.google.android.gms.ads.internal.util.zze.zzj("Interstitials that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit  https://googlemobileadssdk.page.link/admob-interstitial-policies");
                this.zzi.zzb();
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaz)).booleanValue()) {
                    this.zzh.zza(this.zza.zzb.zzb.zzb);
                }
                return false;
            }
        }
        if (this.zzj) {
            com.google.android.gms.ads.internal.util.zze.zzj("The interstitial ad has been showed.");
            this.zzi.zza(zzfem.zzd(10, null, null));
        }
        Activity activity2 = activity;
        if (!this.zzj) {
            if (activity == null) {
                activity2 = this.zzc;
            }
            try {
                this.zzf.zza(z, activity2, this.zzi);
                this.zze.zza();
                this.zzj = true;
                return true;
            } catch (zzdmm e) {
                this.zzi.zzc(e);
            }
        }
        return false;
    }
}
