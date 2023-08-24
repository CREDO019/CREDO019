package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Blake2bDigest implements ExtendedDigest {
    private static final int BLOCK_LENGTH_BYTES = 128;
    private byte[] buffer;
    private int bufferPos;
    private long[] chainValue;
    private int digestLength;

    /* renamed from: f0 */
    private long f1722f0;
    private long[] internalState;
    private byte[] key;
    private int keyLength;
    private byte[] personalization;
    private byte[] salt;

    /* renamed from: t0 */
    private long f1723t0;

    /* renamed from: t1 */
    private long f1724t1;
    private static final long[] blake2b_IV = {7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};
    private static final byte[][] blake2b_sigma = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f1132VT, Ascii.f1121FF, 13, Ascii.f1129SO, Ascii.f1128SI}, new byte[]{Ascii.f1129SO, 10, 4, 8, 9, Ascii.f1128SI, 13, 6, 1, Ascii.f1121FF, 0, 2, Ascii.f1132VT, 7, 5, 3}, new byte[]{Ascii.f1132VT, 8, Ascii.f1121FF, 0, 5, 2, Ascii.f1128SI, 13, 10, Ascii.f1129SO, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, Ascii.f1121FF, Ascii.f1132VT, Ascii.f1129SO, 2, 6, 5, 10, 4, 0, Ascii.f1128SI, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, Ascii.f1128SI, Ascii.f1129SO, 1, Ascii.f1132VT, Ascii.f1121FF, 6, 8, 3, 13}, new byte[]{2, Ascii.f1121FF, 6, 10, 0, Ascii.f1132VT, 8, 3, 4, 13, 7, 5, Ascii.f1128SI, Ascii.f1129SO, 1, 9}, new byte[]{Ascii.f1121FF, 5, 1, Ascii.f1128SI, Ascii.f1129SO, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, Ascii.f1132VT}, new byte[]{13, Ascii.f1132VT, 7, Ascii.f1129SO, Ascii.f1121FF, 1, 3, 9, 5, 0, Ascii.f1128SI, 4, 8, 6, 2, 10}, new byte[]{6, Ascii.f1128SI, Ascii.f1129SO, 9, Ascii.f1132VT, 3, 0, 8, Ascii.f1121FF, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, Ascii.f1128SI, Ascii.f1132VT, 9, Ascii.f1129SO, 3, Ascii.f1121FF, 13, 0}, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f1132VT, Ascii.f1121FF, 13, Ascii.f1129SO, Ascii.f1128SI}, new byte[]{Ascii.f1129SO, 10, 4, 8, 9, Ascii.f1128SI, 13, 6, 1, Ascii.f1121FF, 0, 2, Ascii.f1132VT, 7, 5, 3}};
    private static int ROUNDS = 12;

    public Blake2bDigest() {
        this(512);
    }

    public Blake2bDigest(int r4) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f1723t0 = 0L;
        this.f1724t1 = 0L;
        this.f1722f0 = 0L;
        if (r4 < 8 || r4 > 512 || r4 % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2b digest bit length must be a multiple of 8 and not greater than 512");
        }
        this.buffer = new byte[128];
        this.keyLength = 0;
        this.digestLength = r4 / 8;
        init();
    }

    public Blake2bDigest(Blake2bDigest blake2bDigest) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f1723t0 = 0L;
        this.f1724t1 = 0L;
        this.f1722f0 = 0L;
        this.bufferPos = blake2bDigest.bufferPos;
        this.buffer = Arrays.clone(blake2bDigest.buffer);
        this.keyLength = blake2bDigest.keyLength;
        this.key = Arrays.clone(blake2bDigest.key);
        this.digestLength = blake2bDigest.digestLength;
        this.chainValue = Arrays.clone(blake2bDigest.chainValue);
        this.personalization = Arrays.clone(blake2bDigest.personalization);
        this.salt = Arrays.clone(blake2bDigest.salt);
        this.f1723t0 = blake2bDigest.f1723t0;
        this.f1724t1 = blake2bDigest.f1724t1;
        this.f1722f0 = blake2bDigest.f1722f0;
    }

    public Blake2bDigest(byte[] bArr) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f1723t0 = 0L;
        this.f1724t1 = 0L;
        this.f1722f0 = 0L;
        this.buffer = new byte[128];
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.key = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = bArr.length;
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        this.digestLength = 64;
        init();
    }

    public Blake2bDigest(byte[] bArr, int r8, byte[] bArr2, byte[] bArr3) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f1723t0 = 0L;
        this.f1724t1 = 0L;
        this.f1722f0 = 0L;
        this.buffer = new byte[128];
        if (r8 < 1 || r8 > 64) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
        }
        this.digestLength = r8;
        if (bArr2 != null) {
            if (bArr2.length != 16) {
                throw new IllegalArgumentException("salt length must be exactly 16 bytes");
            }
            byte[] bArr4 = new byte[16];
            this.salt = bArr4;
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        }
        if (bArr3 != null) {
            if (bArr3.length != 16) {
                throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
            }
            byte[] bArr5 = new byte[16];
            this.personalization = bArr5;
            System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
        }
        if (bArr != null) {
            byte[] bArr6 = new byte[bArr.length];
            this.key = bArr6;
            System.arraycopy(bArr, 0, bArr6, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = bArr.length;
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        init();
    }

    /* renamed from: G */
    private void m125G(long j, long j2, int r10, int r11, int r12, int r13) {
        long[] jArr = this.internalState;
        jArr[r10] = jArr[r10] + jArr[r11] + j;
        jArr[r13] = Longs.rotateRight(jArr[r13] ^ jArr[r10], 32);
        long[] jArr2 = this.internalState;
        jArr2[r12] = jArr2[r12] + jArr2[r13];
        jArr2[r11] = Longs.rotateRight(jArr2[r11] ^ jArr2[r12], 24);
        long[] jArr3 = this.internalState;
        jArr3[r10] = jArr3[r10] + jArr3[r11] + j2;
        jArr3[r13] = Longs.rotateRight(jArr3[r13] ^ jArr3[r10], 16);
        long[] jArr4 = this.internalState;
        jArr4[r12] = jArr4[r12] + jArr4[r13];
        jArr4[r11] = Longs.rotateRight(jArr4[r11] ^ jArr4[r12], 63);
    }

    private void compress(byte[] bArr, int r14) {
        initializeInternalState();
        long[] jArr = new long[16];
        int r2 = 0;
        for (int r3 = 0; r3 < 16; r3++) {
            jArr[r3] = Pack.littleEndianToLong(bArr, (r3 * 8) + r14);
        }
        for (int r13 = 0; r13 < ROUNDS; r13++) {
            byte[][] bArr2 = blake2b_sigma;
            m125G(jArr[bArr2[r13][0]], jArr[bArr2[r13][1]], 0, 4, 8, 12);
            m125G(jArr[bArr2[r13][2]], jArr[bArr2[r13][3]], 1, 5, 9, 13);
            m125G(jArr[bArr2[r13][4]], jArr[bArr2[r13][5]], 2, 6, 10, 14);
            m125G(jArr[bArr2[r13][6]], jArr[bArr2[r13][7]], 3, 7, 11, 15);
            m125G(jArr[bArr2[r13][8]], jArr[bArr2[r13][9]], 0, 5, 10, 15);
            m125G(jArr[bArr2[r13][10]], jArr[bArr2[r13][11]], 1, 6, 11, 12);
            m125G(jArr[bArr2[r13][12]], jArr[bArr2[r13][13]], 2, 7, 8, 13);
            m125G(jArr[bArr2[r13][14]], jArr[bArr2[r13][15]], 3, 4, 9, 14);
        }
        while (true) {
            long[] jArr2 = this.chainValue;
            if (r2 >= jArr2.length) {
                return;
            }
            long j = jArr2[r2];
            long[] jArr3 = this.internalState;
            jArr2[r2] = (j ^ jArr3[r2]) ^ jArr3[r2 + 8];
            r2++;
        }
    }

    private void init() {
        if (this.chainValue == null) {
            this.chainValue = r1;
            long[] jArr = blake2b_IV;
            long[] jArr2 = {jArr[0] ^ ((this.digestLength | (this.keyLength << 8)) | 16842752), jArr[1], jArr[2], jArr[3], jArr[4], jArr[5]};
            byte[] bArr = this.salt;
            if (bArr != null) {
                jArr2[4] = jArr2[4] ^ Pack.littleEndianToLong(bArr, 0);
                long[] jArr3 = this.chainValue;
                jArr3[5] = jArr3[5] ^ Pack.littleEndianToLong(this.salt, 8);
            }
            long[] jArr4 = this.chainValue;
            jArr4[6] = jArr[6];
            jArr4[7] = jArr[7];
            byte[] bArr2 = this.personalization;
            if (bArr2 != null) {
                jArr4[6] = Pack.littleEndianToLong(bArr2, 0) ^ jArr4[6];
                long[] jArr5 = this.chainValue;
                jArr5[7] = jArr5[7] ^ Pack.littleEndianToLong(this.personalization, 8);
            }
        }
    }

    private void initializeInternalState() {
        long[] jArr = this.chainValue;
        System.arraycopy(jArr, 0, this.internalState, 0, jArr.length);
        long[] jArr2 = blake2b_IV;
        System.arraycopy(jArr2, 0, this.internalState, this.chainValue.length, 4);
        long[] jArr3 = this.internalState;
        jArr3[12] = this.f1723t0 ^ jArr2[4];
        jArr3[13] = this.f1724t1 ^ jArr2[5];
        jArr3[14] = this.f1722f0 ^ jArr2[6];
        jArr3[15] = jArr2[7];
    }

    public void clearKey() {
        byte[] bArr = this.key;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            Arrays.fill(this.buffer, (byte) 0);
        }
    }

    public void clearSalt() {
        byte[] bArr = this.salt;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r10) {
        long[] jArr;
        int r5;
        this.f1722f0 = -1L;
        long j = this.f1723t0;
        int r2 = this.bufferPos;
        long j2 = j + r2;
        this.f1723t0 = j2;
        if (r2 > 0 && j2 == 0) {
            this.f1724t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.internalState, 0L);
        int r0 = 0;
        while (true) {
            jArr = this.chainValue;
            if (r0 >= jArr.length || (r5 = r0 * 8) >= this.digestLength) {
                break;
            }
            byte[] longToLittleEndian = Pack.longToLittleEndian(jArr[r0]);
            int r6 = this.digestLength;
            if (r5 < r6 - 8) {
                System.arraycopy(longToLittleEndian, 0, bArr, r5 + r10, 8);
            } else {
                System.arraycopy(longToLittleEndian, 0, bArr, r10 + r5, r6 - r5);
            }
            r0++;
        }
        Arrays.fill(jArr, 0L);
        reset();
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "BLAKE2b";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 128;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.bufferPos = 0;
        this.f1722f0 = 0L;
        this.f1723t0 = 0L;
        this.f1724t1 = 0L;
        this.chainValue = null;
        Arrays.fill(this.buffer, (byte) 0);
        byte[] bArr = this.key;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        init();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int r0 = this.bufferPos;
        if (128 - r0 != 0) {
            this.buffer[r0] = b;
            this.bufferPos = r0 + 1;
            return;
        }
        long j = this.f1723t0 + 128;
        this.f1723t0 = j;
        if (j == 0) {
            this.f1724t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        this.buffer[0] = b;
        this.bufferPos = 1;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r13, int r14) {
        int r8;
        if (bArr == null || r14 == 0) {
            return;
        }
        int r0 = this.bufferPos;
        if (r0 != 0) {
            r8 = 128 - r0;
            if (r8 >= r14) {
                System.arraycopy(bArr, r13, this.buffer, r0, r14);
                this.bufferPos += r14;
            }
            System.arraycopy(bArr, r13, this.buffer, r0, r8);
            long j = this.f1723t0 + 128;
            this.f1723t0 = j;
            if (j == 0) {
                this.f1724t1++;
            }
            compress(this.buffer, 0);
            this.bufferPos = 0;
            Arrays.fill(this.buffer, (byte) 0);
        } else {
            r8 = 0;
        }
        int r142 = r14 + r13;
        int r02 = r142 - 128;
        int r132 = r13 + r8;
        while (r132 < r02) {
            long j2 = this.f1723t0 + 128;
            this.f1723t0 = j2;
            if (j2 == 0) {
                this.f1724t1++;
            }
            compress(bArr, r132);
            r132 += 128;
        }
        r14 = r142 - r132;
        System.arraycopy(bArr, r132, this.buffer, 0, r14);
        this.bufferPos += r14;
    }
}
