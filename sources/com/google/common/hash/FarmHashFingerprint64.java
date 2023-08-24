package com.google.common.hash;

import com.google.common.base.Preconditions;
import org.bouncycastle.asn1.cmc.BodyPartID;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();

    /* renamed from: K0 */
    private static final long f1161K0 = -4348849565147123417L;

    /* renamed from: K1 */
    private static final long f1162K1 = -5435081209227447693L;

    /* renamed from: K2 */
    private static final long f1163K2 = -7286425919675154353L;

    private static long hashLength16(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    private static long shiftMix(long j) {
        return j ^ (j >>> 47);
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }

    FarmHashFingerprint64() {
    }

    @Override // com.google.common.hash.AbstractNonStreamingHashFunction, com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int r4, int r5) {
        Preconditions.checkPositionIndexes(r4, r4 + r5, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, r4, r5));
    }

    static long fingerprint(byte[] bArr, int r2, int r3) {
        if (r3 <= 32) {
            if (r3 <= 16) {
                return hashLength0to16(bArr, r2, r3);
            }
            return hashLength17to32(bArr, r2, r3);
        } else if (r3 <= 64) {
            return hashLength33To64(bArr, r2, r3);
        } else {
            return hashLength65Plus(bArr, r2, r3);
        }
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int r7, long j, long j2, long[] jArr) {
        long load64 = LittleEndianByteArray.load64(bArr, r7);
        long load642 = LittleEndianByteArray.load64(bArr, r7 + 8);
        long load643 = LittleEndianByteArray.load64(bArr, r7 + 16);
        long load644 = LittleEndianByteArray.load64(bArr, r7 + 24);
        long j3 = j + load64;
        long j4 = load642 + j3 + load643;
        jArr[0] = j4 + load644;
        jArr[1] = Long.rotateRight(j2 + j3 + load644, 21) + Long.rotateRight(j4, 44) + j3;
    }

    private static long hashLength0to16(byte[] bArr, int r13, int r14) {
        if (r14 >= 8) {
            long j = (r14 * 2) + f1163K2;
            long load64 = LittleEndianByteArray.load64(bArr, r13) + f1163K2;
            long load642 = LittleEndianByteArray.load64(bArr, (r13 + r14) - 8);
            return hashLength16((Long.rotateRight(load642, 37) * j) + load64, (Long.rotateRight(load64, 25) + load642) * j, j);
        } else if (r14 >= 4) {
            return hashLength16(r14 + ((LittleEndianByteArray.load32(bArr, r13) & BodyPartID.bodyIdMax) << 3), LittleEndianByteArray.load32(bArr, (r13 + r14) - 4) & BodyPartID.bodyIdMax, (r14 * 2) + f1163K2);
        } else if (r14 > 0) {
            return shiftMix((((bArr[r13] & 255) + ((bArr[(r14 >> 1) + r13] & 255) << 8)) * f1163K2) ^ ((r14 + ((bArr[r13 + (r14 - 1)] & 255) << 2)) * f1161K0)) * f1163K2;
        } else {
            return f1163K2;
        }
    }

    private static long hashLength17to32(byte[] bArr, int r18, int r19) {
        long j = (r19 * 2) + f1163K2;
        long load64 = LittleEndianByteArray.load64(bArr, r18) * f1162K1;
        long load642 = LittleEndianByteArray.load64(bArr, r18 + 8);
        int r7 = r18 + r19;
        long load643 = LittleEndianByteArray.load64(bArr, r7 - 8) * j;
        return hashLength16((LittleEndianByteArray.load64(bArr, r7 - 16) * f1163K2) + Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30), load64 + Long.rotateRight(load642 + f1163K2, 18) + load643, j);
    }

    private static long hashLength33To64(byte[] bArr, int r21, int r22) {
        long j = (r22 * 2) + f1163K2;
        long load64 = LittleEndianByteArray.load64(bArr, r21) * f1163K2;
        long load642 = LittleEndianByteArray.load64(bArr, r21 + 8);
        int r13 = r21 + r22;
        long load643 = LittleEndianByteArray.load64(bArr, r13 - 8) * j;
        long rotateRight = Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30) + (LittleEndianByteArray.load64(bArr, r13 - 16) * f1163K2);
        long hashLength16 = hashLength16(rotateRight, load643 + Long.rotateRight(load642 + f1163K2, 18) + load64, j);
        long load644 = LittleEndianByteArray.load64(bArr, r21 + 16) * j;
        long load645 = LittleEndianByteArray.load64(bArr, r21 + 24);
        long load646 = (rotateRight + LittleEndianByteArray.load64(bArr, r13 - 32)) * j;
        return hashLength16(((hashLength16 + LittleEndianByteArray.load64(bArr, r13 - 24)) * j) + Long.rotateRight(load644 + load645, 43) + Long.rotateRight(load646, 30), load644 + Long.rotateRight(load645 + load64, 18) + load646, j);
    }

    private static long hashLength65Plus(byte[] bArr, int r25, int r26) {
        long shiftMix = shiftMix(-7956866745689871395L) * f1163K2;
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long load64 = 95310865018149119L + LittleEndianByteArray.load64(bArr, r25);
        int r2 = r26 - 1;
        int r11 = r25 + ((r2 / 64) * 64);
        int r12 = r2 & 63;
        int r13 = (r11 + r12) - 63;
        long j = 2480279821605975764L;
        int r14 = r25;
        while (true) {
            long rotateRight = Long.rotateRight(load64 + j + jArr[0] + LittleEndianByteArray.load64(bArr, r14 + 8), 37) * f1162K1;
            long rotateRight2 = Long.rotateRight(j + jArr[1] + LittleEndianByteArray.load64(bArr, r14 + 48), 42) * f1162K1;
            long j2 = rotateRight ^ jArr2[1];
            long load642 = rotateRight2 + jArr[0] + LittleEndianByteArray.load64(bArr, r14 + 40);
            long rotateRight3 = Long.rotateRight(shiftMix + jArr2[0], 33) * f1162K1;
            weakHashLength32WithSeeds(bArr, r14, jArr[1] * f1162K1, j2 + jArr2[0], jArr);
            weakHashLength32WithSeeds(bArr, r14 + 32, rotateRight3 + jArr2[1], load642 + LittleEndianByteArray.load64(bArr, r14 + 16), jArr2);
            r14 += 64;
            if (r14 == r11) {
                long j3 = ((j2 & 255) << 1) + f1162K1;
                jArr2[0] = jArr2[0] + r12;
                jArr[0] = jArr[0] + jArr2[0];
                jArr2[0] = jArr2[0] + jArr[0];
                long rotateRight4 = (Long.rotateRight(((rotateRight3 + load642) + jArr[0]) + LittleEndianByteArray.load64(bArr, r13 + 8), 37) * j3) ^ (jArr2[1] * 9);
                long rotateRight5 = (Long.rotateRight(load642 + jArr[1] + LittleEndianByteArray.load64(bArr, r13 + 48), 42) * j3) + (jArr[0] * 9) + LittleEndianByteArray.load64(bArr, r13 + 40);
                long rotateRight6 = Long.rotateRight(j2 + jArr2[0], 33) * j3;
                weakHashLength32WithSeeds(bArr, r13, jArr[1] * j3, rotateRight4 + jArr2[0], jArr);
                weakHashLength32WithSeeds(bArr, r13 + 32, rotateRight6 + jArr2[1], LittleEndianByteArray.load64(bArr, r13 + 16) + rotateRight5, jArr2);
                return hashLength16(hashLength16(jArr[0], jArr2[0], j3) + (shiftMix(rotateRight5) * f1161K0) + rotateRight4, hashLength16(jArr[1], jArr2[1], j3) + rotateRight6, j3);
            }
            shiftMix = j2;
            j = load642;
            load64 = rotateRight3;
        }
    }
}
