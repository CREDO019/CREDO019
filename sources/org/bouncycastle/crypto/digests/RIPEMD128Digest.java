package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class RIPEMD128Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: H0 */
    private int f1783H0;

    /* renamed from: H1 */
    private int f1784H1;

    /* renamed from: H2 */
    private int f1785H2;

    /* renamed from: H3 */
    private int f1786H3;

    /* renamed from: X */
    private int[] f1787X;
    private int xOff;

    public RIPEMD128Digest() {
        this.f1787X = new int[16];
        reset();
    }

    public RIPEMD128Digest(RIPEMD128Digest rIPEMD128Digest) {
        super(rIPEMD128Digest);
        this.f1787X = new int[16];
        copyIn(rIPEMD128Digest);
    }

    /* renamed from: F1 */
    private int m107F1(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(r1 + m102f1(r2, r3, r4) + r5, r6);
    }

    /* renamed from: F2 */
    private int m106F2(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(r1 + m101f2(r2, r3, r4) + r5 + 1518500249, r6);
    }

    /* renamed from: F3 */
    private int m105F3(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(r1 + m100f3(r2, r3, r4) + r5 + 1859775393, r6);
    }

    /* renamed from: F4 */
    private int m104F4(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(((r1 + m99f4(r2, r3, r4)) + r5) - 1894007588, r6);
    }

    private int FF1(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(r1 + m102f1(r2, r3, r4) + r5, r6);
    }

    private int FF2(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(r1 + m101f2(r2, r3, r4) + r5 + 1836072691, r6);
    }

    private int FF3(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(r1 + m100f3(r2, r3, r4) + r5 + 1548603684, r6);
    }

    private int FF4(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m103RL(r1 + m99f4(r2, r3, r4) + r5 + 1352829926, r6);
    }

    /* renamed from: RL */
    private int m103RL(int r2, int r3) {
        return (r2 >>> (32 - r3)) | (r2 << r3);
    }

    private void copyIn(RIPEMD128Digest rIPEMD128Digest) {
        super.copyIn((GeneralDigest) rIPEMD128Digest);
        this.f1783H0 = rIPEMD128Digest.f1783H0;
        this.f1784H1 = rIPEMD128Digest.f1784H1;
        this.f1785H2 = rIPEMD128Digest.f1785H2;
        this.f1786H3 = rIPEMD128Digest.f1786H3;
        int[] r0 = rIPEMD128Digest.f1787X;
        System.arraycopy(r0, 0, this.f1787X, 0, r0.length);
        this.xOff = rIPEMD128Digest.xOff;
    }

    /* renamed from: f1 */
    private int m102f1(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    /* renamed from: f2 */
    private int m101f2(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: f3 */
    private int m100f3(int r1, int r2, int r3) {
        return (r1 | (~r2)) ^ r3;
    }

    /* renamed from: f4 */
    private int m99f4(int r1, int r2, int r3) {
        return (r1 & r3) | (r2 & (~r3));
    }

    private void unpackWord(int r3, byte[] bArr, int r5) {
        bArr[r5] = (byte) r3;
        bArr[r5 + 1] = (byte) (r3 >>> 8);
        bArr[r5 + 2] = (byte) (r3 >>> 16);
        bArr[r5 + 3] = (byte) (r3 >>> 24);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD128Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        unpackWord(this.f1783H0, bArr, r4);
        unpackWord(this.f1784H1, bArr, r4 + 4);
        unpackWord(this.f1785H2, bArr, r4 + 8);
        unpackWord(this.f1786H3, bArr, r4 + 12);
        reset();
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int r8 = this.f1783H0;
        int r9 = this.f1784H1;
        int r10 = this.f1785H2;
        int r11 = this.f1786H3;
        int m107F1 = m107F1(r8, r9, r10, r11, this.f1787X[0], 11);
        int m107F12 = m107F1(r11, m107F1, r9, r10, this.f1787X[1], 14);
        int m107F13 = m107F1(r10, m107F12, m107F1, r9, this.f1787X[2], 15);
        int m107F14 = m107F1(r9, m107F13, m107F12, m107F1, this.f1787X[3], 12);
        int m107F15 = m107F1(m107F1, m107F14, m107F13, m107F12, this.f1787X[4], 5);
        int m107F16 = m107F1(m107F12, m107F15, m107F14, m107F13, this.f1787X[5], 8);
        int m107F17 = m107F1(m107F13, m107F16, m107F15, m107F14, this.f1787X[6], 7);
        int m107F18 = m107F1(m107F14, m107F17, m107F16, m107F15, this.f1787X[7], 9);
        int m107F19 = m107F1(m107F15, m107F18, m107F17, m107F16, this.f1787X[8], 11);
        int m107F110 = m107F1(m107F16, m107F19, m107F18, m107F17, this.f1787X[9], 13);
        int m107F111 = m107F1(m107F17, m107F110, m107F19, m107F18, this.f1787X[10], 14);
        int m107F112 = m107F1(m107F18, m107F111, m107F110, m107F19, this.f1787X[11], 15);
        int m107F113 = m107F1(m107F19, m107F112, m107F111, m107F110, this.f1787X[12], 6);
        int m107F114 = m107F1(m107F110, m107F113, m107F112, m107F111, this.f1787X[13], 7);
        int m107F115 = m107F1(m107F111, m107F114, m107F113, m107F112, this.f1787X[14], 9);
        int m107F116 = m107F1(m107F112, m107F115, m107F114, m107F113, this.f1787X[15], 8);
        int m106F2 = m106F2(m107F113, m107F116, m107F115, m107F114, this.f1787X[7], 7);
        int m106F22 = m106F2(m107F114, m106F2, m107F116, m107F115, this.f1787X[4], 6);
        int m106F23 = m106F2(m107F115, m106F22, m106F2, m107F116, this.f1787X[13], 8);
        int m106F24 = m106F2(m107F116, m106F23, m106F22, m106F2, this.f1787X[1], 13);
        int m106F25 = m106F2(m106F2, m106F24, m106F23, m106F22, this.f1787X[10], 11);
        int m106F26 = m106F2(m106F22, m106F25, m106F24, m106F23, this.f1787X[6], 9);
        int m106F27 = m106F2(m106F23, m106F26, m106F25, m106F24, this.f1787X[15], 7);
        int m106F28 = m106F2(m106F24, m106F27, m106F26, m106F25, this.f1787X[3], 15);
        int m106F29 = m106F2(m106F25, m106F28, m106F27, m106F26, this.f1787X[12], 7);
        int m106F210 = m106F2(m106F26, m106F29, m106F28, m106F27, this.f1787X[0], 12);
        int m106F211 = m106F2(m106F27, m106F210, m106F29, m106F28, this.f1787X[9], 15);
        int m106F212 = m106F2(m106F28, m106F211, m106F210, m106F29, this.f1787X[5], 9);
        int m106F213 = m106F2(m106F29, m106F212, m106F211, m106F210, this.f1787X[2], 11);
        int m106F214 = m106F2(m106F210, m106F213, m106F212, m106F211, this.f1787X[14], 7);
        int m106F215 = m106F2(m106F211, m106F214, m106F213, m106F212, this.f1787X[11], 13);
        int m106F216 = m106F2(m106F212, m106F215, m106F214, m106F213, this.f1787X[8], 12);
        int m105F3 = m105F3(m106F213, m106F216, m106F215, m106F214, this.f1787X[3], 11);
        int m105F32 = m105F3(m106F214, m105F3, m106F216, m106F215, this.f1787X[10], 13);
        int m105F33 = m105F3(m106F215, m105F32, m105F3, m106F216, this.f1787X[14], 6);
        int m105F34 = m105F3(m106F216, m105F33, m105F32, m105F3, this.f1787X[4], 7);
        int m105F35 = m105F3(m105F3, m105F34, m105F33, m105F32, this.f1787X[9], 14);
        int m105F36 = m105F3(m105F32, m105F35, m105F34, m105F33, this.f1787X[15], 9);
        int m105F37 = m105F3(m105F33, m105F36, m105F35, m105F34, this.f1787X[8], 13);
        int m105F38 = m105F3(m105F34, m105F37, m105F36, m105F35, this.f1787X[1], 15);
        int m105F39 = m105F3(m105F35, m105F38, m105F37, m105F36, this.f1787X[2], 14);
        int m105F310 = m105F3(m105F36, m105F39, m105F38, m105F37, this.f1787X[7], 8);
        int m105F311 = m105F3(m105F37, m105F310, m105F39, m105F38, this.f1787X[0], 13);
        int m105F312 = m105F3(m105F38, m105F311, m105F310, m105F39, this.f1787X[6], 6);
        int m105F313 = m105F3(m105F39, m105F312, m105F311, m105F310, this.f1787X[13], 5);
        int m105F314 = m105F3(m105F310, m105F313, m105F312, m105F311, this.f1787X[11], 12);
        int m105F315 = m105F3(m105F311, m105F314, m105F313, m105F312, this.f1787X[5], 7);
        int m105F316 = m105F3(m105F312, m105F315, m105F314, m105F313, this.f1787X[12], 5);
        int m104F4 = m104F4(m105F313, m105F316, m105F315, m105F314, this.f1787X[1], 11);
        int m104F42 = m104F4(m105F314, m104F4, m105F316, m105F315, this.f1787X[9], 12);
        int m104F43 = m104F4(m105F315, m104F42, m104F4, m105F316, this.f1787X[11], 14);
        int m104F44 = m104F4(m105F316, m104F43, m104F42, m104F4, this.f1787X[10], 15);
        int m104F45 = m104F4(m104F4, m104F44, m104F43, m104F42, this.f1787X[0], 14);
        int m104F46 = m104F4(m104F42, m104F45, m104F44, m104F43, this.f1787X[8], 15);
        int m104F47 = m104F4(m104F43, m104F46, m104F45, m104F44, this.f1787X[12], 9);
        int m104F48 = m104F4(m104F44, m104F47, m104F46, m104F45, this.f1787X[4], 8);
        int m104F49 = m104F4(m104F45, m104F48, m104F47, m104F46, this.f1787X[13], 9);
        int m104F410 = m104F4(m104F46, m104F49, m104F48, m104F47, this.f1787X[3], 14);
        int m104F411 = m104F4(m104F47, m104F410, m104F49, m104F48, this.f1787X[7], 5);
        int m104F412 = m104F4(m104F48, m104F411, m104F410, m104F49, this.f1787X[15], 6);
        int m104F413 = m104F4(m104F49, m104F412, m104F411, m104F410, this.f1787X[14], 8);
        int m104F414 = m104F4(m104F410, m104F413, m104F412, m104F411, this.f1787X[5], 6);
        int m104F415 = m104F4(m104F411, m104F414, m104F413, m104F412, this.f1787X[6], 5);
        int m104F416 = m104F4(m104F412, m104F415, m104F414, m104F413, this.f1787X[2], 12);
        int FF4 = FF4(r8, r9, r10, r11, this.f1787X[5], 8);
        int FF42 = FF4(r11, FF4, r9, r10, this.f1787X[14], 9);
        int FF43 = FF4(r10, FF42, FF4, r9, this.f1787X[7], 9);
        int FF44 = FF4(r9, FF43, FF42, FF4, this.f1787X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f1787X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f1787X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f1787X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f1787X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f1787X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f1787X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f1787X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f1787X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f1787X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f1787X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f1787X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f1787X[12], 6);
        int FF3 = FF3(FF413, FF416, FF415, FF414, this.f1787X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f1787X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f1787X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f1787X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f1787X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f1787X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f1787X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f1787X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f1787X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f1787X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f1787X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f1787X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f1787X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f1787X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f1787X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f1787X[2], 11);
        int FF2 = FF2(FF313, FF316, FF315, FF314, this.f1787X[15], 9);
        int FF22 = FF2(FF314, FF2, FF316, FF315, this.f1787X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, FF316, this.f1787X[1], 15);
        int FF24 = FF2(FF316, FF23, FF22, FF2, this.f1787X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f1787X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f1787X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f1787X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f1787X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f1787X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f1787X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f1787X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f1787X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f1787X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f1787X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f1787X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f1787X[13], 5);
        int FF1 = FF1(FF213, FF216, FF215, FF214, this.f1787X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, FF215, this.f1787X[6], 5);
        int FF13 = FF1(FF215, FF12, FF1, FF216, this.f1787X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f1787X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f1787X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f1787X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f1787X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f1787X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f1787X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f1787X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f1787X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f1787X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f1787X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f1787X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f1787X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f1787X[14], 8);
        this.f1784H1 = this.f1785H2 + m104F414 + FF113;
        this.f1785H2 = this.f1786H3 + m104F413 + FF116;
        this.f1786H3 = this.f1783H0 + m104F416 + FF115;
        this.f1783H0 = FF114 + m104F415 + this.f1784H1;
        this.xOff = 0;
        int r0 = 0;
        while (true) {
            int[] r1 = this.f1787X;
            if (r0 == r1.length) {
                return;
            }
            r1[r0] = 0;
            r0++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] r0 = this.f1787X;
        r0[14] = (int) ((-1) & j);
        r0[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r8) {
        int[] r0 = this.f1787X;
        int r1 = this.xOff;
        int r2 = r1 + 1;
        this.xOff = r2;
        r0[r1] = ((bArr[r8 + 3] & 255) << 24) | (bArr[r8] & 255) | ((bArr[r8 + 1] & 255) << 8) | ((bArr[r8 + 2] & 255) << 16);
        if (r2 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f1783H0 = 1732584193;
        this.f1784H1 = -271733879;
        this.f1785H2 = -1732584194;
        this.f1786H3 = 271733878;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1787X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD128Digest) memoable);
    }
}
