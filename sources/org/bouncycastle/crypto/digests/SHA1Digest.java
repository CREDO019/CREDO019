package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: Y1 */
    private static final int f1814Y1 = 1518500249;

    /* renamed from: Y2 */
    private static final int f1815Y2 = 1859775393;

    /* renamed from: Y3 */
    private static final int f1816Y3 = -1894007588;

    /* renamed from: Y4 */
    private static final int f1817Y4 = -899497514;

    /* renamed from: H1 */
    private int f1818H1;

    /* renamed from: H2 */
    private int f1819H2;

    /* renamed from: H3 */
    private int f1820H3;

    /* renamed from: H4 */
    private int f1821H4;

    /* renamed from: H5 */
    private int f1822H5;

    /* renamed from: X */
    private int[] f1823X;
    private int xOff;

    public SHA1Digest() {
        this.f1823X = new int[80];
        reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.f1823X = new int[80];
        copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.f1823X = new int[80];
        this.f1818H1 = Pack.bigEndianToInt(bArr, 16);
        this.f1819H2 = Pack.bigEndianToInt(bArr, 20);
        this.f1820H3 = Pack.bigEndianToInt(bArr, 24);
        this.f1821H4 = Pack.bigEndianToInt(bArr, 28);
        this.f1822H5 = Pack.bigEndianToInt(bArr, 32);
        this.xOff = Pack.bigEndianToInt(bArr, 36);
        for (int r0 = 0; r0 != this.xOff; r0++) {
            this.f1823X[r0] = Pack.bigEndianToInt(bArr, (r0 * 4) + 40);
        }
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.f1818H1 = sHA1Digest.f1818H1;
        this.f1819H2 = sHA1Digest.f1819H2;
        this.f1820H3 = sHA1Digest.f1820H3;
        this.f1821H4 = sHA1Digest.f1821H4;
        this.f1822H5 = sHA1Digest.f1822H5;
        int[] r0 = sHA1Digest.f1823X;
        System.arraycopy(r0, 0, this.f1823X, 0, r0.length);
        this.xOff = sHA1Digest.xOff;
    }

    /* renamed from: f */
    private int m77f(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: g */
    private int m76g(int r2, int r3, int r4) {
        return (r2 & r4) | (r2 & r3) | (r3 & r4);
    }

    /* renamed from: h */
    private int m75h(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA1Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        Pack.intToBigEndian(this.f1818H1, bArr, r4);
        Pack.intToBigEndian(this.f1819H2, bArr, r4 + 4);
        Pack.intToBigEndian(this.f1820H3, bArr, r4 + 8);
        Pack.intToBigEndian(this.f1821H4, bArr, r4 + 12);
        Pack.intToBigEndian(this.f1822H5, bArr, r4 + 16);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-1";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 40];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f1818H1, bArr, 16);
        Pack.intToBigEndian(this.f1819H2, bArr, 20);
        Pack.intToBigEndian(this.f1820H3, bArr, 24);
        Pack.intToBigEndian(this.f1821H4, bArr, 28);
        Pack.intToBigEndian(this.f1822H5, bArr, 32);
        Pack.intToBigEndian(this.xOff, bArr, 36);
        for (int r1 = 0; r1 != this.xOff; r1++) {
            Pack.intToBigEndian(this.f1823X[r1], bArr, (r1 * 4) + 40);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int r1 = 16; r1 < 80; r1++) {
            int[] r2 = this.f1823X;
            int r3 = ((r2[r1 - 3] ^ r2[r1 - 8]) ^ r2[r1 - 14]) ^ r2[r1 - 16];
            r2[r1] = (r3 >>> 31) | (r3 << 1);
        }
        int r12 = this.f1818H1;
        int r22 = this.f1819H2;
        int r32 = this.f1820H3;
        int r4 = this.f1821H4;
        int r5 = this.f1822H5;
        int r7 = 0;
        int r8 = 0;
        while (r7 < 4) {
            int r11 = r8 + 1;
            int m77f = r5 + ((r12 << 5) | (r12 >>> 27)) + m77f(r22, r32, r4) + this.f1823X[r8] + f1814Y1;
            int r23 = (r22 >>> 2) | (r22 << 30);
            int r122 = r11 + 1;
            int m77f2 = r4 + ((m77f << 5) | (m77f >>> 27)) + m77f(r12, r23, r32) + this.f1823X[r11] + f1814Y1;
            int r13 = (r12 >>> 2) | (r12 << 30);
            int r112 = r122 + 1;
            int m77f3 = r32 + ((m77f2 << 5) | (m77f2 >>> 27)) + m77f(m77f, r13, r23) + this.f1823X[r122] + f1814Y1;
            r5 = (m77f >>> 2) | (m77f << 30);
            int r123 = r112 + 1;
            r22 = r23 + ((m77f3 << 5) | (m77f3 >>> 27)) + m77f(m77f2, r5, r13) + this.f1823X[r112] + f1814Y1;
            r4 = (m77f2 >>> 2) | (m77f2 << 30);
            r12 = r13 + ((r22 << 5) | (r22 >>> 27)) + m77f(m77f3, r4, r5) + this.f1823X[r123] + f1814Y1;
            r32 = (m77f3 >>> 2) | (m77f3 << 30);
            r7++;
            r8 = r123 + 1;
        }
        int r72 = 0;
        while (r72 < 4) {
            int r124 = r8 + 1;
            int m75h = r5 + ((r12 << 5) | (r12 >>> 27)) + m75h(r22, r32, r4) + this.f1823X[r8] + f1815Y2;
            int r24 = (r22 >>> 2) | (r22 << 30);
            int r132 = r124 + 1;
            int m75h2 = r4 + ((m75h << 5) | (m75h >>> 27)) + m75h(r12, r24, r32) + this.f1823X[r124] + f1815Y2;
            int r14 = (r12 >>> 2) | (r12 << 30);
            int r125 = r132 + 1;
            int m75h3 = r32 + ((m75h2 << 5) | (m75h2 >>> 27)) + m75h(m75h, r14, r24) + this.f1823X[r132] + f1815Y2;
            r5 = (m75h >>> 2) | (m75h << 30);
            int r133 = r125 + 1;
            r22 = r24 + ((m75h3 << 5) | (m75h3 >>> 27)) + m75h(m75h2, r5, r14) + this.f1823X[r125] + f1815Y2;
            r4 = (m75h2 >>> 2) | (m75h2 << 30);
            r12 = r14 + ((r22 << 5) | (r22 >>> 27)) + m75h(m75h3, r4, r5) + this.f1823X[r133] + f1815Y2;
            r32 = (m75h3 >>> 2) | (m75h3 << 30);
            r72++;
            r8 = r133 + 1;
        }
        int r73 = 0;
        while (r73 < 4) {
            int r126 = r8 + 1;
            int m76g = r5 + ((r12 << 5) | (r12 >>> 27)) + m76g(r22, r32, r4) + this.f1823X[r8] + f1816Y3;
            int r25 = (r22 >>> 2) | (r22 << 30);
            int r134 = r126 + 1;
            int m76g2 = r4 + ((m76g << 5) | (m76g >>> 27)) + m76g(r12, r25, r32) + this.f1823X[r126] + f1816Y3;
            int r15 = (r12 >>> 2) | (r12 << 30);
            int r127 = r134 + 1;
            int m76g3 = r32 + ((m76g2 << 5) | (m76g2 >>> 27)) + m76g(m76g, r15, r25) + this.f1823X[r134] + f1816Y3;
            r5 = (m76g >>> 2) | (m76g << 30);
            int r135 = r127 + 1;
            r22 = r25 + ((m76g3 << 5) | (m76g3 >>> 27)) + m76g(m76g2, r5, r15) + this.f1823X[r127] + f1816Y3;
            r4 = (m76g2 >>> 2) | (m76g2 << 30);
            r12 = r15 + ((r22 << 5) | (r22 >>> 27)) + m76g(m76g3, r4, r5) + this.f1823X[r135] + f1816Y3;
            r32 = (m76g3 >>> 2) | (m76g3 << 30);
            r73++;
            r8 = r135 + 1;
        }
        int r74 = 0;
        while (r74 <= 3) {
            int r113 = r8 + 1;
            int m75h4 = r5 + ((r12 << 5) | (r12 >>> 27)) + m75h(r22, r32, r4) + this.f1823X[r8] + f1817Y4;
            int r26 = (r22 >>> 2) | (r22 << 30);
            int r128 = r113 + 1;
            int m75h5 = r4 + ((m75h4 << 5) | (m75h4 >>> 27)) + m75h(r12, r26, r32) + this.f1823X[r113] + f1817Y4;
            int r16 = (r12 >>> 2) | (r12 << 30);
            int r114 = r128 + 1;
            int m75h6 = r32 + ((m75h5 << 5) | (m75h5 >>> 27)) + m75h(m75h4, r16, r26) + this.f1823X[r128] + f1817Y4;
            r5 = (m75h4 >>> 2) | (m75h4 << 30);
            int r129 = r114 + 1;
            r22 = r26 + ((m75h6 << 5) | (m75h6 >>> 27)) + m75h(m75h5, r5, r16) + this.f1823X[r114] + f1817Y4;
            r4 = (m75h5 >>> 2) | (m75h5 << 30);
            r12 = r16 + ((r22 << 5) | (r22 >>> 27)) + m75h(m75h6, r4, r5) + this.f1823X[r129] + f1817Y4;
            r32 = (m75h6 >>> 2) | (m75h6 << 30);
            r74++;
            r8 = r129 + 1;
        }
        this.f1818H1 += r12;
        this.f1819H2 += r22;
        this.f1820H3 += r32;
        this.f1821H4 += r4;
        this.f1822H5 += r5;
        this.xOff = 0;
        for (int r17 = 0; r17 < 16; r17++) {
            this.f1823X[r17] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] r0 = this.f1823X;
        r0[14] = (int) (j >>> 32);
        r0[15] = (int) j;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r5) {
        int r52 = r5 + 1;
        int r53 = r52 + 1;
        int r4 = (bArr[r53 + 1] & 255) | (bArr[r5] << Ascii.CAN) | ((bArr[r52] & 255) << 16) | ((bArr[r53] & 255) << 8);
        int[] r54 = this.f1823X;
        int r0 = this.xOff;
        r54[r0] = r4;
        int r02 = r0 + 1;
        this.xOff = r02;
        if (r02 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f1818H1 = 1732584193;
        this.f1819H2 = -271733879;
        this.f1820H3 = -1732584194;
        this.f1821H4 = 271733878;
        this.f1822H5 = -1009589776;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1823X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn((GeneralDigest) sHA1Digest);
        copyIn(sHA1Digest);
    }
}
