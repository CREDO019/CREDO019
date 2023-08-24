package androidx.collection;

/* loaded from: classes.dex */
class ContainerHelpers {
    static final int[] EMPTY_INTS = new int[0];
    static final long[] EMPTY_LONGS = new long[0];
    static final Object[] EMPTY_OBJECTS = new Object[0];

    public static int idealByteArraySize(int r2) {
        for (int r0 = 4; r0 < 32; r0++) {
            int r1 = (1 << r0) - 12;
            if (r2 <= r1) {
                return r1;
            }
        }
        return r2;
    }

    public static int idealIntArraySize(int r0) {
        return idealByteArraySize(r0 * 4) / 4;
    }

    public static int idealLongArraySize(int r0) {
        return idealByteArraySize(r0 * 8) / 8;
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int binarySearch(int[] r3, int r4, int r5) {
        int r42 = r4 - 1;
        int r0 = 0;
        while (r0 <= r42) {
            int r1 = (r0 + r42) >>> 1;
            int r2 = r3[r1];
            if (r2 < r5) {
                r0 = r1 + 1;
            } else if (r2 <= r5) {
                return r1;
            } else {
                r42 = r1 - 1;
            }
        }
        return ~r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int binarySearch(long[] jArr, int r6, long j) {
        int r62 = r6 - 1;
        int r0 = 0;
        while (r0 <= r62) {
            int r1 = (r0 + r62) >>> 1;
            int r4 = (jArr[r1] > j ? 1 : (jArr[r1] == j ? 0 : -1));
            if (r4 < 0) {
                r0 = r1 + 1;
            } else if (r4 <= 0) {
                return r1;
            } else {
                r62 = r1 - 1;
            }
        }
        return ~r0;
    }

    private ContainerHelpers() {
    }
}
