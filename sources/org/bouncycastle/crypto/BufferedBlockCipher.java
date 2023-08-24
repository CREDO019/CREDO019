package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public class BufferedBlockCipher {
    protected byte[] buf;
    protected int bufOff;
    protected BlockCipher cipher;
    protected boolean forEncryption;
    protected boolean partialBlockOkay;
    protected boolean pgpCFB;

    /* JADX INFO: Access modifiers changed from: protected */
    public BufferedBlockCipher() {
    }

    public BufferedBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.buf = new byte[blockCipher.getBlockSize()];
        boolean z = false;
        this.bufOff = 0;
        String algorithmName = blockCipher.getAlgorithmName();
        int indexOf = algorithmName.indexOf(47) + 1;
        boolean z2 = indexOf > 0 && algorithmName.startsWith("PGP", indexOf);
        this.pgpCFB = z2;
        if (z2 || (blockCipher instanceof StreamCipher)) {
            this.partialBlockOkay = true;
            return;
        }
        if (indexOf > 0 && algorithmName.startsWith("OpenPGP", indexOf)) {
            z = true;
        }
        this.partialBlockOkay = z;
    }

    public int doFinal(byte[] bArr, int r5) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        try {
            int r0 = this.bufOff;
            if (r5 + r0 <= bArr.length) {
                int r1 = 0;
                if (r0 != 0) {
                    if (!this.partialBlockOkay) {
                        throw new DataLengthException("data not block size aligned");
                    }
                    BlockCipher blockCipher = this.cipher;
                    byte[] bArr2 = this.buf;
                    blockCipher.processBlock(bArr2, 0, bArr2, 0);
                    int r02 = this.bufOff;
                    this.bufOff = 0;
                    System.arraycopy(this.buf, 0, bArr, r5, r02);
                    r1 = r02;
                }
                return r1;
            }
            throw new OutputLengthException("output buffer too short for doFinal()");
        } finally {
            reset();
        }
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int getOutputSize(int r2) {
        return r2 + this.bufOff;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public int getUpdateOutputSize(int r3) {
        int length;
        int r0;
        int r32 = r3 + this.bufOff;
        if (!this.pgpCFB) {
            length = this.buf.length;
        } else if (this.forEncryption) {
            r0 = (r32 % this.buf.length) - (this.cipher.getBlockSize() + 2);
            return r32 - r0;
        } else {
            length = this.buf.length;
        }
        r0 = r32 % length;
        return r32 - r0;
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = z;
        reset();
        this.cipher.init(z, cipherParameters);
    }

    public int processByte(byte b, byte[] bArr, int r6) throws DataLengthException, IllegalStateException {
        byte[] bArr2 = this.buf;
        int r1 = this.bufOff;
        int r2 = r1 + 1;
        this.bufOff = r2;
        bArr2[r1] = b;
        if (r2 == bArr2.length) {
            int processBlock = this.cipher.processBlock(bArr2, 0, bArr, r6);
            this.bufOff = 0;
            return processBlock;
        }
        return 0;
    }

    public int processBytes(byte[] bArr, int r7, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        int r1;
        if (r8 >= 0) {
            int blockSize = getBlockSize();
            int updateOutputSize = getUpdateOutputSize(r8);
            if (updateOutputSize <= 0 || updateOutputSize + r10 <= bArr2.length) {
                byte[] bArr3 = this.buf;
                int length = bArr3.length;
                int r3 = this.bufOff;
                int r2 = length - r3;
                if (r8 > r2) {
                    System.arraycopy(bArr, r7, bArr3, r3, r2);
                    r1 = this.cipher.processBlock(this.buf, 0, bArr2, r10) + 0;
                    this.bufOff = 0;
                    r8 -= r2;
                    r7 += r2;
                    while (r8 > this.buf.length) {
                        r1 += this.cipher.processBlock(bArr, r7, bArr2, r10 + r1);
                        r8 -= blockSize;
                        r7 += blockSize;
                    }
                } else {
                    r1 = 0;
                }
                System.arraycopy(bArr, r7, this.buf, this.bufOff, r8);
                int r6 = this.bufOff + r8;
                this.bufOff = r6;
                byte[] bArr4 = this.buf;
                if (r6 == bArr4.length) {
                    int processBlock = r1 + this.cipher.processBlock(bArr4, 0, bArr2, r10 + r1);
                    this.bufOff = 0;
                    return processBlock;
                }
                return r1;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    public void reset() {
        int r1 = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (r1 >= bArr.length) {
                this.bufOff = 0;
                this.cipher.reset();
                return;
            }
            bArr[r1] = 0;
            r1++;
        }
    }
}
