package org.bouncycastle.crypto.engines;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class RC2WrapEngine implements Wrapper {
    private static final byte[] IV2 = {74, -35, -94, 44, 121, -24, 33, 5};
    private CBCBlockCipher engine;
    private boolean forWrapping;

    /* renamed from: iv */
    private byte[] f1942iv;
    private CipherParameters param;
    private ParametersWithIV paramPlusIV;

    /* renamed from: sr */
    private SecureRandom f1943sr;
    Digest sha1 = DigestFactory.createSHA1();
    byte[] digest = new byte[20];

    private byte[] calculateCMSKeyChecksum(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        this.sha1.update(bArr, 0, bArr.length);
        this.sha1.doFinal(this.digest, 0);
        System.arraycopy(this.digest, 0, bArr2, 0, 8);
        return bArr2;
    }

    private boolean checkCMSKeyChecksum(byte[] bArr, byte[] bArr2) {
        return Arrays.constantTimeAreEqual(calculateCMSKeyChecksum(bArr), bArr2);
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return "RC2";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forWrapping = z;
        this.engine = new CBCBlockCipher(new RC2Engine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f1943sr = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            this.f1943sr = CryptoServicesRegistrar.getSecureRandom();
        }
        if (!(cipherParameters instanceof ParametersWithIV)) {
            this.param = cipherParameters;
            if (this.forWrapping) {
                byte[] bArr = new byte[8];
                this.f1942iv = bArr;
                this.f1943sr.nextBytes(bArr);
                this.paramPlusIV = new ParametersWithIV(this.param, this.f1942iv);
                return;
            }
            return;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        this.paramPlusIV = parametersWithIV;
        this.f1942iv = parametersWithIV.getIV();
        this.param = this.paramPlusIV.getParameters();
        if (!this.forWrapping) {
            throw new IllegalArgumentException("You should not supply an IV for unwrapping");
        }
        byte[] bArr2 = this.f1942iv;
        if (bArr2 == null || bArr2.length != 8) {
            throw new IllegalArgumentException("IV is not 8 octets");
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int r6, int r7) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("Not set for unwrapping");
        }
        if (bArr != null) {
            if (r7 % this.engine.getBlockSize() != 0) {
                throw new InvalidCipherTextException("Ciphertext not multiple of " + this.engine.getBlockSize());
            }
            this.engine.init(false, new ParametersWithIV(this.param, IV2));
            byte[] bArr2 = new byte[r7];
            System.arraycopy(bArr, r6, bArr2, 0, r7);
            for (int r5 = 0; r5 < r7 / this.engine.getBlockSize(); r5++) {
                int blockSize = this.engine.getBlockSize() * r5;
                this.engine.processBlock(bArr2, blockSize, bArr2, blockSize);
            }
            byte[] bArr3 = new byte[r7];
            int r62 = 0;
            while (r62 < r7) {
                int r1 = r62 + 1;
                bArr3[r62] = bArr2[r7 - r1];
                r62 = r1;
            }
            byte[] bArr4 = new byte[8];
            this.f1942iv = bArr4;
            int r72 = r7 - 8;
            byte[] bArr5 = new byte[r72];
            System.arraycopy(bArr3, 0, bArr4, 0, 8);
            System.arraycopy(bArr3, 8, bArr5, 0, r72);
            ParametersWithIV parametersWithIV = new ParametersWithIV(this.param, this.f1942iv);
            this.paramPlusIV = parametersWithIV;
            this.engine.init(false, parametersWithIV);
            byte[] bArr6 = new byte[r72];
            System.arraycopy(bArr5, 0, bArr6, 0, r72);
            for (int r0 = 0; r0 < r72 / this.engine.getBlockSize(); r0++) {
                int blockSize2 = this.engine.getBlockSize() * r0;
                this.engine.processBlock(bArr6, blockSize2, bArr6, blockSize2);
            }
            int r73 = r72 - 8;
            byte[] bArr7 = new byte[r73];
            byte[] bArr8 = new byte[8];
            System.arraycopy(bArr6, 0, bArr7, 0, r73);
            System.arraycopy(bArr6, r73, bArr8, 0, 8);
            if (checkCMSKeyChecksum(bArr7, bArr8)) {
                if (r73 - ((bArr7[0] & 255) + 1) <= 7) {
                    int r52 = bArr7[0];
                    byte[] bArr9 = new byte[r52];
                    System.arraycopy(bArr7, 1, bArr9, 0, r52);
                    return bArr9;
                }
                throw new InvalidCipherTextException("too many pad bytes (" + (r73 - ((bArr7[0] & 255) + 1)) + ")");
            }
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
        }
        throw new InvalidCipherTextException("Null pointer as ciphertext");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int r8, int r9) {
        if (this.forWrapping) {
            int r0 = r9 + 1;
            int r1 = r0 % 8;
            int r12 = r1 != 0 ? (8 - r1) + r0 : r0;
            byte[] bArr2 = new byte[r12];
            bArr2[0] = (byte) r9;
            System.arraycopy(bArr, r8, bArr2, 1, r9);
            int r7 = (r12 - r9) - 1;
            byte[] bArr3 = new byte[r7];
            if (r7 > 0) {
                this.f1943sr.nextBytes(bArr3);
                System.arraycopy(bArr3, 0, bArr2, r0, r7);
            }
            byte[] calculateCMSKeyChecksum = calculateCMSKeyChecksum(bArr2);
            int length = calculateCMSKeyChecksum.length + r12;
            byte[] bArr4 = new byte[length];
            System.arraycopy(bArr2, 0, bArr4, 0, r12);
            System.arraycopy(calculateCMSKeyChecksum, 0, bArr4, r12, calculateCMSKeyChecksum.length);
            byte[] bArr5 = new byte[length];
            System.arraycopy(bArr4, 0, bArr5, 0, length);
            int blockSize = length / this.engine.getBlockSize();
            if (length % this.engine.getBlockSize() == 0) {
                this.engine.init(true, this.paramPlusIV);
                for (int r02 = 0; r02 < blockSize; r02++) {
                    int blockSize2 = this.engine.getBlockSize() * r02;
                    this.engine.processBlock(bArr5, blockSize2, bArr5, blockSize2);
                }
                byte[] bArr6 = this.f1942iv;
                int length2 = bArr6.length + length;
                byte[] bArr7 = new byte[length2];
                System.arraycopy(bArr6, 0, bArr7, 0, bArr6.length);
                System.arraycopy(bArr5, 0, bArr7, this.f1942iv.length, length);
                byte[] bArr8 = new byte[length2];
                int r82 = 0;
                while (r82 < length2) {
                    int r03 = r82 + 1;
                    bArr8[r82] = bArr7[length2 - r03];
                    r82 = r03;
                }
                this.engine.init(true, new ParametersWithIV(this.param, IV2));
                for (int r4 = 0; r4 < blockSize + 1; r4++) {
                    int blockSize3 = this.engine.getBlockSize() * r4;
                    this.engine.processBlock(bArr8, blockSize3, bArr8, blockSize3);
                }
                return bArr8;
            }
            throw new IllegalStateException("Not multiple of block length");
        }
        throw new IllegalStateException("Not initialized for wrapping");
    }
}
