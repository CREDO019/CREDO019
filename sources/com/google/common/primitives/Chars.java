package com.google.common.primitives;

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
import kotlin.jvm.internal.CharCompanionObject;
import okhttp3.internal.p026ws.WebSocketProtocol;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Chars {
    public static final int BYTES = 2;

    public static int compare(char c, char c2) {
        return c - c2;
    }

    public static char fromBytes(byte b, byte b2) {
        return (char) ((b << 8) | (b2 & 255));
    }

    public static int hashCode(char c) {
        return c;
    }

    public static char saturatedCast(long j) {
        if (j > WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            return CharCompanionObject.MAX_VALUE;
        }
        if (j < 0) {
            return (char) 0;
        }
        return (char) j;
    }

    public static byte[] toByteArray(char c) {
        return new byte[]{(byte) (c >> '\b'), (byte) c};
    }

    private Chars() {
    }

    public static char checkedCast(long j) {
        char c = (char) j;
        Preconditions.checkArgument(((long) c) == j, "Out of range: %s", j);
        return c;
    }

    public static boolean contains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(char[] cArr, char c) {
        return indexOf(cArr, c, 0, cArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int indexOf(char[] cArr, char c, int r3, int r4) {
        while (r3 < r4) {
            if (cArr[r3] == c) {
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
    public static int indexOf(char[] r5, char[] r6) {
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
            char r3 = r5[r3]
            char r4 = r6[r2]
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.Chars.indexOf(char[], char[]):int");
    }

    public static int lastIndexOf(char[] cArr, char c) {
        return lastIndexOf(cArr, c, 0, cArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int lastIndexOf(char[] cArr, char c, int r3, int r4) {
        for (int r42 = r4 - 1; r42 >= r3; r42--) {
            if (cArr[r42] == c) {
                return r42;
            }
        }
        return -1;
    }

    public static char min(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c = cArr[0];
        for (int r2 = 1; r2 < cArr.length; r2++) {
            if (cArr[r2] < c) {
                c = cArr[r2];
            }
        }
        return c;
    }

    public static char max(char... cArr) {
        Preconditions.checkArgument(cArr.length > 0);
        char c = cArr[0];
        for (int r2 = 1; r2 < cArr.length; r2++) {
            if (cArr[r2] > c) {
                c = cArr[r2];
            }
        }
        return c;
    }

    public static char constrainToRange(char c, char c2, char c3) {
        Preconditions.checkArgument(c2 <= c3, "min (%s) must be less than or equal to max (%s)", c2, c3);
        return c < c2 ? c2 : c < c3 ? c : c3;
    }

    public static char[] concat(char[]... cArr) {
        int r3 = 0;
        for (char[] cArr2 : cArr) {
            r3 += cArr2.length;
        }
        char[] cArr3 = new char[r3];
        int r4 = 0;
        for (char[] cArr4 : cArr) {
            System.arraycopy(cArr4, 0, cArr3, r4, cArr4.length);
            r4 += cArr4.length;
        }
        return cArr3;
    }

    public static char fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 2, "array too small: %s < %s", bArr.length, 2);
        return fromBytes(bArr[0], bArr[1]);
    }

    public static char[] ensureCapacity(char[] cArr, int r5, int r6) {
        Preconditions.checkArgument(r5 >= 0, "Invalid minLength: %s", r5);
        Preconditions.checkArgument(r6 >= 0, "Invalid padding: %s", r6);
        return cArr.length < r5 ? Arrays.copyOf(cArr, r5 + r6) : cArr;
    }

    public static String join(String str, char... cArr) {
        Preconditions.checkNotNull(str);
        int length = cArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((str.length() * (length - 1)) + length);
        sb.append(cArr[0]);
        for (int r2 = 1; r2 < length; r2++) {
            sb.append(str);
            sb.append(cArr[r2]);
        }
        return sb.toString();
    }

    public static Comparator<char[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes3.dex */
    private enum LexicographicalComparator implements Comparator<char[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Chars.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(char[] cArr, char[] cArr2) {
            int min = Math.min(cArr.length, cArr2.length);
            for (int r1 = 0; r1 < min; r1++) {
                int compare = Chars.compare(cArr[r1], cArr2[r1]);
                if (compare != 0) {
                    return compare;
                }
            }
            return cArr.length - cArr2.length;
        }
    }

    public static char[] toArray(Collection<Character> collection) {
        if (collection instanceof CharArrayAsList) {
            return ((CharArrayAsList) collection).toCharArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        char[] cArr = new char[length];
        for (int r2 = 0; r2 < length; r2++) {
            cArr[r2] = ((Character) Preconditions.checkNotNull(array[r2])).charValue();
        }
        return cArr;
    }

    public static void sortDescending(char[] cArr) {
        Preconditions.checkNotNull(cArr);
        sortDescending(cArr, 0, cArr.length);
    }

    public static void sortDescending(char[] cArr, int r2, int r3) {
        Preconditions.checkNotNull(cArr);
        Preconditions.checkPositionIndexes(r2, r3, cArr.length);
        Arrays.sort(cArr, r2, r3);
        reverse(cArr, r2, r3);
    }

    public static void reverse(char[] cArr) {
        Preconditions.checkNotNull(cArr);
        reverse(cArr, 0, cArr.length);
    }

    public static void reverse(char[] cArr, int r3, int r4) {
        Preconditions.checkNotNull(cArr);
        Preconditions.checkPositionIndexes(r3, r4, cArr.length);
        for (int r42 = r4 - 1; r3 < r42; r42--) {
            char c = cArr[r3];
            cArr[r3] = cArr[r42];
            cArr[r42] = c;
            r3++;
        }
    }

    public static List<Character> asList(char... cArr) {
        if (cArr.length == 0) {
            return Collections.emptyList();
        }
        return new CharArrayAsList(cArr);
    }

    /* loaded from: classes3.dex */
    private static class CharArrayAsList extends AbstractList<Character> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final char[] array;
        final int end;
        final int start;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        CharArrayAsList(char[] cArr) {
            this(cArr, 0, cArr.length);
        }

        CharArrayAsList(char[] cArr, int r2, int r3) {
            this.array = cArr;
            this.start = r2;
            this.end = r3;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.end - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int r3) {
            Preconditions.checkElementIndex(r3, size());
            return Character.valueOf(this.array[this.start + r3]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Character) && Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end) != -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            int indexOf;
            if (!(obj instanceof Character) || (indexOf = Chars.indexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return indexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            int lastIndexOf;
            if (!(obj instanceof Character) || (lastIndexOf = Chars.lastIndexOf(this.array, ((Character) obj).charValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return lastIndexOf - this.start;
        }

        @Override // java.util.AbstractList, java.util.List
        public Character set(int r4, Character ch) {
            Preconditions.checkElementIndex(r4, size());
            char[] cArr = this.array;
            int r1 = this.start;
            char c = cArr[r1 + r4];
            cArr[r1 + r4] = ((Character) Preconditions.checkNotNull(ch)).charValue();
            return Character.valueOf(c);
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Character> subList(int r4, int r5) {
            Preconditions.checkPositionIndexes(r4, r5, size());
            if (r4 == r5) {
                return Collections.emptyList();
            }
            char[] cArr = this.array;
            int r2 = this.start;
            return new CharArrayAsList(cArr, r4 + r2, r2 + r5);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof CharArrayAsList) {
                CharArrayAsList charArrayAsList = (CharArrayAsList) obj;
                int size = size();
                if (charArrayAsList.size() != size) {
                    return false;
                }
                for (int r2 = 0; r2 < size; r2++) {
                    if (this.array[this.start + r2] != charArrayAsList.array[charArrayAsList.start + r2]) {
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
                r1 = (r1 * 31) + Chars.hashCode(this.array[r0]);
            }
            return r1;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 3);
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

        char[] toCharArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }
    }
}
