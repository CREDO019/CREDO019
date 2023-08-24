package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: T */
    private static final int[] f1844T = new int[64];

    /* renamed from: V */
    private int[] f1845V;

    /* renamed from: W */
    private int[] f1846W;
    private int[] inwords;
    private int xOff;

    static {
        int r2;
        int r1 = 0;
        while (true) {
            if (r1 >= 16) {
                break;
            }
            f1844T[r1] = (2043430169 >>> (32 - r1)) | (2043430169 << r1);
            r1++;
        }
        for (r2 = 16; r2 < 64; r2++) {
            int r12 = r2 % 32;
            f1844T[r2] = (2055708042 >>> (32 - r12)) | (2055708042 << r12);
        }
    }

    public SM3Digest() {
        this.f1845V = new int[8];
        this.inwords = new int[16];
        this.f1846W = new int[68];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.f1845V = new int[8];
        this.inwords = new int[16];
        this.f1846W = new int[68];
        copyIn(sM3Digest);
    }

    private int FF0(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    private int FF1(int r2, int r3, int r4) {
        return (r2 & r4) | (r2 & r3) | (r3 & r4);
    }

    private int GG0(int r1, int r2, int r3) {
        return (r1 ^ r2) ^ r3;
    }

    private int GG1(int r1, int r2, int r3) {
        return ((~r1) & r3) | (r2 & r1);
    }

    /* renamed from: P0 */
    private int m72P0(int r4) {
        return (r4 ^ ((r4 << 9) | (r4 >>> 23))) ^ ((r4 << 17) | (r4 >>> 15));
    }

    /* renamed from: P1 */
    private int m71P1(int r4) {
        return (r4 ^ ((r4 << 15) | (r4 >>> 17))) ^ ((r4 << 23) | (r4 >>> 9));
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] r0 = sM3Digest.f1845V;
        int[] r1 = this.f1845V;
        System.arraycopy(r0, 0, r1, 0, r1.length);
        int[] r02 = sM3Digest.inwords;
        int[] r12 = this.inwords;
        System.arraycopy(r02, 0, r12, 0, r12.length);
        this.xOff = sM3Digest.xOff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SM3Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r3) {
        finish();
        Pack.intToBigEndian(this.f1845V, bArr, r3);
        reset();
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int r3;
        int r2 = 0;
        while (true) {
            if (r2 >= 16) {
                break;
            }
            this.f1846W[r2] = this.inwords[r2];
            r2++;
        }
        for (int r22 = 16; r22 < 68; r22++) {
            int[] r4 = this.f1846W;
            int r5 = r4[r22 - 3];
            int r52 = (r5 >>> 17) | (r5 << 15);
            int r6 = r4[r22 - 13];
            r4[r22] = (m71P1(r52 ^ (r4[r22 - 16] ^ r4[r22 - 9])) ^ ((r6 >>> 25) | (r6 << 7))) ^ this.f1846W[r22 - 6];
        }
        int[] r23 = this.f1845V;
        int r42 = r23[0];
        int r62 = r23[1];
        int r8 = r23[2];
        int r10 = r23[3];
        int r12 = r23[4];
        int r14 = r23[5];
        int r16 = r23[6];
        int r24 = r23[7];
        int r15 = r16;
        int r13 = 0;
        for (r3 = 16; r13 < r3; r3 = 16) {
            int r18 = (r42 << 12) | (r42 >>> 20);
            int r19 = r18 + r12 + f1844T[r13];
            int r192 = (r19 << 7) | (r19 >>> 25);
            int[] r32 = this.f1846W;
            int r21 = r32[r13];
            int r25 = (r62 << 9) | (r62 >>> 23);
            int r63 = (r14 << 19) | (r14 >>> 13);
            r13++;
            r14 = r12;
            r12 = m72P0(GG0(r12, r14, r15) + r24 + r192 + r21);
            r10 = r8;
            r8 = r25;
            r24 = r15;
            r15 = r63;
            r62 = r42;
            r42 = FF0(r42, r62, r8) + r10 + (r192 ^ r18) + (r21 ^ r32[r13 + 4]);
        }
        int r33 = r24;
        int r132 = r12;
        int r26 = r15;
        int r122 = r10;
        int r102 = r8;
        int r82 = r62;
        int r64 = r42;
        int r43 = 16;
        while (r43 < 64) {
            int r152 = (r64 << 12) | (r64 >>> 20);
            int r182 = r152 + r132 + f1844T[r43];
            int r183 = (r182 << 7) | (r182 >>> 25);
            int[] r11 = this.f1846W;
            int r20 = r11[r43];
            int r83 = (r82 >>> 23) | (r82 << 9);
            int r34 = (r14 << 19) | (r14 >>> 13);
            r43++;
            r14 = r132;
            r132 = m72P0(GG1(r132, r14, r26) + r33 + r183 + r20);
            r122 = r102;
            r102 = r83;
            r82 = r64;
            r64 = FF1(r64, r82, r102) + r122 + (r183 ^ r152) + (r20 ^ r11[r43 + 4]);
            r33 = r26;
            r26 = r34;
        }
        int[] r44 = this.f1845V;
        r44[0] = r64 ^ r44[0];
        r44[1] = r44[1] ^ r82;
        r44[2] = r44[2] ^ r102;
        r44[3] = r44[3] ^ r122;
        r44[4] = r44[4] ^ r132;
        r44[5] = r44[5] ^ r14;
        r44[6] = r26 ^ r44[6];
        r44[7] = r44[7] ^ r33;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        int r0 = this.xOff;
        if (r0 > 14) {
            this.inwords[r0] = 0;
            this.xOff = r0 + 1;
            processBlock();
        }
        while (true) {
            int r02 = this.xOff;
            if (r02 >= 14) {
                int[] r1 = this.inwords;
                int r2 = r02 + 1;
                this.xOff = r2;
                r1[r02] = (int) (j >>> 32);
                this.xOff = r2 + 1;
                r1[r2] = (int) j;
                return;
            }
            this.inwords[r02] = 0;
            this.xOff = r02 + 1;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r5) {
        int r52 = r5 + 1;
        int r53 = r52 + 1;
        int r4 = (bArr[r53 + 1] & 255) | ((bArr[r5] & 255) << 24) | ((bArr[r52] & 255) << 16) | ((bArr[r53] & 255) << 8);
        int[] r54 = this.inwords;
        int r0 = this.xOff;
        r54[r0] = r4;
        int r02 = r0 + 1;
        this.xOff = r02;
        if (r02 >= 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        int[] r0 = this.f1845V;
        r0[0] = 1937774191;
        r0[1] = 1226093241;
        r0[2] = 388252375;
        r0[3] = -628488704;
        r0[4] = -1452330820;
        r0[5] = 372324522;
        r0[6] = -477237683;
        r0[7] = -1325724082;
        this.xOff = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn((GeneralDigest) sM3Digest);
        copyIn(sM3Digest);
    }
}
