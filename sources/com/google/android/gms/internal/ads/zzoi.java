package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzoi {
    private final zzne[] zza;
    private final zzpa zzb;
    private final zzpc zzc;

    public zzoi(zzne... zzneVarArr) {
        zzpa zzpaVar = new zzpa();
        zzpc zzpcVar = new zzpc();
        this.zza = r2;
        System.arraycopy(zzneVarArr, 0, r2, 0, 0);
        this.zzb = zzpaVar;
        this.zzc = zzpcVar;
        zzne[] zzneVarArr2 = {zzpaVar, zzpcVar};
    }

    public final long zza(long j) {
        return this.zzc.zzi(j);
    }

    public final long zzb() {
        return this.zzb.zzo();
    }

    public final zzby zzc(zzby zzbyVar) {
        this.zzc.zzk(zzbyVar.zzc);
        this.zzc.zzj(zzbyVar.zzd);
        return zzbyVar;
    }

    public final boolean zzd(boolean z) {
        this.zzb.zzp(z);
        return z;
    }

    public final zzne[] zze() {
        return this.zza;
    }
}
