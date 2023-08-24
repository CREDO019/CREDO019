package androidx.core.math;

/* loaded from: classes.dex */
public class MathUtils {
    public static double clamp(double d, double d2, double d3) {
        return d < d2 ? d2 : d > d3 ? d3 : d;
    }

    public static float clamp(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public static int clamp(int r0, int r1, int r2) {
        return r0 < r1 ? r1 : r0 > r2 ? r2 : r0;
    }

    public static long clamp(long j, long j2, long j3) {
        return j < j2 ? j2 : j > j3 ? j3 : j;
    }

    private MathUtils() {
    }

    public static int addExact(int r1, int r2) {
        int r0 = r1 + r2;
        if (((r1 ^ r0) & (r2 ^ r0)) >= 0) {
            return r0;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static long addExact(long j, long j2) {
        long j3 = j + j2;
        if (((j ^ j3) & (j2 ^ j3)) >= 0) {
            return j3;
        }
        throw new ArithmeticException("long overflow");
    }

    public static int subtractExact(int r1, int r2) {
        int r0 = r1 - r2;
        if (((r1 ^ r0) & (r2 ^ r1)) >= 0) {
            return r0;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static long subtractExact(long j, long j2) {
        long j3 = j - j2;
        if (((j ^ j3) & (j2 ^ j)) >= 0) {
            return j3;
        }
        throw new ArithmeticException("long overflow");
    }

    public static int multiplyExact(int r4, int r5) {
        long j = r4 * r5;
        int r42 = (int) j;
        if (r42 == j) {
            return r42;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static long multiplyExact(long j, long j2) {
        long j3 = j * j2;
        if (((Math.abs(j) | Math.abs(j2)) >>> 31) == 0 || ((j2 == 0 || j3 / j2 == j) && !(j == Long.MIN_VALUE && j2 == -1))) {
            return j3;
        }
        throw new ArithmeticException("long overflow");
    }

    public static int incrementExact(int r1) {
        if (r1 != Integer.MAX_VALUE) {
            return r1 + 1;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static long incrementExact(long j) {
        if (j != Long.MAX_VALUE) {
            return j + 1;
        }
        throw new ArithmeticException("long overflow");
    }

    public static int decrementExact(int r1) {
        if (r1 != Integer.MIN_VALUE) {
            return r1 - 1;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static long decrementExact(long j) {
        if (j != Long.MIN_VALUE) {
            return j - 1;
        }
        throw new ArithmeticException("long overflow");
    }

    public static int negateExact(int r1) {
        if (r1 != Integer.MIN_VALUE) {
            return -r1;
        }
        throw new ArithmeticException("integer overflow");
    }

    public static long negateExact(long j) {
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        throw new ArithmeticException("long overflow");
    }

    public static int toIntExact(long j) {
        int r0 = (int) j;
        if (r0 == j) {
            return r0;
        }
        throw new ArithmeticException("integer overflow");
    }
}
