package org.bouncycastle.util;

import java.math.BigInteger;
import java.util.NoSuchElementException;

/* loaded from: classes4.dex */
public final class Arrays {

    /* loaded from: classes4.dex */
    public static class Iterator<T> implements java.util.Iterator<T> {
        private final T[] dataArray;
        private int position = 0;

        public Iterator(T[] tArr) {
            this.dataArray = tArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.position < this.dataArray.length;
        }

        @Override // java.util.Iterator
        public T next() {
            int r0 = this.position;
            T[] tArr = this.dataArray;
            if (r0 != tArr.length) {
                this.position = r0 + 1;
                return tArr[r0];
            }
            throw new NoSuchElementException("Out of elements: " + this.position);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove element from an Array.");
        }
    }

    private Arrays() {
    }

    public static byte[] append(byte[] bArr, byte b) {
        if (bArr == null) {
            return new byte[]{b};
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 1];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        bArr2[length] = b;
        return bArr2;
    }

    public static int[] append(int[] r3, int r4) {
        if (r3 == null) {
            return new int[]{r4};
        }
        int length = r3.length;
        int[] r2 = new int[length + 1];
        System.arraycopy(r3, 0, r2, 0, length);
        r2[length] = r4;
        return r2;
    }

    public static String[] append(String[] strArr, String str) {
        if (strArr == null) {
            return new String[]{str};
        }
        int length = strArr.length;
        String[] strArr2 = new String[length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        strArr2[length] = str;
        return strArr2;
    }

    public static short[] append(short[] sArr, short s) {
        if (sArr == null) {
            return new short[]{s};
        }
        int length = sArr.length;
        short[] sArr2 = new short[length + 1];
        System.arraycopy(sArr, 0, sArr2, 0, length);
        sArr2[length] = s;
        return sArr2;
    }

    public static boolean areAllZeroes(byte[] bArr, int r5, int r6) {
        int r2 = 0;
        for (int r1 = 0; r1 < r6; r1++) {
            r2 |= bArr[r5 + r1];
        }
        return r2 == 0;
    }

    public static boolean areEqual(byte[] bArr, int r4, int r5, byte[] bArr2, int r7, int r8) {
        int r52 = r5 - r4;
        if (r52 != r8 - r7) {
            return false;
        }
        for (int r82 = 0; r82 < r52; r82++) {
            if (bArr[r4 + r82] != bArr2[r7 + r82]) {
                return false;
            }
        }
        return true;
    }

    public static boolean areEqual(byte[] bArr, byte[] bArr2) {
        return java.util.Arrays.equals(bArr, bArr2);
    }

    public static boolean areEqual(char[] cArr, char[] cArr2) {
        return java.util.Arrays.equals(cArr, cArr2);
    }

    public static boolean areEqual(int[] r0, int[] r1) {
        return java.util.Arrays.equals(r0, r1);
    }

    public static boolean areEqual(long[] jArr, long[] jArr2) {
        return java.util.Arrays.equals(jArr, jArr2);
    }

    public static boolean areEqual(Object[] objArr, Object[] objArr2) {
        return java.util.Arrays.equals(objArr, objArr2);
    }

    public static boolean areEqual(short[] sArr, short[] sArr2) {
        return java.util.Arrays.equals(sArr, sArr2);
    }

    public static boolean areEqual(boolean[] zArr, boolean[] zArr2) {
        return java.util.Arrays.equals(zArr, zArr2);
    }

    public static void clear(byte[] bArr) {
        if (bArr != null) {
            java.util.Arrays.fill(bArr, (byte) 0);
        }
    }

    public static void clear(int[] r1) {
        if (r1 != null) {
            java.util.Arrays.fill(r1, 0);
        }
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public static byte[] clone(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return null;
        }
        if (bArr2 == null || bArr2.length != bArr.length) {
            return clone(bArr);
        }
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    public static char[] clone(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return (char[]) cArr.clone();
    }

    public static int[] clone(int[] r0) {
        if (r0 == null) {
            return null;
        }
        return (int[]) r0.clone();
    }

    public static long[] clone(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        return (long[]) jArr.clone();
    }

    public static long[] clone(long[] jArr, long[] jArr2) {
        if (jArr == null) {
            return null;
        }
        if (jArr2 == null || jArr2.length != jArr.length) {
            return clone(jArr);
        }
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        return jArr2;
    }

    public static BigInteger[] clone(BigInteger[] bigIntegerArr) {
        if (bigIntegerArr == null) {
            return null;
        }
        return (BigInteger[]) bigIntegerArr.clone();
    }

    public static short[] clone(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        return (short[]) sArr.clone();
    }

    public static boolean[] clone(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        return (boolean[]) zArr.clone();
    }

    public static byte[][] clone(byte[][] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[][] bArr2 = new byte[length];
        for (int r2 = 0; r2 != length; r2++) {
            bArr2[r2] = clone(bArr[r2]);
        }
        return bArr2;
    }

    public static byte[][][] clone(byte[][][] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[][][] bArr2 = new byte[length][];
        for (int r2 = 0; r2 != length; r2++) {
            bArr2[r2] = clone(bArr[r2]);
        }
        return bArr2;
    }

    public static int compareUnsigned(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return 0;
        }
        if (bArr == null) {
            return -1;
        }
        if (bArr2 == null) {
            return 1;
        }
        int min = Math.min(bArr.length, bArr2.length);
        for (int r4 = 0; r4 < min; r4++) {
            int r5 = bArr[r4] & 255;
            int r6 = bArr2[r4] & 255;
            if (r5 < r6) {
                return -1;
            }
            if (r5 > r6) {
                return 1;
            }
        }
        if (bArr.length < bArr2.length) {
            return -1;
        }
        return bArr.length > bArr2.length ? 1 : 0;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return clone(bArr2);
        }
        if (bArr2 == null) {
            return clone(bArr);
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            return concatenate(bArr2, bArr3);
        }
        if (bArr2 == null) {
            return concatenate(bArr, bArr3);
        }
        if (bArr3 == null) {
            return concatenate(bArr, bArr2);
        }
        byte[] bArr4 = new byte[bArr.length + bArr2.length + bArr3.length];
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        int length = bArr.length + 0;
        System.arraycopy(bArr2, 0, bArr4, length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, length + bArr2.length, bArr3.length);
        return bArr4;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr == null) {
            return concatenate(bArr2, bArr3, bArr4);
        }
        if (bArr2 == null) {
            return concatenate(bArr, bArr3, bArr4);
        }
        if (bArr3 == null) {
            return concatenate(bArr, bArr2, bArr4);
        }
        if (bArr4 == null) {
            return concatenate(bArr, bArr2, bArr3);
        }
        byte[] bArr5 = new byte[bArr.length + bArr2.length + bArr3.length + bArr4.length];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        int length = bArr.length + 0;
        System.arraycopy(bArr2, 0, bArr5, length, bArr2.length);
        int length2 = length + bArr2.length;
        System.arraycopy(bArr3, 0, bArr5, length2, bArr3.length);
        System.arraycopy(bArr4, 0, bArr5, length2 + bArr3.length, bArr4.length);
        return bArr5;
    }

    public static byte[] concatenate(byte[][] bArr) {
        int r2 = 0;
        for (int r1 = 0; r1 != bArr.length; r1++) {
            r2 += bArr[r1].length;
        }
        byte[] bArr2 = new byte[r2];
        int r3 = 0;
        for (int r22 = 0; r22 != bArr.length; r22++) {
            System.arraycopy(bArr[r22], 0, bArr2, r3, bArr[r22].length);
            r3 += bArr[r22].length;
        }
        return bArr2;
    }

    public static int[] concatenate(int[] r3, int[] r4) {
        if (r3 == null) {
            return clone(r4);
        }
        if (r4 == null) {
            return clone(r3);
        }
        int[] r0 = new int[r3.length + r4.length];
        System.arraycopy(r3, 0, r0, 0, r3.length);
        System.arraycopy(r4, 0, r0, r3.length, r4.length);
        return r0;
    }

    public static short[] concatenate(short[] sArr, short[] sArr2) {
        if (sArr == null) {
            return clone(sArr2);
        }
        if (sArr2 == null) {
            return clone(sArr);
        }
        short[] sArr3 = new short[sArr.length + sArr2.length];
        System.arraycopy(sArr, 0, sArr3, 0, sArr.length);
        System.arraycopy(sArr2, 0, sArr3, sArr.length, sArr2.length);
        return sArr3;
    }

    public static boolean constantTimeAreEqual(int r5, byte[] bArr, int r7, byte[] bArr2, int r9) {
        java.util.Objects.requireNonNull(bArr, "'a' cannot be null");
        java.util.Objects.requireNonNull(bArr2, "'b' cannot be null");
        if (r5 >= 0) {
            if (r7 <= bArr.length - r5) {
                if (r9 <= bArr2.length - r5) {
                    int r2 = 0;
                    for (int r1 = 0; r1 < r5; r1++) {
                        r2 |= bArr[r7 + r1] ^ bArr2[r9 + r1];
                    }
                    return r2 == 0;
                }
                throw new IndexOutOfBoundsException("'bOff' value invalid for specified length");
            }
            throw new IndexOutOfBoundsException("'aOff' value invalid for specified length");
        }
        throw new IllegalArgumentException("'len' cannot be negative");
    }

    public static boolean constantTimeAreEqual(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return false;
        }
        if (bArr == bArr2) {
            return true;
        }
        int length = bArr.length < bArr2.length ? bArr.length : bArr2.length;
        int length2 = bArr.length ^ bArr2.length;
        for (int r4 = 0; r4 != length; r4++) {
            length2 |= bArr[r4] ^ bArr2[r4];
        }
        while (length < bArr2.length) {
            length2 |= bArr2[length] ^ (~bArr2[length]);
            length++;
        }
        return length2 == 0;
    }

    public static boolean contains(byte[] bArr, byte b) {
        for (byte b2 : bArr) {
            if (b2 == b) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int[] r3, int r4) {
        for (int r2 : r3) {
            if (r2 == r4) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(long[] jArr, long j) {
        for (long j2 : jArr) {
            if (j2 == j) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(short[] sArr, short s) {
        for (short s2 : sArr) {
            if (s2 == s) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(boolean[] zArr, boolean z) {
        for (boolean z2 : zArr) {
            if (z2 == z) {
                return true;
            }
        }
        return false;
    }

    public static byte[] copyOf(byte[] bArr, int r3) {
        byte[] bArr2 = new byte[r3];
        System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, r3));
        return bArr2;
    }

    public static char[] copyOf(char[] cArr, int r3) {
        char[] cArr2 = new char[r3];
        System.arraycopy(cArr, 0, cArr2, 0, Math.min(cArr.length, r3));
        return cArr2;
    }

    public static int[] copyOf(int[] r2, int r3) {
        int[] r0 = new int[r3];
        System.arraycopy(r2, 0, r0, 0, Math.min(r2.length, r3));
        return r0;
    }

    public static long[] copyOf(long[] jArr, int r3) {
        long[] jArr2 = new long[r3];
        System.arraycopy(jArr, 0, jArr2, 0, Math.min(jArr.length, r3));
        return jArr2;
    }

    public static BigInteger[] copyOf(BigInteger[] bigIntegerArr, int r3) {
        BigInteger[] bigIntegerArr2 = new BigInteger[r3];
        System.arraycopy(bigIntegerArr, 0, bigIntegerArr2, 0, Math.min(bigIntegerArr.length, r3));
        return bigIntegerArr2;
    }

    public static short[] copyOf(short[] sArr, int r3) {
        short[] sArr2 = new short[r3];
        System.arraycopy(sArr, 0, sArr2, 0, Math.min(sArr.length, r3));
        return sArr2;
    }

    public static boolean[] copyOf(boolean[] zArr, int r3) {
        boolean[] zArr2 = new boolean[r3];
        System.arraycopy(zArr, 0, zArr2, 0, Math.min(zArr.length, r3));
        return zArr2;
    }

    public static byte[] copyOfRange(byte[] bArr, int r3, int r4) {
        int length = getLength(r3, r4);
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, r3, bArr2, 0, Math.min(bArr.length - r3, length));
        return bArr2;
    }

    public static char[] copyOfRange(char[] cArr, int r3, int r4) {
        int length = getLength(r3, r4);
        char[] cArr2 = new char[length];
        System.arraycopy(cArr, r3, cArr2, 0, Math.min(cArr.length - r3, length));
        return cArr2;
    }

    public static int[] copyOfRange(int[] r2, int r3, int r4) {
        int length = getLength(r3, r4);
        int[] r0 = new int[length];
        System.arraycopy(r2, r3, r0, 0, Math.min(r2.length - r3, length));
        return r0;
    }

    public static long[] copyOfRange(long[] jArr, int r3, int r4) {
        int length = getLength(r3, r4);
        long[] jArr2 = new long[length];
        System.arraycopy(jArr, r3, jArr2, 0, Math.min(jArr.length - r3, length));
        return jArr2;
    }

    public static BigInteger[] copyOfRange(BigInteger[] bigIntegerArr, int r3, int r4) {
        int length = getLength(r3, r4);
        BigInteger[] bigIntegerArr2 = new BigInteger[length];
        System.arraycopy(bigIntegerArr, r3, bigIntegerArr2, 0, Math.min(bigIntegerArr.length - r3, length));
        return bigIntegerArr2;
    }

    public static short[] copyOfRange(short[] sArr, int r3, int r4) {
        int length = getLength(r3, r4);
        short[] sArr2 = new short[length];
        System.arraycopy(sArr, r3, sArr2, 0, Math.min(sArr.length - r3, length));
        return sArr2;
    }

    public static boolean[] copyOfRange(boolean[] zArr, int r3, int r4) {
        int length = getLength(r3, r4);
        boolean[] zArr2 = new boolean[length];
        System.arraycopy(zArr, r3, zArr2, 0, Math.min(zArr.length - r3, length));
        return zArr2;
    }

    public static void fill(byte[] bArr, byte b) {
        java.util.Arrays.fill(bArr, b);
    }

    public static void fill(byte[] bArr, int r1, int r2, byte b) {
        java.util.Arrays.fill(bArr, r1, r2, b);
    }

    public static void fill(char[] cArr, char c) {
        java.util.Arrays.fill(cArr, c);
    }

    public static void fill(char[] cArr, int r1, int r2, char c) {
        java.util.Arrays.fill(cArr, r1, r2, c);
    }

    public static void fill(int[] r0, int r1) {
        java.util.Arrays.fill(r0, r1);
    }

    public static void fill(int[] r0, int r1, int r2, int r3) {
        java.util.Arrays.fill(r0, r1, r2, r3);
    }

    public static void fill(long[] jArr, int r1, int r2, long j) {
        java.util.Arrays.fill(jArr, r1, r2, j);
    }

    public static void fill(long[] jArr, long j) {
        java.util.Arrays.fill(jArr, j);
    }

    public static void fill(Object[] objArr, int r1, int r2, Object obj) {
        java.util.Arrays.fill(objArr, r1, r2, obj);
    }

    public static void fill(Object[] objArr, Object obj) {
        java.util.Arrays.fill(objArr, obj);
    }

    public static void fill(short[] sArr, int r1, int r2, short s) {
        java.util.Arrays.fill(sArr, r1, r2, s);
    }

    public static void fill(short[] sArr, short s) {
        java.util.Arrays.fill(sArr, s);
    }

    public static void fill(boolean[] zArr, int r1, int r2, boolean z) {
        java.util.Arrays.fill(zArr, r1, r2, z);
    }

    public static void fill(boolean[] zArr, boolean z) {
        java.util.Arrays.fill(zArr, z);
    }

    private static int getLength(int r1, int r2) {
        int r0 = r2 - r1;
        if (r0 >= 0) {
            return r0;
        }
        StringBuffer stringBuffer = new StringBuffer(r1);
        stringBuffer.append(" > ");
        stringBuffer.append(r2);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static int hashCode(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length;
        int r1 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return r1;
            }
            r1 = (r1 * 257) ^ bArr[length];
        }
    }

    public static int hashCode(byte[] bArr, int r3, int r4) {
        if (bArr == null) {
            return 0;
        }
        int r0 = r4 + 1;
        while (true) {
            r4--;
            if (r4 < 0) {
                return r0;
            }
            r0 = (r0 * 257) ^ bArr[r3 + r4];
        }
    }

    public static int hashCode(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int r1 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return r1;
            }
            r1 = (r1 * 257) ^ cArr[length];
        }
    }

    public static int hashCode(int[] r3) {
        if (r3 == null) {
            return 0;
        }
        int length = r3.length;
        int r1 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return r1;
            }
            r1 = (r1 * 257) ^ r3[length];
        }
    }

    public static int hashCode(int[] r2, int r3, int r4) {
        if (r2 == null) {
            return 0;
        }
        int r0 = r4 + 1;
        while (true) {
            r4--;
            if (r4 < 0) {
                return r0;
            }
            r0 = (r0 * 257) ^ r2[r3 + r4];
        }
    }

    public static int hashCode(long[] jArr) {
        if (jArr == null) {
            return 0;
        }
        int length = jArr.length;
        int r1 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return r1;
            }
            long j = jArr[length];
            r1 = (((r1 * 257) ^ ((int) j)) * 257) ^ ((int) (j >>> 32));
        }
    }

    public static int hashCode(long[] jArr, int r5, int r6) {
        if (jArr == null) {
            return 0;
        }
        int r0 = r6 + 1;
        while (true) {
            r6--;
            if (r6 < 0) {
                return r0;
            }
            long j = jArr[r5 + r6];
            r0 = (((r0 * 257) ^ ((int) j)) * 257) ^ ((int) (j >>> 32));
        }
    }

    public static int hashCode(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int length = objArr.length;
        int r1 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return r1;
            }
            r1 = (r1 * 257) ^ Objects.hashCode(objArr[length]);
        }
    }

    public static int hashCode(short[] sArr) {
        if (sArr == null) {
            return 0;
        }
        int length = sArr.length;
        int r1 = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return r1;
            }
            r1 = (r1 * 257) ^ (sArr[length] & 255);
        }
    }

    public static int hashCode(int[][] r3) {
        int r1 = 0;
        for (int r0 = 0; r0 != r3.length; r0++) {
            r1 = (r1 * 257) + hashCode(r3[r0]);
        }
        return r1;
    }

    public static int hashCode(short[][] sArr) {
        int r1 = 0;
        for (int r0 = 0; r0 != sArr.length; r0++) {
            r1 = (r1 * 257) + hashCode(sArr[r0]);
        }
        return r1;
    }

    public static int hashCode(short[][][] sArr) {
        int r1 = 0;
        for (int r0 = 0; r0 != sArr.length; r0++) {
            r1 = (r1 * 257) + hashCode(sArr[r0]);
        }
        return r1;
    }

    public static boolean isNullOrContainsNull(Object[] objArr) {
        if (objArr == null) {
            return true;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNullOrEmpty(byte[] bArr) {
        return bArr == null || bArr.length < 1;
    }

    public static boolean isNullOrEmpty(int[] r1) {
        return r1 == null || r1.length < 1;
    }

    public static boolean isNullOrEmpty(Object[] objArr) {
        return objArr == null || objArr.length < 1;
    }

    public static byte[] prepend(byte[] bArr, byte b) {
        if (bArr == null) {
            return new byte[]{b};
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 1];
        System.arraycopy(bArr, 0, bArr2, 1, length);
        bArr2[0] = b;
        return bArr2;
    }

    public static int[] prepend(int[] r4, int r5) {
        if (r4 == null) {
            return new int[]{r5};
        }
        int length = r4.length;
        int[] r3 = new int[length + 1];
        System.arraycopy(r4, 0, r3, 1, length);
        r3[0] = r5;
        return r3;
    }

    public static short[] prepend(short[] sArr, short s) {
        if (sArr == null) {
            return new short[]{s};
        }
        int length = sArr.length;
        short[] sArr2 = new short[length + 1];
        System.arraycopy(sArr, 0, sArr2, 1, length);
        sArr2[0] = s;
        return sArr2;
    }

    public static byte[] reverse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int r0 = 0;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        while (true) {
            length--;
            if (length < 0) {
                return bArr2;
            }
            bArr2[length] = bArr[r0];
            r0++;
        }
    }

    public static int[] reverse(int[] r4) {
        if (r4 == null) {
            return null;
        }
        int r0 = 0;
        int length = r4.length;
        int[] r2 = new int[length];
        while (true) {
            length--;
            if (length < 0) {
                return r2;
            }
            r2[length] = r4[r0];
            r0++;
        }
    }

    public static byte[] reverseInPlace(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length - 1;
        for (int r0 = 0; r0 < length; r0++) {
            byte b = bArr[r0];
            bArr[r0] = bArr[length];
            bArr[length] = b;
            length--;
        }
        return bArr;
    }

    public static int[] reverseInPlace(int[] r5) {
        if (r5 == null) {
            return null;
        }
        int length = r5.length - 1;
        for (int r0 = 0; r0 < length; r0++) {
            int r2 = r5[r0];
            r5[r0] = r5[length];
            r5[length] = r2;
            length--;
        }
        return r5;
    }
}
