package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public class BufferedAsymmetricBlockCipher {
    protected byte[] buf;
    protected int bufOff;
    private final AsymmetricBlockCipher cipher;

    public BufferedAsymmetricBlockCipher(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.cipher = asymmetricBlockCipher;
    }

    public byte[] doFinal() throws InvalidCipherTextException {
        byte[] processBlock = this.cipher.processBlock(this.buf, 0, this.bufOff);
        reset();
        return processBlock;
    }

    public int getBufferPosition() {
        return this.bufOff;
    }

    public int getInputBlockSize() {
        return this.cipher.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.cipher.getOutputBlockSize();
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        reset();
        this.cipher.init(z, cipherParameters);
        this.buf = new byte[this.cipher.getInputBlockSize() + (z ? 1 : 0)];
        this.bufOff = 0;
    }

    public void processByte(byte b) {
        int r0 = this.bufOff;
        byte[] bArr = this.buf;
        if (r0 >= bArr.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        this.bufOff = r0 + 1;
        bArr[r0] = b;
    }

    public void processBytes(byte[] bArr, int r6, int r7) {
        if (r7 == 0) {
            return;
        }
        if (r7 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int r0 = this.bufOff;
        int r1 = r0 + r7;
        byte[] bArr2 = this.buf;
        if (r1 > bArr2.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        System.arraycopy(bArr, r6, bArr2, r0, r7);
        this.bufOff += r7;
    }

    public void reset() {
        if (this.buf != null) {
            int r0 = 0;
            while (true) {
                byte[] bArr = this.buf;
                if (r0 >= bArr.length) {
                    break;
                }
                bArr[r0] = 0;
                r0++;
            }
        }
        this.bufOff = 0;
    }
}
