package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes3.dex */
public class WinternitzOTSVerify {
    private int mdsize;
    private Digest messDigestOTS;

    /* renamed from: w */
    private int f2415w;

    public WinternitzOTSVerify(Digest digest, int r2) {
        this.f2415w = r2;
        this.messDigestOTS = digest;
        this.mdsize = digest.getDigestSize();
    }

    private void hashSignatureBlock(byte[] bArr, int r4, int r5, byte[] bArr2, int r7) {
        if (r5 < 1) {
            System.arraycopy(bArr, r4, bArr2, r7, this.mdsize);
            return;
        }
        this.messDigestOTS.update(bArr, r4, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr2, r7);
            r5--;
            if (r5 <= 0) {
                return;
            }
            this.messDigestOTS.update(bArr2, r7, this.mdsize);
        }
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int r24;
        int r11;
        WinternitzOTSVerify winternitzOTSVerify = this;
        int r8 = winternitzOTSVerify.mdsize;
        byte[] bArr3 = new byte[r8];
        winternitzOTSVerify.messDigestOTS.update(bArr, 0, bArr.length);
        winternitzOTSVerify.messDigestOTS.doFinal(bArr3, 0);
        int r1 = winternitzOTSVerify.f2415w;
        int r112 = ((winternitzOTSVerify.mdsize << 3) + (r1 - 1)) / r1;
        int log = winternitzOTSVerify.getLog((r112 << r1) + 1);
        int r0 = winternitzOTSVerify.f2415w;
        int r2 = winternitzOTSVerify.mdsize;
        int r14 = r2 * ((((log + r0) - 1) / r0) + r112);
        if (r14 != bArr2.length) {
            return null;
        }
        byte[] bArr4 = new byte[r14];
        int r5 = 8;
        if (8 % r0 == 0) {
            int r52 = 8 / r0;
            int r16 = (1 << r0) - 1;
            int r02 = 0;
            int r12 = 0;
            int r4 = 0;
            while (r4 < r8) {
                int r17 = r12;
                int r3 = 0;
                while (r3 < r52) {
                    int r13 = bArr3[r4] & r16;
                    int r18 = r02 + r13;
                    int r03 = winternitzOTSVerify.mdsize;
                    int r19 = r4;
                    hashSignatureBlock(bArr2, r17 * r03, r16 - r13, bArr4, r17 * r03);
                    bArr3[r19] = (byte) (bArr3[r19] >>> winternitzOTSVerify.f2415w);
                    r17++;
                    r3++;
                    r02 = r18;
                    r4 = r19;
                    r52 = r52;
                }
                r4++;
                r12 = r17;
            }
            int r82 = r12;
            int r9 = (r112 << winternitzOTSVerify.f2415w) - r02;
            int r113 = 0;
            while (r113 < log) {
                int r15 = winternitzOTSVerify.mdsize;
                hashSignatureBlock(bArr2, r82 * r15, r16 - (r9 & r16), bArr4, r82 * r15);
                int r04 = winternitzOTSVerify.f2415w;
                r9 >>>= r04;
                r82++;
                r113 += r04;
            }
        } else {
            long j = 0;
            if (r0 < 8) {
                int r83 = r2 / r0;
                int r42 = (1 << r0) - 1;
                int r05 = 0;
                int r110 = 0;
                int r22 = 0;
                int r32 = 0;
                while (r32 < r83) {
                    int r182 = r05;
                    long j2 = 0;
                    int r06 = 0;
                    while (r06 < winternitzOTSVerify.f2415w) {
                        j2 ^= (bArr3[r182] & 255) << (r06 << 3);
                        r182++;
                        r06++;
                        log = log;
                    }
                    int r222 = log;
                    int r10 = r22;
                    int r122 = 0;
                    while (r122 < r5) {
                        int r07 = (int) (j2 & r42);
                        int r6 = r110 + r07;
                        int r111 = this.mdsize;
                        winternitzOTSVerify = this;
                        hashSignatureBlock(bArr2, r10 * r111, r42 - r07, bArr4, r10 * r111);
                        j2 >>>= winternitzOTSVerify.f2415w;
                        r10++;
                        r122++;
                        r42 = r42;
                        r32 = r32;
                        r110 = r6;
                        r5 = 8;
                    }
                    r32++;
                    r22 = r10;
                    r05 = r182;
                    log = r222;
                    r5 = 8;
                }
                int r223 = log;
                int r132 = r42;
                int r33 = winternitzOTSVerify.mdsize % winternitzOTSVerify.f2415w;
                int r43 = 0;
                while (r43 < r33) {
                    j ^= (bArr3[r05] & 255) << (r43 << 3);
                    r05++;
                    r43++;
                    r110 = r110;
                    r22 = r22;
                }
                int r102 = r22;
                int r92 = r33 << 3;
                int r84 = 0;
                while (r84 < r92) {
                    int r08 = (int) (j & r132);
                    int r123 = r110 + r08;
                    int r114 = winternitzOTSVerify.mdsize;
                    hashSignatureBlock(bArr2, r102 * r114, r132 - r08, bArr4, r102 * r114);
                    int r09 = winternitzOTSVerify.f2415w;
                    j >>>= r09;
                    r102++;
                    r84 += r09;
                    r110 = r123;
                }
                int r85 = (r112 << winternitzOTSVerify.f2415w) - r110;
                int r93 = 0;
                while (r93 < r223) {
                    int r115 = winternitzOTSVerify.mdsize;
                    hashSignatureBlock(bArr2, r102 * r115, r132 - (r85 & r132), bArr4, r102 * r115);
                    int r010 = winternitzOTSVerify.f2415w;
                    r85 >>>= r010;
                    r102++;
                    r93 += r010;
                }
            } else if (r0 < 57) {
                int r34 = (r2 << 3) - r0;
                int r011 = (1 << r0) - 1;
                byte[] bArr5 = new byte[r2];
                int r44 = 0;
                int r53 = 0;
                int r86 = 0;
                while (r44 <= r34) {
                    int r103 = r44 >>> 3;
                    int r133 = r44 % 8;
                    int r20 = r34;
                    int r45 = r44 + winternitzOTSVerify.f2415w;
                    int r35 = (r45 + 7) >>> 3;
                    long j3 = 0;
                    int r224 = 0;
                    while (r103 < r35) {
                        j3 ^= (bArr3[r103] & 255) << (r224 << 3);
                        r224++;
                        r103++;
                        r35 = r35;
                        r45 = r45;
                    }
                    int r27 = r45;
                    long j4 = j3 >>> r133;
                    int r104 = r14;
                    long j5 = r011;
                    long j6 = j4 & j5;
                    int r225 = r112;
                    r53 = (int) (r53 + j6);
                    int r105 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr2, r86 * r105, bArr5, 0, r105);
                    for (long j7 = j6; j7 < j5; j7++) {
                        winternitzOTSVerify.messDigestOTS.update(bArr5, 0, r2);
                        winternitzOTSVerify.messDigestOTS.doFinal(bArr5, 0);
                    }
                    int r46 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, r86 * r46, r46);
                    r86++;
                    r34 = r20;
                    r112 = r225;
                    r14 = r104;
                    r44 = r27;
                }
                int r226 = r112;
                r24 = r14;
                int r36 = r44 >>> 3;
                if (r36 < winternitzOTSVerify.mdsize) {
                    int r47 = r44 % 8;
                    int r106 = 0;
                    while (true) {
                        r11 = winternitzOTSVerify.mdsize;
                        if (r36 >= r11) {
                            break;
                        }
                        j ^= (bArr3[r36] & 255) << (r106 << 3);
                        r106++;
                        r36++;
                    }
                    long j8 = r011;
                    long j9 = (j >>> r47) & j8;
                    r53 = (int) (r53 + j9);
                    System.arraycopy(bArr2, r86 * r11, bArr5, 0, r11);
                    while (j9 < j8) {
                        winternitzOTSVerify.messDigestOTS.update(bArr5, 0, r2);
                        winternitzOTSVerify.messDigestOTS.doFinal(bArr5, 0);
                        j9++;
                    }
                    int r37 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, r86 * r37, r37);
                    r86++;
                }
                int r48 = (r226 << winternitzOTSVerify.f2415w) - r53;
                int r38 = 0;
                while (r38 < log) {
                    int r54 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr2, r86 * r54, bArr5, 0, r54);
                    for (long j10 = r48 & r011; j10 < r011; j10++) {
                        winternitzOTSVerify.messDigestOTS.update(bArr5, 0, r2);
                        winternitzOTSVerify.messDigestOTS.doFinal(bArr5, 0);
                    }
                    int r55 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, r86 * r55, r55);
                    int r56 = winternitzOTSVerify.f2415w;
                    r48 >>>= r56;
                    r86++;
                    r38 += r56;
                }
                winternitzOTSVerify.messDigestOTS.update(bArr4, 0, r24);
                byte[] bArr6 = new byte[winternitzOTSVerify.mdsize];
                winternitzOTSVerify.messDigestOTS.doFinal(bArr6, 0);
                return bArr6;
            }
        }
        r24 = r14;
        winternitzOTSVerify.messDigestOTS.update(bArr4, 0, r24);
        byte[] bArr62 = new byte[winternitzOTSVerify.mdsize];
        winternitzOTSVerify.messDigestOTS.doFinal(bArr62, 0);
        return bArr62;
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

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int r2 = this.f2415w;
        int r1 = ((digestSize << 3) + (r2 - 1)) / r2;
        int log = getLog((r1 << r2) + 1);
        int r3 = this.f2415w;
        return digestSize * (r1 + (((log + r3) - 1) / r3));
    }
}
