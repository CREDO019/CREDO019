package com.google.common.math;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Quantiles {
    /* JADX INFO: Access modifiers changed from: private */
    public static double interpolate(double d, double d2, double d3, double d4) {
        if (d == Double.NEGATIVE_INFINITY) {
            return d2 == Double.POSITIVE_INFINITY ? Double.NaN : Double.NEGATIVE_INFINITY;
        } else if (d2 == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else {
            return d + (((d2 - d) * d3) / d4);
        }
    }

    public static ScaleAndIndex median() {
        return scale(2).index(1);
    }

    public static Scale quartiles() {
        return scale(4);
    }

    public static Scale percentiles() {
        return scale(100);
    }

    public static Scale scale(int r2) {
        return new Scale(r2);
    }

    /* loaded from: classes3.dex */
    public static final class Scale {
        private final int scale;

        private Scale(int r3) {
            Preconditions.checkArgument(r3 > 0, "Quantile scale must be positive");
            this.scale = r3;
        }

        public ScaleAndIndex index(int r4) {
            return new ScaleAndIndex(this.scale, r4);
        }

        public ScaleAndIndexes indexes(int... r4) {
            return new ScaleAndIndexes(this.scale, (int[]) r4.clone());
        }

        public ScaleAndIndexes indexes(Collection<Integer> collection) {
            return new ScaleAndIndexes(this.scale, Ints.toArray(collection));
        }
    }

    /* loaded from: classes3.dex */
    public static final class ScaleAndIndex {
        private final int index;
        private final int scale;

        private ScaleAndIndex(int r1, int r2) {
            Quantiles.checkIndex(r2, r1);
            this.scale = r1;
            this.index = r2;
        }

        public double compute(Collection<? extends Number> collection) {
            return computeInPlace(Doubles.toArray(collection));
        }

        public double compute(double... dArr) {
            return computeInPlace((double[]) dArr.clone());
        }

        public double compute(long... jArr) {
            return computeInPlace(Quantiles.longsToDoubles(jArr));
        }

        public double compute(int... r3) {
            return computeInPlace(Quantiles.intsToDoubles(r3));
        }

        public double computeInPlace(double... dArr) {
            Preconditions.checkArgument(dArr.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.containsNaN(dArr)) {
                return Double.NaN;
            }
            long length = this.index * (dArr.length - 1);
            int divide = (int) LongMath.divide(length, this.scale, RoundingMode.DOWN);
            int r4 = (int) (length - (divide * this.scale));
            Quantiles.selectInPlace(divide, dArr, 0, dArr.length - 1);
            if (r4 != 0) {
                int r1 = divide + 1;
                Quantiles.selectInPlace(r1, dArr, r1, dArr.length - 1);
                return Quantiles.interpolate(dArr[divide], dArr[r1], r4, this.scale);
            }
            return dArr[divide];
        }
    }

    /* loaded from: classes3.dex */
    public static final class ScaleAndIndexes {
        private final int[] indexes;
        private final int scale;

        private ScaleAndIndexes(int r5, int[] r6) {
            for (int r3 : r6) {
                Quantiles.checkIndex(r3, r5);
            }
            Preconditions.checkArgument(r6.length > 0, "Indexes must be a non empty array");
            this.scale = r5;
            this.indexes = r6;
        }

        public Map<Integer, Double> compute(Collection<? extends Number> collection) {
            return computeInPlace(Doubles.toArray(collection));
        }

        public Map<Integer, Double> compute(double... dArr) {
            return computeInPlace((double[]) dArr.clone());
        }

        public Map<Integer, Double> compute(long... jArr) {
            return computeInPlace(Quantiles.longsToDoubles(jArr));
        }

        public Map<Integer, Double> compute(int... r1) {
            return computeInPlace(Quantiles.intsToDoubles(r1));
        }

        public Map<Integer, Double> computeInPlace(double... dArr) {
            int r8 = 0;
            int r2 = 1;
            Preconditions.checkArgument(dArr.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.containsNaN(dArr)) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int[] r22 = this.indexes;
                int length = r22.length;
                while (r8 < length) {
                    linkedHashMap.put(Integer.valueOf(r22[r8]), Double.valueOf(Double.NaN));
                    r8++;
                }
                return Collections.unmodifiableMap(linkedHashMap);
            }
            int[] r1 = this.indexes;
            int[] r9 = new int[r1.length];
            int[] r10 = new int[r1.length];
            int[] r12 = new int[r1.length * 2];
            int r3 = 0;
            int r4 = 0;
            while (true) {
                int[] r5 = this.indexes;
                if (r3 >= r5.length) {
                    break;
                }
                long length2 = r5[r3] * (dArr.length - r2);
                int divide = (int) LongMath.divide(length2, this.scale, RoundingMode.DOWN);
                int r16 = r3;
                int r23 = (int) (length2 - (divide * this.scale));
                r9[r16] = divide;
                r10[r16] = r23;
                r12[r4] = divide;
                r4++;
                if (r23 != 0) {
                    r12[r4] = divide + 1;
                    r4++;
                }
                r3 = r16 + 1;
                r2 = 1;
            }
            Arrays.sort(r12, 0, r4);
            Quantiles.selectAllInPlace(r12, 0, r4 - 1, dArr, 0, dArr.length - 1);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            while (true) {
                int[] r24 = this.indexes;
                if (r8 < r24.length) {
                    int r32 = r9[r8];
                    int r42 = r10[r8];
                    if (r42 == 0) {
                        linkedHashMap2.put(Integer.valueOf(r24[r8]), Double.valueOf(dArr[r32]));
                    } else {
                        linkedHashMap2.put(Integer.valueOf(r24[r8]), Double.valueOf(Quantiles.interpolate(dArr[r32], dArr[r32 + 1], r42, this.scale)));
                    }
                    r8++;
                } else {
                    return Collections.unmodifiableMap(linkedHashMap2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean containsNaN(double... dArr) {
        for (double d : dArr) {
            if (Double.isNaN(d)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkIndex(int r2, int r3) {
        if (r2 < 0 || r2 > r3) {
            StringBuilder sb = new StringBuilder(70);
            sb.append("Quantile indexes must be between 0 and the scale, which is ");
            sb.append(r3);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double[] longsToDoubles(long[] jArr) {
        int length = jArr.length;
        double[] dArr = new double[length];
        for (int r2 = 0; r2 < length; r2++) {
            dArr[r2] = jArr[r2];
        }
        return dArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double[] intsToDoubles(int[] r5) {
        int length = r5.length;
        double[] dArr = new double[length];
        for (int r2 = 0; r2 < length; r2++) {
            dArr[r2] = r5[r2];
        }
        return dArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void selectInPlace(int r6, double[] dArr, int r8, int r9) {
        if (r6 != r8) {
            while (r9 > r8) {
                int partition = partition(dArr, r8, r9);
                if (partition >= r6) {
                    r9 = partition - 1;
                }
                if (partition <= r6) {
                    r8 = partition + 1;
                }
            }
            return;
        }
        int r0 = r8;
        for (int r62 = r8 + 1; r62 <= r9; r62++) {
            if (dArr[r0] > dArr[r62]) {
                r0 = r62;
            }
        }
        if (r0 != r8) {
            swap(dArr, r0, r8);
        }
    }

    private static int partition(double[] dArr, int r7, int r8) {
        movePivotToStartOfSlice(dArr, r7, r8);
        double d = dArr[r7];
        int r2 = r8;
        while (r8 > r7) {
            if (dArr[r8] > d) {
                swap(dArr, r2, r8);
                r2--;
            }
            r8--;
        }
        swap(dArr, r7, r2);
        return r2;
    }

    private static void movePivotToStartOfSlice(double[] dArr, int r11, int r12) {
        int r0 = (r11 + r12) >>> 1;
        boolean z = dArr[r12] < dArr[r0];
        boolean z2 = dArr[r0] < dArr[r11];
        boolean z3 = dArr[r12] < dArr[r11];
        if (z == z2) {
            swap(dArr, r0, r11);
        } else if (z != z3) {
            swap(dArr, r11, r12);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void selectAllInPlace(int[] r9, int r10, int r11, double[] dArr, int r13, int r14) {
        int chooseNextSelection = chooseNextSelection(r9, r10, r11, r13, r14);
        int r1 = r9[chooseNextSelection];
        selectInPlace(r1, dArr, r13, r14);
        int r5 = chooseNextSelection - 1;
        while (r5 >= r10 && r9[r5] == r1) {
            r5--;
        }
        if (r5 >= r10) {
            selectAllInPlace(r9, r10, r5, dArr, r13, r1 - 1);
        }
        int r3 = chooseNextSelection + 1;
        while (r3 <= r11 && r9[r3] == r1) {
            r3++;
        }
        if (r3 <= r11) {
            selectAllInPlace(r9, r3, r11, dArr, r1 + 1, r14);
        }
    }

    private static int chooseNextSelection(int[] r2, int r3, int r4, int r5, int r6) {
        if (r3 == r4) {
            return r3;
        }
        int r52 = r5 + r6;
        int r62 = r52 >>> 1;
        while (r4 > r3 + 1) {
            int r0 = (r3 + r4) >>> 1;
            if (r2[r0] > r62) {
                r4 = r0;
            } else if (r2[r0] >= r62) {
                return r0;
            } else {
                r3 = r0;
            }
        }
        return (r52 - r2[r3]) - r2[r4] > 0 ? r4 : r3;
    }

    private static void swap(double[] dArr, int r5, int r6) {
        double d = dArr[r5];
        dArr[r5] = dArr[r6];
        dArr[r6] = d;
    }
}
