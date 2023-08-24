package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p039ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
class DHParametersHelper {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    DHParametersHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger[] generateSafePrimes(int r6, int r7, SecureRandom secureRandom) {
        int r0 = r6 - 1;
        int r62 = r6 >>> 2;
        while (true) {
            BigInteger createRandomPrime = BigIntegers.createRandomPrime(r0, 2, secureRandom);
            BigInteger add = createRandomPrime.shiftLeft(1).add(ONE);
            if (add.isProbablePrime(r7) && (r7 <= 2 || createRandomPrime.isProbablePrime(r7 - 2))) {
                if (WNafUtil.getNafWeight(add) >= r62) {
                    return new BigInteger[]{add, createRandomPrime};
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger selectGenerator(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger modPow;
        BigInteger subtract = bigInteger.subtract(TWO);
        do {
            BigInteger bigInteger3 = TWO;
            modPow = BigIntegers.createRandomInRange(bigInteger3, subtract, secureRandom).modPow(bigInteger3, bigInteger);
        } while (modPow.equals(ONE));
        return modPow;
    }
}
