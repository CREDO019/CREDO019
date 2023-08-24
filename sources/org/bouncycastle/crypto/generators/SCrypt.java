package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.Salsa20Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SCrypt {
    private SCrypt() {
    }

    private static void BlockMix(int[] r6, int[] r7, int[] r8, int[] r9, int r10) {
        System.arraycopy(r6, r6.length - 16, r7, 0, 16);
        int length = r6.length >>> 1;
        int r3 = 0;
        int r4 = 0;
        for (int r102 = r10 * 2; r102 > 0; r102--) {
            Xor(r7, r6, r3, r8);
            Salsa20Engine.salsaCore(8, r8, r7);
            System.arraycopy(r7, 0, r9, r4, 16);
            r4 = (length + r3) - r4;
            r3 += 16;
        }
    }

    private static void Clear(byte[] bArr) {
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    private static void Clear(int[] r1) {
        if (r1 != null) {
            Arrays.fill(r1, 0);
        }
    }

    private static void ClearAll(int[][] r2) {
        for (int[] r1 : r2) {
            Clear(r1);
        }
    }

    private static byte[] MFcrypt(byte[] bArr, byte[] bArr2, int r9, int r10, int r11, int r12) {
        int r0 = r10 * 128;
        byte[] SingleIterationPBKDF2 = SingleIterationPBKDF2(bArr, bArr2, r11 * r0);
        int[] r112 = null;
        try {
            int length = SingleIterationPBKDF2.length >>> 2;
            r112 = new int[length];
            Pack.littleEndianToInt(SingleIterationPBKDF2, 0, r112);
            int r5 = 0;
            for (int r4 = r9 * r10; r9 - r5 > 2 && r4 > 1024; r4 >>>= 1) {
                r5++;
            }
            int r02 = r0 >>> 2;
            for (int r2 = 0; r2 < length; r2 += r02) {
                SMix(r112, r2, r9, r5, r10);
            }
            Pack.intToLittleEndian(r112, SingleIterationPBKDF2, 0);
            return SingleIterationPBKDF2(bArr, SingleIterationPBKDF2, r12);
        } finally {
            Clear(SingleIterationPBKDF2);
            Clear(r112);
        }
    }

    private static void SMix(int[] r19, int r20, int r21, int r22, int r23) {
        int r5 = r21 >>> r22;
        int r7 = 1 << r22;
        int r8 = r5 - 1;
        int numberOfTrailingZeros = Integers.numberOfTrailingZeros(r21) - r22;
        int r9 = r23 * 32;
        int[] r11 = new int[16];
        int[] r10 = new int[16];
        int[] r12 = new int[r9];
        int[] r13 = new int[r9];
        int[][] r14 = new int[r7];
        try {
            System.arraycopy(r19, r20, r13, 0, r9);
            int r15 = 0;
            while (r15 < r7) {
                int[] r6 = new int[r5 * r9];
                r14[r15] = r6;
                int r17 = r7;
                int r0 = 0;
                int r72 = 0;
                while (r72 < r5) {
                    System.arraycopy(r13, 0, r6, r0, r9);
                    int r02 = r0 + r9;
                    BlockMix(r13, r11, r10, r12, r23);
                    System.arraycopy(r12, 0, r6, r02, r9);
                    r0 = r02 + r9;
                    BlockMix(r12, r11, r10, r13, r23);
                    r72 += 2;
                    r5 = r5;
                }
                r15++;
                r7 = r17;
            }
            int r03 = r21 - 1;
            for (int r52 = 0; r52 < r21; r52++) {
                int r62 = r13[r9 - 16] & r03;
                System.arraycopy(r14[r62 >>> numberOfTrailingZeros], (r62 & r8) * r9, r12, 0, r9);
                Xor(r12, r13, 0, r12);
                BlockMix(r12, r11, r10, r13, r23);
            }
            System.arraycopy(r13, 0, r19, r20, r9);
            ClearAll(r14);
            ClearAll(new int[][]{r13, r11, r10, r12});
        } catch (Throwable th) {
            ClearAll(r14);
            ClearAll(new int[][]{r13, r11, r10, r12});
            throw th;
        }
    }

    private static byte[] SingleIterationPBKDF2(byte[] bArr, byte[] bArr2, int r4) {
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        pKCS5S2ParametersGenerator.init(bArr, bArr2, 1);
        return ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedMacParameters(r4 * 8)).getKey();
    }

    private static void Xor(int[] r3, int[] r4, int r5, int[] r6) {
        for (int length = r6.length - 1; length >= 0; length--) {
            r6[length] = r3[length] ^ r4[r5 + length];
        }
    }

    public static byte[] generate(byte[] bArr, byte[] bArr2, int r5, int r6, int r7, int r8) {
        if (bArr != null) {
            if (bArr2 != null) {
                if (r5 <= 1 || !isPowerOf2(r5)) {
                    throw new IllegalArgumentException("Cost parameter N must be > 1 and a power of 2");
                }
                if (r6 != 1 || r5 < 65536) {
                    if (r6 >= 1) {
                        int r1 = Integer.MAX_VALUE / ((r6 * 128) * 8);
                        if (r7 >= 1 && r7 <= r1) {
                            if (r8 >= 1) {
                                return MFcrypt(bArr, bArr2, r5, r6, r7, r8);
                            }
                            throw new IllegalArgumentException("Generated key length dkLen must be >= 1.");
                        }
                        throw new IllegalArgumentException("Parallelisation parameter p must be >= 1 and <= " + r1 + " (based on block size r of " + r6 + ")");
                    }
                    throw new IllegalArgumentException("Block size r must be >= 1.");
                }
                throw new IllegalArgumentException("Cost parameter N must be > 1 and < 65536.");
            }
            throw new IllegalArgumentException("Salt S must be provided.");
        }
        throw new IllegalArgumentException("Passphrase P must be provided.");
    }

    private static boolean isPowerOf2(int r1) {
        return (r1 & (r1 + (-1))) == 0;
    }
}
