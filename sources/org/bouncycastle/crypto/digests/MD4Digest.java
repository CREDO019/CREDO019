package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public class MD4Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;
    private static final int S11 = 3;
    private static final int S12 = 7;
    private static final int S13 = 11;
    private static final int S14 = 19;
    private static final int S21 = 3;
    private static final int S22 = 5;
    private static final int S23 = 9;
    private static final int S24 = 13;
    private static final int S31 = 3;
    private static final int S32 = 9;
    private static final int S33 = 11;
    private static final int S34 = 15;

    /* renamed from: H1 */
    private int f1772H1;

    /* renamed from: H2 */
    private int f1773H2;

    /* renamed from: H3 */
    private int f1774H3;

    /* renamed from: H4 */
    private int f1775H4;

    /* renamed from: X */
    private int[] f1776X;
    private int xOff;

    public MD4Digest() {
        this.f1776X = new int[16];
        reset();
    }

    public MD4Digest(MD4Digest mD4Digest) {
        super(mD4Digest);
        this.f1776X = new int[16];
        copyIn(mD4Digest);
    }

    /* renamed from: F */
    private int m114F(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: G */
    private int m113G(int r2, int r3, int r4) {
        return (r2 & r4) | (r2 & r3) | (r3 & r4);
    }

    /* renamed from: H */
    private int m112H(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    private void copyIn(MD4Digest mD4Digest) {
        super.copyIn((GeneralDigest) mD4Digest);
        this.f1772H1 = mD4Digest.f1772H1;
        this.f1773H2 = mD4Digest.f1773H2;
        this.f1774H3 = mD4Digest.f1774H3;
        this.f1775H4 = mD4Digest.f1775H4;
        int[] r0 = mD4Digest.f1776X;
        System.arraycopy(r0, 0, this.f1776X, 0, r0.length);
        this.xOff = mD4Digest.xOff;
    }

    private int rotateLeft(int r2, int r3) {
        return (r2 >>> (32 - r3)) | (r2 << r3);
    }

    private void unpackWord(int r3, byte[] bArr, int r5) {
        bArr[r5] = (byte) r3;
        bArr[r5 + 1] = (byte) (r3 >>> 8);
        bArr[r5 + 2] = (byte) (r3 >>> 16);
        bArr[r5 + 3] = (byte) (r3 >>> 24);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD4Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        unpackWord(this.f1772H1, bArr, r4);
        unpackWord(this.f1773H2, bArr, r4 + 4);
        unpackWord(this.f1774H3, bArr, r4 + 8);
        unpackWord(this.f1775H4, bArr, r4 + 12);
        reset();
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "MD4";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int r1 = this.f1772H1;
        int r2 = this.f1773H2;
        int r3 = this.f1774H3;
        int r4 = this.f1775H4;
        int rotateLeft = rotateLeft(r1 + m114F(r2, r3, r4) + this.f1776X[0], 3);
        int rotateLeft2 = rotateLeft(r4 + m114F(rotateLeft, r2, r3) + this.f1776X[1], 7);
        int rotateLeft3 = rotateLeft(r3 + m114F(rotateLeft2, rotateLeft, r2) + this.f1776X[2], 11);
        int rotateLeft4 = rotateLeft(r2 + m114F(rotateLeft3, rotateLeft2, rotateLeft) + this.f1776X[3], 19);
        int rotateLeft5 = rotateLeft(rotateLeft + m114F(rotateLeft4, rotateLeft3, rotateLeft2) + this.f1776X[4], 3);
        int rotateLeft6 = rotateLeft(rotateLeft2 + m114F(rotateLeft5, rotateLeft4, rotateLeft3) + this.f1776X[5], 7);
        int rotateLeft7 = rotateLeft(rotateLeft3 + m114F(rotateLeft6, rotateLeft5, rotateLeft4) + this.f1776X[6], 11);
        int rotateLeft8 = rotateLeft(rotateLeft4 + m114F(rotateLeft7, rotateLeft6, rotateLeft5) + this.f1776X[7], 19);
        int rotateLeft9 = rotateLeft(rotateLeft5 + m114F(rotateLeft8, rotateLeft7, rotateLeft6) + this.f1776X[8], 3);
        int rotateLeft10 = rotateLeft(rotateLeft6 + m114F(rotateLeft9, rotateLeft8, rotateLeft7) + this.f1776X[9], 7);
        int rotateLeft11 = rotateLeft(rotateLeft7 + m114F(rotateLeft10, rotateLeft9, rotateLeft8) + this.f1776X[10], 11);
        int rotateLeft12 = rotateLeft(rotateLeft8 + m114F(rotateLeft11, rotateLeft10, rotateLeft9) + this.f1776X[11], 19);
        int rotateLeft13 = rotateLeft(rotateLeft9 + m114F(rotateLeft12, rotateLeft11, rotateLeft10) + this.f1776X[12], 3);
        int rotateLeft14 = rotateLeft(rotateLeft10 + m114F(rotateLeft13, rotateLeft12, rotateLeft11) + this.f1776X[13], 7);
        int rotateLeft15 = rotateLeft(rotateLeft11 + m114F(rotateLeft14, rotateLeft13, rotateLeft12) + this.f1776X[14], 11);
        int rotateLeft16 = rotateLeft(rotateLeft12 + m114F(rotateLeft15, rotateLeft14, rotateLeft13) + this.f1776X[15], 19);
        int rotateLeft17 = rotateLeft(rotateLeft13 + m113G(rotateLeft16, rotateLeft15, rotateLeft14) + this.f1776X[0] + 1518500249, 3);
        int rotateLeft18 = rotateLeft(rotateLeft14 + m113G(rotateLeft17, rotateLeft16, rotateLeft15) + this.f1776X[4] + 1518500249, 5);
        int rotateLeft19 = rotateLeft(rotateLeft15 + m113G(rotateLeft18, rotateLeft17, rotateLeft16) + this.f1776X[8] + 1518500249, 9);
        int rotateLeft20 = rotateLeft(rotateLeft16 + m113G(rotateLeft19, rotateLeft18, rotateLeft17) + this.f1776X[12] + 1518500249, 13);
        int rotateLeft21 = rotateLeft(rotateLeft17 + m113G(rotateLeft20, rotateLeft19, rotateLeft18) + this.f1776X[1] + 1518500249, 3);
        int rotateLeft22 = rotateLeft(rotateLeft18 + m113G(rotateLeft21, rotateLeft20, rotateLeft19) + this.f1776X[5] + 1518500249, 5);
        int rotateLeft23 = rotateLeft(rotateLeft19 + m113G(rotateLeft22, rotateLeft21, rotateLeft20) + this.f1776X[9] + 1518500249, 9);
        int rotateLeft24 = rotateLeft(rotateLeft20 + m113G(rotateLeft23, rotateLeft22, rotateLeft21) + this.f1776X[13] + 1518500249, 13);
        int rotateLeft25 = rotateLeft(rotateLeft21 + m113G(rotateLeft24, rotateLeft23, rotateLeft22) + this.f1776X[2] + 1518500249, 3);
        int rotateLeft26 = rotateLeft(rotateLeft22 + m113G(rotateLeft25, rotateLeft24, rotateLeft23) + this.f1776X[6] + 1518500249, 5);
        int rotateLeft27 = rotateLeft(rotateLeft23 + m113G(rotateLeft26, rotateLeft25, rotateLeft24) + this.f1776X[10] + 1518500249, 9);
        int rotateLeft28 = rotateLeft(rotateLeft24 + m113G(rotateLeft27, rotateLeft26, rotateLeft25) + this.f1776X[14] + 1518500249, 13);
        int rotateLeft29 = rotateLeft(rotateLeft25 + m113G(rotateLeft28, rotateLeft27, rotateLeft26) + this.f1776X[3] + 1518500249, 3);
        int rotateLeft30 = rotateLeft(rotateLeft26 + m113G(rotateLeft29, rotateLeft28, rotateLeft27) + this.f1776X[7] + 1518500249, 5);
        int rotateLeft31 = rotateLeft(rotateLeft27 + m113G(rotateLeft30, rotateLeft29, rotateLeft28) + this.f1776X[11] + 1518500249, 9);
        int rotateLeft32 = rotateLeft(rotateLeft28 + m113G(rotateLeft31, rotateLeft30, rotateLeft29) + this.f1776X[15] + 1518500249, 13);
        int rotateLeft33 = rotateLeft(rotateLeft29 + m112H(rotateLeft32, rotateLeft31, rotateLeft30) + this.f1776X[0] + 1859775393, 3);
        int rotateLeft34 = rotateLeft(rotateLeft30 + m112H(rotateLeft33, rotateLeft32, rotateLeft31) + this.f1776X[8] + 1859775393, 9);
        int rotateLeft35 = rotateLeft(rotateLeft31 + m112H(rotateLeft34, rotateLeft33, rotateLeft32) + this.f1776X[4] + 1859775393, 11);
        int rotateLeft36 = rotateLeft(rotateLeft32 + m112H(rotateLeft35, rotateLeft34, rotateLeft33) + this.f1776X[12] + 1859775393, 15);
        int rotateLeft37 = rotateLeft(rotateLeft33 + m112H(rotateLeft36, rotateLeft35, rotateLeft34) + this.f1776X[2] + 1859775393, 3);
        int rotateLeft38 = rotateLeft(rotateLeft34 + m112H(rotateLeft37, rotateLeft36, rotateLeft35) + this.f1776X[10] + 1859775393, 9);
        int rotateLeft39 = rotateLeft(rotateLeft35 + m112H(rotateLeft38, rotateLeft37, rotateLeft36) + this.f1776X[6] + 1859775393, 11);
        int rotateLeft40 = rotateLeft(rotateLeft36 + m112H(rotateLeft39, rotateLeft38, rotateLeft37) + this.f1776X[14] + 1859775393, 15);
        int rotateLeft41 = rotateLeft(rotateLeft37 + m112H(rotateLeft40, rotateLeft39, rotateLeft38) + this.f1776X[1] + 1859775393, 3);
        int rotateLeft42 = rotateLeft(rotateLeft38 + m112H(rotateLeft41, rotateLeft40, rotateLeft39) + this.f1776X[9] + 1859775393, 9);
        int rotateLeft43 = rotateLeft(rotateLeft39 + m112H(rotateLeft42, rotateLeft41, rotateLeft40) + this.f1776X[5] + 1859775393, 11);
        int rotateLeft44 = rotateLeft(rotateLeft40 + m112H(rotateLeft43, rotateLeft42, rotateLeft41) + this.f1776X[13] + 1859775393, 15);
        int rotateLeft45 = rotateLeft(rotateLeft41 + m112H(rotateLeft44, rotateLeft43, rotateLeft42) + this.f1776X[3] + 1859775393, 3);
        int rotateLeft46 = rotateLeft(rotateLeft42 + m112H(rotateLeft45, rotateLeft44, rotateLeft43) + this.f1776X[11] + 1859775393, 9);
        int rotateLeft47 = rotateLeft(rotateLeft43 + m112H(rotateLeft46, rotateLeft45, rotateLeft44) + this.f1776X[7] + 1859775393, 11);
        int rotateLeft48 = rotateLeft(rotateLeft44 + m112H(rotateLeft47, rotateLeft46, rotateLeft45) + this.f1776X[15] + 1859775393, 15);
        this.f1772H1 += rotateLeft45;
        this.f1773H2 += rotateLeft48;
        this.f1774H3 += rotateLeft47;
        this.f1775H4 += rotateLeft46;
        this.xOff = 0;
        int r12 = 0;
        while (true) {
            int[] r22 = this.f1776X;
            if (r12 == r22.length) {
                return;
            }
            r22[r12] = 0;
            r12++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] r0 = this.f1776X;
        r0[14] = (int) ((-1) & j);
        r0[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r8) {
        int[] r0 = this.f1776X;
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
        this.f1772H1 = 1732584193;
        this.f1773H2 = -271733879;
        this.f1774H3 = -1732584194;
        this.f1775H4 = 271733878;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1776X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD4Digest) memoable);
    }
}
