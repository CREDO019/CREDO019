package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcbg implements Callable {
    final /* synthetic */ Context zza;
    final /* synthetic */ zzcbi zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcbg(zzcbi zzcbiVar, Context context) {
        this.zzb = zzcbiVar;
        this.zza = context;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        WeakHashMap weakHashMap;
        zzcbf zza;
        WeakHashMap weakHashMap2;
        weakHashMap = this.zzb.zza;
        zzcbh zzcbhVar = (zzcbh) weakHashMap.get(this.zza);
        if (zzcbhVar == null || zzcbhVar.zza + ((Long) zzbke.zza.zze()).longValue() < com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis()) {
            zza = new zzcbe(this.zza).zza();
        } else {
            zza = new zzcbe(this.zza, zzcbhVar.zzb).zza();
        }
        zzcbi zzcbiVar = this.zzb;
        weakHashMap2 = zzcbiVar.zza;
        weakHashMap2.put(this.zza, new zzcbh(zzcbiVar, zza));
        return zza;
    }
}
