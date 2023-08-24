package org.apache.commons.lang3.math;

import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.math.BigInteger;
import org.apache.commons.lang3.Validate;

/* loaded from: classes5.dex */
public final class Fraction extends Number implements Comparable<Fraction> {
    private static final long serialVersionUID = 65382027393090L;
    private final int denominator;
    private final int numerator;
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    private transient int hashCode = 0;
    private transient String toString = null;
    private transient String toProperString = null;

    private Fraction(int r2, int r3) {
        this.numerator = r2;
        this.denominator = r3;
    }

    public static Fraction getFraction(int r1, int r2) {
        if (r2 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (r2 < 0) {
            if (r1 == Integer.MIN_VALUE || r2 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            r1 = -r1;
            r2 = -r2;
        }
        return new Fraction(r1, r2);
    }

    public static Fraction getFraction(int r4, int r5, int r6) {
        if (r6 != 0) {
            if (r6 >= 0) {
                if (r5 >= 0) {
                    long j = r4 < 0 ? (r4 * r6) - r5 : (r4 * r6) + r5;
                    if (j < -2147483648L || j > 2147483647L) {
                        throw new ArithmeticException("Numerator too large to represent as an Integer.");
                    }
                    return new Fraction((int) j, r6);
                }
                throw new ArithmeticException("The numerator must not be negative");
            }
            throw new ArithmeticException("The denominator must not be negative");
        }
        throw new ArithmeticException("The denominator must not be zero");
    }

    public static Fraction getReducedFraction(int r2, int r3) {
        if (r3 != 0) {
            if (r2 == 0) {
                return ZERO;
            }
            if (r3 == Integer.MIN_VALUE && (r2 & 1) == 0) {
                r2 /= 2;
                r3 /= 2;
            }
            if (r3 < 0) {
                if (r2 == Integer.MIN_VALUE || r3 == Integer.MIN_VALUE) {
                    throw new ArithmeticException("overflow: can't negate");
                }
                r2 = -r2;
                r3 = -r3;
            }
            int greatestCommonDivisor = greatestCommonDivisor(r2, r3);
            return new Fraction(r2 / greatestCommonDivisor, r3 / greatestCommonDivisor);
        }
        throw new ArithmeticException("The denominator must not be zero");
    }

    public static Fraction getFraction(double d) {
        int r1;
        int r0;
        int r12 = d < 0.0d ? -1 : 1;
        double abs = Math.abs(d);
        if (abs > 2.147483647E9d || Double.isNaN(abs)) {
            throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN");
        }
        int r4 = (int) abs;
        double d2 = abs - r4;
        int r5 = (int) d2;
        double d3 = 1.0d;
        double d4 = d2 - r5;
        double d5 = Double.MAX_VALUE;
        int r17 = r12;
        int r122 = 1;
        int r13 = 0;
        int r14 = 0;
        int r15 = 1;
        int r16 = 1;
        while (true) {
            int r02 = (int) (d3 / d4);
            double d6 = d5;
            double d7 = d3 - (r02 * d4);
            int r18 = (r5 * r122) + r13;
            int r52 = (r5 * r14) + r15;
            d5 = Math.abs(d2 - (r18 / r52));
            r1 = r16 + 1;
            if (d6 <= d5 || r52 > 10000 || r52 <= 0) {
                break;
            }
            r0 = 25;
            if (r1 >= 25) {
                break;
            }
            r16 = r1;
            int r20 = r14;
            r14 = r52;
            r5 = r02;
            r13 = r122;
            r122 = r18;
            r15 = r20;
            d3 = d4;
            d4 = d7;
        }
        if (r1 == r0) {
            throw new ArithmeticException("Unable to convert double to fraction");
        }
        return getReducedFraction((r122 + (r4 * r14)) * r17, r14);
    }

    public static Fraction getFraction(String str) {
        Validate.isTrue(str != null, "The string must not be null", new Object[0]);
        if (str.indexOf(46) >= 0) {
            return getFraction(Double.parseDouble(str));
        }
        int indexOf = str.indexOf(32);
        if (indexOf > 0) {
            int parseInt = Integer.parseInt(str.substring(0, indexOf));
            String substring = str.substring(indexOf + 1);
            int indexOf2 = substring.indexOf(47);
            if (indexOf2 < 0) {
                throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
            }
            return getFraction(parseInt, Integer.parseInt(substring.substring(0, indexOf2)), Integer.parseInt(substring.substring(indexOf2 + 1)));
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 < 0) {
            return getFraction(Integer.parseInt(str), 1);
        }
        return getFraction(Integer.parseInt(str.substring(0, indexOf3)), Integer.parseInt(str.substring(indexOf3 + 1)));
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getProperNumerator() {
        return Math.abs(this.numerator % this.denominator);
    }

    public int getProperWhole() {
        return this.numerator / this.denominator;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.numerator / this.denominator;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.numerator / this.denominator;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.numerator / this.denominator;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.numerator / this.denominator;
    }

    public Fraction reduce() {
        int r0 = this.numerator;
        if (r0 == 0) {
            Fraction fraction = ZERO;
            return equals(fraction) ? this : fraction;
        }
        int greatestCommonDivisor = greatestCommonDivisor(Math.abs(r0), this.denominator);
        return greatestCommonDivisor == 1 ? this : getFraction(this.numerator / greatestCommonDivisor, this.denominator / greatestCommonDivisor);
    }

    public Fraction invert() {
        int r0 = this.numerator;
        if (r0 != 0) {
            if (r0 != Integer.MIN_VALUE) {
                if (r0 < 0) {
                    return new Fraction(-this.denominator, -this.numerator);
                }
                return new Fraction(this.denominator, this.numerator);
            }
            throw new ArithmeticException("overflow: can't negate numerator");
        }
        throw new ArithmeticException("Unable to invert zero.");
    }

    public Fraction negate() {
        if (this.numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: too large to negate");
        }
        return new Fraction(-this.numerator, this.denominator);
    }

    public Fraction abs() {
        return this.numerator >= 0 ? this : negate();
    }

    public Fraction pow(int r4) {
        if (r4 == 1) {
            return this;
        }
        if (r4 == 0) {
            return ONE;
        }
        if (r4 < 0) {
            if (r4 == Integer.MIN_VALUE) {
                return invert().pow(2).pow(-(r4 / 2));
            }
            return invert().pow(-r4);
        }
        Fraction multiplyBy = multiplyBy(this);
        if (r4 % 2 == 0) {
            return multiplyBy.pow(r4 / 2);
        }
        return multiplyBy.pow(r4 / 2).multiplyBy(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0031, code lost:
        if (r3 != 1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0033, code lost:
        r0 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0035, code lost:
        r0 = -(r6 / 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003a, code lost:
        if ((r0 & 1) != 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x003c, code lost:
        r0 = r0 / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x003f, code lost:
        if (r0 <= 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0041, code lost:
        r6 = -r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0043, code lost:
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0044, code lost:
        r0 = (r7 - r6) / 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
        if (r0 != 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004f, code lost:
        return (-r6) * (1 << r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int greatestCommonDivisor(int r6, int r7) {
        /*
            java.lang.String r0 = "overflow: gcd is 2^31"
            if (r6 == 0) goto L57
            if (r7 != 0) goto L7
            goto L57
        L7:
            int r1 = java.lang.Math.abs(r6)
            r2 = 1
            if (r1 == r2) goto L56
            int r1 = java.lang.Math.abs(r7)
            if (r1 != r2) goto L15
            goto L56
        L15:
            if (r6 <= 0) goto L18
            int r6 = -r6
        L18:
            if (r7 <= 0) goto L1b
            int r7 = -r7
        L1b:
            r1 = 0
        L1c:
            r3 = r6 & 1
            r4 = 31
            if (r3 != 0) goto L2f
            r5 = r7 & 1
            if (r5 != 0) goto L2f
            if (r1 >= r4) goto L2f
            int r6 = r6 / 2
            int r7 = r7 / 2
            int r1 = r1 + 1
            goto L1c
        L2f:
            if (r1 == r4) goto L50
            if (r3 != r2) goto L35
            r0 = r7
            goto L38
        L35:
            int r0 = r6 / 2
            int r0 = -r0
        L38:
            r3 = r0 & 1
            if (r3 != 0) goto L3f
            int r0 = r0 / 2
            goto L38
        L3f:
            if (r0 <= 0) goto L43
            int r6 = -r0
            goto L44
        L43:
            r7 = r0
        L44:
            int r0 = r7 - r6
            int r0 = r0 / 2
            if (r0 != 0) goto L38
            int r6 = -r6
            int r7 = r2 << r1
            int r6 = r6 * r7
            return r6
        L50:
            java.lang.ArithmeticException r6 = new java.lang.ArithmeticException
            r6.<init>(r0)
            throw r6
        L56:
            return r2
        L57:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r6 == r1) goto L67
            if (r7 == r1) goto L67
            int r6 = java.lang.Math.abs(r6)
            int r7 = java.lang.Math.abs(r7)
            int r6 = r6 + r7
            return r6
        L67:
            java.lang.ArithmeticException r6 = new java.lang.ArithmeticException
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.math.Fraction.greatestCommonDivisor(int, int):int");
    }

    private static int mulAndCheck(int r3, int r4) {
        long j = r3 * r4;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: mul");
        }
        return (int) j;
    }

    private static int mulPosAndCheck(int r3, int r4) {
        long j = r3 * r4;
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: mulPos");
    }

    private static int addAndCheck(int r3, int r4) {
        long j = r3 + r4;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j;
    }

    private static int subAndCheck(int r3, int r4) {
        long j = r3 - r4;
        if (j < -2147483648L || j > 2147483647L) {
            throw new ArithmeticException("overflow: add");
        }
        return (int) j;
    }

    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    private Fraction addSub(Fraction fraction, boolean z) {
        Validate.isTrue(fraction != null, "The fraction must not be null", new Object[0]);
        if (this.numerator == 0) {
            return z ? fraction : fraction.negate();
        } else if (fraction.numerator == 0) {
            return this;
        } else {
            int greatestCommonDivisor = greatestCommonDivisor(this.denominator, fraction.denominator);
            if (greatestCommonDivisor == 1) {
                int mulAndCheck = mulAndCheck(this.numerator, fraction.denominator);
                int mulAndCheck2 = mulAndCheck(fraction.numerator, this.denominator);
                return new Fraction(z ? addAndCheck(mulAndCheck, mulAndCheck2) : subAndCheck(mulAndCheck, mulAndCheck2), mulPosAndCheck(this.denominator, fraction.denominator));
            }
            BigInteger multiply = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / greatestCommonDivisor));
            BigInteger multiply2 = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / greatestCommonDivisor));
            BigInteger add = z ? multiply.add(multiply2) : multiply.subtract(multiply2);
            int intValue = add.mod(BigInteger.valueOf(greatestCommonDivisor)).intValue();
            int greatestCommonDivisor2 = intValue == 0 ? greatestCommonDivisor : greatestCommonDivisor(intValue, greatestCommonDivisor);
            BigInteger divide = add.divide(BigInteger.valueOf(greatestCommonDivisor2));
            if (divide.bitLength() > 31) {
                throw new ArithmeticException("overflow: numerator too large after multiply");
            }
            return new Fraction(divide.intValue(), mulPosAndCheck(this.denominator / greatestCommonDivisor, fraction.denominator / greatestCommonDivisor2));
        }
    }

    public Fraction multiplyBy(Fraction fraction) {
        Validate.isTrue(fraction != null, "The fraction must not be null", new Object[0]);
        int r0 = this.numerator;
        if (r0 == 0 || fraction.numerator == 0) {
            return ZERO;
        }
        int greatestCommonDivisor = greatestCommonDivisor(r0, fraction.denominator);
        int greatestCommonDivisor2 = greatestCommonDivisor(fraction.numerator, this.denominator);
        return getReducedFraction(mulAndCheck(this.numerator / greatestCommonDivisor, fraction.numerator / greatestCommonDivisor2), mulPosAndCheck(this.denominator / greatestCommonDivisor2, fraction.denominator / greatestCommonDivisor));
    }

    public Fraction divideBy(Fraction fraction) {
        Validate.isTrue(fraction != null, "The fraction must not be null", new Object[0]);
        if (fraction.numerator == 0) {
            throw new ArithmeticException("The fraction to divide by must not be zero");
        }
        return multiplyBy(fraction.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Fraction) {
            Fraction fraction = (Fraction) obj;
            return getNumerator() == fraction.getNumerator() && getDenominator() == fraction.getDenominator();
        }
        return false;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = ((getNumerator() + 629) * 37) + getDenominator();
        }
        return this.hashCode;
    }

    @Override // java.lang.Comparable
    public int compareTo(Fraction fraction) {
        if (this == fraction) {
            return 0;
        }
        int r1 = this.numerator;
        int r2 = fraction.numerator;
        if (r1 == r2 && this.denominator == fraction.denominator) {
            return 0;
        }
        return Long.compare(r1 * fraction.denominator, r2 * this.denominator);
    }

    public String toString() {
        if (this.toString == null) {
            this.toString = getNumerator() + "/" + getDenominator();
        }
        return this.toString;
    }

    public String toProperString() {
        if (this.toProperString == null) {
            int r0 = this.numerator;
            if (r0 == 0) {
                this.toProperString = SessionDescription.SUPPORTED_SDP_VERSION;
            } else {
                int r1 = this.denominator;
                if (r0 == r1) {
                    this.toProperString = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                } else if (r0 == r1 * (-1)) {
                    this.toProperString = "-1";
                } else {
                    if (r0 > 0) {
                        r0 = -r0;
                    }
                    if (r0 < (-r1)) {
                        int properNumerator = getProperNumerator();
                        if (properNumerator == 0) {
                            this.toProperString = Integer.toString(getProperWhole());
                        } else {
                            this.toProperString = getProperWhole() + " " + properNumerator + "/" + getDenominator();
                        }
                    } else {
                        this.toProperString = getNumerator() + "/" + getDenominator();
                    }
                }
            }
        }
        return this.toProperString;
    }
}
