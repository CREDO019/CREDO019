package org.bouncycastle.crypto.signers;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class PSSSigner implements Signer {
    public static final byte TRAILER_IMPLICIT = -68;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest contentDigest;
    private int emBits;
    private int hLen;
    private byte[] mDash;
    private Digest mgfDigest;
    private int mgfhLen;
    private SecureRandom random;
    private int sLen;
    private boolean sSet;
    private byte[] salt;
    private byte trailer;

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int r4) {
        this(asymmetricBlockCipher, digest, r4, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, int r9, byte b) {
        this(asymmetricBlockCipher, digest, digest, r9, b);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int r10) {
        this(asymmetricBlockCipher, digest, digest2, r10, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int r4, byte b) {
        this.cipher = asymmetricBlockCipher;
        this.contentDigest = digest;
        this.mgfDigest = digest2;
        this.hLen = digest.getDigestSize();
        this.mgfhLen = digest2.getDigestSize();
        this.sSet = false;
        this.sLen = r4;
        this.salt = new byte[r4];
        this.mDash = new byte[r4 + 8 + this.hLen];
        this.trailer = b;
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest2, bArr, (byte) TRAILER_IMPLICIT);
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr, byte b) {
        this.cipher = asymmetricBlockCipher;
        this.contentDigest = digest;
        this.mgfDigest = digest2;
        this.hLen = digest.getDigestSize();
        this.mgfhLen = digest2.getDigestSize();
        this.sSet = true;
        int length = bArr.length;
        this.sLen = length;
        this.salt = bArr;
        this.mDash = new byte[length + 8 + this.hLen];
        this.trailer = b;
    }

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest, bArr, (byte) TRAILER_IMPLICIT);
    }

    private void ItoOSP(int r4, byte[] bArr) {
        bArr[0] = (byte) (r4 >>> 24);
        bArr[1] = (byte) (r4 >>> 16);
        bArr[2] = (byte) (r4 >>> 8);
        bArr[3] = (byte) (r4 >>> 0);
    }

    private void clearBlock(byte[] bArr) {
        for (int r1 = 0; r1 != bArr.length; r1++) {
            bArr[r1] = 0;
        }
    }

    private byte[] maskGenerator(byte[] bArr, int r4, int r5, int r6) {
        Digest digest = this.mgfDigest;
        if (digest instanceof Xof) {
            byte[] bArr2 = new byte[r6];
            digest.update(bArr, r4, r5);
            ((Xof) this.mgfDigest).doFinal(bArr2, 0, r6);
            return bArr2;
        }
        return maskGeneratorFunction1(bArr, r4, r5, r6);
    }

    private byte[] maskGeneratorFunction1(byte[] bArr, int r10, int r11, int r12) {
        int r6;
        byte[] bArr2 = new byte[r12];
        byte[] bArr3 = new byte[this.mgfhLen];
        byte[] bArr4 = new byte[4];
        this.mgfDigest.reset();
        int r5 = 0;
        while (true) {
            r6 = this.mgfhLen;
            if (r5 >= r12 / r6) {
                break;
            }
            ItoOSP(r5, bArr4);
            this.mgfDigest.update(bArr, r10, r11);
            this.mgfDigest.update(bArr4, 0, 4);
            this.mgfDigest.doFinal(bArr3, 0);
            int r62 = this.mgfhLen;
            System.arraycopy(bArr3, 0, bArr2, r5 * r62, r62);
            r5++;
        }
        if (r6 * r5 < r12) {
            ItoOSP(r5, bArr4);
            this.mgfDigest.update(bArr, r10, r11);
            this.mgfDigest.update(bArr4, 0, 4);
            this.mgfDigest.doFinal(bArr3, 0);
            int r9 = this.mgfhLen;
            System.arraycopy(bArr3, 0, bArr2, r5 * r9, r12 - (r5 * r9));
        }
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException, DataLengthException {
        Digest digest = this.contentDigest;
        byte[] bArr = this.mDash;
        digest.doFinal(bArr, (bArr.length - this.hLen) - this.sLen);
        if (this.sLen != 0) {
            if (!this.sSet) {
                this.random.nextBytes(this.salt);
            }
            byte[] bArr2 = this.salt;
            byte[] bArr3 = this.mDash;
            int length = bArr3.length;
            int r4 = this.sLen;
            System.arraycopy(bArr2, 0, bArr3, length - r4, r4);
        }
        int r0 = this.hLen;
        byte[] bArr4 = new byte[r0];
        Digest digest2 = this.contentDigest;
        byte[] bArr5 = this.mDash;
        digest2.update(bArr5, 0, bArr5.length);
        this.contentDigest.doFinal(bArr4, 0);
        byte[] bArr6 = this.block;
        int length2 = bArr6.length;
        int r5 = this.sLen;
        int r7 = this.hLen;
        bArr6[(((length2 - r5) - 1) - r7) - 1] = 1;
        System.arraycopy(this.salt, 0, bArr6, ((bArr6.length - r5) - r7) - 1, r5);
        byte[] maskGenerator = maskGenerator(bArr4, 0, r0, (this.block.length - this.hLen) - 1);
        for (int r3 = 0; r3 != maskGenerator.length; r3++) {
            byte[] bArr7 = this.block;
            bArr7[r3] = (byte) (bArr7[r3] ^ maskGenerator[r3]);
        }
        byte[] bArr8 = this.block;
        int length3 = bArr8.length;
        int r42 = this.hLen;
        System.arraycopy(bArr4, 0, bArr8, (length3 - r42) - 1, r42);
        byte[] bArr9 = this.block;
        bArr9[0] = (byte) ((255 >>> ((bArr9.length * 8) - this.emBits)) & bArr9[0]);
        bArr9[bArr9.length - 1] = this.trailer;
        byte[] processBlock = this.cipher.processBlock(bArr9, 0, bArr9.length);
        clearBlock(this.block);
        return processBlock;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        RSAKeyParameters rSAKeyParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            cipherParameters2 = parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            if (z) {
                this.random = CryptoServicesRegistrar.getSecureRandom();
            }
            cipherParameters2 = cipherParameters;
        }
        if (cipherParameters2 instanceof RSABlindingParameters) {
            rSAKeyParameters = ((RSABlindingParameters) cipherParameters2).getPublicKey();
            this.cipher.init(z, cipherParameters);
        } else {
            rSAKeyParameters = (RSAKeyParameters) cipherParameters2;
            this.cipher.init(z, cipherParameters2);
        }
        int bitLength = rSAKeyParameters.getModulus().bitLength() - 1;
        this.emBits = bitLength;
        if (bitLength < (this.hLen * 8) + (this.sLen * 8) + 9) {
            throw new IllegalArgumentException("key too small for specified hash and salt lengths");
        }
        this.block = new byte[(bitLength + 7) / 8];
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.contentDigest.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.contentDigest.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int r3, int r4) {
        this.contentDigest.update(bArr, r3, r4);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] bArr2;
        int length;
        Digest digest = this.contentDigest;
        byte[] bArr3 = this.mDash;
        digest.doFinal(bArr3, (bArr3.length - this.hLen) - this.sLen);
        try {
            byte[] processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
            byte[] bArr4 = this.block;
            Arrays.fill(bArr4, 0, bArr4.length - processBlock.length, (byte) 0);
            byte[] bArr5 = this.block;
            System.arraycopy(processBlock, 0, bArr5, bArr5.length - processBlock.length, processBlock.length);
            bArr2 = this.block;
            length = 255 >>> ((bArr2.length * 8) - this.emBits);
        } catch (Exception unused) {
        }
        if ((255 & bArr2[0]) != (bArr2[0] & length) || bArr2[bArr2.length - 1] != this.trailer) {
            clearBlock(bArr2);
            return false;
        }
        int length2 = bArr2.length;
        int r4 = this.hLen;
        byte[] maskGenerator = maskGenerator(bArr2, (length2 - r4) - 1, r4, (bArr2.length - r4) - 1);
        for (int r2 = 0; r2 != maskGenerator.length; r2++) {
            byte[] bArr6 = this.block;
            bArr6[r2] = (byte) (bArr6[r2] ^ maskGenerator[r2]);
        }
        byte[] bArr7 = this.block;
        bArr7[0] = (byte) (length & bArr7[0]);
        int r8 = 0;
        while (true) {
            byte[] bArr8 = this.block;
            int length3 = bArr8.length;
            int r42 = this.hLen;
            int r5 = this.sLen;
            if (r8 != ((length3 - r42) - r5) - 2) {
                if (bArr8[r8] != 0) {
                    clearBlock(bArr8);
                    return false;
                }
                r8++;
            } else if (bArr8[((bArr8.length - r42) - r5) - 2] != 1) {
                clearBlock(bArr8);
                return false;
            } else {
                if (this.sSet) {
                    byte[] bArr9 = this.salt;
                    byte[] bArr10 = this.mDash;
                    System.arraycopy(bArr9, 0, bArr10, bArr10.length - r5, r5);
                } else {
                    byte[] bArr11 = this.mDash;
                    System.arraycopy(bArr8, ((bArr8.length - r5) - r42) - 1, bArr11, bArr11.length - r5, r5);
                }
                Digest digest2 = this.contentDigest;
                byte[] bArr12 = this.mDash;
                digest2.update(bArr12, 0, bArr12.length);
                Digest digest3 = this.contentDigest;
                byte[] bArr13 = this.mDash;
                digest3.doFinal(bArr13, bArr13.length - this.hLen);
                int length4 = this.block.length;
                int r1 = this.hLen;
                int r82 = (length4 - r1) - 1;
                int length5 = this.mDash.length - r1;
                while (true) {
                    byte[] bArr14 = this.mDash;
                    if (length5 == bArr14.length) {
                        clearBlock(bArr14);
                        clearBlock(this.block);
                        return true;
                    } else if ((this.block[r82] ^ bArr14[length5]) != 0) {
                        clearBlock(bArr14);
                        clearBlock(this.block);
                        return false;
                    } else {
                        r82++;
                        length5++;
                    }
                }
            }
        }
    }
}
