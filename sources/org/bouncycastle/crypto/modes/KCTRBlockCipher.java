package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class KCTRBlockCipher extends StreamBlockCipher {
    private int byteCount;
    private BlockCipher engine;
    private boolean initialised;

    /* renamed from: iv */
    private byte[] f2067iv;
    private byte[] ofbOutV;
    private byte[] ofbV;

    public KCTRBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.engine = blockCipher;
        this.f2067iv = new byte[blockCipher.getBlockSize()];
        this.ofbV = new byte[blockCipher.getBlockSize()];
        this.ofbOutV = new byte[blockCipher.getBlockSize()];
    }

    private void checkCounter() {
    }

    private void incrementCounterAt(int r4) {
        while (true) {
            byte[] bArr = this.ofbV;
            if (r4 >= bArr.length) {
                return;
            }
            int r1 = r4 + 1;
            byte b = (byte) (bArr[r4] + 1);
            bArr[r4] = b;
            if (b != 0) {
                return;
            }
            r4 = r1;
        }
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) {
        int r0 = this.byteCount;
        if (r0 == 0) {
            incrementCounterAt(0);
            checkCounter();
            this.engine.processBlock(this.ofbV, 0, this.ofbOutV, 0);
            byte[] bArr = this.ofbOutV;
            int r1 = this.byteCount;
            this.byteCount = r1 + 1;
            return (byte) (b ^ bArr[r1]);
        }
        byte[] bArr2 = this.ofbOutV;
        int r3 = r0 + 1;
        this.byteCount = r3;
        byte b2 = (byte) (b ^ bArr2[r0]);
        if (r3 == this.ofbV.length) {
            this.byteCount = 0;
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KCTR";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.engine.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.initialised = true;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameter passed");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] bArr = parametersWithIV.getIV();
        byte[] bArr2 = this.f2067iv;
        Arrays.fill(bArr2, (byte) 0);
        System.arraycopy(bArr, 0, this.f2067iv, bArr2.length - bArr.length, bArr.length);
        CipherParameters parameters = parametersWithIV.getParameters();
        if (parameters != null) {
            this.engine.init(true, parameters);
        }
        reset();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r10, byte[] bArr2, int r12) throws DataLengthException, IllegalStateException {
        if (bArr.length - r10 >= getBlockSize()) {
            if (bArr2.length - r12 >= getBlockSize()) {
                processBytes(bArr, r10, getBlockSize(), bArr2, r12);
                return getBlockSize();
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        if (this.initialised) {
            this.engine.processBlock(this.f2067iv, 0, this.ofbV, 0);
        }
        this.engine.reset();
        this.byteCount = 0;
    }
}
