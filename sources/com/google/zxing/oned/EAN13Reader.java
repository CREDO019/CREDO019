package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

/* loaded from: classes3.dex */
public final class EAN13Reader extends UPCEANReader {
    static final int[] FIRST_DIGIT_ENCODINGS = {0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private final int[] decodeMiddleCounters = new int[4];

    @Override // com.google.zxing.oned.UPCEANReader
    protected int decodeMiddle(BitArray bitArray, int[] r12, StringBuilder sb) throws NotFoundException {
        int[] r0 = this.decodeMiddleCounters;
        r0[0] = 0;
        r0[1] = 0;
        r0[2] = 0;
        r0[3] = 0;
        int size = bitArray.getSize();
        int r122 = r12[1];
        int r5 = 0;
        for (int r4 = 0; r4 < 6 && r122 < size; r4++) {
            int decodeDigit = decodeDigit(bitArray, r0, r122, L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            for (int r9 : r0) {
                r122 += r9;
            }
            if (decodeDigit >= 10) {
                r5 |= 1 << (5 - r4);
            }
        }
        determineFirstDigit(sb, r5);
        int r123 = findGuardPattern(bitArray, r122, true, MIDDLE_PATTERN)[1];
        for (int r2 = 0; r2 < 6 && r123 < size; r2++) {
            sb.append((char) (decodeDigit(bitArray, r0, r123, L_PATTERNS) + 48));
            for (int r7 : r0) {
                r123 += r7;
            }
        }
        return r123;
    }

    @Override // com.google.zxing.oned.UPCEANReader
    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.EAN_13;
    }

    private static void determineFirstDigit(StringBuilder sb, int r4) throws NotFoundException {
        for (int r1 = 0; r1 < 10; r1++) {
            if (r4 == FIRST_DIGIT_ENCODINGS[r1]) {
                sb.insert(0, (char) (r1 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
