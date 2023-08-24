package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaeh {
    private static final long[] zza = {128, 64, 32, 16, 8, 4, 2, 1};
    private final byte[] zzb = new byte[8];
    private int zzc;
    private int zzd;

    public static int zzb(int r6) {
        int r0 = 0;
        while (r0 < 8) {
            int r5 = ((zza[r0] & r6) > 0L ? 1 : ((zza[r0] & r6) == 0L ? 0 : -1));
            r0++;
            if (r5 != 0) {
                return r0;
            }
        }
        return -1;
    }

    public static long zzc(byte[] bArr, int r7, boolean z) {
        long j = bArr[0] & 255;
        if (z) {
            j &= ~zza[r7 - 1];
        }
        for (int r8 = 1; r8 < r7; r8++) {
            j = (j << 8) | (bArr[r8] & 255);
        }
        return j;
    }

    public final int zza() {
        return this.zzd;
    }

    public final long zzd(zzzg zzzgVar, boolean z, boolean z2, int r8) throws IOException {
        if (this.zzc == 0) {
            if (!zzzgVar.zzn(this.zzb, 0, 1, z)) {
                return -1L;
            }
            int zzb = zzb(this.zzb[0] & 255);
            this.zzd = zzb;
            if (zzb != -1) {
                this.zzc = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        int r6 = this.zzd;
        if (r6 > r8) {
            this.zzc = 0;
            return -2L;
        }
        if (r6 != 1) {
            ((zzyv) zzzgVar).zzn(this.zzb, 1, r6 - 1, false);
        }
        this.zzc = 0;
        return zzc(this.zzb, this.zzd, z2);
    }

    public final void zze() {
        this.zzc = 0;
        this.zzd = 0;
    }
}
