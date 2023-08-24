package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

@Deprecated
/* loaded from: classes3.dex */
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int r13 = height / 2;
        int r14 = width / 2;
        int max = Math.max(1, height / 256);
        int max2 = Math.max(1, width / 256);
        int r7 = -max;
        int r16 = r14 / 2;
        int y = ((int) findCornerFromCenter(r14, 0, 0, width, r13, r7, 0, height, r16).getY()) - 1;
        int r19 = r13 / 2;
        ResultPoint findCornerFromCenter = findCornerFromCenter(r14, -max2, 0, width, r13, 0, y, height, r19);
        int x = ((int) findCornerFromCenter.getX()) - 1;
        ResultPoint findCornerFromCenter2 = findCornerFromCenter(r14, max2, x, width, r13, 0, y, height, r19);
        int x2 = ((int) findCornerFromCenter2.getX()) + 1;
        ResultPoint findCornerFromCenter3 = findCornerFromCenter(r14, 0, x, x2, r13, max, y, height, r16);
        return new ResultPoint[]{findCornerFromCenter(r14, 0, x, x2, r13, r7, y, ((int) findCornerFromCenter3.getY()) + 1, r14 / 4), findCornerFromCenter, findCornerFromCenter2, findCornerFromCenter3};
    }

    private ResultPoint findCornerFromCenter(int r16, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24) throws NotFoundException {
        int[] blackWhiteRange;
        int[] r2 = null;
        int r11 = r16;
        int r10 = r20;
        while (r10 < r23 && r10 >= r22 && r11 < r19 && r11 >= r18) {
            if (r17 == 0) {
                blackWhiteRange = blackWhiteRange(r10, r24, r18, r19, true);
            } else {
                blackWhiteRange = blackWhiteRange(r11, r24, r22, r23, false);
            }
            if (blackWhiteRange == null) {
                if (r2 != null) {
                    if (r17 == 0) {
                        int r102 = r10 - r21;
                        if (r2[0] < r16) {
                            if (r2[1] > r16) {
                                return new ResultPoint(r2[r21 > 0 ? (char) 0 : (char) 1], r102);
                            }
                            return new ResultPoint(r2[0], r102);
                        }
                        return new ResultPoint(r2[1], r102);
                    }
                    int r112 = r11 - r17;
                    if (r2[0] < r20) {
                        if (r2[1] > r20) {
                            return new ResultPoint(r112, r2[r17 < 0 ? (char) 0 : (char) 1]);
                        }
                        return new ResultPoint(r112, r2[0]);
                    }
                    return new ResultPoint(r112, r2[1]);
                }
                throw NotFoundException.getNotFoundInstance();
            }
            r10 += r21;
            r11 += r17;
            r2 = blackWhiteRange;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0031 A[EDGE_INSN: B:70:0x0031->B:22:0x0031 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0067 A[EDGE_INSN: B:86:0x0067->B:47:0x0067 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] blackWhiteRange(int r6, int r7, int r8, int r9, boolean r10) {
        /*
            r5 = this;
            int r0 = r8 + r9
            r1 = 2
            int r0 = r0 / r1
            r2 = r0
        L5:
            if (r2 < r8) goto L3a
            com.google.zxing.common.BitMatrix r3 = r5.image
            if (r10 == 0) goto L12
            boolean r3 = r3.get(r2, r6)
            if (r3 == 0) goto L1b
            goto L18
        L12:
            boolean r3 = r3.get(r6, r2)
            if (r3 == 0) goto L1b
        L18:
            int r2 = r2 + (-1)
            goto L5
        L1b:
            r3 = r2
        L1c:
            int r3 = r3 + (-1)
            if (r3 < r8) goto L31
            com.google.zxing.common.BitMatrix r4 = r5.image
            if (r10 == 0) goto L2b
            boolean r4 = r4.get(r3, r6)
            if (r4 == 0) goto L1c
            goto L31
        L2b:
            boolean r4 = r4.get(r6, r3)
            if (r4 == 0) goto L1c
        L31:
            int r4 = r2 - r3
            if (r3 < r8) goto L3a
            if (r4 <= r7) goto L38
            goto L3a
        L38:
            r2 = r3
            goto L5
        L3a:
            r8 = 1
            int r2 = r2 + r8
        L3c:
            if (r0 >= r9) goto L70
            com.google.zxing.common.BitMatrix r3 = r5.image
            if (r10 == 0) goto L49
            boolean r3 = r3.get(r0, r6)
            if (r3 == 0) goto L52
            goto L4f
        L49:
            boolean r3 = r3.get(r6, r0)
            if (r3 == 0) goto L52
        L4f:
            int r0 = r0 + 1
            goto L3c
        L52:
            r3 = r0
        L53:
            int r3 = r3 + r8
            if (r3 >= r9) goto L67
            com.google.zxing.common.BitMatrix r4 = r5.image
            if (r10 == 0) goto L61
            boolean r4 = r4.get(r3, r6)
            if (r4 == 0) goto L53
            goto L67
        L61:
            boolean r4 = r4.get(r6, r3)
            if (r4 == 0) goto L53
        L67:
            int r4 = r3 - r0
            if (r3 >= r9) goto L70
            if (r4 <= r7) goto L6e
            goto L70
        L6e:
            r0 = r3
            goto L3c
        L70:
            int r0 = r0 + (-1)
            if (r0 <= r2) goto L7c
            int[] r6 = new int[r1]
            r7 = 0
            r6[r7] = r2
            r6[r8] = r0
            return r6
        L7c:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.detector.MonochromeRectangleDetector.blackWhiteRange(int, int, int, int, boolean):int[]");
    }
}
