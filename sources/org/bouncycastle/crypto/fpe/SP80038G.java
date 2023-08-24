package org.bouncycastle.crypto.fpe;

import java.math.BigInteger;
import kotlin.UShort;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
class SP80038G {
    protected static final int BLOCK_SIZE = 16;
    static final String FF1_DISABLED = "org.bouncycastle.fpe.disable_ff1";
    static final String FPE_DISABLED = "org.bouncycastle.fpe.disable";
    protected static final double LOG2 = Math.log(2.0d);
    protected static final double TWO_TO_96 = Math.pow(2.0d, 96.0d);

    SP80038G() {
    }

    protected static BigInteger[] calculateModUV(BigInteger bigInteger, int r4, int r5) {
        BigInteger[] bigIntegerArr = {bigInteger.pow(r4), bigIntegerArr[0]};
        if (r5 != r4) {
            bigIntegerArr[1] = bigIntegerArr[1].multiply(bigInteger);
        }
        return bigIntegerArr;
    }

    protected static byte[] calculateP_FF1(int r4, byte b, int r6, int r7) {
        byte[] bArr = {1, 2, 1, 0, (byte) (r4 >> 8), (byte) r4, 10, b};
        Pack.intToBigEndian(r6, bArr, 8);
        Pack.intToBigEndian(r7, bArr, 12);
        return bArr;
    }

    protected static byte[] calculateTweak64_FF3_1(byte[] bArr) {
        return new byte[]{bArr[0], bArr[1], bArr[2], (byte) (bArr[3] & 240), bArr[4], bArr[5], bArr[6], (byte) (bArr[3] << 4)};
    }

    protected static BigInteger calculateY_FF1(BlockCipher blockCipher, BigInteger bigInteger, byte[] bArr, int r7, int r8, int r9, byte[] bArr2, short[] sArr) {
        int length = bArr.length;
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(num(bigInteger, sArr));
        int r11 = ((-(length + r7 + 1)) & 15) + length;
        int r2 = r11 + 1 + r7;
        byte[] bArr3 = new byte[r2];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        bArr3[r11] = (byte) r9;
        System.arraycopy(asUnsignedByteArray, 0, bArr3, r2 - asUnsignedByteArray.length, asUnsignedByteArray.length);
        byte[] prf = prf(blockCipher, Arrays.concatenate(bArr2, bArr3));
        if (r8 > 16) {
            int r72 = ((r8 + 16) - 1) / 16;
            byte[] bArr4 = new byte[r72 * 16];
            System.arraycopy(prf, 0, bArr4, 0, 16);
            byte[] bArr5 = new byte[4];
            for (int r1 = 1; r1 < r72; r1++) {
                int r0 = r1 * 16;
                System.arraycopy(prf, 0, bArr4, r0, 16);
                Pack.intToBigEndian(r1, bArr5, 0);
                xor(bArr5, 0, bArr4, (r0 + 16) - 4, 4);
                blockCipher.processBlock(bArr4, r0, bArr4, r0);
            }
            prf = bArr4;
        }
        return num(prf, 0, r8);
    }

    protected static BigInteger calculateY_FF3(BlockCipher blockCipher, BigInteger bigInteger, byte[] bArr, int r6, int r7, short[] sArr) {
        byte[] bArr2 = new byte[16];
        Pack.intToBigEndian(r7, bArr2, 0);
        xor(bArr, r6, bArr2, 0, 4);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(num(bigInteger, sArr));
        if (16 - asUnsignedByteArray.length >= 4) {
            System.arraycopy(asUnsignedByteArray, 0, bArr2, 16 - asUnsignedByteArray.length, asUnsignedByteArray.length);
            rev(bArr2);
            blockCipher.processBlock(bArr2, 0, bArr2, 0);
            rev(bArr2);
            return num(bArr2, 0, 16);
        }
        throw new IllegalStateException("input out of range");
    }

