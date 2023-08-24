package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.DSTU7624Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class DSTU7624Mac implements Mac {
    private static final int BITS_IN_BYTE = 8;
    private int blockSize;
    private byte[] buf;
    private int bufOff;

    /* renamed from: c */
    private byte[] f2005c;
    private byte[] cTemp;
    private DSTU7624Engine engine;
    private boolean initCalled = false;
    private byte[] kDelta;
    private int macSize;

    public DSTU7624Mac(int r2, int r3) {
        this.engine = new DSTU7624Engine(r2);
        int r22 = r2 / 8;
        this.blockSize = r22;
        this.macSize = r3 / 8;
        this.f2005c = new byte[r22];
        this.kDelta = new byte[r22];
        this.cTemp = new byte[r22];
        this.buf = new byte[r22];
    }

    private void processBlock(byte[] bArr, int r8) {
        xor(this.f2005c, 0, bArr, r8, this.cTemp);
        this.engine.processBlock(this.cTemp, 0, this.f2005c, 0);
    }

    private void xor(byte[] bArr, int r5, byte[] bArr2, int r7, byte[] bArr3) {
        int length = bArr.length - r5;
        int r1 = this.blockSize;
        if (length < r1 || bArr2.length - r7 < r1 || bArr3.length < r1) {
            throw new IllegalArgumentException("some of input buffers too short");
        }
        for (int r0 = 0; r0 < this.blockSize; r0++) {
            bArr3[r0] = (byte) (bArr[r0 + r5] ^ bArr2[r0 + r7]);
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r15) throws DataLengthException, IllegalStateException {
        int r0 = this.bufOff;
        byte[] bArr2 = this.buf;
        if (r0 % bArr2.length == 0) {
            xor(this.f2005c, 0, bArr2, 0, this.cTemp);
            xor(this.cTemp, 0, this.kDelta, 0, this.f2005c);
            DSTU7624Engine dSTU7624Engine = this.engine;
            byte[] bArr3 = this.f2005c;
            dSTU7624Engine.processBlock(bArr3, 0, bArr3, 0);
            int r02 = this.macSize;
            if (r02 + r15 <= bArr.length) {
                System.arraycopy(this.f2005c, 0, bArr, r15, r02);
                reset();
                return this.macSize;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input must be a multiple of blocksize");
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "DSTU7624Mac";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Invalid parameter passed to DSTU7624Mac");
        }
        this.engine.init(true, cipherParameters);
        this.initCalled = true;
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        Arrays.fill(this.f2005c, (byte) 0);
        Arrays.fill(this.cTemp, (byte) 0);
        Arrays.fill(this.kDelta, (byte) 0);
        Arrays.fill(this.buf, (byte) 0);
        this.engine.reset();
        if (this.initCalled) {
            DSTU7624Engine dSTU7624Engine = this.engine;
            byte[] bArr = this.kDelta;
            dSTU7624Engine.processBlock(bArr, 0, bArr, 0);
        }
        this.bufOff = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        int r0 = this.bufOff;
        byte[] bArr = this.buf;
        if (r0 == bArr.length) {
            processBlock(bArr, 0);
            this.bufOff = 0;
        }
        byte[] bArr2 = this.buf;
        int r1 = this.bufOff;
        this.bufOff = r1 + 1;
        bArr2[r1] = b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r6, int r7) {
        if (r7 < 0) {
            throw new IllegalArgumentException("can't have a negative input length!");
        }
        int blockSize = this.engine.getBlockSize();
        int r1 = this.bufOff;
        int r2 = blockSize - r1;
        if (r7 > r2) {
            System.arraycopy(bArr, r6, this.buf, r1, r2);
            processBlock(this.buf, 0);
            this.bufOff = 0;
            r7 -= r2;
            r6 += r2;
            while (r7 > blockSize) {
                processBlock(bArr, r6);
                r7 -= blockSize;
                r6 += blockSize;
            }
        }
        System.arraycopy(bArr, r6, this.buf, this.bufOff, r7);
        this.bufOff += r7;
    }
}
