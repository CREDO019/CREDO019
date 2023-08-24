package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SHA224Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 28;

    /* renamed from: K */
    static final int[] f1824K = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};

    /* renamed from: H1 */
    private int f1825H1;

    /* renamed from: H2 */
    private int f1826H2;

    /* renamed from: H3 */
    private int f1827H3;

    /* renamed from: H4 */
    private int f1828H4;

    /* renamed from: H5 */
    private int f1829H5;

    /* renamed from: H6 */
    private int f1830H6;

    /* renamed from: H7 */
    private int f1831H7;

    /* renamed from: H8 */
    private int f1832H8;

    /* renamed from: X */
    private int[] f1833X;
    private int xOff;

    public SHA224Digest() {
        this.f1833X = new int[64];
        reset();
    }

    public SHA224Digest(SHA224Digest sHA224Digest) {
        super(sHA224Digest);
        this.f1833X = new int[64];
        doCopy(sHA224Digest);
    }

    public SHA224Digest(byte[] bArr) {
        super(bArr);
        this.f1833X = new int[64];
        this.f1825H1 = Pack.bigEndianToInt(bArr, 16);
        this.f1826H2 = Pack.bigEndianToInt(bArr, 20);
        this.f1827H3 = Pack.bigEndianToInt(bArr, 24);
        this.f1828H4 = Pack.bigEndianToInt(bArr, 28);
        this.f1829H5 = Pack.bigEndianToInt(bArr, 32);
        this.f1830H6 = Pack.bigEndianToInt(bArr, 36);
        this.f1831H7 = Pack.bigEndianToInt(bArr, 40);
        this.f1832H8 = Pack.bigEndianToInt(bArr, 44);
        this.xOff = Pack.bigEndianToInt(bArr, 48);
        for (int r0 = 0; r0 != this.xOff; r0++) {
            this.f1833X[r0] = Pack.bigEndianToInt(bArr, (r0 * 4) + 52);
        }
    }

    /* renamed from: Ch */
    private int m74Ch(int r1, int r2, int r3) {
        return ((~r1) & r3) ^ (r2 & r1);
    }

    private int Maj(int r2, int r3, int r4) {
        return ((r2 & r4) ^ (r2 & r3)) ^ (r3 & r4);
    }

    private int Sum0(int r4) {
        return ((r4 << 10) | (r4 >>> 22)) ^ (((r4 >>> 2) | (r4 << 30)) ^ ((r4 >>> 13) | (r4 << 19)));
    }

    private int Sum1(int r4) {
        return ((r4 << 7) | (r4 >>> 25)) ^ (((r4 >>> 6) | (r4 << 26)) ^ ((r4 >>> 11) | (r4 << 21)));
    }

    private int Theta0(int r4) {
        return (r4 >>> 3) ^ (((r4 >>> 7) | (r4 << 25)) ^ ((r4 >>> 18) | (r4 << 14)));
    }

    private int Theta1(int r4) {
        return (r4 >>> 10) ^ (((r4 >>> 17) | (r4 << 15)) ^ ((r4 >>> 19) | (r4 << 13)));
    }

    private void doCopy(SHA224Digest sHA224Digest) {
        super.copyIn(sHA224Digest);
        this.f1825H1 = sHA224Digest.f1825H1;
        this.f1826H2 = sHA224Digest.f1826H2;
        this.f1827H3 = sHA224Digest.f1827H3;
        this.f1828H4 = sHA224Digest.f1828H4;
        this.f1829H5 = sHA224Digest.f1829H5;
        this.f1830H6 = sHA224Digest.f1830H6;
        this.f1831H7 = sHA224Digest.f1831H7;
        this.f1832H8 = sHA224Digest.f1832H8;
        int[] r0 = sHA224Digest.f1833X;
        System.arraycopy(r0, 0, this.f1833X, 0, r0.length);
        this.xOff = sHA224Digest.xOff;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA224Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) {
        finish();
        Pack.intToBigEndian(this.f1825H1, bArr, r4);
        Pack.intToBigEndian(this.f1826H2, bArr, r4 + 4);
        Pack.intToBigEndian(this.f1827H3, bArr, r4 + 8);
        Pack.intToBigEndian(this.f1828H4, bArr, r4 + 12);
        Pack.intToBigEndian(this.f1829H5, bArr, r4 + 16);
        Pack.intToBigEndian(this.f1830H6, bArr, r4 + 20);
        Pack.intToBigEndian(this.f1831H7, bArr, r4 + 24);
        reset();
        return 28;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA224;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 28;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 52];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f1825H1, bArr, 16);
        Pack.intToBigEndian(this.f1826H2, bArr, 20);
        Pack.intToBigEndian(this.f1827H3, bArr, 24);
        Pack.intToBigEndian(this.f1828H4, bArr, 28);
        Pack.intToBigEndian(this.f1829H5, bArr, 32);
        Pack.intToBigEndian(this.f1830H6, bArr, 36);
        Pack.intToBigEndian(this.f1831H7, bArr, 40);
        Pack.intToBigEndian(this.f1832H8, bArr, 44);
        Pack.intToBigEndian(this.xOff, bArr, 48);
        for (int r1 = 0; r1 != this.xOff; r1++) {
            Pack.intToBigEndian(this.f1833X[r1], bArr, (r1 * 4) + 52);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int r1 = 16; r1 <= 63; r1++) {
            int[] r2 = this.f1833X;
            int Theta1 = Theta1(r2[r1 - 2]);
            int[] r4 = this.f1833X;
            r2[r1] = Theta1 + r4[r1 - 7] + Theta0(r4[r1 - 15]) + this.f1833X[r1 - 16];
        }
        int r12 = this.f1825H1;
        int r22 = this.f1826H2;
        int r3 = this.f1827H3;
        int r42 = this.f1828H4;
        int r5 = this.f1829H5;
        int r6 = this.f1830H6;
        int r7 = this.f1831H7;
        int r8 = this.f1832H8;
        int r11 = 0;
        for (int r10 = 0; r10 < 8; r10++) {
            int Sum1 = Sum1(r5) + m74Ch(r5, r6, r7);
            int[] r13 = f1824K;
            int r82 = r8 + Sum1 + r13[r11] + this.f1833X[r11];
            int r43 = r42 + r82;
            int Sum0 = r82 + Sum0(r12) + Maj(r12, r22, r3);
            int r112 = r11 + 1;
            int Sum12 = r7 + Sum1(r43) + m74Ch(r43, r5, r6) + r13[r112] + this.f1833X[r112];
            int r32 = r3 + Sum12;
            int Sum02 = Sum12 + Sum0(Sum0) + Maj(Sum0, r12, r22);
            int r113 = r112 + 1;
            int Sum13 = r6 + Sum1(r32) + m74Ch(r32, r43, r5) + r13[r113] + this.f1833X[r113];
            int r23 = r22 + Sum13;
            int Sum03 = Sum13 + Sum0(Sum02) + Maj(Sum02, Sum0, r12);
            int r114 = r113 + 1;
            int Sum14 = r5 + Sum1(r23) + m74Ch(r23, r32, r43) + r13[r114] + this.f1833X[r114];
            int r14 = r12 + Sum14;
            int Sum04 = Sum14 + Sum0(Sum03) + Maj(Sum03, Sum02, Sum0);
            int r115 = r114 + 1;
            int Sum15 = r43 + Sum1(r14) + m74Ch(r14, r23, r32) + r13[r115] + this.f1833X[r115];
            r8 = Sum0 + Sum15;
            r42 = Sum15 + Sum0(Sum04) + Maj(Sum04, Sum03, Sum02);
            int r116 = r115 + 1;
            int Sum16 = r32 + Sum1(r8) + m74Ch(r8, r14, r23) + r13[r116] + this.f1833X[r116];
            r7 = Sum02 + Sum16;
            r3 = Sum16 + Sum0(r42) + Maj(r42, Sum04, Sum03);
            int r117 = r116 + 1;
            int Sum17 = r23 + Sum1(r7) + m74Ch(r7, r8, r14) + r13[r117] + this.f1833X[r117];
            r6 = Sum03 + Sum17;
            r22 = Sum17 + Sum0(r3) + Maj(r3, r42, Sum04);
            int r118 = r117 + 1;
            int Sum18 = r14 + Sum1(r6) + m74Ch(r6, r7, r8) + r13[r118] + this.f1833X[r118];
            r5 = Sum04 + Sum18;
            r12 = Sum18 + Sum0(r22) + Maj(r22, r3, r42);
            r11 = r118 + 1;
        }
        this.f1825H1 += r12;
        this.f1826H2 += r22;
        this.f1827H3 += r3;
        this.f1828H4 += r42;
        this.f1829H5 += r5;
        this.f1830H6 += r6;
        this.f1831H7 += r7;
        this.f1832H8 += r8;
        this.xOff = 0;
        for (int r15 = 0; r15 < 16; r15++) {
            this.f1833X[r15] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] r0 = this.f1833X;
        r0[14] = (int) (j >>> 32);
        r0[15] = (int) (j & (-1));
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int r5) {
        int r52 = r5 + 1;
        int r53 = r52 + 1;
        int r4 = (bArr[r53 + 1] & 255) | (bArr[r5] << Ascii.CAN) | ((bArr[r52] & 255) << 16) | ((bArr[r53] & 255) << 8);
        int[] r54 = this.f1833X;
        int r0 = this.xOff;
        r54[r0] = r4;
        int r02 = r0 + 1;
        this.xOff = r02;
        if (r02 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f1825H1 = -1056596264;
        this.f1826H2 = 914150663;
        this.f1827H3 = 812702999;
        this.f1828H4 = -150054599;
        this.f1829H5 = -4191439;
        this.f1830H6 = 1750603025;
        this.f1831H7 = 1694076839;
        this.f1832H8 = -1090891868;
        this.xOff = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.f1833X;
            if (r1 == r2.length) {
                return;
            }
            r2[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        doCopy((SHA224Digest) memoable);
    }
}
