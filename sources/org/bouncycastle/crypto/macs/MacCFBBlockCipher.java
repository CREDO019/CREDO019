package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
class MacCFBBlockCipher {

    /* renamed from: IV */
    private byte[] f2007IV;
    private int blockSize;
    private byte[] cfbOutV;
    private byte[] cfbV;
    private BlockCipher cipher;

    public MacCFBBlockCipher(BlockCipher blockCipher, int r2) {
        this.cipher = blockCipher;
        this.blockSize = r2 / 8;
        this.f2007IV = new byte[blockCipher.getBlockSize()];
        this.cfbV = new byte[blockCipher.getBlockSize()];
        this.cfbOutV = new byte[blockCipher.getBlockSize()];
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CFB" + (this.blockSize * 8);
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getMacBlock(byte[] bArr) {
        this.cipher.processBlock(this.cfbV, 0, bArr, 0);
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] bArr = parametersWithIV.getIV();
            int length = bArr.length;
            byte[] bArr2 = this.f2007IV;
            if (length < bArr2.length) {
                System.arraycopy(bArr, 0, bArr2, bArr2.length - bArr.length, bArr.length);
            } else {
                System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            }
            reset();
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            reset();
            blockCipher = this.cipher;
        }
        blockCipher.init(true, cipherParameters);
    }

    public int processBlock(byte[] bArr, int r7, byte[] bArr2, int r9) throws DataLengthException, IllegalStateException {
        int r0 = this.blockSize;
        if (r7 + r0 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (r0 + r9 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        int r02 = 0;
        while (true) {
            int r1 = this.blockSize;
            if (r02 >= r1) {
                byte[] bArr3 = this.cfbV;
                System.arraycopy(bArr3, r1, bArr3, 0, bArr3.length - r1);
                byte[] bArr4 = this.cfbV;
                int length = bArr4.length;
                int r03 = this.blockSize;
                System.arraycopy(bArr2, r9, bArr4, length - r03, r03);
                return this.blockSize;
            }
            bArr2[r9 + r02] = (byte) (this.cfbOutV[r02] ^ bArr[r7 + r02]);
            r02++;
        }
    }

    public void reset() {
        byte[] bArr = this.f2007IV;
        System.arraycopy(bArr, 0, this.cfbV, 0, bArr.length);
        this.cipher.reset();
    }
}
