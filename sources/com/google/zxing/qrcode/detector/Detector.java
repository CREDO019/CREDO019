package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

/* loaded from: classes3.dex */
public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    protected final ResultPointCallback getResultPointCallback() {
        return this.resultPointCallback;
    }

    public DetectorResult detect() throws NotFoundException, FormatException {
        return detect(null);
    }

    public final DetectorResult detect(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        this.resultPointCallback = resultPointCallback;
        return processFinderPatternInfo(new FinderPatternFinder(this.image, resultPointCallback).find(map));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DetectorResult processFinderPatternInfo(FinderPatternInfo finderPatternInfo) throws NotFoundException, FormatException {
        FinderPattern topLeft = finderPatternInfo.getTopLeft();
        FinderPattern topRight = finderPatternInfo.getTopRight();
        FinderPattern bottomLeft = finderPatternInfo.getBottomLeft();
        float calculateModuleSize = calculateModuleSize(topLeft, topRight, bottomLeft);
        if (calculateModuleSize < 1.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int computeDimension = computeDimension(topLeft, topRight, bottomLeft, calculateModuleSize);
        Version provisionalVersionForDimension = Version.getProvisionalVersionForDimension(computeDimension);
        int dimensionForVersion = provisionalVersionForDimension.getDimensionForVersion() - 7;
        AlignmentPattern alignmentPattern = null;
        if (provisionalVersionForDimension.getAlignmentPatternCenters().length > 0) {
            float x = (topRight.getX() - topLeft.getX()) + bottomLeft.getX();
            float y = (topRight.getY() - topLeft.getY()) + bottomLeft.getY();
            float f = 1.0f - (3.0f / dimensionForVersion);
            int x2 = (int) (topLeft.getX() + ((x - topLeft.getX()) * f));
            int y2 = (int) (topLeft.getY() + (f * (y - topLeft.getY())));
            for (int r6 = 4; r6 <= 16; r6 <<= 1) {
                try {
                    alignmentPattern = findAlignmentInRegion(calculateModuleSize, x2, y2, r6);
                    break;
                } catch (NotFoundException unused) {
                }
            }
        }
        return new DetectorResult(sampleGrid(this.image, createTransform(topLeft, topRight, bottomLeft, alignmentPattern, computeDimension), computeDimension), alignmentPattern == null ? new ResultPoint[]{bottomLeft, topLeft, topRight} : new ResultPoint[]{bottomLeft, topLeft, topRight, alignmentPattern});
    }

    private static PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int r22) {
        float x;
        float y;
        float f;
        float f2 = r22 - 3.5f;
        if (resultPoint4 != null) {
            x = resultPoint4.getX();
            y = resultPoint4.getY();
            f = f2 - 3.0f;
        } else {
            x = (resultPoint2.getX() - resultPoint.getX()) + resultPoint3.getX();
            y = (resultPoint2.getY() - resultPoint.getY()) + resultPoint3.getY();
            f = f2;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f2, 3.5f, f, f, 3.5f, f2, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), x, y, resultPoint3.getX(), resultPoint3.getY());
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int r3) throws NotFoundException {
        return GridSampler.getInstance().sampleGrid(bitMatrix, r3, r3, perspectiveTransform);
    }

    private static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f) throws NotFoundException {
        int round = ((MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2) / f) + MathUtils.round(ResultPoint.distance(resultPoint, resultPoint3) / f)) / 2) + 7;
        int r2 = round & 3;
        if (r2 != 0) {
            if (r2 != 2) {
                if (r2 != 3) {
                    return round;
                }
                throw NotFoundException.getNotFoundInstance();
            }
            return round - 1;
        }
        return round + 1;
    }

    protected final float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / 2.0f;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float sizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        float sizeOfBlackWhiteBlackRunBothWays2 = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        return Float.isNaN(sizeOfBlackWhiteBlackRunBothWays) ? sizeOfBlackWhiteBlackRunBothWays2 / 7.0f : Float.isNaN(sizeOfBlackWhiteBlackRunBothWays2) ? sizeOfBlackWhiteBlackRunBothWays / 7.0f : (sizeOfBlackWhiteBlackRunBothWays + sizeOfBlackWhiteBlackRunBothWays2) / 14.0f;
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int r6, int r7, int r8, int r9) {
        float f;
        float f2;
        float sizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(r6, r7, r8, r9);
        int r82 = r6 - (r8 - r6);
        int r1 = 0;
        if (r82 < 0) {
            f = r6 / (r6 - r82);
            r82 = 0;
        } else if (r82 >= this.image.getWidth()) {
            f = ((this.image.getWidth() - 1) - r6) / (r82 - r6);
            r82 = this.image.getWidth() - 1;
        } else {
            f = 1.0f;
        }
        float f3 = r7;
        int r92 = (int) (f3 - ((r9 - r7) * f));
        if (r92 < 0) {
            f2 = f3 / (r7 - r92);
        } else if (r92 >= this.image.getHeight()) {
            f2 = ((this.image.getHeight() - 1) - r7) / (r92 - r7);
            r1 = this.image.getHeight() - 1;
        } else {
            r1 = r92;
            f2 = 1.0f;
        }
        return (sizeOfBlackWhiteBlackRun + sizeOfBlackWhiteBlackRun(r6, r7, (int) (r6 + ((r82 - r6) * f2)), r1)) - 1.0f;
    }

    private float sizeOfBlackWhiteBlackRun(int r18, int r19, int r20, int r21) {
        int r1;
        int r4;
        int r5;
        int r6;
        int r192;
        Detector detector;
        boolean z;
        boolean z2;
        int r3 = 1;
        boolean z3 = Math.abs(r21 - r19) > Math.abs(r20 - r18);
        if (z3) {
            r4 = r18;
            r1 = r19;
            r6 = r20;
            r5 = r21;
        } else {
            r1 = r18;
            r4 = r19;
            r5 = r20;
            r6 = r21;
        }
        int abs = Math.abs(r5 - r1);
        int abs2 = Math.abs(r6 - r4);
        int r9 = (-abs) / 2;
        int r12 = r1 < r5 ? 1 : -1;
        int r11 = r4 < r6 ? 1 : -1;
        int r52 = r5 + r12;
        int r13 = r1;
        int r14 = r4;
        int r15 = 0;
        while (true) {
            if (r13 == r52) {
                r192 = r52;
                break;
            }
            int r2 = z3 ? r14 : r13;
            int r10 = z3 ? r13 : r14;
            if (r15 == r3) {
                detector = this;
                z = z3;
                r192 = r52;
                z2 = true;
            } else {
                detector = this;
                z = z3;
                r192 = r52;
                z2 = false;
            }
            if (z2 == detector.image.get(r2, r10)) {
                if (r15 == 2) {
                    return MathUtils.distance(r13, r14, r1, r4);
                }
                r15++;
            }
            r9 += abs2;
            if (r9 > 0) {
                if (r14 == r6) {
                    break;
                }
                r14 += r11;
                r9 -= abs;
            }
            r13 += r12;
            r52 = r192;
            z3 = z;
            r3 = 1;
        }
        if (r15 == 2) {
            return MathUtils.distance(r192, r6, r1, r4);
        }
        return Float.NaN;
    }

    protected final AlignmentPattern findAlignmentInRegion(float f, int r12, int r13, float f2) throws NotFoundException {
        int r14 = (int) (f2 * f);
        int max = Math.max(0, r12 - r14);
        int min = Math.min(this.image.getWidth() - 1, r12 + r14) - max;
        float f3 = 3.0f * f;
        if (min < f3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int max2 = Math.max(0, r13 - r14);
        int min2 = Math.min(this.image.getHeight() - 1, r13 + r14) - max2;
        if (min2 < f3) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new AlignmentPatternFinder(this.image, max, max2, min, min2, f, this.resultPointCallback).find();
    }
}
