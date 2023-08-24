package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public final class Detector {
    private static final int[] EXPECTED_CORNER_BITS = {3808, 476, 2107, 1799};
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    public AztecDetectorResult detect(boolean z) throws NotFoundException {
        ResultPoint[] bullsEyeCorners = getBullsEyeCorners(getMatrixCenter());
        if (z) {
            ResultPoint resultPoint = bullsEyeCorners[0];
            bullsEyeCorners[0] = bullsEyeCorners[2];
            bullsEyeCorners[2] = resultPoint;
        }
        extractParameters(bullsEyeCorners);
        BitMatrix bitMatrix = this.image;
        int r0 = this.shift;
        return new AztecDetectorResult(sampleGrid(bitMatrix, bullsEyeCorners[r0 % 4], bullsEyeCorners[(r0 + 1) % 4], bullsEyeCorners[(r0 + 2) % 4], bullsEyeCorners[(r0 + 3) % 4]), getMatrixCornerPoints(bullsEyeCorners), this.compact, this.nbDataBlocks, this.nbLayers);
    }

    private void extractParameters(ResultPoint[] resultPointArr) throws NotFoundException {
        long j;
        long j2;
        if (!isValid(resultPointArr[0]) || !isValid(resultPointArr[1]) || !isValid(resultPointArr[2]) || !isValid(resultPointArr[3])) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r4 = this.nbCenterLayers * 2;
        int[] r6 = {sampleLine(resultPointArr[0], resultPointArr[1], r4), sampleLine(resultPointArr[1], resultPointArr[2], r4), sampleLine(resultPointArr[2], resultPointArr[3], r4), sampleLine(resultPointArr[3], resultPointArr[0], r4)};
        this.shift = getRotation(r6, r4);
        long j3 = 0;
        for (int r0 = 0; r0 < 4; r0++) {
            int r10 = r6[(this.shift + r0) % 4];
            if (this.compact) {
                j = j3 << 7;
                j2 = (r10 >> 1) & 127;
            } else {
                j = j3 << 10;
                j2 = ((r10 >> 2) & 992) + ((r10 >> 1) & 31);
            }
            j3 = j + j2;
        }
        int correctedParameterData = getCorrectedParameterData(j3, this.compact);
        if (this.compact) {
            this.nbLayers = (correctedParameterData >> 6) + 1;
            this.nbDataBlocks = (correctedParameterData & 63) + 1;
            return;
        }
        this.nbLayers = (correctedParameterData >> 11) + 1;
        this.nbDataBlocks = (correctedParameterData & 2047) + 1;
    }

    private static int getRotation(int[] r6, int r7) throws NotFoundException {
        int r3 = 0;
        for (int r5 : r6) {
            r3 = (r3 << 3) + ((r5 >> (r7 - 2)) << 1) + (r5 & 1);
        }
        int r62 = ((r3 & 1) << 11) + (r3 >> 1);
        for (int r1 = 0; r1 < 4; r1++) {
            if (Integer.bitCount(EXPECTED_CORNER_BITS[r1] ^ r62) <= 2) {
                return r1;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getCorrectedParameterData(long j, boolean z) throws NotFoundException {
        int r7;
        int r1;
        if (z) {
            r7 = 7;
            r1 = 2;
        } else {
            r7 = 10;
            r1 = 4;
        }
        int r2 = r7 - r1;
        int[] r3 = new int[r7];
        for (int r72 = r7 - 1; r72 >= 0; r72--) {
            r3[r72] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decode(r3, r2);
            int r6 = 0;
            for (int r5 = 0; r5 < r1; r5++) {
                r6 = (r6 << 4) + r3[r5];
            }
            return r6;
        } catch (ReedSolomonException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private ResultPoint[] getBullsEyeCorners(Point point) throws NotFoundException {
        this.nbCenterLayers = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        Point point5 = point4;
        boolean z = true;
        while (this.nbCenterLayers < 9) {
            Point firstDifferent = getFirstDifferent(point2, z, 1, -1);
            Point firstDifferent2 = getFirstDifferent(point3, z, 1, 1);
            Point firstDifferent3 = getFirstDifferent(point4, z, -1, 1);
            Point firstDifferent4 = getFirstDifferent(point5, z, -1, -1);
            if (this.nbCenterLayers > 2) {
                double distance = (distance(firstDifferent4, firstDifferent) * this.nbCenterLayers) / (distance(point5, point2) * (this.nbCenterLayers + 2));
                if (distance < 0.75d || distance > 1.25d || !isWhiteOrBlackRectangle(firstDifferent, firstDifferent2, firstDifferent3, firstDifferent4)) {
                    break;
                }
            }
            z = !z;
            this.nbCenterLayers++;
            point5 = firstDifferent4;
            point2 = firstDifferent;
            point3 = firstDifferent2;
            point4 = firstDifferent3;
        }
        int r6 = this.nbCenterLayers;
        if (r6 != 5 && r6 != 7) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.compact = r6 == 5;
        ResultPoint[] resultPointArr = {new ResultPoint(point2.getX() + 0.5f, point2.getY() - 0.5f), new ResultPoint(point3.getX() + 0.5f, point3.getY() + 0.5f), new ResultPoint(point4.getX() - 0.5f, point4.getY() + 0.5f), new ResultPoint(point5.getX() - 0.5f, point5.getY() - 0.5f)};
        int r2 = this.nbCenterLayers;
        return expandSquare(resultPointArr, (r2 * 2) - 3, r2 * 2);
    }

    private Point getMatrixCenter() {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint resultPoint4;
        ResultPoint resultPoint5;
        ResultPoint resultPoint6;
        ResultPoint resultPoint7;
        ResultPoint resultPoint8;
        try {
            ResultPoint[] detect = new WhiteRectangleDetector(this.image).detect();
            resultPoint3 = detect[0];
            resultPoint4 = detect[1];
            resultPoint2 = detect[2];
            resultPoint = detect[3];
        } catch (NotFoundException unused) {
            int width = this.image.getWidth() / 2;
            int height = this.image.getHeight() / 2;
            int r8 = width + 7;
            int r9 = height - 7;
            ResultPoint resultPoint9 = getFirstDifferent(new Point(r8, r9), false, 1, -1).toResultPoint();
            int r6 = height + 7;
            ResultPoint resultPoint10 = getFirstDifferent(new Point(r8, r6), false, 1, 1).toResultPoint();
            int r5 = width - 7;
            ResultPoint resultPoint11 = getFirstDifferent(new Point(r5, r6), false, -1, 1).toResultPoint();
            resultPoint = getFirstDifferent(new Point(r5, r9), false, -1, -1).toResultPoint();
            resultPoint2 = resultPoint11;
            resultPoint3 = resultPoint9;
            resultPoint4 = resultPoint10;
        }
        int round = MathUtils.round((((resultPoint3.getX() + resultPoint.getX()) + resultPoint4.getX()) + resultPoint2.getX()) / 4.0f);
        int round2 = MathUtils.round((((resultPoint3.getY() + resultPoint.getY()) + resultPoint4.getY()) + resultPoint2.getY()) / 4.0f);
        try {
            ResultPoint[] detect2 = new WhiteRectangleDetector(this.image, 15, round, round2).detect();
            resultPoint5 = detect2[0];
            resultPoint6 = detect2[1];
            resultPoint7 = detect2[2];
            resultPoint8 = detect2[3];
        } catch (NotFoundException unused2) {
            int r1 = round + 7;
            int r62 = round2 - 7;
            resultPoint5 = getFirstDifferent(new Point(r1, r62), false, 1, -1).toResultPoint();
            int r52 = round2 + 7;
            resultPoint6 = getFirstDifferent(new Point(r1, r52), false, 1, 1).toResultPoint();
            int r92 = round - 7;
            resultPoint7 = getFirstDifferent(new Point(r92, r52), false, -1, 1).toResultPoint();
            resultPoint8 = getFirstDifferent(new Point(r92, r62), false, -1, -1).toResultPoint();
        }
        return new Point(MathUtils.round((((resultPoint5.getX() + resultPoint8.getX()) + resultPoint6.getX()) + resultPoint7.getX()) / 4.0f), MathUtils.round((((resultPoint5.getY() + resultPoint8.getY()) + resultPoint6.getY()) + resultPoint7.getY()) / 4.0f));
    }

    private ResultPoint[] getMatrixCornerPoints(ResultPoint[] resultPointArr) {
        return expandSquare(resultPointArr, this.nbCenterLayers * 2, getDimension());
    }

    private BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        GridSampler gridSampler = GridSampler.getInstance();
        int dimension = getDimension();
        float f = dimension / 2.0f;
        int r8 = this.nbCenterLayers;
        float f2 = f - r8;
        float f3 = f + r8;
        return gridSampler.sampleGrid(bitMatrix, dimension, dimension, f2, f2, f3, f2, f3, f3, f2, f3, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY());
    }

    private int sampleLine(ResultPoint resultPoint, ResultPoint resultPoint2, int r10) {
        float distance = distance(resultPoint, resultPoint2);
        float f = distance / r10;
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = ((resultPoint2.getX() - resultPoint.getX()) * f) / distance;
        float y2 = (f * (resultPoint2.getY() - resultPoint.getY())) / distance;
        int r9 = 0;
        for (int r8 = 0; r8 < r10; r8++) {
            float f2 = r8;
            if (this.image.get(MathUtils.round((f2 * x2) + x), MathUtils.round((f2 * y2) + y))) {
                r9 |= 1 << ((r10 - r8) - 1);
            }
        }
        return r9;
    }

    private boolean isWhiteOrBlackRectangle(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(point.getX() - 3, point.getY() + 3);
        Point point6 = new Point(point2.getX() - 3, point2.getY() - 3);
        Point point7 = new Point(point3.getX() + 3, point3.getY() - 3);
        Point point8 = new Point(point4.getX() + 3, point4.getY() + 3);
        int color = getColor(point8, point5);
        return color != 0 && getColor(point5, point6) == color && getColor(point6, point7) == color && getColor(point7, point8) == color;
    }

    private int getColor(Point point, Point point2) {
        float distance = distance(point, point2);
        float x = (point2.getX() - point.getX()) / distance;
        float y = (point2.getY() - point.getY()) / distance;
        float x2 = point.getX();
        float y2 = point.getY();
        boolean z = this.image.get(point.getX(), point.getY());
        int ceil = (int) Math.ceil(distance);
        int r7 = 0;
        for (int r6 = 0; r6 < ceil; r6++) {
            x2 += x;
            y2 += y;
            if (this.image.get(MathUtils.round(x2), MathUtils.round(y2)) != z) {
                r7++;
            }
        }
        float f = r7 / distance;
        if (f <= 0.1f || f >= 0.9f) {
            return (f <= 0.1f) == z ? 1 : -1;
        }
        return 0;
    }

    private Point getFirstDifferent(Point point, boolean z, int r5, int r6) {
        int x = point.getX() + r5;
        int y = point.getY();
        while (true) {
            y += r6;
            if (!isValid(x, y) || this.image.get(x, y) != z) {
                break;
            }
            x += r5;
        }
        int r0 = x - r5;
        int r3 = y - r6;
        while (isValid(r0, r3) && this.image.get(r0, r3) == z) {
            r0 += r5;
        }
        int r02 = r0 - r5;
        while (isValid(r02, r3) && this.image.get(r02, r3) == z) {
            r3 += r6;
        }
        return new Point(r02, r3 - r6);
    }

    private static ResultPoint[] expandSquare(ResultPoint[] resultPointArr, int r11, int r12) {
        float f = r12 / (r11 * 2.0f);
        float x = resultPointArr[0].getX() - resultPointArr[2].getX();
        float y = resultPointArr[0].getY() - resultPointArr[2].getY();
        float x2 = (resultPointArr[0].getX() + resultPointArr[2].getX()) / 2.0f;
        float y2 = (resultPointArr[0].getY() + resultPointArr[2].getY()) / 2.0f;
        float f2 = x * f;
        float f3 = y * f;
        ResultPoint resultPoint = new ResultPoint(x2 + f2, y2 + f3);
        ResultPoint resultPoint2 = new ResultPoint(x2 - f2, y2 - f3);
        float x3 = resultPointArr[1].getX() - resultPointArr[3].getX();
        float y3 = resultPointArr[1].getY() - resultPointArr[3].getY();
        float x4 = (resultPointArr[1].getX() + resultPointArr[3].getX()) / 2.0f;
        float y4 = (resultPointArr[1].getY() + resultPointArr[3].getY()) / 2.0f;
        float f4 = x3 * f;
        float f5 = f * y3;
        return new ResultPoint[]{resultPoint, new ResultPoint(x4 + f4, y4 + f5), resultPoint2, new ResultPoint(x4 - f4, y4 - f5)};
    }

    private boolean isValid(int r2, int r3) {
        return r2 >= 0 && r2 < this.image.getWidth() && r3 > 0 && r3 < this.image.getHeight();
    }

    private boolean isValid(ResultPoint resultPoint) {
        return isValid(MathUtils.round(resultPoint.getX()), MathUtils.round(resultPoint.getY()));
    }

    private static float distance(Point point, Point point2) {
        return MathUtils.distance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    private static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private int getDimension() {
        if (this.compact) {
            return (this.nbLayers * 4) + 11;
        }
        int r0 = this.nbLayers;
        return r0 <= 4 ? (r0 * 4) + 15 : (r0 * 4) + ((((r0 - 4) / 8) + 1) * 2) + 15;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class Point {

        /* renamed from: x */
        private final int f1216x;

        /* renamed from: y */
        private final int f1217y;

        ResultPoint toResultPoint() {
            return new ResultPoint(getX(), getY());
        }

        Point(int r1, int r2) {
            this.f1216x = r1;
            this.f1217y = r2;
        }

        int getX() {
            return this.f1216x;
        }

        int getY() {
            return this.f1217y;
        }

        public String toString() {
            return "<" + this.f1216x + ' ' + this.f1217y + Typography.greater;
        }
    }
}
