package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes3.dex */
public final class ITFWriter extends OneDimensionalCodeWriter {

    /* renamed from: N */
    private static final int f1227N = 1;

    /* renamed from: W */
    private static final int f1228W = 3;
    private static final int[] START_PATTERN = {1, 1, 1, 1};
    private static final int[] END_PATTERN = {3, 1, 1};
    private static final int[][] PATTERNS = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int r4, int r5, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.ITF) {
            throw new IllegalArgumentException("Can only encode ITF, but got ".concat(String.valueOf(barcodeFormat)));
        }
        return super.encode(str, barcodeFormat, r4, r5, map);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length % 2 == 0) {
            if (length > 80) {
                throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got ".concat(String.valueOf(length)));
            }
            boolean[] zArr = new boolean[(length * 9) + 9];
            int appendPattern = appendPattern(zArr, 0, START_PATTERN, true);
            for (int r5 = 0; r5 < length; r5 += 2) {
                int digit = Character.digit(str.charAt(r5), 10);
                int digit2 = Character.digit(str.charAt(r5 + 1), 10);
                int[] r7 = new int[10];
                for (int r9 = 0; r9 < 5; r9++) {
                    int r10 = r9 * 2;
                    int[][] r11 = PATTERNS;
                    r7[r10] = r11[digit][r9];
                    r7[r10 + 1] = r11[digit2][r9];
                }
                appendPattern += appendPattern(zArr, appendPattern, r7, true);
            }
            appendPattern(zArr, appendPattern, END_PATTERN, true);
            return zArr;
        }
        throw new IllegalArgumentException("The length of the input should be even");
    }
}
