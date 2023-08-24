package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.AdFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfjc implements Runnable {
    private final zzfje zzb;
    private String zzc;
    private String zzd;
    private zzfdd zze;
    private com.google.android.gms.ads.internal.client.zze zzf;
    private Future zzg;
    private final List zza = new ArrayList();
    private int zzh = 2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfjc(zzfje zzfjeVar) {
        this.zzb = zzfjeVar;
    }

    @Override // java.lang.Runnable
    public final synchronized void run() {
        zzg();
    }

    public final synchronized zzfjc zza(zzfir zzfirVar) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            List list = this.zza;
            zzfirVar.zzg();
            list.add(zzfirVar);
            Future future = this.zzg;
            if (future != null) {
                future.cancel(false);
            }
            this.zzg = zzcha.zzd.schedule(this, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhu)).intValue(), TimeUnit.MILLISECONDS);
        }
        return this;
    }

    public final synchronized zzfjc zzb(String str) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue() && zzfjb.zze(str)) {
            this.zzc = str;
        }
        return this;
    }

    public final synchronized zzfjc zzc(com.google.android.gms.ads.internal.client.zze zzeVar) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            this.zzf = zzeVar;
        }
        return this;
    }

    public final synchronized zzfjc zzd(ArrayList arrayList) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            if (!arrayList.contains("banner") && !arrayList.contains(AdFormat.BANNER.name())) {
                if (!arrayList.contains("interstitial") && !arrayList.contains(AdFormat.INTERSTITIAL.name())) {
                    if (!arrayList.contains("native") && !arrayList.contains(AdFormat.NATIVE.name())) {
                        if (!arrayList.contains("rewarded") && !arrayList.contains(AdFormat.REWARDED.name())) {
                            if (arrayList.contains("app_open_ad")) {
                                this.zzh = 7;
                            } else if (arrayList.contains("rewarded_interstitial") || arrayList.contains(AdFormat.REWARDED_INTERSTITIAL.name())) {
                                this.zzh = 6;
                            }
                        }
                        this.zzh = 5;
                    }
                    this.zzh = 8;
                }
                this.zzh = 4;
            }
            this.zzh = 3;
        }
        return this;
    }

    public final synchronized zzfjc zze(String str) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            this.zzd = str;
        }
        return this;
    }

    public final synchronized zzfjc zzf(zzfdd zzfddVar) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            this.zze = zzfddVar;
        }
        return this;
    }

    public final synchronized void zzg() {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            Future future = this.zzg;
            if (future != null) {
                future.cancel(false);
            }
            for (zzfir zzfirVar : this.zza) {
                int r2 = this.zzh;
                if (r2 != 2) {
                    zzfirVar.zzk(r2);
                }
                if (!TextUtils.isEmpty(this.zzc)) {
                    zzfirVar.zzd(this.zzc);
                }
                if (!TextUtils.isEmpty(this.zzd) && !zzfirVar.zzi()) {
                    zzfirVar.zzc(this.zzd);
                }
                zzfdd zzfddVar = this.zze;
                if (zzfddVar != null) {
                    zzfirVar.zzb(zzfddVar);
                } else {
                    com.google.android.gms.ads.internal.client.zze zzeVar = this.zzf;
                    if (zzeVar != null) {
                        zzfirVar.zza(zzeVar);
                    }
                }
                this.zzb.zzb(zzfirVar.zzj());
            }
            this.zza.clear();
        }
    }

    public final synchronized zzfjc zzh(int r2) {
        if (((Boolean) zzbkh.zzc.zze()).booleanValue()) {
            this.zzh = r2;
        }
        return this;
    }
}
