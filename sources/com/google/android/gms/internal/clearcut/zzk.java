package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes2.dex */
public final class zzk {
    private static int zza(byte[] bArr, int r3) {
        return ((bArr[r3 + 3] & 255) << 24) | (bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8) | ((bArr[r3 + 2] & 255) << 16);
    }

    private static long zza(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    public static long zza(byte[] bArr) {
        int length = bArr.length;
        if (length < 0 || length > bArr.length) {
            StringBuilder sb = new StringBuilder(67);
            sb.append("Out of bound index with offput: 0 and length: ");
            sb.append(length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        int r8 = 37;
        char c = 0;
        if (length <= 32) {
            if (length > 16) {
                long j = (length << 1) - 7286425919675154353L;
                long zzb = zzb(bArr, 0) * (-5435081209227447693L);
                long zzb2 = zzb(bArr, 8);
                int r0 = length + 0;
                long zzb3 = zzb(bArr, r0 - 8) * j;
                return zza(Long.rotateRight(zzb + zzb2, 43) + Long.rotateRight(zzb3, 30) + (zzb(bArr, r0 - 16) * (-7286425919675154353L)), zzb + Long.rotateRight(zzb2 - 7286425919675154353L, 18) + zzb3, j);
            } else if (length >= 8) {
                long j2 = (length << 1) - 7286425919675154353L;
                long zzb4 = zzb(bArr, 0) - 7286425919675154353L;
                long zzb5 = zzb(bArr, (length + 0) - 8);
                return zza((Long.rotateRight(zzb5, 37) * j2) + zzb4, (Long.rotateRight(zzb4, 25) + zzb5) * j2, j2);
            } else if (length >= 4) {
                return zza(length + ((zza(bArr, 0) & BodyPartID.bodyIdMax) << 3), zza(bArr, (length + 0) - 4) & BodyPartID.bodyIdMax, (length << 1) - 7286425919675154353L);
            } else if (length > 0) {
                long j3 = (((bArr[0] & 255) + ((bArr[(length >> 1) + 0] & 255) << 8)) * (-7286425919675154353L)) ^ ((length + ((bArr[(length - 1) + 0] & 255) << 2)) * (-4348849565147123417L));
                return (j3 ^ (j3 >>> 47)) * (-7286425919675154353L);
            } else {
                return -7286425919675154353L;
            }
        } else if (length <= 64) {
            long j4 = (length << 1) - 7286425919675154353L;
            long zzb6 = zzb(bArr, 0) * (-7286425919675154353L);
            long zzb7 = zzb(bArr, 8);
            int r02 = length + 0;
            long zzb8 = zzb(bArr, r02 - 8) * j4;
            long rotateRight = Long.rotateRight(zzb6 + zzb7, 43) + Long.rotateRight(zzb8, 30) + (zzb(bArr, r02 - 16) * (-7286425919675154353L));
            long zza = zza(rotateRight, Long.rotateRight((-7286425919675154353L) + zzb7, 18) + zzb6 + zzb8, j4);
            long zzb9 = zzb(bArr, 16) * j4;
            long zzb10 = zzb(bArr, 24);
            long zzb11 = (rotateRight + zzb(bArr, r02 - 32)) * j4;
            return zza(Long.rotateRight(zzb9 + zzb10, 43) + Long.rotateRight(zzb11, 30) + ((zza + zzb(bArr, r02 - 24)) * j4), zzb9 + Long.rotateRight(zzb10 + zzb6, 18) + zzb11, j4);
        } else {
            long j5 = 2480279821605975764L;
            long j6 = 1390051526045402406L;
            long[] jArr = new long[2];
            long[] jArr2 = new long[2];
            long zzb12 = zzb(bArr, 0) + 95310865018149119L;
            int r03 = length - 1;
            int r4 = ((r03 / 64) << 6) + 0;
            int r3 = r03 & 63;
            int r19 = (r4 + r3) - 63;
            int r20 = 0;
            while (true) {
                long rotateRight2 = (Long.rotateRight(((zzb12 + j5) + jArr[c]) + zzb(bArr, r20 + 8), r8) * (-5435081209227447693L)) ^ jArr2[1];
                long rotateRight3 = (Long.rotateRight(j5 + jArr[1] + zzb(bArr, r20 + 48), 42) * (-5435081209227447693L)) + jArr[0] + zzb(bArr, r20 + 40);
                long rotateRight4 = Long.rotateRight(j6 + jArr2[0], 33) * (-5435081209227447693L);
                int r11 = r3;
                int r6 = r4;
                zza(bArr, r20, jArr[1] * (-5435081209227447693L), rotateRight2 + jArr2[0], jArr);
                zza(bArr, r20 + 32, rotateRight4 + jArr2[1], rotateRight3 + zzb(bArr, r20 + 16), jArr2);
                int r04 = r20 + 64;
                if (r04 == r6) {
                    long j7 = ((255 & rotateRight2) << 1) - 5435081209227447693L;
                    jArr2[0] = jArr2[0] + r11;
                    jArr[0] = jArr[0] + jArr2[0];
                    jArr2[0] = jArr2[0] + jArr[0];
                    long rotateRight5 = (Long.rotateRight(((rotateRight4 + rotateRight3) + jArr[0]) + zzb(bArr, r19 + 8), 37) * j7) ^ (jArr2[1] * 9);
                    long rotateRight6 = (Long.rotateRight(rotateRight3 + jArr[1] + zzb(bArr, r19 + 48), 42) * j7) + (jArr[0] * 9) + zzb(bArr, r19 + 40);
                    long rotateRight7 = Long.rotateRight(rotateRight2 + jArr2[0], 33) * j7;
                    zza(bArr, r19, jArr[1] * j7, rotateRight5 + jArr2[0], jArr);
                    zza(bArr, r19 + 32, jArr2[1] + rotateRight7, zzb(bArr, r19 + 16) + rotateRight6, jArr2);
                    return zza(zza(jArr[0], jArr2[0], j7) + (((rotateRight6 >>> 47) ^ rotateRight6) * (-4348849565147123417L)) + rotateRight5, zza(jArr[1], jArr2[1], j7) + rotateRight7, j7);
                }
                r20 = r04;
                r4 = r6;
                r3 = r11;
                j6 = rotateRight2;
                j5 = rotateRight3;
                zzb12 = rotateRight4;
                r8 = 37;
                c = 0;
            }
        }
    }

    private static void zza(byte[] bArr, int r7, long j, long j2, long[] jArr) {
        long zzb = zzb(bArr, r7);
        long zzb2 = zzb(bArr, r7 + 8);
        long zzb3 = zzb(bArr, r7 + 16);
        long zzb4 = zzb(bArr, r7 + 24);
        long j3 = j + zzb;
        long j4 = zzb2 + j3 + zzb3;
        jArr[0] = j4 + zzb4;
        jArr[1] = Long.rotateRight(j2 + j3 + zzb4, 21) + Long.rotateRight(j4, 44) + j3;
    }

    private static long zzb(byte[] bArr, int r2) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, r2, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }
}
