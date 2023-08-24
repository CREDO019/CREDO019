package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

/* loaded from: classes3.dex */
public final class ITFReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.38f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.5f;

    /* renamed from: N */
    private static final int f1224N = 1;

    /* renamed from: W */
    private static final int f1225W = 3;

    /* renamed from: w */
    private static final int f1226w = 2;
    private int narrowLineWidth = -1;
    private static final int[] DEFAULT_ALLOWED_LENGTHS = {6, 8, 10, 12, 14};
    private static final int[] START_PATTERN = {1, 1, 1, 1};
    private static final int[][] END_PATTERN_REVERSED = {new int[]{1, 1, 2}, new int[]{1, 1, 3}};
    private static final int[][] PATTERNS = {new int[]{1, 1, 2, 2, 1}, new int[]{2, 1, 1, 1, 2}, new int[]{1, 2, 1, 1, 2}, new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 2, 1, 2}, new int[]{2, 1, 2, 1, 1}, new int[]{1, 2, 2, 1, 1}, new int[]{1, 1, 1, 2, 2}, new int[]{2, 1, 1, 2, 1}, new int[]{1, 2, 1, 2, 1}, new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int r11, BitArray bitArray, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        boolean z;
        int[] decodeStart = decodeStart(bitArray);
        int[] decodeEnd = decodeEnd(bitArray);
        StringBuilder sb = new StringBuilder(20);
        decodeMiddle(bitArray, decodeStart[1], decodeEnd[0], sb);
        String sb2 = sb.toString();
        int[] r13 = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (r13 == null) {
            r13 = DEFAULT_ALLOWED_LENGTHS;
        }
        int length = sb2.length();
        int length2 = r13.length;
        int r7 = 0;
        int r8 = 0;
        while (true) {
            if (r7 >= length2) {
                z = false;
                break;
            }
            int r9 = r13[r7];
            if (length == r9) {
                z = true;
                break;
            }
            if (r9 > r8) {
                r8 = r9;
            }
            r7++;
        }
        if (!z && length > r8) {
            z = true;
        }
        if (!z) {
            throw FormatException.getFormatInstance();
        }
        float f = r11;
        return new Result(sb2, null, new ResultPoint[]{new ResultPoint(decodeStart[1], f), new ResultPoint(decodeEnd[0], f)}, BarcodeFormat.ITF);
    }

    private static void decodeMiddle(BitArray bitArray, int r10, int r11, StringBuilder sb) throws NotFoundException {
        int[] r1 = new int[10];
        int[] r3 = new int[5];
        int[] r4 = new int[5];
        while (r10 < r11) {
            recordPattern(bitArray, r10, r1);
            for (int r6 = 0; r6 < 5; r6++) {
                int r7 = r6 * 2;
                r3[r6] = r1[r7];
                r4[r6] = r1[r7 + 1];
            }
            sb.append((char) (decodeDigit(r3) + 48));
            sb.append((char) (decodeDigit(r4) + 48));
            for (int r5 = 0; r5 < 10; r5++) {
                r10 += r1[r5];
            }
        }
    }

    private int[] decodeStart(BitArray bitArray) throws NotFoundException {
        int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), START_PATTERN);
        this.narrowLineWidth = (findGuardPattern[1] - findGuardPattern[0]) / 4;
        validateQuietZone(bitArray, findGuardPattern[0]);
        return findGuardPattern;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001b, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void validateQuietZone(com.google.zxing.common.BitArray r3, int r4) throws com.google.zxing.NotFoundException {
        /*
            r2 = this;
            int r0 = r2.narrowLineWidth
            int r0 = r0 * 10
            if (r0 >= r4) goto L7
            goto L8
        L7:
            r0 = r4
        L8:
            int r4 = r4 + (-1)
        La:
            if (r0 <= 0) goto L19
            if (r4 < 0) goto L19
            boolean r1 = r3.get(r4)
            if (r1 != 0) goto L19
            int r0 = r0 + (-1)
            int r4 = r4 + (-1)
            goto La
        L19:
            if (r0 != 0) goto L1c
            return
        L1c:
            com.google.zxing.NotFoundException r3 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.ITFReader.validateQuietZone(com.google.zxing.common.BitArray, int):void");
    }

    private static int skipWhiteSpace(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        if (nextSet != size) {
            return nextSet;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] decodeEnd(BitArray bitArray) throws NotFoundException {
        int r0;
        int[] findGuardPattern;
        bitArray.reverse();
        try {
            try {
                findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), END_PATTERN_REVERSED[0]);
            } catch (NotFoundException unused) {
                findGuardPattern = findGuardPattern(bitArray, r0, END_PATTERN_REVERSED[1]);
            }
            validateQuietZone(bitArray, findGuardPattern[0]);
            int r3 = findGuardPattern[0];
            findGuardPattern[0] = bitArray.getSize() - findGuardPattern[1];
            findGuardPattern[1] = bitArray.getSize() - r3;
            return findGuardPattern;
        } finally {
            bitArray.reverse();
        }
    }

    private static int[] findGuardPattern(BitArray bitArray, int r12, int[] r13) throws NotFoundException {
        int length = r13.length;
        int[] r1 = new int[length];
        int size = bitArray.getSize();
        int r4 = r12;
        boolean z = false;
        int r6 = 0;
        while (r12 < size) {
            if (bitArray.get(r12) != z) {
                r1[r6] = r1[r6] + 1;
            } else {
                if (r6 != length - 1) {
                    r6++;
                } else if (patternMatchVariance(r1, r13, MAX_INDIVIDUAL_VARIANCE) < 0.38f) {
                    return new int[]{r4, r12};
                } else {
                    r4 += r1[0] + r1[1];
                    int r7 = r6 - 1;
                    System.arraycopy(r1, 2, r1, 0, r7);
                    r1[r7] = 0;
                    r1[r6] = 0;
                    r6--;
                }
                r1[r6] = 1;
                z = !z;
            }
            r12++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeDigit(int[] r7) throws NotFoundException {
        int length = PATTERNS.length;
        float f = 0.38f;
        int r4 = -1;
        for (int r3 = 0; r3 < length; r3++) {
            float patternMatchVariance = patternMatchVariance(r7, PATTERNS[r3], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < f) {
                r4 = r3;
                f = patternMatchVariance;
            } else if (patternMatchVariance == f) {
                r4 = -1;
            }
        }
        if (r4 >= 0) {
            return r4 % 10;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
