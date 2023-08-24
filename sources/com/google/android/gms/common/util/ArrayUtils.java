package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public final class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> T[] concat(T[]... tArr) {
        if (tArr.length != 0) {
            int r2 = 0;
            for (T[] tArr2 : tArr) {
                r2 += tArr2.length;
            }
            T[] tArr3 = (T[]) Arrays.copyOf(tArr[0], r2);
            int length = tArr[0].length;
            for (int r3 = 1; r3 < tArr.length; r3++) {
                T[] tArr4 = tArr[r3];
                int length2 = tArr4.length;
                System.arraycopy(tArr4, 0, tArr3, length, length2);
                length += length2;
            }
            return tArr3;
        }
        return (T[]) ((Object[]) Array.newInstance(tArr.getClass(), 0));
    }

    public static byte[] concatByteArrays(byte[]... bArr) {
        if (bArr.length != 0) {
            int r2 = 0;
            for (byte[] bArr2 : bArr) {
                r2 += bArr2.length;
            }
            byte[] copyOf = Arrays.copyOf(bArr[0], r2);
            int length = bArr[0].length;
            for (int r3 = 1; r3 < bArr.length; r3++) {
                byte[] bArr3 = bArr[r3];
                int length2 = bArr3.length;
                System.arraycopy(bArr3, 0, copyOf, length, length2);
                length += length2;
            }
            return copyOf;
        }
        return new byte[0];
    }

    public static boolean contains(int[] r4, int r5) {
        if (r4 == null) {
            return false;
        }
        for (int r3 : r4) {
            if (r3 == r5) {
                return true;
            }
        }
        return false;
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> T[] removeAll(T[] tArr, T... tArr2) {
        int length;
        int r5;
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || (length = tArr2.length) == 0) {
            return (T[]) Arrays.copyOf(tArr, tArr.length);
        }
        T[] tArr3 = (T[]) ((Object[]) Array.newInstance(tArr2.getClass().getComponentType(), tArr.length));
        if (length == 1) {
            r5 = 0;
            for (T t : tArr) {
                if (!Objects.equal(tArr2[0], t)) {
                    tArr3[r5] = t;
                    r5++;
                }
            }
        } else {
            int r1 = 0;
            for (T t2 : tArr) {
                if (!contains(tArr2, t2)) {
                    tArr3[r1] = t2;
                    r1++;
                }
            }
            r5 = r1;
        }
        if (tArr3 == null) {
            return null;
        }
        return r5 == tArr3.length ? tArr3 : (T[]) Arrays.copyOf(tArr3, r5);
    }

    public static <T> ArrayList<T> toArrayList(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        for (T t : tArr) {
            arrayList.add(t);
        }
        return arrayList;
    }

    public static int[] toPrimitiveArray(Collection<Integer> collection) {
        int r0 = 0;
        if (collection == null || collection.isEmpty()) {
            return new int[0];
        }
        int[] r1 = new int[collection.size()];
        for (Integer num : collection) {
            r1[r0] = num.intValue();
            r0++;
        }
        return r1;
    }

    public static Integer[] toWrapperArray(int[] r4) {
        if (r4 == null) {
            return null;
        }
        int length = r4.length;
        Integer[] numArr = new Integer[length];
        for (int r2 = 0; r2 < length; r2++) {
            numArr[r2] = Integer.valueOf(r4[r2]);
        }
        return numArr;
    }

    public static void writeArray(StringBuilder sb, double[] dArr) {
        int length = dArr.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (r1 != 0) {
                sb.append(",");
            }
            sb.append(Double.toString(dArr[r1]));
        }
    }

    public static void writeStringArray(StringBuilder sb, String[] strArr) {
        int length = strArr.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (r1 != 0) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(strArr[r1]);
            sb.append("\"");
        }
    }

    public static <T> boolean contains(T[] tArr, T t) {
        int length = tArr != null ? tArr.length : 0;
        int r2 = 0;
        while (true) {
            if (r2 >= length) {
                break;
            } else if (!Objects.equal(tArr[r2], t)) {
                r2++;
            } else if (r2 >= 0) {
                return true;
            }
        }
        return false;
    }

    public static void writeArray(StringBuilder sb, float[] fArr) {
        int length = fArr.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (r1 != 0) {
                sb.append(",");
            }
            sb.append(Float.toString(fArr[r1]));
        }
    }

    public static void writeArray(StringBuilder sb, int[] r4) {
        int length = r4.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (r1 != 0) {
                sb.append(",");
            }
            sb.append(Integer.toString(r4[r1]));
        }
    }

    public static void writeArray(StringBuilder sb, long[] jArr) {
        int length = jArr.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (r1 != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(jArr[r1]));
        }
    }

    public static <T> void writeArray(StringBuilder sb, T[] tArr) {
        int length = tArr.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (r1 != 0) {
                sb.append(",");
            }
            sb.append(tArr[r1]);
        }
    }

    public static void writeArray(StringBuilder sb, boolean[] zArr) {
        int length = zArr.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (r1 != 0) {
                sb.append(",");
            }
            sb.append(Boolean.toString(zArr[r1]));
        }
    }
}
