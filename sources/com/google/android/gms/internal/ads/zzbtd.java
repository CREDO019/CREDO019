package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtd implements zzbpq {
    final /* synthetic */ zzbsm zza;
    final /* synthetic */ com.google.android.gms.ads.internal.util.zzca zzb;
    final /* synthetic */ zzbtr zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbtd(zzbtr zzbtrVar, zzapb zzapbVar, zzbsm zzbsmVar, com.google.android.gms.ads.internal.util.zzca zzcaVar) {
        this.zzc = zzbtrVar;
        this.zza = zzbsmVar;
        this.zzb = zzcaVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.gms.internal.ads.zzbpq, java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        Object obj2;
        int r4;
        zzbts zzbtsVar = (zzbts) obj;
        obj2 = this.zzc.zza;
        synchronized (obj2) {
            com.google.android.gms.ads.internal.util.zze.zzi("JS Engine is requesting an update");
            r4 = this.zzc.zzi;
            if (r4 == 0) {
                com.google.android.gms.ads.internal.util.zze.zzi("Starting reload.");
                this.zzc.zzi = 2;
                this.zzc.zzd(null);
            }
            this.zza.zzr("/requestReload", this.zzb.zza());
        }
    }
}
