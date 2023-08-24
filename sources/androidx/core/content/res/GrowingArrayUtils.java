package androidx.core.content.res;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
final class GrowingArrayUtils {
    public static int growSize(int r1) {
        if (r1 <= 4) {
            return 8;
        }
        return r1 * 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object[], java.lang.Object] */
    public static <T> T[] append(T[] tArr, int r3, T t) {
        if (r3 + 1 > tArr.length) {
            ?? r0 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), growSize(r3));
            System.arraycopy(tArr, 0, r0, 0, r3);
            tArr = r0;
        }
        tArr[r3] = t;
        return tArr;
    }

    public static int[] append(int[] r2, int r3, int r4) {
        if (r3 + 1 > r2.length) {
            int[] r0 = new int[growSize(r3)];
            System.arraycopy(r2, 0, r0, 0, r3);
            r2 = r0;
        }
        r2[r3] = r4;
        return r2;
    }

    public static long[] append(long[] jArr, int r3, long j) {
        if (r3 + 1 > jArr.length) {
            long[] jArr2 = new long[growSize(r3)];
            System.arraycopy(jArr, 0, jArr2, 0, r3);
            jArr = jArr2;
        }
        jArr[r3] = j;
        return jArr;
    }

    public static boolean[] append(boolean[] zArr, int r3, boolean z) {
        if (r3 + 1 > zArr.length) {
            boolean[] zArr2 = new boolean[growSize(r3)];
            System.arraycopy(zArr, 0, zArr2, 0, r3);
            zArr = zArr2;
        }
        zArr[r3] = z;
        return zArr;
    }

    public static <T> T[] insert(T[] tArr, int r3, int r4, T t) {
        if (r3 + 1 <= tArr.length) {
            System.arraycopy(tArr, r4, tArr, r4 + 1, r3 - r4);
            tArr[r4] = t;
            return tArr;
        }
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), growSize(r3)));
        System.arraycopy(tArr, 0, tArr2, 0, r4);
        tArr2[r4] = t;
        System.arraycopy(tArr, r4, tArr2, r4 + 1, tArr.length - r4);
        return tArr2;
    }

    public static int[] insert(int[] r2, int r3, int r4, int r5) {
        if (r3 + 1 <= r2.length) {
            System.arraycopy(r2, r4, r2, r4 + 1, r3 - r4);
            r2[r4] = r5;
            return r2;
        }
        int[] r32 = new int[growSize(r3)];
        System.arraycopy(r2, 0, r32, 0, r4);
        r32[r4] = r5;
        System.arraycopy(r2, r4, r32, r4 + 1, r2.length - r4);
        return r32;
    }

    public static long[] insert(long[] jArr, int r3, int r4, long j) {
        if (r3 + 1 <= jArr.length) {
            System.arraycopy(jArr, r4, jArr, r4 + 1, r3 - r4);
            jArr[r4] = j;
            return jArr;
        }
        long[] jArr2 = new long[growSize(r3)];
        System.arraycopy(jArr, 0, jArr2, 0, r4);
        jArr2[r4] = j;
        System.arraycopy(jArr, r4, jArr2, r4 + 1, jArr.length - r4);
        return jArr2;
    }

    public static boolean[] insert(boolean[] zArr, int r3, int r4, boolean z) {
        if (r3 + 1 <= zArr.length) {
            System.arraycopy(zArr, r4, zArr, r4 + 1, r3 - r4);
            zArr[r4] = z;
            return zArr;
        }
        boolean[] zArr2 = new boolean[growSize(r3)];
        System.arraycopy(zArr, 0, zArr2, 0, r4);
        zArr2[r4] = z;
        System.arraycopy(zArr, r4, zArr2, r4 + 1, zArr.length - r4);
        return zArr2;
    }

    private GrowingArrayUtils() {
    }
}
