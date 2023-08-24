package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class RIPEMD256Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: H0 */
    private int f1794H0;

    /* renamed from: H1 */
    private int f1795H1;

    /* renamed from: H2 */
    private int f1796H2;

    /* renamed from: H3 */
    private int f1797H3;

    /* renamed from: H4 */
    private int f1798H4;

    /* renamed from: H5 */
    private int f1799H5;

    /* renamed from: H6 */
    private int f1800H6;

    /* renamed from: H7 */
    private int f1801H7;

    /* renamed from: X */
    private int[] f1802X;
    private int xOff;

    public RIPEMD256Digest() {
        this.f1802X = new int[16];
        reset();
    }

    public RIPEMD256Digest(RIPEMD256Digest rIPEMD256Digest) {
        super(rIPEMD256Digest);
        this.f1802X = new int[16];
        copyIn(rIPEMD256Digest);
    }

    /* renamed from: F1 */
    private int m92F1(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(r1 + m87f1(r2, r3, r4) + r5, r6);
    }

    /* renamed from: F2 */
    private int m91F2(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(r1 + m86f2(r2, r3, r4) + r5 + 1518500249, r6);
    }

    /* renamed from: F3 */
    private int m90F3(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(r1 + m85f3(r2, r3, r4) + r5 + 1859775393, r6);
    }

    /* renamed from: F4 */
    private int m89F4(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(((r1 + m84f4(r2, r3, r4)) + r5) - 1894007588, r6);
    }

    private int FF1(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(r1 + m87f1(r2, r3, r4) + r5, r6);
    }

    private int FF2(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(r1 + m86f2(r2, r3, r4) + r5 + 1836072691, r6);
    }

    private int FF3(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(r1 + m85f3(r2, r3, r4) + r5 + 1548603684, r6);
    }

    private int FF4(int r1, int r2, int r3, int r4, int r5, int r6) {
        return m88RL(r1 + m84f4(r2, r3, r4) + r5 + 1352829926, r6);
    }

    /* renamed from: RL */
    private int m88RL(int r2, int r3) {
        return (r2 >>> (32 - r3)) | (r2 << r3);
    }

    private void copyIn(RIPEMD256Digest rIPEMD256Digest) {
        super.copyIn((GeneralDigest) rIPEMD256Digest);
        this.f1794H0 = rIPEMD256Digest.f1794H0;
        this.f1795H1 = rIPEMD256Digest.f1795H1;
        this.f1796H2 = rIPEMD256Digest.f1796H2;
        this.f1797H3 = rIPEMD256Digest.f1797H3;
        this.f1798H4 = rIPEMD256Digest.f1798H4;
        this.f1799H5 = rIPEMD256Digest.f1799H5;
        this.f1800H6 = rIPEMD256Digest.f1800H6;
        this.f1801H7 = rIPEMD256Digest.f1801H7;
        int[] r0 = rIPEMD256Digest.f1802X;
        System.arraycopy(r0, 0, this.f1802X, 0, r0.length);
        this.xOff = rIPEMD256Digest.xOff;
    }

    /* renamed from: f1 */
    private int m87f1(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    /* renamed from: f2 */
    private int m86f2(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: f3 */
    private int m85f3(int r1, int r2, int r3) {
        return (r1 | (~r2)) ^ r3;
    }

    /* renamed from: f4 */
    private int m84f4(int r1, int r2, int r3) {
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
        return new RIPEMD256Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        unpackWord(this.f1794H0, bArr, r4);
        unpackWord(this.f1795H1, bArr, r4 + 4);
        unpackWord(this.f1796H2, bArr, r4 + 8);
        unpackWord(this.f1797H3, bArr, r4 + 12);
        unpackWord(this.f1798H4, bArr, r4 + 16);
        unpackWord(this.f1799H5, bArr, r4 + 20);
        unpackWord(this.f1800H6, bArr, r4 + 24);
        unpackWord(this.f1801H7, bArr, r4 + 28);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int r1 = this.f1794H0;
        int r8 = this.f1795H1;
        int r9 = this.f1796H2;
        int r10 = this.f1797H3;
        int r11 = this.f1798H4;
        int r12 = this.f1799H5;
        int r13 = this.f1800H6;
        int r14 = this.f1801H7;
        int m92F1 = m92F1(r1, r8, r9, r10, this.f1802X[0], 11);
        int m92F12 = m92F1(r10, m92F1, r8, r9, this.f1802X[1], 14);
        int m92F13 = m92F1(r9, m92F12, m92F1, r8, this.f1802X[2], 15);
        int m92F14 = m92F1(r8, m92F13, m92F12, m92F1, this.f1802X[3], 12);
        int m92F15 = m92F1(m92F1, m92F14, m92F13, m92F12, this.f1802X[4], 5);
        int m92F16 = m92F1(m92F12, m92F15, m92F14, m92F13, this.f1802X[5], 8);
        int m92F17 = m92F1(m92F13, m92F16, m92F15, m92F14, this.f1802X[6], 7);
        int m92F18 = m92F1(m92F14, m92F17, m92F16, m92F15, this.f1802X[7], 9);
        int m92F19 = m92F1(m92F15, m92F18, m92F17, m92F16, this.f1802X[8], 11);
        int m92F110 = m92F1(m92F16, m92F19, m92F18, m92F17, this.f1802X[9], 13);
        int m92F111 = m92F1(m92F17, m92F110, m92F19, m92F18, this.f1802X[10], 14);
        int m92F112 = m92F1(m92F18, m92F111, m92F110, m92F19, this.f1802X[11], 15);
        int m92F113 = m92F1(m92F19, m92F112, m92F111, m92F110, this.f1802X[12], 6);
        int m92F114 = m92F1(m92F110, m92F113, m92F112, m92F111, this.f1802X[13], 7);
        int m92F115 = m92F1(m92F111, m92F114, m92F113, m92F112, this.f1802X[14], 9);
        int m92F116 = m92F1(m92F112, m92F115, m92F114, m92F113, this.f1802X[15], 8);
        int FF4 = FF4(r11, r12, r13, r14, this.f1802X[5], 8);
        int FF42 = FF4(r14, FF4, r12, r13, this.f1802X[14], 9);
        int FF43 = FF4(r13, FF42, FF4, r12, this.f1802X[7], 9);
        int FF44 = FF4(r12, FF43, FF42, FF4, this.f1802X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f1802X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f1802X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f1802X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f1802X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f1802X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f1802X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f1802X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f1802X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f1802X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f1802X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f1802X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f1802X[12], 6);
        int m91F2 = m91F2(FF413, m92F116, m92F115, m92F114, this.f1802X[7], 7);
        int m91F22 = m91F2(m92F114, m91F2, m92F116, m92F115, this.f1802X[4], 6);
        int m91F23 = m91F2(m92F115, m91F22, m91F2, m92F116, this.f1802X[13], 8);
        int m91F24 = m91F2(m92F116, m91F23, m91F22, m91F2, this.f1802X[1], 13);
        int m91F25 = m91F2(m91F2, m91F24, m91F23, m91F22, this.f1802X[10], 11);
        int m91F26 = m91F2(m91F22, m91F25, m91F24, m91F23, this.f1802X[6], 9);
        int m91F27 = m91F2(m91F23, m91F26, m91F25, m91F24, this.f1802X[15], 7);
        int m91F28 = m91F2(m91F24, m91F27, m91F26, m91F25, this.f1802X[3], 15);
        int m91F29 = m91F2(m91F25, m91F28, m91F27, m91F26, this.f1802X[12], 7);
        int m91F210 = m91F2(m91F26, m91F29, m91F28, m91F27, this.f1802X[0], 12);
        int m91F211 = m91F2(m91F27, m91F210, m91F29, m91F28, this.f1802X[9], 15);
        int m91F212 = m91F2(m91F28, m91F211, m91F210, m91F29, this.f1802X[5], 9);
        int m91F213 = m91F2(m91F29, m91F212, m91F211, m91F210, this.f1802X[2], 11);
        int m91F214 = m91F2(m91F210, m91F213, m91F212, m91F211, this.f1802X[14], 7);
        int m91F215 = m91F2(m91F211, m91F214, m91F213, m91F212, this.f1802X[11], 13);
        int m91F216 = m91F2(m91F212, m91F215, m91F214, m91F213, this.f1802X[8], 12);
        int FF3 = FF3(m92F113, FF416, FF415, FF414, this.f1802X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f1802X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f1802X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f1802X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f1802X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f1802X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f1802X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f1802X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f1802X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f1802X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f1802X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f1802X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f1802X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f1802X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f1802X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f1802X[2], 11);
        int m90F3 = m90F3(m91F213, FF316, m91F215, m91F214, this.f1802X[3], 11);
        int m90F32 = m90F3(m91F214, m90F3, FF316, m91F215, this.f1802X[10], 13);
        int m90F33 = m90F3(m91F215, m90F32, m90F3, FF316, this.f1802X[14], 6);
        int m90F34 = m90F3(FF316, m90F33, m90F32, m90F3, this.f1802X[4], 7);
        int m90F35 = m90F3(m90F3, m90F34, m90F33, m90F32, this.f1802X[9], 14);
        int m90F36 = m90F3(m90F32, m90F35, m90F34, m90F33, this.f1802X[15], 9);
        int m90F37 = m90F3(m90F33, m90F36, m90F35, m90F34, this.f1802X[8], 13);
        int m90F38 = m90F3(m90F34, m90F37, m90F36, m90F35, this.f1802X[1], 15);
        int m90F39 = m90F3(m90F35, m90F38, m90F37, m90F36, this.f1802X[2], 14);
        int m90F310 = m90F3(m90F36, m90F39, m90F38, m90F37, this.f1802X[7], 8);
        int m90F311 = m90F3(m90F37, m90F310, m90F39, m90F38, this.f1802X[0], 13);
        int m90F312 = m90F3(m90F38, m90F311, m90F310, m90F39, this.f1802X[6], 6);
        int m90F313 = m90F3(m90F39, m90F312, m90F311, m90F310, this.f1802X[13], 5);
        int m90F314 = m90F3(m90F310, m90F313, m90F312, m90F311, this.f1802X[11], 12);
        int m90F315 = m90F3(m90F311, m90F314, m90F313, m90F312, this.f1802X[5], 7);
        int m90F316 = m90F3(m90F312, m90F315, m90F314, m90F313, this.f1802X[12], 5);
        int FF2 = FF2(FF313, m91F216, FF315, FF314, this.f1802X[15], 9);
        int FF22 = FF2(FF314, FF2, m91F216, FF315, this.f1802X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, m91F216, this.f1802X[1], 15);
        int FF24 = FF2(m91F216, FF23, FF22, FF2, this.f1802X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f1802X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f1802X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f1802X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f1802X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f1802X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f1802X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f1802X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f1802X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f1802X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f1802X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f1802X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f1802X[13], 5);
        int m89F4 = m89F4(m90F313, m90F316, FF215, m90F314, this.f1802X[1], 11);
        int m89F42 = m89F4(m90F314, m89F4, m90F316, FF215, this.f1802X[9], 12);
        int m89F43 = m89F4(FF215, m89F42, m89F4, m90F316, this.f1802X[11], 14);
        int m89F44 = m89F4(m90F316, m89F43, m89F42, m89F4, this.f1802X[10], 15);
        int m89F45 = m89F4(m89F4, m89F44, m89F43, m89F42, this.f1802X[0], 14);
        int m89F46 = m89F4(m89F42, m89F45, m89F44, m89F43, this.f1802X[8], 15);
        int m89F47 = m89F4(m89F43, m89F46, m89F45, m89F44, this.f1802X[12], 9);
        int m89F48 = m89F4(m89F44, m89F47, m89F46, m89F45, this.f1802X[4], 8);
        int m89F49 = m89F4(m89F45, m89F48, m89F47, m89F46, this.f1802X[13], 9);
        int m89F410 = m89F4(m89F46, m89F49, m89F48, m89F47, this.f1802X[3], 14);
        int m89F411 = m89F4(m89F47, m89F410, m89F49, m89F48, this.f1802X[7], 5);
        int m89F412 = m89F4(m89F48, m89F411, m89F410, m89F49, this.f1802X[15], 6);
        int m89F413 = m89F4(m89F49, m89F412, m89F411, m89F410, this.f1802X[14], 8);
        int m89F414 = m89F4(m89F410, m89F413, m89F412, m89F411, this.f1802X[5], 6);
        int m89F415 = m89F4(m89F411, m89F414, m89F413, m89F412, this.f1802X[6], 5);
        int m89F416 = m89F4(m89F412, m89F415, m89F414, m89F413, this.f1802X[2], 12);
        int FF1 = FF1(FF213, FF216, m90F315, FF214, this.f1802X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, m90F315, this.f1802X[6], 5);
        int FF13 = FF1(m90F315, FF12, FF1, FF216, this.f1802X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f1802X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f1802X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f1802X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f1802X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f1802X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f1802X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f1802X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f1802X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f1802X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f1802X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f1802X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f1802X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f1802X[14], 8);
        this.f1794H0 += m89F413;
        this.f1795H1 += m89F416;
        this.f1796H2 += m89F415;
        this.f1797H3 += FF114;
        this.f1798H4 += FF113;
        this.f1799H5 += FF116;
        this.f1800H6 += FF115;
        this.f1801H7 += m89F414;
        this.xOff = 0;
        int r0 = 0;
        while (true) {
            int[] r15 = this.f1802X;
            if (r0 == r15.length) {
                return;
            }
            r15[r0] = 0;
            r0++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] r0 = this.f1802X;
        r0[14] = (int) ((-1) & j);
        r0[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r8) {
        int[] r0 = this.f1802X;
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
        this.f1794H0 = 1732584193;
        this.f1795H1 = -271733879;
        this.f1796H2 = -1732584194;
        this.f1797H3 = 271733878;
        this.f1798H4 = 1985229328;
        this.f1799H5 = -19088744;
        this.f1800H6 = -1985229329;
        this.f1801H7 = 19088743;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1802X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD256Digest) memoable);
    }
}
