package org.apache.commons.lang3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.MutableInt;

/* loaded from: classes5.dex */
public class ArrayUtils {
    public static final int INDEX_NOT_FOUND = -1;
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

    public static <T> T[] toArray(T... tArr) {
        return tArr;
    }

    public static String toString(Object obj) {
        return toString(obj, "{}");
    }

    public static String toString(Object obj, String str) {
        return obj == null ? str : new ToStringBuilder(obj, ToStringStyle.SIMPLE_STYLE).append(obj).toString();
    }

    public static int hashCode(Object obj) {
        return new HashCodeBuilder().append(obj).toHashCode();
    }

    @Deprecated
    public static boolean isEquals(Object obj, Object obj2) {
        return new EqualsBuilder().append(obj, obj2).isEquals();
    }

    public static Map<Object, Object> toMap(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        HashMap hashMap = new HashMap((int) (objArr.length * 1.5d));
        for (int r2 = 0; r2 < objArr.length; r2++) {
            Object obj = objArr[r2];
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                hashMap.put(entry.getKey(), entry.getValue());
            } else if (obj instanceof Object[]) {
                Object[] objArr2 = (Object[]) obj;
                if (objArr2.length < 2) {
                    throw new IllegalArgumentException("Array element " + r2 + ", '" + obj + "', has a length less than 2");
                }
                hashMap.put(objArr2[0], objArr2[1]);
            } else {
                throw new IllegalArgumentException("Array element " + r2 + ", '" + obj + "', is neither of type Map.Entry nor an Array");
            }
        }
        return hashMap;
    }

    public static <T> T[] clone(T[] tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Object[]) tArr.clone());
    }

    public static long[] clone(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        return (long[]) jArr.clone();
    }

    public static int[] clone(int[] r0) {
        if (r0 == null) {
            return null;
        }
        return (int[]) r0.clone();
    }

    public static short[] clone(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        return (short[]) sArr.clone();
    }

    public static char[] clone(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return (char[]) cArr.clone();
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public static double[] clone(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public static float[] clone(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        return (float[]) fArr.clone();
    }

    public static boolean[] clone(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        return (boolean[]) zArr.clone();
    }

    public static <T> T[] nullToEmpty(T[] tArr, Class<T[]> cls) {
        if (cls != null) {
            return tArr == null ? cls.cast(Array.newInstance(cls.getComponentType(), 0)) : tArr;
        }
        throw new IllegalArgumentException("The type must not be null");
    }

    public static Object[] nullToEmpty(Object[] objArr) {
        return isEmpty(objArr) ? EMPTY_OBJECT_ARRAY : objArr;
    }

    public static Class<?>[] nullToEmpty(Class<?>[] clsArr) {
        return isEmpty(clsArr) ? EMPTY_CLASS_ARRAY : clsArr;
    }

    public static String[] nullToEmpty(String[] strArr) {
        return isEmpty(strArr) ? EMPTY_STRING_ARRAY : strArr;
    }

    public static long[] nullToEmpty(long[] jArr) {
        return isEmpty(jArr) ? EMPTY_LONG_ARRAY : jArr;
    }

    public static int[] nullToEmpty(int[] r1) {
        return isEmpty(r1) ? EMPTY_INT_ARRAY : r1;
    }

    public static short[] nullToEmpty(short[] sArr) {
        return isEmpty(sArr) ? EMPTY_SHORT_ARRAY : sArr;
    }

    public static char[] nullToEmpty(char[] cArr) {
        return isEmpty(cArr) ? EMPTY_CHAR_ARRAY : cArr;
    }

    public static byte[] nullToEmpty(byte[] bArr) {
        return isEmpty(bArr) ? EMPTY_BYTE_ARRAY : bArr;
    }

    public static double[] nullToEmpty(double[] dArr) {
        return isEmpty(dArr) ? EMPTY_DOUBLE_ARRAY : dArr;
    }

    public static float[] nullToEmpty(float[] fArr) {
        return isEmpty(fArr) ? EMPTY_FLOAT_ARRAY : fArr;
    }

    public static boolean[] nullToEmpty(boolean[] zArr) {
        return isEmpty(zArr) ? EMPTY_BOOLEAN_ARRAY : zArr;
    }

    public static Long[] nullToEmpty(Long[] lArr) {
        return isEmpty(lArr) ? EMPTY_LONG_OBJECT_ARRAY : lArr;
    }

    public static Integer[] nullToEmpty(Integer[] numArr) {
        return isEmpty(numArr) ? EMPTY_INTEGER_OBJECT_ARRAY : numArr;
    }

    public static Short[] nullToEmpty(Short[] shArr) {
        return isEmpty(shArr) ? EMPTY_SHORT_OBJECT_ARRAY : shArr;
    }

    public static Character[] nullToEmpty(Character[] chArr) {
        return isEmpty(chArr) ? EMPTY_CHARACTER_OBJECT_ARRAY : chArr;
    }

    public static Byte[] nullToEmpty(Byte[] bArr) {
        return isEmpty(bArr) ? EMPTY_BYTE_OBJECT_ARRAY : bArr;
    }

    public static Double[] nullToEmpty(Double[] dArr) {
        return isEmpty(dArr) ? EMPTY_DOUBLE_OBJECT_ARRAY : dArr;
    }

    public static Float[] nullToEmpty(Float[] fArr) {
        return isEmpty(fArr) ? EMPTY_FLOAT_OBJECT_ARRAY : fArr;
    }

    public static Boolean[] nullToEmpty(Boolean[] boolArr) {
        return isEmpty(boolArr) ? EMPTY_BOOLEAN_OBJECT_ARRAY : boolArr;
    }

    public static <T> T[] subarray(T[] tArr, int r3, int r4) {
        if (tArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > tArr.length) {
            r4 = tArr.length;
        }
        int r42 = r4 - r3;
        Class<?> componentType = tArr.getClass().getComponentType();
        if (r42 <= 0) {
            return (T[]) ((Object[]) Array.newInstance(componentType, 0));
        }
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(componentType, r42));
        System.arraycopy(tArr, r3, tArr2, 0, r42);
        return tArr2;
    }

    public static long[] subarray(long[] jArr, int r3, int r4) {
        if (jArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > jArr.length) {
            r4 = jArr.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr2 = new long[r42];
        System.arraycopy(jArr, r3, jArr2, 0, r42);
        return jArr2;
    }

    public static int[] subarray(int[] r2, int r3, int r4) {
        if (r2 == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > r2.length) {
            r4 = r2.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] r1 = new int[r42];
        System.arraycopy(r2, r3, r1, 0, r42);
        return r1;
    }

    public static short[] subarray(short[] sArr, int r3, int r4) {
        if (sArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > sArr.length) {
            r4 = sArr.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr2 = new short[r42];
        System.arraycopy(sArr, r3, sArr2, 0, r42);
        return sArr2;
    }

    public static char[] subarray(char[] cArr, int r3, int r4) {
        if (cArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > cArr.length) {
            r4 = cArr.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr2 = new char[r42];
        System.arraycopy(cArr, r3, cArr2, 0, r42);
        return cArr2;
    }

    public static byte[] subarray(byte[] bArr, int r3, int r4) {
        if (bArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > bArr.length) {
            r4 = bArr.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[r42];
        System.arraycopy(bArr, r3, bArr2, 0, r42);
        return bArr2;
    }

    public static double[] subarray(double[] dArr, int r3, int r4) {
        if (dArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > dArr.length) {
            r4 = dArr.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[r42];
        System.arraycopy(dArr, r3, dArr2, 0, r42);
        return dArr2;
    }

    public static float[] subarray(float[] fArr, int r3, int r4) {
        if (fArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > fArr.length) {
            r4 = fArr.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[r42];
        System.arraycopy(fArr, r3, fArr2, 0, r42);
        return fArr2;
    }

    public static boolean[] subarray(boolean[] zArr, int r3, int r4) {
        if (zArr == null) {
            return null;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        if (r4 > zArr.length) {
            r4 = zArr.length;
        }
        int r42 = r4 - r3;
        if (r42 <= 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr2 = new boolean[r42];
        System.arraycopy(zArr, r3, zArr2, 0, r42);
        return zArr2;
    }

    public static boolean isSameLength(Object[] objArr, Object[] objArr2) {
        return getLength(objArr) == getLength(objArr2);
    }

    public static boolean isSameLength(long[] jArr, long[] jArr2) {
        return getLength(jArr) == getLength(jArr2);
    }

    public static boolean isSameLength(int[] r0, int[] r1) {
        return getLength(r0) == getLength(r1);
    }

    public static boolean isSameLength(short[] sArr, short[] sArr2) {
        return getLength(sArr) == getLength(sArr2);
    }

    public static boolean isSameLength(char[] cArr, char[] cArr2) {
        return getLength(cArr) == getLength(cArr2);
    }

    public static boolean isSameLength(byte[] bArr, byte[] bArr2) {
        return getLength(bArr) == getLength(bArr2);
    }

    public static boolean isSameLength(double[] dArr, double[] dArr2) {
        return getLength(dArr) == getLength(dArr2);
    }

    public static boolean isSameLength(float[] fArr, float[] fArr2) {
        return getLength(fArr) == getLength(fArr2);
    }

    public static boolean isSameLength(boolean[] zArr, boolean[] zArr2) {
        return getLength(zArr) == getLength(zArr2);
    }

    public static int getLength(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Array.getLength(obj);
    }

    public static boolean isSameType(Object obj, Object obj2) {
        if (obj == null || obj2 == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        return obj.getClass().getName().equals(obj2.getClass().getName());
    }

    public static void reverse(Object[] objArr) {
        if (objArr == null) {
            return;
        }
        reverse(objArr, 0, objArr.length);
    }

    public static void reverse(long[] jArr) {
        if (jArr == null) {
            return;
        }
        reverse(jArr, 0, jArr.length);
    }

    public static void reverse(int[] r2) {
        if (r2 == null) {
            return;
        }
        reverse(r2, 0, r2.length);
    }

    public static void reverse(short[] sArr) {
        if (sArr == null) {
            return;
        }
        reverse(sArr, 0, sArr.length);
    }

    public static void reverse(char[] cArr) {
        if (cArr == null) {
            return;
        }
        reverse(cArr, 0, cArr.length);
    }

    public static void reverse(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        reverse(bArr, 0, bArr.length);
    }

    public static void reverse(double[] dArr) {
        if (dArr == null) {
            return;
        }
        reverse(dArr, 0, dArr.length);
    }

    public static void reverse(float[] fArr) {
        if (fArr == null) {
            return;
        }
        reverse(fArr, 0, fArr.length);
    }

    public static void reverse(boolean[] zArr) {
        if (zArr == null) {
            return;
        }
        reverse(zArr, 0, zArr.length);
    }

    public static void reverse(boolean[] zArr, int r3, int r4) {
        if (zArr == null) {
            return;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        int min = Math.min(zArr.length, r4) - 1;
        while (min > r3) {
            boolean z = zArr[min];
            zArr[min] = zArr[r3];
            zArr[r3] = z;
            min--;
            r3++;
        }
    }

    public static void reverse(byte[] bArr, int r3, int r4) {
        if (bArr == null) {
            return;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        int min = Math.min(bArr.length, r4) - 1;
        while (min > r3) {
            byte b = bArr[min];
            bArr[min] = bArr[r3];
            bArr[r3] = b;
            min--;
            r3++;
        }
    }

    public static void reverse(char[] cArr, int r3, int r4) {
        if (cArr == null) {
            return;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        int min = Math.min(cArr.length, r4) - 1;
        while (min > r3) {
            char c = cArr[min];
            cArr[min] = cArr[r3];
            cArr[r3] = c;
            min--;
            r3++;
        }
    }

    public static void reverse(double[] dArr, int r5, int r6) {
        if (dArr == null) {
            return;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(dArr.length, r6) - 1;
        while (min > r5) {
            double d = dArr[min];
            dArr[min] = dArr[r5];
            dArr[r5] = d;
            min--;
            r5++;
        }
    }

    public static void reverse(float[] fArr, int r3, int r4) {
        if (fArr == null) {
            return;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        int min = Math.min(fArr.length, r4) - 1;
        while (min > r3) {
            float f = fArr[min];
            fArr[min] = fArr[r3];
            fArr[r3] = f;
            min--;
            r3++;
        }
    }

    public static void reverse(int[] r2, int r3, int r4) {
        if (r2 == null) {
            return;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        int min = Math.min(r2.length, r4) - 1;
        while (min > r3) {
            int r0 = r2[min];
            r2[min] = r2[r3];
            r2[r3] = r0;
            min--;
            r3++;
        }
    }

    public static void reverse(long[] jArr, int r5, int r6) {
        if (jArr == null) {
            return;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(jArr.length, r6) - 1;
        while (min > r5) {
            long j = jArr[min];
            jArr[min] = jArr[r5];
            jArr[r5] = j;
            min--;
            r5++;
        }
    }

    public static void reverse(Object[] objArr, int r3, int r4) {
        if (objArr == null) {
            return;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        int min = Math.min(objArr.length, r4) - 1;
        while (min > r3) {
            Object obj = objArr[min];
            objArr[min] = objArr[r3];
            objArr[r3] = obj;
            min--;
            r3++;
        }
    }

    public static void reverse(short[] sArr, int r3, int r4) {
        if (sArr == null) {
            return;
        }
        if (r3 < 0) {
            r3 = 0;
        }
        int min = Math.min(sArr.length, r4) - 1;
        while (min > r3) {
            short s = sArr[min];
            sArr[min] = sArr[r3];
            sArr[r3] = s;
            min--;
            r3++;
        }
    }

    public static void swap(Object[] objArr, int r2, int r3) {
        if (objArr == null || objArr.length == 0) {
            return;
        }
        swap(objArr, r2, r3, 1);
    }

    public static void swap(long[] jArr, int r2, int r3) {
        if (jArr == null || jArr.length == 0) {
            return;
        }
        swap(jArr, r2, r3, 1);
    }

    public static void swap(int[] r1, int r2, int r3) {
        if (r1 == null || r1.length == 0) {
            return;
        }
        swap(r1, r2, r3, 1);
    }

    public static void swap(short[] sArr, int r2, int r3) {
        if (sArr == null || sArr.length == 0) {
            return;
        }
        swap(sArr, r2, r3, 1);
    }

    public static void swap(char[] cArr, int r2, int r3) {
        if (cArr == null || cArr.length == 0) {
            return;
        }
        swap(cArr, r2, r3, 1);
    }

    public static void swap(byte[] bArr, int r2, int r3) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        swap(bArr, r2, r3, 1);
    }

    public static void swap(double[] dArr, int r2, int r3) {
        if (dArr == null || dArr.length == 0) {
            return;
        }
        swap(dArr, r2, r3, 1);
    }

    public static void swap(float[] fArr, int r2, int r3) {
        if (fArr == null || fArr.length == 0) {
            return;
        }
        swap(fArr, r2, r3, 1);
    }

    public static void swap(boolean[] zArr, int r2, int r3) {
        if (zArr == null || zArr.length == 0) {
            return;
        }
        swap(zArr, r2, r3, 1);
    }

    public static void swap(boolean[] zArr, int r4, int r5, int r6) {
        if (zArr == null || zArr.length == 0 || r4 >= zArr.length || r5 >= zArr.length) {
            return;
        }
        int r0 = 0;
        if (r4 < 0) {
            r4 = 0;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(Math.min(r6, zArr.length - r4), zArr.length - r5);
        while (r0 < min) {
            boolean z = zArr[r4];
            zArr[r4] = zArr[r5];
            zArr[r5] = z;
            r0++;
            r4++;
            r5++;
        }
    }

    public static void swap(byte[] bArr, int r4, int r5, int r6) {
        if (bArr == null || bArr.length == 0 || r4 >= bArr.length || r5 >= bArr.length) {
            return;
        }
        int r0 = 0;
        if (r4 < 0) {
            r4 = 0;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(Math.min(r6, bArr.length - r4), bArr.length - r5);
        while (r0 < min) {
            byte b = bArr[r4];
            bArr[r4] = bArr[r5];
            bArr[r5] = b;
            r0++;
            r4++;
            r5++;
        }
    }

    public static void swap(char[] cArr, int r4, int r5, int r6) {
        if (cArr == null || cArr.length == 0 || r4 >= cArr.length || r5 >= cArr.length) {
            return;
        }
        int r0 = 0;
        if (r4 < 0) {
            r4 = 0;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(Math.min(r6, cArr.length - r4), cArr.length - r5);
        while (r0 < min) {
            char c = cArr[r4];
            cArr[r4] = cArr[r5];
            cArr[r5] = c;
            r0++;
            r4++;
            r5++;
        }
    }

    public static void swap(double[] dArr, int r6, int r7, int r8) {
        if (dArr == null || dArr.length == 0 || r6 >= dArr.length || r7 >= dArr.length) {
            return;
        }
        int r0 = 0;
        if (r6 < 0) {
            r6 = 0;
        }
        if (r7 < 0) {
            r7 = 0;
        }
        int min = Math.min(Math.min(r8, dArr.length - r6), dArr.length - r7);
        while (r0 < min) {
            double d = dArr[r6];
            dArr[r6] = dArr[r7];
            dArr[r7] = d;
            r0++;
            r6++;
            r7++;
        }
    }

    public static void swap(float[] fArr, int r4, int r5, int r6) {
        if (fArr == null || fArr.length == 0 || r4 >= fArr.length || r5 >= fArr.length) {
            return;
        }
        int r0 = 0;
        if (r4 < 0) {
            r4 = 0;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(Math.min(r6, fArr.length - r4), fArr.length - r5);
        while (r0 < min) {
            float f = fArr[r4];
            fArr[r4] = fArr[r5];
            fArr[r5] = f;
            r0++;
            r4++;
            r5++;
        }
    }

    public static void swap(int[] r3, int r4, int r5, int r6) {
        if (r3 == null || r3.length == 0 || r4 >= r3.length || r5 >= r3.length) {
            return;
        }
        int r0 = 0;
        if (r4 < 0) {
            r4 = 0;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(Math.min(r6, r3.length - r4), r3.length - r5);
        while (r0 < min) {
            int r1 = r3[r4];
            r3[r4] = r3[r5];
            r3[r5] = r1;
            r0++;
            r4++;
            r5++;
        }
    }

    public static void swap(long[] jArr, int r6, int r7, int r8) {
        if (jArr == null || jArr.length == 0 || r6 >= jArr.length || r7 >= jArr.length) {
            return;
        }
        int r0 = 0;
        if (r6 < 0) {
            r6 = 0;
        }
        if (r7 < 0) {
            r7 = 0;
        }
        int min = Math.min(Math.min(r8, jArr.length - r6), jArr.length - r7);
        while (r0 < min) {
            long j = jArr[r6];
            jArr[r6] = jArr[r7];
            jArr[r7] = j;
            r0++;
            r6++;
            r7++;
        }
    }

    public static void swap(Object[] objArr, int r4, int r5, int r6) {
        if (objArr == null || objArr.length == 0 || r4 >= objArr.length || r5 >= objArr.length) {
            return;
        }
        int r0 = 0;
        if (r4 < 0) {
            r4 = 0;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        int min = Math.min(Math.min(r6, objArr.length - r4), objArr.length - r5);
        while (r0 < min) {
            Object obj = objArr[r4];
            objArr[r4] = objArr[r5];
            objArr[r5] = obj;
            r0++;
            r4++;
            r5++;
        }
    }

    public static void swap(short[] sArr, int r4, int r5, int r6) {
        if (sArr == null || sArr.length == 0 || r4 >= sArr.length || r5 >= sArr.length) {
            return;
        }
        int r0 = 0;
        if (r4 < 0) {
            r4 = 0;
        }
        if (r5 < 0) {
            r5 = 0;
        }
        if (r4 == r5) {
            return;
        }
        int min = Math.min(Math.min(r6, sArr.length - r4), sArr.length - r5);
        while (r0 < min) {
            short s = sArr[r4];
            sArr[r4] = sArr[r5];
            sArr[r5] = s;
            r0++;
            r4++;
            r5++;
        }
    }

    public static void shift(Object[] objArr, int r3) {
        if (objArr == null) {
            return;
        }
        shift(objArr, 0, objArr.length, r3);
    }

    public static void shift(long[] jArr, int r3) {
        if (jArr == null) {
            return;
        }
        shift(jArr, 0, jArr.length, r3);
    }

    public static void shift(int[] r2, int r3) {
        if (r2 == null) {
            return;
        }
        shift(r2, 0, r2.length, r3);
    }

    public static void shift(short[] sArr, int r3) {
        if (sArr == null) {
            return;
        }
        shift(sArr, 0, sArr.length, r3);
    }

    public static void shift(char[] cArr, int r3) {
        if (cArr == null) {
            return;
        }
        shift(cArr, 0, cArr.length, r3);
    }

    public static void shift(byte[] bArr, int r3) {
        if (bArr == null) {
            return;
        }
        shift(bArr, 0, bArr.length, r3);
    }

    public static void shift(double[] dArr, int r3) {
        if (dArr == null) {
            return;
        }
        shift(dArr, 0, dArr.length, r3);
    }

    public static void shift(float[] fArr, int r3) {
        if (fArr == null) {
            return;
        }
        shift(fArr, 0, fArr.length, r3);
    }

    public static void shift(boolean[] zArr, int r3) {
        if (zArr == null) {
            return;
        }
        shift(zArr, 0, zArr.length, r3);
    }

    public static void shift(boolean[] zArr, int r4, int r5, int r6) {
        if (zArr != null && r4 < zArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= zArr.length) {
                r5 = zArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(zArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(zArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(zArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(byte[] bArr, int r4, int r5, int r6) {
        if (bArr != null && r4 < bArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= bArr.length) {
                r5 = bArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(bArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(bArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(bArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(char[] cArr, int r4, int r5, int r6) {
        if (cArr != null && r4 < cArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= cArr.length) {
                r5 = cArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(cArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(cArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(cArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(double[] dArr, int r4, int r5, int r6) {
        if (dArr != null && r4 < dArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= dArr.length) {
                r5 = dArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(dArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(dArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(dArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(float[] fArr, int r4, int r5, int r6) {
        if (fArr != null && r4 < fArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= fArr.length) {
                r5 = fArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(fArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(fArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(fArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(int[] r3, int r4, int r5, int r6) {
        if (r3 != null && r4 < r3.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= r3.length) {
                r5 = r3.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(r3, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(r3, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(r3, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(long[] jArr, int r4, int r5, int r6) {
        if (jArr != null && r4 < jArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= jArr.length) {
                r5 = jArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(jArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(jArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(jArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(Object[] objArr, int r4, int r5, int r6) {
        if (objArr != null && r4 < objArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= objArr.length) {
                r5 = objArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(objArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(objArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(objArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static void shift(short[] sArr, int r4, int r5, int r6) {
        if (sArr != null && r4 < sArr.length - 1 && r5 > 0) {
            if (r4 < 0) {
                r4 = 0;
            }
            if (r5 >= sArr.length) {
                r5 = sArr.length;
            }
            int r52 = r5 - r4;
            if (r52 <= 1) {
                return;
            }
            int r62 = r6 % r52;
            if (r62 < 0) {
                r62 += r52;
            }
            while (r52 > 1 && r62 > 0) {
                int r0 = r52 - r62;
                if (r62 > r0) {
                    swap(sArr, r4, (r52 + r4) - r0, r0);
                    int r2 = r62;
                    r62 -= r0;
                    r52 = r2;
                } else if (r62 < r0) {
                    swap(sArr, r4, r4 + r0, r62);
                    r4 += r62;
                    r52 = r0;
                } else {
                    swap(sArr, r4, r0 + r4, r62);
                    return;
                }
            }
        }
    }

    public static int indexOf(Object[] objArr, Object obj) {
        return indexOf(objArr, obj, 0);
    }

    public static int indexOf(Object[] objArr, Object obj, int r4) {
        if (objArr == null) {
            return -1;
        }
        if (r4 < 0) {
            r4 = 0;
        }
        if (obj == null) {
            while (r4 < objArr.length) {
                if (objArr[r4] == null) {
                    return r4;
                }
                r4++;
            }
        } else {
            while (r4 < objArr.length) {
                if (obj.equals(objArr[r4])) {
                    return r4;
                }
                r4++;
            }
        }
        return -1;
    }

    public static int lastIndexOf(Object[] objArr, Object obj) {
        return lastIndexOf(objArr, obj, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(Object[] objArr, Object obj, int r4) {
        if (objArr != null && r4 >= 0) {
            if (r4 >= objArr.length) {
                r4 = objArr.length - 1;
            }
            if (obj == null) {
                while (r4 >= 0) {
                    if (objArr[r4] == null) {
                        return r4;
                    }
                    r4--;
                }
            } else if (objArr.getClass().getComponentType().isInstance(obj)) {
                while (r4 >= 0) {
                    if (obj.equals(objArr[r4])) {
                        return r4;
                    }
                    r4--;
                }
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(Object[] objArr, Object obj) {
        return indexOf(objArr, obj) != -1;
    }

    public static int indexOf(long[] jArr, long j) {
        return indexOf(jArr, j, 0);
    }

    public static int indexOf(long[] jArr, long j, int r7) {
        if (jArr == null) {
            return -1;
        }
        if (r7 < 0) {
            r7 = 0;
        }
        while (r7 < jArr.length) {
            if (j == jArr[r7]) {
                return r7;
            }
            r7++;
        }
        return -1;
    }

    public static int lastIndexOf(long[] jArr, long j) {
        return lastIndexOf(jArr, j, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(long[] jArr, long j, int r7) {
        if (jArr != null && r7 >= 0) {
            if (r7 >= jArr.length) {
                r7 = jArr.length - 1;
            }
            while (r7 >= 0) {
                if (j == jArr[r7]) {
                    return r7;
                }
                r7--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(long[] jArr, long j) {
        return indexOf(jArr, j) != -1;
    }

    public static int indexOf(int[] r1, int r2) {
        return indexOf(r1, r2, 0);
    }

    public static int indexOf(int[] r2, int r3, int r4) {
        if (r2 == null) {
            return -1;
        }
        if (r4 < 0) {
            r4 = 0;
        }
        while (r4 < r2.length) {
            if (r3 == r2[r4]) {
                return r4;
            }
            r4++;
        }
        return -1;
    }

    public static int lastIndexOf(int[] r1, int r2) {
        return lastIndexOf(r1, r2, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(int[] r2, int r3, int r4) {
        if (r2 != null && r4 >= 0) {
            if (r4 >= r2.length) {
                r4 = r2.length - 1;
            }
            while (r4 >= 0) {
                if (r3 == r2[r4]) {
                    return r4;
                }
                r4--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(int[] r0, int r1) {
        return indexOf(r0, r1) != -1;
    }

    public static int indexOf(short[] sArr, short s) {
        return indexOf(sArr, s, 0);
    }

    public static int indexOf(short[] sArr, short s, int r4) {
        if (sArr == null) {
            return -1;
        }
        if (r4 < 0) {
            r4 = 0;
        }
        while (r4 < sArr.length) {
            if (s == sArr[r4]) {
                return r4;
            }
            r4++;
        }
        return -1;
    }

    public static int lastIndexOf(short[] sArr, short s) {
        return lastIndexOf(sArr, s, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(short[] sArr, short s, int r4) {
        if (sArr != null && r4 >= 0) {
            if (r4 >= sArr.length) {
                r4 = sArr.length - 1;
            }
            while (r4 >= 0) {
                if (s == sArr[r4]) {
                    return r4;
                }
                r4--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(short[] sArr, short s) {
        return indexOf(sArr, s) != -1;
    }

    public static int indexOf(char[] cArr, char c) {
        return indexOf(cArr, c, 0);
    }

    public static int indexOf(char[] cArr, char c, int r4) {
        if (cArr == null) {
            return -1;
        }
        if (r4 < 0) {
            r4 = 0;
        }
        while (r4 < cArr.length) {
            if (c == cArr[r4]) {
                return r4;
            }
            r4++;
        }
        return -1;
    }

    public static int lastIndexOf(char[] cArr, char c) {
        return lastIndexOf(cArr, c, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(char[] cArr, char c, int r4) {
        if (cArr != null && r4 >= 0) {
            if (r4 >= cArr.length) {
                r4 = cArr.length - 1;
            }
            while (r4 >= 0) {
                if (c == cArr[r4]) {
                    return r4;
                }
                r4--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(char[] cArr, char c) {
        return indexOf(cArr, c) != -1;
    }

    public static int indexOf(byte[] bArr, byte b) {
        return indexOf(bArr, b, 0);
    }

    public static int indexOf(byte[] bArr, byte b, int r4) {
        if (bArr == null) {
            return -1;
        }
        if (r4 < 0) {
            r4 = 0;
        }
        while (r4 < bArr.length) {
            if (b == bArr[r4]) {
                return r4;
            }
            r4++;
        }
        return -1;
    }

    public static int lastIndexOf(byte[] bArr, byte b) {
        return lastIndexOf(bArr, b, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(byte[] bArr, byte b, int r4) {
        if (bArr != null && r4 >= 0) {
            if (r4 >= bArr.length) {
                r4 = bArr.length - 1;
            }
            while (r4 >= 0) {
                if (b == bArr[r4]) {
                    return r4;
                }
                r4--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(byte[] bArr, byte b) {
        return indexOf(bArr, b) != -1;
    }

    public static int indexOf(double[] dArr, double d) {
        return indexOf(dArr, d, 0);
    }

    public static int indexOf(double[] dArr, double d, double d2) {
        return indexOf(dArr, d, 0, d2);
    }

    public static int indexOf(double[] dArr, double d, int r7) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (r7 < 0) {
            r7 = 0;
        }
        while (r7 < dArr.length) {
            if (d == dArr[r7]) {
                return r7;
            }
            r7++;
        }
        return -1;
    }

    public static int indexOf(double[] dArr, double d, int r7, double d2) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (r7 < 0) {
            r7 = 0;
        }
        double d3 = d - d2;
        double d4 = d + d2;
        while (r7 < dArr.length) {
            if (dArr[r7] >= d3 && dArr[r7] <= d4) {
                return r7;
            }
            r7++;
        }
        return -1;
    }

    public static int lastIndexOf(double[] dArr, double d) {
        return lastIndexOf(dArr, d, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(double[] dArr, double d, double d2) {
        return lastIndexOf(dArr, d, Integer.MAX_VALUE, d2);
    }

    public static int lastIndexOf(double[] dArr, double d, int r7) {
        if (!isEmpty(dArr) && r7 >= 0) {
            if (r7 >= dArr.length) {
                r7 = dArr.length - 1;
            }
            while (r7 >= 0) {
                if (d == dArr[r7]) {
                    return r7;
                }
                r7--;
            }
            return -1;
        }
        return -1;
    }

    public static int lastIndexOf(double[] dArr, double d, int r7, double d2) {
        if (!isEmpty(dArr) && r7 >= 0) {
            if (r7 >= dArr.length) {
                r7 = dArr.length - 1;
            }
            double d3 = d - d2;
            double d4 = d + d2;
            while (r7 >= 0) {
                if (dArr[r7] >= d3 && dArr[r7] <= d4) {
                    return r7;
                }
                r7--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(double[] dArr, double d) {
        return indexOf(dArr, d) != -1;
    }

    public static boolean contains(double[] dArr, double d, double d2) {
        return indexOf(dArr, d, 0, d2) != -1;
    }

    public static int indexOf(float[] fArr, float f) {
        return indexOf(fArr, f, 0);
    }

    public static int indexOf(float[] fArr, float f, int r4) {
        if (isEmpty(fArr)) {
            return -1;
        }
        if (r4 < 0) {
            r4 = 0;
        }
        while (r4 < fArr.length) {
            if (f == fArr[r4]) {
                return r4;
            }
            r4++;
        }
        return -1;
    }

    public static int lastIndexOf(float[] fArr, float f) {
        return lastIndexOf(fArr, f, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(float[] fArr, float f, int r4) {
        if (!isEmpty(fArr) && r4 >= 0) {
            if (r4 >= fArr.length) {
                r4 = fArr.length - 1;
            }
            while (r4 >= 0) {
                if (f == fArr[r4]) {
                    return r4;
                }
                r4--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(float[] fArr, float f) {
        return indexOf(fArr, f) != -1;
    }

    public static int indexOf(boolean[] zArr, boolean z) {
        return indexOf(zArr, z, 0);
    }

    public static int indexOf(boolean[] zArr, boolean z, int r4) {
        if (isEmpty(zArr)) {
            return -1;
        }
        if (r4 < 0) {
            r4 = 0;
        }
        while (r4 < zArr.length) {
            if (z == zArr[r4]) {
                return r4;
            }
            r4++;
        }
        return -1;
    }

    public static int lastIndexOf(boolean[] zArr, boolean z) {
        return lastIndexOf(zArr, z, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(boolean[] zArr, boolean z, int r4) {
        if (!isEmpty(zArr) && r4 >= 0) {
            if (r4 >= zArr.length) {
                r4 = zArr.length - 1;
            }
            while (r4 >= 0) {
                if (z == zArr[r4]) {
                    return r4;
                }
                r4--;
            }
            return -1;
        }
        return -1;
    }

    public static boolean contains(boolean[] zArr, boolean z) {
        return indexOf(zArr, z) != -1;
    }

    public static char[] toPrimitive(Character[] chArr) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[chArr.length];
        for (int r1 = 0; r1 < chArr.length; r1++) {
            cArr[r1] = chArr[r1].charValue();
        }
        return cArr;
    }

    public static char[] toPrimitive(Character[] chArr, char c) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[chArr.length];
        for (int r1 = 0; r1 < chArr.length; r1++) {
            Character ch = chArr[r1];
            cArr[r1] = ch == null ? c : ch.charValue();
        }
        return cArr;
    }

    public static Character[] toObject(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        if (cArr.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        Character[] chArr = new Character[cArr.length];
        for (int r1 = 0; r1 < cArr.length; r1++) {
            chArr[r1] = Character.valueOf(cArr[r1]);
        }
        return chArr;
    }

    public static long[] toPrimitive(Long[] lArr) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr = new long[lArr.length];
        for (int r1 = 0; r1 < lArr.length; r1++) {
            jArr[r1] = lArr[r1].longValue();
        }
        return jArr;
    }

    public static long[] toPrimitive(Long[] lArr, long j) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr = new long[lArr.length];
        for (int r1 = 0; r1 < lArr.length; r1++) {
            Long l = lArr[r1];
            jArr[r1] = l == null ? j : l.longValue();
        }
        return jArr;
    }

    public static Long[] toObject(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        if (jArr.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        Long[] lArr = new Long[jArr.length];
        for (int r1 = 0; r1 < jArr.length; r1++) {
            lArr[r1] = Long.valueOf(jArr[r1]);
        }
        return lArr;
    }

    public static int[] toPrimitive(Integer[] numArr) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] r0 = new int[numArr.length];
        for (int r1 = 0; r1 < numArr.length; r1++) {
            r0[r1] = numArr[r1].intValue();
        }
        return r0;
    }

    public static int[] toPrimitive(Integer[] numArr, int r4) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] r0 = new int[numArr.length];
        for (int r1 = 0; r1 < numArr.length; r1++) {
            Integer num = numArr[r1];
            r0[r1] = num == null ? r4 : num.intValue();
        }
        return r0;
    }

    public static Integer[] toObject(int[] r3) {
        if (r3 == null) {
            return null;
        }
        if (r3.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        Integer[] numArr = new Integer[r3.length];
        for (int r1 = 0; r1 < r3.length; r1++) {
            numArr[r1] = Integer.valueOf(r3[r1]);
        }
        return numArr;
    }

    public static short[] toPrimitive(Short[] shArr) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[shArr.length];
        for (int r1 = 0; r1 < shArr.length; r1++) {
            sArr[r1] = shArr[r1].shortValue();
        }
        return sArr;
    }

    public static short[] toPrimitive(Short[] shArr, short s) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[shArr.length];
        for (int r1 = 0; r1 < shArr.length; r1++) {
            Short sh = shArr[r1];
            sArr[r1] = sh == null ? s : sh.shortValue();
        }
        return sArr;
    }

    public static Short[] toObject(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        if (sArr.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        Short[] shArr = new Short[sArr.length];
        for (int r1 = 0; r1 < sArr.length; r1++) {
            shArr[r1] = Short.valueOf(sArr[r1]);
        }
        return shArr;
    }

    public static byte[] toPrimitive(Byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            bArr2[r1] = bArr[r1].byteValue();
        }
        return bArr2;
    }

    public static byte[] toPrimitive(Byte[] bArr, byte b) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            Byte b2 = bArr[r1];
            bArr2[r1] = b2 == null ? b : b2.byteValue();
        }
        return bArr2;
    }

    public static Byte[] toObject(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        Byte[] bArr2 = new Byte[bArr.length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            bArr2[r1] = Byte.valueOf(bArr[r1]);
        }
        return bArr2;
    }

    public static double[] toPrimitive(Double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[dArr.length];
        for (int r1 = 0; r1 < dArr.length; r1++) {
            dArr2[r1] = dArr[r1].doubleValue();
        }
        return dArr2;
    }

    public static double[] toPrimitive(Double[] dArr, double d) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[dArr.length];
        for (int r1 = 0; r1 < dArr.length; r1++) {
            Double d2 = dArr[r1];
            dArr2[r1] = d2 == null ? d : d2.doubleValue();
        }
        return dArr2;
    }

    public static Double[] toObject(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        Double[] dArr2 = new Double[dArr.length];
        for (int r1 = 0; r1 < dArr.length; r1++) {
            dArr2[r1] = Double.valueOf(dArr[r1]);
        }
        return dArr2;
    }

    public static float[] toPrimitive(Float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[fArr.length];
        for (int r1 = 0; r1 < fArr.length; r1++) {
            fArr2[r1] = fArr[r1].floatValue();
        }
        return fArr2;
    }

    public static float[] toPrimitive(Float[] fArr, float f) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[fArr.length];
        for (int r1 = 0; r1 < fArr.length; r1++) {
            Float f2 = fArr[r1];
            fArr2[r1] = f2 == null ? f : f2.floatValue();
        }
        return fArr2;
    }

    public static Float[] toObject(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        Float[] fArr2 = new Float[fArr.length];
        for (int r1 = 0; r1 < fArr.length; r1++) {
            fArr2[r1] = Float.valueOf(fArr[r1]);
        }
        return fArr2;
    }

    public static Object toPrimitive(Object obj) {
        if (obj == null) {
            return null;
        }
        Class<?> wrapperToPrimitive = ClassUtils.wrapperToPrimitive(obj.getClass().getComponentType());
        if (Integer.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Integer[]) obj);
        }
        if (Long.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Long[]) obj);
        }
        if (Short.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Short[]) obj);
        }
        if (Double.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Double[]) obj);
        }
        return Float.TYPE.equals(wrapperToPrimitive) ? toPrimitive((Float[]) obj) : obj;
    }

    public static boolean[] toPrimitive(Boolean[] boolArr) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int r1 = 0; r1 < boolArr.length; r1++) {
            zArr[r1] = boolArr[r1].booleanValue();
        }
        return zArr;
    }

    public static boolean[] toPrimitive(Boolean[] boolArr, boolean z) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int r1 = 0; r1 < boolArr.length; r1++) {
            Boolean bool = boolArr[r1];
            zArr[r1] = bool == null ? z : bool.booleanValue();
        }
        return zArr;
    }

    public static Boolean[] toObject(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        if (zArr.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int r1 = 0; r1 < zArr.length; r1++) {
            boolArr[r1] = zArr[r1] ? Boolean.TRUE : Boolean.FALSE;
        }
        return boolArr;
    }

    public static boolean isEmpty(Object[] objArr) {
        return getLength(objArr) == 0;
    }

    public static boolean isEmpty(long[] jArr) {
        return getLength(jArr) == 0;
    }

    public static boolean isEmpty(int[] r0) {
        return getLength(r0) == 0;
    }

    public static boolean isEmpty(short[] sArr) {
        return getLength(sArr) == 0;
    }

    public static boolean isEmpty(char[] cArr) {
        return getLength(cArr) == 0;
    }

    public static boolean isEmpty(byte[] bArr) {
        return getLength(bArr) == 0;
    }

    public static boolean isEmpty(double[] dArr) {
        return getLength(dArr) == 0;
    }

    public static boolean isEmpty(float[] fArr) {
        return getLength(fArr) == 0;
    }

    public static boolean isEmpty(boolean[] zArr) {
        return getLength(zArr) == 0;
    }

    public static <T> boolean isNotEmpty(T[] tArr) {
        return !isEmpty(tArr);
    }

    public static boolean isNotEmpty(long[] jArr) {
        return !isEmpty(jArr);
    }

    public static boolean isNotEmpty(int[] r0) {
        return !isEmpty(r0);
    }

    public static boolean isNotEmpty(short[] sArr) {
        return !isEmpty(sArr);
    }

    public static boolean isNotEmpty(char[] cArr) {
        return !isEmpty(cArr);
    }

    public static boolean isNotEmpty(byte[] bArr) {
        return !isEmpty(bArr);
    }

    public static boolean isNotEmpty(double[] dArr) {
        return !isEmpty(dArr);
    }

    public static boolean isNotEmpty(float[] fArr) {
        return !isEmpty(fArr);
    }

    public static boolean isNotEmpty(boolean[] zArr) {
        return !isEmpty(zArr);
    }

    public static <T> T[] addAll(T[] tArr, T... tArr2) {
        if (tArr == null) {
            return (T[]) clone(tArr2);
        }
        if (tArr2 == null) {
            return (T[]) clone(tArr);
        }
        Class<?> componentType = tArr.getClass().getComponentType();
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(componentType, tArr.length + tArr2.length));
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        try {
            System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
            return tArr3;
        } catch (ArrayStoreException e) {
            Class<?> componentType2 = tArr2.getClass().getComponentType();
            if (!componentType.isAssignableFrom(componentType2)) {
                throw new IllegalArgumentException("Cannot store " + componentType2.getName() + " in an array of " + componentType.getName(), e);
            }
            throw e;
        }
    }

    public static boolean[] addAll(boolean[] zArr, boolean... zArr2) {
        if (zArr == null) {
            return clone(zArr2);
        }
        if (zArr2 == null) {
            return clone(zArr);
        }
        boolean[] zArr3 = new boolean[zArr.length + zArr2.length];
        System.arraycopy(zArr, 0, zArr3, 0, zArr.length);
        System.arraycopy(zArr2, 0, zArr3, zArr.length, zArr2.length);
        return zArr3;
    }

    public static char[] addAll(char[] cArr, char... cArr2) {
        if (cArr == null) {
            return clone(cArr2);
        }
        if (cArr2 == null) {
            return clone(cArr);
        }
        char[] cArr3 = new char[cArr.length + cArr2.length];
        System.arraycopy(cArr, 0, cArr3, 0, cArr.length);
        System.arraycopy(cArr2, 0, cArr3, cArr.length, cArr2.length);
        return cArr3;
    }

    public static byte[] addAll(byte[] bArr, byte... bArr2) {
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

    public static short[] addAll(short[] sArr, short... sArr2) {
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

    public static int[] addAll(int[] r3, int... r4) {
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

    public static long[] addAll(long[] jArr, long... jArr2) {
        if (jArr == null) {
            return clone(jArr2);
        }
        if (jArr2 == null) {
            return clone(jArr);
        }
        long[] jArr3 = new long[jArr.length + jArr2.length];
        System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
        System.arraycopy(jArr2, 0, jArr3, jArr.length, jArr2.length);
        return jArr3;
    }

    public static float[] addAll(float[] fArr, float... fArr2) {
        if (fArr == null) {
            return clone(fArr2);
        }
        if (fArr2 == null) {
            return clone(fArr);
        }
        float[] fArr3 = new float[fArr.length + fArr2.length];
        System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
        System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
        return fArr3;
    }

    public static double[] addAll(double[] dArr, double... dArr2) {
        if (dArr == null) {
            return clone(dArr2);
        }
        if (dArr2 == null) {
            return clone(dArr);
        }
        double[] dArr3 = new double[dArr.length + dArr2.length];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr3, dArr.length, dArr2.length);
        return dArr3;
    }

    public static <T> T[] add(T[] tArr, T t) {
        Class<?> cls;
        if (tArr != null) {
            cls = tArr.getClass().getComponentType();
        } else if (t != null) {
            cls = t.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        T[] tArr2 = (T[]) ((Object[]) copyArrayGrow1(tArr, cls));
        tArr2[tArr2.length - 1] = t;
        return tArr2;
    }

    public static boolean[] add(boolean[] zArr, boolean z) {
        boolean[] zArr2 = (boolean[]) copyArrayGrow1(zArr, Boolean.TYPE);
        zArr2[zArr2.length - 1] = z;
        return zArr2;
    }

    public static byte[] add(byte[] bArr, byte b) {
        byte[] bArr2 = (byte[]) copyArrayGrow1(bArr, Byte.TYPE);
        bArr2[bArr2.length - 1] = b;
        return bArr2;
    }

    public static char[] add(char[] cArr, char c) {
        char[] cArr2 = (char[]) copyArrayGrow1(cArr, Character.TYPE);
        cArr2[cArr2.length - 1] = c;
        return cArr2;
    }

    public static double[] add(double[] dArr, double d) {
        double[] dArr2 = (double[]) copyArrayGrow1(dArr, Double.TYPE);
        dArr2[dArr2.length - 1] = d;
        return dArr2;
    }

    public static float[] add(float[] fArr, float f) {
        float[] fArr2 = (float[]) copyArrayGrow1(fArr, Float.TYPE);
        fArr2[fArr2.length - 1] = f;
        return fArr2;
    }

    public static int[] add(int[] r1, int r2) {
        int[] r12 = (int[]) copyArrayGrow1(r1, Integer.TYPE);
        r12[r12.length - 1] = r2;
        return r12;
    }

    public static long[] add(long[] jArr, long j) {
        long[] jArr2 = (long[]) copyArrayGrow1(jArr, Long.TYPE);
        jArr2[jArr2.length - 1] = j;
        return jArr2;
    }

    public static short[] add(short[] sArr, short s) {
        short[] sArr2 = (short[]) copyArrayGrow1(sArr, Short.TYPE);
        sArr2[sArr2.length - 1] = s;
        return sArr2;
    }

    private static Object copyArrayGrow1(Object obj, Class<?> cls) {
        if (obj != null) {
            int length = Array.getLength(obj);
            Object newInstance = Array.newInstance(obj.getClass().getComponentType(), length + 1);
            System.arraycopy(obj, 0, newInstance, 0, length);
            return newInstance;
        }
        return Array.newInstance(cls, 1);
    }

    @Deprecated
    public static <T> T[] add(T[] tArr, int r2, T t) {
        Class<?> cls;
        if (tArr != null) {
            cls = tArr.getClass().getComponentType();
        } else if (t != null) {
            cls = t.getClass();
        } else {
            throw new IllegalArgumentException("Array and element cannot both be null");
        }
        return (T[]) ((Object[]) add(tArr, r2, t, cls));
    }

    @Deprecated
    public static boolean[] add(boolean[] zArr, int r2, boolean z) {
        return (boolean[]) add(zArr, r2, Boolean.valueOf(z), Boolean.TYPE);
    }

    @Deprecated
    public static char[] add(char[] cArr, int r2, char c) {
        return (char[]) add(cArr, r2, Character.valueOf(c), Character.TYPE);
    }

    @Deprecated
    public static byte[] add(byte[] bArr, int r2, byte b) {
        return (byte[]) add(bArr, r2, Byte.valueOf(b), Byte.TYPE);
    }

    @Deprecated
    public static short[] add(short[] sArr, int r2, short s) {
        return (short[]) add(sArr, r2, Short.valueOf(s), Short.TYPE);
    }

    @Deprecated
    public static int[] add(int[] r1, int r2, int r3) {
        return (int[]) add(r1, r2, Integer.valueOf(r3), Integer.TYPE);
    }

    @Deprecated
    public static long[] add(long[] jArr, int r1, long j) {
        return (long[]) add(jArr, r1, Long.valueOf(j), Long.TYPE);
    }

    @Deprecated
    public static float[] add(float[] fArr, int r2, float f) {
        return (float[]) add(fArr, r2, Float.valueOf(f), Float.TYPE);
    }

    @Deprecated
    public static double[] add(double[] dArr, int r1, double d) {
        return (double[]) add(dArr, r1, Double.valueOf(d), Double.TYPE);
    }

    private static Object add(Object obj, int r4, Object obj2, Class<?> cls) {
        if (obj == null) {
            if (r4 != 0) {
                throw new IndexOutOfBoundsException("Index: " + r4 + ", Length: 0");
            }
            Object newInstance = Array.newInstance(cls, 1);
            Array.set(newInstance, 0, obj2);
            return newInstance;
        }
        int length = Array.getLength(obj);
        if (r4 > length || r4 < 0) {
            throw new IndexOutOfBoundsException("Index: " + r4 + ", Length: " + length);
        }
        Object newInstance2 = Array.newInstance(cls, length + 1);
        System.arraycopy(obj, 0, newInstance2, 0, r4);
        Array.set(newInstance2, r4, obj2);
        if (r4 < length) {
            System.arraycopy(obj, r4, newInstance2, r4 + 1, length - r4);
        }
        return newInstance2;
    }

    public static <T> T[] remove(T[] tArr, int r1) {
        return (T[]) ((Object[]) remove((Object) tArr, r1));
    }

    public static <T> T[] removeElement(T[] tArr, Object obj) {
        int indexOf = indexOf(tArr, obj);
        if (indexOf == -1) {
            return (T[]) clone(tArr);
        }
        return (T[]) remove((Object[]) tArr, indexOf);
    }

    public static boolean[] remove(boolean[] zArr, int r1) {
        return (boolean[]) remove((Object) zArr, r1);
    }

    public static boolean[] removeElement(boolean[] zArr, boolean z) {
        int indexOf = indexOf(zArr, z);
        if (indexOf == -1) {
            return clone(zArr);
        }
        return remove(zArr, indexOf);
    }

    public static byte[] remove(byte[] bArr, int r1) {
        return (byte[]) remove((Object) bArr, r1);
    }

    public static byte[] removeElement(byte[] bArr, byte b) {
        int indexOf = indexOf(bArr, b);
        if (indexOf == -1) {
            return clone(bArr);
        }
        return remove(bArr, indexOf);
    }

    public static char[] remove(char[] cArr, int r1) {
        return (char[]) remove((Object) cArr, r1);
    }

    public static char[] removeElement(char[] cArr, char c) {
        int indexOf = indexOf(cArr, c);
        if (indexOf == -1) {
            return clone(cArr);
        }
        return remove(cArr, indexOf);
    }

    public static double[] remove(double[] dArr, int r1) {
        return (double[]) remove((Object) dArr, r1);
    }

    public static double[] removeElement(double[] dArr, double d) {
        int indexOf = indexOf(dArr, d);
        if (indexOf == -1) {
            return clone(dArr);
        }
        return remove(dArr, indexOf);
    }

    public static float[] remove(float[] fArr, int r1) {
        return (float[]) remove((Object) fArr, r1);
    }

    public static float[] removeElement(float[] fArr, float f) {
        int indexOf = indexOf(fArr, f);
        if (indexOf == -1) {
            return clone(fArr);
        }
        return remove(fArr, indexOf);
    }

    public static int[] remove(int[] r0, int r1) {
        return (int[]) remove((Object) r0, r1);
    }

    public static int[] removeElement(int[] r1, int r2) {
        int indexOf = indexOf(r1, r2);
        if (indexOf == -1) {
            return clone(r1);
        }
        return remove(r1, indexOf);
    }

    public static long[] remove(long[] jArr, int r1) {
        return (long[]) remove((Object) jArr, r1);
    }

    public static long[] removeElement(long[] jArr, long j) {
        int indexOf = indexOf(jArr, j);
        if (indexOf == -1) {
            return clone(jArr);
        }
        return remove(jArr, indexOf);
    }

    public static short[] remove(short[] sArr, int r1) {
        return (short[]) remove((Object) sArr, r1);
    }

    public static short[] removeElement(short[] sArr, short s) {
        int indexOf = indexOf(sArr, s);
        if (indexOf == -1) {
            return clone(sArr);
        }
        return remove(sArr, indexOf);
    }

    private static Object remove(Object obj, int r5) {
        int length = getLength(obj);
        if (r5 < 0 || r5 >= length) {
            throw new IndexOutOfBoundsException("Index: " + r5 + ", Length: " + length);
        }
        int r2 = length - 1;
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), r2);
        System.arraycopy(obj, 0, newInstance, 0, r5);
        if (r5 < r2) {
            System.arraycopy(obj, r5 + 1, newInstance, r5, (length - r5) - 1);
        }
        return newInstance;
    }

    public static <T> T[] removeAll(T[] tArr, int... r1) {
        return (T[]) ((Object[]) removeAll((Object) tArr, r1));
    }

    @SafeVarargs
    public static <T> T[] removeElements(T[] tArr, T... tArr2) {
        if (isEmpty(tArr) || isEmpty(tArr2)) {
            return (T[]) clone(tArr);
        }
        HashMap hashMap = new HashMap(tArr2.length);
        for (T t : tArr2) {
            MutableInt mutableInt = (MutableInt) hashMap.get(t);
            if (mutableInt == null) {
                hashMap.put(t, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < tArr.length; r2++) {
            T t2 = tArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(t2);
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(t2);
                }
                bitSet.set(r2);
            }
        }
        return (T[]) ((Object[]) removeAll(tArr, bitSet));
    }

    public static byte[] removeAll(byte[] bArr, int... r1) {
        return (byte[]) removeAll((Object) bArr, r1);
    }

    public static byte[] removeElements(byte[] bArr, byte... bArr2) {
        if (isEmpty(bArr) || isEmpty(bArr2)) {
            return clone(bArr);
        }
        HashMap hashMap = new HashMap(bArr2.length);
        for (byte b : bArr2) {
            Byte valueOf = Byte.valueOf(b);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < bArr.length; r2++) {
            byte b2 = bArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Byte.valueOf(b2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Byte.valueOf(b2));
                }
                bitSet.set(r2);
            }
        }
        return (byte[]) removeAll(bArr, bitSet);
    }

    public static short[] removeAll(short[] sArr, int... r1) {
        return (short[]) removeAll((Object) sArr, r1);
    }

    public static short[] removeElements(short[] sArr, short... sArr2) {
        if (isEmpty(sArr) || isEmpty(sArr2)) {
            return clone(sArr);
        }
        HashMap hashMap = new HashMap(sArr2.length);
        for (short s : sArr2) {
            Short valueOf = Short.valueOf(s);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < sArr.length; r2++) {
            short s2 = sArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Short.valueOf(s2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Short.valueOf(s2));
                }
                bitSet.set(r2);
            }
        }
        return (short[]) removeAll(sArr, bitSet);
    }

    public static int[] removeAll(int[] r0, int... r1) {
        return (int[]) removeAll((Object) r0, r1);
    }

    public static int[] removeElements(int[] r7, int... r8) {
        if (isEmpty(r7) || isEmpty(r8)) {
            return clone(r7);
        }
        HashMap hashMap = new HashMap(r8.length);
        for (int r4 : r8) {
            Integer valueOf = Integer.valueOf(r4);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < r7.length; r2++) {
            int r1 = r7[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Integer.valueOf(r1));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Integer.valueOf(r1));
                }
                bitSet.set(r2);
            }
        }
        return (int[]) removeAll(r7, bitSet);
    }

    public static char[] removeAll(char[] cArr, int... r1) {
        return (char[]) removeAll((Object) cArr, r1);
    }

    public static char[] removeElements(char[] cArr, char... cArr2) {
        if (isEmpty(cArr) || isEmpty(cArr2)) {
            return clone(cArr);
        }
        HashMap hashMap = new HashMap(cArr2.length);
        for (char c : cArr2) {
            Character valueOf = Character.valueOf(c);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < cArr.length; r2++) {
            char c2 = cArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Character.valueOf(c2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Character.valueOf(c2));
                }
                bitSet.set(r2);
            }
        }
        return (char[]) removeAll(cArr, bitSet);
    }

    public static long[] removeAll(long[] jArr, int... r1) {
        return (long[]) removeAll((Object) jArr, r1);
    }

    public static long[] removeElements(long[] jArr, long... jArr2) {
        if (isEmpty(jArr) || isEmpty(jArr2)) {
            return clone(jArr);
        }
        HashMap hashMap = new HashMap(jArr2.length);
        for (long j : jArr2) {
            Long valueOf = Long.valueOf(j);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < jArr.length; r2++) {
            long j2 = jArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Long.valueOf(j2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Long.valueOf(j2));
                }
                bitSet.set(r2);
            }
        }
        return (long[]) removeAll(jArr, bitSet);
    }

    public static float[] removeAll(float[] fArr, int... r1) {
        return (float[]) removeAll((Object) fArr, r1);
    }

    public static float[] removeElements(float[] fArr, float... fArr2) {
        if (isEmpty(fArr) || isEmpty(fArr2)) {
            return clone(fArr);
        }
        HashMap hashMap = new HashMap(fArr2.length);
        for (float f : fArr2) {
            Float valueOf = Float.valueOf(f);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < fArr.length; r2++) {
            float f2 = fArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Float.valueOf(f2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Float.valueOf(f2));
                }
                bitSet.set(r2);
            }
        }
        return (float[]) removeAll(fArr, bitSet);
    }

    public static double[] removeAll(double[] dArr, int... r1) {
        return (double[]) removeAll((Object) dArr, r1);
    }

    public static double[] removeElements(double[] dArr, double... dArr2) {
        if (isEmpty(dArr) || isEmpty(dArr2)) {
            return clone(dArr);
        }
        HashMap hashMap = new HashMap(dArr2.length);
        for (double d : dArr2) {
            Double valueOf = Double.valueOf(d);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < dArr.length; r2++) {
            double d2 = dArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Double.valueOf(d2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Double.valueOf(d2));
                }
                bitSet.set(r2);
            }
        }
        return (double[]) removeAll(dArr, bitSet);
    }

    public static boolean[] removeAll(boolean[] zArr, int... r1) {
        return (boolean[]) removeAll((Object) zArr, r1);
    }

    public static boolean[] removeElements(boolean[] zArr, boolean... zArr2) {
        if (isEmpty(zArr) || isEmpty(zArr2)) {
            return clone(zArr);
        }
        HashMap hashMap = new HashMap(2);
        for (boolean z : zArr2) {
            Boolean valueOf = Boolean.valueOf(z);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf);
            if (mutableInt == null) {
                hashMap.put(valueOf, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int r2 = 0; r2 < zArr.length; r2++) {
            boolean z2 = zArr[r2];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Boolean.valueOf(z2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Boolean.valueOf(z2));
                }
                bitSet.set(r2);
            }
        }
        return (boolean[]) removeAll(zArr, bitSet);
    }

    static Object removeAll(Object obj, int... r9) {
        int r3;
        int r5;
        int length = getLength(obj);
        int[] clone = clone(r9);
        Arrays.sort(clone);
        if (isNotEmpty(clone)) {
            int length2 = clone.length;
            int r4 = length;
            r3 = 0;
            while (true) {
                length2--;
                if (length2 < 0) {
                    break;
                }
                r5 = clone[length2];
                if (r5 < 0 || r5 >= length) {
                    break;
                } else if (r5 < r4) {
                    r3++;
                    r4 = r5;
                }
            }
            throw new IndexOutOfBoundsException("Index: " + r5 + ", Length: " + length);
        }
        r3 = 0;
        int r42 = length - r3;
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), r42);
        if (r3 < length) {
            int length3 = clone.length - 1;
            while (length3 >= 0) {
                int r6 = clone[length3];
                int r0 = length - r6;
                if (r0 > 1) {
                    int r02 = r0 - 1;
                    r42 -= r02;
                    System.arraycopy(obj, r6 + 1, newInstance, r42, r02);
                }
                length3--;
                length = r6;
            }
            if (length > 0) {
                System.arraycopy(obj, 0, newInstance, 0, length);
            }
        }
        return newInstance;
    }

    static Object removeAll(Object obj, BitSet bitSet) {
        int length = getLength(obj);
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), length - bitSet.cardinality());
        int r2 = 0;
        int r3 = 0;
        while (true) {
            int nextSetBit = bitSet.nextSetBit(r2);
            if (nextSetBit == -1) {
                break;
            }
            int r5 = nextSetBit - r2;
            if (r5 > 0) {
                System.arraycopy(obj, r2, newInstance, r3, r5);
                r3 += r5;
            }
            r2 = bitSet.nextClearBit(nextSetBit);
        }
        int r0 = length - r2;
        if (r0 > 0) {
            System.arraycopy(obj, r2, newInstance, r3, r0);
        }
        return newInstance;
    }

    public static <T extends Comparable<? super T>> boolean isSorted(T[] tArr) {
        return isSorted(tArr, new Comparator<T>() { // from class: org.apache.commons.lang3.ArrayUtils.1
            /* JADX WARN: Incorrect types in method signature: (TT;TT;)I */
            @Override // java.util.Comparator
            public int compare(Comparable comparable, Comparable comparable2) {
                return comparable.compareTo(comparable2);
            }
        });
    }

    public static <T> boolean isSorted(T[] tArr, Comparator<T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator should not be null.");
        }
        if (tArr != null && tArr.length >= 2) {
            T t = tArr[0];
            int length = tArr.length;
            int r4 = 1;
            while (r4 < length) {
                T t2 = tArr[r4];
                if (comparator.compare(t, t2) > 0) {
                    return false;
                }
                r4++;
                t = t2;
            }
        }
        return true;
    }

    public static boolean isSorted(int[] r6) {
        if (r6 != null && r6.length >= 2) {
            int r2 = r6[0];
            int length = r6.length;
            int r4 = 1;
            while (r4 < length) {
                int r5 = r6[r4];
                if (NumberUtils.compare(r2, r5) > 0) {
                    return false;
                }
                r4++;
                r2 = r5;
            }
        }
        return true;
    }

    public static boolean isSorted(long[] jArr) {
        if (jArr != null && jArr.length >= 2) {
            long j = jArr[0];
            int length = jArr.length;
            int r5 = 1;
            while (r5 < length) {
                long j2 = jArr[r5];
                if (NumberUtils.compare(j, j2) > 0) {
                    return false;
                }
                r5++;
                j = j2;
            }
        }
        return true;
    }

    public static boolean isSorted(short[] sArr) {
        if (sArr != null && sArr.length >= 2) {
            short s = sArr[0];
            int length = sArr.length;
            int r4 = 1;
            while (r4 < length) {
                short s2 = sArr[r4];
                if (NumberUtils.compare(s, s2) > 0) {
                    return false;
                }
                r4++;
                s = s2;
            }
        }
        return true;
    }

    public static boolean isSorted(double[] dArr) {
        if (dArr != null && dArr.length >= 2) {
            double d = dArr[0];
            int length = dArr.length;
            int r5 = 1;
            while (r5 < length) {
                double d2 = dArr[r5];
                if (Double.compare(d, d2) > 0) {
                    return false;
                }
                r5++;
                d = d2;
            }
        }
        return true;
    }

    public static boolean isSorted(float[] fArr) {
        if (fArr != null && fArr.length >= 2) {
            float f = fArr[0];
            int length = fArr.length;
            int r4 = 1;
            while (r4 < length) {
                float f2 = fArr[r4];
                if (Float.compare(f, f2) > 0) {
                    return false;
                }
                r4++;
                f = f2;
            }
        }
        return true;
    }

    public static boolean isSorted(byte[] bArr) {
        if (bArr != null && bArr.length >= 2) {
            byte b = bArr[0];
            int length = bArr.length;
            int r4 = 1;
            while (r4 < length) {
                byte b2 = bArr[r4];
                if (NumberUtils.compare(b, b2) > 0) {
                    return false;
                }
                r4++;
                b = b2;
            }
        }
        return true;
    }

    public static boolean isSorted(char[] cArr) {
        if (cArr != null && cArr.length >= 2) {
            char c = cArr[0];
            int length = cArr.length;
            int r4 = 1;
            while (r4 < length) {
                char c2 = cArr[r4];
                if (CharUtils.compare(c, c2) > 0) {
                    return false;
                }
                r4++;
                c = c2;
            }
        }
        return true;
    }

    public static boolean isSorted(boolean[] zArr) {
        if (zArr != null && zArr.length >= 2) {
            boolean z = zArr[0];
            int length = zArr.length;
            int r4 = 1;
            while (r4 < length) {
                boolean z2 = zArr[r4];
                if (BooleanUtils.compare(z, z2) > 0) {
                    return false;
                }
                r4++;
                z = z2;
            }
        }
        return true;
    }

    public static boolean[] removeAllOccurences(boolean[] zArr, boolean z) {
        int indexOf = indexOf(zArr, z);
        if (indexOf == -1) {
            return clone(zArr);
        }
        int[] r2 = new int[zArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(zArr, z, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(zArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static char[] removeAllOccurences(char[] cArr, char c) {
        int indexOf = indexOf(cArr, c);
        if (indexOf == -1) {
            return clone(cArr);
        }
        int[] r2 = new int[cArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(cArr, c, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(cArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static byte[] removeAllOccurences(byte[] bArr, byte b) {
        int indexOf = indexOf(bArr, b);
        if (indexOf == -1) {
            return clone(bArr);
        }
        int[] r2 = new int[bArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(bArr, b, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(bArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static short[] removeAllOccurences(short[] sArr, short s) {
        int indexOf = indexOf(sArr, s);
        if (indexOf == -1) {
            return clone(sArr);
        }
        int[] r2 = new int[sArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(sArr, s, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(sArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static int[] removeAllOccurences(int[] r6, int r7) {
        int indexOf = indexOf(r6, r7);
        if (indexOf == -1) {
            return clone(r6);
        }
        int[] r2 = new int[r6.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(r6, r7, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(r6, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static long[] removeAllOccurences(long[] jArr, long j) {
        int indexOf = indexOf(jArr, j);
        if (indexOf == -1) {
            return clone(jArr);
        }
        int[] r2 = new int[jArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(jArr, j, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(jArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static float[] removeAllOccurences(float[] fArr, float f) {
        int indexOf = indexOf(fArr, f);
        if (indexOf == -1) {
            return clone(fArr);
        }
        int[] r2 = new int[fArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(fArr, f, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(fArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static double[] removeAllOccurences(double[] dArr, double d) {
        int indexOf = indexOf(dArr, d);
        if (indexOf == -1) {
            return clone(dArr);
        }
        int[] r2 = new int[dArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(dArr, d, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return removeAll(dArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static <T> T[] removeAllOccurences(T[] tArr, T t) {
        int indexOf = indexOf(tArr, t);
        if (indexOf == -1) {
            return (T[]) clone(tArr);
        }
        int[] r2 = new int[tArr.length - indexOf];
        r2[0] = indexOf;
        int r3 = 1;
        while (true) {
            int indexOf2 = indexOf(tArr, t, r2[r3 - 1] + 1);
            if (indexOf2 != -1) {
                r2[r3] = indexOf2;
                r3++;
            } else {
                return (T[]) removeAll((Object[]) tArr, Arrays.copyOf(r2, r3));
            }
        }
    }

    public static String[] toStringArray(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return EMPTY_STRING_ARRAY;
        }
        String[] strArr = new String[objArr.length];
        for (int r1 = 0; r1 < objArr.length; r1++) {
            strArr[r1] = objArr[r1].toString();
        }
        return strArr;
    }

    public static String[] toStringArray(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return EMPTY_STRING_ARRAY;
        }
        String[] strArr = new String[objArr.length];
        for (int r1 = 0; r1 < objArr.length; r1++) {
            Object obj = objArr[r1];
            strArr[r1] = obj == null ? str : obj.toString();
        }
        return strArr;
    }

    public static boolean[] insert(int r3, boolean[] zArr, boolean... zArr2) {
        if (zArr == null) {
            return null;
        }
        if (zArr2 == null || zArr2.length == 0) {
            return clone(zArr);
        }
        if (r3 < 0 || r3 > zArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + zArr.length);
        }
        boolean[] zArr3 = new boolean[zArr.length + zArr2.length];
        System.arraycopy(zArr2, 0, zArr3, r3, zArr2.length);
        if (r3 > 0) {
            System.arraycopy(zArr, 0, zArr3, 0, r3);
        }
        if (r3 < zArr.length) {
            System.arraycopy(zArr, r3, zArr3, zArr2.length + r3, zArr.length - r3);
        }
        return zArr3;
    }

    public static byte[] insert(int r3, byte[] bArr, byte... bArr2) {
        if (bArr == null) {
            return null;
        }
        if (bArr2 == null || bArr2.length == 0) {
            return clone(bArr);
        }
        if (r3 < 0 || r3 > bArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + bArr.length);
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr2, 0, bArr3, r3, bArr2.length);
        if (r3 > 0) {
            System.arraycopy(bArr, 0, bArr3, 0, r3);
        }
        if (r3 < bArr.length) {
            System.arraycopy(bArr, r3, bArr3, bArr2.length + r3, bArr.length - r3);
        }
        return bArr3;
    }

    public static char[] insert(int r3, char[] cArr, char... cArr2) {
        if (cArr == null) {
            return null;
        }
        if (cArr2 == null || cArr2.length == 0) {
            return clone(cArr);
        }
        if (r3 < 0 || r3 > cArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + cArr.length);
        }
        char[] cArr3 = new char[cArr.length + cArr2.length];
        System.arraycopy(cArr2, 0, cArr3, r3, cArr2.length);
        if (r3 > 0) {
            System.arraycopy(cArr, 0, cArr3, 0, r3);
        }
        if (r3 < cArr.length) {
            System.arraycopy(cArr, r3, cArr3, cArr2.length + r3, cArr.length - r3);
        }
        return cArr3;
    }

    public static double[] insert(int r3, double[] dArr, double... dArr2) {
        if (dArr == null) {
            return null;
        }
        if (dArr2 == null || dArr2.length == 0) {
            return clone(dArr);
        }
        if (r3 < 0 || r3 > dArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + dArr.length);
        }
        double[] dArr3 = new double[dArr.length + dArr2.length];
        System.arraycopy(dArr2, 0, dArr3, r3, dArr2.length);
        if (r3 > 0) {
            System.arraycopy(dArr, 0, dArr3, 0, r3);
        }
        if (r3 < dArr.length) {
            System.arraycopy(dArr, r3, dArr3, dArr2.length + r3, dArr.length - r3);
        }
        return dArr3;
    }

    public static float[] insert(int r3, float[] fArr, float... fArr2) {
        if (fArr == null) {
            return null;
        }
        if (fArr2 == null || fArr2.length == 0) {
            return clone(fArr);
        }
        if (r3 < 0 || r3 > fArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + fArr.length);
        }
        float[] fArr3 = new float[fArr.length + fArr2.length];
        System.arraycopy(fArr2, 0, fArr3, r3, fArr2.length);
        if (r3 > 0) {
            System.arraycopy(fArr, 0, fArr3, 0, r3);
        }
        if (r3 < fArr.length) {
            System.arraycopy(fArr, r3, fArr3, fArr2.length + r3, fArr.length - r3);
        }
        return fArr3;
    }

    public static int[] insert(int r3, int[] r4, int... r5) {
        if (r4 == null) {
            return null;
        }
        if (r5 == null || r5.length == 0) {
            return clone(r4);
        }
        if (r3 < 0 || r3 > r4.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + r4.length);
        }
        int[] r0 = new int[r4.length + r5.length];
        System.arraycopy(r5, 0, r0, r3, r5.length);
        if (r3 > 0) {
            System.arraycopy(r4, 0, r0, 0, r3);
        }
        if (r3 < r4.length) {
            System.arraycopy(r4, r3, r0, r5.length + r3, r4.length - r3);
        }
        return r0;
    }

    public static long[] insert(int r3, long[] jArr, long... jArr2) {
        if (jArr == null) {
            return null;
        }
        if (jArr2 == null || jArr2.length == 0) {
            return clone(jArr);
        }
        if (r3 < 0 || r3 > jArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + jArr.length);
        }
        long[] jArr3 = new long[jArr.length + jArr2.length];
        System.arraycopy(jArr2, 0, jArr3, r3, jArr2.length);
        if (r3 > 0) {
            System.arraycopy(jArr, 0, jArr3, 0, r3);
        }
        if (r3 < jArr.length) {
            System.arraycopy(jArr, r3, jArr3, jArr2.length + r3, jArr.length - r3);
        }
        return jArr3;
    }

    public static short[] insert(int r3, short[] sArr, short... sArr2) {
        if (sArr == null) {
            return null;
        }
        if (sArr2 == null || sArr2.length == 0) {
            return clone(sArr);
        }
        if (r3 < 0 || r3 > sArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + sArr.length);
        }
        short[] sArr3 = new short[sArr.length + sArr2.length];
        System.arraycopy(sArr2, 0, sArr3, r3, sArr2.length);
        if (r3 > 0) {
            System.arraycopy(sArr, 0, sArr3, 0, r3);
        }
        if (r3 < sArr.length) {
            System.arraycopy(sArr, r3, sArr3, sArr2.length + r3, sArr.length - r3);
        }
        return sArr3;
    }

    @SafeVarargs
    public static <T> T[] insert(int r3, T[] tArr, T... tArr2) {
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || tArr2.length == 0) {
            return (T[]) clone(tArr);
        }
        if (r3 < 0 || r3 > tArr.length) {
            throw new IndexOutOfBoundsException("Index: " + r3 + ", Length: " + tArr.length);
        }
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), tArr.length + tArr2.length));
        System.arraycopy(tArr2, 0, tArr3, r3, tArr2.length);
        if (r3 > 0) {
            System.arraycopy(tArr, 0, tArr3, 0, r3);
        }
        if (r3 < tArr.length) {
            System.arraycopy(tArr, r3, tArr3, tArr2.length + r3, tArr.length - r3);
        }
        return tArr3;
    }

    public static void shuffle(Object[] objArr) {
        shuffle(objArr, new Random());
    }

    public static void shuffle(Object[] objArr, Random random) {
        for (int length = objArr.length; length > 1; length--) {
            swap(objArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(boolean[] zArr) {
        shuffle(zArr, new Random());
    }

    public static void shuffle(boolean[] zArr, Random random) {
        for (int length = zArr.length; length > 1; length--) {
            swap(zArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(byte[] bArr) {
        shuffle(bArr, new Random());
    }

    public static void shuffle(byte[] bArr, Random random) {
        for (int length = bArr.length; length > 1; length--) {
            swap(bArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(char[] cArr) {
        shuffle(cArr, new Random());
    }

    public static void shuffle(char[] cArr, Random random) {
        for (int length = cArr.length; length > 1; length--) {
            swap(cArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(short[] sArr) {
        shuffle(sArr, new Random());
    }

    public static void shuffle(short[] sArr, Random random) {
        for (int length = sArr.length; length > 1; length--) {
            swap(sArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(int[] r1) {
        shuffle(r1, new Random());
    }

    public static void shuffle(int[] r4, Random random) {
        for (int length = r4.length; length > 1; length--) {
            swap(r4, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(long[] jArr) {
        shuffle(jArr, new Random());
    }

    public static void shuffle(long[] jArr, Random random) {
        for (int length = jArr.length; length > 1; length--) {
            swap(jArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(float[] fArr) {
        shuffle(fArr, new Random());
    }

    public static void shuffle(float[] fArr, Random random) {
        for (int length = fArr.length; length > 1; length--) {
            swap(fArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static void shuffle(double[] dArr) {
        shuffle(dArr, new Random());
    }

    public static void shuffle(double[] dArr, Random random) {
        for (int length = dArr.length; length > 1; length--) {
            swap(dArr, length - 1, random.nextInt(length), 1);
        }
    }

    public static <T> boolean isArrayIndexValid(T[] tArr, int r3) {
        return getLength(tArr) != 0 && tArr.length > r3 && r3 >= 0;
    }
}
