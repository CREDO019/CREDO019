package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SHA512Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 64;

    public SHA512Digest() {
    }

    public SHA512Digest(SHA512Digest sHA512Digest) {
        super(sHA512Digest);
    }

    public SHA512Digest(byte[] bArr) {
        restoreState(bArr);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA512Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r5) {
        finish();
        Pack.longToBigEndian(this.f1759H1, bArr, r5);
        Pack.longToBigEndian(this.f1760H2, bArr, r5 + 8);
        Pack.longToBigEndian(this.f1761H3, bArr, r5 + 16);
        Pack.longToBigEndian(this.f1762H4, bArr, r5 + 24);
        Pack.longToBigEndian(this.f1763H5, bArr, r5 + 32);
        Pack.longToBigEndian(this.f1764H6, bArr, r5 + 40);
        Pack.longToBigEndian(this.f1765H7, bArr, r5 + 48);
        Pack.longToBigEndian(this.f1766H8, bArr, r5 + 56);
        reset();
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-512";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[getEncodedStateSize()];
        super.populateState(bArr);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.LongDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f1759H1 = 7640891576956012808L;
        this.f1760H2 = -4942790177534073029L;
        this.f1761H3 = 4354685564936845355L;
        this.f1762H4 = -6534734903238641935L;
        this.f1763H5 = 5840696475078001361L;
        this.f1764H6 = -7276294671716946913L;
        this.f1765H7 = 2270897969802886507L;
        this.f1766H8 = 6620516959819538809L;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((SHA512Digest) memoable);
    }
}
