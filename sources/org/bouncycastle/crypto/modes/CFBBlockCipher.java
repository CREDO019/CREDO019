package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class CFBBlockCipher extends StreamBlockCipher {

    /* renamed from: IV */
    private byte[] f2045IV;
    private int blockSize;
    private int byteCount;
    private byte[] cfbOutV;
    private byte[] cfbV;
    private BlockCipher cipher;
    private boolean encrypting;
    private byte[] inBuf;

    public CFBBlockCipher(BlockCipher blockCipher, int r4) {
        super(blockCipher);
        this.cipher = null;
        if (r4 > blockCipher.getBlockSize() * 8 || r4 < 8 || r4 % 8 != 0) {
            throw new IllegalArgumentException("CFB" + r4 + " not supported");
        }
        this.cipher = blockCipher;
        this.blockSize = r4 / 8;
        this.f2045IV = new byte[blockCipher.getBlockSize()];
        this.cfbV = new byte[blockCipher.getBlockSize()];
        this.cfbOutV = new byte[blockCipher.getBlockSize()];
        this.inBuf = new byte[this.blockSize];
    }

    private byte decryptByte(byte b) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        byte[] bArr = this.inBuf;
        int r2 = this.byteCount;
        bArr[r2] = b;
        byte[] bArr2 = this.cfbOutV;
        int r3 = r2 + 1;
        this.byteCount = r3;
        byte b2 = (byte) (b ^ bArr2[r2]);
        int r0 = this.blockSize;
        if (r3 == r0) {
            this.byteCount = 0;
            byte[] bArr3 = this.cfbV;
            System.arraycopy(bArr3, r0, bArr3, 0, bArr3.length - r0);
            byte[] bArr4 = this.inBuf;
            byte[] bArr5 = this.cfbV;
            int length = bArr5.length;
            int r4 = this.blockSize;
            System.arraycopy(bArr4, 0, bArr5, length - r4, r4);
        }
        return b2;
    }

    private byte encryptByte(byte b) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        byte[] bArr = this.cfbOutV;
        int r2 = this.byteCount;
        byte b2 = (byte) (b ^ bArr[r2]);
        byte[] bArr2 = this.inBuf;
        int r3 = r2 + 1;
        this.byteCount = r3;
        bArr2[r2] = b2;
        int r0 = this.blockSize;
        if (r3 == r0) {
            this.byteCount = 0;
            byte[] bArr3 = this.cfbV;
            System.arraycopy(bArr3, r0, bArr3, 0, bArr3.length - r0);
            byte[] bArr4 = this.inBuf;
            byte[] bArr5 = this.cfbV;
            int length = bArr5.length;
            int r4 = this.blockSize;
            System.arraycopy(bArr4, 0, bArr5, length - r4, r4);
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        return this.encrypting ? encryptByte(b) : decryptByte(b);
    }

    public int decryptBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        processBytes(bArr, r8, this.blockSize, bArr2, r10);
        return this.blockSize;
    }

    public int encryptBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        processBytes(bArr, r8, this.blockSize, bArr2, r10);
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CFB" + (this.blockSize * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blockSize;
    }

    public byte[] getCurrentIV() {
        return Arrays.clone(this.cfbV);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.encrypting = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] bArr = parametersWithIV.getIV();
            int length = bArr.length;
            byte[] bArr2 = this.f2045IV;
            if (length < bArr2.length) {
                System.arraycopy(bArr, 0, bArr2, bArr2.length - bArr.length, bArr.length);
                int r1 = 0;
                while (true) {
                    byte[] bArr3 = this.f2045IV;
                    if (r1 >= bArr3.length - bArr.length) {
                        break;
                    }
                    bArr3[r1] = 0;
                    r1++;
                }
            } else {
                System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            }
            reset();
            if (parametersWithIV.getParameters() == null) {
                return;
            }
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            reset();
            if (cipherParameters == null) {
                return;
            }
            blockCipher = this.cipher;
        }
        blockCipher.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        processBytes(bArr, r8, this.blockSize, bArr2, r10);
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.f2045IV;
        System.arraycopy(bArr, 0, this.cfbV, 0, bArr.length);
        Arrays.fill(this.inBuf, (byte) 0);
        this.byteCount = 0;
        this.cipher.reset();
    }
}
