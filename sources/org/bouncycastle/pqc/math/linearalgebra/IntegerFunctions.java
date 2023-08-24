package org.bouncycastle.pqc.math.linearalgebra;

import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.zxing.pdf417.PDF417Common;
import java.math.BigInteger;
import java.security.SecureRandom;
import okhttp3.internal.http.StatusLine;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public final class IntegerFunctions {
    private static final long SMALL_PRIME_PRODUCT = 152125131763605L;
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final int[] SMALL_PRIMES = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};

    /* renamed from: sr */
    private static SecureRandom f2535sr = null;
    private static final int[] jacobiTable = {0, 1, 0, -1, 0, -1, 0, 1};

    private IntegerFunctions() {
    }

    public static BigInteger binomial(int r4, int r5) {
        BigInteger bigInteger = ONE;
        if (r4 == 0) {
            return r5 == 0 ? bigInteger : ZERO;
        }
        if (r5 > (r4 >>> 1)) {
            r5 = r4 - r5;
        }
        for (int r1 = 1; r1 <= r5; r1++) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(r4 - (r1 - 1))).divide(BigInteger.valueOf(r1));
        }
        return bigInteger;
    }

    public static int bitCount(int r2) {
        int r0 = 0;
        while (r2 != 0) {
            r0 += r2 & 1;
            r2 >>>= 1;
        }
        return r0;
    }

    public static int ceilLog(int r2) {
        int r0 = 1;
        int r1 = 0;
        while (r0 < r2) {
            r0 <<= 1;
            r1++;
        }
        return r1;
    }

    public static int ceilLog(BigInteger bigInteger) {
        int r1 = 0;
        for (BigInteger bigInteger2 = ONE; bigInteger2.compareTo(bigInteger) < 0; bigInteger2 = bigInteger2.shiftLeft(1)) {
            r1++;
        }
        return r1;
    }

    public static int ceilLog256(int r1) {
        if (r1 == 0) {
            return 1;
        }
        if (r1 < 0) {
            r1 = -r1;
        }
        int r0 = 0;
        while (r1 > 0) {
            r0++;
            r1 >>>= 8;
        }
        return r0;
    }

    public static int ceilLog256(long j) {
        int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (r2 == 0) {
            return 1;
        }
        if (r2 < 0) {
            j = -j;
        }
        int r22 = 0;
        while (j > 0) {
            r22++;
            j >>>= 8;
        }
        return r22;
    }

    public static BigInteger divideAndRound(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.signum() < 0 ? divideAndRound(bigInteger.negate(), bigInteger2).negate() : bigInteger2.signum() < 0 ? divideAndRound(bigInteger, bigInteger2.negate()).negate() : bigInteger.shiftLeft(1).add(bigInteger2).divide(bigInteger2.shiftLeft(1));
    }

    public static BigInteger[] divideAndRound(BigInteger[] bigIntegerArr, BigInteger bigInteger) {
        BigInteger[] bigIntegerArr2 = new BigInteger[bigIntegerArr.length];
        for (int r1 = 0; r1 < bigIntegerArr.length; r1++) {
            bigIntegerArr2[r1] = divideAndRound(bigIntegerArr[r1], bigInteger);
        }
        return bigIntegerArr2;
    }

    public static int[] extGCD(int r2, int r3) {
        BigInteger[] extgcd = extgcd(BigInteger.valueOf(r2), BigInteger.valueOf(r3));
        return new int[]{extgcd[0].intValue(), extgcd[1].intValue(), extgcd[2].intValue()};
    }

    public static BigInteger[] extgcd(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = ONE;
        BigInteger bigInteger4 = ZERO;
        if (bigInteger2.signum() != 0) {
            BigInteger bigInteger5 = bigInteger;
            BigInteger bigInteger6 = bigInteger2;
            while (bigInteger6.signum() != 0) {
                BigInteger[] divideAndRemainder = bigInteger5.divideAndRemainder(bigInteger6);
                BigInteger bigInteger7 = divideAndRemainder[0];
                BigInteger bigInteger8 = divideAndRemainder[1];
                BigInteger bigInteger9 = bigInteger4;
                bigInteger4 = bigInteger3.subtract(bigInteger7.multiply(bigInteger4));
                bigInteger3 = bigInteger9;
                bigInteger5 = bigInteger6;
                bigInteger6 = bigInteger8;
            }
            bigInteger4 = bigInteger5.subtract(bigInteger.multiply(bigInteger3)).divide(bigInteger2);
            bigInteger = bigInteger5;
        }
        return new BigInteger[]{bigInteger, bigInteger3, bigInteger4};
    }

    public static float floatPow(float f, int r2) {
        float f2 = 1.0f;
        while (r2 > 0) {
            f2 *= f;
            r2--;
        }
        return f2;
    }

    public static int floorLog(int r1) {
        if (r1 <= 0) {
            return -1;
        }
        int r0 = 0;
        for (int r12 = r1 >>> 1; r12 > 0; r12 >>>= 1) {
            r0++;
        }
        return r0;
    }

    public static int floorLog(BigInteger bigInteger) {
        int r1 = -1;
        for (BigInteger bigInteger2 = ONE; bigInteger2.compareTo(bigInteger) <= 0; bigInteger2 = bigInteger2.shiftLeft(1)) {
            r1++;
        }
        return r1;
    }

    public static int gcd(int r2, int r3) {
        return BigInteger.valueOf(r2).gcd(BigInteger.valueOf(r3)).intValue();
    }

    public static float intRoot(int r8, int r9) {
        float floatPow;
        float f = r8 / r9;
        float f2 = 0.0f;
        while (Math.abs(f2 - f) > 1.0E-4d) {
            while (true) {
                floatPow = floatPow(f, r9);
                if (Float.isInfinite(floatPow)) {
                    f = (f + f2) / 2.0f;
                }
            }
            f2 = f;
            f -= (floatPow - r8) / (r9 * floatPow(f, r9 - 1));
        }
        return f;
    }

    public static byte[] integerToOctets(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.abs().toByteArray();
        if ((bigInteger.bitLength() & 7) != 0) {
            return byteArray;
        }
        int bitLength = bigInteger.bitLength() >> 3;
        byte[] bArr = new byte[bitLength];
        System.arraycopy(byteArray, 1, bArr, 0, bitLength);
        return bArr;
    }

    public static boolean isIncreasing(int[] r4) {
        for (int r1 = 1; r1 < r4.length; r1++) {
            if (r4[r1 - 1] >= r4[r1]) {
                return false;
            }
        }
        return true;
    }

    public static int isPower(int r3, int r4) {
        if (r3 <= 0) {
            return -1;
        }
        int r1 = 0;
        while (r3 > 1) {
            if (r3 % r4 != 0) {
                return -1;
            }
            r3 /= r4;
            r1++;
        }
        return r1;
    }

    public static boolean isPrime(int r5) {
        if (r5 < 2) {
            return false;
        }
        if (r5 == 2) {
            return true;
        }
        if ((r5 & 1) == 0) {
            return false;
        }
        if (r5 < 42) {
            int r0 = 0;
            while (true) {
                int[] r3 = SMALL_PRIMES;
                if (r0 >= r3.length) {
                    break;
                } else if (r5 == r3[r0]) {
                    return true;
                } else {
                    r0++;
                }
            }
        }
        if (r5 % 3 == 0 || r5 % 5 == 0 || r5 % 7 == 0 || r5 % 11 == 0 || r5 % 13 == 0 || r5 % 17 == 0 || r5 % 19 == 0 || r5 % 23 == 0 || r5 % 29 == 0 || r5 % 31 == 0 || r5 % 37 == 0 || r5 % 41 == 0) {
            return false;
        }
        return BigInteger.valueOf(r5).isProbablePrime(20);
    }

    public static int jacobi(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = ZERO;
        if (bigInteger2.equals(bigInteger3)) {
            return bigInteger.abs().equals(ONE) ? 1 : 0;
        }
        if (bigInteger.testBit(0) || bigInteger2.testBit(0)) {
            long j = 1;
            if (bigInteger2.signum() == -1) {
                bigInteger2 = bigInteger2.negate();
                if (bigInteger.signum() == -1) {
                    j = -1;
                }
            }
            while (!bigInteger2.testBit(0)) {
                bigInteger3 = bigInteger3.add(ONE);
                bigInteger2 = bigInteger2.divide(TWO);
            }
            if (bigInteger3.testBit(0)) {
                j *= jacobiTable[bigInteger.intValue() & 7];
            }
            if (bigInteger.signum() < 0) {
                if (bigInteger2.testBit(1)) {
                    j = -j;
                }
                bigInteger = bigInteger.negate();
            }
            while (bigInteger.signum() != 0) {
                BigInteger bigInteger4 = ZERO;
                while (!bigInteger.testBit(0)) {
                    bigInteger4 = bigInteger4.add(ONE);
                    bigInteger = bigInteger.divide(TWO);
                }
                if (bigInteger4.testBit(0)) {
                    j *= jacobiTable[bigInteger2.intValue() & 7];
                }
                if (bigInteger.compareTo(bigInteger2) >= 0) {
                    BigInteger bigInteger5 = bigInteger2;
                    bigInteger2 = bigInteger;
                    bigInteger = bigInteger5;
                } else if (bigInteger2.testBit(1) && bigInteger.testBit(1)) {
                    j = -j;
                }
                BigInteger subtract = bigInteger2.subtract(bigInteger);
                bigInteger2 = bigInteger;
                bigInteger = subtract;
            }
            if (bigInteger2.equals(ONE)) {
                return (int) j;
            }
            return 0;
        }
        return 0;
    }

    public static BigInteger leastCommonMultiple(BigInteger[] bigIntegerArr) {
        int length = bigIntegerArr.length;
        BigInteger bigInteger = bigIntegerArr[0];
        for (int r2 = 1; r2 < length; r2++) {
            bigInteger = bigInteger.multiply(bigIntegerArr[r2]).divide(bigInteger.gcd(bigIntegerArr[r2]));
        }
        return bigInteger;
    }

    public static int leastDiv(int r2) {
        if (r2 < 0) {
            r2 = -r2;
        }
        if (r2 == 0) {
            return 1;
        }
        if ((r2 & 1) == 0) {
            return 2;
        }
        for (int r0 = 3; r0 <= r2 / r0; r0 += 2) {
            if (r2 % r0 == 0) {
                return r0;
            }
        }
        return r2;
    }

    public static double log(double d) {
        double d2 = 1.0d;
        if (d <= 0.0d || d >= 1.0d) {
            int r2 = 0;
            double d3 = d;
            while (d3 > 2.0d) {
                d3 /= 2.0d;
                r2++;
                d2 *= 2.0d;
            }
            return r2 + logBKM(d / d2);
        }
        return -log(1.0d / d);
    }

    public static double log(long j) {
        int floorLog = floorLog(BigInteger.valueOf(j));
        return floorLog + logBKM(j / (1 << floorLog));
    }

    private static double logBKM(double d) {
        double[] dArr = {1.0d, 0.5849625007211562d, 0.32192809488736235d, 0.16992500144231237d, 0.0874628412503394d, 0.044394119358453436d, 0.02236781302845451d, 0.01122725542325412d, 0.005624549193878107d, 0.0028150156070540383d, 0.0014081943928083889d, 7.042690112466433E-4d, 3.5217748030102726E-4d, 1.7609948644250602E-4d, 8.80524301221769E-5d, 4.4026886827316716E-5d, 2.2013611360340496E-5d, 1.1006847667481442E-5d, 5.503434330648604E-6d, 2.751719789561283E-6d, 1.375860550841138E-6d, 6.879304394358497E-7d, 3.4396526072176454E-7d, 1.7198264061184464E-7d, 8.599132286866321E-8d, 4.299566207501687E-8d, 2.1497831197679756E-8d, 1.0748915638882709E-8d, 5.374457829452062E-9d, 2.687228917228708E-9d, 1.3436144592400231E-9d, 6.718072297764289E-10d, 3.3590361492731876E-10d, 1.6795180747343547E-10d, 8.397590373916176E-11d, 4.1987951870191886E-11d, 2.0993975935248694E-11d, 1.0496987967662534E-11d, 5.2484939838408146E-12d, 2.624246991922794E-12d, 1.3121234959619935E-12d, 6.56061747981146E-13d, 3.2803087399061026E-13d, 1.6401543699531447E-13d, 8.200771849765956E-14d, 4.1003859248830365E-14d, 2.0501929624415328E-14d, 1.02509648122077E-14d, 5.1254824061038595E-15d, 2.5627412030519317E-15d, 1.2813706015259665E-15d, 6.406853007629834E-16d, 3.203426503814917E-16d, 1.6017132519074588E-16d, 8.008566259537294E-17d, 4.004283129768647E-17d, 2.0021415648843235E-17d, 1.0010707824421618E-17d, 5.005353912210809E-18d, 2.5026769561054044E-18d, 1.2513384780527022E-18d, 6.256692390263511E-19d, 3.1283461951317555E-19d, 1.5641730975658778E-19d, 7.820865487829389E-20d, 3.9104327439146944E-20d, 1.9552163719573472E-20d, 9.776081859786736E-21d, 4.888040929893368E-21d, 2.444020464946684E-21d, 1.222010232473342E-21d, 6.11005116236671E-22d, 3.055025581183355E-22d, 1.5275127905916775E-22d, 7.637563952958387E-23d, 3.818781976479194E-23d, 1.909390988239597E-23d, 9.546954941197984E-24d, 4.773477470598992E-24d, 2.386738735299496E-24d, 1.193369367649748E-24d, 5.96684683824874E-25d, 2.98342341912437E-25d, 1.491711709562185E-25d, 7.458558547810925E-26d, 3.7292792739054626E-26d, 1.8646396369527313E-26d, 9.323198184763657E-27d, 4.661599092381828E-27d, 2.330799546190914E-27d, 1.165399773095457E-27d, 5.826998865477285E-28d, 2.9134994327386427E-28d, 1.4567497163693213E-28d, 7.283748581846607E-29d, 3.6418742909233034E-29d, 1.8209371454616517E-29d, 9.104685727308258E-30d, 4.552342863654129E-30d, 2.2761714318270646E-30d};
        double d2 = 1.0d;
        double d3 = 0.0d;
        double d4 = 1.0d;
        for (int r7 = 0; r7 < 53; r7++) {
            double d5 = (d2 * d4) + d2;
            if (d5 <= d) {
                d3 += dArr[r7];
                d2 = d5;
            }
            d4 *= 0.5d;
        }
        return d3;
    }

    public static int maxPower(int r3) {
        int r0 = 0;
        if (r3 != 0) {
            for (int r1 = 1; (r3 & r1) == 0; r1 <<= 1) {
                r0++;
            }
        }
        return r0;
    }

    public static long mod(long j, long j2) {
        long j3 = j % j2;
        return j3 < 0 ? j3 + j2 : j3;
    }

    public static int modInverse(int r2, int r3) {
        return BigInteger.valueOf(r2).modInverse(BigInteger.valueOf(r3)).intValue();
    }

    public static long modInverse(long j, long j2) {
        return BigInteger.valueOf(j).modInverse(BigInteger.valueOf(j2)).longValue();
    }

    public static int modPow(int r3, int r4, int r5) {
        if (r5 <= 0 || r5 * r5 > Integer.MAX_VALUE || r4 < 0) {
            return 0;
        }
        int r32 = ((r3 % r5) + r5) % r5;
        int r1 = 1;
        while (r4 > 0) {
            if ((r4 & 1) == 1) {
                r1 = (r1 * r32) % r5;
            }
            r32 = (r32 * r32) % r5;
            r4 >>>= 1;
        }
        return r1;
    }

    public static BigInteger nextPrime(long j) {
        if (j <= 1) {
            return BigInteger.valueOf(2L);
        }
        if (j == 2) {
            return BigInteger.valueOf(3L);
        }
        boolean z = false;
        long j2 = 0;
        for (long j3 = j + 1 + (j & 1); j3 <= (j << 1) && !z; j3 += 2) {
            for (long j4 = 3; j4 <= (j3 >> 1) && !z; j4 += 2) {
                if (j3 % j4 == 0) {
                    z = true;
                }
            }
            if (!z) {
                j2 = j3;
            }
            z = !z;
        }
        return BigInteger.valueOf(j2);
    }

    public static BigInteger nextProbablePrime(BigInteger bigInteger) {
        return nextProbablePrime(bigInteger, 20);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x009c, code lost:
        if ((r0 % 41) != 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.math.BigInteger nextProbablePrime(java.math.BigInteger r7, int r8) {
        /*
            int r0 = r7.signum()
            if (r0 < 0) goto Lb0
            int r0 = r7.signum()
            if (r0 == 0) goto Lb0
            java.math.BigInteger r0 = org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.ONE
            boolean r1 = r7.equals(r0)
            if (r1 == 0) goto L16
            goto Lb0
        L16:
            java.math.BigInteger r7 = r7.add(r0)
            r1 = 0
            boolean r1 = r7.testBit(r1)
            if (r1 != 0) goto L25
        L21:
            java.math.BigInteger r7 = r7.add(r0)
        L25:
            int r0 = r7.bitLength()
            r1 = 6
            if (r0 <= r1) goto La1
            r0 = 152125131763605(0x8a5b6470af95, double:7.515980147347E-310)
            java.math.BigInteger r0 = java.math.BigInteger.valueOf(r0)
            java.math.BigInteger r0 = r7.remainder(r0)
            long r0 = r0.longValue()
            r2 = 3
            long r2 = r0 % r2
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 5
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 7
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 11
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 13
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 17
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 19
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 23
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 29
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 31
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 37
            long r2 = r0 % r2
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L9e
            r2 = 41
            long r0 = r0 % r2
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto La1
        L9e:
            java.math.BigInteger r0 = org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.TWO
            goto L21
        La1:
            int r0 = r7.bitLength()
            r1 = 4
            if (r0 >= r1) goto La9
            return r7
        La9:
            boolean r0 = r7.isProbablePrime(r8)
            if (r0 == 0) goto L9e
            return r7
        Lb0:
            java.math.BigInteger r7 = org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.TWO
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions.nextProbablePrime(java.math.BigInteger, int):java.math.BigInteger");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0010 -> B:13:0x0012). Please submit an issue!!! */
    public static int nextSmallerPrime(int r2) {
        if (r2 <= 2) {
            return 1;
        }
        if (r2 == 3) {
            return 2;
        }
        if ((r2 & 1) == 0) {
            r2--;
            if (r2 > 3 || isPrime(r2)) {
                return r2;
            }
        }
        r2 -= 2;
        if (r2 > 3) {
        }
        return r2;
    }

    public static BigInteger octetsToInteger(byte[] bArr) {
        return octetsToInteger(bArr, 0, bArr.length);
    }

    public static BigInteger octetsToInteger(byte[] bArr, int r3, int r4) {
        byte[] bArr2 = new byte[r4 + 1];
        bArr2[0] = 0;
        System.arraycopy(bArr, r3, bArr2, 1, r4);
        return new BigInteger(bArr2);
    }

    public static int order(int r3, int r4) {
        int r0 = r3 % r4;
        if (r0 == 0) {
            throw new IllegalArgumentException(r3 + " is not an element of Z/(" + r4 + "Z)^*; it is not meaningful to compute its order.");
        }
        int r2 = 1;
        while (r0 != 1) {
            r0 = (r0 * r3) % r4;
            if (r0 < 0) {
                r0 += r4;
            }
            r2++;
        }
        return r2;
    }

    public static boolean passesSmallPrimeTest(BigInteger bigInteger) {
        int[] r1 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, StatusLine.HTTP_MISDIRECTED_REQUEST, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, PDF417Common.NUMBER_OF_CODEWORDS, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499};
        for (int r3 = 0; r3 < 239; r3++) {
            if (bigInteger.mod(BigInteger.valueOf(r1[r3])).equals(ZERO)) {
                return false;
            }
        }
        return true;
    }

    public static int pow(int r3, int r4) {
        int r1 = 1;
        while (r4 > 0) {
            if ((r4 & 1) == 1) {
                r1 *= r3;
            }
            r3 *= r3;
            r4 >>>= 1;
        }
        return r1;
    }

    public static long pow(long j, int r6) {
        long j2 = 1;
        while (r6 > 0) {
            if ((r6 & 1) == 1) {
                j2 *= j;
            }
            j *= j;
            r6 >>>= 1;
        }
        return j2;
    }

    public static BigInteger randomize(BigInteger bigInteger) {
        if (f2535sr == null) {
            f2535sr = CryptoServicesRegistrar.getSecureRandom();
        }
        return randomize(bigInteger, f2535sr);
    }

    public static BigInteger randomize(BigInteger bigInteger, SecureRandom secureRandom) {
        int bitLength = bigInteger.bitLength();
        BigInteger valueOf = BigInteger.valueOf(0L);
        if (secureRandom == null && (secureRandom = f2535sr) == null) {
            secureRandom = CryptoServicesRegistrar.getSecureRandom();
        }
        for (int r2 = 0; r2 < 20; r2++) {
            valueOf = BigIntegers.createRandomBigInteger(bitLength, secureRandom);
            if (valueOf.compareTo(bigInteger) < 0) {
                return valueOf;
            }
        }
        return valueOf.mod(bigInteger);
    }

    public static BigInteger reduceInto(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger.subtract(bigInteger2).mod(bigInteger3.subtract(bigInteger2)).add(bigInteger2);
    }

    public static BigInteger ressol(BigInteger bigInteger, BigInteger bigInteger2) throws IllegalArgumentException {
        BigInteger bigInteger3;
        BigInteger bigInteger4 = ZERO;
        BigInteger bigInteger5 = bigInteger;
        if (bigInteger5.compareTo(bigInteger4) < 0) {
            bigInteger5 = bigInteger.add(bigInteger2);
        }
        if (bigInteger5.equals(bigInteger4)) {
            return bigInteger4;
        }
        if (bigInteger2.equals(TWO)) {
            return bigInteger5;
        }
        if (bigInteger2.testBit(0) && bigInteger2.testBit(1)) {
            if (jacobi(bigInteger5, bigInteger2) == 1) {
                return bigInteger5.modPow(bigInteger2.add(ONE).shiftRight(2), bigInteger2);
            }
            throw new IllegalArgumentException("No quadratic residue: " + bigInteger5 + ", " + bigInteger2);
        }
        BigInteger subtract = bigInteger2.subtract(ONE);
        long j = 0;
        while (!subtract.testBit(0)) {
            j++;
            subtract = subtract.shiftRight(1);
        }
        BigInteger bigInteger6 = ONE;
        BigInteger shiftRight = subtract.subtract(bigInteger6).shiftRight(1);
        BigInteger modPow = bigInteger5.modPow(shiftRight, bigInteger2);
        BigInteger remainder = modPow.multiply(modPow).remainder(bigInteger2).multiply(bigInteger5).remainder(bigInteger2);
        BigInteger remainder2 = modPow.multiply(bigInteger5).remainder(bigInteger2);
        if (remainder.equals(bigInteger6)) {
            return remainder2;
        }
        BigInteger bigInteger7 = TWO;
        while (jacobi(bigInteger7, bigInteger2) == 1) {
            bigInteger7 = bigInteger7.add(ONE);
        }
        BigInteger modPow2 = bigInteger7.modPow(shiftRight.multiply(TWO).add(ONE), bigInteger2);
        for (int r6 = 1; remainder.compareTo(ONE) == r6; r6 = 1) {
            long j2 = 0;
            BigInteger bigInteger8 = remainder;
            while (true) {
                bigInteger3 = ONE;
                if (bigInteger8.equals(bigInteger3)) {
                    break;
                }
                bigInteger8 = bigInteger8.multiply(bigInteger8).mod(bigInteger2);
                j2++;
            }
            long j3 = j - j2;
            if (j3 == 0) {
                throw new IllegalArgumentException("No quadratic residue: " + bigInteger5 + ", " + bigInteger2);
            }
            for (long j4 = 0; j4 < j3 - 1; j4++) {
                bigInteger3 = bigInteger3.shiftLeft(1);
            }
            BigInteger modPow3 = modPow2.modPow(bigInteger3, bigInteger2);
            remainder2 = remainder2.multiply(modPow3).remainder(bigInteger2);
            modPow2 = modPow3.multiply(modPow3).remainder(bigInteger2);
            remainder = remainder.multiply(modPow2).mod(bigInteger2);
            j = j2;
        }
        return remainder2;
    }

    public static BigInteger squareRoot(BigInteger bigInteger) {
        int r2;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = ZERO;
        if (bigInteger.compareTo(bigInteger3) < 0) {
            throw new ArithmeticException("cannot extract root of negative number" + bigInteger + ".");
        }
        int bitLength = bigInteger.bitLength();
        if ((bitLength & 1) != 0) {
            int r1 = bitLength - 1;
            bigInteger2 = bigInteger3;
            bigInteger3 = bigInteger3.add(ONE);
            r2 = r1;
        } else {
            r2 = bitLength;
            bigInteger2 = bigInteger3;
        }
        while (r2 > 0) {
            BigInteger bigInteger4 = FOUR;
            BigInteger multiply = bigInteger2.multiply(bigInteger4);
            int r22 = r2 - 1;
            int r4 = bigInteger.testBit(r22) ? 2 : 0;
            r2 = r22 - 1;
            bigInteger2 = multiply.add(BigInteger.valueOf(r4 + (bigInteger.testBit(r2) ? 1 : 0)));
            BigInteger multiply2 = bigInteger3.multiply(bigInteger4);
            BigInteger bigInteger5 = ONE;
            BigInteger add = multiply2.add(bigInteger5);
            bigInteger3 = bigInteger3.multiply(TWO);
            if (bigInteger2.compareTo(add) != -1) {
                bigInteger3 = bigInteger3.add(bigInteger5);
                bigInteger2 = bigInteger2.subtract(add);
            }
        }
        return bigInteger3;
    }
}
