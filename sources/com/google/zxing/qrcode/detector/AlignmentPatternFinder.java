package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
final class AlignmentPatternFinder {
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;
    private final List<AlignmentPattern> possibleCenters = new ArrayList(5);
    private final int[] crossCheckStateCount = new int[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlignmentPatternFinder(BitMatrix bitMatrix, int r3, int r4, int r5, int r6, float f, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.startX = r3;
        this.startY = r4;
        this.width = r5;
        this.height = r6;
        this.moduleSize = f;
        this.resultPointCallback = resultPointCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlignmentPattern find() throws NotFoundException {
        AlignmentPattern handlePossibleCenter;
        AlignmentPattern handlePossibleCenter2;
        int r0 = this.startX;
        int r1 = this.height;
        int r2 = this.width + r0;
        int r3 = this.startY + (r1 / 2);
        int[] r4 = new int[3];
        for (int r6 = 0; r6 < r1; r6++) {
            int r7 = ((r6 & 1) == 0 ? (r6 + 1) / 2 : -((r6 + 1) / 2)) + r3;
            r4[0] = 0;
            r4[1] = 0;
            r4[2] = 0;
            int r10 = r0;
            while (r10 < r2 && !this.image.get(r10, r7)) {
                r10++;
            }
            int r11 = 0;
            while (r10 < r2) {
                if (!this.image.get(r10, r7)) {
                    if (r11 == 1) {
                        r11++;
                    }
                    r4[r11] = r4[r11] + 1;
                } else if (r11 == 1) {
                    r4[1] = r4[1] + 1;
                } else if (r11 == 2) {
                    if (foundPatternCross(r4) && (handlePossibleCenter2 = handlePossibleCenter(r4, r7, r10)) != null) {
                        return handlePossibleCenter2;
                    }
                    r4[0] = r4[2];
                    r4[1] = 1;
                    r4[2] = 0;
                    r11 = 1;
                } else {
                    r11++;
                    r4[r11] = r4[r11] + 1;
                }
                r10++;
            }
            if (foundPatternCross(r4) && (handlePossibleCenter = handlePossibleCenter(r4, r7, r2)) != null) {
                return handlePossibleCenter;
            }
        }
        if (!this.possibleCenters.isEmpty()) {
            return this.possibleCenters.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static float centerFromEnd(int[] r1, int r2) {
        return (r2 - r1[2]) - (r1[1] / 2.0f);
    }

    private boolean foundPatternCross(int[] r6) {
        float f = this.moduleSize;
        float f2 = f / 2.0f;
        for (int r3 = 0; r3 < 3; r3++) {
            if (Math.abs(f - r6[r3]) >= f2) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0062, code lost:
        if (r2[1] <= r12) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0065, code lost:
        if (r10 >= r1) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006b, code lost:
        if (r0.get(r11, r10) != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006f, code lost:
        if (r2[2] > r12) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0071, code lost:
        r2[2] = r2[2] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007b, code lost:
        if (r2[2] <= r12) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007d, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008f, code lost:
        if ((java.lang.Math.abs(((r2[0] + r2[1]) + r2[2]) - r13) * 5) < (r13 * 2)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0091, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0096, code lost:
        if (foundPatternCross(r2) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x009c, code lost:
        return centerFromEnd(r2, r10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckVertical(int r10, int r11, int r12, int r13) {
        /*
            r9 = this;
            com.google.zxing.common.BitMatrix r0 = r9.image
            int r1 = r0.getHeight()
            int[] r2 = r9.crossCheckStateCount
            r3 = 0
            r2[r3] = r3
            r4 = 1
            r2[r4] = r3
            r5 = 2
            r2[r5] = r3
            r6 = r10
        L12:
            if (r6 < 0) goto L26
            boolean r7 = r0.get(r11, r6)
            if (r7 == 0) goto L26
            r7 = r2[r4]
            if (r7 > r12) goto L26
            r7 = r2[r4]
            int r7 = r7 + r4
            r2[r4] = r7
            int r6 = r6 + (-1)
            goto L12
        L26:
            r7 = 2143289344(0x7fc00000, float:NaN)
            if (r6 < 0) goto L9d
            r8 = r2[r4]
            if (r8 <= r12) goto L30
            goto L9d
        L30:
            if (r6 < 0) goto L44
            boolean r8 = r0.get(r11, r6)
            if (r8 != 0) goto L44
            r8 = r2[r3]
            if (r8 > r12) goto L44
            r8 = r2[r3]
            int r8 = r8 + r4
            r2[r3] = r8
            int r6 = r6 + (-1)
            goto L30
        L44:
            r6 = r2[r3]
            if (r6 <= r12) goto L49
            return r7
        L49:
            int r10 = r10 + r4
        L4a:
            if (r10 >= r1) goto L5e
            boolean r6 = r0.get(r11, r10)
            if (r6 == 0) goto L5e
            r6 = r2[r4]
            if (r6 > r12) goto L5e
            r6 = r2[r4]
            int r6 = r6 + r4
            r2[r4] = r6
            int r10 = r10 + 1
            goto L4a
        L5e:
            if (r10 == r1) goto L9d
            r6 = r2[r4]
            if (r6 <= r12) goto L65
            goto L9d
        L65:
            if (r10 >= r1) goto L79
            boolean r6 = r0.get(r11, r10)
            if (r6 != 0) goto L79
            r6 = r2[r5]
            if (r6 > r12) goto L79
            r6 = r2[r5]
            int r6 = r6 + r4
            r2[r5] = r6
            int r10 = r10 + 1
            goto L65
        L79:
            r11 = r2[r5]
            if (r11 <= r12) goto L7e
            return r7
        L7e:
            r11 = r2[r3]
            r12 = r2[r4]
            int r11 = r11 + r12
            r12 = r2[r5]
            int r11 = r11 + r12
            int r11 = r11 - r13
            int r11 = java.lang.Math.abs(r11)
            int r11 = r11 * 5
            int r13 = r13 * 2
            if (r11 < r13) goto L92
            return r7
        L92:
            boolean r11 = r9.foundPatternCross(r2)
            if (r11 == 0) goto L9d
            float r10 = centerFromEnd(r2, r10)
            return r10
        L9d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.AlignmentPatternFinder.crossCheckVertical(int, int, int, int):float");
    }

    private AlignmentPattern handlePossibleCenter(int[] r7, int r8, int r9) {
        int r1 = r7[0] + r7[1] + r7[2];
        float centerFromEnd = centerFromEnd(r7, r9);
        float crossCheckVertical = crossCheckVertical(r8, (int) centerFromEnd, r7[1] * 2, r1);
        if (Float.isNaN(crossCheckVertical)) {
            return null;
        }
        float f = ((r7[0] + r7[1]) + r7[2]) / 3.0f;
        for (AlignmentPattern alignmentPattern : this.possibleCenters) {
            if (alignmentPattern.aboutEquals(f, crossCheckVertical, centerFromEnd)) {
                return alignmentPattern.combineEstimate(crossCheckVertical, centerFromEnd, f);
            }
        }
        AlignmentPattern alignmentPattern2 = new AlignmentPattern(centerFromEnd, crossCheckVertical, f);
        this.possibleCenters.add(alignmentPattern2);
        ResultPointCallback resultPointCallback = this.resultPointCallback;
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(alignmentPattern2);
            return null;
        }
        return null;
    }
}
