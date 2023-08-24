package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

/* loaded from: classes3.dex */
public final class UPCEReader extends UPCEANReader {
    private static final int[] MIDDLE_END_PATTERN = {1, 1, 1, 1, 1, 1};
    static final int[][] NUMSYS_AND_CHECK_DIGIT_PATTERNS = {new int[]{56, 52, 50, 49, 44, 38, 35, 42, 41, 37}, new int[]{7, 11, 13, 14, 19, 25, 28, 21, 22, 26}};
    private final int[] decodeMiddleCounters = new int[4];

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.zxing.oned.UPCEANReader
    public int decodeMiddle(BitArray bitArray, int[] r12, StringBuilder sb) throws NotFoundException {
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
        determineNumSysAndCheckDigit(sb, r5);
        return r122;
    }

    @Override // com.google.zxing.oned.UPCEANReader
    protected int[] decodeEnd(BitArray bitArray, int r4) throws NotFoundException {
        return findGuardPattern(bitArray, r4, true, MIDDLE_END_PATTERN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.zxing.oned.UPCEANReader
    public boolean checkChecksum(String str) throws FormatException {
        return super.checkChecksum(convertUPCEtoUPCA(str));
    }

    private static void determineNumSysAndCheckDigit(StringBuilder sb, int r5) throws NotFoundException {
        for (int r1 = 0; r1 <= 1; r1++) {
            for (int r2 = 0; r2 < 10; r2++) {
                if (r5 == NUMSYS_AND_CHECK_DIGIT_PATTERNS[r1][r2]) {
                    sb.insert(0, (char) (r1 + 48));
                    sb.append((char) (r2 + 48));
                    return;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.oned.UPCEANReader
    BarcodeFormat getBarcodeFormat() {
        return BarcodeFormat.UPC_E;
    }

    public static String convertUPCEtoUPCA(String str) {
        char[] cArr = new char[6];
        str.getChars(1, 7, cArr, 0);
        StringBuilder sb = new StringBuilder(12);
        sb.append(str.charAt(0));
        char c = cArr[5];
        switch (c) {
            case '0':
            case '1':
            case '2':
                sb.append(cArr, 0, 2);
                sb.append(c);
                sb.append("0000");
                sb.append(cArr, 2, 3);
                break;
            case '3':
                sb.append(cArr, 0, 3);
                sb.append("00000");
                sb.append(cArr, 3, 2);
                break;
            case '4':
                sb.append(cArr, 0, 4);
                sb.append("00000");
                sb.append(cArr[4]);
                break;
            default:
                sb.append(cArr, 0, 5);
                sb.append("0000");
                sb.append(c);
                break;
        }
        if (str.length() >= 8) {
            sb.append(str.charAt(7));
        }
        return sb.toString();
    }
}
