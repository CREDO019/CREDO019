package com.google.common.primitives;

import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Floats extends FloatsMethodsForWeb {
    public static final int BYTES = 4;

    public static boolean isFinite(float f) {
        return Float.NEGATIVE_INFINITY < f && f < Float.POSITIVE_INFINITY;
    }

    private Floats() {
    }

    public static int hashCode(float f) {
        return Float.valueOf(f).hashCode();
    }

    public static int compare(float f, float f2) {
        return Float.compare(f, f2);
    }

    public static boolean contains(float[] fArr, float f) {
        for (float f2 : fArr) {
            if (f2 == f) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(float[] fArr, float f) {
        return indexOf(fArr, f, 0, fArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(float[] fArr, float f, int r3, int r4) {
        while (r3 < r4) {
            if (fArr[r3] == f) {
                return r3;
            }
            r3++;
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(float[] r5, float[] r6) {
        /*
            java.lang.String r0 = "array"
            com.google.common.base.Preconditions.checkNotNull(r5, r0)
            java.lang.String r0 = "target"
            com.google.common.base.Preconditions.checkNotNull(r6, r0)
            int r0 = r6.length
            r1 = 0
            if (r0 != 0) goto Lf
            return r1
        Lf:
            r0 = 0
        L10:
            int r2 = r5.length
            int r3 = r6.length
            int r2 = r2 - r3
            int r2 = r2 + 1
            if (r0 >= r2) goto L2c
            r2 = 0
        L18:
            int r3 = r6.length
            if (r2 >= r3) goto L2b
            int r3 = r0 + r2
            r3 = r5[r3]
            r4 = r6[r2]
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 == 0) goto L28
            int r0 = r0 + 1
            goto L10
        L28:
            int r2 = r2 + 1
            goto L18
        L2b:
            return r0
        L2c:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Floats.indexOf(float[], float[]):int");
    }

    public static int lastIndexOf(float[] fArr, float f) {
        return lastIndexOf(fArr, f, 0, fArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(float[] fArr, float f, int r3, int r4) {
        for (int r42 = r4 - 1; r42 >= r3; r42--) {
            if (fArr[r42] == f) {
                return r42;
            }
        }
        return -1;
    }

    public static float min(float... fArr) {
        Preconditions.checkArgument(fArr.length > 0);
        float f = fArr[0];
        for (int r2 = 1; r2 < fArr.length; r2++) {
            f = Math.min(f, fArr[r2]);
        }
        return f;
    }

    public static float max(float... fArr) {
        Preconditions.checkArgument(fArr.length > 0);
        float f = fArr[0];
        for (int r2 = 1; r2 < fArr.length; r2++) {
            f = Math.max(f, fArr[r2]);
        }
        return f;
    }

    public static float constrainToRange(float f, float f2, float f3) {
        if (f2 <= f3) {
            return Math.min(Math.max(f, f2), f3);
        }
        throw new IllegalArgumentException(Strings.lenientFormat("min (%s) must be less than or equal to max (%s)", Float.valueOf(f2), Float.valueOf(f3)));
    }

    public static float[] concat(float[]... fArr) {
        int r3 = 0;
        for (float[] fArr2 : fArr) {
            r3 += fArr2.length;
        }
        float[] fArr3 = new float[r3];
        int r4 = 0;
        for (float[] fArr4 : fArr) {
            System.arraycopy(fArr4, 0, fArr3, r4, fArr4.length);
            r4 += fArr4.length;
        }
        return fArr3;
    }

    /* loaded from: classes3.dex */
    private static final class FloatConverter extends Converter<String, Float> implements Serializable {
        static final FloatConverter INSTANCE = new FloatConverter();
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Floats.stringConverter()";
        }

        private FloatConverter() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public Float doForward(String str) {
            return Float.valueOf(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public String doBackward(Float f) {
            return f.toString();
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    public static Converter<String, Float> stringConverter() {
        return FloatConverter.INSTANCE;
    }

    public static float[] ensureCapacity(float[] fArr, int r5, int r6) {
        Preconditions.checkArgument(r5 >= 0, "Invalid minLength: %s", r5);
        Preconditions.checkArgument(r6 >= 0, "Invalid padding: %s", r6);
        return fArr.length < r5 ? Arrays.copyOf(fArr, r5 + r6) : fArr;
    }

    public static String join(String str, float... fArr) {
        Preconditions.checkNotNull(str);
        if (fArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(fArr.length * 12);
        sb.append(fArr[0]);
        for (int r1 = 1; r1 < fArr.length; r1++) {
            sb.append(str);
            sb.append(fArr[r1]);
        }
        return sb.toString();
    }

    public static Comparator<float[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    private enum LexicographicalComparator implements Comparator<float[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Floats.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(float[] fArr, float[] fArr2) {
            int min = Math.min(fArr.length, fArr2.length);
            for (int r1 = 0; r1 < min; r1++) {
                int compare = Float.compare(fArr[r1], fArr2[r1]);
                if (compare != 0) {
                    return compare;
                }
            }
            return fArr.length - fArr2.length;
        }
    }

    public static void sortDescending(float[] fArr) {
        Preconditions.checkNotNull(fArr);
        sortDescending(fArr, 0, fArr.length);
    }

    public static void sortDescending(float[] fArr, int r2, int r3) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkPositionIndexes(r2, r3, fArr.length);
        Arrays.sort(fArr, r2, r3);
        reverse(fArr, r2, r3);
    }

    public static void reverse(float[] fArr) {
        Preconditions.checkNotNull(fArr);
        reverse(fArr, 0, fArr.length);
    }

    public static void reverse(float[] fArr, int r3, int r4) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkPositionIndexes(r3, r4, fArr.length);
        for (int r42 = r4 - 1; r3 < r42; r42--) {
            float f = fArr[r3];
            fArr[r3] = fArr[r42];
            fArr[r42] = f;
            r3++;
        }
    }

    public static float[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof FloatArrayAsList) {
            return ((FloatArrayAsList) collection).toFloatArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        float[] fArr = new float[length];
        for (int r2 = 0; r2 < length; r2++) {
            fArr[r2] = ((Number) Preconditions.checkNotNull(array[r2])).floatValue();
        }
        return fArr;
    }

    public static List<Float> asList(float... fArr) {
        if (fArr.length == 0) {
            return Collections.emptyList();
        }
        return new FloatArrayAsList(fArr);
    }

    /* loaded from: classes3.dex */
    private static class FloatArrayAsList extends AbstractList<Float> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final float[] array;
        final int end;
        final int start;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        FloatArrayAsList(float[] fArr) {
            this(fArr, 0, fArr.length);
        }

        FloatArrayAsList(float[] fArr, int r2, int r3) {
            this.array = fArr;
            this.start = r2;
            this.end = r3;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Float get(int r3) {
            Preconditions.checkElementIndex(r3, size());
            return Float.valueOf(this.array[this.start + r3]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Float) && Floats.indexOf(this.array, ((Float) obj).floatValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            int indexOf;
            if (!(obj instanceof Float) || (indexOf = Floats.indexOf(this.array, ((Float) obj).floatValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return indexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Float) || (lastIndexOf = Floats.lastIndexOf(this.array, ((Float) obj).floatValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return lastIndexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Float set(int r4, Float f) {
            Preconditions.checkElementIndex(r4, size());
            float[] fArr = this.array;
            int r1 = this.start;
            float f2 = fArr[r1 + r4];
            fArr[r1 + r4] = ((Float) Preconditions.checkNotNull(f)).floatValue();
            return Float.valueOf(f2);
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Float> subList(int r4, int r5) {
            Preconditions.checkPositionIndexes(r4, r5, size());
            if (r4 == r5) {
                return Collections.emptyList();
            }
            float[] fArr = this.array;
            int r2 = this.start;
            return new FloatArrayAsList(fArr, r4 + r2, r2 + r5);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof FloatArrayAsList) {
                FloatArrayAsList floatArrayAsList = (FloatArrayAsList) obj;
                int size = size();
                if (floatArrayAsList.size() != size) {
                    return false;
                }
                for (int r2 = 0; r2 < size; r2++) {
                    if (this.array[this.start + r2] != floatArrayAsList.array[floatArrayAsList.start + r2]) {
                        return false;
                    }
                }
                return true;
            }
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            int r1 = 1;
            for (int r0 = this.start; r0 < this.end; r0++) {
                r1 = (r1 * 31) + Floats.hashCode(this.array[r0]);
            }
            return r1;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 12);
            sb.append('[');
            sb.append(this.array[this.start]);
            int r1 = this.start;
            while (true) {
                r1++;
                if (r1 < this.end) {
                    sb.append(", ");
                    sb.append(this.array[r1]);
                } else {
                    sb.append(']');
                    return sb.toString();
                }
            }
        }

        float[] toFloatArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    @CheckForNull
    public static Float tryParse(String str) {
        if (Doubles.FLOATING_POINT_PATTERN.matcher(str).matches()) {
            try {
                return Float.valueOf(Float.parseFloat(str));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return null;
    }
}
