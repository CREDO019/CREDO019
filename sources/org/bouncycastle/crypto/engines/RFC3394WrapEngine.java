package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class RFC3394WrapEngine implements Wrapper {
    private BlockCipher engine;
    private boolean forWrapping;

    /* renamed from: iv */
    private byte[] f1949iv;
    private KeyParameter param;
    private boolean wrapCipherMode;

    public RFC3394WrapEngine(BlockCipher blockCipher) {
        this(blockCipher, false);
    }

    public RFC3394WrapEngine(BlockCipher blockCipher, boolean z) {
        this.f1949iv = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};
        this.engine = blockCipher;
        this.wrapCipherMode = !z;
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forWrapping = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.param = (KeyParameter) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f1949iv = parametersWithIV.getIV();
            this.param = (KeyParameter) parametersWithIV.getParameters();
            if (this.f1949iv.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int r14, int r15) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int r0 = r15 / 8;
        if (r0 * 8 == r15) {
            byte[] bArr2 = this.f1949iv;
            byte[] bArr3 = new byte[r15 - bArr2.length];
            byte[] bArr4 = new byte[bArr2.length];
            byte[] bArr5 = new byte[bArr2.length + 8];
            System.arraycopy(bArr, r14, bArr4, 0, bArr2.length);
            byte[] bArr6 = this.f1949iv;
            System.arraycopy(bArr, r14 + bArr6.length, bArr3, 0, r15 - bArr6.length);
            this.engine.init(!this.wrapCipherMode, this.param);
            int r02 = r0 - 1;
            for (int r13 = 5; r13 >= 0; r13--) {
                for (int r142 = r02; r142 >= 1; r142--) {
                    System.arraycopy(bArr4, 0, bArr5, 0, this.f1949iv.length);
                    int r1 = (r142 - 1) * 8;
                    System.arraycopy(bArr3, r1, bArr5, this.f1949iv.length, 8);
                    int r7 = (r02 * r13) + r142;
                    int r8 = 1;
                    while (r7 != 0) {
                        int length = this.f1949iv.length - r8;
                        bArr5[length] = (byte) (((byte) r7) ^ bArr5[length]);
                        r7 >>>= 8;
                        r8++;
                    }
                    this.engine.processBlock(bArr5, 0, bArr5, 0);
                    System.arraycopy(bArr5, 0, bArr4, 0, 8);
                    System.arraycopy(bArr5, 8, bArr3, r1, 8);
                }
            }
            if (Arrays.constantTimeAreEqual(bArr4, this.f1949iv)) {
                return bArr3;
            }
            throw new InvalidCipherTextException("checksum failed");
        }
        throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int r13, int r14) {
        if (this.forWrapping) {
            int r0 = r14 / 8;
            if (r0 * 8 == r14) {
                byte[] bArr2 = this.f1949iv;
                byte[] bArr3 = new byte[bArr2.length + r14];
                byte[] bArr4 = new byte[bArr2.length + 8];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                System.arraycopy(bArr, r13, bArr3, this.f1949iv.length, r14);
                this.engine.init(this.wrapCipherMode, this.param);
                for (int r12 = 0; r12 != 6; r12++) {
                    for (int r142 = 1; r142 <= r0; r142++) {
                        System.arraycopy(bArr3, 0, bArr4, 0, this.f1949iv.length);
                        int r1 = r142 * 8;
                        System.arraycopy(bArr3, r1, bArr4, this.f1949iv.length, 8);
                        this.engine.processBlock(bArr4, 0, bArr4, 0);
                        int r5 = (r0 * r12) + r142;
                        int r7 = 1;
                        while (r5 != 0) {
                            int length = this.f1949iv.length - r7;
                            bArr4[length] = (byte) (((byte) r5) ^ bArr4[length]);
                            r5 >>>= 8;
                            r7++;
                        }
                        System.arraycopy(bArr4, 0, bArr3, 0, 8);
                        System.arraycopy(bArr4, 8, bArr3, r1, 8);
                    }
                }
                return bArr3;
            }
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
