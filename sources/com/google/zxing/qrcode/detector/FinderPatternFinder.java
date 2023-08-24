package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 97;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int r4 = ((height * 3) / 388 < 3 || z) ? 3 : 3;
        int[] r13 = new int[5];
        int r6 = r4 - 1;
        boolean z2 = false;
        while (r6 < height && !z2) {
            clearCounts(r13);
            int r8 = 0;
            int r9 = 0;
            while (r8 < width) {
                if (this.image.get(r8, r6)) {
                    if ((r9 & 1) == 1) {
                        r9++;
                    }
                    r13[r9] = r13[r9] + 1;
                } else if ((r9 & 1) != 0) {
                    r13[r9] = r13[r9] + 1;
                } else if (r9 == 4) {
                    if (foundPatternCross(r13)) {
                        if (handlePossibleCenter(r13, r6, r8)) {
                            if (this.hasSkipped) {
                                z2 = haveMultiplyConfirmedCenters();
                            } else {
                                int findRowSkip = findRowSkip();
                                if (findRowSkip > r13[2]) {
                                    r6 += (findRowSkip - r13[2]) - 2;
                                    r8 = width - 1;
                                }
                            }
                            clearCounts(r13);
                            r4 = 2;
                            r9 = 0;
                        } else {
                            shiftCounts2(r13);
                        }
                    } else {
                        shiftCounts2(r13);
                    }
                    r9 = 3;
                } else {
                    r9++;
                    r13[r9] = r13[r9] + 1;
                }
                r8++;
            }
            if (foundPatternCross(r13) && handlePossibleCenter(r13, r6, width)) {
                r4 = r13[0];
                if (this.hasSkipped) {
                    z2 = haveMultiplyConfirmedCenters();
                }
            }
            r6 += r4;
        }
        FinderPattern[] selectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(selectBestPatterns);
        return new FinderPatternInfo(selectBestPatterns);
    }

    private static float centerFromEnd(int[] r1, int r2) {
        return ((r2 - r1[4]) - r1[3]) - (r1[2] / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean foundPatternCross(int[] r7) {
        int r2 = 0;
        for (int r1 = 0; r1 < 5; r1++) {
            int r3 = r7[r1];
            if (r3 == 0) {
                return false;
            }
            r2 += r3;
        }
        if (r2 < 7) {
            return false;
        }
        float f = r2 / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) r7[0])) < f2 && Math.abs(f - ((float) r7[1])) < f2 && Math.abs((f * 3.0f) - ((float) r7[2])) < 3.0f * f2 && Math.abs(f - ((float) r7[3])) < f2 && Math.abs(f - ((float) r7[4])) < f2;
    }

    protected static boolean foundPatternDiagonal(int[] r7) {
        int r2 = 0;
        for (int r1 = 0; r1 < 5; r1++) {
            int r3 = r7[r1];
            if (r3 == 0) {
                return false;
            }
            r2 += r3;
        }
        if (r2 < 7) {
            return false;
        }
        float f = r2 / 7.0f;
        float f2 = f / 1.333f;
        return Math.abs(f - ((float) r7[0])) < f2 && Math.abs(f - ((float) r7[1])) < f2 && Math.abs((f * 3.0f) - ((float) r7[2])) < 3.0f * f2 && Math.abs(f - ((float) r7[3])) < f2 && Math.abs(f - ((float) r7[4])) < f2;
    }

    private int[] getCrossCheckStateCount() {
        clearCounts(this.crossCheckStateCount);
        return this.crossCheckStateCount;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void clearCounts(int[] r4) {
        for (int r1 = 0; r1 < r4.length; r1++) {
            r4[r1] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void shiftCounts2(int[] r7) {
        r7[0] = r7[2];
        r7[1] = r7[3];
        r7[2] = r7[4];
        r7[3] = 1;
        r7[4] = 0;
    }

    private boolean crossCheckDiagonal(int r11, int r12) {
        int r8;
        int r82;
        int r83;
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int r2 = 0;
        while (r11 >= r2 && r12 >= r2 && this.image.get(r12 - r2, r11 - r2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            r2++;
        }
        if (crossCheckStateCount[2] == 0) {
            return false;
        }
        while (r11 >= r2 && r12 >= r2 && !this.image.get(r12 - r2, r11 - r2)) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            r2++;
        }
        if (crossCheckStateCount[1] == 0) {
            return false;
        }
        while (r11 >= r2 && r12 >= r2 && this.image.get(r12 - r2, r11 - r2)) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            r2++;
        }
        if (crossCheckStateCount[0] == 0) {
            return false;
        }
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int r6 = 1;
        while (true) {
            int r7 = r11 + r6;
            if (r7 >= height || (r83 = r12 + r6) >= width || !this.image.get(r83, r7)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            r6++;
        }
        while (true) {
            int r3 = r11 + r6;
            if (r3 >= height || (r82 = r12 + r6) >= width || this.image.get(r82, r3)) {
                break;
            }
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            r6++;
        }
        if (crossCheckStateCount[3] == 0) {
            return false;
        }
        while (true) {
            int r32 = r11 + r6;
            if (r32 >= height || (r8 = r12 + r6) >= width || !this.image.get(r8, r32)) {
                break;
            }
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            r6++;
        }
        if (crossCheckStateCount[4] == 0) {
            return false;
        }
        return foundPatternDiagonal(crossCheckStateCount);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r2[1] <= r13) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r3 < 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r0.get(r12, r3) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r2[0] > r13) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r2[0] <= r13) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        if (r0.get(r12, r11) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        if (r11 != r1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006d, code lost:
        if (r11 >= r1) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r0.get(r12, r11) != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        if (r2[3] >= r13) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0079, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0081, code lost:
        if (r11 == r1) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0085, code lost:
        if (r2[3] < r13) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0089, code lost:
        if (r11 >= r1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        if (r0.get(r12, r11) == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (r2[4] >= r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009f, code lost:
        if (r2[4] < r13) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a1, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b9, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < (r14 * 2)) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00bb, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00c0, code lost:
        if (foundPatternCross(r2) == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c6, code lost:
        return centerFromEnd(r2, r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckVertical(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckVertical(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r2[1] <= r13) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r3 < 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r0.get(r3, r12) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r2[0] > r13) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r2[0] <= r13) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        if (r0.get(r11, r12) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        if (r11 != r1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006d, code lost:
        if (r11 >= r1) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r0.get(r11, r12) != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        if (r2[3] >= r13) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0079, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0081, code lost:
        if (r11 == r1) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0085, code lost:
        if (r2[3] < r13) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0089, code lost:
        if (r11 >= r1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        if (r0.get(r11, r12) == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (r2[4] >= r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009f, code lost:
        if (r2[4] < r13) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a1, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b7, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < r14) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b9, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00be, code lost:
        if (foundPatternCross(r2) == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c4, code lost:
        return centerFromEnd(r2, r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckHorizontal(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckHorizontal(int, int, int, int):float");
    }

    @Deprecated
    protected final boolean handlePossibleCenter(int[] r1, int r2, int r3, boolean z) {
        return handlePossibleCenter(r1, r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean handlePossibleCenter(int[] r6, int r7, int r8) {
        boolean z = false;
        int r1 = r6[0] + r6[1] + r6[2] + r6[3] + r6[4];
        int centerFromEnd = (int) centerFromEnd(r6, r8);
        float crossCheckVertical = crossCheckVertical(r7, centerFromEnd, r6[2], r1);
        if (!Float.isNaN(crossCheckVertical)) {
            int r4 = (int) crossCheckVertical;
            float crossCheckHorizontal = crossCheckHorizontal(centerFromEnd, r4, r6[2], r1);
            if (!Float.isNaN(crossCheckHorizontal) && crossCheckDiagonal(r4, (int) crossCheckHorizontal)) {
                float f = r1 / 7.0f;
                int r12 = 0;
                while (true) {
                    if (r12 >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern finderPattern = this.possibleCenters.get(r12);
                    if (finderPattern.aboutEquals(f, crossCheckVertical, crossCheckHorizontal)) {
                        this.possibleCenters.set(r12, finderPattern.combineEstimate(crossCheckVertical, crossCheckHorizontal, f));
                        z = true;
                        break;
                    }
                    r12++;
                }
                if (!z) {
                    FinderPattern finderPattern2 = new FinderPattern(crossCheckHorizontal, crossCheckVertical, f);
                    this.possibleCenters.add(finderPattern2);
                    ResultPointCallback resultPointCallback = this.resultPointCallback;
                    if (resultPointCallback != null) {
                        resultPointCallback.foundPossibleResultPoint(finderPattern2);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float f = 0.0f;
        int r4 = 0;
        float f2 = 0.0f;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                r4++;
                f2 += finderPattern.getEstimatedModuleSize();
            }
        }
        if (r4 < 3) {
            return false;
        }
        float f3 = f2 / size;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            f += Math.abs(finderPattern2.getEstimatedModuleSize() - f3);
        }
        return f <= f2 * 0.05f;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        float f;
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float f2 = 0.0f;
        if (size > 3) {
            float f3 = 0.0f;
            float f4 = 0.0f;
            for (FinderPattern finderPattern : this.possibleCenters) {
                float estimatedModuleSize = finderPattern.getEstimatedModuleSize();
                f3 += estimatedModuleSize;
                f4 += estimatedModuleSize * estimatedModuleSize;
            }
            float f5 = f3 / size;
            float sqrt = (float) Math.sqrt((f4 / f) - (f5 * f5));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f5));
            float max = Math.max(0.2f * f5, sqrt);
            int r6 = 0;
            while (r6 < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(r6).getEstimatedModuleSize() - f5) > max) {
                    this.possibleCenters.remove(r6);
                    r6--;
                }
                r6++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            for (FinderPattern finderPattern2 : this.possibleCenters) {
                f2 += finderPattern2.getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(f2 / this.possibleCenters.size()));
            List<FinderPattern> list = this.possibleCenters;
            list.subList(3, list.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class FurthestFromAverageComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            return Float.compare(Math.abs(finderPattern2.getEstimatedModuleSize() - this.average), Math.abs(finderPattern.getEstimatedModuleSize() - this.average));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class CenterComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            int compare = Integer.compare(finderPattern2.getCount(), finderPattern.getCount());
            return compare == 0 ? Float.compare(Math.abs(finderPattern.getEstimatedModuleSize() - this.average), Math.abs(finderPattern2.getEstimatedModuleSize() - this.average)) : compare;
        }
    }
}
