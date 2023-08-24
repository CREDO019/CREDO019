package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class GMSSRootSig {
    private long big8;
    private int checksum;
    private int counter;
    private GMSSRandom gmssRandom;
    private byte[] hash;
    private int height;

    /* renamed from: ii */
    private int f2411ii;

    /* renamed from: k */
    private int f2412k;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[] privateKeyOTS;

    /* renamed from: r */
    private int f2413r;
    private byte[] seed;
    private byte[] sign;
    private int steps;
    private int test;
    private long test8;

    /* renamed from: w */
    private int f2414w;

    public GMSSRootSig(Digest digest, int r4, int r5) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        this.f2414w = r4;
        this.height = r5;
        this.f2412k = (1 << r4) - 1;
        this.messagesize = (int) Math.ceil((digestSize << 3) / r4);
    }

    public GMSSRootSig(Digest digest, byte[][] bArr, int[] r17) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.counter = r17[0];
        this.test = r17[1];
        this.f2411ii = r17[2];
        this.f2413r = r17[3];
        this.steps = r17[4];
        this.keysize = r17[5];
        this.height = r17[6];
        this.f2414w = r17[7];
        this.checksum = r17[8];
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        int r11 = this.f2414w;
        this.f2412k = (1 << r11) - 1;
        this.messagesize = (int) Math.ceil((digestSize << 3) / r11);
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.hash = bArr[2];
        this.sign = bArr[3];
        this.test8 = ((bArr[4][1] & 255) << 8) | (bArr[4][0] & 255) | ((bArr[4][2] & 255) << 16) | ((bArr[4][3] & 255) << 24) | ((bArr[4][4] & 255) << 32) | ((bArr[4][5] & 255) << 40) | ((bArr[4][6] & 255) << 48) | ((bArr[4][7] & 255) << 56);
        this.big8 = (bArr[4][8] & 255) | ((bArr[4][9] & 255) << 8) | ((bArr[4][10] & 255) << 16) | ((bArr[4][11] & 255) << 24) | ((bArr[4][12] & 255) << 32) | ((bArr[4][13] & 255) << 40) | ((bArr[4][14] & 255) << 48) | ((bArr[4][15] & 255) << 56);
    }

    private void oneStep() {
        long j;
        int r0 = this.f2414w;
        if (8 % r0 == 0) {
            int r02 = this.test;
            if (r02 == 0) {
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
                int r03 = this.f2411ii;
                if (r03 < this.mdsize) {
                    byte[] bArr = this.hash;
                    this.test = bArr[r03] & this.f2412k;
                    bArr[r03] = (byte) (bArr[r03] >>> this.f2414w);
                } else {
                    int r04 = this.checksum;
                    this.test = this.f2412k & r04;
                    this.checksum = r04 >>> this.f2414w;
                }
            } else if (r02 > 0) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.privateKeyOTS;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr3;
                this.messDigestOTS.doFinal(bArr3, 0);
                this.test--;
            }
            if (this.test == 0) {
                byte[] bArr4 = this.privateKeyOTS;
                byte[] bArr5 = this.sign;
                int r4 = this.counter;
                int r5 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr5, r4 * r5, r5);
                int r05 = this.counter + 1;
                this.counter = r05;
                if (r05 % (8 / this.f2414w) == 0) {
                    this.f2411ii++;
                    return;
                }
                return;
            }
            return;
        }
        if (r0 < 8) {
            int r1 = this.test;
            if (r1 == 0) {
                int r12 = this.counter;
                if (r12 % 8 == 0) {
                    int r2 = this.f2411ii;
                    int r6 = this.mdsize;
                    if (r2 < r6) {
                        this.big8 = 0L;
                        if (r12 < ((r6 / r0) << 3)) {
                            for (int r06 = 0; r06 < this.f2414w; r06++) {
                                long j2 = this.big8;
                                byte[] bArr6 = this.hash;
                                int r52 = this.f2411ii;
                                this.big8 = j2 ^ ((bArr6[r52] & 255) << (r06 << 3));
                                this.f2411ii = r52 + 1;
                            }
                        } else {
                            for (int r07 = 0; r07 < this.mdsize % this.f2414w; r07++) {
                                long j3 = this.big8;
                                byte[] bArr7 = this.hash;
                                int r53 = this.f2411ii;
                                this.big8 = j3 ^ ((bArr7[r53] & 255) << (r07 << 3));
                                this.f2411ii = r53 + 1;
                            }
                        }
                    }
                }
                if (this.counter == this.messagesize) {
                    this.big8 = this.checksum;
                }
                this.test = (int) (this.big8 & this.f2412k);
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (r1 > 0) {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr8 = this.privateKeyOTS;
                digest2.update(bArr8, 0, bArr8.length);
                byte[] bArr9 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr9;
                this.messDigestOTS.doFinal(bArr9, 0);
                this.test--;
            }
            if (this.test != 0) {
                return;
            }
            byte[] bArr10 = this.privateKeyOTS;
            byte[] bArr11 = this.sign;
            int r22 = this.counter;
            int r42 = this.mdsize;
            System.arraycopy(bArr10, 0, bArr11, r22 * r42, r42);
            this.big8 >>>= this.f2414w;
        } else if (r0 >= 57) {
            return;
        } else {
            long j4 = this.test8;
            if (j4 == 0) {
                this.big8 = 0L;
                this.f2411ii = 0;
                int r13 = this.f2413r;
                int r23 = r13 % 8;
                int r62 = r13 >>> 3;
                int r7 = this.mdsize;
                if (r62 < r7) {
                    if (r13 <= (r7 << 3) - r0) {
                        int r14 = r13 + r0;
                        this.f2413r = r14;
                        r7 = (r14 + 7) >>> 3;
                    } else {
                        this.f2413r = r13 + r0;
                    }
                    while (true) {
                        j = this.big8;
                        if (r62 >= r7) {
                            break;
                        }
                        int r9 = this.f2411ii;
                        this.big8 = j ^ ((this.hash[r62] & 255) << (r9 << 3));
                        this.f2411ii = r9 + 1;
                        r62++;
                    }
                    long j5 = j >>> r23;
                    this.big8 = j5;
                    this.test8 = j5 & this.f2412k;
                } else {
                    int r15 = this.checksum;
                    this.test8 = this.f2412k & r15;
                    this.checksum = r15 >>> r0;
                }
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (j4 > 0) {
                Digest digest3 = this.messDigestOTS;
                byte[] bArr12 = this.privateKeyOTS;
                digest3.update(bArr12, 0, bArr12.length);
                byte[] bArr13 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr13;
                this.messDigestOTS.doFinal(bArr13, 0);
                this.test8--;
            }
            if (this.test8 != 0) {
                return;
            }
            byte[] bArr14 = this.privateKeyOTS;
            byte[] bArr15 = this.sign;
            int r24 = this.counter;
            int r43 = this.mdsize;
            System.arraycopy(bArr14, 0, bArr15, r24 * r43, r43);
        }
        this.counter++;
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

    public byte[] getSig() {
        return this.sign;
    }

    public byte[][] getStatByte() {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, 5, this.mdsize);
        bArr[0] = this.privateKeyOTS;
        bArr[1] = this.seed;
        bArr[2] = this.hash;
        bArr[3] = this.sign;
        bArr[4] = getStatLong();
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.counter, this.test, this.f2411ii, this.f2413r, this.steps, this.keysize, this.height, this.f2414w, this.checksum};
    }

    public byte[] getStatLong() {
        long j = this.test8;
        long j2 = this.big8;
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 56) & 255), (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), (byte) ((j2 >> 32) & 255), (byte) ((j2 >> 40) & 255), (byte) ((j2 >> 48) & 255), (byte) ((j2 >> 56) & 255)};
    }

    public void initSign(byte[] bArr, byte[] bArr2) {
        int r7;
        int r72;
        this.hash = new byte[this.mdsize];
        this.messDigestOTS.update(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
        this.hash = bArr3;
        this.messDigestOTS.doFinal(bArr3, 0);
        int r1 = this.mdsize;
        byte[] bArr4 = new byte[r1];
        System.arraycopy(this.hash, 0, bArr4, 0, r1);
        int log = getLog((this.messagesize << this.f2414w) + 1);
        int r5 = this.f2414w;
        int r6 = 8;
        if (8 % r5 == 0) {
            int r62 = 8 / r5;
            r7 = 0;
            for (int r52 = 0; r52 < this.mdsize; r52++) {
                for (int r10 = 0; r10 < r62; r10++) {
                    r7 += bArr4[r52] & this.f2412k;
                    bArr4[r52] = (byte) (bArr4[r52] >>> this.f2414w);
                }
            }
            int r2 = (this.messagesize << this.f2414w) - r7;
            this.checksum = r2;
            int r53 = 0;
            while (r53 < log) {
                r7 += this.f2412k & r2;
                int r63 = this.f2414w;
                r2 >>>= r63;
                r53 += r63;
            }
        } else if (r5 < 8) {
            int r73 = this.mdsize / r5;
            int r54 = 0;
            int r102 = 0;
            int r11 = 0;
            while (r54 < r73) {
                long j = 0;
                for (int r12 = 0; r12 < this.f2414w; r12++) {
                    j ^= (bArr4[r102] & 255) << (r12 << 3);
                    r102++;
                }
                int r8 = 0;
                while (r8 < r6) {
                    r11 += (int) (this.f2412k & j);
                    j >>>= this.f2414w;
                    r8++;
                    r73 = r73;
                    r6 = 8;
                }
                r54++;
                r6 = 8;
            }
            int r55 = this.mdsize % this.f2414w;
            long j2 = 0;
            for (int r64 = 0; r64 < r55; r64++) {
                j2 ^= (bArr4[r102] & 255) << (r64 << 3);
                r102++;
            }
            int r22 = r55 << 3;
            int r56 = 0;
            while (r56 < r22) {
                r11 += (int) (this.f2412k & j2);
                int r65 = this.f2414w;
                j2 >>>= r65;
                r56 += r65;
            }
            int r23 = (this.messagesize << this.f2414w) - r11;
            this.checksum = r23;
            r7 = r11;
            int r57 = 0;
            while (r57 < log) {
                r7 += this.f2412k & r23;
                int r66 = this.f2414w;
                r23 >>>= r66;
                r57 += r66;
            }
        } else if (r5 < 57) {
            int r58 = 0;
            int r67 = 0;
            while (true) {
                r72 = this.mdsize;
                int r9 = this.f2414w;
                if (r58 > (r72 << 3) - r9) {
                    break;
                }
                int r82 = r58 % 8;
                r58 += r9;
                int r92 = (r58 + 7) >>> 3;
                long j3 = 0;
                int r122 = 0;
                for (int r74 = r58 >>> 3; r74 < r92; r74++) {
                    j3 ^= (bArr4[r74] & 255) << (r122 << 3);
                    r122++;
                }
                r67 = (int) (r67 + ((j3 >>> r82) & this.f2412k));
            }
            int r83 = r58 >>> 3;
            if (r83 < r72) {
                int r59 = r58 % 8;
                int r75 = 0;
                long j4 = 0;
                while (r83 < this.mdsize) {
                    j4 ^= (bArr4[r83] & 255) << (r75 << 3);
                    r75++;
                    r83++;
                }
                r67 = (int) (r67 + ((j4 >>> r59) & this.f2412k));
            }
            int r24 = (this.messagesize << this.f2414w) - r67;
            this.checksum = r24;
            r7 = r67;
            int r510 = 0;
            while (r510 < log) {
                r7 += this.f2412k & r24;
                int r68 = this.f2414w;
                r24 >>>= r68;
                r510 += r68;
            }
        } else {
            r7 = 0;
        }
        int ceil = this.messagesize + ((int) Math.ceil(log / this.f2414w));
        this.keysize = ceil;
        this.steps = (int) Math.ceil((ceil + r7) / (1 << this.height));
        int r13 = this.keysize;
        int r25 = this.mdsize;
        this.sign = new byte[r13 * r25];
        this.counter = 0;
        this.test = 0;
        this.f2411ii = 0;
        this.test8 = 0L;
        this.f2413r = 0;
        this.privateKeyOTS = new byte[r25];
        byte[] bArr5 = new byte[r25];
        this.seed = bArr5;
        System.arraycopy(bArr, 0, bArr5, 0, r25);
    }

    public String toString() {
        String str = "" + this.big8 + "  ";
        int[] statInt = getStatInt();
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, 5, this.mdsize);
        byte[][] statByte = getStatByte();
        for (int r5 = 0; r5 < 9; r5++) {
            str = str + statInt[r5] + " ";
        }
        for (int r2 = 0; r2 < 5; r2++) {
            str = str + new String(Hex.encode(statByte[r2])) + " ";
        }
        return str;
    }

    public boolean updateSign() {
        for (int r1 = 0; r1 < this.steps; r1++) {
            if (this.counter < this.keysize) {
                oneStep();
            }
            if (this.counter == this.keysize) {
                return true;
            }
        }
        return false;
    }
}
