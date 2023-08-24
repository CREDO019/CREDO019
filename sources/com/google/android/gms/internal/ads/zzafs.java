package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzafs {
    public final int zza;
    public final int zzb;
    public final long zzc;
    public final long zzd;
    public final long zze;
    public final zzaf zzf;
    public final int zzg;
    public final long[] zzh;
    public final long[] zzi;
    public final int zzj;
    private final zzaft[] zzk;

    public zzafs(int r1, int r2, long j, long j2, long j3, zzaf zzafVar, int r10, zzaft[] zzaftVarArr, int r12, long[] jArr, long[] jArr2) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = j;
        this.zzd = j2;
        this.zze = j3;
        this.zzf = zzafVar;
        this.zzg = r10;
        this.zzk = zzaftVarArr;
        this.zzj = r12;
        this.zzh = jArr;
        this.zzi = jArr2;
    }

    public final zzaft zza(int r2) {
        zzaft[] zzaftVarArr = this.zzk;
        if (zzaftVarArr == null) {
            return null;
        }
        return zzaftVarArr[r2];
    }
}
