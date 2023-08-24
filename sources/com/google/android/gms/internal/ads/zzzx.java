package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzx implements zzaai {
    private final long[] zza;
    private final long[] zzb;
    private final long zzc;
    private final boolean zzd;

    public zzzx(long[] jArr, long[] jArr2, long j) {
        int length = jArr.length;
        int length2 = jArr2.length;
        zzdd.zzd(length == length2);
        boolean z = length2 > 0;
        this.zzd = z;
        if (!z || jArr2[0] <= 0) {
            this.zza = jArr;
            this.zzb = jArr2;
        } else {
            int r0 = length2 + 1;
            long[] jArr3 = new long[r0];
            this.zza = jArr3;
            long[] jArr4 = new long[r0];
            this.zzb = jArr4;
            System.arraycopy(jArr, 0, jArr3, 1, length2);
            System.arraycopy(jArr2, 0, jArr4, 1, length2);
        }
        this.zzc = j;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        if (!this.zzd) {
            zzaaj zzaajVar = zzaaj.zza;
            return new zzaag(zzaajVar, zzaajVar);
        }
        int zzd = zzel.zzd(this.zzb, j, true, true);
        zzaaj zzaajVar2 = new zzaaj(this.zzb[zzd], this.zza[zzd]);
        if (zzaajVar2.zzb != j) {
            long[] jArr = this.zzb;
            if (zzd != jArr.length - 1) {
                int r0 = zzd + 1;
                return new zzaag(zzaajVar2, new zzaaj(jArr[r0], this.zza[r0]));
            }
        }
        return new zzaag(zzaajVar2, zzaajVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return this.zzd;
    }
}
