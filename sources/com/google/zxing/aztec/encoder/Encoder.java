package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* loaded from: classes3.dex */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int totalBitsInLayer(int r1, boolean z) {
        return ((z ? 88 : 112) + (r1 << 4)) * r1;
    }

    private Encoder() {
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    public static AztecCode encode(byte[] bArr, int r20, int r21) {
        BitArray bitArray;
        int r8;
        boolean z;
        int r7;
        int r4;
        int r10;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * r20) / 100) + 11;
        int size2 = encode.getSize() + size;
        int r5 = 0;
        int r6 = 1;
        if (r21 != 0) {
            z = r21 < 0;
            r7 = Math.abs(r21);
            if (r7 > (z ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(r21)));
            }
            r4 = totalBitsInLayer(r7, z);
            r8 = WORD_SIZE[r7];
            int r9 = r4 - (r4 % r8);
            bitArray = stuffBits(encode, r8);
            if (bitArray.getSize() + size > r9) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z && bitArray.getSize() > (r8 << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        } else {
            BitArray bitArray2 = null;
            int r82 = 0;
            int r92 = 0;
            while (r82 <= 32) {
                boolean z2 = r82 <= 3;
                int r11 = z2 ? r82 + 1 : r82;
                int r12 = totalBitsInLayer(r11, z2);
                if (size2 <= r12) {
                    if (bitArray2 == null || r92 != WORD_SIZE[r11]) {
                        int r72 = WORD_SIZE[r11];
                        r92 = r72;
                        bitArray2 = stuffBits(encode, r72);
                    }
                    int r13 = r12 - (r12 % r92);
                    if ((!z2 || bitArray2.getSize() <= (r92 << 6)) && bitArray2.getSize() + size <= r13) {
                        bitArray = bitArray2;
                        r8 = r92;
                        z = z2;
                        r7 = r11;
                        r4 = r12;
                    }
                }
                r82++;
                r5 = 0;
                r6 = 1;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, r4, r8);
        int size3 = bitArray.getSize() / r8;
        BitArray generateModeMessage = generateModeMessage(z, r7, size3);
        int r2 = (z ? 11 : 14) + (r7 << 2);
        int[] r83 = new int[r2];
        int r93 = 2;
        if (z) {
            for (int r102 = 0; r102 < r2; r102++) {
                r83[r102] = r102;
            }
            r10 = r2;
        } else {
            int r112 = r2 / 2;
            r10 = r2 + 1 + (((r112 - 1) / 15) * 2);
            int r122 = r10 / 2;
            for (int r132 = 0; r132 < r112; r132++) {
                int r14 = (r132 / 15) + r132;
                r83[(r112 - r132) - r6] = (r122 - r14) - 1;
                r83[r112 + r132] = r14 + r122 + r6;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(r10);
        int r123 = 0;
        int r133 = 0;
        while (r123 < r7) {
            int r142 = ((r7 - r123) << r93) + (z ? 9 : 12);
            int r15 = 0;
            while (r15 < r142) {
                int r16 = r15 << 1;
                while (r5 < r93) {
                    if (generateCheckWords.get(r133 + r16 + r5)) {
                        int r62 = r123 << 1;
                        bitMatrix.set(r83[r62 + r5], r83[r62 + r15]);
                    }
                    if (generateCheckWords.get((r142 << 1) + r133 + r16 + r5)) {
                        int r63 = r123 << 1;
                        bitMatrix.set(r83[r63 + r15], r83[((r2 - 1) - r63) - r5]);
                    }
                    if (generateCheckWords.get((r142 << 2) + r133 + r16 + r5)) {
                        int r64 = (r2 - 1) - (r123 << 1);
                        bitMatrix.set(r83[r64 - r5], r83[r64 - r15]);
                    }
                    if (generateCheckWords.get((r142 * 6) + r133 + r16 + r5)) {
                        int r94 = r123 << 1;
                        bitMatrix.set(r83[((r2 - 1) - r94) - r15], r83[r94 + r5]);
                    }
                    r5++;
                    r93 = 2;
                }
                r15++;
                r5 = 0;
                r93 = 2;
            }
            r133 += r142 << 3;
            r123++;
            r5 = 0;
            r93 = 2;
        }
        drawModeMessage(bitMatrix, z, r10, generateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, r10 / 2, 5);
        } else {
            int r1 = r10 / 2;
            drawBullsEye(bitMatrix, r1, 7);
            int r42 = 0;
            int r52 = 0;
            while (r52 < (r2 / 2) - 1) {
                for (int r84 = r1 & 1; r84 < r10; r84 += 2) {
                    int r95 = r1 - r42;
                    bitMatrix.set(r95, r84);
                    int r134 = r1 + r42;
                    bitMatrix.set(r134, r84);
                    bitMatrix.set(r84, r95);
                    bitMatrix.set(r84, r134);
                }
                r52 += 15;
                r42 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(r10);
        aztecCode.setLayers(r7);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int r5, int r6) {
        for (int r0 = 0; r0 < r6; r0 += 2) {
            int r1 = r5 - r0;
            int r2 = r1;
            while (true) {
                int r3 = r5 + r0;
                if (r2 <= r3) {
                    bitMatrix.set(r2, r1);
                    bitMatrix.set(r2, r3);
                    bitMatrix.set(r1, r2);
                    bitMatrix.set(r3, r2);
                    r2++;
                }
            }
        }
        int r02 = r5 - r6;
        bitMatrix.set(r02, r02);
        int r12 = r02 + 1;
        bitMatrix.set(r12, r02);
        bitMatrix.set(r02, r12);
        int r52 = r5 + r6;
        bitMatrix.set(r52, r02);
        bitMatrix.set(r52, r12);
        bitMatrix.set(r52, r52 - 1);
    }

    static BitArray generateModeMessage(boolean z, int r3, int r4) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(r3 - 1, 2);
            bitArray.appendBits(r4 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(r3 - 1, 5);
        bitArray.appendBits(r4 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int r4, BitArray bitArray) {
        int r42 = r4 / 2;
        int r0 = 0;
        if (z) {
            while (r0 < 7) {
                int r3 = (r42 - 3) + r0;
                if (bitArray.get(r0)) {
                    bitMatrix.set(r3, r42 - 5);
                }
                if (bitArray.get(r0 + 7)) {
                    bitMatrix.set(r42 + 5, r3);
                }
                if (bitArray.get(20 - r0)) {
                    bitMatrix.set(r3, r42 + 5);
                }
                if (bitArray.get(27 - r0)) {
                    bitMatrix.set(r42 - 5, r3);
                }
                r0++;
            }
            return;
        }
        while (r0 < 10) {
            int r32 = (r42 - 5) + r0 + (r0 / 5);
            if (bitArray.get(r0)) {
                bitMatrix.set(r32, r42 - 7);
            }
            if (bitArray.get(r0 + 10)) {
                bitMatrix.set(r42 + 7, r32);
            }
            if (bitArray.get(29 - r0)) {
                bitMatrix.set(r32, r42 + 7);
            }
            if (bitArray.get(39 - r0)) {
                bitMatrix.set(r42 - 7, r32);
            }
            r0++;
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int r4, int r5) {
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(r5));
        int r2 = r4 / r5;
        int[] bitsToWords = bitsToWords(bitArray, r5, r2);
        reedSolomonEncoder.encode(bitsToWords, r2 - (bitArray.getSize() / r5));
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, r4 % r5);
        for (int r22 : bitsToWords) {
            bitArray2.appendBits(r22, r5);
        }
        return bitArray2;
    }

    private static int[] bitsToWords(BitArray bitArray, int r8, int r9) {
        int[] r92 = new int[r9];
        int size = bitArray.getSize() / r8;
        for (int r2 = 0; r2 < size; r2++) {
            int r4 = 0;
            for (int r3 = 0; r3 < r8; r3++) {
                r4 |= bitArray.get((r2 * r8) + r3) ? 1 << ((r8 - r3) - 1) : 0;
            }
            r92[r2] = r4;
        }
        return r92;
    }

    private static GenericGF getGF(int r2) {
        if (r2 != 4) {
            if (r2 != 6) {
                if (r2 != 8) {
                    if (r2 != 10) {
                        if (r2 == 12) {
                            return GenericGF.AZTEC_DATA_12;
                        }
                        throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(r2)));
                    }
                    return GenericGF.AZTEC_DATA_10;
                }
                return GenericGF.AZTEC_DATA_8;
            }
            return GenericGF.AZTEC_DATA_6;
        }
        return GenericGF.AZTEC_PARAM;
    }

    static BitArray stuffBits(BitArray bitArray, int r10) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int r3 = (1 << r10) - 2;
        int r5 = 0;
        while (r5 < size) {
            int r7 = 0;
            for (int r6 = 0; r6 < r10; r6++) {
                int r8 = r5 + r6;
                if (r8 >= size || bitArray.get(r8)) {
                    r7 |= 1 << ((r10 - 1) - r6);
                }
            }
            int r62 = r7 & r3;
            if (r62 == r3) {
                bitArray2.appendBits(r62, r10);
            } else if (r62 == 0) {
                bitArray2.appendBits(r7 | 1, r10);
            } else {
                bitArray2.appendBits(r7, r10);
                r5 += r10;
            }
            r5--;
            r5 += r10;
        }
        return bitArray2;
    }
}