    protected static void checkArgs(BlockCipher blockCipher, boolean z, int r2, byte[] bArr, int r4, int r5) {
        checkCipher(blockCipher);
        if (r2 < 2 || r2 > 256) {
            throw new IllegalArgumentException();
        }
        checkData(z, r2, bArr, r4, r5);
    }

    protected static void checkArgs(BlockCipher blockCipher, boolean z, int r2, short[] sArr, int r4, int r5) {
        checkCipher(blockCipher);
        if (r2 < 2 || r2 > 65536) {
            throw new IllegalArgumentException();
        }
        checkData(z, r2, sArr, r4, r5);
    }

    protected static void checkCipher(BlockCipher blockCipher) {
        if (16 != blockCipher.getBlockSize()) {
            throw new IllegalArgumentException();
        }
    }

    protected static void checkData(boolean z, int r2, byte[] bArr, int r4, int r5) {
        checkLength(z, r2, r5);
        for (int r1 = 0; r1 < r5; r1++) {
            if ((bArr[r4 + r1] & 255) >= r2) {
                throw new IllegalArgumentException("input data outside of radix");
            }
        }
    }

    protected static void checkData(boolean z, int r3, short[] sArr, int r5, int r6) {
        checkLength(z, r3, r6);
        for (int r2 = 0; r2 < r6; r2++) {
            if ((sArr[r5 + r2] & UShort.MAX_VALUE) >= r3) {
                throw new IllegalArgumentException("input data outside of radix");
            }
        }
    }

    private static void checkLength(boolean z, int r8, int r9) {
        int floor;
        if (r9 >= 2) {
            double d = r8;
            if (Math.pow(d, r9) >= 1000000.0d) {
                if (z || r9 <= (floor = ((int) Math.floor(Math.log(TWO_TO_96) / Math.log(d))) * 2)) {
                    return;
                }
                throw new IllegalArgumentException("maximum input length is " + floor);
            }
        }
        throw new IllegalArgumentException("input too short");
    }

    static short[] decFF1(BlockCipher blockCipher, int r18, byte[] bArr, int r20, int r21, int r22, short[] sArr, short[] sArr2) {
        int length = bArr.length;
        int ceil = (((int) Math.ceil((Math.log(r18) * r22) / LOG2)) + 7) / 8;
        int r12 = (((ceil + 3) / 4) * 4) + 4;
        byte[] calculateP_FF1 = calculateP_FF1(r18, (byte) r21, r20, length);
        BigInteger valueOf = BigInteger.valueOf(r18);
        BigInteger[] calculateModUV = calculateModUV(valueOf, r21, r22);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int r15 = r21;
        int r16 = 9;
        while (r16 >= 0) {
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            r15 = r20 - r15;
            str(valueOf, num(valueOf, sArr5).subtract(calculateY_FF1(blockCipher, valueOf, bArr, ceil, r12, r16, calculateP_FF1, sArr4)).mod(calculateModUV[r16 & 1]), r15, sArr5, 0);
            r16--;
            sArr3 = sArr5;
        }
        return Arrays.concatenate(sArr3, sArr4);
    }

