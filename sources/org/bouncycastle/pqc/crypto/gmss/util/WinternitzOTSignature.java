package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes3.dex */
public class WinternitzOTSignature {
    private int checksumsize;
    private GMSSRandom gmssRandom;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[][] privateKeyOTS;

    /* renamed from: w */
    private int f2416w;

    public WinternitzOTSignature(byte[] bArr, Digest digest, int r4) {
        this.f2416w = r4;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        int r3 = (((digestSize << 3) + r4) - 1) / r4;
        this.messagesize = r3;
        int log = getLog((r3 << r4) + 1);
        this.checksumsize = log;
        int r0 = this.messagesize + (((log + r4) - 1) / r4);
        this.keysize = r0;
        this.privateKeyOTS = new byte[r0];
        int r32 = this.mdsize;
        byte[] bArr2 = new byte[r32];
        System.arraycopy(bArr, 0, bArr2, 0, r32);
        for (int r02 = 0; r02 < this.keysize; r02++) {
            this.privateKeyOTS[r02] = this.gmssRandom.nextSeed(bArr2);
        }
    }

    private void hashPrivateKeyBlock(int r4, int r5, byte[] bArr, int r7) {
        if (r5 < 1) {
            System.arraycopy(this.privateKeyOTS[r4], 0, bArr, r7, this.mdsize);
            return;
        }
        this.messDigestOTS.update(this.privateKeyOTS[r4], 0, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr, r7);
            r5--;
            if (r5 <= 0) {
                return;
            }
            this.messDigestOTS.update(bArr, r7, this.mdsize);
        }
    }

    public int getLog(int r3) {
        int r0 = 1;
        int r1 = 2;
        while (r1 < r3) {
            r1 <<= 1;
            r0++;
        }
        return r0;
    }

    public byte[][] getPrivateKey() {
        return this.privateKeyOTS;
    }

    public byte[] getPublicKey() {
        int r0 = this.keysize * this.mdsize;
        byte[] bArr = new byte[r0];
        int r2 = (1 << this.f2416w) - 1;
        int r5 = 0;
        for (int r4 = 0; r4 < this.keysize; r4++) {
            hashPrivateKeyBlock(r4, r2, bArr, r5);
            r5 += this.mdsize;
        }
        this.messDigestOTS.update(bArr, 0, r0);
        byte[] bArr2 = new byte[this.mdsize];
        this.messDigestOTS.doFinal(bArr2, 0);
        return bArr2;
    }

    public byte[] getSignature(byte[] bArr) {
        int r7;
        int r2 = this.keysize;
        int r3 = this.mdsize;
        byte[] bArr2 = new byte[r2 * r3];
        byte[] bArr3 = new byte[r3];
        int r72 = 0;
        this.messDigestOTS.update(bArr, 0, bArr.length);
        this.messDigestOTS.doFinal(bArr3, 0);
        int r1 = this.f2416w;
        int r5 = 8;
        if (8 % r1 == 0) {
            int r52 = 8 / r1;
            int r12 = (1 << r1) - 1;
            int r9 = 0;
            int r10 = 0;
            for (int r6 = 0; r6 < r3; r6++) {
                for (int r11 = 0; r11 < r52; r11++) {
                    int r122 = bArr3[r6] & r12;
                    r9 += r122;
                    hashPrivateKeyBlock(r10, r122, bArr2, this.mdsize * r10);
                    bArr3[r6] = (byte) (bArr3[r6] >>> this.f2416w);
                    r10++;
                }
            }
            int r32 = (this.messagesize << this.f2416w) - r9;
            while (r72 < this.checksumsize) {
                hashPrivateKeyBlock(r10, r32 & r12, bArr2, this.mdsize * r10);
                int r4 = this.f2416w;
                r32 >>>= r4;
                r10++;
                r72 += r4;
            }
        } else if (r1 < 8) {
            int r33 = this.mdsize / r1;
            int r13 = (1 << r1) - 1;
            int r62 = 0;
            int r112 = 0;
            int r123 = 0;
            int r132 = 0;
            while (r62 < r33) {
                long j = 0;
                for (int r14 = 0; r14 < this.f2416w; r14++) {
                    j ^= (bArr3[r112] & 255) << (r14 << 3);
                    r112++;
                }
                long j2 = j;
                int r142 = 0;
                while (r142 < r5) {
                    int r15 = ((int) j2) & r13;
                    r132 += r15;
                    hashPrivateKeyBlock(r123, r15, bArr2, this.mdsize * r123);
                    j2 >>>= this.f2416w;
                    r123++;
                    r142++;
                    r5 = 8;
                }
                r62++;
                r5 = 8;
            }
            int r34 = this.mdsize % this.f2416w;
            long j3 = 0;
            for (int r53 = 0; r53 < r34; r53++) {
                j3 ^= (bArr3[r112] & 255) << (r53 << 3);
                r112++;
            }
            int r35 = r34 << 3;
            int r42 = 0;
            while (r42 < r35) {
                int r54 = ((int) j3) & r13;
                r132 += r54;
                hashPrivateKeyBlock(r123, r54, bArr2, this.mdsize * r123);
                int r55 = this.f2416w;
                j3 >>>= r55;
                r123++;
                r42 += r55;
            }
            int r36 = (this.messagesize << this.f2416w) - r132;
            while (r72 < this.checksumsize) {
                hashPrivateKeyBlock(r123, r36 & r13, bArr2, this.mdsize * r123);
                int r43 = this.f2416w;
                r36 >>>= r43;
                r123++;
                r72 += r43;
            }
        } else if (r1 < 57) {
            int r37 = this.mdsize;
            int r56 = (r37 << 3) - r1;
            int r16 = (1 << r1) - 1;
            byte[] bArr4 = new byte[r37];
            int r92 = 0;
            int r102 = 0;
            int r113 = 0;
            while (r92 <= r56) {
                int r143 = r92 >>> 3;
                int r152 = r92 % 8;
                r92 += this.f2416w;
                int r133 = 0;
                long j4 = 0;
                while (r143 < ((r92 + 7) >>> 3)) {
                    j4 ^= (bArr3[r143] & 255) << (r133 << 3);
                    r133++;
                    r143++;
                    bArr3 = bArr3;
                    r56 = r56;
                }
                byte[] bArr5 = bArr3;
                int r21 = r56;
                long j5 = (j4 >>> r152) & r16;
                r113 = (int) (r113 + j5);
                System.arraycopy(this.privateKeyOTS[r102], 0, bArr4, 0, this.mdsize);
                while (j5 > 0) {
                    this.messDigestOTS.update(bArr4, 0, r37);
                    this.messDigestOTS.doFinal(bArr4, 0);
                    j5--;
                }
                int r44 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, r102 * r44, r44);
                r102++;
                bArr3 = bArr5;
                r56 = r21;
            }
            byte[] bArr6 = bArr3;
            int r45 = r92 >>> 3;
            if (r45 < this.mdsize) {
                int r93 = r92 % 8;
                int r57 = 0;
                long j6 = 0;
                while (true) {
                    r7 = this.mdsize;
                    if (r45 >= r7) {
                        break;
                    }
                    j6 ^= (bArr6[r45] & 255) << (r57 << 3);
                    r57++;
                    r45++;
                }
                long j7 = (j6 >>> r93) & r16;
                r113 = (int) (r113 + j7);
                System.arraycopy(this.privateKeyOTS[r102], 0, bArr4, 0, r7);
                while (j7 > 0) {
                    this.messDigestOTS.update(bArr4, 0, r37);
                    this.messDigestOTS.doFinal(bArr4, 0);
                    j7--;
                }
                int r46 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, r102 * r46, r46);
                r102++;
            }
            int r47 = (this.messagesize << this.f2416w) - r113;
            int r134 = 0;
            while (r134 < this.checksumsize) {
                System.arraycopy(this.privateKeyOTS[r102], 0, bArr4, 0, this.mdsize);
                for (long j8 = r47 & r16; j8 > 0; j8--) {
                    this.messDigestOTS.update(bArr4, 0, r37);
                    this.messDigestOTS.doFinal(bArr4, 0);
                }
                int r58 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr2, r102 * r58, r58);
                int r59 = this.f2416w;
                r47 >>>= r59;
                r102++;
                r134 += r59;
            }
        }
        return bArr2;
    }
}
