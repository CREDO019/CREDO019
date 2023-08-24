package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes3.dex */
public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int r5, int r6, int r7) throws NotFoundException {
        this.image = bitMatrix;
        int height = bitMatrix.getHeight();
        this.height = height;
        int width = bitMatrix.getWidth();
        this.width = width;
        int r52 = r5 / 2;
        int r1 = r6 - r52;
        this.leftInit = r1;
        int r62 = r6 + r52;
        this.rightInit = r62;
        int r2 = r7 - r52;
        this.upInit = r2;
        int r72 = r7 + r52;
        this.downInit = r72;
        if (r2 < 0 || r1 < 0 || r72 >= height || r62 >= width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public ResultPoint[] detect() throws NotFoundException {
        int r0 = this.leftInit;
        int r1 = this.rightInit;
        int r2 = this.upInit;
        int r3 = this.downInit;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (z2) {
            boolean z8 = true;
            boolean z9 = false;
            while (true) {
                if ((z8 || !z3) && r1 < this.width) {
                    z8 = containsBlackPoint(r2, r3, r1, false);
                    if (z8) {
                        r1++;
                        z3 = true;
                        z9 = true;
                    } else if (!z3) {
                        r1++;
                    }
                }
            }
            if (r1 < this.width) {
                boolean z10 = true;
                while (true) {
                    if ((z10 || !z4) && r3 < this.height) {
                        z10 = containsBlackPoint(r0, r1, r3, true);
                        if (z10) {
                            r3++;
                            z4 = true;
                            z9 = true;
                        } else if (!z4) {
                            r3++;
                        }
                    }
                }
                if (r3 < this.height) {
                    boolean z11 = true;
                    while (true) {
                        if ((z11 || !z5) && r0 >= 0) {
                            z11 = containsBlackPoint(r2, r3, r0, false);
                            if (z11) {
                                r0--;
                                z5 = true;
                                z9 = true;
                            } else if (!z5) {
                                r0--;
                            }
                        }
                    }
                    if (r0 >= 0) {
                        z2 = z9;
                        boolean z12 = true;
                        while (true) {
                            if ((z12 || !z7) && r2 >= 0) {
                                z12 = containsBlackPoint(r0, r1, r2, true);
                                if (z12) {
                                    r2--;
                                    z2 = true;
                                    z7 = true;
                                } else if (!z7) {
                                    r2--;
                                }
                            }
                        }
                        if (r2 >= 0) {
                            if (z2) {
                                z6 = true;
                            }
                        }
                    }
                }
            }
            z = true;
            break;
        }
        if (z || !z6) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r4 = r1 - r0;
        ResultPoint resultPoint = null;
        ResultPoint resultPoint2 = null;
        for (int r8 = 1; resultPoint2 == null && r8 < r4; r8++) {
            resultPoint2 = getBlackPointOnSegment(r0, r3 - r8, r0 + r8, r3);
        }
        if (resultPoint2 != null) {
            ResultPoint resultPoint3 = null;
            for (int r9 = 1; resultPoint3 == null && r9 < r4; r9++) {
                resultPoint3 = getBlackPointOnSegment(r0, r2 + r9, r0 + r9, r2);
            }
            if (resultPoint3 != null) {
                ResultPoint resultPoint4 = null;
                for (int r92 = 1; resultPoint4 == null && r92 < r4; r92++) {
                    resultPoint4 = getBlackPointOnSegment(r1, r2 + r92, r1 - r92, r2);
                }
                if (resultPoint4 != null) {
                    for (int r5 = 1; resultPoint == null && r5 < r4; r5++) {
                        resultPoint = getBlackPointOnSegment(r1, r3 - r5, r1 - r5, r3);
                    }
                    if (resultPoint == null) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    return centerEdges(resultPoint, resultPoint2, resultPoint4, resultPoint3);
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private ResultPoint getBlackPointOnSegment(float f, float f2, float f3, float f4) {
        int round = MathUtils.round(MathUtils.distance(f, f2, f3, f4));
        float f5 = round;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        for (int r1 = 0; r1 < round; r1++) {
            float f8 = r1;
            int round2 = MathUtils.round((f8 * f6) + f);
            int round3 = MathUtils.round((f8 * f7) + f2);
            if (this.image.get(round2, round3)) {
                return new ResultPoint(round2, round3);
            }
        }
        return null;
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = resultPoint2.getX();
        float y2 = resultPoint2.getY();
        float x3 = resultPoint3.getX();
        float y3 = resultPoint3.getY();
        float x4 = resultPoint4.getX();
        float y4 = resultPoint4.getY();
        return x < ((float) this.width) / 2.0f ? new ResultPoint[]{new ResultPoint(x4 - 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 + 1.0f), new ResultPoint(x3 - 1.0f, y3 - 1.0f), new ResultPoint(x + 1.0f, y - 1.0f)} : new ResultPoint[]{new ResultPoint(x4 + 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 - 1.0f), new ResultPoint(x3 - 1.0f, y3 + 1.0f), new ResultPoint(x - 1.0f, y - 1.0f)};
    }

    private boolean containsBlackPoint(int r2, int r3, int r4, boolean z) {
        if (z) {
            while (r2 <= r3) {
                if (this.image.get(r2, r4)) {
                    return true;
                }
                r2++;
            }
            return false;
        }
        while (r2 <= r3) {
            if (this.image.get(r4, r2)) {
                return true;
            }
            r2++;
        }
        return false;
    }
}
