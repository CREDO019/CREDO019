package org.bouncycastle.math.raw;

import androidx.core.view.MotionEventCompat;

/* loaded from: classes5.dex */
public class Interleave {
    private static final long M32 = 1431655765;
    private static final long M64 = 6148914691236517205L;
    private static final long M64R = -6148914691236517206L;

    public static int expand16to32(int r1) {
        int r12 = r1 & 65535;
        int r13 = (r12 | (r12 << 8)) & 16711935;
        int r14 = (r13 | (r13 << 4)) & 252645135;
        int r15 = (r14 | (r14 << 2)) & 858993459;
        return (r15 | (r15 << 1)) & 1431655765;
    }

    public static long expand32to64(int r6) {
        int bitPermuteStep = Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(r6, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 8), 15728880, 4), 202116108, 2), 572662306, 1);
        return (((bitPermuteStep >>> 1) & M32) << 32) | (M32 & bitPermuteStep);
    }

    public static void expand64To128(long j, long[] jArr, int r8) {
        long bitPermuteStep = Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(j, 4294901760L, 16), 280375465148160L, 8), 67555025218437360L, 4), 868082074056920076L, 2), 2459565876494606882L, 1);
        jArr[r8] = bitPermuteStep & M64;
        jArr[r8 + 1] = (bitPermuteStep >>> 1) & M64;
    }

    public static void expand64To128(long[] jArr, int r4, int r5, long[] jArr2, int r7) {
        for (int r0 = 0; r0 < r5; r0++) {
            expand64To128(jArr[r4 + r0], jArr2, r7);
            r7 += 2;
        }
    }

    public static void expand64To128Rev(long j, long[] jArr, int r8) {
        long bitPermuteStep = Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(j, 4294901760L, 16), 280375465148160L, 8), 67555025218437360L, 4), 868082074056920076L, 2), 2459565876494606882L, 1);
        jArr[r8] = bitPermuteStep & M64R;
        jArr[r8 + 1] = (bitPermuteStep << 1) & M64R;
    }

    public static int expand8to16(int r1) {
        int r12 = r1 & 255;
        int r13 = (r12 | (r12 << 4)) & 3855;
        int r14 = (r13 | (r13 << 2)) & 13107;
        return (r14 | (r14 << 1)) & 21845;
    }

    public static int shuffle(int r2) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(r2, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 8), 15728880, 4), 202116108, 2), 572662306, 1);
    }

    public static long shuffle(long j) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(j, 4294901760L, 16), 280375465148160L, 8), 67555025218437360L, 4), 868082074056920076L, 2), 2459565876494606882L, 1);
    }

    public static int shuffle2(int r2) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(r2, 11141290, 7), 52428, 14), 15728880, 4), (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 8);
    }

    public static long shuffle2(long j) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(j, 4278255360L, 24), 57421771435671756L, 6), 264913582878960L, 12), 723401728380766730L, 3);
    }

    public static long shuffle3(long j) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(j, 47851476196393130L, 7), 225176545447116L, 14), 4042322160L, 28);
    }

    public static int unshuffle(int r2) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(r2, 572662306, 1), 202116108, 2), 15728880, 4), (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 8);
    }

    public static long unshuffle(long j) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(j, 2459565876494606882L, 1), 868082074056920076L, 2), 67555025218437360L, 4), 280375465148160L, 8), 4294901760L, 16);
    }

    public static int unshuffle2(int r2) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(r2, (int) MotionEventCompat.ACTION_POINTER_INDEX_MASK, 8), 15728880, 4), 52428, 14), 11141290, 7);
    }

    public static long unshuffle2(long j) {
        return Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(Bits.bitPermuteStep(j, 723401728380766730L, 3), 264913582878960L, 12), 57421771435671756L, 6), 4278255360L, 24);
    }

    public static long unshuffle3(long j) {
        return shuffle3(j);
    }
}
