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
import kotlin.jvm.internal.ShortCompanionObject;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Shorts extends ShortsMethodsForWeb {
    public static final int BYTES = 2;
    public static final short MAX_POWER_OF_TWO = 16384;

    public static int compare(short s, short s2) {
        return s - s2;
    }

    public static short fromBytes(byte b, byte b2) {
        return (short) ((b << 8) | (b2 & 255));
    }

    public static int hashCode(short s) {
        return s;
    }

    public static short saturatedCast(long j) {
        return j > 32767 ? ShortCompanionObject.MAX_VALUE : j < -32768 ? ShortCompanionObject.MIN_VALUE : (short) j;
    }

    public static byte[] toByteArray(short s) {
        return new byte[]{(byte) (s >> 8), (byte) s};
    }

    private Shorts() {
    }

    public static short checkedCast(long j) {
        short s = (short) j;
        Preconditions.checkArgument(((long) s) == j, "Out of range: %s", j);
        return s;
    }

    public static boolean contains(short[] sArr, short s) {
        for (short s2 : sArr) {
            if (s2 == s) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(short[] sArr, short s) {
        return indexOf(sArr, s, 0, sArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(short[] sArr, short s, int r3, int r4) {
        while (r3 < r4) {
            if (sArr[r3] == s) {
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
    public static int indexOf(short[] r5, short[] r6) {
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
            short r3 = r5[r3]
            short r4 = r6[r2]
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Shorts.indexOf(short[], short[]):int");
    }

    public static int lastIndexOf(short[] sArr, short s) {
        return lastIndexOf(sArr, s, 0, sArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(short[] sArr, short s, int r3, int r4) {
        for (int r42 = r4 - 1; r42 >= r3; r42--) {
            if (sArr[r42] == s) {
                return r42;
            }
        }
        return -1;
    }

    public static short min(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s = sArr[0];
        for (int r2 = 1; r2 < sArr.length; r2++) {
            if (sArr[r2] < s) {
                s = sArr[r2];
            }
        }
        return s;
    }

    public static short max(short... sArr) {
        Preconditions.checkArgument(sArr.length > 0);
        short s = sArr[0];
        for (int r2 = 1; r2 < sArr.length; r2++) {
            if (sArr[r2] > s) {
                s = sArr[r2];
            }
        }
        return s;
    }

    public static short constrainToRange(short s, short s2, short s3) {
        Preconditions.checkArgument(s2 <= s3, "min (%s) must be less than or equal to max (%s)", (int) s2, (int) s3);
        return s < s2 ? s2 : s < s3 ? s : s3;
    }

    public static short[] concat(short[]... sArr) {
        int r3 = 0;
        for (short[] sArr2 : sArr) {
            r3 += sArr2.length;
        }
        short[] sArr3 = new short[r3];
        int r4 = 0;
        for (short[] sArr4 : sArr) {
            System.arraycopy(sArr4, 0, sArr3, r4, sArr4.length);
            r4 += sArr4.length;
        }
        return sArr3;
    }

    public static short fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    /* loaded from: classes3.dex */
    private static final class ShortConverter extends Converter<String, Short> implements Serializable {
        static final ShortConverter INSTANCE = new ShortConverter();
        private static final long serialVersionUID = 1;

        public String toString() {
            return "Shorts.stringConverter()";
        }

        private ShortConverter() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public Short doForward(String str) {
            return Short.decode(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public String doBackward(Short sh) {
            return sh.toString();
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    public static Converter<String, Short> stringConverter() {
        return ShortConverter.INSTANCE;
    }

    public static short[] ensureCapacity(short[] sArr, int r5, int r6) {
        Preconditions.checkArgument(r5 >= 0, "Invalid minLength: %s", r5);
        Preconditions.checkArgument(r6 >= 0, "Invalid padding: %s", r6);
        return sArr.length < r5 ? Arrays.copyOf(sArr, r5 + r6) : sArr;
    }

    public static String join(String str, short... sArr) {
        Preconditions.checkNotNull(str);
        if (sArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(sArr.length * 6);
        sb.append((int) sArr[0]);
        for (int r1 = 1; r1 < sArr.length; r1++) {
            sb.append(str);
            sb.append((int) sArr[r1]);
        }
        return sb.toString();
    }

    public static Comparator<short[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    private enum LexicographicalComparator implements Comparator<short[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Shorts.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(short[] sArr, short[] sArr2) {
            int min = Math.min(sArr.length, sArr2.length);
            for (int r1 = 0; r1 < min; r1++) {
                int compare = Shorts.compare(sArr[r1], sArr2[r1]);
                if (compare != 0) {
                    return compare;
                }
            }
            return sArr.length - sArr2.length;
        }
    }

    public static void sortDescending(short[] sArr) {
        Preconditions.checkNotNull(sArr);
        sortDescending(sArr, 0, sArr.length);
    }

    public static void sortDescending(short[] sArr, int r2, int r3) {
        Preconditions.checkNotNull(sArr);
        Preconditions.checkPositionIndexes(r2, r3, sArr.length);
        Arrays.sort(sArr, r2, r3);
        reverse(sArr, r2, r3);
    }

    public static void reverse(short[] sArr) {
        Preconditions.checkNotNull(sArr);
        reverse(sArr, 0, sArr.length);
    }

    public static void reverse(short[] sArr, int r3, int r4) {
        Preconditions.checkNotNull(sArr);
        Preconditions.checkPositionIndexes(r3, r4, sArr.length);
        for (int r42 = r4 - 1; r3 < r42; r42--) {
            short s = sArr[r3];
            sArr[r3] = sArr[r42];
            sArr[r42] = s;
            r3++;
        }
    }

    public static short[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof ShortArrayAsList) {
            return ((ShortArrayAsList) collection).toShortArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        short[] sArr = new short[length];
        for (int r2 = 0; r2 < length; r2++) {
            sArr[r2] = ((Number) Preconditions.checkNotNull(array[r2])).shortValue();
        }
        return sArr;
    }

    public static List<Short> asList(short... sArr) {
        if (sArr.length == 0) {
            return Collections.emptyList();
        }
        return new ShortArrayAsList(sArr);
    }

    /* loaded from: classes3.dex */
    private static class ShortArrayAsList extends AbstractList<Short> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final short[] array;
        final int end;
        final int start;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        ShortArrayAsList(short[] sArr) {
            this(sArr, 0, sArr.length);
        }

        ShortArrayAsList(short[] sArr, int r2, int r3) {
            this.array = sArr;
            this.start = r2;
            this.end = r3;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Short get(int r3) {
            Preconditions.checkElementIndex(r3, size());
            return Short.valueOf(this.array[this.start + r3]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Short) && Shorts.indexOf(this.array, ((Short) obj).shortValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            int indexOf;
            if (!(obj instanceof Short) || (indexOf = Shorts.indexOf(this.array, ((Short) obj).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return indexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Short) || (lastIndexOf = Shorts.lastIndexOf(this.array, ((Short) obj).shortValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return lastIndexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Short set(int r4, Short sh) {
            Preconditions.checkElementIndex(r4, size());
            short[] sArr = this.array;
            int r1 = this.start;
            short s = sArr[r1 + r4];
            sArr[r1 + r4] = ((Short) Preconditions.checkNotNull(sh)).shortValue();
            return Short.valueOf(s);
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Short> subList(int r4, int r5) {
            Preconditions.checkPositionIndexes(r4, r5, size());
            if (r4 == r5) {
                return Collections.emptyList();
            }
            short[] sArr = this.array;
            int r2 = this.start;
            return new ShortArrayAsList(sArr, r4 + r2, r2 + r5);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ShortArrayAsList) {
                ShortArrayAsList shortArrayAsList = (ShortArrayAsList) obj;
                int size = size();
                if (shortArrayAsList.size() != size) {
                    return false;
                }
                for (int r2 = 0; r2 < size; r2++) {
                    if (this.array[this.start + r2] != shortArrayAsList.array[shortArrayAsList.start + r2]) {
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
                r1 = (r1 * 31) + Shorts.hashCode(this.array[r0]);
            }
            return r1;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 6);
            sb.append('[');
            sb.append((int) this.array[this.start]);
            int r1 = this.start;
            while (true) {
                r1++;
                if (r1 < this.end) {
                    sb.append(", ");
                    sb.append((int) this.array[r1]);
                } else {
                    sb.append(']');
                    return sb.toString();
                }
            }
        }

        short[] toShortArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }
}
