package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
final class MultiFinderPatternFinder extends FinderPatternFinder {
    private static final float DIFF_MODSIZE_CUTOFF = 0.5f;
    private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05f;
    private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
    private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0f;
    private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ModuleSizeComparator implements Serializable, Comparator<FinderPattern> {
        private ModuleSizeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            double estimatedModuleSize = finderPattern2.getEstimatedModuleSize() - finderPattern.getEstimatedModuleSize();
            if (estimatedModuleSize < 0.0d) {
                return -1;
            }
            return estimatedModuleSize > 0.0d ? 1 : 0;
        }
    }

    MultiFinderPatternFinder(BitMatrix bitMatrix) {
        super(bitMatrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiFinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        super(bitMatrix, resultPointCallback);
    }

    private FinderPattern[][] selectMutipleBestPatterns() throws NotFoundException {
        List<FinderPattern> possibleCenters = getPossibleCenters();
        int size = possibleCenters.size();
        int r2 = 3;
        if (size >= 3) {
            char c = 0;
            if (size == 3) {
                return new FinderPattern[][]{new FinderPattern[]{possibleCenters.get(0), possibleCenters.get(1), possibleCenters.get(2)}};
            }
            Collections.sort(possibleCenters, new ModuleSizeComparator());
            ArrayList arrayList = new ArrayList();
            int r7 = 0;
            while (r7 < size - 2) {
                FinderPattern finderPattern = possibleCenters.get(r7);
                if (finderPattern != null) {
                    int r9 = r7 + 1;
                    while (r9 < size - 1) {
                        FinderPattern finderPattern2 = possibleCenters.get(r9);
                        if (finderPattern2 != null) {
                            float estimatedModuleSize = (finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize()) / Math.min(finderPattern.getEstimatedModuleSize(), finderPattern2.getEstimatedModuleSize());
                            float abs = Math.abs(finderPattern.getEstimatedModuleSize() - finderPattern2.getEstimatedModuleSize());
                            float f = DIFF_MODSIZE_CUTOFF_PERCENT;
                            float f2 = DIFF_MODSIZE_CUTOFF;
                            if (abs <= DIFF_MODSIZE_CUTOFF || estimatedModuleSize < DIFF_MODSIZE_CUTOFF_PERCENT) {
                                int r11 = r9 + 1;
                                while (r11 < size) {
                                    FinderPattern finderPattern3 = possibleCenters.get(r11);
                                    if (finderPattern3 != null) {
                                        float estimatedModuleSize2 = (finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) / Math.min(finderPattern2.getEstimatedModuleSize(), finderPattern3.getEstimatedModuleSize());
                                        if (Math.abs(finderPattern2.getEstimatedModuleSize() - finderPattern3.getEstimatedModuleSize()) <= f2 || estimatedModuleSize2 < f) {
                                            FinderPattern[] finderPatternArr = new FinderPattern[r2];
                                            finderPatternArr[c] = finderPattern;
                                            finderPatternArr[1] = finderPattern2;
                                            finderPatternArr[2] = finderPattern3;
                                            ResultPoint.orderBestPatterns(finderPatternArr);
                                            FinderPatternInfo finderPatternInfo = new FinderPatternInfo(finderPatternArr);
                                            float distance = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getBottomLeft());
                                            float distance2 = ResultPoint.distance(finderPatternInfo.getTopRight(), finderPatternInfo.getBottomLeft());
                                            float distance3 = ResultPoint.distance(finderPatternInfo.getTopLeft(), finderPatternInfo.getTopRight());
                                            float estimatedModuleSize3 = (distance + distance3) / (finderPattern.getEstimatedModuleSize() * 2.0f);
                                            if (estimatedModuleSize3 <= 180.0f && estimatedModuleSize3 >= MIN_MODULE_COUNT_PER_EDGE && Math.abs((distance - distance3) / Math.min(distance, distance3)) < 0.1f) {
                                                float sqrt = (float) Math.sqrt((distance * distance) + (distance3 * distance3));
                                                if (Math.abs((distance2 - sqrt) / Math.min(distance2, sqrt)) < 0.1f) {
                                                    arrayList.add(finderPatternArr);
                                                }
                                            }
                                        }
                                    }
                                    r11++;
                                    r2 = 3;
                                    c = 0;
                                    f = DIFF_MODSIZE_CUTOFF_PERCENT;
                                    f2 = DIFF_MODSIZE_CUTOFF;
                                }
                            }
                        }
                        r9++;
                        r2 = 3;
                        c = 0;
                    }
                }
                r7++;
                r2 = 3;
                c = 0;
            }
            if (!arrayList.isEmpty()) {
                return (FinderPattern[][]) arrayList.toArray(new FinderPattern[arrayList.size()]);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public FinderPatternInfo[] findMulti(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        BitMatrix image = getImage();
        int height = image.getHeight();
        int width = image.getWidth();
        int r5 = ((height * 3) / 388 < 3 || z) ? 3 : 3;
        int[] r12 = new int[5];
        for (int r7 = r5 - 1; r7 < height; r7 += r5) {
            clearCounts(r12);
            int r9 = 0;
            for (int r8 = 0; r8 < width; r8++) {
                if (image.get(r8, r7)) {
                    if ((r9 & 1) == 1) {
                        r9++;
                    }
                    r12[r9] = r12[r9] + 1;
                } else if ((r9 & 1) != 0) {
                    r12[r9] = r12[r9] + 1;
                } else if (r9 == 4) {
                    if (foundPatternCross(r12) && handlePossibleCenter(r12, r7, r8)) {
                        clearCounts(r12);
                        r9 = 0;
                    } else {
                        shiftCounts2(r12);
                        r9 = 3;
                    }
                } else {
                    r9++;
                    r12[r9] = r12[r9] + 1;
                }
            }
            if (foundPatternCross(r12)) {
                handlePossibleCenter(r12, r7, width);
            }
        }
        FinderPattern[][] selectMutipleBestPatterns = selectMutipleBestPatterns();
        ArrayList arrayList = new ArrayList();
        for (FinderPattern[] finderPatternArr : selectMutipleBestPatterns) {
            ResultPoint.orderBestPatterns(finderPatternArr);
            arrayList.add(new FinderPatternInfo(finderPatternArr));
        }
        if (arrayList.isEmpty()) {
            return EMPTY_RESULT_ARRAY;
        }
        return (FinderPatternInfo[]) arrayList.toArray(new FinderPatternInfo[arrayList.size()]);
    }
}
