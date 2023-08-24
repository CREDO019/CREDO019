package org.bouncycastle.crypto.encodings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class OAEPEncoding implements AsymmetricBlockCipher {
    private byte[] defHash;
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private Digest mgf1Hash;
    private SecureRandom random;

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this(asymmetricBlockCipher, DigestFactory.createSHA1(), null);
    }

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, null);
    }

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr) {
        this.engine = asymmetricBlockCipher;
        this.mgf1Hash = digest2;
        this.defHash = new byte[digest.getDigestSize()];
        digest.reset();
        if (bArr != null) {
            digest.update(bArr, 0, bArr.length);
        }
        digest.doFinal(this.defHash, 0);
    }

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest, bArr);
    }

    private byte[] maskGeneratorFunction1(byte[] bArr, int r10, int r11, int r12) {
        byte[] bArr2 = new byte[r12];
        int digestSize = this.mgf1Hash.getDigestSize();
        byte[] bArr3 = new byte[digestSize];
        byte[] bArr4 = new byte[4];
        this.mgf1Hash.reset();
        int r6 = 0;
        while (r6 < r12 / digestSize) {
            Pack.intToBigEndian(r6, bArr4, 0);
            this.mgf1Hash.update(bArr, r10, r11);
            this.mgf1Hash.update(bArr4, 0, 4);
            this.mgf1Hash.doFinal(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, r6 * digestSize, digestSize);
            r6++;
        }
        int r1 = digestSize * r6;
        if (r1 < r12) {
            Pack.intToBigEndian(r6, bArr4, 0);
            this.mgf1Hash.update(bArr, r10, r11);
            this.mgf1Hash.update(bArr4, 0, 4);
            this.mgf1Hash.doFinal(bArr3, 0);
            System.arraycopy(bArr3, 0, bArr2, r1, r12 - r1);
        }
        return bArr2;
    }

    public byte[] decodeBlock(byte[] bArr, int r9, int r10) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        byte[] processBlock = this.engine.processBlock(bArr, r9, r10);
        int outputBlockSize = this.engine.getOutputBlockSize();
        byte[] bArr4 = new byte[outputBlockSize];
        boolean z = outputBlockSize < (this.defHash.length * 2) + 1;
        if (processBlock.length <= outputBlockSize) {
            System.arraycopy(processBlock, 0, bArr4, outputBlockSize - processBlock.length, processBlock.length);
        } else {
            System.arraycopy(processBlock, 0, bArr4, 0, outputBlockSize);
            z = true;
        }
        byte[] bArr5 = this.defHash;
        byte[] maskGeneratorFunction1 = maskGeneratorFunction1(bArr4, bArr5.length, outputBlockSize - bArr5.length, bArr5.length);
        int r3 = 0;
        while (true) {
            bArr2 = this.defHash;
            if (r3 == bArr2.length) {
                break;
            }
            bArr4[r3] = (byte) (bArr4[r3] ^ maskGeneratorFunction1[r3]);
            r3++;
        }
        byte[] maskGeneratorFunction12 = maskGeneratorFunction1(bArr4, 0, bArr2.length, outputBlockSize - bArr2.length);
        for (int length = this.defHash.length; length != outputBlockSize; length++) {
            bArr4[length] = (byte) (bArr4[length] ^ maskGeneratorFunction12[length - this.defHash.length]);
        }
        int r8 = 0;
        boolean z2 = false;
        while (true) {
            bArr3 = this.defHash;
            if (r8 == bArr3.length) {
                break;
            }
            if (bArr3[r8] != bArr4[bArr3.length + r8]) {
                z2 = true;
            }
            r8++;
        }
        int r4 = outputBlockSize;
        for (int length2 = bArr3.length * 2; length2 != outputBlockSize; length2++) {
            if ((bArr4[length2] != 0) & (r4 == outputBlockSize)) {
                r4 = length2;
            }
        }
        boolean z3 = r4 > outputBlockSize + (-1);
        boolean z4 = bArr4[r4] != 1;
        int r42 = r4 + 1;
        if ((z3 | z4) || (z | z2)) {
            Arrays.fill(bArr4, (byte) 0);
            throw new InvalidCipherTextException("data wrong");
        }
        int r92 = outputBlockSize - r42;
        byte[] bArr6 = new byte[r92];
        System.arraycopy(bArr4, r42, bArr6, 0, r92);
        Arrays.fill(bArr4, (byte) 0);
        return bArr6;
    }

    public byte[] encodeBlock(byte[] bArr, int r7, int r8) throws InvalidCipherTextException {
        if (r8 <= getInputBlockSize()) {
            int inputBlockSize = getInputBlockSize() + 1 + (this.defHash.length * 2);
            byte[] bArr2 = new byte[inputBlockSize];
            int r3 = inputBlockSize - r8;
            System.arraycopy(bArr, r7, bArr2, r3, r8);
            bArr2[r3 - 1] = 1;
            byte[] bArr3 = this.defHash;
            System.arraycopy(bArr3, 0, bArr2, bArr3.length, bArr3.length);
            int length = this.defHash.length;
            byte[] bArr4 = new byte[length];
            this.random.nextBytes(bArr4);
            byte[] maskGeneratorFunction1 = maskGeneratorFunction1(bArr4, 0, length, inputBlockSize - this.defHash.length);
            for (int length2 = this.defHash.length; length2 != inputBlockSize; length2++) {
                bArr2[length2] = (byte) (bArr2[length2] ^ maskGeneratorFunction1[length2 - this.defHash.length]);
            }
            System.arraycopy(bArr4, 0, bArr2, 0, this.defHash.length);
            byte[] bArr5 = this.defHash;
            byte[] maskGeneratorFunction12 = maskGeneratorFunction1(bArr2, bArr5.length, inputBlockSize - bArr5.length, bArr5.length);
            for (int r72 = 0; r72 != this.defHash.length; r72++) {
                bArr2[r72] = (byte) (bArr2[r72] ^ maskGeneratorFunction12[r72]);
            }
            return this.engine.processBlock(bArr2, 0, inputBlockSize);
        }
        throw new DataLengthException("input data too long");
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? (inputBlockSize - 1) - (this.defHash.length * 2) : inputBlockSize;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? outputBlockSize : (outputBlockSize - 1) - (this.defHash.length * 2);
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        this.random = cipherParameters instanceof ParametersWithRandom ? ((ParametersWithRandom) cipherParameters).getRandom() : CryptoServicesRegistrar.getSecureRandom();
        this.engine.init(z, cipherParameters);
        this.forEncryption = z;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int r3, int r4) throws InvalidCipherTextException {
        return this.forEncryption ? encodeBlock(bArr, r3, r4) : decodeBlock(bArr, r3, r4);
    }
}
