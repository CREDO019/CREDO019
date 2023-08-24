package org.bouncycastle.crypto.macs;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;

    /* renamed from: h0 */
    private int f2008h0;

    /* renamed from: h1 */
    private int f2009h1;

    /* renamed from: h2 */
    private int f2010h2;

    /* renamed from: h3 */
    private int f2011h3;

    /* renamed from: h4 */
    private int f2012h4;

    /* renamed from: k0 */
    private int f2013k0;

    /* renamed from: k1 */
    private int f2014k1;

    /* renamed from: k2 */
    private int f2015k2;

    /* renamed from: k3 */
    private int f2016k3;

    /* renamed from: r0 */
    private int f2017r0;

    /* renamed from: r1 */
    private int f2018r1;

    /* renamed from: r2 */
    private int f2019r2;

    /* renamed from: r3 */
    private int f2020r3;

    /* renamed from: r4 */
    private int f2021r4;

    /* renamed from: s1 */
    private int f2022s1;

    /* renamed from: s2 */
    private int f2023s2;

    /* renamed from: s3 */
    private int f2024s3;

    /* renamed from: s4 */
    private int f2025s4;
    private final byte[] singleByte;

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.cipher = blockCipher;
    }

    private static final long mul32x32_64(int r4, int r5) {
        return (r4 & BodyPartID.bodyIdMax) * r5;
    }

    private void processBlock() {
        int r1 = this.currentBlockOffset;
        if (r1 < 16) {
            this.currentBlock[r1] = 1;
            for (int r12 = r1 + 1; r12 < 16; r12++) {
                this.currentBlock[r12] = 0;
            }
        }
        long littleEndianToInt = Pack.littleEndianToInt(this.currentBlock, 0) & BodyPartID.bodyIdMax;
        long littleEndianToInt2 = Pack.littleEndianToInt(this.currentBlock, 4) & BodyPartID.bodyIdMax;
        long littleEndianToInt3 = Pack.littleEndianToInt(this.currentBlock, 8) & BodyPartID.bodyIdMax;
        long littleEndianToInt4 = BodyPartID.bodyIdMax & Pack.littleEndianToInt(this.currentBlock, 12);
        int r8 = (int) (this.f2008h0 + (littleEndianToInt & 67108863));
        this.f2008h0 = r8;
        this.f2009h1 = (int) (this.f2009h1 + ((((littleEndianToInt2 << 32) | littleEndianToInt) >>> 26) & 67108863));
        this.f2010h2 = (int) (this.f2010h2 + (((littleEndianToInt2 | (littleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.f2011h3 = (int) (this.f2011h3 + ((((littleEndianToInt4 << 32) | littleEndianToInt3) >>> 14) & 67108863));
        int r2 = (int) (this.f2012h4 + (littleEndianToInt4 >>> 8));
        this.f2012h4 = r2;
        if (this.currentBlockOffset == 16) {
            this.f2012h4 = r2 + 16777216;
        }
        long mul32x32_64 = mul32x32_64(r8, this.f2017r0) + mul32x32_64(this.f2009h1, this.f2025s4) + mul32x32_64(this.f2010h2, this.f2024s3) + mul32x32_64(this.f2011h3, this.f2023s2) + mul32x32_64(this.f2012h4, this.f2022s1);
        long mul32x32_642 = mul32x32_64(this.f2008h0, this.f2018r1) + mul32x32_64(this.f2009h1, this.f2017r0) + mul32x32_64(this.f2010h2, this.f2025s4) + mul32x32_64(this.f2011h3, this.f2024s3) + mul32x32_64(this.f2012h4, this.f2023s2);
        long mul32x32_643 = mul32x32_64(this.f2008h0, this.f2019r2) + mul32x32_64(this.f2009h1, this.f2018r1) + mul32x32_64(this.f2010h2, this.f2017r0) + mul32x32_64(this.f2011h3, this.f2025s4) + mul32x32_64(this.f2012h4, this.f2024s3);
        long mul32x32_644 = mul32x32_64(this.f2008h0, this.f2020r3) + mul32x32_64(this.f2009h1, this.f2019r2) + mul32x32_64(this.f2010h2, this.f2018r1) + mul32x32_64(this.f2011h3, this.f2017r0) + mul32x32_64(this.f2012h4, this.f2025s4);
        long mul32x32_645 = mul32x32_64(this.f2008h0, this.f2021r4) + mul32x32_64(this.f2009h1, this.f2020r3) + mul32x32_64(this.f2010h2, this.f2019r2) + mul32x32_64(this.f2011h3, this.f2018r1) + mul32x32_64(this.f2012h4, this.f2017r0);
        long j = mul32x32_642 + (mul32x32_64 >>> 26);
        long j2 = mul32x32_643 + (j >>> 26);
        this.f2010h2 = ((int) j2) & 67108863;
        long j3 = mul32x32_644 + (j2 >>> 26);
        this.f2011h3 = ((int) j3) & 67108863;
        long j4 = mul32x32_645 + (j3 >>> 26);
        this.f2012h4 = ((int) j4) & 67108863;
        int r11 = (((int) mul32x32_64) & 67108863) + (((int) (j4 >>> 26)) * 5);
        this.f2009h1 = (((int) j) & 67108863) + (r11 >>> 26);
        this.f2008h0 = r11 & 67108863;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        int r1 = 16;
        if (this.cipher != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        this.f2017r0 = 67108863 & littleEndianToInt;
        int r2 = ((littleEndianToInt >>> 26) | (littleEndianToInt2 << 6)) & 67108611;
        this.f2018r1 = r2;
        int r3 = ((littleEndianToInt2 >>> 20) | (littleEndianToInt3 << 12)) & 67092735;
        this.f2019r2 = r3;
        int r5 = ((littleEndianToInt3 >>> 14) | (littleEndianToInt4 << 18)) & 66076671;
        this.f2020r3 = r5;
        int r4 = (littleEndianToInt4 >>> 8) & 1048575;
        this.f2021r4 = r4;
        this.f2022s1 = r2 * 5;
        this.f2023s2 = r3 * 5;
        this.f2024s3 = r5 * 5;
        this.f2025s4 = r4 * 5;
        BlockCipher blockCipher = this.cipher;
        if (blockCipher != null) {
            byte[] bArr3 = new byte[16];
            blockCipher.init(true, new KeyParameter(bArr, 16, 16));
            this.cipher.processBlock(bArr2, 0, bArr3, 0);
            bArr = bArr3;
            r1 = 0;
        }
        this.f2013k0 = Pack.littleEndianToInt(bArr, r1 + 0);
        this.f2014k1 = Pack.littleEndianToInt(bArr, r1 + 4);
        this.f2015k2 = Pack.littleEndianToInt(bArr, r1 + 8);
        this.f2016k3 = Pack.littleEndianToInt(bArr, r1 + 12);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r15) throws DataLengthException, IllegalStateException {
        int r1;
        int r0;
        int r3;
        int r2;
        int r4;
        if (r15 + 16 <= bArr.length) {
            if (this.currentBlockOffset > 0) {
                processBlock();
            }
            int r02 = this.f2009h1;
            int r12 = this.f2008h0;
            int r03 = r02 + (r12 >>> 26);
            int r32 = this.f2010h2 + (r03 >>> 26);
            int r42 = this.f2011h3 + (r32 >>> 26);
            int r33 = r32 & 67108863;
            int r5 = this.f2012h4 + (r42 >>> 26);
            int r43 = r42 & 67108863;
            int r13 = (r12 & 67108863) + ((r5 >>> 26) * 5);
            int r52 = r5 & 67108863;
            int r04 = (r03 & 67108863) + (r13 >>> 26);
            int r14 = r13 & 67108863;
            int r6 = r14 + 5;
            int r7 = (r6 >>> 26) + r04;
            int r8 = (r7 >>> 26) + r33;
            int r9 = (r8 >>> 26) + r43;
            int r22 = 67108863 & r9;
            int r10 = ((r9 >>> 26) + r52) - 67108864;
            int r92 = (r10 >>> 31) - 1;
            int r11 = ~r92;
            this.f2008h0 = (r14 & r11) | (r6 & 67108863 & r92);
            this.f2009h1 = (r04 & r11) | (r7 & 67108863 & r92);
            this.f2010h2 = (r33 & r11) | (r8 & 67108863 & r92);
            this.f2011h3 = (r22 & r92) | (r43 & r11);
            this.f2012h4 = (r52 & r11) | (r10 & r92);
            long j = ((r1 | (r0 << 26)) & BodyPartID.bodyIdMax) + (this.f2013k0 & BodyPartID.bodyIdMax);
            long j2 = (((r0 >>> 6) | (r3 << 20)) & BodyPartID.bodyIdMax) + (this.f2014k1 & BodyPartID.bodyIdMax);
            long j3 = (((r3 >>> 12) | (r2 << 14)) & BodyPartID.bodyIdMax) + (this.f2015k2 & BodyPartID.bodyIdMax);
            Pack.intToLittleEndian((int) j, bArr, r15);
            long j4 = j2 + (j >>> 32);
            Pack.intToLittleEndian((int) j4, bArr, r15 + 4);
            long j5 = j3 + (j4 >>> 32);
            Pack.intToLittleEndian((int) j5, bArr, r15 + 8);
            Pack.intToLittleEndian((int) ((((r2 >>> 18) | (r4 << 8)) & BodyPartID.bodyIdMax) + (BodyPartID.bodyIdMax & this.f2016k3) + (j5 >>> 32)), bArr, r15 + 12);
            reset();
            return 16;
        }
        throw new OutputLengthException("Output buffer is too short.");
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        if (this.cipher == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.cipher.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        if (this.cipher == null) {
            bArr = null;
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        }
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        setKey(((KeyParameter) cipherParameters).getKey(), bArr);
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.currentBlockOffset = 0;
        this.f2012h4 = 0;
        this.f2011h3 = 0;
        this.f2010h2 = 0;
        this.f2009h1 = 0;
        this.f2008h0 = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r8, int r9) throws DataLengthException, IllegalStateException {
        int r1 = 0;
        while (r9 > r1) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int min = Math.min(r9 - r1, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, r1 + r8, this.currentBlock, this.currentBlockOffset, min);
            r1 += min;
            this.currentBlockOffset += min;
        }
    }
}
