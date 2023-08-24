package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.util.Arrays;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgcv {
    public static byte[] zza(byte[] bArr, byte[] bArr2) {
        long zzb = zzb(bArr, 0, 0);
        long zzb2 = zzb(bArr, 3, 2) & 67108611;
        long zzb3 = zzb(bArr, 6, 4) & 67092735;
        long zzb4 = zzb(bArr, 9, 6) & 66076671;
        long zzb5 = zzb(bArr, 12, 8) & 1048575;
        long j = zzb2 * 5;
        long j2 = zzb3 * 5;
        long j3 = zzb4 * 5;
        long j4 = zzb5 * 5;
        int r9 = 17;
        byte[] bArr3 = new byte[17];
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        int r10 = 0;
        while (true) {
            int length = bArr2.length;
            if (r10 < length) {
                int min = Math.min(16, length - r10);
                System.arraycopy(bArr2, r10, bArr3, 0, min);
                bArr3[min] = 1;
                if (min != 16) {
                    Arrays.fill(bArr3, min + 1, r9, (byte) 0);
                }
                long zzb6 = j9 + zzb(bArr3, 0, 0);
                long zzb7 = j6 + zzb(bArr3, 3, 2);
                long zzb8 = j5 + zzb(bArr3, 6, 4);
                long zzb9 = j7 + zzb(bArr3, 9, 6);
                long zzb10 = j8 + (zzb(bArr3, 12, 8) | (bArr3[16] << Ascii.CAN));
                long j10 = (zzb6 * zzb) + (zzb7 * j4) + (zzb8 * j3) + (zzb9 * j2) + (zzb10 * j);
                long j11 = (zzb6 * zzb2) + (zzb7 * zzb) + (zzb8 * j4) + (zzb9 * j3) + (zzb10 * j2) + (j10 >> 26);
                long j12 = (zzb6 * zzb3) + (zzb7 * zzb2) + (zzb8 * zzb) + (zzb9 * j4) + (zzb10 * j3) + (j11 >> 26);
                long j13 = (zzb6 * zzb4) + (zzb7 * zzb3) + (zzb8 * zzb2) + (zzb9 * zzb) + (zzb10 * j4) + (j12 >> 26);
                long j14 = (zzb6 * zzb5) + (zzb7 * zzb4) + (zzb8 * zzb3) + (zzb9 * zzb2) + (zzb10 * zzb) + (j13 >> 26);
                j8 = j14 & 67108863;
                long j15 = (j10 & 67108863) + ((j14 >> 26) * 5);
                j9 = j15 & 67108863;
                j6 = (j11 & 67108863) + (j15 >> 26);
                r10 += 16;
                j7 = j13 & 67108863;
                j5 = j12 & 67108863;
                r9 = 17;
            } else {
                long j16 = j5 + (j6 >> 26);
                long j17 = j16 & 67108863;
                long j18 = j7 + (j16 >> 26);
                long j19 = j18 & 67108863;
                long j20 = j8 + (j18 >> 26);
                long j21 = j20 & 67108863;
                long j22 = j9 + ((j20 >> 26) * 5);
                long j23 = j22 & 67108863;
                long j24 = (j6 & 67108863) + (j22 >> 26);
                long j25 = j23 + 5;
                long j26 = (j25 >> 26) + j24;
                long j27 = j17 + (j26 >> 26);
                long j28 = j19 + (j27 >> 26);
                long j29 = (j21 + (j28 >> 26)) - 67108864;
                long j30 = j29 >> 63;
                long j31 = ~j30;
                long j32 = (j24 & j30) | (j26 & 67108863 & j31);
                long j33 = (j17 & j30) | (j27 & 67108863 & j31);
                long j34 = (j19 & j30) | (j28 & 67108863 & j31);
                long zzc = (((j23 & j30) | (j25 & 67108863 & j31) | (j32 << 26)) & BodyPartID.bodyIdMax) + zzc(bArr, 16);
                long zzc2 = (((j32 >> 6) | (j33 << 20)) & BodyPartID.bodyIdMax) + zzc(bArr, 20) + (zzc >> 32);
                long zzc3 = (((j33 >> 12) | (j34 << 14)) & BodyPartID.bodyIdMax) + zzc(bArr, 24) + (zzc2 >> 32);
                long zzc4 = zzc(bArr, 28);
                byte[] bArr4 = new byte[16];
                zzd(bArr4, zzc & BodyPartID.bodyIdMax, 0);
                zzd(bArr4, zzc2 & BodyPartID.bodyIdMax, 4);
                zzd(bArr4, zzc3 & BodyPartID.bodyIdMax, 8);
                zzd(bArr4, ((((((j29 & j31) | (j21 & j30)) << 8) | (j34 >> 18)) & BodyPartID.bodyIdMax) + zzc4 + (zzc3 >> 32)) & BodyPartID.bodyIdMax, 12);
                return bArr4;
            }
        }
    }

    private static long zzb(byte[] bArr, int r3, int r4) {
        return (zzc(bArr, r3) >> r4) & 67108863;
    }

    private static long zzc(byte[] bArr, int r3) {
        return (((bArr[r3 + 3] & 255) << 24) | (bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8) | ((bArr[r3 + 2] & 255) << 16)) & BodyPartID.bodyIdMax;
    }

    private static void zzd(byte[] bArr, long j, int r7) {
        int r0 = 0;
        while (r0 < 4) {
            bArr[r7 + r0] = (byte) (255 & j);
            r0++;
            j >>= 8;
        }
    }
}
