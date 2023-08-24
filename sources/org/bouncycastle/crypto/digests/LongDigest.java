package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public abstract class LongDigest implements ExtendedDigest, Memoable, EncodableDigest {
    private static final int BYTE_LENGTH = 128;

    /* renamed from: K */
    static final long[] f1758K = {4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};

    /* renamed from: H1 */
    protected long f1759H1;

    /* renamed from: H2 */
    protected long f1760H2;

    /* renamed from: H3 */
    protected long f1761H3;

    /* renamed from: H4 */
    protected long f1762H4;

    /* renamed from: H5 */
    protected long f1763H5;

    /* renamed from: H6 */
    protected long f1764H6;

    /* renamed from: H7 */
    protected long f1765H7;

    /* renamed from: H8 */
    protected long f1766H8;

    /* renamed from: W */
    private long[] f1767W;
    private long byteCount1;
    private long byteCount2;
    private int wOff;
    private byte[] xBuf;
    private int xBufOff;

    /* JADX INFO: Access modifiers changed from: protected */
    public LongDigest() {
        this.xBuf = new byte[8];
        this.f1767W = new long[80];
        this.xBufOff = 0;
        reset();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LongDigest(LongDigest longDigest) {
        this.xBuf = new byte[8];
        this.f1767W = new long[80];
        copyIn(longDigest);
    }

    /* renamed from: Ch */
    private long m115Ch(long j, long j2, long j3) {
        return ((~j) & j3) ^ (j2 & j);
    }

    private long Maj(long j, long j2, long j3) {
        return ((j & j3) ^ (j & j2)) ^ (j2 & j3);
    }

    private long Sigma0(long j) {
        return (j >>> 7) ^ (((j << 63) | (j >>> 1)) ^ ((j << 56) | (j >>> 8)));
    }

    private long Sigma1(long j) {
        return (j >>> 6) ^ (((j << 45) | (j >>> 19)) ^ ((j << 3) | (j >>> 61)));
    }

    private long Sum0(long j) {
        return ((j >>> 39) | (j << 25)) ^ (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34)));
    }

    private long Sum1(long j) {
        return ((j >>> 41) | (j << 23)) ^ (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18)));
    }

    private void adjustByteCounts() {
        long j = this.byteCount1;
        if (j > 2305843009213693951L) {
            this.byteCount2 += j >>> 61;
            this.byteCount1 = j & 2305843009213693951L;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copyIn(LongDigest longDigest) {
        byte[] bArr = longDigest.xBuf;
        System.arraycopy(bArr, 0, this.xBuf, 0, bArr.length);
        this.xBufOff = longDigest.xBufOff;
        this.byteCount1 = longDigest.byteCount1;
        this.byteCount2 = longDigest.byteCount2;
        this.f1759H1 = longDigest.f1759H1;
        this.f1760H2 = longDigest.f1760H2;
        this.f1761H3 = longDigest.f1761H3;
        this.f1762H4 = longDigest.f1762H4;
        this.f1763H5 = longDigest.f1763H5;
        this.f1764H6 = longDigest.f1764H6;
        this.f1765H7 = longDigest.f1765H7;
        this.f1766H8 = longDigest.f1766H8;
        long[] jArr = longDigest.f1767W;
        System.arraycopy(jArr, 0, this.f1767W, 0, jArr.length);
        this.wOff = longDigest.wOff;
    }

    public void finish() {
        adjustByteCounts();
        long j = this.byteCount1 << 3;
        long j2 = this.byteCount2;
        byte b = Byte.MIN_VALUE;
        while (true) {
            update(b);
            if (this.xBufOff == 0) {
                processLength(j, j2);
                processBlock();
                return;
            }
            b = 0;
        }
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 128;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getEncodedStateSize() {
        return (this.wOff * 8) + 96;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void populateState(byte[] bArr) {
        System.arraycopy(this.xBuf, 0, bArr, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, bArr, 8);
        Pack.longToBigEndian(this.byteCount1, bArr, 12);
        Pack.longToBigEndian(this.byteCount2, bArr, 20);
        Pack.longToBigEndian(this.f1759H1, bArr, 28);
        Pack.longToBigEndian(this.f1760H2, bArr, 36);
        Pack.longToBigEndian(this.f1761H3, bArr, 44);
        Pack.longToBigEndian(this.f1762H4, bArr, 52);
        Pack.longToBigEndian(this.f1763H5, bArr, 60);
        Pack.longToBigEndian(this.f1764H6, bArr, 68);
        Pack.longToBigEndian(this.f1765H7, bArr, 76);
        Pack.longToBigEndian(this.f1766H8, bArr, 84);
        Pack.intToBigEndian(this.wOff, bArr, 92);
        for (int r2 = 0; r2 < this.wOff; r2++) {
            Pack.longToBigEndian(this.f1767W[r2], bArr, (r2 * 8) + 96);
        }
    }

    protected void processBlock() {
        adjustByteCounts();
        for (int r0 = 16; r0 <= 79; r0++) {
            long[] jArr = this.f1767W;
            long Sigma1 = Sigma1(jArr[r0 - 2]);
            long[] jArr2 = this.f1767W;
            jArr[r0] = Sigma1 + jArr2[r0 - 7] + Sigma0(jArr2[r0 - 15]) + this.f1767W[r0 - 16];
        }
        long j = this.f1759H1;
        long j2 = this.f1760H2;
        long j3 = this.f1761H3;
        long j4 = this.f1762H4;
        long j5 = this.f1763H5;
        long j6 = this.f1764H6;
        long j7 = this.f1765H7;
        long j8 = j6;
        long j9 = j4;
        int r23 = 0;
        long j10 = j2;
        long j11 = j3;
        long j12 = j5;
        int r3 = 0;
        long j13 = this.f1766H8;
        long j14 = j;
        long j15 = j7;
        while (r3 < 10) {
            long j16 = j12;
            long[] jArr3 = f1758K;
            int r30 = r23 + 1;
            long Sum1 = j13 + Sum1(j12) + m115Ch(j12, j8, j15) + jArr3[r23] + this.f1767W[r23];
            long j17 = j9 + Sum1;
            long Sum0 = Sum1 + Sum0(j14) + Maj(j14, j10, j11);
            int r25 = r30 + 1;
            long Sum12 = j15 + Sum1(j17) + m115Ch(j17, j16, j8) + jArr3[r30] + this.f1767W[r30];
            long j18 = j11 + Sum12;
            long Sum02 = Sum12 + Sum0(Sum0) + Maj(Sum0, j14, j10);
            int r12 = r25 + 1;
            long Sum13 = j8 + Sum1(j18) + m115Ch(j18, j17, j16) + jArr3[r25] + this.f1767W[r25];
            long j19 = j10 + Sum13;
            long Sum03 = Sum13 + Sum0(Sum02) + Maj(Sum02, Sum0, j14);
            int r252 = r12 + 1;
            long Sum14 = j16 + Sum1(j19) + m115Ch(j19, j18, j17) + jArr3[r12] + this.f1767W[r12];
            long j20 = j14 + Sum14;
            long Sum04 = Sum14 + Sum0(Sum03) + Maj(Sum03, Sum02, Sum0);
            int r122 = r252 + 1;
            long Sum15 = j17 + Sum1(j20) + m115Ch(j20, j19, j18) + jArr3[r252] + this.f1767W[r252];
            long j21 = Sum0 + Sum15;
            long Sum05 = Sum15 + Sum0(Sum04) + Maj(Sum04, Sum03, Sum02);
            int r253 = r122 + 1;
            long Sum16 = j18 + Sum1(j21) + m115Ch(j21, j20, j19) + jArr3[r122] + this.f1767W[r122];
            long j22 = Sum02 + Sum16;
            long Sum06 = Sum16 + Sum0(Sum05) + Maj(Sum05, Sum04, Sum03);
            j15 = j22;
            int r123 = r253 + 1;
            long Sum17 = j19 + Sum1(j22) + m115Ch(j22, j21, j20) + jArr3[r253] + this.f1767W[r253];
            long j23 = Sum03 + Sum17;
            j8 = j23;
            j10 = Sum17 + Sum0(Sum06) + Maj(Sum06, Sum05, Sum04);
            long Sum18 = j20 + Sum1(j23) + m115Ch(j23, j15, j21) + jArr3[r123] + this.f1767W[r123];
            long Sum07 = Sum18 + Sum0(j10) + Maj(j10, Sum06, Sum05);
            r3++;
            j12 = Sum04 + Sum18;
            j11 = Sum06;
            j13 = j21;
            j9 = Sum05;
            r23 = r123 + 1;
            j14 = Sum07;
        }
        this.f1759H1 += j14;
        this.f1760H2 += j10;
        this.f1761H3 += j11;
        this.f1762H4 += j9;
        this.f1763H5 += j12;
        this.f1764H6 += j8;
        this.f1765H7 += j15;
        this.f1766H8 += j13;
        this.wOff = 0;
        for (int r10 = 0; r10 < 16; r10++) {
            this.f1767W[r10] = 0;
        }
    }

    protected void processLength(long j, long j2) {
        if (this.wOff > 14) {
            processBlock();
        }
        long[] jArr = this.f1767W;
        jArr[14] = j2;
        jArr[15] = j;
    }

    protected void processWord(byte[] bArr, int r4) {
        this.f1767W[this.wOff] = Pack.bigEndianToLong(bArr, r4);
        int r3 = this.wOff + 1;
        this.wOff = r3;
        if (r3 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.byteCount1 = 0L;
        this.byteCount2 = 0L;
        int r2 = 0;
        this.xBufOff = 0;
        int r3 = 0;
        while (true) {
            byte[] bArr = this.xBuf;
            if (r3 >= bArr.length) {
                break;
            }
            bArr[r3] = 0;
            r3++;
        }
        this.wOff = 0;
        while (true) {
            long[] jArr = this.f1767W;
            if (r2 == jArr.length) {
                return;
            }
            jArr[r2] = 0;
            r2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restoreState(byte[] bArr) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, 8);
        this.xBufOff = bigEndianToInt;
        System.arraycopy(bArr, 0, this.xBuf, 0, bigEndianToInt);
        this.byteCount1 = Pack.bigEndianToLong(bArr, 12);
        this.byteCount2 = Pack.bigEndianToLong(bArr, 20);
        this.f1759H1 = Pack.bigEndianToLong(bArr, 28);
        this.f1760H2 = Pack.bigEndianToLong(bArr, 36);
        this.f1761H3 = Pack.bigEndianToLong(bArr, 44);
        this.f1762H4 = Pack.bigEndianToLong(bArr, 52);
        this.f1763H5 = Pack.bigEndianToLong(bArr, 60);
        this.f1764H6 = Pack.bigEndianToLong(bArr, 68);
        this.f1765H7 = Pack.bigEndianToLong(bArr, 76);
        this.f1766H8 = Pack.bigEndianToLong(bArr, 84);
        this.wOff = Pack.bigEndianToInt(bArr, 92);
        for (int r2 = 0; r2 < this.wOff; r2++) {
            this.f1767W[r2] = Pack.bigEndianToLong(bArr, (r2 * 8) + 96);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.xBuf;
        int r1 = this.xBufOff;
        int r2 = r1 + 1;
        this.xBufOff = r2;
        bArr[r1] = b;
        if (r2 == bArr.length) {
            processWord(bArr, 0);
            this.xBufOff = 0;
        }
        this.byteCount1++;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r7, int r8) {
        while (this.xBufOff != 0 && r8 > 0) {
            update(bArr[r7]);
            r7++;
            r8--;
        }
        while (r8 > this.xBuf.length) {
            processWord(bArr, r7);
            byte[] bArr2 = this.xBuf;
            r7 += bArr2.length;
            r8 -= bArr2.length;
            this.byteCount1 += bArr2.length;
        }
        while (r8 > 0) {
            update(bArr[r7]);
            r7++;
            r8--;
        }
    }
}
