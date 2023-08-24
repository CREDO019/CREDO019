package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzain {
    public static int zza(byte[] bArr, int r3, int r4) {
        while (r3 < r4 && bArr[r3] != 71) {
            r3++;
        }
        return r3;
    }

    public static long zzb(zzed zzedVar, int r10, int r11) {
        zzedVar.zzF(r10);
        if (zzedVar.zza() < 5) {
            return C1856C.TIME_UNSET;
        }
        int zze = zzedVar.zze();
        if ((8388608 & zze) == 0 && ((zze >> 8) & 8191) == r11 && (zze & 32) != 0 && zzedVar.zzk() >= 7 && zzedVar.zza() >= 7 && (zzedVar.zzk() & 16) == 16) {
            byte[] bArr = new byte[6];
            zzedVar.zzB(bArr, 0, 6);
            byte b = bArr[0];
            byte b2 = bArr[1];
            byte b3 = bArr[2];
            long j = bArr[3] & 255;
            return ((b2 & 255) << 17) | ((b & 255) << 25) | ((b3 & 255) << 9) | (j + j) | ((bArr[4] & 255) >> 7);
        }
        return C1856C.TIME_UNSET;
    }
}
