package com.google.common.primitives;

import com.google.common.base.Ascii;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
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
public final class Ints extends IntsMethodsForWeb {
    public static final int BYTES = 4;
    public static final int MAX_POWER_OF_TWO = 1073741824;

    public static int compare(int r0, int r1) {
        if (r0 < r1) {
            return -1;
        }
        return r0 > r1 ? 1 : 0;
    }

    public static int fromBytes(byte b, byte b2, byte b3, byte b4) {
        return (b << Ascii.CAN) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    public static int hashCode(int r0) {
        return r0;
    }

    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    public static byte[] toByteArray(int r3) {
        return new byte[]{(byte) (r3 >> 24), (byte) (r3 >> 16), (byte) (r3 >> 8), (byte) r3};
    }

    private Ints() {
    }

    public static int checkedCast(long j) {
        int r0 = (int) j;
        Preconditions.checkArgument(((long) r0) == j, "Out of range: %s", j);
        return r0;
    }

    public static boolean contains(int[] r4, int r5) {
        for (int r3 : r4) {
            if (r3 == r5) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(int[] r2, int r3) {
        return indexOf(r2, r3, 0, r2.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(int[] r1, int r2, int r3, int r4) {
        while (r3 < r4) {
            if (r1[r3] == r2) {
                return r3;
            }
            r3++;
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
        r0 = r0 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int indexOf(int[] r5, int[] r6) {
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
            if (r0 >= r2) goto L2a
            r2 = 0
        L18:
            int r3 = r6.length
            if (r2 >= r3) goto L29
            int r3 = r0 + r2
            r3 = r5[r3]
            r4 = r6[r2]
            if (r3 == r4) goto L26
            int r0 = r0 + 1
            goto L10
        L26:
            int r2 = r2 + 1
            goto L18
        L29:
            return r0
        L2a:
            r5 = -1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Ints.indexOf(int[], int[]):int");
    }

    public static int lastIndexOf(int[] r2, int r3) {
        return lastIndexOf(r2, r3, 0, r2.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(int[] r1, int r2, int r3, int r4) {
        for (int r42 = r4 - 1; r42 >= r3; r42--) {
            if (r1[r42] == r2) {
                return r42;
            }
        }
        return -1;
    }

    public static int min(int... r3) {
        Preconditions.checkArgument(r3.length > 0);
        int r0 = r3[0];
        for (int r2 = 1; r2 < r3.length; r2++) {
            if (r3[r2] < r0) {
                r0 = r3[r2];
            }
        }
        return r0;
    }

    public static int max(int... r3) {
        Preconditions.checkArgument(r3.length > 0);
        int r0 = r3[0];
        for (int r2 = 1; r2 < r3.length; r2++) {
            if (r3[r2] > r0) {
                r0 = r3[r2];
            }
        }
        return r0;
    }

    public static int constrainToRange(int r2, int r3, int r4) {
        Preconditions.checkArgument(r3 <= r4, "min (%s) must be less than or equal to max (%s)", r3, r4);
        return Math.min(Math.max(r2, r3), r4);
    }

    public static int[] concat(int[]... r7) {
        int r3 = 0;
        for (int[] r4 : r7) {
            r3 += r4.length;
        }
        int[] r0 = new int[r3];
        int r42 = 0;
        for (int[] r5 : r7) {
            System.arraycopy(r5, 0, r0, r42, r5.length);
            r42 += r5.length;
        }
        return r0;
    }

    public static int fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 4, "array too small: %s < %s", bArr.length, 4);
        return fromBytes(bArr[0], bArr[1], bArr[2], bArr[3]);
    }

    /* loaded from: classes3.dex */
    private static final class IntConverter extends Converter<String, Integer> implements Serializable {
        static final IntConverter INSTANCE = new IntConverter();
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Ints.stringConverter()";
        }

        private IntConverter() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public Integer doForward(String str) {
            return Integer.decode(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public String doBackward(Integer num) {
            return num.toString();
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    public static Converter<String, Integer> stringConverter() {
        return IntConverter.INSTANCE;
    }

    public static int[] ensureCapacity(int[] r4, int r5, int r6) {
        Preconditions.checkArgument(r5 >= 0, "Invalid minLength: %s", r5);
        Preconditions.checkArgument(r6 >= 0, "Invalid padding: %s", r6);
        return r4.length < r5 ? Arrays.copyOf(r4, r5 + r6) : r4;
    }

    public static String join(String str, int... r4) {
        Preconditions.checkNotNull(str);
        if (r4.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(r4.length * 5);
        sb.append(r4[0]);
        for (int r1 = 1; r1 < r4.length; r1++) {
            sb.append(str);
            sb.append(r4[r1]);
        }
        return sb.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    private enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Ints.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(int[] r5, int[] r6) {
            int min = Math.min(r5.length, r6.length);
            for (int r1 = 0; r1 < min; r1++) {
                int compare = Ints.compare(r5[r1], r6[r1]);
                if (compare != 0) {
                    return compare;
                }
            }
            return r5.length - r6.length;
        }
    }

    public static void sortDescending(int[] r2) {
        Preconditions.checkNotNull(r2);
        sortDescending(r2, 0, r2.length);
    }

    public static void sortDescending(int[] r1, int r2, int r3) {
        Preconditions.checkNotNull(r1);
        Preconditions.checkPositionIndexes(r2, r3, r1.length);
        Arrays.sort(r1, r2, r3);
        reverse(r1, r2, r3);
    }

    public static void reverse(int[] r2) {
        Preconditions.checkNotNull(r2);
        reverse(r2, 0, r2.length);
    }

    public static void reverse(int[] r2, int r3, int r4) {
        Preconditions.checkNotNull(r2);
        Preconditions.checkPositionIndexes(r3, r4, r2.length);
        for (int r42 = r4 - 1; r3 < r42; r42--) {
            int r0 = r2[r3];
            r2[r3] = r2[r42];
            r2[r42] = r0;
            r3++;
        }
    }

    public static int[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof IntArrayAsList) {
            return ((IntArrayAsList) collection).toIntArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        int[] r1 = new int[length];
        for (int r2 = 0; r2 < length; r2++) {
            r1[r2] = ((Number) Preconditions.checkNotNull(array[r2])).intValue();
        }
        return r1;
    }

    public static List<Integer> asList(int... r1) {
        if (r1.length == 0) {
            return Collections.emptyList();
        }
        return new IntArrayAsList(r1);
    }

    /* loaded from: classes3.dex */
    private static class IntArrayAsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final int[] array;
        final int end;
        final int start;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        IntArrayAsList(int[] r3) {
            this(r3, 0, r3.length);
        }

        IntArrayAsList(int[] r1, int r2, int r3) {
            this.array = r1;
            this.start = r2;
            this.end = r3;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer get(int r3) {
            Preconditions.checkElementIndex(r3, size());
            return Integer.valueOf(this.array[this.start + r3]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Integer) && Ints.indexOf(this.array, ((Integer) obj).intValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            int indexOf;
            if (!(obj instanceof Integer) || (indexOf = Ints.indexOf(this.array, ((Integer) obj).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return indexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Integer) || (lastIndexOf = Ints.lastIndexOf(this.array, ((Integer) obj).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return lastIndexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Integer set(int r4, Integer num) {
            Preconditions.checkElementIndex(r4, size());
            int[] r0 = this.array;
            int r1 = this.start;
            int r2 = r0[r1 + r4];
            r0[r1 + r4] = ((Integer) Preconditions.checkNotNull(num)).intValue();
            return Integer.valueOf(r2);
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Integer> subList(int r4, int r5) {
            Preconditions.checkPositionIndexes(r4, r5, size());
            if (r4 == r5) {
                return Collections.emptyList();
            }
            int[] r1 = this.array;
            int r2 = this.start;
            return new IntArrayAsList(r1, r4 + r2, r2 + r5);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof IntArrayAsList) {
                IntArrayAsList intArrayAsList = (IntArrayAsList) obj;
                int size = size();
                if (intArrayAsList.size() != size) {
                    return false;
                }
                for (int r2 = 0; r2 < size; r2++) {
                    if (this.array[this.start + r2] != intArrayAsList.array[intArrayAsList.start + r2]) {
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
                r1 = (r1 * 31) + Ints.hashCode(this.array[r0]);
            }
            return r1;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
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

        int[] toIntArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }

    @CheckForNull
    public static Integer tryParse(String str) {
        return tryParse(str, 10);
    }

    @CheckForNull
    public static Integer tryParse(String str, int r5) {
        Long tryParse = Longs.tryParse(str, r5);
        if (tryParse == null || tryParse.longValue() != tryParse.intValue()) {
            return null;
        }
        return Integer.valueOf(tryParse.intValue());
    }
}
