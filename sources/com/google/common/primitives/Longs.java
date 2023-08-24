package com.google.common.primitives;

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
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 4611686018427387904L;

    public static int compare(long j, long j2) {
        int r0 = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (r0 < 0) {
            return -1;
        }
        return r0 > 0 ? 1 : 0;
    }

    public static long fromBytes(byte b, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        return ((b2 & 255) << 48) | ((b & 255) << 56) | ((b3 & 255) << 40) | ((b4 & 255) << 32) | ((b5 & 255) << 24) | ((b6 & 255) << 16) | ((b7 & 255) << 8) | (b8 & 255);
    }

    public static int hashCode(long j) {
        return (int) (j ^ (j >>> 32));
    }

    private Longs() {
    }

    public static boolean contains(long[] jArr, long j) {
        for (long j2 : jArr) {
            if (j2 == j) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(long[] jArr, long j) {
        return indexOf(jArr, j, 0, jArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(long[] jArr, long j, int r6, int r7) {
        while (r6 < r7) {
            if (jArr[r6] == j) {
                return r6;
            }
            r6++;
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
    public static int indexOf(long[] r8, long[] r9) {
        /*
            java.lang.String r0 = "array"
            com.google.common.base.Preconditions.checkNotNull(r8, r0)
            java.lang.String r0 = "target"
            com.google.common.base.Preconditions.checkNotNull(r9, r0)
            int r0 = r9.length
            r1 = 0
            if (r0 != 0) goto Lf
            return r1
        Lf:
            r0 = 0
        L10:
            int r2 = r8.length
            int r3 = r9.length
            int r2 = r2 - r3
            int r2 = r2 + 1
            if (r0 >= r2) goto L2c
            r2 = 0
        L18:
            int r3 = r9.length
            if (r2 >= r3) goto L2b
            int r3 = r0 + r2
            r3 = r8[r3]
            r5 = r9[r2]
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L28
            int r0 = r0 + 1
            goto L10
        L28:
            int r2 = r2 + 1
            goto L18
        L2b:
            return r0
        L2c:
            r8 = -1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Longs.indexOf(long[], long[]):int");
    }

    public static int lastIndexOf(long[] jArr, long j) {
        return lastIndexOf(jArr, j, 0, jArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(long[] jArr, long j, int r6, int r7) {
        for (int r72 = r7 - 1; r72 >= r6; r72--) {
            if (jArr[r72] == j) {
                return r72;
            }
        }
        return -1;
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j = jArr[0];
        for (int r2 = 1; r2 < jArr.length; r2++) {
            if (jArr[r2] < j) {
                j = jArr[r2];
            }
        }
        return j;
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j = jArr[0];
        for (int r2 = 1; r2 < jArr.length; r2++) {
            if (jArr[r2] > j) {
                j = jArr[r2];
            }
        }
        return j;
    }

    public static long constrainToRange(long j, long j2, long j3) {
        Preconditions.checkArgument(j2 <= j3, "min (%s) must be less than or equal to max (%s)", j2, j3);
        return Math.min(Math.max(j, j2), j3);
    }

    public static long[] concat(long[]... jArr) {
        int r3 = 0;
        for (long[] jArr2 : jArr) {
            r3 += jArr2.length;
        }
        long[] jArr3 = new long[r3];
        int r4 = 0;
        for (long[] jArr4 : jArr) {
            System.arraycopy(jArr4, 0, jArr3, r4, jArr4.length);
            r4 += jArr4.length;
        }
        return jArr3;
    }

    public static byte[] toByteArray(long j) {
        byte[] bArr = new byte[8];
        for (int r2 = 7; r2 >= 0; r2--) {
            bArr[r2] = (byte) (255 & j);
            j >>= 8;
        }
        return bArr;
    }

    public static long fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 8, "array too small: %s < %s", bArr.length, 8);
        return fromBytes(bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class AsciiDigits {
        private static final byte[] asciiDigits;

        private AsciiDigits() {
        }

        static {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int r2 = 0; r2 < 10; r2++) {
                bArr[r2 + 48] = (byte) r2;
            }
            for (int r1 = 0; r1 < 26; r1++) {
                byte b = (byte) (r1 + 10);
                bArr[r1 + 65] = b;
                bArr[r1 + 97] = b;
            }
            asciiDigits = bArr;
        }

        static int digit(char c) {
            if (c < 128) {
                return asciiDigits[c];
            }
            return -1;
        }
    }

    @CheckForNull
    public static Long tryParse(String str) {
        return tryParse(str, 10);
    }

    @CheckForNull
    public static Long tryParse(String str, int r19) {
        if (((String) Preconditions.checkNotNull(str)).isEmpty()) {
            return null;
        }
        if (r19 < 2 || r19 > 36) {
            StringBuilder sb = new StringBuilder(65);
            sb.append("radix must be between MIN_RADIX and MAX_RADIX but was ");
            sb.append(r19);
            throw new IllegalArgumentException(sb.toString());
        }
        int r2 = str.charAt(0) == '-' ? 1 : 0;
        if (r2 == str.length()) {
            return null;
        }
        int r4 = r2 + 1;
        int digit = AsciiDigits.digit(str.charAt(r2));
        if (digit < 0 || digit >= r19) {
            return null;
        }
        long j = -digit;
        long j2 = r19;
        long j3 = Long.MIN_VALUE / j2;
        while (r4 < str.length()) {
            int r13 = r4 + 1;
            int digit2 = AsciiDigits.digit(str.charAt(r4));
            if (digit2 < 0 || digit2 >= r19 || j < j3) {
                return null;
            }
            long j4 = j * j2;
            long j5 = digit2;
            if (j4 < j5 - Long.MIN_VALUE) {
                return null;
            }
            j = j4 - j5;
            r4 = r13;
        }
        if (r2 != 0) {
            return Long.valueOf(j);
        }
        if (j == Long.MIN_VALUE) {
            return null;
        }
        return Long.valueOf(-j);
    }

    /* loaded from: classes3.dex */
    private static final class LongConverter extends Converter<String, Long> implements Serializable {
        static final LongConverter INSTANCE = new LongConverter();
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Longs.stringConverter()";
        }

        private LongConverter() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public Long doForward(String str) {
            return Long.decode(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public String doBackward(Long l) {
            return l.toString();
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] ensureCapacity(long[] jArr, int r5, int r6) {
        Preconditions.checkArgument(r5 >= 0, "Invalid minLength: %s", r5);
        Preconditions.checkArgument(r6 >= 0, "Invalid padding: %s", r6);
        return jArr.length < r5 ? Arrays.copyOf(jArr, r5 + r6) : jArr;
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 10);
        sb.append(jArr[0]);
        for (int r1 = 1; r1 < jArr.length; r1++) {
            sb.append(str);
            sb.append(jArr[r1]);
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    private enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Longs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int r1 = 0; r1 < min; r1++) {
                int compare = Longs.compare(jArr[r1], jArr2[r1]);
                if (compare != 0) {
                    return compare;
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    public static void sortDescending(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sortDescending(jArr, 0, jArr.length);
    }

    public static void sortDescending(long[] jArr, int r2, int r3) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(r2, r3, jArr.length);
        Arrays.sort(jArr, r2, r3);
        reverse(jArr, r2, r3);
    }

    public static void reverse(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        reverse(jArr, 0, jArr.length);
    }

    public static void reverse(long[] jArr, int r5, int r6) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(r5, r6, jArr.length);
        for (int r62 = r6 - 1; r5 < r62; r62--) {
            long j = jArr[r5];
            jArr[r5] = jArr[r62];
            jArr[r62] = j;
            r5++;
        }
    }

    public static long[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList) collection).toLongArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        long[] jArr = new long[length];
        for (int r2 = 0; r2 < length; r2++) {
            jArr[r2] = ((Number) Preconditions.checkNotNull(array[r2])).longValue();
        }
        return jArr;
    }

    public static List<Long> asList(long... jArr) {
        if (jArr.length == 0) {
            return Collections.emptyList();
        }
        return new LongArrayAsList(jArr);
    }

    /* loaded from: classes3.dex */
    private static class LongArrayAsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final long[] array;
        final int end;
        final int start;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        LongArrayAsList(long[] jArr) {
            this(jArr, 0, jArr.length);
        }

        LongArrayAsList(long[] jArr, int r2, int r3) {
            this.array = jArr;
            this.start = r2;
            this.end = r3;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int r4) {
            Preconditions.checkElementIndex(r4, size());
            return Long.valueOf(this.array[this.start + r4]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Long) && Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            int indexOf;
            if (!(obj instanceof Long) || (indexOf = Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return indexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Long) || (lastIndexOf = Longs.lastIndexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return lastIndexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Long set(int r5, Long l) {
            Preconditions.checkElementIndex(r5, size());
            long[] jArr = this.array;
            int r1 = this.start;
            long j = jArr[r1 + r5];
            jArr[r1 + r5] = ((Long) Preconditions.checkNotNull(l)).longValue();
            return Long.valueOf(j);
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Long> subList(int r4, int r5) {
            Preconditions.checkPositionIndexes(r4, r5, size());
            if (r4 == r5) {
                return Collections.emptyList();
            }
            long[] jArr = this.array;
            int r2 = this.start;
            return new LongArrayAsList(jArr, r4 + r2, r2 + r5);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof LongArrayAsList) {
                LongArrayAsList longArrayAsList = (LongArrayAsList) obj;
                int size = size();
                if (longArrayAsList.size() != size) {
                    return false;
                }
                for (int r2 = 0; r2 < size; r2++) {
                    if (this.array[this.start + r2] != longArrayAsList.array[longArrayAsList.start + r2]) {
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
                r1 = (r1 * 31) + Longs.hashCode(this.array[r0]);
            }
            return r1;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 10);
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

        long[] toLongArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }
}
