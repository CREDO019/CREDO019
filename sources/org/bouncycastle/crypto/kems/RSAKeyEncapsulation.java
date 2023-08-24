package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.KeyEncapsulation;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes5.dex */
public class RSAKeyEncapsulation implements KeyEncapsulation {
    private DerivationFunction kdf;
    private RSAKeyParameters key;
    private SecureRandom rnd;
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private static final BigInteger ONE = BigInteger.valueOf(1);

    public RSAKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
    }

    public CipherParameters decrypt(byte[] bArr, int r4) {
        return decrypt(bArr, 0, bArr.length, r4);
    }

    @Override // org.bouncycastle.crypto.KeyEncapsulation
    public CipherParameters decrypt(byte[] bArr, int r6, int r7, int r8) throws IllegalArgumentException {
        if (this.key.isPrivate()) {
            BigInteger modulus = this.key.getModulus();
            BigInteger exponent = this.key.getExponent();
            byte[] bArr2 = new byte[r7];
            System.arraycopy(bArr, r6, bArr2, 0, r7);
            return generateKey(modulus, new BigInteger(1, bArr2).modPow(exponent, modulus), r8);
        }
        throw new IllegalArgumentException("Private key required for decryption");
    }

    public CipherParameters encrypt(byte[] bArr, int r3) {
        return encrypt(bArr, 0, r3);
    }

    @Override // org.bouncycastle.crypto.KeyEncapsulation
    public CipherParameters encrypt(byte[] bArr, int r7, int r8) throws IllegalArgumentException {
        if (this.key.isPrivate()) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        BigInteger modulus = this.key.getModulus();
        BigInteger exponent = this.key.getExponent();
        BigInteger createRandomInRange = BigIntegers.createRandomInRange(ZERO, modulus.subtract(ONE), this.rnd);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray((modulus.bitLength() + 7) / 8, createRandomInRange.modPow(exponent, modulus));
        System.arraycopy(asUnsignedByteArray, 0, bArr, r7, asUnsignedByteArray.length);
        return generateKey(modulus, createRandomInRange, r8);
    }

    protected KeyParameter generateKey(BigInteger bigInteger, BigInteger bigInteger2, int r5) {
        this.kdf.init(new KDFParameters(BigIntegers.asUnsignedByteArray((bigInteger.bitLength() + 7) / 8, bigInteger2), null));
        byte[] bArr = new byte[r5];
        this.kdf.generateBytes(bArr, 0, r5);
        return new KeyParameter(bArr);
    }

    @Override // org.bouncycastle.crypto.KeyEncapsulation
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof RSAKeyParameters)) {
            throw new IllegalArgumentException("RSA key required");
        }
        this.key = (RSAKeyParameters) cipherParameters;
    }
}
