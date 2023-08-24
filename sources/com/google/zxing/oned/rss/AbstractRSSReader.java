package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.OneDReader;

/* loaded from: classes3.dex */
public abstract class AbstractRSSReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.2f;
    private static final float MAX_FINDER_PATTERN_RATIO = 0.89285713f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.45f;
    private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667f;
    private final int[] dataCharacterCounters;
    private final int[] evenCounts;
    private final int[] oddCounts;
    private final int[] decodeFinderCounters = new int[4];
    private final float[] oddRoundingErrors = new float[4];
    private final float[] evenRoundingErrors = new float[4];

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractRSSReader() {
        int[] r1 = new int[8];
        this.dataCharacterCounters = r1;
        this.oddCounts = new int[r1.length / 2];
        this.evenCounts = new int[r1.length / 2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getDecodeFinderCounters() {
        return this.decodeFinderCounters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getDataCharacterCounters() {
        return this.dataCharacterCounters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float[] getOddRoundingErrors() {
        return this.oddRoundingErrors;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float[] getEvenRoundingErrors() {
        return this.evenRoundingErrors;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getOddCounts() {
        return this.oddCounts;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getEvenCounts() {
        return this.evenCounts;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int parseFinderValue(int[] r3, int[][] r4) throws NotFoundException {
        for (int r0 = 0; r0 < r4.length; r0++) {
            if (patternMatchVariance(r3, r4[r0], MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                return r0;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Deprecated
    protected static int count(int[] r0) {
        return MathUtils.sum(r0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void increment(int[] r5, float[] fArr) {
        int r0 = 0;
        float f = fArr[0];
        for (int r3 = 1; r3 < r5.length; r3++) {
            if (fArr[r3] > f) {
                f = fArr[r3];
                r0 = r3;
            }
        }
        r5[r0] = r5[r0] + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void decrement(int[] r5, float[] fArr) {
        int r0 = 0;
        float f = fArr[0];
        for (int r3 = 1; r3 < r5.length; r3++) {
            if (fArr[r3] < f) {
                f = fArr[r3];
                r0 = r3;
            }
        }
        r5[r0] = r5[r0] - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isFinderPattern(int[] r7) {
        int r1;
        float f = (r7[0] + r7[1]) / ((r7[2] + r1) + r7[3]);
        if (f >= MIN_FINDER_PATTERN_RATIO && f <= MAX_FINDER_PATTERN_RATIO) {
            int r12 = Integer.MAX_VALUE;
            int r3 = Integer.MIN_VALUE;
            for (int r6 : r7) {
                if (r6 > r3) {
                    r3 = r6;
                }
                if (r6 < r12) {
                    r12 = r6;
                }
            }
            if (r3 < r12 * 10) {
                return true;
            }
        }
        return false;
    }
}
