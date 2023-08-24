package org.bouncycastle.crypto.engines;

import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class RFC3211WrapEngine implements Wrapper {
    private CBCBlockCipher engine;
    private boolean forWrapping;
    private ParametersWithIV param;
    private SecureRandom rand;

    public RFC3211WrapEngine(BlockCipher blockCipher) {
        this.engine = new CBCBlockCipher(blockCipher);
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.engine.getUnderlyingCipher().getAlgorithmName() + "/RFC3211Wrap";
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forWrapping = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.rand = parametersWithRandom.getRandom();
            if (!(parametersWithRandom.getParameters() instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("RFC3211Wrap requires an IV");
            }
            this.param = (ParametersWithIV) parametersWithRandom.getParameters();
            return;
        }
        if (z) {
            this.rand = CryptoServicesRegistrar.getSecureRandom();
        }
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("RFC3211Wrap requires an IV");
        }
        this.param = (ParametersWithIV) cipherParameters;
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int r8, int r9) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int blockSize = this.engine.getBlockSize();
        if (r9 >= blockSize * 2) {
            byte[] bArr2 = new byte[r9];
            byte[] bArr3 = new byte[blockSize];
            System.arraycopy(bArr, r8, bArr2, 0, r9);
            System.arraycopy(bArr, r8, bArr3, 0, blockSize);
            this.engine.init(false, new ParametersWithIV(this.param.getParameters(), bArr3));
            for (int r7 = blockSize; r7 < r9; r7 += blockSize) {
                this.engine.processBlock(bArr2, r7, bArr2, r7);
            }
            System.arraycopy(bArr2, r9 - blockSize, bArr3, 0, blockSize);
            this.engine.init(false, new ParametersWithIV(this.param.getParameters(), bArr3));
            this.engine.processBlock(bArr2, 0, bArr2, 0);
            this.engine.init(false, this.param);
            for (int r72 = 0; r72 < r9; r72 += blockSize) {
                this.engine.processBlock(bArr2, r72, bArr2, r72);
            }
            int r92 = r9 - 4;
            boolean z = (bArr2[0] & 255) > r92;
            if (!z) {
                r92 = bArr2[0] & 255;
            }
            byte[] bArr4 = new byte[r92];
            System.arraycopy(bArr2, 4, bArr4, 0, bArr4.length);
            int r82 = 0;
            int r2 = 0;
            while (r82 != 3) {
                int r4 = r82 + 1;
                r2 |= bArr2[r82 + 4] ^ ((byte) (~bArr2[r4]));
                r82 = r4;
            }
            Arrays.clear(bArr2);
            if (!z && !(r2 != 0)) {
                return bArr4;
            }
            throw new InvalidCipherTextException("wrapped key corrupted");
        }
        throw new InvalidCipherTextException("input too short");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int r8, int r9) {
        if (this.forWrapping) {
            if (r9 > 255 || r9 < 0) {
                throw new IllegalArgumentException("input must be from 0 to 255 bytes");
            }
            this.engine.init(true, this.param);
            int blockSize = this.engine.getBlockSize();
            int r1 = r9 + 4;
            int r3 = blockSize * 2;
            if (r1 >= r3) {
                r3 = r1 % blockSize == 0 ? r1 : ((r1 / blockSize) + 1) * blockSize;
            }
            byte[] bArr2 = new byte[r3];
            bArr2[0] = (byte) r9;
            System.arraycopy(bArr, r8, bArr2, 4, r9);
            int length = bArr2.length - r1;
            byte[] bArr3 = new byte[length];
            this.rand.nextBytes(bArr3);
            System.arraycopy(bArr3, 0, bArr2, r1, length);
            bArr2[1] = (byte) (~bArr2[4]);
            bArr2[2] = (byte) (~bArr2[5]);
            bArr2[3] = (byte) (~bArr2[6]);
            for (int r7 = 0; r7 < bArr2.length; r7 += blockSize) {
                this.engine.processBlock(bArr2, r7, bArr2, r7);
            }
            for (int r5 = 0; r5 < bArr2.length; r5 += blockSize) {
                this.engine.processBlock(bArr2, r5, bArr2, r5);
            }
            return bArr2;
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
