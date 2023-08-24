package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class G3413OFBBlockCipher extends StreamBlockCipher {

    /* renamed from: R */
    private byte[] f2053R;
    private byte[] R_init;

    /* renamed from: Y */
    private byte[] f2054Y;
    private int blockSize;
    private int byteCount;
    private BlockCipher cipher;
    private boolean initialized;

    /* renamed from: m */
    private int f2055m;

    public G3413OFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.initialized = false;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.cipher = blockCipher;
        this.f2054Y = new byte[blockSize];
    }

    private void generateR() {
        byte[] LSB = GOST3413CipherUtil.LSB(this.f2053R, this.f2055m - this.blockSize);
        System.arraycopy(LSB, 0, this.f2053R, 0, LSB.length);
        System.arraycopy(this.f2054Y, 0, this.f2053R, LSB.length, this.f2055m - LSB.length);
    }

    private void generateY() {
        this.cipher.processBlock(GOST3413CipherUtil.MSB(this.f2053R, this.blockSize), 0, this.f2054Y, 0);
    }

    private void initArrays() {
        int r0 = this.f2055m;
        this.f2053R = new byte[r0];
        this.R_init = new byte[r0];
    }

    private void setupDefaultParams() {
        this.f2055m = this.blockSize * 2;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            generateY();
        }
        byte[] bArr = this.f2054Y;
        int r1 = this.byteCount;
        byte b2 = (byte) (b ^ bArr[r1]);
        int r12 = r1 + 1;
        this.byteCount = r12;
        if (r12 == getBlockSize()) {
            this.byteCount = 0;
            generateR();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OFB";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            setupDefaultParams();
            initArrays();
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f2053R, 0, bArr.length);
            if (cipherParameters != null) {
                blockCipher = this.cipher;
                blockCipher.init(true, cipherParameters);
            }
            this.initialized = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] bArr2 = parametersWithIV.getIV();
        if (bArr2.length < this.blockSize) {
            throw new IllegalArgumentException("Parameter m must blockSize <= m");
        }
        this.f2055m = bArr2.length;
        initArrays();
        byte[] clone = Arrays.clone(bArr2);
        this.R_init = clone;
        System.arraycopy(clone, 0, this.f2053R, 0, clone.length);
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        processBytes(bArr, r8, this.blockSize, bArr2, r10);
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.initialized) {
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f2053R, 0, bArr.length);
            Arrays.clear(this.f2054Y);
            this.byteCount = 0;
            this.cipher.reset();
        }
    }
}
