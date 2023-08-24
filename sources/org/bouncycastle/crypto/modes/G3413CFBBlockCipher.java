package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class G3413CFBBlockCipher extends StreamBlockCipher {

    /* renamed from: R */
    private byte[] f2048R;
    private byte[] R_init;
    private int blockSize;
    private int byteCount;
    private BlockCipher cipher;
    private boolean forEncryption;
    private byte[] gamma;
    private byte[] inBuf;
    private boolean initialized;

    /* renamed from: m */
    private int f2049m;

    /* renamed from: s */
    private final int f2050s;

    public G3413CFBBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public G3413CFBBlockCipher(BlockCipher blockCipher, int r4) {
        super(blockCipher);
        this.initialized = false;
        if (r4 < 0 || r4 > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("Parameter bitBlockSize must be in range 0 < bitBlockSize <= " + (blockCipher.getBlockSize() * 8));
        }
        this.blockSize = blockCipher.getBlockSize();
        this.cipher = blockCipher;
        this.f2050s = r4 / 8;
        this.inBuf = new byte[getBlockSize()];
    }

    private void initArrays() {
        int r0 = this.f2049m;
        this.f2048R = new byte[r0];
        this.R_init = new byte[r0];
    }

    private void setupDefaultParams() {
        this.f2049m = this.blockSize * 2;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            this.gamma = createGamma();
        }
        byte[] bArr = this.gamma;
        int r1 = this.byteCount;
        byte b2 = (byte) (bArr[r1] ^ b);
        byte[] bArr2 = this.inBuf;
        int r3 = r1 + 1;
        this.byteCount = r3;
        if (this.forEncryption) {
            b = b2;
        }
        bArr2[r1] = b;
        if (r3 == getBlockSize()) {
            this.byteCount = 0;
            generateR(this.inBuf);
        }
        return b2;
    }

    byte[] createGamma() {
        byte[] MSB = GOST3413CipherUtil.MSB(this.f2048R, this.blockSize);
        byte[] bArr = new byte[MSB.length];
        this.cipher.processBlock(MSB, 0, bArr, 0);
        return GOST3413CipherUtil.MSB(bArr, this.f2050s);
    }

    void generateR(byte[] bArr) {
        byte[] LSB = GOST3413CipherUtil.LSB(this.f2048R, this.f2049m - this.f2050s);
        System.arraycopy(LSB, 0, this.f2048R, 0, LSB.length);
        System.arraycopy(bArr, 0, this.f2048R, LSB.length, this.f2049m - LSB.length);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CFB" + (this.blockSize * 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.f2050s;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.forEncryption = z;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            setupDefaultParams();
            initArrays();
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f2048R, 0, bArr.length);
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
        this.f2049m = bArr2.length;
        initArrays();
        byte[] clone = Arrays.clone(bArr2);
        this.R_init = clone;
        System.arraycopy(clone, 0, this.f2048R, 0, clone.length);
        if (parametersWithIV.getParameters() != null) {
            blockCipher = this.cipher;
            cipherParameters = parametersWithIV.getParameters();
            blockCipher.init(true, cipherParameters);
        }
        this.initialized = true;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) throws DataLengthException, IllegalStateException {
        processBytes(bArr, r8, getBlockSize(), bArr2, r10);
        return getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.byteCount = 0;
        Arrays.clear(this.inBuf);
        Arrays.clear(this.gamma);
        if (this.initialized) {
            byte[] bArr = this.R_init;
            System.arraycopy(bArr, 0, this.f2048R, 0, bArr.length);
            this.cipher.reset();
        }
    }
}
