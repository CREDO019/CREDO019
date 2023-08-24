package org.bouncycastle.crypto.digests;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class MD5Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 16;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;

    /* renamed from: H1 */
    private int f1777H1;

    /* renamed from: H2 */
    private int f1778H2;

    /* renamed from: H3 */
    private int f1779H3;

    /* renamed from: H4 */
    private int f1780H4;

    /* renamed from: X */
    private int[] f1781X;
    private int xOff;

    public MD5Digest() {
        this.f1781X = new int[16];
        reset();
    }

    public MD5Digest(MD5Digest mD5Digest) {
        super(mD5Digest);
        this.f1781X = new int[16];
        copyIn(mD5Digest);
    }

    public MD5Digest(byte[] bArr) {
        super(bArr);
        this.f1781X = new int[16];
        this.f1777H1 = Pack.bigEndianToInt(bArr, 16);
        this.f1778H2 = Pack.bigEndianToInt(bArr, 20);
        this.f1779H3 = Pack.bigEndianToInt(bArr, 24);
        this.f1780H4 = Pack.bigEndianToInt(bArr, 28);
        this.xOff = Pack.bigEndianToInt(bArr, 32);
        for (int r0 = 0; r0 != this.xOff; r0++) {
            this.f1781X[r0] = Pack.bigEndianToInt(bArr, (r0 * 4) + 36);
        }
    }

    /* renamed from: F */
    private int m111F(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: G */
    private int m110G(int r1, int r2, int r3) {
        return (r1 & r3) | (r2 & (~r3));
    }

    /* renamed from: H */
    private int m109H(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    /* renamed from: K */
    private int m108K(int r1, int r2, int r3) {
        return (r1 | (~r3)) ^ r2;
    }

    private void copyIn(MD5Digest mD5Digest) {
        super.copyIn((GeneralDigest) mD5Digest);
        this.f1777H1 = mD5Digest.f1777H1;
        this.f1778H2 = mD5Digest.f1778H2;
        this.f1779H3 = mD5Digest.f1779H3;
        this.f1780H4 = mD5Digest.f1780H4;
        int[] r0 = mD5Digest.f1781X;
        System.arraycopy(r0, 0, this.f1781X, 0, r0.length);
        this.xOff = mD5Digest.xOff;
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
        return new MD5Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        unpackWord(this.f1777H1, bArr, r4);
        unpackWord(this.f1778H2, bArr, r4 + 4);
        unpackWord(this.f1779H3, bArr, r4 + 8);
        unpackWord(this.f1780H4, bArr, r4 + 12);
        reset();
        return 16;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return MessageDigestAlgorithms.MD5;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 36];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f1777H1, bArr, 16);
        Pack.intToBigEndian(this.f1778H2, bArr, 20);
        Pack.intToBigEndian(this.f1779H3, bArr, 24);
        Pack.intToBigEndian(this.f1780H4, bArr, 28);
        Pack.intToBigEndian(this.xOff, bArr, 32);
        for (int r1 = 0; r1 != this.xOff; r1++) {
            Pack.intToBigEndian(this.f1781X[r1], bArr, (r1 * 4) + 36);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int r1 = this.f1777H1;
        int r2 = this.f1778H2;
        int r3 = this.f1779H3;
        int r4 = this.f1780H4;
        int rotateLeft = rotateLeft(((r1 + m111F(r2, r3, r4)) + this.f1781X[0]) - 680876936, 7) + r2;
        int rotateLeft2 = rotateLeft(((r4 + m111F(rotateLeft, r2, r3)) + this.f1781X[1]) - 389564586, 12) + rotateLeft;
        int rotateLeft3 = rotateLeft(r3 + m111F(rotateLeft2, rotateLeft, r2) + this.f1781X[2] + 606105819, 17) + rotateLeft2;
        int rotateLeft4 = rotateLeft(((r2 + m111F(rotateLeft3, rotateLeft2, rotateLeft)) + this.f1781X[3]) - 1044525330, 22) + rotateLeft3;
        int rotateLeft5 = rotateLeft(((rotateLeft + m111F(rotateLeft4, rotateLeft3, rotateLeft2)) + this.f1781X[4]) - 176418897, 7) + rotateLeft4;
        int rotateLeft6 = rotateLeft(rotateLeft2 + m111F(rotateLeft5, rotateLeft4, rotateLeft3) + this.f1781X[5] + 1200080426, 12) + rotateLeft5;
        int rotateLeft7 = rotateLeft(((rotateLeft3 + m111F(rotateLeft6, rotateLeft5, rotateLeft4)) + this.f1781X[6]) - 1473231341, 17) + rotateLeft6;
        int rotateLeft8 = rotateLeft(((rotateLeft4 + m111F(rotateLeft7, rotateLeft6, rotateLeft5)) + this.f1781X[7]) - 45705983, 22) + rotateLeft7;
        int rotateLeft9 = rotateLeft(rotateLeft5 + m111F(rotateLeft8, rotateLeft7, rotateLeft6) + this.f1781X[8] + 1770035416, 7) + rotateLeft8;
        int rotateLeft10 = rotateLeft(((rotateLeft6 + m111F(rotateLeft9, rotateLeft8, rotateLeft7)) + this.f1781X[9]) - 1958414417, 12) + rotateLeft9;
        int rotateLeft11 = rotateLeft(((rotateLeft7 + m111F(rotateLeft10, rotateLeft9, rotateLeft8)) + this.f1781X[10]) - 42063, 17) + rotateLeft10;
        int rotateLeft12 = rotateLeft(((rotateLeft8 + m111F(rotateLeft11, rotateLeft10, rotateLeft9)) + this.f1781X[11]) - 1990404162, 22) + rotateLeft11;
        int rotateLeft13 = rotateLeft(rotateLeft9 + m111F(rotateLeft12, rotateLeft11, rotateLeft10) + this.f1781X[12] + 1804603682, 7) + rotateLeft12;
        int rotateLeft14 = rotateLeft(((rotateLeft10 + m111F(rotateLeft13, rotateLeft12, rotateLeft11)) + this.f1781X[13]) - 40341101, 12) + rotateLeft13;
        int rotateLeft15 = rotateLeft(((rotateLeft11 + m111F(rotateLeft14, rotateLeft13, rotateLeft12)) + this.f1781X[14]) - 1502002290, 17) + rotateLeft14;
        int rotateLeft16 = rotateLeft(rotateLeft12 + m111F(rotateLeft15, rotateLeft14, rotateLeft13) + this.f1781X[15] + 1236535329, 22) + rotateLeft15;
        int rotateLeft17 = rotateLeft(((rotateLeft13 + m110G(rotateLeft16, rotateLeft15, rotateLeft14)) + this.f1781X[1]) - 165796510, 5) + rotateLeft16;
        int rotateLeft18 = rotateLeft(((rotateLeft14 + m110G(rotateLeft17, rotateLeft16, rotateLeft15)) + this.f1781X[6]) - 1069501632, 9) + rotateLeft17;
        int rotateLeft19 = rotateLeft(rotateLeft15 + m110G(rotateLeft18, rotateLeft17, rotateLeft16) + this.f1781X[11] + 643717713, 14) + rotateLeft18;
        int rotateLeft20 = rotateLeft(((rotateLeft16 + m110G(rotateLeft19, rotateLeft18, rotateLeft17)) + this.f1781X[0]) - 373897302, 20) + rotateLeft19;
        int rotateLeft21 = rotateLeft(((rotateLeft17 + m110G(rotateLeft20, rotateLeft19, rotateLeft18)) + this.f1781X[5]) - 701558691, 5) + rotateLeft20;
        int rotateLeft22 = rotateLeft(rotateLeft18 + m110G(rotateLeft21, rotateLeft20, rotateLeft19) + this.f1781X[10] + 38016083, 9) + rotateLeft21;
        int rotateLeft23 = rotateLeft(((rotateLeft19 + m110G(rotateLeft22, rotateLeft21, rotateLeft20)) + this.f1781X[15]) - 660478335, 14) + rotateLeft22;
        int rotateLeft24 = rotateLeft(((rotateLeft20 + m110G(rotateLeft23, rotateLeft22, rotateLeft21)) + this.f1781X[4]) - 405537848, 20) + rotateLeft23;
        int rotateLeft25 = rotateLeft(rotateLeft21 + m110G(rotateLeft24, rotateLeft23, rotateLeft22) + this.f1781X[9] + 568446438, 5) + rotateLeft24;
        int rotateLeft26 = rotateLeft(((rotateLeft22 + m110G(rotateLeft25, rotateLeft24, rotateLeft23)) + this.f1781X[14]) - 1019803690, 9) + rotateLeft25;
        int rotateLeft27 = rotateLeft(((rotateLeft23 + m110G(rotateLeft26, rotateLeft25, rotateLeft24)) + this.f1781X[3]) - 187363961, 14) + rotateLeft26;
        int rotateLeft28 = rotateLeft(rotateLeft24 + m110G(rotateLeft27, rotateLeft26, rotateLeft25) + this.f1781X[8] + 1163531501, 20) + rotateLeft27;
        int rotateLeft29 = rotateLeft(((rotateLeft25 + m110G(rotateLeft28, rotateLeft27, rotateLeft26)) + this.f1781X[13]) - 1444681467, 5) + rotateLeft28;
        int rotateLeft30 = rotateLeft(((rotateLeft26 + m110G(rotateLeft29, rotateLeft28, rotateLeft27)) + this.f1781X[2]) - 51403784, 9) + rotateLeft29;
        int rotateLeft31 = rotateLeft(rotateLeft27 + m110G(rotateLeft30, rotateLeft29, rotateLeft28) + this.f1781X[7] + 1735328473, 14) + rotateLeft30;
        int rotateLeft32 = rotateLeft(((rotateLeft28 + m110G(rotateLeft31, rotateLeft30, rotateLeft29)) + this.f1781X[12]) - 1926607734, 20) + rotateLeft31;
        int rotateLeft33 = rotateLeft(((rotateLeft29 + m109H(rotateLeft32, rotateLeft31, rotateLeft30)) + this.f1781X[5]) - 378558, 4) + rotateLeft32;
        int rotateLeft34 = rotateLeft(((rotateLeft30 + m109H(rotateLeft33, rotateLeft32, rotateLeft31)) + this.f1781X[8]) - 2022574463, 11) + rotateLeft33;
        int rotateLeft35 = rotateLeft(rotateLeft31 + m109H(rotateLeft34, rotateLeft33, rotateLeft32) + this.f1781X[11] + 1839030562, 16) + rotateLeft34;
        int rotateLeft36 = rotateLeft(((rotateLeft32 + m109H(rotateLeft35, rotateLeft34, rotateLeft33)) + this.f1781X[14]) - 35309556, 23) + rotateLeft35;
        int rotateLeft37 = rotateLeft(((rotateLeft33 + m109H(rotateLeft36, rotateLeft35, rotateLeft34)) + this.f1781X[1]) - 1530992060, 4) + rotateLeft36;
        int rotateLeft38 = rotateLeft(rotateLeft34 + m109H(rotateLeft37, rotateLeft36, rotateLeft35) + this.f1781X[4] + 1272893353, 11) + rotateLeft37;
        int rotateLeft39 = rotateLeft(((rotateLeft35 + m109H(rotateLeft38, rotateLeft37, rotateLeft36)) + this.f1781X[7]) - 155497632, 16) + rotateLeft38;
        int rotateLeft40 = rotateLeft(((rotateLeft36 + m109H(rotateLeft39, rotateLeft38, rotateLeft37)) + this.f1781X[10]) - 1094730640, 23) + rotateLeft39;
        int rotateLeft41 = rotateLeft(rotateLeft37 + m109H(rotateLeft40, rotateLeft39, rotateLeft38) + this.f1781X[13] + 681279174, 4) + rotateLeft40;
        int rotateLeft42 = rotateLeft(((rotateLeft38 + m109H(rotateLeft41, rotateLeft40, rotateLeft39)) + this.f1781X[0]) - 358537222, 11) + rotateLeft41;
        int rotateLeft43 = rotateLeft(((rotateLeft39 + m109H(rotateLeft42, rotateLeft41, rotateLeft40)) + this.f1781X[3]) - 722521979, 16) + rotateLeft42;
        int rotateLeft44 = rotateLeft(rotateLeft40 + m109H(rotateLeft43, rotateLeft42, rotateLeft41) + this.f1781X[6] + 76029189, 23) + rotateLeft43;
        int rotateLeft45 = rotateLeft(((rotateLeft41 + m109H(rotateLeft44, rotateLeft43, rotateLeft42)) + this.f1781X[9]) - 640364487, 4) + rotateLeft44;
        int rotateLeft46 = rotateLeft(((rotateLeft42 + m109H(rotateLeft45, rotateLeft44, rotateLeft43)) + this.f1781X[12]) - 421815835, 11) + rotateLeft45;
        int rotateLeft47 = rotateLeft(rotateLeft43 + m109H(rotateLeft46, rotateLeft45, rotateLeft44) + this.f1781X[15] + 530742520, 16) + rotateLeft46;
        int rotateLeft48 = rotateLeft(((rotateLeft44 + m109H(rotateLeft47, rotateLeft46, rotateLeft45)) + this.f1781X[2]) - 995338651, 23) + rotateLeft47;
        int rotateLeft49 = rotateLeft(((rotateLeft45 + m108K(rotateLeft48, rotateLeft47, rotateLeft46)) + this.f1781X[0]) - 198630844, 6) + rotateLeft48;
        int rotateLeft50 = rotateLeft(rotateLeft46 + m108K(rotateLeft49, rotateLeft48, rotateLeft47) + this.f1781X[7] + 1126891415, 10) + rotateLeft49;
        int rotateLeft51 = rotateLeft(((rotateLeft47 + m108K(rotateLeft50, rotateLeft49, rotateLeft48)) + this.f1781X[14]) - 1416354905, 15) + rotateLeft50;
        int rotateLeft52 = rotateLeft(((rotateLeft48 + m108K(rotateLeft51, rotateLeft50, rotateLeft49)) + this.f1781X[5]) - 57434055, 21) + rotateLeft51;
        int rotateLeft53 = rotateLeft(rotateLeft49 + m108K(rotateLeft52, rotateLeft51, rotateLeft50) + this.f1781X[12] + 1700485571, 6) + rotateLeft52;
        int rotateLeft54 = rotateLeft(((rotateLeft50 + m108K(rotateLeft53, rotateLeft52, rotateLeft51)) + this.f1781X[3]) - 1894986606, 10) + rotateLeft53;
        int rotateLeft55 = rotateLeft(((rotateLeft51 + m108K(rotateLeft54, rotateLeft53, rotateLeft52)) + this.f1781X[10]) - 1051523, 15) + rotateLeft54;
        int rotateLeft56 = rotateLeft(((rotateLeft52 + m108K(rotateLeft55, rotateLeft54, rotateLeft53)) + this.f1781X[1]) - 2054922799, 21) + rotateLeft55;
        int rotateLeft57 = rotateLeft(rotateLeft53 + m108K(rotateLeft56, rotateLeft55, rotateLeft54) + this.f1781X[8] + 1873313359, 6) + rotateLeft56;
        int rotateLeft58 = rotateLeft(((rotateLeft54 + m108K(rotateLeft57, rotateLeft56, rotateLeft55)) + this.f1781X[15]) - 30611744, 10) + rotateLeft57;
        int rotateLeft59 = rotateLeft(((rotateLeft55 + m108K(rotateLeft58, rotateLeft57, rotateLeft56)) + this.f1781X[6]) - 1560198380, 15) + rotateLeft58;
        int rotateLeft60 = rotateLeft(rotateLeft56 + m108K(rotateLeft59, rotateLeft58, rotateLeft57) + this.f1781X[13] + 1309151649, 21) + rotateLeft59;
        int rotateLeft61 = rotateLeft(((rotateLeft57 + m108K(rotateLeft60, rotateLeft59, rotateLeft58)) + this.f1781X[4]) - 145523070, 6) + rotateLeft60;
        int rotateLeft62 = rotateLeft(((rotateLeft58 + m108K(rotateLeft61, rotateLeft60, rotateLeft59)) + this.f1781X[11]) - 1120210379, 10) + rotateLeft61;
        int rotateLeft63 = rotateLeft(rotateLeft59 + m108K(rotateLeft62, rotateLeft61, rotateLeft60) + this.f1781X[2] + 718787259, 15) + rotateLeft62;
        this.f1777H1 += rotateLeft61;
        this.f1778H2 += rotateLeft(((rotateLeft60 + m108K(rotateLeft63, rotateLeft62, rotateLeft61)) + this.f1781X[9]) - 343485551, 21) + rotateLeft63;
        this.f1779H3 += rotateLeft63;
        this.f1780H4 += rotateLeft62;
        this.xOff = 0;
        int r12 = 0;
        while (true) {
            int[] r22 = this.f1781X;
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
        int[] r0 = this.f1781X;
        r0[14] = (int) ((-1) & j);
        r0[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r8) {
        int[] r0 = this.f1781X;
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
        this.f1777H1 = 1732584193;
        this.f1778H2 = -271733879;
        this.f1779H3 = -1732584194;
        this.f1780H4 = 271733878;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1781X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((MD5Digest) memoable);
    }
}
