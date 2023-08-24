package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahp implements zzahy {
    private zzaf zza;
    private zzej zzb;
    private zzaam zzc;

    public zzahp(String str) {
        zzad zzadVar = new zzad();
        zzadVar.zzS(str);
        this.zza = zzadVar.zzY();
    }

    @Override // com.google.android.gms.internal.ads.zzahy
    public final void zza(zzed zzedVar) {
        zzdd.zzb(this.zzb);
        int r0 = zzel.zza;
        long zzd = this.zzb.zzd();
        long zze = this.zzb.zze();
        if (zzd == C1856C.TIME_UNSET || zze == C1856C.TIME_UNSET) {
            return;
        }
        zzaf zzafVar = this.zza;
        if (zze != zzafVar.zzq) {
            zzad zzb = zzafVar.zzb();
            zzb.zzW(zze);
            zzaf zzY = zzb.zzY();
            this.zza = zzY;
            this.zzc.zzk(zzY);
        }
        int zza = zzedVar.zza();
        this.zzc.zzq(zzedVar, zza);
        this.zzc.zzs(zzd, 1, zza, 0, null);
    }

    @Override // com.google.android.gms.internal.ads.zzahy
    public final void zzb(zzej zzejVar, zzzi zzziVar, zzail zzailVar) {
        this.zzb = zzejVar;
        zzailVar.zzc();
        zzaam zzv = zzziVar.zzv(zzailVar.zza(), 5);
        this.zzc = zzv;
        zzv.zzk(this.zza);
    }
}
