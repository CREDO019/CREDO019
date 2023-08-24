package org.bouncycastle.crypto.engines;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class NaccacheSternEngine implements AsymmetricBlockCipher {
    private boolean forEncryption;
    private NaccacheSternKeyParameters key;
    private static BigInteger ZERO = BigInteger.valueOf(0);
    private static BigInteger ONE = BigInteger.valueOf(1);
    private Vector[] lookup = null;
    private boolean debug = false;

    private static BigInteger chineseRemainder(Vector vector, Vector vector2) {
        BigInteger bigInteger = ZERO;
        BigInteger bigInteger2 = ONE;
        for (int r3 = 0; r3 < vector2.size(); r3++) {
            bigInteger2 = bigInteger2.multiply((BigInteger) vector2.elementAt(r3));
        }
        for (int r2 = 0; r2 < vector2.size(); r2++) {
            BigInteger bigInteger3 = (BigInteger) vector2.elementAt(r2);
            BigInteger divide = bigInteger2.divide(bigInteger3);
            bigInteger = bigInteger.add(divide.multiply(divide.modInverse(bigInteger3)).multiply((BigInteger) vector.elementAt(r2)));
        }
        return bigInteger.mod(bigInteger2);
    }

    public byte[] addCryptedBlocks(byte[] bArr, byte[] bArr2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            if (bArr.length > getOutputBlockSize() || bArr2.length > getOutputBlockSize()) {
                throw new InvalidCipherTextException("BlockLength too large for simple addition.\n");
            }
        } else if (bArr.length > getInputBlockSize() || bArr2.length > getInputBlockSize()) {
            throw new InvalidCipherTextException("BlockLength too large for simple addition.\n");
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        BigInteger bigInteger2 = new BigInteger(1, bArr2);
        BigInteger mod = bigInteger.multiply(bigInteger2).mod(this.key.getModulus());
        if (this.debug) {
            PrintStream printStream = System.out;
            printStream.println("c(m1) as BigInteger:....... " + bigInteger);
            PrintStream printStream2 = System.out;
            printStream2.println("c(m2) as BigInteger:....... " + bigInteger2);
            PrintStream printStream3 = System.out;
            printStream3.println("c(m1)*c(m2)%n = c(m1+m2)%n: " + mod);
        }
        byte[] byteArray = this.key.getModulus().toByteArray();
        Arrays.fill(byteArray, (byte) 0);
        System.arraycopy(mod.toByteArray(), 0, byteArray, byteArray.length - mod.toByteArray().length, mod.toByteArray().length);
        return byteArray;
    }

    public byte[] encrypt(BigInteger bigInteger) {
        byte[] byteArray = this.key.getModulus().toByteArray();
        Arrays.fill(byteArray, (byte) 0);
        byte[] byteArray2 = this.key.getG().modPow(bigInteger, this.key.getModulus()).toByteArray();
        System.arraycopy(byteArray2, 0, byteArray, byteArray.length - byteArray2.length, byteArray2.length);
        if (this.debug) {
            PrintStream printStream = System.out;
            printStream.println("Encrypted value is:  " + new BigInteger(byteArray));
        }
        return byteArray;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.forEncryption ? ((this.key.getLowerSigmaBound() + 7) / 8) - 1 : this.key.getModulus().toByteArray().length;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.forEncryption ? this.key.getModulus().toByteArray().length : ((this.key.getLowerSigmaBound() + 7) / 8) - 1;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        this.key = (NaccacheSternKeyParameters) cipherParameters;
        if (this.forEncryption) {
            return;
        }
        if (this.debug) {
            System.out.println("Constructing lookup Array");
        }
        NaccacheSternPrivateKeyParameters naccacheSternPrivateKeyParameters = (NaccacheSternPrivateKeyParameters) this.key;
        Vector smallPrimes = naccacheSternPrivateKeyParameters.getSmallPrimes();
        this.lookup = new Vector[smallPrimes.size()];
        for (int r0 = 0; r0 < smallPrimes.size(); r0++) {
            BigInteger bigInteger = (BigInteger) smallPrimes.elementAt(r0);
            int intValue = bigInteger.intValue();
            this.lookup[r0] = new Vector();
            this.lookup[r0].addElement(ONE);
            if (this.debug) {
                PrintStream printStream = System.out;
                printStream.println("Constructing lookup ArrayList for " + intValue);
            }
            BigInteger bigInteger2 = ZERO;
            for (int r4 = 1; r4 < intValue; r4++) {
                bigInteger2 = bigInteger2.add(naccacheSternPrivateKeyParameters.getPhi_n());
                this.lookup[r0].addElement(naccacheSternPrivateKeyParameters.getG().modPow(bigInteger2.divide(bigInteger), naccacheSternPrivateKeyParameters.getModulus()));
            }
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int r9, int r10) throws InvalidCipherTextException {
        if (this.key != null) {
            if (r10 <= getInputBlockSize() + 1) {
                if (this.forEncryption || r10 >= getInputBlockSize()) {
                    if (r9 != 0 || r10 != bArr.length) {
                        byte[] bArr2 = new byte[r10];
                        System.arraycopy(bArr, r9, bArr2, 0, r10);
                        bArr = bArr2;
                    }
                    BigInteger bigInteger = new BigInteger(1, bArr);
                    if (this.debug) {
                        PrintStream printStream = System.out;
                        printStream.println("input as BigInteger: " + bigInteger);
                    }
                    if (this.forEncryption) {
                        return encrypt(bigInteger);
                    }
                    Vector vector = new Vector();
                    NaccacheSternPrivateKeyParameters naccacheSternPrivateKeyParameters = (NaccacheSternPrivateKeyParameters) this.key;
                    Vector smallPrimes = naccacheSternPrivateKeyParameters.getSmallPrimes();
                    for (int r2 = 0; r2 < smallPrimes.size(); r2++) {
                        BigInteger modPow = bigInteger.modPow(naccacheSternPrivateKeyParameters.getPhi_n().divide((BigInteger) smallPrimes.elementAt(r2)), naccacheSternPrivateKeyParameters.getModulus());
                        Vector[] vectorArr = this.lookup;
                        Vector vector2 = vectorArr[r2];
                        if (vectorArr[r2].size() != ((BigInteger) smallPrimes.elementAt(r2)).intValue()) {
                            if (this.debug) {
                                PrintStream printStream2 = System.out;
                                printStream2.println("Prime is " + smallPrimes.elementAt(r2) + ", lookup table has size " + vector2.size());
                            }
                            throw new InvalidCipherTextException("Error in lookup Array for " + ((BigInteger) smallPrimes.elementAt(r2)).intValue() + ": Size mismatch. Expected ArrayList with length " + ((BigInteger) smallPrimes.elementAt(r2)).intValue() + " but found ArrayList of length " + this.lookup[r2].size());
                        }
                        int indexOf = vector2.indexOf(modPow);
                        if (indexOf == -1) {
                            if (this.debug) {
                                PrintStream printStream3 = System.out;
                                printStream3.println("Actual prime is " + smallPrimes.elementAt(r2));
                                PrintStream printStream4 = System.out;
                                printStream4.println("Decrypted value is " + modPow);
                                PrintStream printStream5 = System.out;
                                printStream5.println("LookupList for " + smallPrimes.elementAt(r2) + " with size " + this.lookup[r2].size() + " is: ");
                                for (int r0 = 0; r0 < this.lookup[r2].size(); r0++) {
                                    System.out.println(this.lookup[r2].elementAt(r0));
                                }
                            }
                            throw new InvalidCipherTextException("Lookup failed");
                        }
                        vector.addElement(BigInteger.valueOf(indexOf));
                    }
                    return chineseRemainder(vector, smallPrimes).toByteArray();
                }
                throw new InvalidCipherTextException("BlockLength does not match modulus for Naccache-Stern cipher.\n");
            }
            throw new DataLengthException("input too large for Naccache-Stern cipher.\n");
        }
        throw new IllegalStateException("NaccacheStern engine not initialised");
    }

    public byte[] processData(byte[] bArr) throws InvalidCipherTextException {
        byte[] processBlock;
        if (this.debug) {
            System.out.println();
        }
        if (bArr.length <= getInputBlockSize()) {
            if (this.debug) {
                System.out.println("data size is less then input block size, processing directly");
            }
            return processBlock(bArr, 0, bArr.length);
        }
        int inputBlockSize = getInputBlockSize();
        int outputBlockSize = getOutputBlockSize();
        if (this.debug) {
            PrintStream printStream = System.out;
            printStream.println("Input blocksize is:  " + inputBlockSize + " bytes");
            PrintStream printStream2 = System.out;
            printStream2.println("Output blocksize is: " + outputBlockSize + " bytes");
            PrintStream printStream3 = System.out;
            printStream3.println("Data has length:.... " + bArr.length + " bytes");
        }
        byte[] bArr2 = new byte[((bArr.length / inputBlockSize) + 1) * outputBlockSize];
        int r3 = 0;
        int r5 = 0;
        while (r3 < bArr.length) {
            int r6 = r3 + inputBlockSize;
            if (r6 < bArr.length) {
                processBlock = processBlock(bArr, r3, inputBlockSize);
                r3 = r6;
            } else {
                processBlock = processBlock(bArr, r3, bArr.length - r3);
                r3 += bArr.length - r3;
            }
            if (this.debug) {
                PrintStream printStream4 = System.out;
                printStream4.println("new datapos is " + r3);
            }
            if (processBlock == null) {
                if (this.debug) {
                    System.out.println("cipher returned null");
                }
                throw new InvalidCipherTextException("cipher returned null");
            }
            System.arraycopy(processBlock, 0, bArr2, r5, processBlock.length);
            r5 += processBlock.length;
        }
        byte[] bArr3 = new byte[r5];
        System.arraycopy(bArr2, 0, bArr3, 0, r5);
        if (this.debug) {
            PrintStream printStream5 = System.out;
            printStream5.println("returning " + r5 + " bytes");
        }
        return bArr3;
    }

    public void setDebug(boolean z) {
        this.debug = z;
    }
}