    private static short[] decFF3_1(BlockCipher blockCipher, int r15, byte[] bArr, int r17, int r18, int r19, short[] sArr, short[] sArr2) {
        BigInteger valueOf = BigInteger.valueOf(r15);
        int r2 = r19;
        BigInteger[] calculateModUV = calculateModUV(valueOf, r18, r2);
        rev(sArr);
        rev(sArr2);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int r10 = 7;
        while (r10 >= 0) {
            int r11 = r17 - r2;
            int r22 = r10 & 1;
            str(valueOf, num(valueOf, sArr4).subtract(calculateY_FF3(blockCipher, valueOf, bArr, 4 - (r22 * 4), r10, sArr3)).mod(calculateModUV[1 - r22]), r11, sArr4, 0);
            r10--;
            r2 = r11;
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            sArr3 = sArr5;
        }
        rev(sArr3);
        rev(sArr4);
        return Arrays.concatenate(sArr3, sArr4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] decryptFF1(BlockCipher blockCipher, int r11, byte[] bArr, byte[] bArr2, int r14, int r15) {
        checkArgs(blockCipher, true, r11, bArr2, r14, r15);
        int r6 = r15 / 2;
        int r7 = r15 - r6;
        return toByte(decFF1(blockCipher, r11, bArr, r15, r6, r7, toShort(bArr2, r14, r6), toShort(bArr2, r14 + r6, r7)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] decryptFF1w(BlockCipher blockCipher, int r11, byte[] bArr, short[] sArr, int r14, int r15) {
        checkArgs(blockCipher, true, r11, sArr, r14, r15);
        int r6 = r15 / 2;
        int r7 = r15 - r6;
        short[] sArr2 = new short[r6];
        short[] sArr3 = new short[r7];
        System.arraycopy(sArr, r14, sArr2, 0, r6);
        System.arraycopy(sArr, r14 + r6, sArr3, 0, r7);
        return decFF1(blockCipher, r11, bArr, r15, r6, r7, sArr2, sArr3);
    }

    static byte[] decryptFF3(BlockCipher blockCipher, int r7, byte[] bArr, byte[] bArr2, int r10, int r11) {
        checkArgs(blockCipher, false, r7, bArr2, r10, r11);
        if (bArr.length == 8) {
            return implDecryptFF3(blockCipher, r7, bArr, bArr2, r10, r11);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] decryptFF3_1(BlockCipher blockCipher, int r9, byte[] bArr, byte[] bArr2, int r12, int r13) {
        checkArgs(blockCipher, false, r9, bArr2, r12, r13);
        if (bArr.length == 7) {
            return implDecryptFF3(blockCipher, r9, calculateTweak64_FF3_1(bArr), bArr2, r12, r13);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] decryptFF3_1w(BlockCipher blockCipher, int r9, byte[] bArr, short[] sArr, int r12, int r13) {
        checkArgs(blockCipher, false, r9, sArr, r12, r13);
        if (bArr.length == 7) {
            return implDecryptFF3w(blockCipher, r9, calculateTweak64_FF3_1(bArr), sArr, r12, r13);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    private static short[] encFF1(BlockCipher blockCipher, int r19, byte[] bArr, int r21, int r22, int r23, short[] sArr, short[] sArr2) {
        int length = bArr.length;
        int ceil = (((int) Math.ceil((Math.log(r19) * r23) / LOG2)) + 7) / 8;
        int r12 = (((ceil + 3) / 4) * 4) + 4;
        byte[] calculateP_FF1 = calculateP_FF1(r19, (byte) r22, r21, length);
        BigInteger valueOf = BigInteger.valueOf(r19);
        BigInteger[] calculateModUV = calculateModUV(valueOf, r22, r23);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int r16 = r23;
        int r7 = 0;
        while (r7 < 10) {
            int r17 = r7;
            short[] sArr5 = sArr3;
            sArr3 = sArr4;
            BigInteger calculateY_FF1 = calculateY_FF1(blockCipher, valueOf, bArr, ceil, r12, r7, calculateP_FF1, sArr3);
            int r3 = r21 - r16;
            str(valueOf, num(valueOf, sArr5).add(calculateY_FF1).mod(calculateModUV[r17 & 1]), r3, sArr5, 0);
            r7 = r17 + 1;
            r16 = r3;
            sArr4 = sArr5;
        }
        return Arrays.concatenate(sArr3, sArr4);
    }

    private static short[] encFF3_1(BlockCipher blockCipher, int r16, byte[] bArr, int r18, int r19, int r20, short[] sArr, short[] sArr2) {
        BigInteger valueOf = BigInteger.valueOf(r16);
        int r1 = r19;
        BigInteger[] calculateModUV = calculateModUV(valueOf, r1, r20);
        rev(sArr);
        rev(sArr2);
        short[] sArr3 = sArr;
        short[] sArr4 = sArr2;
        int r12 = 0;
        while (r12 < 8) {
            r1 = r18 - r1;
            int r2 = r12 & 1;
            str(valueOf, num(valueOf, sArr3).add(calculateY_FF3(blockCipher, valueOf, bArr, 4 - (r2 * 4), r12, sArr4)).mod(calculateModUV[1 - r2]), r1, sArr3, 0);
            r12++;
            short[] sArr5 = sArr4;
            sArr4 = sArr3;
            sArr3 = sArr5;
        }
        rev(sArr3);
        rev(sArr4);
        return Arrays.concatenate(sArr3, sArr4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] encryptFF1(BlockCipher blockCipher, int r11, byte[] bArr, byte[] bArr2, int r14, int r15) {
        checkArgs(blockCipher, true, r11, bArr2, r14, r15);
        int r6 = r15 / 2;
        int r7 = r15 - r6;
        return toByte(encFF1(blockCipher, r11, bArr, r15, r6, r7, toShort(bArr2, r14, r6), toShort(bArr2, r14 + r6, r7)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] encryptFF1w(BlockCipher blockCipher, int r11, byte[] bArr, short[] sArr, int r14, int r15) {
        checkArgs(blockCipher, true, r11, sArr, r14, r15);
        int r6 = r15 / 2;
        int r7 = r15 - r6;
        short[] sArr2 = new short[r6];
        short[] sArr3 = new short[r7];
        System.arraycopy(sArr, r14, sArr2, 0, r6);
        System.arraycopy(sArr, r14 + r6, sArr3, 0, r7);
        return encFF1(blockCipher, r11, bArr, r15, r6, r7, sArr2, sArr3);
    }

    static byte[] encryptFF3(BlockCipher blockCipher, int r7, byte[] bArr, byte[] bArr2, int r10, int r11) {
        checkArgs(blockCipher, false, r7, bArr2, r10, r11);
        if (bArr.length == 8) {
            return implEncryptFF3(blockCipher, r7, bArr, bArr2, r10, r11);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] encryptFF3_1(BlockCipher blockCipher, int r9, byte[] bArr, byte[] bArr2, int r12, int r13) {
        checkArgs(blockCipher, false, r9, bArr2, r12, r13);
        if (bArr.length == 7) {
            return encryptFF3(blockCipher, r9, calculateTweak64_FF3_1(bArr), bArr2, r12, r13);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short[] encryptFF3_1w(BlockCipher blockCipher, int r9, byte[] bArr, short[] sArr, int r12, int r13) {
        checkArgs(blockCipher, false, r9, sArr, r12, r13);
        if (bArr.length == 7) {
            return encryptFF3w(blockCipher, r9, calculateTweak64_FF3_1(bArr), sArr, r12, r13);
        }
        throw new IllegalArgumentException("tweak should be 56 bits");
    }

    static short[] encryptFF3w(BlockCipher blockCipher, int r7, byte[] bArr, short[] sArr, int r10, int r11) {
        checkArgs(blockCipher, false, r7, sArr, r10, r11);
        if (bArr.length == 8) {
            return implEncryptFF3w(blockCipher, r7, bArr, sArr, r10, r11);
        }
        throw new IllegalArgumentException();
    }

    protected static byte[] implDecryptFF3(BlockCipher blockCipher, int r9, byte[] bArr, byte[] bArr2, int r12, int r13) {
        int r4 = r13 / 2;
        int r5 = r13 - r4;
        return toByte(decFF3_1(blockCipher, r9, bArr, r13, r4, r5, toShort(bArr2, r12, r5), toShort(bArr2, r12 + r5, r4)));
    }

    protected static short[] implDecryptFF3w(BlockCipher blockCipher, int r9, byte[] bArr, short[] sArr, int r12, int r13) {
        int r4 = r13 / 2;
        int r5 = r13 - r4;
        short[] sArr2 = new short[r5];
        short[] sArr3 = new short[r4];
        System.arraycopy(sArr, r12, sArr2, 0, r5);
        System.arraycopy(sArr, r12 + r5, sArr3, 0, r4);
        return decFF3_1(blockCipher, r9, bArr, r13, r4, r5, sArr2, sArr3);
    }

    protected static byte[] implEncryptFF3(BlockCipher blockCipher, int r9, byte[] bArr, byte[] bArr2, int r12, int r13) {
        int r4 = r13 / 2;
        int r5 = r13 - r4;
        return toByte(encFF3_1(blockCipher, r9, bArr, r13, r4, r5, toShort(bArr2, r12, r5), toShort(bArr2, r12 + r5, r4)));
    }

    protected static short[] implEncryptFF3w(BlockCipher blockCipher, int r9, byte[] bArr, short[] sArr, int r12, int r13) {
        int r4 = r13 / 2;
        int r5 = r13 - r4;
        short[] sArr2 = new short[r5];
        short[] sArr3 = new short[r4];
        System.arraycopy(sArr, r12, sArr2, 0, r5);
        System.arraycopy(sArr, r12 + r5, sArr3, 0, r4);
        return encFF3_1(blockCipher, r9, bArr, r13, r4, r5, sArr2, sArr3);
    }

    protected static BigInteger num(BigInteger bigInteger, short[] sArr) {
        BigInteger bigInteger2 = BigIntegers.ZERO;
        for (short s : sArr) {
            bigInteger2 = bigInteger2.multiply(bigInteger).add(BigInteger.valueOf(s & UShort.MAX_VALUE));
        }
        return bigInteger2;
    }

    protected static BigInteger num(byte[] bArr, int r2, int r3) {
        return new BigInteger(1, Arrays.copyOfRange(bArr, r2, r3 + r2));
    }

    protected static byte[] prf(BlockCipher blockCipher, byte[] bArr) {
        if (bArr.length % 16 == 0) {
            int length = bArr.length / 16;
            byte[] bArr2 = new byte[16];
            for (int r4 = 0; r4 < length; r4++) {
                xor(bArr, r4 * 16, bArr2, 0, 16);
                blockCipher.processBlock(bArr2, 0, bArr2, 0);
            }
            return bArr2;
        }
        throw new IllegalArgumentException();
    }

    protected static void rev(byte[] bArr) {
        int length = bArr.length / 2;
        int length2 = bArr.length - 1;
        for (int r2 = 0; r2 < length; r2++) {
            byte b = bArr[r2];
            int r4 = length2 - r2;
            bArr[r2] = bArr[r4];
            bArr[r4] = b;
        }
    }

    protected static void rev(short[] sArr) {
        int length = sArr.length / 2;
        int length2 = sArr.length - 1;
        for (int r2 = 0; r2 < length; r2++) {
            short s = sArr[r2];
            int r4 = length2 - r2;
            sArr[r2] = sArr[r4];
            sArr[r4] = s;
        }
    }

    protected static void str(BigInteger bigInteger, BigInteger bigInteger2, int r6, short[] sArr, int r8) {
        if (bigInteger2.signum() < 0) {
            throw new IllegalArgumentException();
        }
        for (int r1 = 1; r1 <= r6; r1++) {
            BigInteger[] divideAndRemainder = bigInteger2.divideAndRemainder(bigInteger);
            sArr[(r8 + r6) - r1] = (short) divideAndRemainder[1].intValue();
            bigInteger2 = divideAndRemainder[0];
        }
        if (bigInteger2.signum() != 0) {
            throw new IllegalArgumentException();
        }
    }

    private static byte[] toByte(short[] sArr) {
        int length = sArr.length;
        byte[] bArr = new byte[length];
        for (int r2 = 0; r2 != length; r2++) {
            bArr[r2] = (byte) sArr[r2];
        }
        return bArr;
    }

    private static short[] toShort(byte[] bArr, int r4, int r5) {
        short[] sArr = new short[r5];
        for (int r1 = 0; r1 != r5; r1++) {
            sArr[r1] = (short) (bArr[r4 + r1] & 255);
        }
        return sArr;
    }

    protected static void xor(byte[] bArr, int r5, byte[] bArr2, int r7, int r8) {
        for (int r0 = 0; r0 < r8; r0++) {
            int r1 = r7 + r0;
            bArr2[r1] = (byte) (bArr2[r1] ^ bArr[r5 + r0]);
        }
    }
}
