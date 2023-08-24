package org.bouncycastle.math;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public abstract class Primes {
    public static final int SMALL_FACTOR_LIMIT = 211;
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);

    /* loaded from: classes5.dex */
    public static class MROutput {
        private BigInteger factor;
        private boolean provablyComposite;

        private MROutput(boolean z, BigInteger bigInteger) {
            this.provablyComposite = z;
            this.factor = bigInteger;
        }

        static /* synthetic */ MROutput access$000() {
            return probablyPrime();
        }

        static /* synthetic */ MROutput access$200() {
            return provablyCompositeNotPrimePower();
        }

        private static MROutput probablyPrime() {
            return new MROutput(false, null);
        }

        private static MROutput provablyCompositeNotPrimePower() {
            return new MROutput(true, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static MROutput provablyCompositeWithFactor(BigInteger bigInteger) {
            return new MROutput(true, bigInteger);
        }

        public BigInteger getFactor() {
            return this.factor;
        }

        public boolean isNotPrimePower() {
            return this.provablyComposite && this.factor == null;
        }

        public boolean isProvablyComposite() {
            return this.provablyComposite;
        }
    }

    /* loaded from: classes5.dex */
    public static class STOutput {
        private BigInteger prime;
        private int primeGenCounter;
        private byte[] primeSeed;

        private STOutput(BigInteger bigInteger, byte[] bArr, int r3) {
            this.prime = bigInteger;
            this.primeSeed = bArr;
            this.primeGenCounter = r3;
        }

        public BigInteger getPrime() {
            return this.prime;
        }

        public int getPrimeGenCounter() {
            return this.primeGenCounter;
        }

        public byte[] getPrimeSeed() {
            return this.primeSeed;
        }
    }

    private static void checkCandidate(BigInteger bigInteger, String str) {
        if (bigInteger == null || bigInteger.signum() < 1 || bigInteger.bitLength() < 2) {
            throw new IllegalArgumentException("'" + str + "' must be non-null and >= 2");
        }
    }

    public static MROutput enhancedMRProbablePrimeTest(BigInteger bigInteger, SecureRandom secureRandom, int r13) {
        BigInteger bigInteger2;
        boolean z;
        checkCandidate(bigInteger, "candidate");
        if (secureRandom != null) {
            if (r13 >= 1) {
                if (bigInteger.bitLength() == 2) {
                    return MROutput.access$000();
                }
                if (bigInteger.testBit(0)) {
                    BigInteger subtract = bigInteger.subtract(ONE);
                    BigInteger subtract2 = bigInteger.subtract(TWO);
                    int lowestSetBit = subtract.getLowestSetBit();
                    BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
                    for (int r6 = 0; r6 < r13; r6++) {
                        BigInteger createRandomInRange = BigIntegers.createRandomInRange(TWO, subtract2, secureRandom);
                        BigInteger gcd = createRandomInRange.gcd(bigInteger);
                        BigInteger bigInteger3 = ONE;
                        if (gcd.compareTo(bigInteger3) > 0) {
                            return MROutput.provablyCompositeWithFactor(gcd);
                        }
                        BigInteger modPow = createRandomInRange.modPow(shiftRight, bigInteger);
                        if (!modPow.equals(bigInteger3) && !modPow.equals(subtract)) {
                            int r8 = 1;
                            while (true) {
                                if (r8 >= lowestSetBit) {
                                    bigInteger2 = modPow;
                                    break;
                                }
                                bigInteger2 = modPow.modPow(TWO, bigInteger);
                                if (bigInteger2.equals(subtract)) {
                                    z = true;
                                    break;
                                } else if (bigInteger2.equals(ONE)) {
                                    break;
                                } else {
                                    r8++;
                                    modPow = bigInteger2;
                                }
                            }
                            z = false;
                            if (!z) {
                                BigInteger bigInteger4 = ONE;
                                if (!bigInteger2.equals(bigInteger4)) {
                                    modPow = bigInteger2.modPow(TWO, bigInteger);
                                    if (modPow.equals(bigInteger4)) {
                                        modPow = bigInteger2;
                                    }
                                }
                                BigInteger gcd2 = modPow.subtract(bigInteger4).gcd(bigInteger);
                                return gcd2.compareTo(bigInteger4) > 0 ? MROutput.provablyCompositeWithFactor(gcd2) : MROutput.access$200();
                            }
                        }
                    }
                    return MROutput.access$000();
                }
                return MROutput.provablyCompositeWithFactor(TWO);
            }
            throw new IllegalArgumentException("'iterations' must be > 0");
        }
        throw new IllegalArgumentException("'random' cannot be null");
    }

    private static int extract32(byte[] bArr) {
        int min = Math.min(4, bArr.length);
        int r1 = 0;
        int r2 = 0;
        while (r1 < min) {
            int r4 = r1 + 1;
            r2 |= (bArr[bArr.length - r4] & 255) << (r1 * 8);
            r1 = r4;
        }
        return r2;
    }

    public static STOutput generateSTRandomPrime(Digest digest, int r2, byte[] bArr) {
        if (digest != null) {
            if (r2 >= 2) {
                if (bArr == null || bArr.length == 0) {
                    throw new IllegalArgumentException("'inputSeed' cannot be null or empty");
                }
                return implSTRandomPrime(digest, r2, Arrays.clone(bArr));
            }
            throw new IllegalArgumentException("'length' must be >= 2");
        }
        throw new IllegalArgumentException("'hash' cannot be null");
    }

    public static boolean hasAnySmallFactors(BigInteger bigInteger) {
        checkCandidate(bigInteger, "candidate");
        return implHasAnySmallFactors(bigInteger);
    }

    private static void hash(Digest digest, byte[] bArr, byte[] bArr2, int r5) {
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, r5);
    }

    private static BigInteger hashGen(Digest digest, byte[] bArr, int r7) {
        int digestSize = digest.getDigestSize();
        int r1 = r7 * digestSize;
        byte[] bArr2 = new byte[r1];
        for (int r3 = 0; r3 < r7; r3++) {
            r1 -= digestSize;
            hash(digest, bArr, bArr2, r1);
            inc(bArr, 1);
        }
        return new BigInteger(1, bArr2);
    }

    private static boolean implHasAnySmallFactors(BigInteger bigInteger) {
        int intValue = bigInteger.mod(BigInteger.valueOf(223092870)).intValue();
        if (intValue % 2 != 0 && intValue % 3 != 0 && intValue % 5 != 0 && intValue % 7 != 0 && intValue % 11 != 0 && intValue % 13 != 0 && intValue % 17 != 0 && intValue % 19 != 0 && intValue % 23 != 0) {
            int intValue2 = bigInteger.mod(BigInteger.valueOf(58642669)).intValue();
            if (intValue2 % 29 != 0 && intValue2 % 31 != 0 && intValue2 % 37 != 0 && intValue2 % 41 != 0 && intValue2 % 43 != 0) {
                int intValue3 = bigInteger.mod(BigInteger.valueOf(600662303)).intValue();
                if (intValue3 % 47 != 0 && intValue3 % 53 != 0 && intValue3 % 59 != 0 && intValue3 % 61 != 0 && intValue3 % 67 != 0) {
                    int intValue4 = bigInteger.mod(BigInteger.valueOf(33984931)).intValue();
                    if (intValue4 % 71 != 0 && intValue4 % 73 != 0 && intValue4 % 79 != 0 && intValue4 % 83 != 0) {
                        int intValue5 = bigInteger.mod(BigInteger.valueOf(89809099)).intValue();
                        if (intValue5 % 89 != 0 && intValue5 % 97 != 0 && intValue5 % 101 != 0 && intValue5 % 103 != 0) {
                            int intValue6 = bigInteger.mod(BigInteger.valueOf(167375713)).intValue();
                            if (intValue6 % 107 != 0 && intValue6 % 109 != 0 && intValue6 % 113 != 0 && intValue6 % 127 != 0) {
                                int intValue7 = bigInteger.mod(BigInteger.valueOf(371700317)).intValue();
                                if (intValue7 % 131 != 0 && intValue7 % 137 != 0 && intValue7 % 139 != 0 && intValue7 % 149 != 0) {
                                    int intValue8 = bigInteger.mod(BigInteger.valueOf(645328247)).intValue();
                                    if (intValue8 % 151 != 0 && intValue8 % 157 != 0 && intValue8 % 163 != 0 && intValue8 % 167 != 0) {
                                        int intValue9 = bigInteger.mod(BigInteger.valueOf(1070560157)).intValue();
                                        if (intValue9 % 173 != 0 && intValue9 % 179 != 0 && intValue9 % 181 != 0 && intValue9 % 191 != 0) {
                                            int intValue10 = bigInteger.mod(BigInteger.valueOf(1596463769)).intValue();
                                            if (intValue10 % 193 != 0 && intValue10 % 197 != 0 && intValue10 % 199 != 0 && intValue10 % SMALL_FACTOR_LIMIT != 0) {
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean implMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int r6, BigInteger bigInteger4) {
        BigInteger modPow = bigInteger4.modPow(bigInteger3, bigInteger);
        if (modPow.equals(ONE) || modPow.equals(bigInteger2)) {
            return true;
        }
        for (int r7 = 1; r7 < r6; r7++) {
            modPow = modPow.modPow(TWO, bigInteger);
            if (modPow.equals(bigInteger2)) {
                return true;
            }
            if (modPow.equals(ONE)) {
                return false;
            }
        }
        return false;
    }

    private static STOutput implSTRandomPrime(Digest digest, int r17, byte[] bArr) {
        Object obj;
        int digestSize = digest.getDigestSize();
        Object obj2 = null;
        int r7 = 1;
        if (r17 < 33) {
            byte[] bArr2 = new byte[digestSize];
            byte[] bArr3 = new byte[digestSize];
            int r9 = 0;
            do {
                hash(digest, bArr, bArr2, 0);
                inc(bArr, 1);
                hash(digest, bArr, bArr3, 0);
                inc(bArr, 1);
                r9++;
                long extract32 = (((extract32(bArr2) ^ extract32(bArr3)) & ((-1) >>> (32 - r17))) | (1 << (r17 - 1)) | 1) & BodyPartID.bodyIdMax;
                if (isPrime32(extract32)) {
                    return new STOutput(BigInteger.valueOf(extract32), bArr, r9);
                }
            } while (r9 <= r17 * 4);
            throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
        }
        STOutput implSTRandomPrime = implSTRandomPrime(digest, (r17 + 3) / 2, bArr);
        BigInteger prime = implSTRandomPrime.getPrime();
        byte[] primeSeed = implSTRandomPrime.getPrimeSeed();
        int primeGenCounter = implSTRandomPrime.getPrimeGenCounter();
        int r10 = r17 - 1;
        int r3 = (r10 / (digestSize * 8)) + 1;
        BigInteger hashGen = hashGen(digest, primeSeed, r3);
        BigInteger bigInteger = ONE;
        BigInteger bit = hashGen.mod(bigInteger.shiftLeft(r10)).setBit(r10);
        BigInteger shiftLeft = prime.shiftLeft(1);
        BigInteger shiftLeft2 = bit.subtract(bigInteger).divide(shiftLeft).add(bigInteger).shiftLeft(1);
        BigInteger add = shiftLeft2.multiply(prime).add(bigInteger);
        int r14 = primeGenCounter;
        int r15 = 0;
        while (true) {
            if (add.bitLength() > r17) {
                BigInteger bigInteger2 = ONE;
                shiftLeft2 = bigInteger2.shiftLeft(r10).subtract(bigInteger2).divide(shiftLeft).add(bigInteger2).shiftLeft(r7);
                add = shiftLeft2.multiply(prime).add(bigInteger2);
            }
            r14 += r7;
            if (implHasAnySmallFactors(add)) {
                obj = obj2;
                inc(primeSeed, r3);
            } else {
                BigInteger add2 = hashGen(digest, primeSeed, r3).mod(add.subtract(THREE)).add(TWO);
                BigInteger add3 = shiftLeft2.add(BigInteger.valueOf(r15));
                BigInteger modPow = add2.modPow(add3, add);
                BigInteger bigInteger3 = ONE;
                if (add.gcd(modPow.subtract(bigInteger3)).equals(bigInteger3) && modPow.modPow(prime, add).equals(bigInteger3)) {
                    return new STOutput(add, primeSeed, r14);
                }
                obj = null;
                shiftLeft2 = add3;
                r15 = 0;
            }
            if (r14 >= (r17 * 4) + primeGenCounter) {
                throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
            }
            r15 += 2;
            add = add.add(shiftLeft);
            obj2 = obj;
            r7 = 1;
        }
    }

    private static void inc(byte[] bArr, int r3) {
        int length = bArr.length;
        while (r3 > 0) {
            length--;
            if (length < 0) {
                return;
            }
            int r32 = r3 + (bArr[length] & 255);
            bArr[length] = (byte) r32;
            r3 = r32 >>> 8;
        }
    }

    public static boolean isMRProbablePrime(BigInteger bigInteger, SecureRandom secureRandom, int r10) {
        checkCandidate(bigInteger, "candidate");
        if (secureRandom != null) {
            if (r10 >= 1) {
                if (bigInteger.bitLength() == 2) {
                    return true;
                }
                if (bigInteger.testBit(0)) {
                    BigInteger subtract = bigInteger.subtract(ONE);
                    BigInteger subtract2 = bigInteger.subtract(TWO);
                    int lowestSetBit = subtract.getLowestSetBit();
                    BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
                    for (int r6 = 0; r6 < r10; r6++) {
                        if (!implMRProbablePrimeToBase(bigInteger, subtract, shiftRight, lowestSetBit, BigIntegers.createRandomInRange(TWO, subtract2, secureRandom))) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            throw new IllegalArgumentException("'iterations' must be > 0");
        }
        throw new IllegalArgumentException("'random' cannot be null");
    }

    public static boolean isMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2) {
        checkCandidate(bigInteger, "candidate");
        checkCandidate(bigInteger2, "base");
        BigInteger bigInteger3 = ONE;
        if (bigInteger2.compareTo(bigInteger.subtract(bigInteger3)) < 0) {
            if (bigInteger.bitLength() == 2) {
                return true;
            }
            BigInteger subtract = bigInteger.subtract(bigInteger3);
            int lowestSetBit = subtract.getLowestSetBit();
            return implMRProbablePrimeToBase(bigInteger, subtract, subtract.shiftRight(lowestSetBit), lowestSetBit, bigInteger2);
        }
        throw new IllegalArgumentException("'base' must be < ('candidate' - 1)");
    }

    private static boolean isPrime32(long j) {
        if ((j >>> 32) != 0) {
            throw new IllegalArgumentException("Size limit exceeded");
        }
        int r8 = (j > 5L ? 1 : (j == 5L ? 0 : -1));
        if (r8 <= 0) {
            return j == 2 || j == 3 || r8 == 0;
        } else if ((1 & j) == 0 || j % 3 == 0 || j % 5 == 0) {
            return false;
        } else {
            long[] jArr = {1, 7, 11, 13, 17, 19, 23, 29};
            long j2 = 0;
            int r4 = 1;
            while (true) {
                if (r4 >= 8) {
                    j2 += 30;
                    if (j2 * j2 >= j) {
                        return true;
                    }
                    r4 = 0;
                } else if (j % (jArr[r4] + j2) == 0) {
                    return j < 30;
                } else {
                    r4++;
                }
            }
        }
    }
}
