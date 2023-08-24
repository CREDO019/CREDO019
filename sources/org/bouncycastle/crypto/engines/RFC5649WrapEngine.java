package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class RFC5649WrapEngine implements Wrapper {
    private BlockCipher engine;
    private byte[] extractedAIV = null;
    private boolean forWrapping;
    private byte[] highOrderIV;
    private KeyParameter param;
    private byte[] preIV;

    public RFC5649WrapEngine(BlockCipher blockCipher) {
        byte[] bArr = {-90, 89, 89, -90};
        this.highOrderIV = bArr;
        this.preIV = bArr;
        this.engine = blockCipher;
    }

    private byte[] padPlaintext(byte[] bArr) {
        int length = bArr.length;
        int r1 = (8 - (length % 8)) % 8;
        byte[] bArr2 = new byte[length + r1];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        if (r1 != 0) {
            System.arraycopy(new byte[r1], 0, bArr2, length, r1);
        }
        return bArr2;
    }

    private byte[] rfc3394UnwrapNoIvCheck(byte[] bArr, int r14, int r15) {
        int r0 = r15 - 8;
        byte[] bArr2 = new byte[r0];
        byte[] bArr3 = new byte[8];
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, r14, bArr3, 0, 8);
        System.arraycopy(bArr, r14 + 8, bArr2, 0, r0);
        this.engine.init(false, this.param);
        int r152 = (r15 / 8) - 1;
        for (int r142 = 5; r142 >= 0; r142--) {
            for (int r02 = r152; r02 >= 1; r02--) {
                System.arraycopy(bArr3, 0, bArr4, 0, 8);
                int r6 = (r02 - 1) * 8;
                System.arraycopy(bArr2, r6, bArr4, 8, 8);
                int r7 = (r152 * r142) + r02;
                int r8 = 1;
                while (r7 != 0) {
                    int r10 = 8 - r8;
                    bArr4[r10] = (byte) (((byte) r7) ^ bArr4[r10]);
                    r7 >>>= 8;
                    r8++;
                }
                this.engine.processBlock(bArr4, 0, bArr4, 0);
                System.arraycopy(bArr4, 0, bArr3, 0, 8);
                System.arraycopy(bArr4, 8, bArr2, r6, 8);
            }
        }
        this.extractedAIV = bArr3;
        return bArr2;
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
            this.preIV = this.highOrderIV;
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.preIV = parametersWithIV.getIV();
            this.param = (KeyParameter) parametersWithIV.getParameters();
            if (this.preIV.length != 4) {
                throw new IllegalArgumentException("IV length not equal to 4");
            }
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int r8, int r9) throws InvalidCipherTextException {
        byte[] rfc3394UnwrapNoIvCheck;
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int r0 = r9 / 8;
        if (r0 * 8 == r9) {
            if (r0 > 1) {
                byte[] bArr2 = new byte[r9];
                System.arraycopy(bArr, r8, bArr2, 0, r9);
                byte[] bArr3 = new byte[r9];
                if (r0 == 2) {
                    this.engine.init(false, this.param);
                    int r7 = 0;
                    while (r7 < r9) {
                        this.engine.processBlock(bArr2, r7, bArr3, r7);
                        r7 += this.engine.getBlockSize();
                    }
                    byte[] bArr4 = new byte[8];
                    this.extractedAIV = bArr4;
                    System.arraycopy(bArr3, 0, bArr4, 0, bArr4.length);
                    byte[] bArr5 = this.extractedAIV;
                    int length = r9 - bArr5.length;
                    rfc3394UnwrapNoIvCheck = new byte[length];
                    System.arraycopy(bArr3, bArr5.length, rfc3394UnwrapNoIvCheck, 0, length);
                } else {
                    rfc3394UnwrapNoIvCheck = rfc3394UnwrapNoIvCheck(bArr, r8, r9);
                }
                int r72 = 4;
                byte[] bArr6 = new byte[4];
                byte[] bArr7 = new byte[4];
                System.arraycopy(this.extractedAIV, 0, bArr6, 0, 4);
                System.arraycopy(this.extractedAIV, 4, bArr7, 0, 4);
                int bigEndianToInt = Pack.bigEndianToInt(bArr7, 0);
                boolean constantTimeAreEqual = Arrays.constantTimeAreEqual(bArr6, this.preIV);
                int length2 = rfc3394UnwrapNoIvCheck.length;
                if (bigEndianToInt <= length2 - 8) {
                    constantTimeAreEqual = false;
                }
                if (bigEndianToInt > length2) {
                    constantTimeAreEqual = false;
                }
                int r1 = length2 - bigEndianToInt;
                if (r1 >= 8 || r1 < 0) {
                    constantTimeAreEqual = false;
                } else {
                    r72 = r1;
                }
                byte[] bArr8 = new byte[r72];
                System.arraycopy(rfc3394UnwrapNoIvCheck, rfc3394UnwrapNoIvCheck.length - r72, bArr8, 0, r72);
                if (!Arrays.constantTimeAreEqual(bArr8, new byte[r72])) {
                    constantTimeAreEqual = false;
                }
                if (constantTimeAreEqual) {
                    byte[] bArr9 = new byte[bigEndianToInt];
                    System.arraycopy(rfc3394UnwrapNoIvCheck, 0, bArr9, 0, bigEndianToInt);
                    return bArr9;
                }
                throw new InvalidCipherTextException("checksum failed");
            }
            throw new InvalidCipherTextException("unwrap data must be at least 16 bytes");
        }
        throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int r8, int r9) {
        if (this.forWrapping) {
            byte[] bArr2 = new byte[8];
            byte[] intToBigEndian = Pack.intToBigEndian(r9);
            byte[] bArr3 = this.preIV;
            int r5 = 0;
            System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
            System.arraycopy(intToBigEndian, 0, bArr2, this.preIV.length, intToBigEndian.length);
            byte[] bArr4 = new byte[r9];
            System.arraycopy(bArr, r8, bArr4, 0, r9);
            byte[] padPlaintext = padPlaintext(bArr4);
            if (padPlaintext.length != 8) {
                RFC3394WrapEngine rFC3394WrapEngine = new RFC3394WrapEngine(this.engine);
                rFC3394WrapEngine.init(true, new ParametersWithIV(this.param, bArr2));
                return rFC3394WrapEngine.wrap(padPlaintext, 0, padPlaintext.length);
            }
            int length = padPlaintext.length + 8;
            byte[] bArr5 = new byte[length];
            System.arraycopy(bArr2, 0, bArr5, 0, 8);
            System.arraycopy(padPlaintext, 0, bArr5, 8, padPlaintext.length);
            this.engine.init(true, this.param);
            while (r5 < length) {
                this.engine.processBlock(bArr5, r5, bArr5, r5);
                r5 += this.engine.getBlockSize();
            }
            return bArr5;
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
