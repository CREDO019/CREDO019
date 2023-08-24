package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdxn {
    final /* synthetic */ zzdxo zza;
    private final Map zzb = new ConcurrentHashMap();

    public zzdxn(zzdxo zzdxoVar) {
        this.zza = zzdxoVar;
    }

    public static /* bridge */ /* synthetic */ zzdxn zza(zzdxn zzdxnVar) {
        Map map;
        Map map2 = zzdxnVar.zzb;
        map = zzdxnVar.zza.zzc;
        map2.putAll(map);
        return zzdxnVar;
    }

    public final zzdxn zzb(String str, String str2) {
        this.zzb.put(str, str2);
        return this;
    }

    public final zzdxn zzc(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.zzb.put(str, str2);
        }
        return this;
    }

    public final zzdxn zzd(zzfcs zzfcsVar) {
        this.zzb.put("aai", zzfcsVar.zzx);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzga)).booleanValue()) {
            zzc("rid", zzfcsVar.zzap);
        }
        return this;
    }

    public final zzdxn zze(zzfcv zzfcvVar) {
        this.zzb.put("gqi", zzfcvVar.zzb);
        return this;
    }

    public final String zzf() {
        zzdxt zzdxtVar;
        zzdxtVar = this.zza.zza;
        return zzdxtVar.zzb(this.zzb);
    }

    public final void zzg() {
        Executor executor;
        executor = this.zza.zzb;
        executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdxm
            @Override // java.lang.Runnable
            public final void run() {
                zzdxn.this.zzi();
            }
        });
    }

    public final void zzh() {
        Executor executor;
        executor = this.zza.zzb;
        executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdxl
            @Override // java.lang.Runnable
            public final void run() {
                zzdxn.this.zzj();
            }
        });
    }

    public final /* synthetic */ void zzi() {
        zzdxt zzdxtVar;
        zzdxtVar = this.zza.zza;
        zzdxtVar.zze(this.zzb);
    }

    public final /* synthetic */ void zzj() {
        zzdxt zzdxtVar;
        zzdxtVar = this.zza.zza;
        zzdxtVar.zzd(this.zzb);
    }
}
