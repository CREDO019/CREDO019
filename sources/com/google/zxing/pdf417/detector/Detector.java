package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] INDEXES_START_PATTERN = {0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    public static PDF417DetectorResult detect(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        BitMatrix blackMatrix = binaryBitmap.getBlackMatrix();
        List<ResultPoint[]> detect = detect(z, blackMatrix);
        if (detect.isEmpty()) {
            blackMatrix = blackMatrix.m1519clone();
            blackMatrix.rotate180();
            detect = detect(z, blackMatrix);
        }
        return new PDF417DetectorResult(blackMatrix, detect);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r5 == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001f, code lost:
        r4 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
        if (r4.hasNext() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
        r5 = (com.google.zxing.ResultPoint[]) r4.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
        if (r5[1] == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
        r3 = (int) java.lang.Math.max(r3, r5[1].getY());
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (r5[3] == null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0043, code lost:
        r3 = java.lang.Math.max(r3, (int) r5[3].getY());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<com.google.zxing.ResultPoint[]> detect(boolean r8, com.google.zxing.common.BitMatrix r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 1
            r2 = 0
            r3 = 0
        L8:
            r4 = 0
            r5 = 0
        La:
            int r6 = r9.getHeight()
            if (r3 >= r6) goto L7c
            com.google.zxing.ResultPoint[] r4 = findVertices(r9, r3, r4)
            r6 = r4[r2]
            if (r6 != 0) goto L52
            r6 = 3
            r7 = r4[r6]
            if (r7 != 0) goto L52
            if (r5 == 0) goto L7c
            java.util.Iterator r4 = r0.iterator()
        L23:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L4f
            java.lang.Object r5 = r4.next()
            com.google.zxing.ResultPoint[] r5 = (com.google.zxing.ResultPoint[]) r5
            r7 = r5[r1]
            if (r7 == 0) goto L3f
            float r3 = (float) r3
            r7 = r5[r1]
            float r7 = r7.getY()
            float r3 = java.lang.Math.max(r3, r7)
            int r3 = (int) r3
        L3f:
            r7 = r5[r6]
            if (r7 == 0) goto L23
            r5 = r5[r6]
            float r5 = r5.getY()
            int r5 = (int) r5
            int r3 = java.lang.Math.max(r3, r5)
            goto L23
        L4f:
            int r3 = r3 + 5
            goto L8
        L52:
            r0.add(r4)
            if (r8 == 0) goto L7c
            r3 = 2
            r5 = r4[r3]
            if (r5 == 0) goto L6a
            r5 = r4[r3]
            float r5 = r5.getX()
            int r5 = (int) r5
            r3 = r4[r3]
            float r3 = r3.getY()
            goto L78
        L6a:
            r3 = 4
            r5 = r4[r3]
            float r5 = r5.getX()
            int r5 = (int) r5
            r3 = r4[r3]
            float r3 = r3.getY()
        L78:
            int r3 = (int) r3
            r4 = r5
            r5 = 1
            goto La
        L7c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.detector.Detector.detect(boolean, com.google.zxing.common.BitMatrix):java.util.List");
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix, int r10, int r11) {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, r10, r11, START_PATTERN), INDEXES_START_PATTERN);
        if (resultPointArr[4] != null) {
            r11 = (int) resultPointArr[4].getX();
            r10 = (int) resultPointArr[4].getY();
        }
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, r10, r11, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return resultPointArr;
    }

    private static void copyToResult(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] r5) {
        for (int r0 = 0; r0 < r5.length; r0++) {
            resultPointArr[r5[r0]] = resultPointArr2[r0];
        }
    }

    private static ResultPoint[] findRowsWithPattern(BitMatrix bitMatrix, int r19, int r20, int r21, int r22, int[] r23) {
        boolean z;
        int r14;
        int r16;
        int r142;
        ResultPoint[] resultPointArr = new ResultPoint[4];
        int[] r10 = new int[r23.length];
        int r11 = r21;
        while (true) {
            if (r11 >= r19) {
                z = false;
                break;
            }
            int[] findGuardPattern = findGuardPattern(bitMatrix, r22, r11, r20, false, r23, r10);
            if (findGuardPattern != null) {
                int r17 = r11;
                int[] r112 = findGuardPattern;
                int r2 = r17;
                while (true) {
                    if (r2 <= 0) {
                        r142 = r2;
                        break;
                    }
                    int r143 = r2 - 1;
                    int[] findGuardPattern2 = findGuardPattern(bitMatrix, r22, r143, r20, false, r23, r10);
                    if (findGuardPattern2 == null) {
                        r142 = r143 + 1;
                        break;
                    }
                    r112 = findGuardPattern2;
                    r2 = r143;
                }
                float f = r142;
                resultPointArr[0] = new ResultPoint(r112[0], f);
                resultPointArr[1] = new ResultPoint(r112[1], f);
                r11 = r142;
                z = true;
            } else {
                r11 += 5;
            }
        }
        int r3 = r11 + 1;
        if (z) {
            int[] r15 = {(int) resultPointArr[0].getX(), (int) resultPointArr[1].getX()};
            int r8 = r3;
            int r7 = 0;
            while (true) {
                if (r8 >= r19) {
                    r14 = r7;
                    r16 = r8;
                    break;
                }
                r14 = r7;
                r16 = r8;
                int[] findGuardPattern3 = findGuardPattern(bitMatrix, r15[0], r8, r20, false, r23, r10);
                if (findGuardPattern3 != null && Math.abs(r15[0] - findGuardPattern3[0]) < 5 && Math.abs(r15[1] - findGuardPattern3[1]) < 5) {
                    r15 = findGuardPattern3;
                    r7 = 0;
                } else if (r14 > 25) {
                    break;
                } else {
                    r7 = r14 + 1;
                }
                r8 = r16 + 1;
            }
            r3 = r16 - (r14 + 1);
            float f2 = r3;
            resultPointArr[2] = new ResultPoint(r15[0], f2);
            resultPointArr[3] = new ResultPoint(r15[1], f2);
        }
        if (r3 - r11 < 10) {
            Arrays.fill(resultPointArr, (Object) null);
        }
        return resultPointArr;
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int r10, int r11, int r12, boolean z, int[] r14, int[] r15) {
        Arrays.fill(r15, 0, r15.length, 0);
        int r0 = 0;
        while (bitMatrix.get(r10, r11) && r10 > 0) {
            int r2 = r0 + 1;
            if (r0 >= 3) {
                break;
            }
            r10--;
            r0 = r2;
        }
        int length = r14.length;
        boolean z2 = z;
        int r3 = 0;
        int r13 = r10;
        while (r10 < r12) {
            if (bitMatrix.get(r10, r11) != z2) {
                r15[r3] = r15[r3] + 1;
            } else {
                if (r3 != length - 1) {
                    r3++;
                } else if (patternMatchVariance(r15, r14, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                    return new int[]{r13, r10};
                } else {
                    r13 += r15[0] + r15[1];
                    int r4 = r3 - 1;
                    System.arraycopy(r15, 2, r15, 0, r4);
                    r15[r4] = 0;
                    r15[r3] = 0;
                    r3--;
                }
                r15[r3] = 1;
                z2 = !z2;
            }
            r10++;
        }
        if (r3 != length - 1 || patternMatchVariance(r15, r14, MAX_INDIVIDUAL_VARIANCE) >= MAX_AVG_VARIANCE) {
            return null;
        }
        return new int[]{r13, r10 - 1};
    }

    private static float patternMatchVariance(int[] r9, int[] r10, float f) {
        int length = r9.length;
        int r3 = 0;
        int r4 = 0;
        for (int r2 = 0; r2 < length; r2++) {
            r3 += r9[r2];
            r4 += r10[r2];
        }
        if (r3 < r4) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = r3;
        float f3 = f2 / r4;
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int r1 = 0; r1 < length; r1++) {
            float f6 = r10[r1] * f3;
            float f7 = r9[r1];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }
}
