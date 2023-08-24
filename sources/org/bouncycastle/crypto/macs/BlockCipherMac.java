package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;

/* loaded from: classes5.dex */
public class BlockCipherMac implements Mac {
    private byte[] buf;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] mac;
    private int macSize;

    public BlockCipherMac(BlockCipher blockCipher) {
        this(blockCipher, (blockCipher.getBlockSize() * 8) / 2);
    }

    public BlockCipherMac(BlockCipher blockCipher, int r3) {
        if (r3 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        this.cipher = new CBCBlockCipher(blockCipher);
        this.macSize = r3 / 8;
        this.mac = new byte[blockCipher.getBlockSize()];
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r6) {
        int blockSize = this.cipher.getBlockSize();
        while (true) {
            int r1 = this.bufOff;
            if (r1 >= blockSize) {
                this.cipher.processBlock(this.buf, 0, this.mac, 0);
                System.arraycopy(this.mac, 0, bArr, r6, this.macSize);
                reset();
                return this.macSize;
            }
            this.buf[r1] = 0;
            this.bufOff = r1 + 1;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        reset();
        this.cipher.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.Mac
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

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        int r0 = this.bufOff;
        byte[] bArr = this.buf;
        if (r0 == bArr.length) {
            this.cipher.processBlock(bArr, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr2 = this.buf;
        int r1 = this.bufOff;
        this.bufOff = r1 + 1;
        bArr2[r1] = b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r8, int r9) {
        if (r9 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int blockSize = this.cipher.getBlockSize();
        int r1 = this.bufOff;
        int r2 = blockSize - r1;
        if (r9 > r2) {
            System.arraycopy(bArr, r8, this.buf, r1, r2);
            this.cipher.processBlock(this.buf, 0, this.mac, 0);
            this.bufOff = 0;
            r9 -= r2;
            r8 += r2;
            while (r9 > blockSize) {
                this.cipher.processBlock(bArr, r8, this.mac, 0);
                r9 -= blockSize;
                r8 += blockSize;
            }
        }
        System.arraycopy(bArr, r8, this.buf, this.bufOff, r9);
        this.bufOff += r9;
    }
}
