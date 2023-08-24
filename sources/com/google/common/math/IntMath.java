package com.google.common.math;

import com.google.android.exoplayer2.audio.AacUtil;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.onesignal.NotificationBundleProcessor;
import java.math.RoundingMode;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class IntMath {
    static final int FLOOR_SQRT_MAX_INT = 46340;
    static final int MAX_POWER_OF_SQRT2_UNSIGNED = -1257966797;
    static final int MAX_SIGNED_POWER_OF_TWO = 1073741824;
    static final byte[] maxLog10ForLeadingZeros = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    static final int[] powersOf10 = {1, 10, 100, 1000, 10000, AacUtil.AAC_LC_MAX_RATE_BYTES_PER_SECOND, 1000000, 10000000, 100000000, 1000000000};
    static final int[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    static int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    public static boolean isPowerOfTwo(int r4) {
        return (r4 > 0) & ((r4 & (r4 + (-1))) == 0);
    }

    static int lessThanBranchFree(int r0, int r1) {
        return (~(~(r0 - r1))) >>> 31;
    }

    public static int mean(int r1, int r2) {
        return (r1 & r2) + ((r1 ^ r2) >> 1);
    }

    public static int ceilingPowerOfTwo(int r3) {
        MathPreconditions.checkPositive("x", r3);
        if (r3 > 1073741824) {
            StringBuilder sb = new StringBuilder(58);
            sb.append("ceilingPowerOfTwo(");
            sb.append(r3);
            sb.append(") not representable as an int");
            throw new ArithmeticException(sb.toString());
        }
        return 1 << (-Integer.numberOfLeadingZeros(r3 - 1));
    }

    public static int floorPowerOfTwo(int r1) {
        MathPreconditions.checkPositive("x", r1);
        return Integer.highestOneBit(r1);
    }

    /* renamed from: com.google.common.math.IntMath$1 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C30861 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] r0 = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = r0;
            try {
                r0[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(int r1, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", r1);
        switch (C30861.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(r1));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(r1 - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(r1);
                return (31 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, r1);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(r1);
    }

    public static int log10(int r3, RoundingMode roundingMode) {
        int lessThanBranchFree;
        MathPreconditions.checkPositive("x", r3);
        int log10Floor = log10Floor(r3);
        int r1 = powersOf10[log10Floor];
        switch (C30861.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(r3 == r1);
                return log10Floor;
            case 2:
            case 3:
                return log10Floor;
            case 4:
            case 5:
                lessThanBranchFree = lessThanBranchFree(r1, r3);
                return log10Floor + lessThanBranchFree;
            case 6:
            case 7:
            case 8:
                lessThanBranchFree = lessThanBranchFree(halfPowersOf10[log10Floor], r3);
                return log10Floor + lessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int log10Floor(int r2) {
        byte b = maxLog10ForLeadingZeros[Integer.numberOfLeadingZeros(r2)];
        return b - lessThanBranchFree(r2, powersOf10[b]);
    }

    public static int pow(int r4, int r5) {
        MathPreconditions.checkNonNegative("exponent", r5);
        if (r4 == -2) {
            if (r5 < 32) {
                return (r5 & 1) == 0 ? 1 << r5 : -(1 << r5);
            }
            return 0;
        } else if (r4 == -1) {
            return (r5 & 1) == 0 ? 1 : -1;
        } else if (r4 == 0) {
            return r5 == 0 ? 1 : 0;
        } else if (r4 != 1) {
            if (r4 == 2) {
                if (r5 < 32) {
                    return 1 << r5;
                }
                return 0;
            }
            int r0 = 1;
            while (r5 != 0) {
                if (r5 == 1) {
                    return r4 * r0;
                }
                r0 *= (r5 & 1) == 0 ? 1 : r4;
                r4 *= r4;
                r5 >>= 1;
            }
            return r0;
        } else {
            return 1;
        }
    }

    public static int sqrt(int r2, RoundingMode roundingMode) {
        int lessThanBranchFree;
        MathPreconditions.checkNonNegative("x", r2);
        int sqrtFloor = sqrtFloor(r2);
        switch (C30861.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(sqrtFloor * sqrtFloor == r2);
                return sqrtFloor;
            case 2:
            case 3:
                return sqrtFloor;
            case 4:
            case 5:
                lessThanBranchFree = lessThanBranchFree(sqrtFloor * sqrtFloor, r2);
                return sqrtFloor + lessThanBranchFree;
            case 6:
            case 7:
            case 8:
                lessThanBranchFree = lessThanBranchFree((sqrtFloor * sqrtFloor) + sqrtFloor, r2);
                return sqrtFloor + lessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int sqrtFloor(int r2) {
        return (int) Math.sqrt(r2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
        if (((r7 == java.math.RoundingMode.HALF_EVEN) & ((r0 & 1) != 0)) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r1 > 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004a, code lost:
        if (r5 > 0) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004d, code lost:
        if (r5 < 0) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int divide(int r5, int r6, java.math.RoundingMode r7) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r7)
            if (r6 == 0) goto L5c
            int r0 = r5 / r6
            int r1 = r6 * r0
            int r1 = r5 - r1
            if (r1 != 0) goto Le
            return r0
        Le:
            r5 = r5 ^ r6
            int r5 = r5 >> 31
            r2 = 1
            r5 = r5 | r2
            int[] r3 = com.google.common.math.IntMath.C30861.$SwitchMap$java$math$RoundingMode
            int r4 = r7.ordinal()
            r3 = r3[r4]
            r4 = 0
            switch(r3) {
                case 1: goto L50;
                case 2: goto L57;
                case 3: goto L4d;
                case 4: goto L58;
                case 5: goto L4a;
                case 6: goto L25;
                case 7: goto L25;
                case 8: goto L25;
                default: goto L1f;
            }
        L1f:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L25:
            int r1 = java.lang.Math.abs(r1)
            int r6 = java.lang.Math.abs(r6)
            int r6 = r6 - r1
            int r1 = r1 - r6
            if (r1 != 0) goto L47
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_UP
            if (r7 == r6) goto L58
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_EVEN
            if (r7 != r6) goto L3b
            r6 = 1
            goto L3c
        L3b:
            r6 = 0
        L3c:
            r7 = r0 & 1
            if (r7 == 0) goto L42
            r7 = 1
            goto L43
        L42:
            r7 = 0
        L43:
            r6 = r6 & r7
            if (r6 == 0) goto L57
            goto L58
        L47:
            if (r1 <= 0) goto L57
            goto L58
        L4a:
            if (r5 <= 0) goto L57
            goto L58
        L4d:
            if (r5 >= 0) goto L57
            goto L58
        L50:
            if (r1 != 0) goto L53
            goto L54
        L53:
            r2 = 0
        L54:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r2)
        L57:
            r2 = 0
        L58:
            if (r2 == 0) goto L5b
            int r0 = r0 + r5
        L5b:
            return r0
        L5c:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "/ by zero"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.IntMath.divide(int, int, java.math.RoundingMode):int");
    }

    public static int mod(int r2, int r3) {
        if (r3 <= 0) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Modulus ");
            sb.append(r3);
            sb.append(" must be > 0");
            throw new ArithmeticException(sb.toString());
        }
        int r22 = r2 % r3;
        return r22 >= 0 ? r22 : r22 + r3;
    }

    public static int gcd(int r3, int r4) {
        MathPreconditions.checkNonNegative(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, r3);
        MathPreconditions.checkNonNegative("b", r4);
        if (r3 == 0) {
            return r4;
        }
        if (r4 == 0) {
            return r3;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(r3);
        int r32 = r3 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(r4);
        int r42 = r4 >> numberOfTrailingZeros2;
        while (r32 != r42) {
            int r33 = r32 - r42;
            int r2 = (r33 >> 31) & r33;
            int r34 = (r33 - r2) - r2;
            r42 += r2;
            r32 = r34 >> Integer.numberOfTrailingZeros(r34);
        }
        return r32 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static int checkedAdd(int r6, int r7) {
        long j = r6 + r7;
        int r2 = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) r2), "checkedAdd", r6, r7);
        return r2;
    }

    public static int checkedSubtract(int r6, int r7) {
        long j = r6 - r7;
        int r2 = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) r2), "checkedSubtract", r6, r7);
        return r2;
    }

    public static int checkedMultiply(int r6, int r7) {
        long j = r6 * r7;
        int r2 = (int) j;
        MathPreconditions.checkNoOverflow(j == ((long) r2), "checkedMultiply", r6, r7);
        return r2;
    }

    public static int checkedPow(int r6, int r7) {
        MathPreconditions.checkNonNegative("exponent", r7);
        if (r6 == -2) {
            MathPreconditions.checkNoOverflow(r7 < 32, "checkedPow", r6, r7);
            return (r7 & 1) == 0 ? 1 << r7 : (-1) << r7;
        } else if (r6 == -1) {
            return (r7 & 1) == 0 ? 1 : -1;
        } else if (r6 == 0) {
            return r7 == 0 ? 1 : 0;
        } else if (r6 != 1) {
            if (r6 == 2) {
                MathPreconditions.checkNoOverflow(r7 < 31, "checkedPow", r6, r7);
                return 1 << r7;
            }
            int r0 = 1;
            while (r7 != 0) {
                if (r7 == 1) {
                    return checkedMultiply(r0, r6);
                }
                if ((r7 & 1) != 0) {
                    r0 = checkedMultiply(r0, r6);
                }
                r7 >>= 1;
                if (r7 > 0) {
                    MathPreconditions.checkNoOverflow((-46340 <= r6) & (r6 <= FLOOR_SQRT_MAX_INT), "checkedPow", r6, r7);
                    r6 *= r6;
                }
            }
            return r0;
        } else {
            return 1;
        }
    }

    public static int saturatedAdd(int r2, int r3) {
        return Ints.saturatedCast(r2 + r3);
    }

    public static int saturatedSubtract(int r2, int r3) {
        return Ints.saturatedCast(r2 - r3);
    }

    public static int saturatedMultiply(int r2, int r3) {
        return Ints.saturatedCast(r2 * r3);
    }

    public static int saturatedPow(int r6, int r7) {
        MathPreconditions.checkNonNegative("exponent", r7);
        if (r6 == -2) {
            return r7 >= 32 ? (r7 & 1) + Integer.MAX_VALUE : (r7 & 1) == 0 ? 1 << r7 : (-1) << r7;
        } else if (r6 == -1) {
            return (r7 & 1) == 0 ? 1 : -1;
        } else if (r6 == 0) {
            return r7 == 0 ? 1 : 0;
        } else if (r6 != 1) {
            if (r6 == 2) {
                if (r7 >= 31) {
                    return Integer.MAX_VALUE;
                }
                return 1 << r7;
            }
            int r2 = ((r6 >>> 31) & r7 & 1) + Integer.MAX_VALUE;
            int r1 = 1;
            while (r7 != 0) {
                if (r7 == 1) {
                    return saturatedMultiply(r1, r6);
                }
                if ((r7 & 1) != 0) {
                    r1 = saturatedMultiply(r1, r6);
                }
                r7 >>= 1;
                if (r7 > 0) {
                    if ((-46340 > r6) || (r6 > FLOOR_SQRT_MAX_INT)) {
                        return r2;
                    }
                    r6 *= r6;
                }
            }
            return r1;
        } else {
            return 1;
        }
    }

    public static int factorial(int r2) {
        MathPreconditions.checkNonNegative(NotificationBundleProcessor.PUSH_MINIFIED_BUTTON_TEXT, r2);
        int[] r0 = factorials;
        if (r2 < r0.length) {
            return r0[r2];
        }
        return Integer.MAX_VALUE;
    }

    public static int binomial(int r5, int r6) {
        MathPreconditions.checkNonNegative(NotificationBundleProcessor.PUSH_MINIFIED_BUTTON_TEXT, r5);
        MathPreconditions.checkNonNegative("k", r6);
        int r0 = 0;
        Preconditions.checkArgument(r6 <= r5, "k (%s) > n (%s)", r6, r5);
        if (r6 > (r5 >> 1)) {
            r6 = r5 - r6;
        }
        int[] r2 = biggestBinomials;
        if (r6 >= r2.length || r5 > r2[r6]) {
            return Integer.MAX_VALUE;
        }
        if (r6 != 0) {
            if (r6 != 1) {
                long j = 1;
                while (r0 < r6) {
                    r0++;
                    j = (j * (r5 - r0)) / r0;
                }
                return (int) j;
            }
            return r5;
        }
        return 1;
    }

    public static boolean isPrime(int r2) {
        return LongMath.isPrime(r2);
    }

    private IntMath() {
    }
}
