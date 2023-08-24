package org.bouncycastle.crypto.paddings;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithRandom;

/* loaded from: classes5.dex */
public class PaddedBufferedBlockCipher extends BufferedBlockCipher {
    BlockCipherPadding padding;

    public PaddedBufferedBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, new PKCS7Padding());
    }

    public PaddedBufferedBlockCipher(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
        this.cipher = blockCipher;
        this.padding = blockCipherPadding;
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int r7) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        int padCount;
        int r0;
        int blockSize = this.cipher.getBlockSize();
        if (this.forEncryption) {
            if (this.bufOff != blockSize) {
                r0 = 0;
            } else if ((blockSize * 2) + r7 > bArr.length) {
                reset();
                throw new OutputLengthException("output buffer too short");
            } else {
                r0 = this.cipher.processBlock(this.buf, 0, bArr, r7);
                this.bufOff = 0;
            }
            this.padding.addPadding(this.buf, this.bufOff);
            padCount = r0 + this.cipher.processBlock(this.buf, 0, bArr, r7 + r0);
        } else if (this.bufOff != blockSize) {
            reset();
            throw new DataLengthException("last block incomplete in decryption");
        } else {
            int processBlock = this.cipher.processBlock(this.buf, 0, this.buf, 0);
            this.bufOff = 0;
            try {
                padCount = processBlock - this.padding.padCount(this.buf);
                System.arraycopy(this.buf, 0, bArr, r7, padCount);
            } finally {
                reset();
            }
        }
        return padCount;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int r2) {
        int length;
        int r22 = r2 + this.bufOff;
        int length2 = r22 % this.buf.length;
        if (length2 != 0) {
            r22 -= length2;
            length = this.buf.length;
        } else if (!this.forEncryption) {
            return r22;
        } else {
            length = this.buf.length;
        }
        return r22 + length;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int r3) {
        int r32 = r3 + this.bufOff;
        int length = r32 % this.buf.length;
        return length == 0 ? Math.max(0, r32 - this.buf.length) : r32 - length;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.forEncryption = z;
        reset();
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.padding.init(parametersWithRandom.getRandom());
            blockCipher = this.cipher;
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            this.padding.init(null);
            blockCipher = this.cipher;
        }
        blockCipher.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processByte(byte b, byte[] bArr, int r6) throws DataLengthException, IllegalStateException {
        int r2 = 0;
        if (this.bufOff == this.buf.length) {
            int processBlock = this.cipher.processBlock(this.buf, 0, bArr, r6);
            this.bufOff = 0;
            r2 = processBlock;
        }
        byte[] bArr2 = this.buf;
        int r62 = this.bufOff;
        this.bufOff = r62 + 1;
        bArr2[r62] = b;
        return r2;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int r7, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        if (r8 >= 0) {
            int blockSize = getBlockSize();
            int updateOutputSize = getUpdateOutputSize(r8);
            if (updateOutputSize <= 0 || updateOutputSize + r10 <= bArr2.length) {
                int length = this.buf.length - this.bufOff;
                int r2 = 0;
                if (r8 > length) {
                    System.arraycopy(bArr, r7, this.buf, this.bufOff, length);
                    this.bufOff = 0;
                    r8 -= length;
                    r7 += length;
                    r2 = this.cipher.processBlock(this.buf, 0, bArr2, r10) + 0;
                    while (r8 > this.buf.length) {
                        r2 += this.cipher.processBlock(bArr, r7, bArr2, r10 + r2);
                        r8 -= blockSize;
                        r7 += blockSize;
                    }
                }
                System.arraycopy(bArr, r7, this.buf, this.bufOff, r8);
                this.bufOff += r8;
                return r2;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
