package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.CramerShoupParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public class CramerShoupParametersGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private int certainty;
    private SecureRandom random;
    private int size;

    /* loaded from: classes5.dex */
    private static class ParametersHelper {
        private static final BigInteger TWO = BigInteger.valueOf(2);

        private ParametersHelper() {
        }

        static BigInteger[] generateSafePrimes(int r5, int r6, SecureRandom secureRandom) {
            BigInteger createRandomPrime;
            BigInteger add;
            int r52 = r5 - 1;
            while (true) {
                createRandomPrime = BigIntegers.createRandomPrime(r52, 2, secureRandom);
                add = createRandomPrime.shiftLeft(1).add(CramerShoupParametersGenerator.ONE);
                if (!add.isProbablePrime(r6) || (r6 > 2 && !createRandomPrime.isProbablePrime(r6))) {
                }
            }
            return new BigInteger[]{add, createRandomPrime};
        }

        static BigInteger selectGenerator(BigInteger bigInteger, SecureRandom secureRandom) {
            BigInteger modPow;
            BigInteger subtract = bigInteger.subtract(TWO);
            do {
                BigInteger bigInteger2 = TWO;
                modPow = BigIntegers.createRandomInRange(bigInteger2, subtract, secureRandom).modPow(bigInteger2, bigInteger);
            } while (modPow.equals(CramerShoupParametersGenerator.ONE));
            return modPow;
        }
    }

    public CramerShoupParameters generateParameters() {
        BigInteger selectGenerator;
        BigInteger bigInteger = ParametersHelper.generateSafePrimes(this.size, this.certainty, this.random)[1];
        BigInteger selectGenerator2 = ParametersHelper.selectGenerator(bigInteger, this.random);
        do {
            selectGenerator = ParametersHelper.selectGenerator(bigInteger, this.random);
        } while (selectGenerator2.equals(selectGenerator));
        return new CramerShoupParameters(bigInteger, selectGenerator2, selectGenerator, new SHA256Digest());
    }

    public CramerShoupParameters generateParameters(DHParameters dHParameters) {
        BigInteger selectGenerator;
        BigInteger p = dHParameters.getP();
        BigInteger g = dHParameters.getG();
        do {
            selectGenerator = ParametersHelper.selectGenerator(p, this.random);
        } while (g.equals(selectGenerator));
        return new CramerShoupParameters(p, g, selectGenerator, new SHA256Digest());
    }

    public void init(int r1, int r2, SecureRandom secureRandom) {
        this.size = r1;
        this.certainty = r2;
        this.random = secureRandom;
    }
}