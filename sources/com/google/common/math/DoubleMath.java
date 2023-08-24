package com.google.common.math;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import com.onesignal.NotificationBundleProcessor;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class DoubleMath {
    static final int MAX_FACTORIAL = 170;
    private static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    private static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    private static final double LN_2 = Math.log(2.0d);
    static final double[] everySixteenthFactorial = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    static double roundIntermediate(double d, RoundingMode roundingMode) {
        if (!DoubleUtils.isFinite(d)) {
            throw new ArithmeticException("input is infinite or NaN");
        }
        switch (C30851.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(d));
                return d;
            case 2:
                return (d >= LN_2 || isMathematicalInteger(d)) ? d : ((long) d) - 1;
            case 3:
                return (d <= LN_2 || isMathematicalInteger(d)) ? d : ((long) d) + 1;
            case 4:
                return d;
            case 5:
                if (isMathematicalInteger(d)) {
                    return d;
                }
                return ((long) d) + (d > LN_2 ? 1 : -1);
            case 6:
                return Math.rint(d);
            case 7:
                double rint = Math.rint(d);
                return Math.abs(d - rint) == 0.5d ? d + Math.copySign(0.5d, d) : rint;
            case 8:
                double rint2 = Math.rint(d);
                return Math.abs(d - rint2) == 0.5d ? d : rint2;
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.DoubleMath$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C30851 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] r0 = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = r0;
            try {
                r0[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static int roundToInt(double d, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d, roundingMode);
        MathPreconditions.checkInRangeForRoundingInputs((roundIntermediate < 2.147483648E9d) & (roundIntermediate > -2.147483649E9d), d, roundingMode);
        return (int) roundIntermediate;
    }

    public static long roundToLong(double d, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d, roundingMode);
        MathPreconditions.checkInRangeForRoundingInputs((MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d) & (roundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE), d, roundingMode);
        return (long) roundIntermediate;
    }

    public static BigInteger roundToBigInteger(double d, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d, roundingMode);
        if ((roundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE) & (MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d)) {
            return BigInteger.valueOf((long) roundIntermediate);
        }
        BigInteger shiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(roundIntermediate)).shiftLeft(Math.getExponent(roundIntermediate) - 52);
        return roundIntermediate < LN_2 ? shiftLeft.negate() : shiftLeft;
    }

    public static boolean isPowerOfTwo(double d) {
        if (d <= LN_2 || !DoubleUtils.isFinite(d)) {
            return false;
        }
        long significand = DoubleUtils.getSignificand(d);
        return (significand & (significand - 1)) == 0;
    }

    public static double log2(double d) {
        return Math.log(d) / LN_2;
    }

    public static int log2(double d, RoundingMode roundingMode) {
        boolean isPowerOfTwo;
        Preconditions.checkArgument(d > LN_2 && DoubleUtils.isFinite(d), "x must be positive and finite");
        int exponent = Math.getExponent(d);
        if (!DoubleUtils.isNormal(d)) {
            return log2(d * 4.503599627370496E15d, roundingMode) - 52;
        }
        switch (C30851.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(d));
                break;
            case 2:
                break;
            case 3:
                r0 = !isPowerOfTwo(d);
                break;
            case 4:
                r0 = exponent < 0;
                isPowerOfTwo = isPowerOfTwo(d);
                r0 &= !isPowerOfTwo;
                break;
            case 5:
                r0 = exponent >= 0;
                isPowerOfTwo = isPowerOfTwo(d);
                r0 &= !isPowerOfTwo;
                break;
            case 6:
            case 7:
            case 8:
                double scaleNormalize = DoubleUtils.scaleNormalize(d);
                if (scaleNormalize * scaleNormalize > 2.0d) {
                    r0 = true;
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        return r0 ? exponent + 1 : exponent;
    }

    public static boolean isMathematicalInteger(double d) {
        return DoubleUtils.isFinite(d) && (d == LN_2 || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(d)) <= Math.getExponent(d));
    }

    public static double factorial(int r5) {
        MathPreconditions.checkNonNegative(NotificationBundleProcessor.PUSH_MINIFIED_BUTTON_TEXT, r5);
        if (r5 > MAX_FACTORIAL) {
            return Double.POSITIVE_INFINITY;
        }
        double d = 1.0d;
        int r2 = r5 & (-16);
        while (true) {
            r2++;
            if (r2 > r5) {
                return d * everySixteenthFactorial[r5 >> 4];
            }
            d *= r2;
        }
    }

    public static boolean fuzzyEquals(double d, double d2, double d3) {
        MathPreconditions.checkNonNegative("tolerance", d3);
        return Math.copySign(d - d2, 1.0d) <= d3 || d == d2 || (Double.isNaN(d) && Double.isNaN(d2));
    }

    public static int fuzzyCompare(double d, double d2, double d3) {
        if (fuzzyEquals(d, d2, d3)) {
            return 0;
        }
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        return Booleans.compare(Double.isNaN(d), Double.isNaN(d2));
    }

    @Deprecated
    public static double mean(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0, "Cannot take mean of 0 values");
        double checkFinite = checkFinite(dArr[0]);
        long j = 1;
        for (int r2 = 1; r2 < dArr.length; r2++) {
            checkFinite(dArr[r2]);
            j++;
            checkFinite += (dArr[r2] - checkFinite) / j;
        }
        return checkFinite;
    }

    @Deprecated
    public static double mean(int... r6) {
        Preconditions.checkArgument(r6.length > 0, "Cannot take mean of 0 values");
        long j = 0;
        for (int r0 : r6) {
            j += r0;
        }
        return j / r6.length;
    }

    @Deprecated
    public static double mean(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0, "Cannot take mean of 0 values");
        double d = jArr[0];
        long j = 1;
        for (int r2 = 1; r2 < jArr.length; r2++) {
            j++;
            d += (jArr[r2] - d) / j;
        }
        return d;
    }

    @Deprecated
    public static double mean(Iterable<? extends Number> iterable) {
        return mean(iterable.iterator());
    }

    @Deprecated
    public static double mean(Iterator<? extends Number> it) {
        Preconditions.checkArgument(it.hasNext(), "Cannot take mean of 0 values");
        double checkFinite = checkFinite(it.next().doubleValue());
        long j = 1;
        while (it.hasNext()) {
            j++;
            checkFinite += (checkFinite(it.next().doubleValue()) - checkFinite) / j;
        }
        return checkFinite;
    }

    private static double checkFinite(double d) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d));
        return d;
    }

    private DoubleMath() {
    }
}
