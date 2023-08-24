package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes3.dex */
public class Code93Writer extends OneDimensionalCodeWriter {
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r4, int r5, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.CODE_93) {
            throw new IllegalArgumentException("Can only encode CODE_93, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return super.encode(str, barcodeFormat, r4, r5, map);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
        }
        int[] r2 = new int[9];
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], r2);
        boolean[] zArr = new boolean[((str.length() + 2 + 2) * 9) + 1];
        int appendPattern = appendPattern(zArr, 0, r2);
        for (int r4 = 0; r4 < length; r4++) {
            toIntArray(Code93Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(r4))], r2);
            appendPattern += appendPattern(zArr, appendPattern, r2);
        }
        int computeChecksumIndex = computeChecksumIndex(str, 20);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[computeChecksumIndex], r2);
        int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, r2);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[computeChecksumIndex(str + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(computeChecksumIndex), 15)], r2);
        int appendPattern3 = appendPattern2 + appendPattern(zArr, appendPattern2, r2);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], r2);
        zArr[appendPattern3 + appendPattern(zArr, appendPattern3, r2)] = true;
        return zArr;
    }

    private static void toIntArray(int r4, int[] r5) {
        for (int r1 = 0; r1 < 9; r1++) {
            int r3 = 1;
            if (((1 << (8 - r1)) & r4) == 0) {
                r3 = 0;
            }
            r5[r1] = r3;
        }
    }

    @Deprecated
    protected static int appendPattern(boolean[] zArr, int r1, int[] r2, boolean z) {
        return appendPattern(zArr, r1, r2);
    }

    private static int appendPattern(boolean[] zArr, int r6, int[] r7) {
        int length = r7.length;
        int r2 = 0;
        while (r2 < length) {
            int r4 = r6 + 1;
            zArr[r6] = r7[r2] != 0;
            r2++;
            r6 = r4;
        }
        return 9;
    }

    private static int computeChecksumIndex(String str, int r7) {
        int r2 = 0;
        int r3 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            r2 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * r3;
            r3++;
            if (r3 > r7) {
                r3 = 1;
            }
        }
        return r2 % 47;
    }
}
