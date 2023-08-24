package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class GeneralDigest implements ExtendedDigest, Memoable {
    private static final int BYTE_LENGTH = 64;
    private long byteCount;
    private final byte[] xBuf;
    private int xBufOff;

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneralDigest() {
        this.xBuf = new byte[4];
        this.xBufOff = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneralDigest(GeneralDigest generalDigest) {
        this.xBuf = new byte[4];
        copyIn(generalDigest);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneralDigest(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        this.xBuf = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.xBufOff = Pack.bigEndianToInt(bArr, 4);
        this.byteCount = Pack.bigEndianToLong(bArr, 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copyIn(GeneralDigest generalDigest) {
        byte[] bArr = generalDigest.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = generalDigest.xBufOff;
        this.byteCount = generalDigest.byteCount;
    }

    public void finish() {
        long j = this.byteCount << 3;
        byte b = Byte.MIN_VALUE;
        while (true) {
            update(b);
            if (this.xBufOff == 0) {
                processLength(j);
                processBlock();
                return;
            }
            b = 0;
        }
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void populateState(byte[] bArr) {
        System.arraycopy(this.xBuf, 0, bArr, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, bArr, 4);
        Pack.longToBigEndian(this.byteCount, bArr, 8);
    }

    protected abstract void processBlock();

    protected abstract void processLength(long j);

    protected abstract void processWord(byte[] bArr, int r2);

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.byteCount = 0L;
        this.xBufOff = 0;
        int r1 = 0;
        while (true) {
            byte[] bArr = this.xBuf;
            if (r1 >= bArr.length) {
                return;
            }
            bArr[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.xBuf;
        int r1 = this.xBufOff;
        int r2 = r1 + 1;
        this.xBufOff = r2;
        bArr[r1] = b;
        if (r2 == bArr.length) {
            processWord(bArr, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r8, int r9) {
        int r0 = 0;
        int max = Math.max(0, r9);
        if (this.xBufOff != 0) {
            int r1 = 0;
            while (true) {
                if (r1 >= max) {
                    r0 = r1;
                    break;
                }
                byte[] bArr2 = this.xBuf;
                int r3 = this.xBufOff;
                int r4 = r3 + 1;
                this.xBufOff = r4;
                int r5 = r1 + 1;
                bArr2[r3] = bArr[r1 + r8];
                if (r4 == 4) {
                    processWord(bArr2, 0);
                    this.xBufOff = 0;
                    r0 = r5;
                    break;
                }
                r1 = r5;
            }
        }
        int r12 = ((max - r0) & (-4)) + r0;
        while (r0 < r12) {
            processWord(bArr, r8 + r0);
            r0 += 4;
        }
        while (r0 < max) {
            byte[] bArr3 = this.xBuf;
            int r2 = this.xBufOff;
            this.xBufOff = r2 + 1;
            bArr3[r2] = bArr[r0 + r8];
            r0++;
        }
        this.byteCount += max;
    }
}
