package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgly {
    private static final int[] zza = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    private static final int[] zzb = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] zzc = {67108863, 33554431};
    private static final int[] zzd = {26, 25};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[19];
        zzb(jArr4, jArr2, jArr3);
        zzc(jArr4, jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr[0] = jArr2[0] * jArr3[0];
        jArr[1] = (jArr2[0] * jArr3[1]) + (jArr2[1] * jArr3[0]);
        long j = jArr2[1];
        jArr[2] = ((j + j) * jArr3[1]) + (jArr2[0] * jArr3[2]) + (jArr2[2] * jArr3[0]);
        jArr[3] = (jArr2[1] * jArr3[2]) + (jArr2[2] * jArr3[1]) + (jArr2[0] * jArr3[3]) + (jArr2[3] * jArr3[0]);
        long j2 = jArr2[2];
        long j3 = jArr3[2];
        long j4 = (jArr2[1] * jArr3[3]) + (jArr2[3] * jArr3[1]);
        jArr[4] = (j2 * j3) + j4 + j4 + (jArr2[0] * jArr3[4]) + (jArr2[4] * jArr3[0]);
        jArr[5] = (jArr2[2] * jArr3[3]) + (jArr2[3] * jArr3[2]) + (jArr2[1] * jArr3[4]) + (jArr2[4] * jArr3[1]) + (jArr2[0] * jArr3[5]) + (jArr2[5] * jArr3[0]);
        long j5 = (jArr2[3] * jArr3[3]) + (jArr2[1] * jArr3[5]) + (jArr2[5] * jArr3[1]);
        jArr[6] = j5 + j5 + (jArr2[2] * jArr3[4]) + (jArr2[4] * jArr3[2]) + (jArr2[0] * jArr3[6]) + (jArr2[6] * jArr3[0]);
        jArr[7] = (jArr2[3] * jArr3[4]) + (jArr2[4] * jArr3[3]) + (jArr2[2] * jArr3[5]) + (jArr2[5] * jArr3[2]) + (jArr2[1] * jArr3[6]) + (jArr2[6] * jArr3[1]) + (jArr2[0] * jArr3[7]) + (jArr2[7] * jArr3[0]);
        long j6 = jArr2[4];
        long j7 = jArr3[4];
        long j8 = (jArr2[3] * jArr3[5]) + (jArr2[5] * jArr3[3]) + (jArr2[1] * jArr3[7]) + (jArr2[7] * jArr3[1]);
        jArr[8] = (j6 * j7) + j8 + j8 + (jArr2[2] * jArr3[6]) + (jArr2[6] * jArr3[2]) + (jArr2[0] * jArr3[8]) + (jArr2[8] * jArr3[0]);
        jArr[9] = (jArr2[4] * jArr3[5]) + (jArr2[5] * jArr3[4]) + (jArr2[3] * jArr3[6]) + (jArr2[6] * jArr3[3]) + (jArr2[2] * jArr3[7]) + (jArr2[7] * jArr3[2]) + (jArr2[1] * jArr3[8]) + (jArr2[8] * jArr3[1]) + (jArr2[0] * jArr3[9]) + (jArr2[9] * jArr3[0]);
        long j9 = (jArr2[5] * jArr3[5]) + (jArr2[3] * jArr3[7]) + (jArr2[7] * jArr3[3]) + (jArr2[1] * jArr3[9]) + (jArr2[9] * jArr3[1]);
        jArr[10] = j9 + j9 + (jArr2[4] * jArr3[6]) + (jArr2[6] * jArr3[4]) + (jArr2[2] * jArr3[8]) + (jArr2[8] * jArr3[2]);
        jArr[11] = (jArr2[5] * jArr3[6]) + (jArr2[6] * jArr3[5]) + (jArr2[4] * jArr3[7]) + (jArr2[7] * jArr3[4]) + (jArr2[3] * jArr3[8]) + (jArr2[8] * jArr3[3]) + (jArr2[2] * jArr3[9]) + (jArr2[9] * jArr3[2]);
        long j10 = jArr2[6];
        long j11 = jArr3[6];
        long j12 = (jArr2[5] * jArr3[7]) + (jArr2[7] * jArr3[5]) + (jArr2[3] * jArr3[9]) + (jArr2[9] * jArr3[3]);
        jArr[12] = (j10 * j11) + j12 + j12 + (jArr2[4] * jArr3[8]) + (jArr2[8] * jArr3[4]);
        jArr[13] = (jArr2[6] * jArr3[7]) + (jArr2[7] * jArr3[6]) + (jArr2[5] * jArr3[8]) + (jArr2[8] * jArr3[5]) + (jArr2[4] * jArr3[9]) + (jArr2[9] * jArr3[4]);
        long j13 = (jArr2[7] * jArr3[7]) + (jArr2[5] * jArr3[9]) + (jArr2[9] * jArr3[5]);
        jArr[14] = j13 + j13 + (jArr2[6] * jArr3[8]) + (jArr2[8] * jArr3[6]);
        jArr[15] = (jArr2[7] * jArr3[8]) + (jArr2[8] * jArr3[7]) + (jArr2[6] * jArr3[9]) + (jArr2[9] * jArr3[6]);
        long j14 = jArr2[8] * jArr3[8];
        long j15 = (jArr2[7] * jArr3[9]) + (jArr2[9] * jArr3[7]);
        jArr[16] = j14 + j15 + j15;
        jArr[17] = (jArr2[8] * jArr3[9]) + (jArr2[9] * jArr3[8]);
        long j16 = jArr2[9];
        jArr[18] = (j16 + j16) * jArr3[9];
    }

    static void zzc(long[] jArr, long[] jArr2) {
        zze(jArr);
        zzd(jArr);
        System.arraycopy(jArr, 0, jArr2, 0, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzd(long[] jArr) {
        jArr[10] = 0;
        int r4 = 0;
        while (r4 < 10) {
            long j = jArr[r4];
            long j2 = j / 67108864;
            jArr[r4] = j - (j2 << 26);
            int r5 = r4 + 1;
            long j3 = jArr[r5] + j2;
            jArr[r5] = j3;
            long j4 = j3 / 33554432;
            jArr[r5] = j3 - (j4 << 25);
            r4 += 2;
            jArr[r4] = jArr[r4] + j4;
        }
        long j5 = jArr[0] + (jArr[10] << 4);
        jArr[0] = j5;
        long j6 = jArr[10];
        long j7 = j5 + j6 + j6;
        jArr[0] = j7;
        jArr[0] = j7 + jArr[10];
        jArr[10] = 0;
        long j8 = jArr[0];
        long j9 = j8 / 67108864;
        jArr[0] = j8 - (j9 << 26);
        jArr[1] = jArr[1] + j9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zze(long[] jArr) {
        long j = jArr[8] + (jArr[18] << 4);
        jArr[8] = j;
        long j2 = jArr[18];
        long j3 = j + j2 + j2;
        jArr[8] = j3;
        jArr[8] = j3 + jArr[18];
        long j4 = jArr[7] + (jArr[17] << 4);
        jArr[7] = j4;
        long j5 = jArr[17];
        long j6 = j4 + j5 + j5;
        jArr[7] = j6;
        jArr[7] = j6 + jArr[17];
        long j7 = jArr[6] + (jArr[16] << 4);
        jArr[6] = j7;
        long j8 = jArr[16];
        long j9 = j7 + j8 + j8;
        jArr[6] = j9;
        jArr[6] = j9 + jArr[16];
        long j10 = jArr[5] + (jArr[15] << 4);
        jArr[5] = j10;
        long j11 = jArr[15];
        long j12 = j10 + j11 + j11;
        jArr[5] = j12;
        jArr[5] = j12 + jArr[15];
        long j13 = jArr[4] + (jArr[14] << 4);
        jArr[4] = j13;
        long j14 = jArr[14];
        long j15 = j13 + j14 + j14;
        jArr[4] = j15;
        jArr[4] = j15 + jArr[14];
        long j16 = jArr[3] + (jArr[13] << 4);
        jArr[3] = j16;
        long j17 = jArr[13];
        long j18 = j16 + j17 + j17;
        jArr[3] = j18;
        jArr[3] = j18 + jArr[13];
        long j19 = jArr[2] + (jArr[12] << 4);
        jArr[2] = j19;
        long j20 = jArr[12];
        long j21 = j19 + j20 + j20;
        jArr[2] = j21;
        jArr[2] = j21 + jArr[12];
        long j22 = jArr[1] + (jArr[11] << 4);
        jArr[1] = j22;
        long j23 = jArr[11];
        long j24 = j22 + j23 + j23;
        jArr[1] = j24;
        jArr[1] = j24 + jArr[11];
        long j25 = jArr[0] + (jArr[10] << 4);
        jArr[0] = j25;
        long j26 = jArr[10];
        long j27 = j25 + j26 + j26;
        jArr[0] = j27;
        jArr[0] = j27 + jArr[10];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzf(long[] jArr, long[] jArr2, long j) {
        for (int r0 = 0; r0 < 10; r0++) {
            jArr[r0] = jArr2[r0] * j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzg(long[] jArr, long[] jArr2) {
        long j = jArr2[0];
        long j2 = jArr2[0];
        long j3 = jArr2[1];
        long j4 = (j3 * j3) + (jArr2[0] * jArr2[2]);
        long j5 = (jArr2[1] * jArr2[2]) + (jArr2[0] * jArr2[3]);
        long j6 = jArr2[2];
        long j7 = jArr2[1];
        long j8 = jArr2[3];
        long j9 = jArr2[0];
        long j10 = (jArr2[2] * jArr2[3]) + (jArr2[1] * jArr2[4]) + (jArr2[0] * jArr2[5]);
        long j11 = jArr2[3];
        long j12 = jArr2[2];
        long j13 = jArr2[4];
        long j14 = jArr2[0];
        long j15 = jArr2[6];
        long j16 = jArr2[1];
        long j17 = (j11 * j11) + (j12 * j13) + (j14 * j15) + ((j16 + j16) * jArr2[5]);
        long j18 = (jArr2[3] * jArr2[4]) + (jArr2[2] * jArr2[5]) + (jArr2[1] * jArr2[6]) + (jArr2[0] * jArr2[7]);
        long j19 = jArr2[4];
        long j20 = jArr2[2];
        long j21 = jArr2[6];
        long j22 = jArr2[0];
        long j23 = jArr2[8];
        long j24 = (jArr2[1] * jArr2[7]) + (jArr2[3] * jArr2[5]);
        long j25 = (j20 * j21) + (j22 * j23) + j24 + j24;
        long j26 = (jArr2[4] * jArr2[5]) + (jArr2[3] * jArr2[6]) + (jArr2[2] * jArr2[7]) + (jArr2[1] * jArr2[8]) + (jArr2[0] * jArr2[9]);
        long j27 = jArr2[5];
        long j28 = jArr2[4];
        long j29 = jArr2[6];
        long j30 = jArr2[2];
        long j31 = jArr2[8];
        long j32 = (jArr2[3] * jArr2[7]) + (jArr2[1] * jArr2[9]);
        long j33 = (j27 * j27) + (j28 * j29) + (j30 * j31) + j32 + j32;
        long j34 = (jArr2[5] * jArr2[6]) + (jArr2[4] * jArr2[7]) + (jArr2[3] * jArr2[8]) + (jArr2[2] * jArr2[9]);
        long j35 = jArr2[6];
        long j36 = jArr2[4];
        long j37 = jArr2[8];
        long j38 = (jArr2[5] * jArr2[7]) + (jArr2[3] * jArr2[9]);
        long j39 = (j36 * j37) + j38 + j38;
        long j40 = (jArr2[6] * jArr2[7]) + (jArr2[5] * jArr2[8]) + (jArr2[4] * jArr2[9]);
        long j41 = jArr2[7];
        long j42 = jArr2[6];
        long j43 = jArr2[8];
        long j44 = jArr2[5];
        long j45 = (j41 * j41) + (j42 * j43) + ((j44 + j44) * jArr2[9]);
        long j46 = (jArr2[7] * jArr2[8]) + (jArr2[6] * jArr2[9]);
        long j47 = jArr2[8];
        long j48 = jArr2[8];
        long j49 = jArr2[9];
        zzc(new long[]{j * j, (j2 + j2) * jArr2[1], j4 + j4, j5 + j5, (j6 * j6) + (j7 * 4 * j8) + ((j9 + j9) * jArr2[4]), j10 + j10, j17 + j17, j18 + j18, (j19 * j19) + j25 + j25, j26 + j26, j33 + j33, j34 + j34, (j35 * j35) + j39 + j39, j40 + j40, j45 + j45, j46 + j46, (j47 * j47) + (jArr2[7] * 4 * jArr2[9]), (j48 + j48) * jArr2[9], (j49 + j49) * j49}, jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzh(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int r0 = 0; r0 < 10; r0++) {
            jArr[r0] = jArr2[r0] - jArr3[r0];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzi(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int r0 = 0; r0 < 10; r0++) {
            jArr[r0] = jArr2[r0] + jArr3[r0];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzj(long[] jArr) {
        int r3;
        long j;
        long j2;
        int r5;
        int r52;
        int r4;
        int r12;
        int r10;
        int r11;
        long[] copyOf = Arrays.copyOf(jArr, 10);
        for (int r32 = 0; r32 < 2; r32++) {
            int r7 = 0;
            while (r7 < 9) {
                long j3 = copyOf[r7];
                copyOf[r7] = j3 + (r11 << r10);
                r7++;
                copyOf[r7] = copyOf[r7] - (-((int) (((j3 >> 31) & j3) >> zzd[r7 & 1])));
            }
            long j4 = copyOf[9];
            int r42 = -((int) (((j4 >> 31) & j4) >> 25));
            copyOf[9] = j4 + (r42 << 25);
            copyOf[0] = copyOf[0] - (r42 * 19);
        }
        long j5 = copyOf[0];
        copyOf[0] = j5 + (r3 << 26);
        copyOf[1] = copyOf[1] - (-((int) (((j5 >> 31) & j5) >> 26)));
        for (int r33 = 0; r33 < 2; r33++) {
            int r9 = 0;
            while (r9 < 9) {
                long j6 = copyOf[r9];
                int r13 = zzd[r9 & 1];
                copyOf[r9] = zzc[r12] & j6;
                r9++;
                copyOf[r9] = copyOf[r9] + ((int) (j6 >> r13));
            }
        }
        copyOf[9] = 33554431 & copyOf[9];
        copyOf[0] = copyOf[0] + (((int) (j >> 25)) * 19);
        int r34 = ~((((int) j2) - 67108845) >> 31);
        for (int r43 = 1; r43 < 10; r43++) {
            int r6 = ~(((int) copyOf[r43]) ^ zzc[r43 & 1]);
            int r62 = r6 & (r6 << 16);
            int r63 = r62 & (r62 << 8);
            int r64 = r63 & (r63 << 4);
            int r65 = r64 & (r64 << 2);
            r34 &= (r65 & (r65 + r65)) >> 31;
        }
        copyOf[0] = copyOf[0] - (67108845 & r34);
        long j7 = 33554431 & r34;
        copyOf[1] = copyOf[1] - j7;
        for (int r66 = 2; r66 < 10; r66 += 2) {
            copyOf[r66] = copyOf[r66] - (67108863 & r34);
            int r92 = r66 + 1;
            copyOf[r92] = copyOf[r92] - j7;
        }
        for (int r35 = 0; r35 < 10; r35++) {
            copyOf[r35] = copyOf[r35] << zzb[r35];
        }
        byte[] bArr = new byte[32];
        for (int r2 = 0; r2 < 10; r2++) {
            int[] r44 = zza;
            int r53 = r44[r2];
            byte b = bArr[r53];
            long j8 = copyOf[r2];
            bArr[r53] = (byte) (b | (j8 & 255));
            bArr[r44[r2] + 1] = (byte) (bArr[r5] | ((j8 >> 8) & 255));
            bArr[r44[r2] + 2] = (byte) (bArr[r52] | ((j8 >> 16) & 255));
            bArr[r44[r2] + 3] = (byte) (bArr[r4] | ((j8 >> 24) & 255));
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] zzk(byte[] bArr) {
        long[] jArr = new long[10];
        for (int r2 = 0; r2 < 10; r2++) {
            int r3 = zza[r2];
            jArr[r2] = (((((bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8)) | ((bArr[r3 + 2] & 255) << 16)) | ((bArr[r3 + 3] & 255) << 24)) >> zzb[r2]) & zzc[r2 & 1];
        }
        return jArr;
    }
}
