package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class KXTSBlockCipher extends BufferedBlockCipher {
    private static final long RED_POLY_128 = 135;
    private static final long RED_POLY_256 = 1061;
    private static final long RED_POLY_512 = 293;
    private final int blockSize;
    private int counter;
    private final long reductionPolynomial;
    private final long[] tw_current;
    private final long[] tw_init;

    public KXTSBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.reductionPolynomial = getReductionPolynomial(blockSize);
        this.tw_init = new long[blockSize >>> 3];
        this.tw_current = new long[blockSize >>> 3];
        this.counter = -1;
    }

    private static void GF_double(long j, long[] jArr) {
        long j2 = 0;
        int r3 = 0;
        while (r3 < jArr.length) {
            long j3 = jArr[r3];
            jArr[r3] = j2 ^ (j3 << 1);
            r3++;
            j2 = j3 >>> 63;
        }
        jArr[0] = (j & (-j2)) ^ jArr[0];
    }

    protected static long getReductionPolynomial(int r2) {
        if (r2 != 16) {
            if (r2 != 32) {
                if (r2 == 64) {
                    return RED_POLY_512;
                }
                throw new IllegalArgumentException("Only 128, 256, and 512 -bit block sizes supported");
            }
            return RED_POLY_256;
        }
        return RED_POLY_128;
    }

    private void processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) {
        int r0 = this.counter;
        if (r0 == -1) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.counter = r0 + 1;
        GF_double(this.reductionPolynomial, this.tw_current);
        byte[] bArr3 = new byte[this.blockSize];
        Pack.longToLittleEndian(this.tw_current, bArr3, 0);
        int r1 = this.blockSize;
        byte[] bArr4 = new byte[r1];
        System.arraycopy(bArr3, 0, bArr4, 0, r1);
        for (int r12 = 0; r12 < this.blockSize; r12++) {
            bArr4[r12] = (byte) (bArr4[r12] ^ bArr[r8 + r12]);
        }
        this.cipher.processBlock(bArr4, 0, bArr4, 0);
        for (int r2 = 0; r2 < this.blockSize; r2++) {
            bArr2[r10 + r2] = (byte) (bArr4[r2] ^ bArr3[r2]);
        }
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int r2) {
        reset();
        return 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int r1) {
        return r1;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int r1) {
        return r1;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Invalid parameters passed");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        CipherParameters parameters = parametersWithIV.getParameters();
        byte[] bArr = parametersWithIV.getIV();
        int length = bArr.length;
        int r2 = this.blockSize;
        if (length != r2) {
            throw new IllegalArgumentException("Currently only support IVs of exactly one block");
        }
        byte[] bArr2 = new byte[r2];
        System.arraycopy(bArr, 0, bArr2, 0, r2);
        this.cipher.init(true, parameters);
        this.cipher.processBlock(bArr2, 0, bArr2, 0);
        this.cipher.init(z, parameters);
        Pack.littleEndianToLong(bArr2, 0, this.tw_init);
        long[] jArr = this.tw_init;
        System.arraycopy(jArr, 0, this.tw_current, 0, jArr.length);
        this.counter = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processByte(byte b, byte[] bArr, int r3) {
        throw new IllegalStateException("unsupported operation");
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int r5, int r6, byte[] bArr2, int r8) {
        if (bArr.length - r5 >= r6) {
            if (bArr2.length - r5 >= r6) {
                if (r6 % this.blockSize == 0) {
                    int r0 = 0;
                    while (r0 < r6) {
                        processBlock(bArr, r5 + r0, bArr2, r8 + r0);
                        r0 += this.blockSize;
                    }
                    return r6;
                }
                throw new IllegalArgumentException("Partial blocks not supported");
            }
            throw new OutputLengthException("Output buffer too short");
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public void reset() {
        this.cipher.reset();
        long[] jArr = this.tw_init;
        System.arraycopy(jArr, 0, this.tw_current, 0, jArr.length);
        this.counter = 0;
    }
}
