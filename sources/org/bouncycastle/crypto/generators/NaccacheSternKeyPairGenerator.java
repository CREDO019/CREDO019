package org.bouncycastle.crypto.generators;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import okhttp3.internal.http.StatusLine;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NaccacheSternKeyGenerationParameters param;
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, StatusLine.HTTP_MISDIRECTED_REQUEST, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557};
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private static Vector findFirstPrimes(int r4) {
        Vector vector = new Vector(r4);
        for (int r1 = 0; r1 != r4; r1++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[r1]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int r2, int r3, SecureRandom secureRandom) {
        BigInteger createRandomPrime;
        do {
            createRandomPrime = BigIntegers.createRandomPrime(r2, r3, secureRandom);
        } while (createRandomPrime.bitLength() != r2);
        return createRandomPrime;
    }

    private static int getInt(SecureRandom secureRandom, int r5) {
        int nextInt;
        int r2;
        if (((-r5) & r5) == r5) {
            return (int) ((r5 * (secureRandom.nextInt() & Integer.MAX_VALUE)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            r2 = nextInt % r5;
        } while ((nextInt - r2) + (r5 - 1) < 0);
        return r2;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int r3 = 0; r3 < vector.size(); r3++) {
            vector3.addElement(vector.elementAt(r3));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger generatePrime;
        BigInteger add;
        BigInteger generatePrime2;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger add2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        BigInteger bigInteger7;
        boolean z;
        BigInteger bigInteger8;
        BigInteger bigInteger9;
        int r24;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        long j2;
        BigInteger createRandomPrime;
        int r28;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            PrintStream printStream2 = System.out;
            printStream2.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger10 = ONE;
        BigInteger bigInteger11 = bigInteger10;
        for (int r7 = 0; r7 < permuteList.size() / 2; r7++) {
            bigInteger11 = bigInteger11.multiply((BigInteger) permuteList.elementAt(r7));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger10 = bigInteger10.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply = bigInteger11.multiply(bigInteger10);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger11).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger10).shiftLeft(1);
        long j3 = 0;
        while (true) {
            j = j3 + 1;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (add.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        generatePrime2 = generatePrime(24, certainty, random);
                    } while (generatePrime.equals(generatePrime2));
                    BigInteger multiply2 = generatePrime2.multiply(shiftLeft2);
                    bigInteger = shiftLeft2;
                    bigInteger2 = ONE;
                    add2 = multiply2.add(bigInteger2);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    shiftLeft2 = bigInteger;
                }
                bigInteger3 = shiftLeft;
                if (!multiply.gcd(generatePrime.multiply(generatePrime2)).equals(bigInteger2)) {
                    continue;
                } else if (add.multiply(add2).bitLength() >= strength) {
                    break;
                } else if (isDebug) {
                    PrintStream printStream3 = System.out;
                    printStream3.println("key size too small. Should be " + strength + " but is actually " + add.multiply(add2).bitLength());
                }
            } else {
                bigInteger = shiftLeft2;
                bigInteger3 = shiftLeft;
            }
            j3 = j;
            shiftLeft2 = bigInteger;
            shiftLeft = bigInteger3;
        }
        BigInteger bigInteger12 = generatePrime4;
        if (isDebug) {
            PrintStream printStream4 = System.out;
            bigInteger4 = generatePrime3;
            printStream4.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger4 = generatePrime3;
        }
        BigInteger multiply3 = add.multiply(add2);
        BigInteger multiply4 = add.subtract(bigInteger2).multiply(add2.subtract(bigInteger2));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j4 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger5 = add;
            bigInteger6 = add2;
            int r15 = 0;
            while (r15 != permuteList.size()) {
                BigInteger divide = multiply4.divide((BigInteger) permuteList.elementAt(r15));
                while (true) {
                    j2 = j4 + 1;
                    createRandomPrime = BigIntegers.createRandomPrime(strength, certainty, random);
                    r28 = strength;
                    if (createRandomPrime.modPow(divide, multiply3).equals(ONE)) {
                        j4 = j2;
                        strength = r28;
                    }
                }
                vector.addElement(createRandomPrime);
                r15++;
                j4 = j2;
                strength = r28;
            }
            int r282 = strength;
            bigInteger7 = ONE;
            int r6 = 0;
            while (r6 < permuteList.size()) {
                bigInteger7 = bigInteger7.multiply(((BigInteger) vector.elementAt(r6)).modPow(multiply.divide((BigInteger) permuteList.elementAt(r6)), multiply3)).mod(multiply3);
                r6++;
                random = random;
            }
            SecureRandom secureRandom = random;
            int r2 = 0;
            while (true) {
                if (r2 >= permuteList.size()) {
                    z = false;
                    break;
                } else if (bigInteger7.modPow(multiply4.divide((BigInteger) permuteList.elementAt(r2)), multiply3).equals(ONE)) {
                    if (isDebug) {
                        PrintStream printStream5 = System.out;
                        printStream5.println("g has order phi(n)/" + permuteList.elementAt(r2) + "\n g: " + bigInteger7);
                    }
                    z = true;
                } else {
                    r2++;
                }
            }
            if (!z) {
                BigInteger modPow = bigInteger7.modPow(multiply4.divide(BigInteger.valueOf(4L)), multiply3);
                BigInteger bigInteger13 = ONE;
                if (modPow.equals(bigInteger13)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/4\n g:";
                        sb.append(str);
                        sb.append(bigInteger7);
                        printStream.println(sb.toString());
                    }
                } else if (bigInteger7.modPow(multiply4.divide(generatePrime), multiply3).equals(bigInteger13)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/p'\n g: ";
                        sb.append(str);
                        sb.append(bigInteger7);
                        printStream.println(sb.toString());
                    }
                } else if (!bigInteger7.modPow(multiply4.divide(generatePrime2), multiply3).equals(bigInteger13)) {
                    bigInteger8 = bigInteger4;
                    if (!bigInteger7.modPow(multiply4.divide(bigInteger8), multiply3).equals(bigInteger13)) {
                        bigInteger9 = bigInteger12;
                        if (!bigInteger7.modPow(multiply4.divide(bigInteger9), multiply3).equals(bigInteger13)) {
                            break;
                        } else if (isDebug) {
                            PrintStream printStream6 = System.out;
                            StringBuilder sb2 = new StringBuilder();
                            r24 = certainty;
                            sb2.append("g has order phi(n)/b\n g: ");
                            sb2.append(bigInteger7);
                            printStream6.println(sb2.toString());
                        }
                    } else {
                        if (isDebug) {
                            PrintStream printStream7 = System.out;
                            printStream7.println("g has order phi(n)/a\n g: " + bigInteger7);
                        }
                        bigInteger9 = bigInteger12;
                    }
                    r24 = certainty;
                } else if (isDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/q'\n g: ";
                    sb.append(str);
                    sb.append(bigInteger7);
                    printStream.println(sb.toString());
                }
                bigInteger4 = bigInteger8;
                certainty = r24;
                add2 = bigInteger6;
                add = bigInteger5;
                strength = r282;
                random = secureRandom;
                bigInteger12 = bigInteger9;
            }
            bigInteger9 = bigInteger12;
            bigInteger8 = bigInteger4;
            r24 = certainty;
            bigInteger4 = bigInteger8;
            certainty = r24;
            add2 = bigInteger6;
            add = bigInteger5;
            strength = r282;
            random = secureRandom;
            bigInteger12 = bigInteger9;
        }
        if (isDebug) {
            PrintStream printStream8 = System.out;
            printStream8.println("needed " + j4 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            PrintStream printStream9 = System.out;
            printStream9.println("smallPrimes: " + permuteList);
            PrintStream printStream10 = System.out;
            printStream10.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            PrintStream printStream11 = System.out;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("a:.......... ");
            sb3.append(bigInteger8);
            printStream11.println(sb3.toString());
            PrintStream printStream12 = System.out;
            printStream12.println("b:.......... " + bigInteger9);
            PrintStream printStream13 = System.out;
            printStream13.println("p':......... " + generatePrime);
            PrintStream printStream14 = System.out;
            printStream14.println("q':......... " + generatePrime2);
            PrintStream printStream15 = System.out;
            printStream15.println("p:.......... " + bigInteger5);
            PrintStream printStream16 = System.out;
            printStream16.println("q:.......... " + bigInteger6);
            PrintStream printStream17 = System.out;
            printStream17.println("n:.......... " + multiply3);
            PrintStream printStream18 = System.out;
            printStream18.println("phi(n):..... " + multiply4);
            PrintStream printStream19 = System.out;
            printStream19.println("g:.......... " + bigInteger7);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger7, multiply3, multiply.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger7, multiply3, multiply.bitLength(), permuteList, multiply4));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
