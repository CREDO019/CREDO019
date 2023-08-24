package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzyt implements zzaai {
    public final int zza;
    public final int[] zzb;
    public final long[] zzc;
    public final long[] zzd;
    public final long[] zze;
    private final long zzf;

    public zzyt(int[] r3, long[] jArr, long[] jArr2, long[] jArr3) {
        this.zzb = r3;
        this.zzc = jArr;
        this.zzd = jArr2;
        this.zze = jArr3;
        int length = r3.length;
        this.zza = length;
        if (length <= 0) {
            this.zzf = 0L;
            return;
        }
        int r32 = length - 1;
        this.zzf = jArr2[r32] + jArr3[r32];
    }

    public final String toString() {
        int r0 = this.zza;
        String arrays = Arrays.toString(this.zzb);
        String arrays2 = Arrays.toString(this.zzc);
        String arrays3 = Arrays.toString(this.zze);
        String arrays4 = Arrays.toString(this.zzd);
        return "ChunkIndex(length=" + r0 + ", sizes=" + arrays + ", offsets=" + arrays2 + ", timeUs=" + arrays3 + ", durationsUs=" + arrays4 + ")";
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final long zze() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final zzaag zzg(long j) {
        int zzd = zzel.zzd(this.zze, j, true, true);
        zzaaj zzaajVar = new zzaaj(this.zze[zzd], this.zzc[zzd]);
        if (zzaajVar.zzb >= j || zzd == this.zza - 1) {
            return new zzaag(zzaajVar, zzaajVar);
        }
        int r0 = zzd + 1;
        return new zzaag(zzaajVar, new zzaaj(this.zze[r0], this.zzc[r0]));
    }

    @Override // com.google.android.gms.internal.ads.zzaai
    public final boolean zzh() {
        return true;
    }
}
