package org.bouncycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.pqc.crypto.MessageEncryptor;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

/* loaded from: classes3.dex */
public class McElieceKobaraImaiCipher implements MessageEncryptor {
    private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.3";
    public static final byte[] PUBLIC_CONSTANT = "a predetermined public constant".getBytes();
    private boolean forEncryption;

    /* renamed from: k */
    private int f2463k;
    McElieceCCA2KeyParameters key;
    private Digest messDigest;

    /* renamed from: n */
    private int f2464n;

    /* renamed from: sr */
    private SecureRandom f2465sr;

    /* renamed from: t */
    private int f2466t;

    private void initCipherDecrypt(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.messDigest = C5391Utils.getDigest(mcElieceCCA2PrivateKeyParameters.getDigest());
        this.f2464n = mcElieceCCA2PrivateKeyParameters.getN();
        this.f2463k = mcElieceCCA2PrivateKeyParameters.getK();
        this.f2466t = mcElieceCCA2PrivateKeyParameters.getT();
    }

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        this.messDigest = C5391Utils.getDigest(mcElieceCCA2PublicKeyParameters.getDigest());
        this.f2464n = mcElieceCCA2PublicKeyParameters.getN();
        this.f2463k = mcElieceCCA2PublicKeyParameters.getK();
        this.f2466t = mcElieceCCA2PublicKeyParameters.getT();
    }

    public int getKeySize(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) {
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        if (!z) {
            McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters = (McElieceCCA2PrivateKeyParameters) cipherParameters;
            this.key = mcElieceCCA2PrivateKeyParameters;
            initCipherDecrypt(mcElieceCCA2PrivateKeyParameters);
        } else if (!(cipherParameters instanceof ParametersWithRandom)) {
            this.f2465sr = CryptoServicesRegistrar.getSecureRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters = (McElieceCCA2PublicKeyParameters) cipherParameters;
            this.key = mcElieceCCA2PublicKeyParameters;
            initCipherEncrypt(mcElieceCCA2PublicKeyParameters);
        } else {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f2465sr = parametersWithRandom.getRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters2 = (McElieceCCA2PublicKeyParameters) parametersWithRandom.getParameters();
            this.key = mcElieceCCA2PublicKeyParameters2;
            initCipherEncrypt(mcElieceCCA2PublicKeyParameters2);
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public byte[] messageDecrypt(byte[] bArr) throws InvalidCipherTextException {
        byte[] bArr2;
        if (this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int r0 = this.f2464n >> 3;
        if (bArr.length >= r0) {
            int digestSize = this.messDigest.getDigestSize();
            int r2 = this.f2463k >> 3;
            int bitLength = (IntegerFunctions.binomial(this.f2464n, this.f2466t).bitLength() - 1) >> 3;
            int length = bArr.length - r0;
            if (length > 0) {
                byte[][] split = ByteUtils.split(bArr, length);
                bArr2 = split[0];
                bArr = split[1];
            } else {
                bArr2 = new byte[0];
            }
            GF2Vector[] decryptionPrimitive = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters) this.key, GF2Vector.OS2VP(this.f2464n, bArr));
            byte[] encoded = decryptionPrimitive[0].getEncoded();
            GF2Vector gF2Vector = decryptionPrimitive[1];
            if (encoded.length > r2) {
                encoded = ByteUtils.subArray(encoded, 0, r2);
            }
            byte[] decode = Conversions.decode(this.f2464n, this.f2466t, gF2Vector);
            if (decode.length < bitLength) {
                byte[] bArr3 = new byte[bitLength];
                System.arraycopy(decode, 0, bArr3, bitLength - decode.length, decode.length);
                decode = bArr3;
            }
            byte[] concatenate = ByteUtils.concatenate(ByteUtils.concatenate(bArr2, decode), encoded);
            int length2 = concatenate.length - digestSize;
            byte[][] split2 = ByteUtils.split(concatenate, digestSize);
            byte[] bArr4 = split2[0];
            byte[] bArr5 = split2[1];
            byte[] bArr6 = new byte[this.messDigest.getDigestSize()];
            this.messDigest.update(bArr5, 0, bArr5.length);
            this.messDigest.doFinal(bArr6, 0);
            for (int r1 = digestSize - 1; r1 >= 0; r1--) {
                bArr6[r1] = (byte) (bArr6[r1] ^ bArr4[r1]);
            }
            DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
            digestRandomGenerator.addSeedMaterial(bArr6);
            byte[] bArr7 = new byte[length2];
            digestRandomGenerator.nextBytes(bArr7);
            for (int r12 = length2 - 1; r12 >= 0; r12--) {
                bArr7[r12] = (byte) (bArr7[r12] ^ bArr5[r12]);
            }
            byte[] bArr8 = PUBLIC_CONSTANT;
            byte[][] split3 = ByteUtils.split(bArr7, length2 - bArr8.length);
            byte[] bArr9 = split3[0];
            if (ByteUtils.equals(split3[1], bArr8)) {
                return bArr9;
            }
            throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
        }
        throw new InvalidCipherTextException("Bad Padding: Ciphertext too short.");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageEncryptor
    public byte[] messageEncrypt(byte[] bArr) {
        if (this.forEncryption) {
            int digestSize = this.messDigest.getDigestSize();
            int r1 = this.f2463k >> 3;
            int bitLength = (IntegerFunctions.binomial(this.f2464n, this.f2466t).bitLength() - 1) >> 3;
            byte[] bArr2 = PUBLIC_CONSTANT;
            int length = ((r1 + bitLength) - digestSize) - bArr2.length;
            if (bArr.length > length) {
                length = bArr.length;
            }
            int length2 = bArr2.length + length;
            int r6 = ((length2 + digestSize) - r1) - bitLength;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, length, bArr2.length);
            byte[] bArr4 = new byte[digestSize];
            this.f2465sr.nextBytes(bArr4);
            DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
            digestRandomGenerator.addSeedMaterial(bArr4);
            byte[] bArr5 = new byte[length2];
            digestRandomGenerator.nextBytes(bArr5);
            for (int r3 = length2 - 1; r3 >= 0; r3--) {
                bArr5[r3] = (byte) (bArr5[r3] ^ bArr3[r3]);
            }
            byte[] bArr6 = new byte[this.messDigest.getDigestSize()];
            this.messDigest.update(bArr5, 0, length2);
            this.messDigest.doFinal(bArr6, 0);
            for (int r0 = digestSize - 1; r0 >= 0; r0--) {
                bArr6[r0] = (byte) (bArr6[r0] ^ bArr4[r0]);
            }
            byte[] concatenate = ByteUtils.concatenate(bArr6, bArr5);
            byte[] bArr7 = new byte[0];
            if (r6 > 0) {
                bArr7 = new byte[r6];
                System.arraycopy(concatenate, 0, bArr7, 0, r6);
            }
            byte[] bArr8 = new byte[bitLength];
            System.arraycopy(concatenate, r6, bArr8, 0, bitLength);
            byte[] bArr9 = new byte[r1];
            System.arraycopy(concatenate, bitLength + r6, bArr9, 0, r1);
            byte[] encoded = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters) this.key, GF2Vector.OS2VP(this.f2463k, bArr9), Conversions.encode(this.f2464n, this.f2466t, bArr8)).getEncoded();
            return r6 > 0 ? ByteUtils.concatenate(bArr7, encoded) : encoded;
        }
        throw new IllegalStateException("cipher initialised for decryption");
    }
}
