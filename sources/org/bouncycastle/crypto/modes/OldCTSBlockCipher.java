package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* loaded from: classes5.dex */
public class OldCTSBlockCipher extends BufferedBlockCipher {
    private int blockSize;

    public OldCTSBlockCipher(BlockCipher blockCipher) {
        if ((blockCipher instanceof OFBBlockCipher) || (blockCipher instanceof CFBBlockCipher)) {
            throw new IllegalArgumentException("CTSBlockCipher can only accept ECB, or CBC ciphers");
        }
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.buf = new byte[blockSize * 2];
        this.bufOff = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int r11) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        if (this.bufOff + r11 <= bArr.length) {
            int blockSize = this.cipher.getBlockSize();
            int r1 = this.bufOff - blockSize;
            byte[] bArr2 = new byte[blockSize];
            if (this.forEncryption) {
                this.cipher.processBlock(this.buf, 0, bArr2, 0);
                if (this.bufOff < blockSize) {
                    throw new DataLengthException("need at least one block of input for CTS");
                }
                for (int r3 = this.bufOff; r3 != this.buf.length; r3++) {
                    this.buf[r3] = bArr2[r3 - blockSize];
                }
                for (int r32 = blockSize; r32 != this.bufOff; r32++) {
                    byte[] bArr3 = this.buf;
                    bArr3[r32] = (byte) (bArr3[r32] ^ bArr2[r32 - blockSize]);
                }
                if (this.cipher instanceof CBCBlockCipher) {
                    ((CBCBlockCipher) this.cipher).getUnderlyingCipher().processBlock(this.buf, blockSize, bArr, r11);
                } else {
                    this.cipher.processBlock(this.buf, blockSize, bArr, r11);
                }
                System.arraycopy(bArr2, 0, bArr, r11 + blockSize, r1);
            } else {
                byte[] bArr4 = new byte[blockSize];
                if (this.cipher instanceof CBCBlockCipher) {
                    ((CBCBlockCipher) this.cipher).getUnderlyingCipher().processBlock(this.buf, 0, bArr2, 0);
                } else {
                    this.cipher.processBlock(this.buf, 0, bArr2, 0);
                }
                for (int r5 = blockSize; r5 != this.bufOff; r5++) {
                    int r6 = r5 - blockSize;
                    bArr4[r6] = (byte) (bArr2[r6] ^ this.buf[r5]);
                }
                System.arraycopy(this.buf, blockSize, bArr2, 0, r1);
                this.cipher.processBlock(bArr2, 0, bArr, r11);
                System.arraycopy(bArr4, 0, bArr, r11 + blockSize, r1);
            }
            int r10 = this.bufOff;
            reset();
            return r10;
        }
        throw new OutputLengthException("output buffer to small in doFinal");
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int r2) {
        return r2 + this.bufOff;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int r2) {
        int r22 = r2 + this.bufOff;
        int length = r22 % this.buf.length;
        return length == 0 ? r22 - this.buf.length : r22 - length;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processByte(byte b, byte[] bArr, int r7) throws DataLengthException, IllegalStateException {
        int r2 = 0;
        if (this.bufOff == this.buf.length) {
            int processBlock = this.cipher.processBlock(this.buf, 0, bArr, r7);
            System.arraycopy(this.buf, this.blockSize, this.buf, 0, this.blockSize);
            this.bufOff = this.blockSize;
            r2 = processBlock;
        }
        byte[] bArr2 = this.buf;
        int r72 = this.bufOff;
        this.bufOff = r72 + 1;
        bArr2[r72] = b;
        return r2;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int r8, int r9, byte[] bArr2, int r11) throws DataLengthException, IllegalStateException {
        if (r9 >= 0) {
            int blockSize = getBlockSize();
            int updateOutputSize = getUpdateOutputSize(r9);
            if (updateOutputSize <= 0 || updateOutputSize + r11 <= bArr2.length) {
                int length = this.buf.length - this.bufOff;
                int r2 = 0;
                if (r9 > length) {
                    System.arraycopy(bArr, r8, this.buf, this.bufOff, length);
                    int processBlock = this.cipher.processBlock(this.buf, 0, bArr2, r11) + 0;
                    System.arraycopy(this.buf, blockSize, this.buf, 0, blockSize);
                    this.bufOff = blockSize;
                    r9 -= length;
                    r8 += length;
                    while (r9 > blockSize) {
                        System.arraycopy(bArr, r8, this.buf, this.bufOff, blockSize);
                        processBlock += this.cipher.processBlock(this.buf, 0, bArr2, r11 + processBlock);
                        System.arraycopy(this.buf, blockSize, this.buf, 0, blockSize);
                        r9 -= blockSize;
                        r8 += blockSize;
                    }
                    r2 = processBlock;
                }
                System.arraycopy(bArr, r8, this.buf, this.bufOff, r9);
                this.bufOff += r9;
                return r2;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
