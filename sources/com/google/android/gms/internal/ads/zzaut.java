package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaut implements zzavc {
    public final int[] zza;
    public final long[] zzb;
    public final long[] zzc;
    public final long[] zzd;
    private final long zze;

    public zzaut(int[] r3, long[] jArr, long[] jArr2, long[] jArr3) {
        this.zza = r3;
        this.zzb = jArr;
        this.zzc = jArr2;
        this.zzd = jArr3;
        int length = r3.length;
        if (length <= 0) {
            this.zze = 0L;
            return;
        }
        int r32 = length - 1;
        this.zze = jArr2[r32] + jArr3[r32];
    }

    @Override // com.google.android.gms.internal.ads.zzavc
    public final long zza() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzavc
    public final long zzb(long j) {
        return this.zzb[zzban.zzc(this.zzd, j, true, true)];
    }

    @Override // com.google.android.gms.internal.ads.zzavc
    public final boolean zzc() {
        return true;
    }
}
