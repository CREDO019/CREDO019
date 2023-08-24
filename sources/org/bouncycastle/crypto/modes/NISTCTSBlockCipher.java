package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* loaded from: classes5.dex */
public class NISTCTSBlockCipher extends BufferedBlockCipher {
    public static final int CS1 = 1;
    public static final int CS2 = 2;
    public static final int CS3 = 3;
    private final int blockSize;
    private final int type;

    public NISTCTSBlockCipher(int r1, BlockCipher blockCipher) {
        this.type = r1;
        this.cipher = new CBCBlockCipher(blockCipher);
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
                if (this.bufOff < blockSize) {
                    throw new DataLengthException("need at least one block of input for NISTCTS");
                }
                if (this.bufOff > blockSize) {
                    byte[] bArr3 = new byte[blockSize];
                    int r7 = this.type;
                    if (r7 == 2 || r7 == 3) {
                        this.cipher.processBlock(this.buf, 0, bArr2, 0);
                        System.arraycopy(this.buf, blockSize, bArr3, 0, r1);
                        this.cipher.processBlock(bArr3, 0, bArr3, 0);
                        if (this.type == 2 && r1 == blockSize) {
                            System.arraycopy(bArr2, 0, bArr, r11, blockSize);
                            System.arraycopy(bArr3, 0, bArr, r11 + blockSize, r1);
                        } else {
                            System.arraycopy(bArr3, 0, bArr, r11, blockSize);
                            System.arraycopy(bArr2, 0, bArr, r11 + blockSize, r1);
                        }
                    } else {
                        System.arraycopy(this.buf, 0, bArr2, 0, blockSize);
                        this.cipher.processBlock(bArr2, 0, bArr2, 0);
                        System.arraycopy(bArr2, 0, bArr, r11, r1);
                        System.arraycopy(this.buf, this.bufOff - r1, bArr3, 0, r1);
                        this.cipher.processBlock(bArr3, 0, bArr3, 0);
                        System.arraycopy(bArr3, 0, bArr, r11 + r1, blockSize);
                    }
                } else {
                    this.cipher.processBlock(this.buf, 0, bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, r11, blockSize);
                }
            } else if (this.bufOff < blockSize) {
                throw new DataLengthException("need at least one block of input for CTS");
            } else {
                byte[] bArr4 = new byte[blockSize];
                if (this.bufOff > blockSize) {
                    int r72 = this.type;
                    if (r72 == 3 || (r72 == 2 && (this.buf.length - this.bufOff) % blockSize != 0)) {
                        if (this.cipher instanceof CBCBlockCipher) {
                            ((CBCBlockCipher) this.cipher).getUnderlyingCipher().processBlock(this.buf, 0, bArr2, 0);
                        } else {
                            this.cipher.processBlock(this.buf, 0, bArr2, 0);
                        }
                        for (int r4 = blockSize; r4 != this.bufOff; r4++) {
                            int r5 = r4 - blockSize;
                            bArr4[r5] = (byte) (bArr2[r5] ^ this.buf[r4]);
                        }
                        System.arraycopy(this.buf, blockSize, bArr2, 0, r1);
                        this.cipher.processBlock(bArr2, 0, bArr, r11);
                    } else {
                        ((CBCBlockCipher) this.cipher).getUnderlyingCipher().processBlock(this.buf, this.bufOff - blockSize, bArr4, 0);
                        System.arraycopy(this.buf, 0, bArr2, 0, blockSize);
                        if (r1 != blockSize) {
                            System.arraycopy(bArr4, r1, bArr2, r1, blockSize - r1);
                        }
                        this.cipher.processBlock(bArr2, 0, bArr2, 0);
                        System.arraycopy(bArr2, 0, bArr, r11, blockSize);
                        for (int r2 = 0; r2 != r1; r2++) {
                            bArr4[r2] = (byte) (bArr4[r2] ^ this.buf[r2]);
                        }
                    }
                    System.arraycopy(bArr4, 0, bArr, r11 + blockSize, r1);
                } else {
                    this.cipher.processBlock(this.buf, 0, bArr2, 0);
                    System.arraycopy(bArr2, 0, bArr, r11, blockSize);
                }
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
