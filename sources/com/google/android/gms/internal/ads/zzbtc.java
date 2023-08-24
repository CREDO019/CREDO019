package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtc implements zzbpq {
    final /* synthetic */ zzbtq zza;
    final /* synthetic */ zzbsm zzb;
    final /* synthetic */ zzbtr zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbtc(zzbtr zzbtrVar, zzbtq zzbtqVar, zzbsm zzbsmVar) {
        this.zzc = zzbtrVar;
        this.zza = zzbtqVar;
        this.zzb = zzbsmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final /* bridge */ /* synthetic */ void zza(Object obj, Map map) {
        Object obj2;
        zzbts zzbtsVar = (zzbts) obj;
        obj2 = this.zzc.zza;
        synchronized (obj2) {
            if (this.zza.zze() != -1 && this.zza.zze() != 1) {
                this.zzc.zzi = 0;
                zzbsm zzbsmVar = this.zzb;
                zzbsmVar.zzq("/log", zzbpp.zzg);
                zzbsmVar.zzq("/result", zzbpp.zzo);
                this.zza.zzh(this.zzb);
                this.zzc.zzh = this.zza;
                com.google.android.gms.ads.internal.util.zze.zza("Successfully loaded JS Engine.");
            }
        }
    }
}
