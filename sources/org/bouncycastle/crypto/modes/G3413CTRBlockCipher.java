package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class G3413CTRBlockCipher extends StreamBlockCipher {
    private byte[] CTR;

    /* renamed from: IV */
    private byte[] f2051IV;
    private final int blockSize;
    private byte[] buf;
    private int byteCount;
    private final BlockCipher cipher;
    private boolean initialized;

    /* renamed from: s */
    private final int f2052s;

    public G3413CTRBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public G3413CTRBlockCipher(BlockCipher blockCipher, int r4) {
        super(blockCipher);
        this.byteCount = 0;
        if (r4 < 0 || r4 > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (blockCipher.getBlockSize() * 8));
        }
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.f2052s = r4 / 8;
        this.CTR = new byte[blockSize];
    }

    private byte[] generateBuf() {
        byte[] bArr = this.CTR;
        byte[] bArr2 = new byte[bArr.length];
        this.cipher.processBlock(bArr, 0, bArr2, 0);
        return GOST3413CipherUtil.MSB(bArr2, this.f2052s);
    }

    private void generateCRT() {
        byte[] bArr = this.CTR;
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + 1);
    }

    private void initArrays() {
        int r0 = this.blockSize;
        this.f2051IV = new byte[r0 / 2];
        this.CTR = new byte[r0];
        this.buf = new byte[this.f2052s];
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            this.buf = generateBuf();
        }
        byte[] bArr = this.buf;
        int r1 = this.byteCount;
        byte b2 = (byte) (b ^ bArr[r1]);
        int r12 = r1 + 1;
        this.byteCount = r12;
        if (r12 == this.f2052s) {
            this.byteCount = 0;
            generateCRT();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCTR";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f2052s;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            initArrays();
            if (cipherParameters != null) {
                blockCipher = this.cipher;
                blockCipher.init(true, cipherParameters);
            }
            this.initialized = true;
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        initArrays();
        byte[] clone = Arrays.clone(parametersWithIV.getIV());
        this.f2051IV = clone;
        if (clone.length != this.blockSize / 2) {
            throw new IllegalArgumentException("Parameter IV length must be == blockSize/2");
        }
        System.arraycopy(clone, 0, this.CTR, 0, clone.length);
        for (int length = this.f2051IV.length; length < this.blockSize; length++) {
            this.CTR[length] = 0;
        }
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        processBytes(bArr, r8, this.f2052s, bArr2, r10);
        return this.f2052s;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.initialized) {
            byte[] bArr = this.f2051IV;
            System.arraycopy(bArr, 0, this.CTR, 0, bArr.length);
            for (int length = this.f2051IV.length; length < this.blockSize; length++) {
                this.CTR[length] = 0;
            }
            this.byteCount = 0;
            this.cipher.reset();
        }
    }
}
