package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class RIPEMD160Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: H0 */
    private int f1788H0;

    /* renamed from: H1 */
    private int f1789H1;

    /* renamed from: H2 */
    private int f1790H2;

    /* renamed from: H3 */
    private int f1791H3;

    /* renamed from: H4 */
    private int f1792H4;

    /* renamed from: X */
    private int[] f1793X;
    private int xOff;

    public RIPEMD160Digest() {
        this.f1793X = new int[16];
        reset();
    }

    public RIPEMD160Digest(RIPEMD160Digest rIPEMD160Digest) {
        super(rIPEMD160Digest);
        this.f1793X = new int[16];
        copyIn(rIPEMD160Digest);
    }

    /* renamed from: RL */
    private int m98RL(int r2, int r3) {
        return (r2 >>> (32 - r3)) | (r2 << r3);
    }

    private void copyIn(RIPEMD160Digest rIPEMD160Digest) {
        super.copyIn((GeneralDigest) rIPEMD160Digest);
        this.f1788H0 = rIPEMD160Digest.f1788H0;
        this.f1789H1 = rIPEMD160Digest.f1789H1;
        this.f1790H2 = rIPEMD160Digest.f1790H2;
        this.f1791H3 = rIPEMD160Digest.f1791H3;
        this.f1792H4 = rIPEMD160Digest.f1792H4;
        int[] r0 = rIPEMD160Digest.f1793X;
        System.arraycopy(r0, 0, this.f1793X, 0, r0.length);
        this.xOff = rIPEMD160Digest.xOff;
    }

    /* renamed from: f1 */
    private int m97f1(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    /* renamed from: f2 */
    private int m96f2(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: f3 */
    private int m95f3(int r1, int r2, int r3) {
        return (r1 | (~r2)) ^ r3;
    }

    /* renamed from: f4 */
    private int m94f4(int r1, int r2, int r3) {
        return (r1 & r3) | (r2 & (~r3));
    }

    /* renamed from: f5 */
    private int m93f5(int r1, int r2, int r3) {
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
        return new RIPEMD160Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        unpackWord(this.f1788H0, bArr, r4);
        unpackWord(this.f1789H1, bArr, r4 + 4);
        unpackWord(this.f1790H2, bArr, r4 + 8);
        unpackWord(this.f1791H3, bArr, r4 + 12);
        unpackWord(this.f1792H4, bArr, r4 + 16);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD160";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int r1 = this.f1788H0;
        int r2 = this.f1789H1;
        int r3 = this.f1790H2;
        int r4 = this.f1791H3;
        int r5 = this.f1792H4;
        int m98RL = m98RL(m97f1(r2, r3, r4) + r1 + this.f1793X[0], 11) + r5;
        int m98RL2 = m98RL(r3, 10);
        int m98RL3 = m98RL(m97f1(m98RL, r2, m98RL2) + r5 + this.f1793X[1], 14) + r4;
        int m98RL4 = m98RL(r2, 10);
        int m98RL5 = m98RL(m97f1(m98RL3, m98RL, m98RL4) + r4 + this.f1793X[2], 15) + m98RL2;
        int m98RL6 = m98RL(m98RL, 10);
        int m98RL7 = m98RL(m98RL2 + m97f1(m98RL5, m98RL3, m98RL6) + this.f1793X[3], 12) + m98RL4;
        int m98RL8 = m98RL(m98RL3, 10);
        int m98RL9 = m98RL(m98RL4 + m97f1(m98RL7, m98RL5, m98RL8) + this.f1793X[4], 5) + m98RL6;
        int m98RL10 = m98RL(m98RL5, 10);
        int m98RL11 = m98RL(m98RL6 + m97f1(m98RL9, m98RL7, m98RL10) + this.f1793X[5], 8) + m98RL8;
        int m98RL12 = m98RL(m98RL7, 10);
        int m98RL13 = m98RL(m98RL8 + m97f1(m98RL11, m98RL9, m98RL12) + this.f1793X[6], 7) + m98RL10;
        int m98RL14 = m98RL(m98RL9, 10);
        int m98RL15 = m98RL(m98RL10 + m97f1(m98RL13, m98RL11, m98RL14) + this.f1793X[7], 9) + m98RL12;
        int m98RL16 = m98RL(m98RL11, 10);
        int m98RL17 = m98RL(m98RL12 + m97f1(m98RL15, m98RL13, m98RL16) + this.f1793X[8], 11) + m98RL14;
        int m98RL18 = m98RL(m98RL13, 10);
        int m98RL19 = m98RL(m98RL14 + m97f1(m98RL17, m98RL15, m98RL18) + this.f1793X[9], 13) + m98RL16;
        int m98RL20 = m98RL(m98RL15, 10);
        int m98RL21 = m98RL(m98RL16 + m97f1(m98RL19, m98RL17, m98RL20) + this.f1793X[10], 14) + m98RL18;
        int m98RL22 = m98RL(m98RL17, 10);
        int m98RL23 = m98RL(m98RL18 + m97f1(m98RL21, m98RL19, m98RL22) + this.f1793X[11], 15) + m98RL20;
        int m98RL24 = m98RL(m98RL19, 10);
        int m98RL25 = m98RL(m98RL20 + m97f1(m98RL23, m98RL21, m98RL24) + this.f1793X[12], 6) + m98RL22;
        int m98RL26 = m98RL(m98RL21, 10);
        int m98RL27 = m98RL(m98RL22 + m97f1(m98RL25, m98RL23, m98RL26) + this.f1793X[13], 7) + m98RL24;
        int m98RL28 = m98RL(m98RL23, 10);
        int m98RL29 = m98RL(m98RL24 + m97f1(m98RL27, m98RL25, m98RL28) + this.f1793X[14], 9) + m98RL26;
        int m98RL30 = m98RL(m98RL25, 10);
        int m98RL31 = m98RL(m98RL26 + m97f1(m98RL29, m98RL27, m98RL30) + this.f1793X[15], 8) + m98RL28;
        int m98RL32 = m98RL(m98RL27, 10);
        int m98RL33 = m98RL(r1 + m93f5(r2, r3, r4) + this.f1793X[5] + 1352829926, 8) + r5;
        int m98RL34 = m98RL(r3, 10);
        int m98RL35 = m98RL(r5 + m93f5(m98RL33, r2, m98RL34) + this.f1793X[14] + 1352829926, 9) + r4;
        int m98RL36 = m98RL(r2, 10);
        int m98RL37 = m98RL(r4 + m93f5(m98RL35, m98RL33, m98RL36) + this.f1793X[7] + 1352829926, 9) + m98RL34;
        int m98RL38 = m98RL(m98RL33, 10);
        int m98RL39 = m98RL(m98RL34 + m93f5(m98RL37, m98RL35, m98RL38) + this.f1793X[0] + 1352829926, 11) + m98RL36;
        int m98RL40 = m98RL(m98RL35, 10);
        int m98RL41 = m98RL(m98RL36 + m93f5(m98RL39, m98RL37, m98RL40) + this.f1793X[9] + 1352829926, 13) + m98RL38;
        int m98RL42 = m98RL(m98RL37, 10);
        int m98RL43 = m98RL(m98RL38 + m93f5(m98RL41, m98RL39, m98RL42) + this.f1793X[2] + 1352829926, 15) + m98RL40;
        int m98RL44 = m98RL(m98RL39, 10);
        int m98RL45 = m98RL(m98RL40 + m93f5(m98RL43, m98RL41, m98RL44) + this.f1793X[11] + 1352829926, 15) + m98RL42;
        int m98RL46 = m98RL(m98RL41, 10);
        int m98RL47 = m98RL(m98RL42 + m93f5(m98RL45, m98RL43, m98RL46) + this.f1793X[4] + 1352829926, 5) + m98RL44;
        int m98RL48 = m98RL(m98RL43, 10);
        int m98RL49 = m98RL(m98RL44 + m93f5(m98RL47, m98RL45, m98RL48) + this.f1793X[13] + 1352829926, 7) + m98RL46;
        int m98RL50 = m98RL(m98RL45, 10);
        int m98RL51 = m98RL(m98RL46 + m93f5(m98RL49, m98RL47, m98RL50) + this.f1793X[6] + 1352829926, 7) + m98RL48;
        int m98RL52 = m98RL(m98RL47, 10);
        int m98RL53 = m98RL(m98RL48 + m93f5(m98RL51, m98RL49, m98RL52) + this.f1793X[15] + 1352829926, 8) + m98RL50;
        int m98RL54 = m98RL(m98RL49, 10);
        int m98RL55 = m98RL(m98RL50 + m93f5(m98RL53, m98RL51, m98RL54) + this.f1793X[8] + 1352829926, 11) + m98RL52;
        int m98RL56 = m98RL(m98RL51, 10);
        int m98RL57 = m98RL(m98RL52 + m93f5(m98RL55, m98RL53, m98RL56) + this.f1793X[1] + 1352829926, 14) + m98RL54;
        int m98RL58 = m98RL(m98RL53, 10);
        int m98RL59 = m98RL(m98RL54 + m93f5(m98RL57, m98RL55, m98RL58) + this.f1793X[10] + 1352829926, 14) + m98RL56;
        int m98RL60 = m98RL(m98RL55, 10);
        int m98RL61 = m98RL(m98RL56 + m93f5(m98RL59, m98RL57, m98RL60) + this.f1793X[3] + 1352829926, 12) + m98RL58;
        int m98RL62 = m98RL(m98RL57, 10);
        int m98RL63 = m98RL(m98RL58 + m93f5(m98RL61, m98RL59, m98RL62) + this.f1793X[12] + 1352829926, 6) + m98RL60;
        int m98RL64 = m98RL(m98RL59, 10);
        int m98RL65 = m98RL(m98RL28 + m96f2(m98RL31, m98RL29, m98RL32) + this.f1793X[7] + 1518500249, 7) + m98RL30;
        int m98RL66 = m98RL(m98RL29, 10);
        int m98RL67 = m98RL(m98RL30 + m96f2(m98RL65, m98RL31, m98RL66) + this.f1793X[4] + 1518500249, 6) + m98RL32;
        int m98RL68 = m98RL(m98RL31, 10);
        int m98RL69 = m98RL(m98RL32 + m96f2(m98RL67, m98RL65, m98RL68) + this.f1793X[13] + 1518500249, 8) + m98RL66;
        int m98RL70 = m98RL(m98RL65, 10);
        int m98RL71 = m98RL(m98RL66 + m96f2(m98RL69, m98RL67, m98RL70) + this.f1793X[1] + 1518500249, 13) + m98RL68;
        int m98RL72 = m98RL(m98RL67, 10);
        int m98RL73 = m98RL(m98RL68 + m96f2(m98RL71, m98RL69, m98RL72) + this.f1793X[10] + 1518500249, 11) + m98RL70;
        int m98RL74 = m98RL(m98RL69, 10);
        int m98RL75 = m98RL(m98RL70 + m96f2(m98RL73, m98RL71, m98RL74) + this.f1793X[6] + 1518500249, 9) + m98RL72;
        int m98RL76 = m98RL(m98RL71, 10);
        int m98RL77 = m98RL(m98RL72 + m96f2(m98RL75, m98RL73, m98RL76) + this.f1793X[15] + 1518500249, 7) + m98RL74;
        int m98RL78 = m98RL(m98RL73, 10);
        int m98RL79 = m98RL(m98RL74 + m96f2(m98RL77, m98RL75, m98RL78) + this.f1793X[3] + 1518500249, 15) + m98RL76;
        int m98RL80 = m98RL(m98RL75, 10);
        int m98RL81 = m98RL(m98RL76 + m96f2(m98RL79, m98RL77, m98RL80) + this.f1793X[12] + 1518500249, 7) + m98RL78;
        int m98RL82 = m98RL(m98RL77, 10);
        int m98RL83 = m98RL(m98RL78 + m96f2(m98RL81, m98RL79, m98RL82) + this.f1793X[0] + 1518500249, 12) + m98RL80;
        int m98RL84 = m98RL(m98RL79, 10);
        int m98RL85 = m98RL(m98RL80 + m96f2(m98RL83, m98RL81, m98RL84) + this.f1793X[9] + 1518500249, 15) + m98RL82;
        int m98RL86 = m98RL(m98RL81, 10);
        int m98RL87 = m98RL(m98RL82 + m96f2(m98RL85, m98RL83, m98RL86) + this.f1793X[5] + 1518500249, 9) + m98RL84;
        int m98RL88 = m98RL(m98RL83, 10);
        int m98RL89 = m98RL(m98RL84 + m96f2(m98RL87, m98RL85, m98RL88) + this.f1793X[2] + 1518500249, 11) + m98RL86;
        int m98RL90 = m98RL(m98RL85, 10);
        int m98RL91 = m98RL(m98RL86 + m96f2(m98RL89, m98RL87, m98RL90) + this.f1793X[14] + 1518500249, 7) + m98RL88;
        int m98RL92 = m98RL(m98RL87, 10);
        int m98RL93 = m98RL(m98RL88 + m96f2(m98RL91, m98RL89, m98RL92) + this.f1793X[11] + 1518500249, 13) + m98RL90;
        int m98RL94 = m98RL(m98RL89, 10);
        int m98RL95 = m98RL(m98RL90 + m96f2(m98RL93, m98RL91, m98RL94) + this.f1793X[8] + 1518500249, 12) + m98RL92;
        int m98RL96 = m98RL(m98RL91, 10);
        int m98RL97 = m98RL(m98RL60 + m94f4(m98RL63, m98RL61, m98RL64) + this.f1793X[6] + 1548603684, 9) + m98RL62;
        int m98RL98 = m98RL(m98RL61, 10);
        int m98RL99 = m98RL(m98RL62 + m94f4(m98RL97, m98RL63, m98RL98) + this.f1793X[11] + 1548603684, 13) + m98RL64;
        int m98RL100 = m98RL(m98RL63, 10);
        int m98RL101 = m98RL(m98RL64 + m94f4(m98RL99, m98RL97, m98RL100) + this.f1793X[3] + 1548603684, 15) + m98RL98;
        int m98RL102 = m98RL(m98RL97, 10);
        int m98RL103 = m98RL(m98RL98 + m94f4(m98RL101, m98RL99, m98RL102) + this.f1793X[7] + 1548603684, 7) + m98RL100;
        int m98RL104 = m98RL(m98RL99, 10);
        int m98RL105 = m98RL(m98RL100 + m94f4(m98RL103, m98RL101, m98RL104) + this.f1793X[0] + 1548603684, 12) + m98RL102;
        int m98RL106 = m98RL(m98RL101, 10);
        int m98RL107 = m98RL(m98RL102 + m94f4(m98RL105, m98RL103, m98RL106) + this.f1793X[13] + 1548603684, 8) + m98RL104;
        int m98RL108 = m98RL(m98RL103, 10);
        int m98RL109 = m98RL(m98RL104 + m94f4(m98RL107, m98RL105, m98RL108) + this.f1793X[5] + 1548603684, 9) + m98RL106;
        int m98RL110 = m98RL(m98RL105, 10);
        int m98RL111 = m98RL(m98RL106 + m94f4(m98RL109, m98RL107, m98RL110) + this.f1793X[10] + 1548603684, 11) + m98RL108;
        int m98RL112 = m98RL(m98RL107, 10);
        int m98RL113 = m98RL(m98RL108 + m94f4(m98RL111, m98RL109, m98RL112) + this.f1793X[14] + 1548603684, 7) + m98RL110;
        int m98RL114 = m98RL(m98RL109, 10);
        int m98RL115 = m98RL(m98RL110 + m94f4(m98RL113, m98RL111, m98RL114) + this.f1793X[15] + 1548603684, 7) + m98RL112;
        int m98RL116 = m98RL(m98RL111, 10);
        int m98RL117 = m98RL(m98RL112 + m94f4(m98RL115, m98RL113, m98RL116) + this.f1793X[8] + 1548603684, 12) + m98RL114;
        int m98RL118 = m98RL(m98RL113, 10);
        int m98RL119 = m98RL(m98RL114 + m94f4(m98RL117, m98RL115, m98RL118) + this.f1793X[12] + 1548603684, 7) + m98RL116;
        int m98RL120 = m98RL(m98RL115, 10);
        int m98RL121 = m98RL(m98RL116 + m94f4(m98RL119, m98RL117, m98RL120) + this.f1793X[4] + 1548603684, 6) + m98RL118;
        int m98RL122 = m98RL(m98RL117, 10);
        int m98RL123 = m98RL(m98RL118 + m94f4(m98RL121, m98RL119, m98RL122) + this.f1793X[9] + 1548603684, 15) + m98RL120;
        int m98RL124 = m98RL(m98RL119, 10);
        int m98RL125 = m98RL(m98RL120 + m94f4(m98RL123, m98RL121, m98RL124) + this.f1793X[1] + 1548603684, 13) + m98RL122;
        int m98RL126 = m98RL(m98RL121, 10);
        int m98RL127 = m98RL(m98RL122 + m94f4(m98RL125, m98RL123, m98RL126) + this.f1793X[2] + 1548603684, 11) + m98RL124;
        int m98RL128 = m98RL(m98RL123, 10);
        int m98RL129 = m98RL(m98RL92 + m95f3(m98RL95, m98RL93, m98RL96) + this.f1793X[3] + 1859775393, 11) + m98RL94;
        int m98RL130 = m98RL(m98RL93, 10);
        int m98RL131 = m98RL(m98RL94 + m95f3(m98RL129, m98RL95, m98RL130) + this.f1793X[10] + 1859775393, 13) + m98RL96;
        int m98RL132 = m98RL(m98RL95, 10);
        int m98RL133 = m98RL(m98RL96 + m95f3(m98RL131, m98RL129, m98RL132) + this.f1793X[14] + 1859775393, 6) + m98RL130;
        int m98RL134 = m98RL(m98RL129, 10);
        int m98RL135 = m98RL(m98RL130 + m95f3(m98RL133, m98RL131, m98RL134) + this.f1793X[4] + 1859775393, 7) + m98RL132;
        int m98RL136 = m98RL(m98RL131, 10);
        int m98RL137 = m98RL(m98RL132 + m95f3(m98RL135, m98RL133, m98RL136) + this.f1793X[9] + 1859775393, 14) + m98RL134;
        int m98RL138 = m98RL(m98RL133, 10);
        int m98RL139 = m98RL(m98RL134 + m95f3(m98RL137, m98RL135, m98RL138) + this.f1793X[15] + 1859775393, 9) + m98RL136;
        int m98RL140 = m98RL(m98RL135, 10);
        int m98RL141 = m98RL(m98RL136 + m95f3(m98RL139, m98RL137, m98RL140) + this.f1793X[8] + 1859775393, 13) + m98RL138;
        int m98RL142 = m98RL(m98RL137, 10);
        int m98RL143 = m98RL(m98RL138 + m95f3(m98RL141, m98RL139, m98RL142) + this.f1793X[1] + 1859775393, 15) + m98RL140;
        int m98RL144 = m98RL(m98RL139, 10);
        int m98RL145 = m98RL(m98RL140 + m95f3(m98RL143, m98RL141, m98RL144) + this.f1793X[2] + 1859775393, 14) + m98RL142;
        int m98RL146 = m98RL(m98RL141, 10);
        int m98RL147 = m98RL(m98RL142 + m95f3(m98RL145, m98RL143, m98RL146) + this.f1793X[7] + 1859775393, 8) + m98RL144;
        int m98RL148 = m98RL(m98RL143, 10);
        int m98RL149 = m98RL(m98RL144 + m95f3(m98RL147, m98RL145, m98RL148) + this.f1793X[0] + 1859775393, 13) + m98RL146;
        int m98RL150 = m98RL(m98RL145, 10);
        int m98RL151 = m98RL(m98RL146 + m95f3(m98RL149, m98RL147, m98RL150) + this.f1793X[6] + 1859775393, 6) + m98RL148;
        int m98RL152 = m98RL(m98RL147, 10);
        int m98RL153 = m98RL(m98RL148 + m95f3(m98RL151, m98RL149, m98RL152) + this.f1793X[13] + 1859775393, 5) + m98RL150;
        int m98RL154 = m98RL(m98RL149, 10);
        int m98RL155 = m98RL(m98RL150 + m95f3(m98RL153, m98RL151, m98RL154) + this.f1793X[11] + 1859775393, 12) + m98RL152;
        int m98RL156 = m98RL(m98RL151, 10);
        int m98RL157 = m98RL(m98RL152 + m95f3(m98RL155, m98RL153, m98RL156) + this.f1793X[5] + 1859775393, 7) + m98RL154;
        int m98RL158 = m98RL(m98RL153, 10);
        int m98RL159 = m98RL(m98RL154 + m95f3(m98RL157, m98RL155, m98RL158) + this.f1793X[12] + 1859775393, 5) + m98RL156;
        int m98RL160 = m98RL(m98RL155, 10);
        int m98RL161 = m98RL(m98RL124 + m95f3(m98RL127, m98RL125, m98RL128) + this.f1793X[15] + 1836072691, 9) + m98RL126;
        int m98RL162 = m98RL(m98RL125, 10);
        int m98RL163 = m98RL(m98RL126 + m95f3(m98RL161, m98RL127, m98RL162) + this.f1793X[5] + 1836072691, 7) + m98RL128;
        int m98RL164 = m98RL(m98RL127, 10);
        int m98RL165 = m98RL(m98RL128 + m95f3(m98RL163, m98RL161, m98RL164) + this.f1793X[1] + 1836072691, 15) + m98RL162;
        int m98RL166 = m98RL(m98RL161, 10);
        int m98RL167 = m98RL(m98RL162 + m95f3(m98RL165, m98RL163, m98RL166) + this.f1793X[3] + 1836072691, 11) + m98RL164;
        int m98RL168 = m98RL(m98RL163, 10);
        int m98RL169 = m98RL(m98RL164 + m95f3(m98RL167, m98RL165, m98RL168) + this.f1793X[7] + 1836072691, 8) + m98RL166;
        int m98RL170 = m98RL(m98RL165, 10);
        int m98RL171 = m98RL(m98RL166 + m95f3(m98RL169, m98RL167, m98RL170) + this.f1793X[14] + 1836072691, 6) + m98RL168;
        int m98RL172 = m98RL(m98RL167, 10);
        int m98RL173 = m98RL(m98RL168 + m95f3(m98RL171, m98RL169, m98RL172) + this.f1793X[6] + 1836072691, 6) + m98RL170;
        int m98RL174 = m98RL(m98RL169, 10);
        int m98RL175 = m98RL(m98RL170 + m95f3(m98RL173, m98RL171, m98RL174) + this.f1793X[9] + 1836072691, 14) + m98RL172;
        int m98RL176 = m98RL(m98RL171, 10);
        int m98RL177 = m98RL(m98RL172 + m95f3(m98RL175, m98RL173, m98RL176) + this.f1793X[11] + 1836072691, 12) + m98RL174;
        int m98RL178 = m98RL(m98RL173, 10);
        int m98RL179 = m98RL(m98RL174 + m95f3(m98RL177, m98RL175, m98RL178) + this.f1793X[8] + 1836072691, 13) + m98RL176;
        int m98RL180 = m98RL(m98RL175, 10);
        int m98RL181 = m98RL(m98RL176 + m95f3(m98RL179, m98RL177, m98RL180) + this.f1793X[12] + 1836072691, 5) + m98RL178;
        int m98RL182 = m98RL(m98RL177, 10);
        int m98RL183 = m98RL(m98RL178 + m95f3(m98RL181, m98RL179, m98RL182) + this.f1793X[2] + 1836072691, 14) + m98RL180;
        int m98RL184 = m98RL(m98RL179, 10);
        int m98RL185 = m98RL(m98RL180 + m95f3(m98RL183, m98RL181, m98RL184) + this.f1793X[10] + 1836072691, 13) + m98RL182;
        int m98RL186 = m98RL(m98RL181, 10);
        int m98RL187 = m98RL(m98RL182 + m95f3(m98RL185, m98RL183, m98RL186) + this.f1793X[0] + 1836072691, 13) + m98RL184;
        int m98RL188 = m98RL(m98RL183, 10);
        int m98RL189 = m98RL(m98RL184 + m95f3(m98RL187, m98RL185, m98RL188) + this.f1793X[4] + 1836072691, 7) + m98RL186;
        int m98RL190 = m98RL(m98RL185, 10);
        int m98RL191 = m98RL(m98RL186 + m95f3(m98RL189, m98RL187, m98RL190) + this.f1793X[13] + 1836072691, 5) + m98RL188;
        int m98RL192 = m98RL(m98RL187, 10);
        int m98RL193 = m98RL(((m98RL156 + m94f4(m98RL159, m98RL157, m98RL160)) + this.f1793X[1]) - 1894007588, 11) + m98RL158;
        int m98RL194 = m98RL(m98RL157, 10);
        int m98RL195 = m98RL(((m98RL158 + m94f4(m98RL193, m98RL159, m98RL194)) + this.f1793X[9]) - 1894007588, 12) + m98RL160;
        int m98RL196 = m98RL(m98RL159, 10);
        int m98RL197 = m98RL(((m98RL160 + m94f4(m98RL195, m98RL193, m98RL196)) + this.f1793X[11]) - 1894007588, 14) + m98RL194;
        int m98RL198 = m98RL(m98RL193, 10);
        int m98RL199 = m98RL(((m98RL194 + m94f4(m98RL197, m98RL195, m98RL198)) + this.f1793X[10]) - 1894007588, 15) + m98RL196;
        int m98RL200 = m98RL(m98RL195, 10);
        int m98RL201 = m98RL(((m98RL196 + m94f4(m98RL199, m98RL197, m98RL200)) + this.f1793X[0]) - 1894007588, 14) + m98RL198;
        int m98RL202 = m98RL(m98RL197, 10);
        int m98RL203 = m98RL(((m98RL198 + m94f4(m98RL201, m98RL199, m98RL202)) + this.f1793X[8]) - 1894007588, 15) + m98RL200;
        int m98RL204 = m98RL(m98RL199, 10);
        int m98RL205 = m98RL(((m98RL200 + m94f4(m98RL203, m98RL201, m98RL204)) + this.f1793X[12]) - 1894007588, 9) + m98RL202;
        int m98RL206 = m98RL(m98RL201, 10);
        int m98RL207 = m98RL(((m98RL202 + m94f4(m98RL205, m98RL203, m98RL206)) + this.f1793X[4]) - 1894007588, 8) + m98RL204;
        int m98RL208 = m98RL(m98RL203, 10);
        int m98RL209 = m98RL(((m98RL204 + m94f4(m98RL207, m98RL205, m98RL208)) + this.f1793X[13]) - 1894007588, 9) + m98RL206;
        int m98RL210 = m98RL(m98RL205, 10);
        int m98RL211 = m98RL(((m98RL206 + m94f4(m98RL209, m98RL207, m98RL210)) + this.f1793X[3]) - 1894007588, 14) + m98RL208;
        int m98RL212 = m98RL(m98RL207, 10);
        int m98RL213 = m98RL(((m98RL208 + m94f4(m98RL211, m98RL209, m98RL212)) + this.f1793X[7]) - 1894007588, 5) + m98RL210;
        int m98RL214 = m98RL(m98RL209, 10);
        int m98RL215 = m98RL(((m98RL210 + m94f4(m98RL213, m98RL211, m98RL214)) + this.f1793X[15]) - 1894007588, 6) + m98RL212;
        int m98RL216 = m98RL(m98RL211, 10);
        int m98RL217 = m98RL(((m98RL212 + m94f4(m98RL215, m98RL213, m98RL216)) + this.f1793X[14]) - 1894007588, 8) + m98RL214;
        int m98RL218 = m98RL(m98RL213, 10);
        int m98RL219 = m98RL(((m98RL214 + m94f4(m98RL217, m98RL215, m98RL218)) + this.f1793X[5]) - 1894007588, 6) + m98RL216;
        int m98RL220 = m98RL(m98RL215, 10);
        int m98RL221 = m98RL(((m98RL216 + m94f4(m98RL219, m98RL217, m98RL220)) + this.f1793X[6]) - 1894007588, 5) + m98RL218;
        int m98RL222 = m98RL(m98RL217, 10);
        int m98RL223 = m98RL(((m98RL218 + m94f4(m98RL221, m98RL219, m98RL222)) + this.f1793X[2]) - 1894007588, 12) + m98RL220;
        int m98RL224 = m98RL(m98RL219, 10);
        int m98RL225 = m98RL(m98RL188 + m96f2(m98RL191, m98RL189, m98RL192) + this.f1793X[8] + 2053994217, 15) + m98RL190;
        int m98RL226 = m98RL(m98RL189, 10);
        int m98RL227 = m98RL(m98RL190 + m96f2(m98RL225, m98RL191, m98RL226) + this.f1793X[6] + 2053994217, 5) + m98RL192;
        int m98RL228 = m98RL(m98RL191, 10);
        int m98RL229 = m98RL(m98RL192 + m96f2(m98RL227, m98RL225, m98RL228) + this.f1793X[4] + 2053994217, 8) + m98RL226;
        int m98RL230 = m98RL(m98RL225, 10);
        int m98RL231 = m98RL(m98RL226 + m96f2(m98RL229, m98RL227, m98RL230) + this.f1793X[1] + 2053994217, 11) + m98RL228;
        int m98RL232 = m98RL(m98RL227, 10);
        int m98RL233 = m98RL(m98RL228 + m96f2(m98RL231, m98RL229, m98RL232) + this.f1793X[3] + 2053994217, 14) + m98RL230;
        int m98RL234 = m98RL(m98RL229, 10);
        int m98RL235 = m98RL(m98RL230 + m96f2(m98RL233, m98RL231, m98RL234) + this.f1793X[11] + 2053994217, 14) + m98RL232;
        int m98RL236 = m98RL(m98RL231, 10);
        int m98RL237 = m98RL(m98RL232 + m96f2(m98RL235, m98RL233, m98RL236) + this.f1793X[15] + 2053994217, 6) + m98RL234;
        int m98RL238 = m98RL(m98RL233, 10);
        int m98RL239 = m98RL(m98RL234 + m96f2(m98RL237, m98RL235, m98RL238) + this.f1793X[0] + 2053994217, 14) + m98RL236;
        int m98RL240 = m98RL(m98RL235, 10);
        int m98RL241 = m98RL(m98RL236 + m96f2(m98RL239, m98RL237, m98RL240) + this.f1793X[5] + 2053994217, 6) + m98RL238;
        int m98RL242 = m98RL(m98RL237, 10);
        int m98RL243 = m98RL(m98RL238 + m96f2(m98RL241, m98RL239, m98RL242) + this.f1793X[12] + 2053994217, 9) + m98RL240;
        int m98RL244 = m98RL(m98RL239, 10);
        int m98RL245 = m98RL(m98RL240 + m96f2(m98RL243, m98RL241, m98RL244) + this.f1793X[2] + 2053994217, 12) + m98RL242;
        int m98RL246 = m98RL(m98RL241, 10);
        int m98RL247 = m98RL(m98RL242 + m96f2(m98RL245, m98RL243, m98RL246) + this.f1793X[13] + 2053994217, 9) + m98RL244;
        int m98RL248 = m98RL(m98RL243, 10);
        int m98RL249 = m98RL(m98RL244 + m96f2(m98RL247, m98RL245, m98RL248) + this.f1793X[9] + 2053994217, 12) + m98RL246;
        int m98RL250 = m98RL(m98RL245, 10);
        int m98RL251 = m98RL(m98RL246 + m96f2(m98RL249, m98RL247, m98RL250) + this.f1793X[7] + 2053994217, 5) + m98RL248;
        int m98RL252 = m98RL(m98RL247, 10);
        int m98RL253 = m98RL(m98RL248 + m96f2(m98RL251, m98RL249, m98RL252) + this.f1793X[10] + 2053994217, 15) + m98RL250;
        int m98RL254 = m98RL(m98RL249, 10);
        int m98RL255 = m98RL(m98RL250 + m96f2(m98RL253, m98RL251, m98RL254) + this.f1793X[14] + 2053994217, 8) + m98RL252;
        int m98RL256 = m98RL(m98RL251, 10);
        int m98RL257 = m98RL(((m98RL220 + m93f5(m98RL223, m98RL221, m98RL224)) + this.f1793X[4]) - 1454113458, 9) + m98RL222;
        int m98RL258 = m98RL(m98RL221, 10);
        int m98RL259 = m98RL(((m98RL222 + m93f5(m98RL257, m98RL223, m98RL258)) + this.f1793X[0]) - 1454113458, 15) + m98RL224;
        int m98RL260 = m98RL(m98RL223, 10);
        int m98RL261 = m98RL(((m98RL224 + m93f5(m98RL259, m98RL257, m98RL260)) + this.f1793X[5]) - 1454113458, 5) + m98RL258;
        int m98RL262 = m98RL(m98RL257, 10);
        int m98RL263 = m98RL(((m98RL258 + m93f5(m98RL261, m98RL259, m98RL262)) + this.f1793X[9]) - 1454113458, 11) + m98RL260;
        int m98RL264 = m98RL(m98RL259, 10);
        int m98RL265 = m98RL(((m98RL260 + m93f5(m98RL263, m98RL261, m98RL264)) + this.f1793X[7]) - 1454113458, 6) + m98RL262;
        int m98RL266 = m98RL(m98RL261, 10);
        int m98RL267 = m98RL(((m98RL262 + m93f5(m98RL265, m98RL263, m98RL266)) + this.f1793X[12]) - 1454113458, 8) + m98RL264;
        int m98RL268 = m98RL(m98RL263, 10);
        int m98RL269 = m98RL(((m98RL264 + m93f5(m98RL267, m98RL265, m98RL268)) + this.f1793X[2]) - 1454113458, 13) + m98RL266;
        int m98RL270 = m98RL(m98RL265, 10);
        int m98RL271 = m98RL(((m98RL266 + m93f5(m98RL269, m98RL267, m98RL270)) + this.f1793X[10]) - 1454113458, 12) + m98RL268;
        int m98RL272 = m98RL(m98RL267, 10);
        int m98RL273 = m98RL(((m98RL268 + m93f5(m98RL271, m98RL269, m98RL272)) + this.f1793X[14]) - 1454113458, 5) + m98RL270;
        int m98RL274 = m98RL(m98RL269, 10);
        int m98RL275 = m98RL(((m98RL270 + m93f5(m98RL273, m98RL271, m98RL274)) + this.f1793X[1]) - 1454113458, 12) + m98RL272;
        int m98RL276 = m98RL(m98RL271, 10);
        int m98RL277 = m98RL(((m98RL272 + m93f5(m98RL275, m98RL273, m98RL276)) + this.f1793X[3]) - 1454113458, 13) + m98RL274;
        int m98RL278 = m98RL(m98RL273, 10);
        int m98RL279 = m98RL(((m98RL274 + m93f5(m98RL277, m98RL275, m98RL278)) + this.f1793X[8]) - 1454113458, 14) + m98RL276;
        int m98RL280 = m98RL(m98RL275, 10);
        int m98RL281 = m98RL(((m98RL276 + m93f5(m98RL279, m98RL277, m98RL280)) + this.f1793X[11]) - 1454113458, 11) + m98RL278;
        int m98RL282 = m98RL(m98RL277, 10);
        int m98RL283 = m98RL(((m98RL278 + m93f5(m98RL281, m98RL279, m98RL282)) + this.f1793X[6]) - 1454113458, 8) + m98RL280;
        int m98RL284 = m98RL(m98RL279, 10);
        int m98RL285 = m98RL(((m98RL280 + m93f5(m98RL283, m98RL281, m98RL284)) + this.f1793X[15]) - 1454113458, 5) + m98RL282;
        int m98RL286 = m98RL(m98RL281, 10);
        int m98RL287 = m98RL(m98RL283, 10);
        int m98RL288 = m98RL(m98RL252 + m97f1(m98RL255, m98RL253, m98RL256) + this.f1793X[12], 8) + m98RL254;
        int m98RL289 = m98RL(m98RL253, 10);
        int m98RL290 = m98RL(m98RL254 + m97f1(m98RL288, m98RL255, m98RL289) + this.f1793X[15], 5) + m98RL256;
        int m98RL291 = m98RL(m98RL255, 10);
        int m98RL292 = m98RL(m98RL256 + m97f1(m98RL290, m98RL288, m98RL291) + this.f1793X[10], 12) + m98RL289;
        int m98RL293 = m98RL(m98RL288, 10);
        int m98RL294 = m98RL(m98RL289 + m97f1(m98RL292, m98RL290, m98RL293) + this.f1793X[4], 9) + m98RL291;
        int m98RL295 = m98RL(m98RL290, 10);
        int m98RL296 = m98RL(m98RL291 + m97f1(m98RL294, m98RL292, m98RL295) + this.f1793X[1], 12) + m98RL293;
        int m98RL297 = m98RL(m98RL292, 10);
        int m98RL298 = m98RL(m98RL293 + m97f1(m98RL296, m98RL294, m98RL297) + this.f1793X[5], 5) + m98RL295;
        int m98RL299 = m98RL(m98RL294, 10);
        int m98RL300 = m98RL(m98RL295 + m97f1(m98RL298, m98RL296, m98RL299) + this.f1793X[8], 14) + m98RL297;
        int m98RL301 = m98RL(m98RL296, 10);
        int m98RL302 = m98RL(m98RL297 + m97f1(m98RL300, m98RL298, m98RL301) + this.f1793X[7], 6) + m98RL299;
        int m98RL303 = m98RL(m98RL298, 10);
        int m98RL304 = m98RL(m98RL299 + m97f1(m98RL302, m98RL300, m98RL303) + this.f1793X[6], 8) + m98RL301;
        int m98RL305 = m98RL(m98RL300, 10);
        int m98RL306 = m98RL(m98RL301 + m97f1(m98RL304, m98RL302, m98RL305) + this.f1793X[2], 13) + m98RL303;
        int m98RL307 = m98RL(m98RL302, 10);
        int m98RL308 = m98RL(m98RL303 + m97f1(m98RL306, m98RL304, m98RL307) + this.f1793X[13], 6) + m98RL305;
        int m98RL309 = m98RL(m98RL304, 10);
        int m98RL310 = m98RL(m98RL305 + m97f1(m98RL308, m98RL306, m98RL309) + this.f1793X[14], 5) + m98RL307;
        int m98RL311 = m98RL(m98RL306, 10);
        int m98RL312 = m98RL(m98RL307 + m97f1(m98RL310, m98RL308, m98RL311) + this.f1793X[0], 15) + m98RL309;
        int m98RL313 = m98RL(m98RL308, 10);
        int m98RL314 = m98RL(m98RL309 + m97f1(m98RL312, m98RL310, m98RL313) + this.f1793X[3], 13) + m98RL311;
        int m98RL315 = m98RL(m98RL310, 10);
        int m98RL316 = m98RL(m98RL311 + m97f1(m98RL314, m98RL312, m98RL315) + this.f1793X[9], 11) + m98RL313;
        int m98RL317 = m98RL(m98RL312, 10);
        int m98RL318 = m98RL(m98RL313 + m97f1(m98RL316, m98RL314, m98RL317) + this.f1793X[11], 11) + m98RL315;
        this.f1789H1 = this.f1790H2 + m98RL287 + m98RL317;
        this.f1790H2 = this.f1791H3 + m98RL286 + m98RL315;
        this.f1791H3 = this.f1792H4 + m98RL284 + m98RL318;
        this.f1792H4 = this.f1788H0 + m98RL(((m98RL282 + m93f5(m98RL285, m98RL283, m98RL286)) + this.f1793X[13]) - 1454113458, 6) + m98RL284 + m98RL316;
        this.f1788H0 = m98RL(m98RL314, 10) + m98RL285 + this.f1789H1;
        this.xOff = 0;
        int r22 = 0;
        while (true) {
            int[] r32 = this.f1793X;
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
        int[] r0 = this.f1793X;
        r0[14] = (int) ((-1) & j);
        r0[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r8) {
        int[] r0 = this.f1793X;
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
        this.f1788H0 = 1732584193;
        this.f1789H1 = -271733879;
        this.f1790H2 = -1732584194;
        this.f1791H3 = 271733878;
        this.f1792H4 = -1009589776;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1793X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD160Digest) memoable);
    }
}
