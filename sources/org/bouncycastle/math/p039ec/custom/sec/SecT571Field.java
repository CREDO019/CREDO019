package org.bouncycastle.math.p039ec.custom.sec;

import com.facebook.imagepipeline.common.RotationOptions;
import java.math.BigInteger;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat576;

/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT571Field */
/* loaded from: classes5.dex */
public class SecT571Field {
    private static final long M59 = 576460752303423487L;
    private static final long[] ROOT_Z = {3161836309350906777L, -7642453882179322845L, -3821226941089661423L, 7312758566309945096L, -556661012383879292L, 8945041530681231562L, -4750851271514160027L, 6847946401097695794L, 541669439031730457L};

    private static void add(long[] jArr, int r7, long[] jArr2, int r9, long[] jArr3, int r11) {
        for (int r0 = 0; r0 < 9; r0++) {
            jArr3[r11 + r0] = jArr[r7 + r0] ^ jArr2[r9 + r0];
        }
    }

    public static void add(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int r0 = 0; r0 < 9; r0++) {
            jArr3[r0] = jArr[r0] ^ jArr2[r0];
        }
    }

    private static void addBothTo(long[] jArr, int r9, long[] jArr2, int r11, long[] jArr3, int r13) {
        for (int r0 = 0; r0 < 9; r0++) {
            int r1 = r13 + r0;
            jArr3[r1] = jArr3[r1] ^ (jArr[r9 + r0] ^ jArr2[r11 + r0]);
        }
    }

    public static void addBothTo(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int r0 = 0; r0 < 9; r0++) {
            jArr3[r0] = jArr3[r0] ^ (jArr[r0] ^ jArr2[r0]);
        }
    }

    public static void addExt(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int r0 = 0; r0 < 18; r0++) {
            jArr3[r0] = jArr[r0] ^ jArr2[r0];
        }
    }

    public static void addOne(long[] jArr, long[] jArr2) {
        jArr2[0] = jArr[0] ^ 1;
        for (int r0 = 1; r0 < 9; r0++) {
            jArr2[r0] = jArr[r0];
        }
    }

    private static void addTo(long[] jArr, long[] jArr2) {
        for (int r0 = 0; r0 < 9; r0++) {
            jArr2[r0] = jArr2[r0] ^ jArr[r0];
        }
    }

    public static long[] fromBigInteger(BigInteger bigInteger) {
        return Nat.fromBigInteger64(571, bigInteger);
    }

    public static void halfTrace(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat576.createExt64();
        Nat576.copy64(jArr, jArr2);
        for (int r1 = 1; r1 < 571; r1 += 2) {
            implSquare(jArr2, createExt64);
            reduce(createExt64, jArr2);
            implSquare(jArr2, createExt64);
            reduce(createExt64, jArr2);
            addTo(jArr, jArr2);
        }
    }

    protected static void implMultiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[16];
        for (int r10 = 0; r10 < 9; r10++) {
            implMulwAcc(jArr4, jArr[r10], jArr2[r10], jArr3, r10 << 1);
        }
        long j = jArr3[0];
        long j2 = jArr3[1];
        long j3 = j ^ jArr3[2];
        jArr3[1] = j3 ^ j2;
        long j4 = j2 ^ jArr3[3];
        long j5 = j3 ^ jArr3[4];
        jArr3[2] = j5 ^ j4;
        long j6 = j4 ^ jArr3[5];
        long j7 = j5 ^ jArr3[6];
        jArr3[3] = j7 ^ j6;
        long j8 = j6 ^ jArr3[7];
        long j9 = j7 ^ jArr3[8];
        jArr3[4] = j9 ^ j8;
        long j10 = j8 ^ jArr3[9];
        long j11 = j9 ^ jArr3[10];
        jArr3[5] = j11 ^ j10;
        long j12 = j10 ^ jArr3[11];
        long j13 = j11 ^ jArr3[12];
        jArr3[6] = j13 ^ j12;
        long j14 = j12 ^ jArr3[13];
        long j15 = j13 ^ jArr3[14];
        jArr3[7] = j15 ^ j14;
        long j16 = j14 ^ jArr3[15];
        long j17 = j15 ^ jArr3[16];
        jArr3[8] = j17 ^ j16;
        long j18 = j17 ^ (j16 ^ jArr3[17]);
        jArr3[9] = jArr3[0] ^ j18;
        jArr3[10] = jArr3[1] ^ j18;
        jArr3[11] = jArr3[2] ^ j18;
        jArr3[12] = jArr3[3] ^ j18;
        jArr3[13] = jArr3[4] ^ j18;
        jArr3[14] = jArr3[5] ^ j18;
        jArr3[15] = jArr3[6] ^ j18;
        jArr3[16] = jArr3[7] ^ j18;
        jArr3[17] = jArr3[8] ^ j18;
        implMulwAcc(jArr4, jArr[0] ^ jArr[1], jArr2[0] ^ jArr2[1], jArr3, 1);
        implMulwAcc(jArr4, jArr[0] ^ jArr[2], jArr2[0] ^ jArr2[2], jArr3, 2);
        implMulwAcc(jArr4, jArr[0] ^ jArr[3], jArr2[0] ^ jArr2[3], jArr3, 3);
        implMulwAcc(jArr4, jArr[1] ^ jArr[2], jArr2[1] ^ jArr2[2], jArr3, 3);
        implMulwAcc(jArr4, jArr[0] ^ jArr[4], jArr2[0] ^ jArr2[4], jArr3, 4);
        implMulwAcc(jArr4, jArr[1] ^ jArr[3], jArr2[1] ^ jArr2[3], jArr3, 4);
        implMulwAcc(jArr4, jArr[0] ^ jArr[5], jArr2[0] ^ jArr2[5], jArr3, 5);
        implMulwAcc(jArr4, jArr[1] ^ jArr[4], jArr2[1] ^ jArr2[4], jArr3, 5);
        implMulwAcc(jArr4, jArr[2] ^ jArr[3], jArr2[2] ^ jArr2[3], jArr3, 5);
        implMulwAcc(jArr4, jArr[0] ^ jArr[6], jArr2[0] ^ jArr2[6], jArr3, 6);
        implMulwAcc(jArr4, jArr[1] ^ jArr[5], jArr2[1] ^ jArr2[5], jArr3, 6);
        implMulwAcc(jArr4, jArr[2] ^ jArr[4], jArr2[2] ^ jArr2[4], jArr3, 6);
        implMulwAcc(jArr4, jArr[0] ^ jArr[7], jArr2[0] ^ jArr2[7], jArr3, 7);
        implMulwAcc(jArr4, jArr[1] ^ jArr[6], jArr2[1] ^ jArr2[6], jArr3, 7);
        implMulwAcc(jArr4, jArr[2] ^ jArr[5], jArr2[2] ^ jArr2[5], jArr3, 7);
        implMulwAcc(jArr4, jArr[3] ^ jArr[4], jArr2[3] ^ jArr2[4], jArr3, 7);
        implMulwAcc(jArr4, jArr[0] ^ jArr[8], jArr2[0] ^ jArr2[8], jArr3, 8);
        implMulwAcc(jArr4, jArr[1] ^ jArr[7], jArr2[1] ^ jArr2[7], jArr3, 8);
        implMulwAcc(jArr4, jArr[2] ^ jArr[6], jArr2[2] ^ jArr2[6], jArr3, 8);
        implMulwAcc(jArr4, jArr[3] ^ jArr[5], jArr2[3] ^ jArr2[5], jArr3, 8);
        implMulwAcc(jArr4, jArr[1] ^ jArr[8], jArr2[1] ^ jArr2[8], jArr3, 9);
        implMulwAcc(jArr4, jArr[2] ^ jArr[7], jArr2[2] ^ jArr2[7], jArr3, 9);
        implMulwAcc(jArr4, jArr[3] ^ jArr[6], jArr2[3] ^ jArr2[6], jArr3, 9);
        implMulwAcc(jArr4, jArr[4] ^ jArr[5], jArr2[4] ^ jArr2[5], jArr3, 9);
        implMulwAcc(jArr4, jArr[2] ^ jArr[8], jArr2[2] ^ jArr2[8], jArr3, 10);
        implMulwAcc(jArr4, jArr[3] ^ jArr[7], jArr2[3] ^ jArr2[7], jArr3, 10);
        implMulwAcc(jArr4, jArr[4] ^ jArr[6], jArr2[4] ^ jArr2[6], jArr3, 10);
        implMulwAcc(jArr4, jArr[3] ^ jArr[8], jArr2[3] ^ jArr2[8], jArr3, 11);
        implMulwAcc(jArr4, jArr[4] ^ jArr[7], jArr2[4] ^ jArr2[7], jArr3, 11);
        implMulwAcc(jArr4, jArr[5] ^ jArr[6], jArr2[5] ^ jArr2[6], jArr3, 11);
        implMulwAcc(jArr4, jArr[4] ^ jArr[8], jArr2[4] ^ jArr2[8], jArr3, 12);
        implMulwAcc(jArr4, jArr[5] ^ jArr[7], jArr2[5] ^ jArr2[7], jArr3, 12);
        implMulwAcc(jArr4, jArr[5] ^ jArr[8], jArr2[5] ^ jArr2[8], jArr3, 13);
        implMulwAcc(jArr4, jArr[6] ^ jArr[7], jArr2[6] ^ jArr2[7], jArr3, 13);
        implMulwAcc(jArr4, jArr[6] ^ jArr[8], jArr2[6] ^ jArr2[8], jArr3, 14);
        implMulwAcc(jArr4, jArr[7] ^ jArr[8], jArr2[7] ^ jArr2[8], jArr3, 15);
    }

    protected static void implMultiplyPrecomp(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int r1 = 56; r1 >= 0; r1 -= 8) {
            for (int r3 = 1; r3 < 9; r3 += 2) {
                int r5 = (int) (jArr[r3] >>> r1);
                addBothTo(jArr2, (r5 & 15) * 9, jArr2, (((r5 >>> 4) & 15) + 16) * 9, jArr3, r3 - 1);
            }
            Nat.shiftUpBits64(16, jArr3, 0, 8, 0L);
        }
        for (int r0 = 56; r0 >= 0; r0 -= 8) {
            for (int r12 = 0; r12 < 9; r12 += 2) {
                int r4 = (int) (jArr[r12] >>> r0);
                addBothTo(jArr2, (r4 & 15) * 9, jArr2, (((r4 >>> 4) & 15) + 16) * 9, jArr3, r12);
            }
            if (r0 > 0) {
                Nat.shiftUpBits64(18, jArr3, 0, 8, 0L);
            }
        }
    }

    protected static void implMulwAcc(long[] jArr, long j, long j2, long[] jArr2, int r19) {
        long j3 = j;
        jArr[1] = j2;
        for (int r3 = 2; r3 < 16; r3 += 2) {
            jArr[r3] = jArr[r3 >>> 1] << 1;
            jArr[r3 + 1] = jArr[r3] ^ j2;
        }
        int r32 = (int) j3;
        long j4 = 0;
        long j5 = jArr[r32 & 15] ^ (jArr[(r32 >>> 4) & 15] << 4);
        int r33 = 56;
        do {
            int r10 = (int) (j3 >>> r33);
            long j6 = (jArr[(r10 >>> 4) & 15] << 4) ^ jArr[r10 & 15];
            j5 ^= j6 << r33;
            j4 ^= j6 >>> (-r33);
            r33 -= 8;
        } while (r33 > 0);
        for (int r34 = 0; r34 < 7; r34++) {
            j3 = (j3 & (-72340172838076674L)) >>> 1;
            j4 ^= ((j2 << r34) >> 63) & j3;
        }
        jArr2[r19] = jArr2[r19] ^ j5;
        int r0 = r19 + 1;
        jArr2[r0] = jArr2[r0] ^ j4;
    }

    protected static void implSquare(long[] jArr, long[] jArr2) {
        Interleave.expand64To128(jArr, 0, 9, jArr2, 0);
    }

    public static void invert(long[] jArr, long[] jArr2) {
        if (Nat576.isZero64(jArr)) {
            throw new IllegalStateException();
        }
        long[] create64 = Nat576.create64();
        long[] create642 = Nat576.create64();
        long[] create643 = Nat576.create64();
        square(jArr, create643);
        square(create643, create64);
        square(create64, create642);
        multiply(create64, create642, create64);
        squareN(create64, 2, create642);
        multiply(create64, create642, create64);
        multiply(create64, create643, create64);
        squareN(create64, 5, create642);
        multiply(create64, create642, create64);
        squareN(create642, 5, create642);
        multiply(create64, create642, create64);
        squareN(create64, 15, create642);
        multiply(create64, create642, create643);
        squareN(create643, 30, create64);
        squareN(create64, 30, create642);
        multiply(create64, create642, create64);
        squareN(create64, 60, create642);
        multiply(create64, create642, create64);
        squareN(create642, 60, create642);
        multiply(create64, create642, create64);
        squareN(create64, RotationOptions.ROTATE_180, create642);
        multiply(create64, create642, create64);
        squareN(create642, RotationOptions.ROTATE_180, create642);
        multiply(create64, create642, create64);
        multiply(create64, create643, jArr2);
    }

    public static void multiply(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiply(jArr, jArr2, createExt64);
        reduce(createExt64, jArr3);
    }

    public static void multiplyAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiply(jArr, jArr2, createExt64);
        addExt(jArr3, createExt64, jArr3);
    }

    public static void multiplyPrecomp(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiplyPrecomp(jArr, jArr2, createExt64);
        reduce(createExt64, jArr3);
    }

    public static void multiplyPrecompAddToExt(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] createExt64 = Nat576.createExt64();
        implMultiplyPrecomp(jArr, jArr2, createExt64);
        addExt(jArr3, createExt64, jArr3);
    }

    public static long[] precompMultiplicand(long[] jArr) {
        long[] jArr2 = new long[288];
        int r1 = 0;
        System.arraycopy(jArr, 0, jArr2, 9, 9);
        int r9 = 7;
        while (r9 > 0) {
            int r8 = r1 + 18;
            Nat.shiftUpBit64(9, jArr2, r8 >>> 1, 0L, jArr2, r8);
            reduce5(jArr2, r8);
            add(jArr2, 9, jArr2, r8, jArr2, r8 + 9);
            r9--;
            r1 = r8;
        }
        Nat.shiftUpBits64(144, jArr2, 0, 4, 0L, jArr2, 144);
        return jArr2;
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        long j = jArr[9];
        long j2 = jArr[17];
        long j3 = (((j ^ (j2 >>> 59)) ^ (j2 >>> 57)) ^ (j2 >>> 54)) ^ (j2 >>> 49);
        long j4 = (j2 << 15) ^ (((jArr[8] ^ (j2 << 5)) ^ (j2 << 7)) ^ (j2 << 10));
        for (int r9 = 16; r9 >= 10; r9--) {
            long j5 = jArr[r9];
            jArr2[r9 - 8] = (((j4 ^ (j5 >>> 59)) ^ (j5 >>> 57)) ^ (j5 >>> 54)) ^ (j5 >>> 49);
            j4 = (((jArr[r9 - 9] ^ (j5 << 5)) ^ (j5 << 7)) ^ (j5 << 10)) ^ (j5 << 15);
        }
        jArr2[1] = (((j4 ^ (j3 >>> 59)) ^ (j3 >>> 57)) ^ (j3 >>> 54)) ^ (j3 >>> 49);
        long j6 = (j3 << 15) ^ (((jArr[0] ^ (j3 << 5)) ^ (j3 << 7)) ^ (j3 << 10));
        long j7 = jArr2[8];
        long j8 = j7 >>> 59;
        jArr2[0] = (((j6 ^ j8) ^ (j8 << 2)) ^ (j8 << 5)) ^ (j8 << 10);
        jArr2[8] = M59 & j7;
    }

    public static void reduce5(long[] jArr, int r12) {
        int r0 = r12 + 8;
        long j = jArr[r0];
        long j2 = j >>> 59;
        jArr[r12] = ((j2 << 10) ^ (((j2 << 2) ^ j2) ^ (j2 << 5))) ^ jArr[r12];
        jArr[r0] = j & M59;
    }

    public static void sqrt(long[] jArr, long[] jArr2) {
        long[] create64 = Nat576.create64();
        long[] create642 = Nat576.create64();
        int r3 = 0;
        for (int r2 = 0; r2 < 4; r2++) {
            int r7 = r3 + 1;
            long unshuffle = Interleave.unshuffle(jArr[r3]);
            r3 = r7 + 1;
            long unshuffle2 = Interleave.unshuffle(jArr[r7]);
            create64[r2] = (BodyPartID.bodyIdMax & unshuffle) | (unshuffle2 << 32);
            create642[r2] = (unshuffle >>> 32) | ((-4294967296L) & unshuffle2);
        }
        long unshuffle3 = Interleave.unshuffle(jArr[r3]);
        create64[4] = BodyPartID.bodyIdMax & unshuffle3;
        create642[4] = unshuffle3 >>> 32;
        multiply(create642, ROOT_Z, jArr2);
        add(jArr2, create64, jArr2);
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat576.createExt64();
        implSquare(jArr, createExt64);
        reduce(createExt64, jArr2);
    }

    public static void squareAddToExt(long[] jArr, long[] jArr2) {
        long[] createExt64 = Nat576.createExt64();
        implSquare(jArr, createExt64);
        addExt(jArr2, createExt64, jArr2);
    }

    public static void squareN(long[] jArr, int r2, long[] jArr2) {
        long[] createExt64 = Nat576.createExt64();
        implSquare(jArr, createExt64);
        while (true) {
            reduce(createExt64, jArr2);
            r2--;
            if (r2 <= 0) {
                return;
            }
            implSquare(jArr2, createExt64);
        }
    }

    public static int trace(long[] jArr) {
        return ((int) ((jArr[0] ^ (jArr[8] >>> 49)) ^ (jArr[8] >>> 57))) & 1;
    }
}
