package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.math.Primes;
import org.bouncycastle.math.p039ec.WNafUtil;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public class RSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private RSAKeyGenerationParameters param;

    private static int getNumberOfIterations(int r4, int r5) {
        if (r4 >= 1536) {
            if (r5 <= 100) {
                return 3;
            }
            if (r5 <= 128) {
                return 4;
            }
            return 4 + (((r5 - 128) + 1) / 2);
        } else if (r4 >= 1024) {
            if (r5 <= 100) {
                return 4;
            }
            if (r5 <= 112) {
                return 5;
            }
            return (((r5 - 112) + 1) / 2) + 5;
        } else if (r4 < 512) {
            if (r5 <= 80) {
                return 40;
            }
            return 40 + (((r5 - 80) + 1) / 2);
        } else if (r5 <= 80) {
            return 5;
        } else {
            if (r5 <= 100) {
                return 7;
            }
            return (((r5 - 100) + 1) / 2) + 7;
        }
    }

    protected BigInteger chooseRandomPrime(int r5, BigInteger bigInteger, BigInteger bigInteger2) {
        for (int r0 = 0; r0 != r5 * 5; r0++) {
            BigInteger createRandomPrime = BigIntegers.createRandomPrime(r5, 1, this.param.getRandom());
            BigInteger mod = createRandomPrime.mod(bigInteger);
            BigInteger bigInteger3 = ONE;
            if (!mod.equals(bigInteger3) && createRandomPrime.multiply(createRandomPrime).compareTo(bigInteger2) >= 0 && isProbablePrime(createRandomPrime) && bigInteger.gcd(createRandomPrime.subtract(bigInteger3)).equals(bigInteger3)) {
                return createRandomPrime;
            }
        }
        throw new IllegalStateException("unable to generate prime number for RSA key");
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger chooseRandomPrime;
        BigInteger chooseRandomPrime2;
        BigInteger multiply;
        BigInteger bigInteger;
        RSAKeyPairGenerator rSAKeyPairGenerator = this;
        int strength = rSAKeyPairGenerator.param.getStrength();
        int r2 = (strength + 1) / 2;
        int r3 = strength - r2;
        int r4 = strength / 2;
        int r5 = r4 - 100;
        int r6 = strength / 3;
        if (r5 < r6) {
            r5 = r6;
        }
        int r62 = strength >> 2;
        BigInteger pow = BigInteger.valueOf(2L).pow(r4);
        BigInteger bigInteger2 = ONE;
        BigInteger shiftLeft = bigInteger2.shiftLeft(strength - 1);
        BigInteger shiftLeft2 = bigInteger2.shiftLeft(r5);
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = null;
        boolean z = false;
        while (!z) {
            BigInteger publicExponent = rSAKeyPairGenerator.param.getPublicExponent();
            do {
                chooseRandomPrime = rSAKeyPairGenerator.chooseRandomPrime(r2, publicExponent, shiftLeft);
                while (true) {
                    chooseRandomPrime2 = rSAKeyPairGenerator.chooseRandomPrime(r3, publicExponent, shiftLeft);
                    BigInteger abs = chooseRandomPrime2.subtract(chooseRandomPrime).abs();
                    if (abs.bitLength() < r5 || abs.compareTo(shiftLeft2) <= 0) {
                        rSAKeyPairGenerator = this;
                        strength = strength;
                    } else {
                        multiply = chooseRandomPrime.multiply(chooseRandomPrime2);
                        if (multiply.bitLength() != strength) {
                            chooseRandomPrime = chooseRandomPrime.max(chooseRandomPrime2);
                        }
                    }
                }
            } while (WNafUtil.getNafWeight(multiply) < r62);
            if (chooseRandomPrime.compareTo(chooseRandomPrime2) < 0) {
                bigInteger = chooseRandomPrime;
                chooseRandomPrime = chooseRandomPrime2;
            } else {
                bigInteger = chooseRandomPrime2;
            }
            BigInteger bigInteger3 = ONE;
            BigInteger subtract = chooseRandomPrime.subtract(bigInteger3);
            BigInteger subtract2 = bigInteger.subtract(bigInteger3);
            int r23 = strength;
            BigInteger modInverse = publicExponent.modInverse(subtract.divide(subtract.gcd(subtract2)).multiply(subtract2));
            if (modInverse.compareTo(pow) > 0) {
                asymmetricCipherKeyPair = new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RSAKeyParameters(false, multiply, publicExponent), (AsymmetricKeyParameter) new RSAPrivateCrtKeyParameters(multiply, publicExponent, modInverse, chooseRandomPrime, bigInteger, modInverse.remainder(subtract), modInverse.remainder(subtract2), BigIntegers.modOddInverse(chooseRandomPrime, bigInteger)));
                z = true;
            }
            rSAKeyPairGenerator = this;
            strength = r23;
        }
        return asymmetricCipherKeyPair;
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (RSAKeyGenerationParameters) keyGenerationParameters;
    }

    protected boolean isProbablePrime(BigInteger bigInteger) {
        return !Primes.hasAnySmallFactors(bigInteger) && Primes.isMRProbablePrime(bigInteger, this.param.getRandom(), getNumberOfIterations(bigInteger.bitLength(), this.param.getCertainty()));
    }
}
