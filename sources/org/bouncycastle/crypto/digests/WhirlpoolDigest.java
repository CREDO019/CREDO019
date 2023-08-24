package org.bouncycastle.crypto.digests;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.canhub.cropper.CropImage;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;

/* loaded from: classes5.dex */
public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    private static final int BITCOUNT_ARRAY_SIZE = 32;
    private static final int BYTE_LENGTH = 64;
    private static final int DIGEST_LENGTH_BYTES = 64;
    private static final short[] EIGHT;
    private static final int REDUCTION_POLYNOMIAL = 285;
    private static final int ROUNDS = 10;

    /* renamed from: _K */
    private long[] f1863_K;

    /* renamed from: _L */
    private long[] f1864_L;
    private short[] _bitCount;
    private long[] _block;
    private byte[] _buffer;
    private int _bufferPos;
    private long[] _hash;
    private final long[] _rc;
    private long[] _state;
    private static final int[] SBOX = {24, 35, 198, 232, TsExtractor.TS_STREAM_TYPE_E_AC3, 184, 1, 79, 54, 166, 210, 245, 121, 111, 145, 82, 96, 188, 155, 142, 163, 12, 123, 53, 29, 224, JfifUtil.MARKER_RST7, 194, 46, 75, 254, 87, 21, 119, 55, 229, 159, PsExtractor.VIDEO_STREAM_MASK, 74, JfifUtil.MARKER_SOS, 88, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE, 41, 10, 177, 160, 107, 133, PsExtractor.PRIVATE_STREAM_1, 93, 16, 244, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE, 62, 5, 103, 228, 39, 65, 139, 167, 125, 149, JfifUtil.MARKER_SOI, 251, 238, 124, 102, 221, 23, 71, 158, 202, 45, 191, 7, 173, 90, 131, 51, 99, 2, 170, 113, 200, 25, 73, JfifUtil.MARKER_EOI, 242, 227, 91, 136, 154, 38, 50, 176, 233, 15, 213, 128, 190, 205, 52, 72, 255, 122, 144, 95, 32, 104, 26, 174, RotationOptions.ROTATE_180, 84, 147, 34, 100, 241, 115, 18, 64, 8, 195, 236, 219, 161, 141, 61, 151, 0, 207, 43, 118, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 214, 27, 181, 175, 106, 80, 69, 243, 48, 239, 63, 85, 162, 234, 101, 186, 47, 192, 222, 28, 253, 77, 146, 117, 6, TsExtractor.TS_STREAM_TYPE_DTS, 178, 230, 14, 31, 98, 212, 168, 150, 249, 197, 37, 89, 132, 114, 57, 76, 94, 120, 56, 140, 209, 165, 226, 97, 179, 33, 156, 30, 67, 199, 252, 4, 81, 153, 109, 13, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 223, 126, 36, 59, 171, 206, 17, 143, 78, 183, 235, 60, TsExtractor.TS_STREAM_TYPE_AC3, 148, 247, 185, 19, 44, Primes.SMALL_FACTOR_LIMIT, 231, 110, 196, 3, 86, 68, 127, 169, 42, 187, 193, 83, 220, 11, 157, 108, 49, 116, 246, 70, TsExtractor.TS_STREAM_TYPE_AC4, 137, 20, JfifUtil.MARKER_APP1, 22, 58, 105, 9, 112, 182, JfifUtil.MARKER_RST0, 237, CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, 66, 152, 164, 40, 92, 248, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO};

    /* renamed from: C0 */
    private static final long[] f1855C0 = new long[256];

    /* renamed from: C1 */
    private static final long[] f1856C1 = new long[256];

    /* renamed from: C2 */
    private static final long[] f1857C2 = new long[256];

    /* renamed from: C3 */
    private static final long[] f1858C3 = new long[256];

    /* renamed from: C4 */
    private static final long[] f1859C4 = new long[256];

    /* renamed from: C5 */
    private static final long[] f1860C5 = new long[256];

    /* renamed from: C6 */
    private static final long[] f1861C6 = new long[256];

    /* renamed from: C7 */
    private static final long[] f1862C7 = new long[256];

    static {
        short[] sArr = new short[32];
        EIGHT = sArr;
        sArr[31] = 8;
    }

    public WhirlpoolDigest() {
        this._rc = new long[11];
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this.f1863_K = new long[8];
        this.f1864_L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        for (int r12 = 0; r12 < 256; r12++) {
            int r13 = SBOX[r12];
            int maskWithReductionPolynomial = maskWithReductionPolynomial(r13 << 1);
            int maskWithReductionPolynomial2 = maskWithReductionPolynomial(maskWithReductionPolynomial << 1);
            int r16 = maskWithReductionPolynomial2 ^ r13;
            int maskWithReductionPolynomial3 = maskWithReductionPolynomial(maskWithReductionPolynomial2 << 1);
            int r18 = maskWithReductionPolynomial3 ^ r13;
            f1855C0[r12] = packIntoLong(r13, r13, maskWithReductionPolynomial2, r13, maskWithReductionPolynomial3, r16, maskWithReductionPolynomial, r18);
            f1856C1[r12] = packIntoLong(r18, r13, r13, maskWithReductionPolynomial2, r13, maskWithReductionPolynomial3, r16, maskWithReductionPolynomial);
            f1857C2[r12] = packIntoLong(maskWithReductionPolynomial, r18, r13, r13, maskWithReductionPolynomial2, r13, maskWithReductionPolynomial3, r16);
            f1858C3[r12] = packIntoLong(r16, maskWithReductionPolynomial, r18, r13, r13, maskWithReductionPolynomial2, r13, maskWithReductionPolynomial3);
            f1859C4[r12] = packIntoLong(maskWithReductionPolynomial3, r16, maskWithReductionPolynomial, r18, r13, r13, maskWithReductionPolynomial2, r13);
            f1860C5[r12] = packIntoLong(r13, maskWithReductionPolynomial3, r16, maskWithReductionPolynomial, r18, r13, r13, maskWithReductionPolynomial2);
            f1861C6[r12] = packIntoLong(maskWithReductionPolynomial2, r13, maskWithReductionPolynomial3, r16, maskWithReductionPolynomial, r18, r13, r13);
            f1862C7[r12] = packIntoLong(r13, maskWithReductionPolynomial2, r13, maskWithReductionPolynomial3, r16, maskWithReductionPolynomial, r18, r13);
        }
        this._rc[0] = 0;
        for (int r0 = 1; r0 <= 10; r0++) {
            int r1 = (r0 - 1) * 8;
            this._rc[r0] = (((((((f1855C0[r1] & (-72057594037927936L)) ^ (f1856C1[r1 + 1] & 71776119061217280L)) ^ (f1857C2[r1 + 2] & 280375465082880L)) ^ (f1858C3[r1 + 3] & 1095216660480L)) ^ (f1859C4[r1 + 4] & 4278190080L)) ^ (f1860C5[r1 + 5] & 16711680)) ^ (f1861C6[r1 + 6] & 65280)) ^ (f1862C7[r1 + 7] & 255);
        }
    }

    public WhirlpoolDigest(WhirlpoolDigest whirlpoolDigest) {
        this._rc = new long[11];
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this.f1863_K = new long[8];
        this.f1864_L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        reset(whirlpoolDigest);
    }

    private long bytesToLongFromBuffer(byte[] bArr, int r9) {
        return (bArr[r9 + 7] & 255) | ((bArr[r9 + 0] & 255) << 56) | ((bArr[r9 + 1] & 255) << 48) | ((bArr[r9 + 2] & 255) << 40) | ((bArr[r9 + 3] & 255) << 32) | ((bArr[r9 + 4] & 255) << 24) | ((bArr[r9 + 5] & 255) << 16) | ((bArr[r9 + 6] & 255) << 8);
    }

    private void convertLongToByteArray(long j, byte[] bArr, int r10) {
        for (int r0 = 0; r0 < 8; r0++) {
            bArr[r10 + r0] = (byte) ((j >> (56 - (r0 * 8))) & 255);
        }
    }

    private byte[] copyBitLength() {
        byte[] bArr = new byte[32];
        for (int r2 = 0; r2 < 32; r2++) {
            bArr[r2] = (byte) (this._bitCount[r2] & 255);
        }
        return bArr;
    }

    private void finish() {
        byte[] copyBitLength = copyBitLength();
        byte[] bArr = this._buffer;
        int r2 = this._bufferPos;
        int r3 = r2 + 1;
        this._bufferPos = r3;
        bArr[r2] = (byte) (bArr[r2] | 128);
        if (r3 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        if (this._bufferPos > 32) {
            while (this._bufferPos != 0) {
                update((byte) 0);
            }
        }
        while (this._bufferPos <= 32) {
            update((byte) 0);
        }
        System.arraycopy(copyBitLength, 0, this._buffer, 32, copyBitLength.length);
        processFilledBuffer(this._buffer, 0);
    }

    private void increment() {
        int r1 = 0;
        for (int length = this._bitCount.length - 1; length >= 0; length--) {
            short[] sArr = this._bitCount;
            int r3 = (sArr[length] & 255) + EIGHT[length] + r1;
            r1 = r3 >>> 8;
            sArr[length] = (short) (r3 & 255);
        }
    }

    private int maskWithReductionPolynomial(int r6) {
        return ((long) r6) >= 256 ? r6 ^ REDUCTION_POLYNOMIAL : r6;
    }

    private long packIntoLong(int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11) {
        return (((((((r5 << 48) ^ (r4 << 56)) ^ (r6 << 40)) ^ (r7 << 32)) ^ (r8 << 24)) ^ (r9 << 16)) ^ (r10 << 8)) ^ r11;
    }

    private void processFilledBuffer(byte[] bArr, int r5) {
        for (int r52 = 0; r52 < this._state.length; r52++) {
            this._block[r52] = bytesToLongFromBuffer(this._buffer, r52 * 8);
        }
        processBlock();
        this._bufferPos = 0;
        Arrays.fill(this._buffer, (byte) 0);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new WhirlpoolDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r6) {
        finish();
        for (int r0 = 0; r0 < 8; r0++) {
            convertLongToByteArray(this._hash[r0], bArr, (r0 * 8) + r6);
        }
        reset();
        return getDigestSize();
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Whirlpool";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
    }

    protected void processBlock() {
        long[] jArr;
        for (int r2 = 0; r2 < 8; r2++) {
            long[] jArr2 = this._state;
            long j = this._block[r2];
            long[] jArr3 = this.f1863_K;
            long j2 = this._hash[r2];
            jArr3[r2] = j2;
            jArr2[r2] = j ^ j2;
        }
        int r22 = 1;
        while (r22 <= 10) {
            int r4 = 0;
            while (r4 < 8) {
                long[] jArr4 = this.f1864_L;
                jArr4[r4] = 0;
                long j3 = jArr4[r4];
                long[] jArr5 = f1855C0;
                long[] jArr6 = this.f1863_K;
                jArr4[r4] = jArr5[((int) (jArr6[(r4 + 0) & 7] >>> 56)) & 255] ^ j3;
                jArr4[r4] = jArr4[r4] ^ f1856C1[((int) (jArr6[(r4 - 1) & 7] >>> 48)) & 255];
                jArr4[r4] = jArr4[r4] ^ f1857C2[((int) (jArr6[(r4 - 2) & 7] >>> 40)) & 255];
                jArr4[r4] = jArr4[r4] ^ f1858C3[((int) (jArr6[(r4 - 3) & 7] >>> 32)) & 255];
                jArr4[r4] = jArr4[r4] ^ f1859C4[((int) (jArr6[(r4 - 4) & 7] >>> 24)) & 255];
                jArr4[r4] = jArr4[r4] ^ f1860C5[((int) (jArr6[(r4 - 5) & 7] >>> 16)) & 255];
                jArr4[r4] = jArr4[r4] ^ f1861C6[((int) (jArr6[(r4 - 6) & 7] >>> 8)) & 255];
                jArr4[r4] = jArr4[r4] ^ f1862C7[((int) jArr6[(r4 - 7) & 7]) & 255];
                r4++;
                r22 = r22;
            }
            int r18 = r22;
            long[] jArr7 = this.f1864_L;
            long[] jArr8 = this.f1863_K;
            System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
            long[] jArr9 = this.f1863_K;
            jArr9[0] = jArr9[0] ^ this._rc[r18];
            int r11 = 0;
            while (true) {
                jArr = this.f1864_L;
                if (r11 < 8) {
                    jArr[r11] = this.f1863_K[r11];
                    long j4 = jArr[r11];
                    long[] jArr10 = f1855C0;
                    long[] jArr11 = this._state;
                    jArr[r11] = j4 ^ jArr10[((int) (jArr11[(r11 + 0) & 7] >>> 56)) & 255];
                    jArr[r11] = jArr[r11] ^ f1856C1[((int) (jArr11[(r11 - 1) & 7] >>> 48)) & 255];
                    jArr[r11] = jArr[r11] ^ f1857C2[((int) (jArr11[(r11 - 2) & 7] >>> 40)) & 255];
                    jArr[r11] = jArr[r11] ^ f1858C3[((int) (jArr11[(r11 - 3) & 7] >>> 32)) & 255];
                    jArr[r11] = jArr[r11] ^ f1859C4[((int) (jArr11[(r11 - 4) & 7] >>> 24)) & 255];
                    jArr[r11] = jArr[r11] ^ f1860C5[((int) (jArr11[(r11 - 5) & 7] >>> 16)) & 255];
                    jArr[r11] = jArr[r11] ^ f1861C6[((int) (jArr11[(r11 - 6) & 7] >>> 8)) & 255];
                    jArr[r11] = jArr[r11] ^ f1862C7[((int) jArr11[(r11 - 7) & 7]) & 255];
                    r11++;
                }
            }
            long[] jArr12 = this._state;
            System.arraycopy(jArr, 0, jArr12, 0, jArr12.length);
            r22 = r18 + 1;
        }
        for (int r1 = 0; r1 < 8; r1++) {
            long[] jArr13 = this._hash;
            jArr13[r1] = jArr13[r1] ^ (this._state[r1] ^ this._block[r1]);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this._bufferPos = 0;
        Arrays.fill(this._bitCount, (short) 0);
        Arrays.fill(this._buffer, (byte) 0);
        Arrays.fill(this._hash, 0L);
        Arrays.fill(this.f1863_K, 0L);
        Arrays.fill(this.f1864_L, 0L);
        Arrays.fill(this._block, 0L);
        Arrays.fill(this._state, 0L);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        WhirlpoolDigest whirlpoolDigest = (WhirlpoolDigest) memoable;
        long[] jArr = whirlpoolDigest._rc;
        long[] jArr2 = this._rc;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        byte[] bArr = whirlpoolDigest._buffer;
        byte[] bArr2 = this._buffer;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this._bufferPos = whirlpoolDigest._bufferPos;
        short[] sArr = whirlpoolDigest._bitCount;
        short[] sArr2 = this._bitCount;
        System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
        long[] jArr3 = whirlpoolDigest._hash;
        long[] jArr4 = this._hash;
        System.arraycopy(jArr3, 0, jArr4, 0, jArr4.length);
        long[] jArr5 = whirlpoolDigest.f1863_K;
        long[] jArr6 = this.f1863_K;
        System.arraycopy(jArr5, 0, jArr6, 0, jArr6.length);
        long[] jArr7 = whirlpoolDigest.f1864_L;
        long[] jArr8 = this.f1864_L;
        System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
        long[] jArr9 = whirlpoolDigest._block;
        long[] jArr10 = this._block;
        System.arraycopy(jArr9, 0, jArr10, 0, jArr10.length);
        long[] jArr11 = whirlpoolDigest._state;
        long[] jArr12 = this._state;
        System.arraycopy(jArr11, 0, jArr12, 0, jArr12.length);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this._buffer;
        int r1 = this._bufferPos;
        bArr[r1] = b;
        int r12 = r1 + 1;
        this._bufferPos = r12;
        if (r12 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        increment();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r3, int r4) {
        while (r4 > 0) {
            update(bArr[r3]);
            r3++;
            r4--;
        }
    }
}
