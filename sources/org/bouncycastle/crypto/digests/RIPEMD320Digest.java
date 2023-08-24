package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class RIPEMD320Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 40;

    /* renamed from: H0 */
    private int f1803H0;

    /* renamed from: H1 */
    private int f1804H1;

    /* renamed from: H2 */
    private int f1805H2;

    /* renamed from: H3 */
    private int f1806H3;

    /* renamed from: H4 */
    private int f1807H4;

    /* renamed from: H5 */
    private int f1808H5;

    /* renamed from: H6 */
    private int f1809H6;

    /* renamed from: H7 */
    private int f1810H7;

    /* renamed from: H8 */
    private int f1811H8;

    /* renamed from: H9 */
    private int f1812H9;

    /* renamed from: X */
    private int[] f1813X;
    private int xOff;

    public RIPEMD320Digest() {
        this.f1813X = new int[16];
        reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super(rIPEMD320Digest);
        this.f1813X = new int[16];
        doCopy(rIPEMD320Digest);
    }

    /* renamed from: RL */
    private int m83RL(int r2, int r3) {
        return (r2 >>> (32 - r3)) | (r2 << r3);
    }

    private void doCopy(RIPEMD320Digest rIPEMD320Digest) {
        super.copyIn(rIPEMD320Digest);
        this.f1803H0 = rIPEMD320Digest.f1803H0;
        this.f1804H1 = rIPEMD320Digest.f1804H1;
        this.f1805H2 = rIPEMD320Digest.f1805H2;
        this.f1806H3 = rIPEMD320Digest.f1806H3;
        this.f1807H4 = rIPEMD320Digest.f1807H4;
        this.f1808H5 = rIPEMD320Digest.f1808H5;
        this.f1809H6 = rIPEMD320Digest.f1809H6;
        this.f1810H7 = rIPEMD320Digest.f1810H7;
        this.f1811H8 = rIPEMD320Digest.f1811H8;
        this.f1812H9 = rIPEMD320Digest.f1812H9;
        int[] r0 = rIPEMD320Digest.f1813X;
        System.arraycopy(r0, 0, this.f1813X, 0, r0.length);
        this.xOff = rIPEMD320Digest.xOff;
    }

    /* renamed from: f1 */
    private int m82f1(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    /* renamed from: f2 */
    private int m81f2(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: f3 */
    private int m80f3(int r1, int r2, int r3) {
        return (r1 | (~r2)) ^ r3;
    }

    /* renamed from: f4 */
    private int m79f4(int r1, int r2, int r3) {
        return (r1 & r3) | (r2 & (~r3));
    }

    /* renamed from: f5 */
    private int m78f5(int r1, int r2, int r3) {
        return r1 ^ (r2 | (~r3));
    }

    private void unpackWord(int r3, byte[] bArr, int r5) {
        bArr[r5] = (byte) r3;
        bArr[r5 + 1] = (byte) (r3 >>> 8);
        bArr[r5 + 2] = (byte) (r3 >>> 16);
        bArr[r5 + 3] = (byte) (r3 >>> 24);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        unpackWord(this.f1803H0, bArr, r4);
        unpackWord(this.f1804H1, bArr, r4 + 4);
        unpackWord(this.f1805H2, bArr, r4 + 8);
        unpackWord(this.f1806H3, bArr, r4 + 12);
        unpackWord(this.f1807H4, bArr, r4 + 16);
        unpackWord(this.f1808H5, bArr, r4 + 20);
        unpackWord(this.f1809H6, bArr, r4 + 24);
        unpackWord(this.f1810H7, bArr, r4 + 28);
        unpackWord(this.f1811H8, bArr, r4 + 32);
        unpackWord(this.f1812H9, bArr, r4 + 36);
        reset();
        return 40;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 40;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int r1 = this.f1803H0;
        int r2 = this.f1804H1;
        int r3 = this.f1805H2;
        int r4 = this.f1806H3;
        int r5 = this.f1807H4;
        int r6 = this.f1808H5;
        int r7 = this.f1809H6;
        int r8 = this.f1810H7;
        int r9 = this.f1811H8;
        int r10 = this.f1812H9;
        int m83RL = m83RL(r1 + m82f1(r2, r3, r4) + this.f1813X[0], 11) + r5;
        int m83RL2 = m83RL(r3, 10);
        int m83RL3 = m83RL(r5 + m82f1(m83RL, r2, m83RL2) + this.f1813X[1], 14) + r4;
        int m83RL4 = m83RL(r2, 10);
        int m83RL5 = m83RL(r4 + m82f1(m83RL3, m83RL, m83RL4) + this.f1813X[2], 15) + m83RL2;
        int m83RL6 = m83RL(m83RL, 10);
        int m83RL7 = m83RL(m83RL2 + m82f1(m83RL5, m83RL3, m83RL6) + this.f1813X[3], 12) + m83RL4;
        int m83RL8 = m83RL(m83RL3, 10);
        int m83RL9 = m83RL(m83RL4 + m82f1(m83RL7, m83RL5, m83RL8) + this.f1813X[4], 5) + m83RL6;
        int m83RL10 = m83RL(m83RL5, 10);
        int m83RL11 = m83RL(m83RL6 + m82f1(m83RL9, m83RL7, m83RL10) + this.f1813X[5], 8) + m83RL8;
        int m83RL12 = m83RL(m83RL7, 10);
        int m83RL13 = m83RL(m83RL8 + m82f1(m83RL11, m83RL9, m83RL12) + this.f1813X[6], 7) + m83RL10;
        int m83RL14 = m83RL(m83RL9, 10);
        int m83RL15 = m83RL(m83RL10 + m82f1(m83RL13, m83RL11, m83RL14) + this.f1813X[7], 9) + m83RL12;
        int m83RL16 = m83RL(m83RL11, 10);
        int m83RL17 = m83RL(m83RL12 + m82f1(m83RL15, m83RL13, m83RL16) + this.f1813X[8], 11) + m83RL14;
        int m83RL18 = m83RL(m83RL13, 10);
        int m83RL19 = m83RL(m83RL14 + m82f1(m83RL17, m83RL15, m83RL18) + this.f1813X[9], 13) + m83RL16;
        int m83RL20 = m83RL(m83RL15, 10);
        int m83RL21 = m83RL(m83RL16 + m82f1(m83RL19, m83RL17, m83RL20) + this.f1813X[10], 14) + m83RL18;
        int m83RL22 = m83RL(m83RL17, 10);
        int m83RL23 = m83RL(m83RL18 + m82f1(m83RL21, m83RL19, m83RL22) + this.f1813X[11], 15) + m83RL20;
        int m83RL24 = m83RL(m83RL19, 10);
        int m83RL25 = m83RL(m83RL20 + m82f1(m83RL23, m83RL21, m83RL24) + this.f1813X[12], 6) + m83RL22;
        int m83RL26 = m83RL(m83RL21, 10);
        int m83RL27 = m83RL(m83RL22 + m82f1(m83RL25, m83RL23, m83RL26) + this.f1813X[13], 7) + m83RL24;
        int m83RL28 = m83RL(m83RL23, 10);
        int m83RL29 = m83RL(m83RL24 + m82f1(m83RL27, m83RL25, m83RL28) + this.f1813X[14], 9) + m83RL26;
        int m83RL30 = m83RL(m83RL25, 10);
        int m83RL31 = m83RL(m83RL26 + m82f1(m83RL29, m83RL27, m83RL30) + this.f1813X[15], 8) + m83RL28;
        int m83RL32 = m83RL(m83RL27, 10);
        int m83RL33 = m83RL(r6 + m78f5(r7, r8, r9) + this.f1813X[5] + 1352829926, 8) + r10;
        int m83RL34 = m83RL(r8, 10);
        int m83RL35 = m83RL(r10 + m78f5(m83RL33, r7, m83RL34) + this.f1813X[14] + 1352829926, 9) + r9;
        int m83RL36 = m83RL(r7, 10);
        int m83RL37 = m83RL(r9 + m78f5(m83RL35, m83RL33, m83RL36) + this.f1813X[7] + 1352829926, 9) + m83RL34;
        int m83RL38 = m83RL(m83RL33, 10);
        int m83RL39 = m83RL(m83RL34 + m78f5(m83RL37, m83RL35, m83RL38) + this.f1813X[0] + 1352829926, 11) + m83RL36;
        int m83RL40 = m83RL(m83RL35, 10);
        int m83RL41 = m83RL(m83RL36 + m78f5(m83RL39, m83RL37, m83RL40) + this.f1813X[9] + 1352829926, 13) + m83RL38;
        int m83RL42 = m83RL(m83RL37, 10);
        int m83RL43 = m83RL(m83RL38 + m78f5(m83RL41, m83RL39, m83RL42) + this.f1813X[2] + 1352829926, 15) + m83RL40;
        int m83RL44 = m83RL(m83RL39, 10);
        int m83RL45 = m83RL(m83RL40 + m78f5(m83RL43, m83RL41, m83RL44) + this.f1813X[11] + 1352829926, 15) + m83RL42;
        int m83RL46 = m83RL(m83RL41, 10);
        int m83RL47 = m83RL(m83RL42 + m78f5(m83RL45, m83RL43, m83RL46) + this.f1813X[4] + 1352829926, 5) + m83RL44;
        int m83RL48 = m83RL(m83RL43, 10);
        int m83RL49 = m83RL(m83RL44 + m78f5(m83RL47, m83RL45, m83RL48) + this.f1813X[13] + 1352829926, 7) + m83RL46;
        int m83RL50 = m83RL(m83RL45, 10);
        int m83RL51 = m83RL(m83RL46 + m78f5(m83RL49, m83RL47, m83RL50) + this.f1813X[6] + 1352829926, 7) + m83RL48;
        int m83RL52 = m83RL(m83RL47, 10);
        int m83RL53 = m83RL(m83RL48 + m78f5(m83RL51, m83RL49, m83RL52) + this.f1813X[15] + 1352829926, 8) + m83RL50;
        int m83RL54 = m83RL(m83RL49, 10);
        int m83RL55 = m83RL(m83RL50 + m78f5(m83RL53, m83RL51, m83RL54) + this.f1813X[8] + 1352829926, 11) + m83RL52;
        int m83RL56 = m83RL(m83RL51, 10);
        int m83RL57 = m83RL(m83RL52 + m78f5(m83RL55, m83RL53, m83RL56) + this.f1813X[1] + 1352829926, 14) + m83RL54;
        int m83RL58 = m83RL(m83RL53, 10);
        int m83RL59 = m83RL(m83RL54 + m78f5(m83RL57, m83RL55, m83RL58) + this.f1813X[10] + 1352829926, 14) + m83RL56;
        int m83RL60 = m83RL(m83RL55, 10);
        int m83RL61 = m83RL(m83RL56 + m78f5(m83RL59, m83RL57, m83RL60) + this.f1813X[3] + 1352829926, 12) + m83RL58;
        int m83RL62 = m83RL(m83RL57, 10);
        int m83RL63 = m83RL(m83RL58 + m78f5(m83RL61, m83RL59, m83RL62) + this.f1813X[12] + 1352829926, 6) + m83RL60;
        int m83RL64 = m83RL(m83RL59, 10);
        int m83RL65 = m83RL(m83RL28 + m81f2(m83RL63, m83RL29, m83RL32) + this.f1813X[7] + 1518500249, 7) + m83RL30;
        int m83RL66 = m83RL(m83RL29, 10);
        int m83RL67 = m83RL(m83RL30 + m81f2(m83RL65, m83RL63, m83RL66) + this.f1813X[4] + 1518500249, 6) + m83RL32;
        int m83RL68 = m83RL(m83RL63, 10);
        int m83RL69 = m83RL(m83RL32 + m81f2(m83RL67, m83RL65, m83RL68) + this.f1813X[13] + 1518500249, 8) + m83RL66;
        int m83RL70 = m83RL(m83RL65, 10);
        int m83RL71 = m83RL(m83RL66 + m81f2(m83RL69, m83RL67, m83RL70) + this.f1813X[1] + 1518500249, 13) + m83RL68;
        int m83RL72 = m83RL(m83RL67, 10);
        int m83RL73 = m83RL(m83RL68 + m81f2(m83RL71, m83RL69, m83RL72) + this.f1813X[10] + 1518500249, 11) + m83RL70;
        int m83RL74 = m83RL(m83RL69, 10);
        int m83RL75 = m83RL(m83RL70 + m81f2(m83RL73, m83RL71, m83RL74) + this.f1813X[6] + 1518500249, 9) + m83RL72;
        int m83RL76 = m83RL(m83RL71, 10);
        int m83RL77 = m83RL(m83RL72 + m81f2(m83RL75, m83RL73, m83RL76) + this.f1813X[15] + 1518500249, 7) + m83RL74;
        int m83RL78 = m83RL(m83RL73, 10);
        int m83RL79 = m83RL(m83RL74 + m81f2(m83RL77, m83RL75, m83RL78) + this.f1813X[3] + 1518500249, 15) + m83RL76;
        int m83RL80 = m83RL(m83RL75, 10);
        int m83RL81 = m83RL(m83RL76 + m81f2(m83RL79, m83RL77, m83RL80) + this.f1813X[12] + 1518500249, 7) + m83RL78;
        int m83RL82 = m83RL(m83RL77, 10);
        int m83RL83 = m83RL(m83RL78 + m81f2(m83RL81, m83RL79, m83RL82) + this.f1813X[0] + 1518500249, 12) + m83RL80;
        int m83RL84 = m83RL(m83RL79, 10);
        int m83RL85 = m83RL(m83RL80 + m81f2(m83RL83, m83RL81, m83RL84) + this.f1813X[9] + 1518500249, 15) + m83RL82;
        int m83RL86 = m83RL(m83RL81, 10);
        int m83RL87 = m83RL(m83RL82 + m81f2(m83RL85, m83RL83, m83RL86) + this.f1813X[5] + 1518500249, 9) + m83RL84;
        int m83RL88 = m83RL(m83RL83, 10);
        int m83RL89 = m83RL(m83RL84 + m81f2(m83RL87, m83RL85, m83RL88) + this.f1813X[2] + 1518500249, 11) + m83RL86;
        int m83RL90 = m83RL(m83RL85, 10);
        int m83RL91 = m83RL(m83RL86 + m81f2(m83RL89, m83RL87, m83RL90) + this.f1813X[14] + 1518500249, 7) + m83RL88;
        int m83RL92 = m83RL(m83RL87, 10);
        int m83RL93 = m83RL(m83RL88 + m81f2(m83RL91, m83RL89, m83RL92) + this.f1813X[11] + 1518500249, 13) + m83RL90;
        int m83RL94 = m83RL(m83RL89, 10);
        int m83RL95 = m83RL(m83RL90 + m81f2(m83RL93, m83RL91, m83RL94) + this.f1813X[8] + 1518500249, 12) + m83RL92;
        int m83RL96 = m83RL(m83RL91, 10);
        int m83RL97 = m83RL(m83RL60 + m79f4(m83RL31, m83RL61, m83RL64) + this.f1813X[6] + 1548603684, 9) + m83RL62;
        int m83RL98 = m83RL(m83RL61, 10);
        int m83RL99 = m83RL(m83RL62 + m79f4(m83RL97, m83RL31, m83RL98) + this.f1813X[11] + 1548603684, 13) + m83RL64;
        int m83RL100 = m83RL(m83RL31, 10);
        int m83RL101 = m83RL(m83RL64 + m79f4(m83RL99, m83RL97, m83RL100) + this.f1813X[3] + 1548603684, 15) + m83RL98;
        int m83RL102 = m83RL(m83RL97, 10);
        int m83RL103 = m83RL(m83RL98 + m79f4(m83RL101, m83RL99, m83RL102) + this.f1813X[7] + 1548603684, 7) + m83RL100;
        int m83RL104 = m83RL(m83RL99, 10);
        int m83RL105 = m83RL(m83RL100 + m79f4(m83RL103, m83RL101, m83RL104) + this.f1813X[0] + 1548603684, 12) + m83RL102;
        int m83RL106 = m83RL(m83RL101, 10);
        int m83RL107 = m83RL(m83RL102 + m79f4(m83RL105, m83RL103, m83RL106) + this.f1813X[13] + 1548603684, 8) + m83RL104;
        int m83RL108 = m83RL(m83RL103, 10);
        int m83RL109 = m83RL(m83RL104 + m79f4(m83RL107, m83RL105, m83RL108) + this.f1813X[5] + 1548603684, 9) + m83RL106;
        int m83RL110 = m83RL(m83RL105, 10);
        int m83RL111 = m83RL(m83RL106 + m79f4(m83RL109, m83RL107, m83RL110) + this.f1813X[10] + 1548603684, 11) + m83RL108;
        int m83RL112 = m83RL(m83RL107, 10);
        int m83RL113 = m83RL(m83RL108 + m79f4(m83RL111, m83RL109, m83RL112) + this.f1813X[14] + 1548603684, 7) + m83RL110;
        int m83RL114 = m83RL(m83RL109, 10);
        int m83RL115 = m83RL(m83RL110 + m79f4(m83RL113, m83RL111, m83RL114) + this.f1813X[15] + 1548603684, 7) + m83RL112;
        int m83RL116 = m83RL(m83RL111, 10);
        int m83RL117 = m83RL(m83RL112 + m79f4(m83RL115, m83RL113, m83RL116) + this.f1813X[8] + 1548603684, 12) + m83RL114;
        int m83RL118 = m83RL(m83RL113, 10);
        int m83RL119 = m83RL(m83RL114 + m79f4(m83RL117, m83RL115, m83RL118) + this.f1813X[12] + 1548603684, 7) + m83RL116;
        int m83RL120 = m83RL(m83RL115, 10);
        int m83RL121 = m83RL(m83RL116 + m79f4(m83RL119, m83RL117, m83RL120) + this.f1813X[4] + 1548603684, 6) + m83RL118;
        int m83RL122 = m83RL(m83RL117, 10);
        int m83RL123 = m83RL(m83RL118 + m79f4(m83RL121, m83RL119, m83RL122) + this.f1813X[9] + 1548603684, 15) + m83RL120;
        int m83RL124 = m83RL(m83RL119, 10);
        int m83RL125 = m83RL(m83RL120 + m79f4(m83RL123, m83RL121, m83RL124) + this.f1813X[1] + 1548603684, 13) + m83RL122;
        int m83RL126 = m83RL(m83RL121, 10);
        int m83RL127 = m83RL(m83RL122 + m79f4(m83RL125, m83RL123, m83RL126) + this.f1813X[2] + 1548603684, 11) + m83RL124;
        int m83RL128 = m83RL(m83RL123, 10);
        int m83RL129 = m83RL(m83RL92 + m80f3(m83RL95, m83RL93, m83RL128) + this.f1813X[3] + 1859775393, 11) + m83RL94;
        int m83RL130 = m83RL(m83RL93, 10);
        int m83RL131 = m83RL(m83RL94 + m80f3(m83RL129, m83RL95, m83RL130) + this.f1813X[10] + 1859775393, 13) + m83RL128;
        int m83RL132 = m83RL(m83RL95, 10);
        int m83RL133 = m83RL(m83RL128 + m80f3(m83RL131, m83RL129, m83RL132) + this.f1813X[14] + 1859775393, 6) + m83RL130;
        int m83RL134 = m83RL(m83RL129, 10);
        int m83RL135 = m83RL(m83RL130 + m80f3(m83RL133, m83RL131, m83RL134) + this.f1813X[4] + 1859775393, 7) + m83RL132;
        int m83RL136 = m83RL(m83RL131, 10);
        int m83RL137 = m83RL(m83RL132 + m80f3(m83RL135, m83RL133, m83RL136) + this.f1813X[9] + 1859775393, 14) + m83RL134;
        int m83RL138 = m83RL(m83RL133, 10);
        int m83RL139 = m83RL(m83RL134 + m80f3(m83RL137, m83RL135, m83RL138) + this.f1813X[15] + 1859775393, 9) + m83RL136;
        int m83RL140 = m83RL(m83RL135, 10);
        int m83RL141 = m83RL(m83RL136 + m80f3(m83RL139, m83RL137, m83RL140) + this.f1813X[8] + 1859775393, 13) + m83RL138;
        int m83RL142 = m83RL(m83RL137, 10);
        int m83RL143 = m83RL(m83RL138 + m80f3(m83RL141, m83RL139, m83RL142) + this.f1813X[1] + 1859775393, 15) + m83RL140;
        int m83RL144 = m83RL(m83RL139, 10);
        int m83RL145 = m83RL(m83RL140 + m80f3(m83RL143, m83RL141, m83RL144) + this.f1813X[2] + 1859775393, 14) + m83RL142;
        int m83RL146 = m83RL(m83RL141, 10);
        int m83RL147 = m83RL(m83RL142 + m80f3(m83RL145, m83RL143, m83RL146) + this.f1813X[7] + 1859775393, 8) + m83RL144;
        int m83RL148 = m83RL(m83RL143, 10);
        int m83RL149 = m83RL(m83RL144 + m80f3(m83RL147, m83RL145, m83RL148) + this.f1813X[0] + 1859775393, 13) + m83RL146;
        int m83RL150 = m83RL(m83RL145, 10);
        int m83RL151 = m83RL(m83RL146 + m80f3(m83RL149, m83RL147, m83RL150) + this.f1813X[6] + 1859775393, 6) + m83RL148;
        int m83RL152 = m83RL(m83RL147, 10);
        int m83RL153 = m83RL(m83RL148 + m80f3(m83RL151, m83RL149, m83RL152) + this.f1813X[13] + 1859775393, 5) + m83RL150;
        int m83RL154 = m83RL(m83RL149, 10);
        int m83RL155 = m83RL(m83RL150 + m80f3(m83RL153, m83RL151, m83RL154) + this.f1813X[11] + 1859775393, 12) + m83RL152;
        int m83RL156 = m83RL(m83RL151, 10);
        int m83RL157 = m83RL(m83RL152 + m80f3(m83RL155, m83RL153, m83RL156) + this.f1813X[5] + 1859775393, 7) + m83RL154;
        int m83RL158 = m83RL(m83RL153, 10);
        int m83RL159 = m83RL(m83RL154 + m80f3(m83RL157, m83RL155, m83RL158) + this.f1813X[12] + 1859775393, 5) + m83RL156;
        int m83RL160 = m83RL(m83RL155, 10);
        int m83RL161 = m83RL(m83RL124 + m80f3(m83RL127, m83RL125, m83RL96) + this.f1813X[15] + 1836072691, 9) + m83RL126;
        int m83RL162 = m83RL(m83RL125, 10);
        int m83RL163 = m83RL(m83RL126 + m80f3(m83RL161, m83RL127, m83RL162) + this.f1813X[5] + 1836072691, 7) + m83RL96;
        int m83RL164 = m83RL(m83RL127, 10);
        int m83RL165 = m83RL(m83RL96 + m80f3(m83RL163, m83RL161, m83RL164) + this.f1813X[1] + 1836072691, 15) + m83RL162;
        int m83RL166 = m83RL(m83RL161, 10);
        int m83RL167 = m83RL(m83RL162 + m80f3(m83RL165, m83RL163, m83RL166) + this.f1813X[3] + 1836072691, 11) + m83RL164;
        int m83RL168 = m83RL(m83RL163, 10);
        int m83RL169 = m83RL(m83RL164 + m80f3(m83RL167, m83RL165, m83RL168) + this.f1813X[7] + 1836072691, 8) + m83RL166;
        int m83RL170 = m83RL(m83RL165, 10);
        int m83RL171 = m83RL(m83RL166 + m80f3(m83RL169, m83RL167, m83RL170) + this.f1813X[14] + 1836072691, 6) + m83RL168;
        int m83RL172 = m83RL(m83RL167, 10);
        int m83RL173 = m83RL(m83RL168 + m80f3(m83RL171, m83RL169, m83RL172) + this.f1813X[6] + 1836072691, 6) + m83RL170;
        int m83RL174 = m83RL(m83RL169, 10);
        int m83RL175 = m83RL(m83RL170 + m80f3(m83RL173, m83RL171, m83RL174) + this.f1813X[9] + 1836072691, 14) + m83RL172;
        int m83RL176 = m83RL(m83RL171, 10);
        int m83RL177 = m83RL(m83RL172 + m80f3(m83RL175, m83RL173, m83RL176) + this.f1813X[11] + 1836072691, 12) + m83RL174;
        int m83RL178 = m83RL(m83RL173, 10);
        int m83RL179 = m83RL(m83RL174 + m80f3(m83RL177, m83RL175, m83RL178) + this.f1813X[8] + 1836072691, 13) + m83RL176;
        int m83RL180 = m83RL(m83RL175, 10);
        int m83RL181 = m83RL(m83RL176 + m80f3(m83RL179, m83RL177, m83RL180) + this.f1813X[12] + 1836072691, 5) + m83RL178;
        int m83RL182 = m83RL(m83RL177, 10);
        int m83RL183 = m83RL(m83RL178 + m80f3(m83RL181, m83RL179, m83RL182) + this.f1813X[2] + 1836072691, 14) + m83RL180;
        int m83RL184 = m83RL(m83RL179, 10);
        int m83RL185 = m83RL(m83RL180 + m80f3(m83RL183, m83RL181, m83RL184) + this.f1813X[10] + 1836072691, 13) + m83RL182;
        int m83RL186 = m83RL(m83RL181, 10);
        int m83RL187 = m83RL(m83RL182 + m80f3(m83RL185, m83RL183, m83RL186) + this.f1813X[0] + 1836072691, 13) + m83RL184;
        int m83RL188 = m83RL(m83RL183, 10);
        int m83RL189 = m83RL(m83RL184 + m80f3(m83RL187, m83RL185, m83RL188) + this.f1813X[4] + 1836072691, 7) + m83RL186;
        int m83RL190 = m83RL(m83RL185, 10);
        int m83RL191 = m83RL(m83RL186 + m80f3(m83RL189, m83RL187, m83RL190) + this.f1813X[13] + 1836072691, 5) + m83RL188;
        int m83RL192 = m83RL(m83RL187, 10);
        int m83RL193 = m83RL(((m83RL188 + m79f4(m83RL159, m83RL157, m83RL160)) + this.f1813X[1]) - 1894007588, 11) + m83RL158;
        int m83RL194 = m83RL(m83RL157, 10);
        int m83RL195 = m83RL(((m83RL158 + m79f4(m83RL193, m83RL159, m83RL194)) + this.f1813X[9]) - 1894007588, 12) + m83RL160;
        int m83RL196 = m83RL(m83RL159, 10);
        int m83RL197 = m83RL(((m83RL160 + m79f4(m83RL195, m83RL193, m83RL196)) + this.f1813X[11]) - 1894007588, 14) + m83RL194;
        int m83RL198 = m83RL(m83RL193, 10);
        int m83RL199 = m83RL(((m83RL194 + m79f4(m83RL197, m83RL195, m83RL198)) + this.f1813X[10]) - 1894007588, 15) + m83RL196;
        int m83RL200 = m83RL(m83RL195, 10);
        int m83RL201 = m83RL(((m83RL196 + m79f4(m83RL199, m83RL197, m83RL200)) + this.f1813X[0]) - 1894007588, 14) + m83RL198;
        int m83RL202 = m83RL(m83RL197, 10);
        int m83RL203 = m83RL(((m83RL198 + m79f4(m83RL201, m83RL199, m83RL202)) + this.f1813X[8]) - 1894007588, 15) + m83RL200;
        int m83RL204 = m83RL(m83RL199, 10);
        int m83RL205 = m83RL(((m83RL200 + m79f4(m83RL203, m83RL201, m83RL204)) + this.f1813X[12]) - 1894007588, 9) + m83RL202;
        int m83RL206 = m83RL(m83RL201, 10);
        int m83RL207 = m83RL(((m83RL202 + m79f4(m83RL205, m83RL203, m83RL206)) + this.f1813X[4]) - 1894007588, 8) + m83RL204;
        int m83RL208 = m83RL(m83RL203, 10);
        int m83RL209 = m83RL(((m83RL204 + m79f4(m83RL207, m83RL205, m83RL208)) + this.f1813X[13]) - 1894007588, 9) + m83RL206;
        int m83RL210 = m83RL(m83RL205, 10);
        int m83RL211 = m83RL(((m83RL206 + m79f4(m83RL209, m83RL207, m83RL210)) + this.f1813X[3]) - 1894007588, 14) + m83RL208;
        int m83RL212 = m83RL(m83RL207, 10);
        int m83RL213 = m83RL(((m83RL208 + m79f4(m83RL211, m83RL209, m83RL212)) + this.f1813X[7]) - 1894007588, 5) + m83RL210;
        int m83RL214 = m83RL(m83RL209, 10);
        int m83RL215 = m83RL(((m83RL210 + m79f4(m83RL213, m83RL211, m83RL214)) + this.f1813X[15]) - 1894007588, 6) + m83RL212;
        int m83RL216 = m83RL(m83RL211, 10);
        int m83RL217 = m83RL(((m83RL212 + m79f4(m83RL215, m83RL213, m83RL216)) + this.f1813X[14]) - 1894007588, 8) + m83RL214;
        int m83RL218 = m83RL(m83RL213, 10);
        int m83RL219 = m83RL(((m83RL214 + m79f4(m83RL217, m83RL215, m83RL218)) + this.f1813X[5]) - 1894007588, 6) + m83RL216;
        int m83RL220 = m83RL(m83RL215, 10);
        int m83RL221 = m83RL(((m83RL216 + m79f4(m83RL219, m83RL217, m83RL220)) + this.f1813X[6]) - 1894007588, 5) + m83RL218;
        int m83RL222 = m83RL(m83RL217, 10);
        int m83RL223 = m83RL(((m83RL218 + m79f4(m83RL221, m83RL219, m83RL222)) + this.f1813X[2]) - 1894007588, 12) + m83RL220;
        int m83RL224 = m83RL(m83RL219, 10);
        int m83RL225 = m83RL(m83RL156 + m81f2(m83RL191, m83RL189, m83RL192) + this.f1813X[8] + 2053994217, 15) + m83RL190;
        int m83RL226 = m83RL(m83RL189, 10);
        int m83RL227 = m83RL(m83RL190 + m81f2(m83RL225, m83RL191, m83RL226) + this.f1813X[6] + 2053994217, 5) + m83RL192;
        int m83RL228 = m83RL(m83RL191, 10);
        int m83RL229 = m83RL(m83RL192 + m81f2(m83RL227, m83RL225, m83RL228) + this.f1813X[4] + 2053994217, 8) + m83RL226;
        int m83RL230 = m83RL(m83RL225, 10);
        int m83RL231 = m83RL(m83RL226 + m81f2(m83RL229, m83RL227, m83RL230) + this.f1813X[1] + 2053994217, 11) + m83RL228;
        int m83RL232 = m83RL(m83RL227, 10);
        int m83RL233 = m83RL(m83RL228 + m81f2(m83RL231, m83RL229, m83RL232) + this.f1813X[3] + 2053994217, 14) + m83RL230;
        int m83RL234 = m83RL(m83RL229, 10);
        int m83RL235 = m83RL(m83RL230 + m81f2(m83RL233, m83RL231, m83RL234) + this.f1813X[11] + 2053994217, 14) + m83RL232;
        int m83RL236 = m83RL(m83RL231, 10);
        int m83RL237 = m83RL(m83RL232 + m81f2(m83RL235, m83RL233, m83RL236) + this.f1813X[15] + 2053994217, 6) + m83RL234;
        int m83RL238 = m83RL(m83RL233, 10);
        int m83RL239 = m83RL(m83RL234 + m81f2(m83RL237, m83RL235, m83RL238) + this.f1813X[0] + 2053994217, 14) + m83RL236;
        int m83RL240 = m83RL(m83RL235, 10);
        int m83RL241 = m83RL(m83RL236 + m81f2(m83RL239, m83RL237, m83RL240) + this.f1813X[5] + 2053994217, 6) + m83RL238;
        int m83RL242 = m83RL(m83RL237, 10);
        int m83RL243 = m83RL(m83RL238 + m81f2(m83RL241, m83RL239, m83RL242) + this.f1813X[12] + 2053994217, 9) + m83RL240;
        int m83RL244 = m83RL(m83RL239, 10);
        int m83RL245 = m83RL(m83RL240 + m81f2(m83RL243, m83RL241, m83RL244) + this.f1813X[2] + 2053994217, 12) + m83RL242;
        int m83RL246 = m83RL(m83RL241, 10);
        int m83RL247 = m83RL(m83RL242 + m81f2(m83RL245, m83RL243, m83RL246) + this.f1813X[13] + 2053994217, 9) + m83RL244;
        int m83RL248 = m83RL(m83RL243, 10);
        int m83RL249 = m83RL(m83RL244 + m81f2(m83RL247, m83RL245, m83RL248) + this.f1813X[9] + 2053994217, 12) + m83RL246;
        int m83RL250 = m83RL(m83RL245, 10);
        int m83RL251 = m83RL(m83RL246 + m81f2(m83RL249, m83RL247, m83RL250) + this.f1813X[7] + 2053994217, 5) + m83RL248;
        int m83RL252 = m83RL(m83RL247, 10);
        int m83RL253 = m83RL(m83RL248 + m81f2(m83RL251, m83RL249, m83RL252) + this.f1813X[10] + 2053994217, 15) + m83RL250;
        int m83RL254 = m83RL(m83RL249, 10);
        int m83RL255 = m83RL(m83RL250 + m81f2(m83RL253, m83RL251, m83RL254) + this.f1813X[14] + 2053994217, 8) + m83RL252;
        int m83RL256 = m83RL(m83RL251, 10);
        int m83RL257 = m83RL(((m83RL220 + m78f5(m83RL223, m83RL253, m83RL224)) + this.f1813X[4]) - 1454113458, 9) + m83RL222;
        int m83RL258 = m83RL(m83RL253, 10);
        int m83RL259 = m83RL(((m83RL222 + m78f5(m83RL257, m83RL223, m83RL258)) + this.f1813X[0]) - 1454113458, 15) + m83RL224;
        int m83RL260 = m83RL(m83RL223, 10);
        int m83RL261 = m83RL(((m83RL224 + m78f5(m83RL259, m83RL257, m83RL260)) + this.f1813X[5]) - 1454113458, 5) + m83RL258;
        int m83RL262 = m83RL(m83RL257, 10);
        int m83RL263 = m83RL(((m83RL258 + m78f5(m83RL261, m83RL259, m83RL262)) + this.f1813X[9]) - 1454113458, 11) + m83RL260;
        int m83RL264 = m83RL(m83RL259, 10);
        int m83RL265 = m83RL(((m83RL260 + m78f5(m83RL263, m83RL261, m83RL264)) + this.f1813X[7]) - 1454113458, 6) + m83RL262;
        int m83RL266 = m83RL(m83RL261, 10);
        int m83RL267 = m83RL(((m83RL262 + m78f5(m83RL265, m83RL263, m83RL266)) + this.f1813X[12]) - 1454113458, 8) + m83RL264;
        int m83RL268 = m83RL(m83RL263, 10);
        int m83RL269 = m83RL(((m83RL264 + m78f5(m83RL267, m83RL265, m83RL268)) + this.f1813X[2]) - 1454113458, 13) + m83RL266;
        int m83RL270 = m83RL(m83RL265, 10);
        int m83RL271 = m83RL(((m83RL266 + m78f5(m83RL269, m83RL267, m83RL270)) + this.f1813X[10]) - 1454113458, 12) + m83RL268;
        int m83RL272 = m83RL(m83RL267, 10);
        int m83RL273 = m83RL(((m83RL268 + m78f5(m83RL271, m83RL269, m83RL272)) + this.f1813X[14]) - 1454113458, 5) + m83RL270;
        int m83RL274 = m83RL(m83RL269, 10);
        int m83RL275 = m83RL(((m83RL270 + m78f5(m83RL273, m83RL271, m83RL274)) + this.f1813X[1]) - 1454113458, 12) + m83RL272;
        int m83RL276 = m83RL(m83RL271, 10);
        int m83RL277 = m83RL(((m83RL272 + m78f5(m83RL275, m83RL273, m83RL276)) + this.f1813X[3]) - 1454113458, 13) + m83RL274;
        int m83RL278 = m83RL(m83RL273, 10);
        int m83RL279 = m83RL(((m83RL274 + m78f5(m83RL277, m83RL275, m83RL278)) + this.f1813X[8]) - 1454113458, 14) + m83RL276;
        int m83RL280 = m83RL(m83RL275, 10);
        int m83RL281 = m83RL(((m83RL276 + m78f5(m83RL279, m83RL277, m83RL280)) + this.f1813X[11]) - 1454113458, 11) + m83RL278;
        int m83RL282 = m83RL(m83RL277, 10);
        int m83RL283 = m83RL(((m83RL278 + m78f5(m83RL281, m83RL279, m83RL282)) + this.f1813X[6]) - 1454113458, 8) + m83RL280;
        int m83RL284 = m83RL(m83RL279, 10);
        int m83RL285 = m83RL(((m83RL280 + m78f5(m83RL283, m83RL281, m83RL284)) + this.f1813X[15]) - 1454113458, 5) + m83RL282;
        int m83RL286 = m83RL(m83RL281, 10);
        int m83RL287 = m83RL(((m83RL282 + m78f5(m83RL285, m83RL283, m83RL286)) + this.f1813X[13]) - 1454113458, 6) + m83RL284;
        int m83RL288 = m83RL(m83RL283, 10);
        int m83RL289 = m83RL(m83RL252 + m82f1(m83RL255, m83RL221, m83RL256) + this.f1813X[12], 8) + m83RL254;
        int m83RL290 = m83RL(m83RL221, 10);
        int m83RL291 = m83RL(m83RL254 + m82f1(m83RL289, m83RL255, m83RL290) + this.f1813X[15], 5) + m83RL256;
        int m83RL292 = m83RL(m83RL255, 10);
        int m83RL293 = m83RL(m83RL256 + m82f1(m83RL291, m83RL289, m83RL292) + this.f1813X[10], 12) + m83RL290;
        int m83RL294 = m83RL(m83RL289, 10);
        int m83RL295 = m83RL(m83RL290 + m82f1(m83RL293, m83RL291, m83RL294) + this.f1813X[4], 9) + m83RL292;
        int m83RL296 = m83RL(m83RL291, 10);
        int m83RL297 = m83RL(m83RL292 + m82f1(m83RL295, m83RL293, m83RL296) + this.f1813X[1], 12) + m83RL294;
        int m83RL298 = m83RL(m83RL293, 10);
        int m83RL299 = m83RL(m83RL294 + m82f1(m83RL297, m83RL295, m83RL298) + this.f1813X[5], 5) + m83RL296;
        int m83RL300 = m83RL(m83RL295, 10);
        int m83RL301 = m83RL(m83RL296 + m82f1(m83RL299, m83RL297, m83RL300) + this.f1813X[8], 14) + m83RL298;
        int m83RL302 = m83RL(m83RL297, 10);
        int m83RL303 = m83RL(m83RL298 + m82f1(m83RL301, m83RL299, m83RL302) + this.f1813X[7], 6) + m83RL300;
        int m83RL304 = m83RL(m83RL299, 10);
        int m83RL305 = m83RL(m83RL300 + m82f1(m83RL303, m83RL301, m83RL304) + this.f1813X[6], 8) + m83RL302;
        int m83RL306 = m83RL(m83RL301, 10);
        int m83RL307 = m83RL(m83RL302 + m82f1(m83RL305, m83RL303, m83RL306) + this.f1813X[2], 13) + m83RL304;
        int m83RL308 = m83RL(m83RL303, 10);
        int m83RL309 = m83RL(m83RL304 + m82f1(m83RL307, m83RL305, m83RL308) + this.f1813X[13], 6) + m83RL306;
        int m83RL310 = m83RL(m83RL305, 10);
        int m83RL311 = m83RL(m83RL306 + m82f1(m83RL309, m83RL307, m83RL310) + this.f1813X[14], 5) + m83RL308;
        int m83RL312 = m83RL(m83RL307, 10);
        int m83RL313 = m83RL(m83RL308 + m82f1(m83RL311, m83RL309, m83RL312) + this.f1813X[0], 15) + m83RL310;
        int m83RL314 = m83RL(m83RL309, 10);
        int m83RL315 = m83RL(m83RL310 + m82f1(m83RL313, m83RL311, m83RL314) + this.f1813X[3], 13) + m83RL312;
        int m83RL316 = m83RL(m83RL311, 10);
        int m83RL317 = m83RL(m83RL312 + m82f1(m83RL315, m83RL313, m83RL316) + this.f1813X[9], 11) + m83RL314;
        int m83RL318 = m83RL(m83RL313, 10);
        int m83RL319 = m83RL(m83RL314 + m82f1(m83RL317, m83RL315, m83RL318) + this.f1813X[11], 11) + m83RL316;
        int m83RL320 = m83RL(m83RL315, 10);
        this.f1803H0 += m83RL284;
        this.f1804H1 += m83RL287;
        this.f1805H2 += m83RL285;
        this.f1806H3 += m83RL288;
        this.f1807H4 += m83RL318;
        this.f1808H5 += m83RL316;
        this.f1809H6 += m83RL319;
        this.f1810H7 += m83RL317;
        this.f1811H8 += m83RL320;
        this.f1812H9 += m83RL286;
        this.xOff = 0;
        int r22 = 0;
        while (true) {
            int[] r32 = this.f1813X;
            if (r22 == r32.length) {
                return;
            }
            r32[r22] = 0;
            r22++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] r0 = this.f1813X;
        r0[14] = (int) ((-1) & j);
        r0[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r8) {
        int[] r0 = this.f1813X;
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
        this.f1803H0 = 1732584193;
        this.f1804H1 = -271733879;
        this.f1805H2 = -1732584194;
        this.f1806H3 = 271733878;
        this.f1807H4 = -1009589776;
        this.f1808H5 = 1985229328;
        this.f1809H6 = -19088744;
        this.f1810H7 = -1985229329;
        this.f1811H8 = 19088743;
        this.f1812H9 = 1009589775;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1813X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        doCopy((RIPEMD320Digest) memoable);
    }
}
