package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Blake2sDigest implements ExtendedDigest {
    private static final int BLOCK_LENGTH_BYTES = 64;
    private static final int ROUNDS = 10;
    private static final int[] blake2s_IV = {1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225};
    private static final byte[][] blake2s_sigma = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.f1132VT, Ascii.f1121FF, 13, Ascii.f1129SO, Ascii.f1128SI}, new byte[]{Ascii.f1129SO, 10, 4, 8, 9, Ascii.f1128SI, 13, 6, 1, Ascii.f1121FF, 0, 2, Ascii.f1132VT, 7, 5, 3}, new byte[]{Ascii.f1132VT, 8, Ascii.f1121FF, 0, 5, 2, Ascii.f1128SI, 13, 10, Ascii.f1129SO, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, Ascii.f1121FF, Ascii.f1132VT, Ascii.f1129SO, 2, 6, 5, 10, 4, 0, Ascii.f1128SI, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, Ascii.f1128SI, Ascii.f1129SO, 1, Ascii.f1132VT, Ascii.f1121FF, 6, 8, 3, 13}, new byte[]{2, Ascii.f1121FF, 6, 10, 0, Ascii.f1132VT, 8, 3, 4, 13, 7, 5, Ascii.f1128SI, Ascii.f1129SO, 1, 9}, new byte[]{Ascii.f1121FF, 5, 1, Ascii.f1128SI, Ascii.f1129SO, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, Ascii.f1132VT}, new byte[]{13, Ascii.f1132VT, 7, Ascii.f1129SO, Ascii.f1121FF, 1, 3, 9, 5, 0, Ascii.f1128SI, 4, 8, 6, 2, 10}, new byte[]{6, Ascii.f1128SI, Ascii.f1129SO, 9, Ascii.f1132VT, 3, 0, 8, Ascii.f1121FF, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, Ascii.f1128SI, Ascii.f1132VT, 9, Ascii.f1129SO, 3, Ascii.f1121FF, 13, 0}};
    private byte[] buffer;
    private int bufferPos;
    private int[] chainValue;
    private int depth;
    private int digestLength;

    /* renamed from: f0 */
    private int f1725f0;
    private int fanout;
    private int innerHashLength;
    private int[] internalState;
    private byte[] key;
    private int keyLength;
    private int leafLength;
    private int nodeDepth;
    private long nodeOffset;
    private byte[] personalization;
    private byte[] salt;

    /* renamed from: t0 */
    private int f1726t0;

    /* renamed from: t1 */
    private int f1727t1;

    public Blake2sDigest() {
        this(256);
    }

    public Blake2sDigest(int r5) {
        this.digestLength = 32;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.fanout = 1;
        this.depth = 1;
        this.leafLength = 0;
        this.nodeOffset = 0L;
        this.nodeDepth = 0;
        this.innerHashLength = 0;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new int[16];
        this.chainValue = null;
        this.f1726t0 = 0;
        this.f1727t1 = 0;
        this.f1725f0 = 0;
        if (r5 < 8 || r5 > 256 || r5 % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2s digest bit length must be a multiple of 8 and not greater than 256");
        }
        this.digestLength = r5 / 8;
        init(null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Blake2sDigest(int r4, int r5, long j) {
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new int[16];
        this.chainValue = null;
        this.f1726t0 = 0;
        this.f1727t1 = 0;
        this.f1725f0 = 0;
        this.digestLength = r4;
        this.nodeOffset = j;
        this.fanout = 0;
        this.depth = 0;
        this.leafLength = r5;
        this.innerHashLength = r5;
        this.nodeDepth = 0;
        init(null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Blake2sDigest(int r4, byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.fanout = 1;
        this.depth = 1;
        this.leafLength = 0;
        this.nodeDepth = 0;
        this.innerHashLength = 0;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new int[16];
        this.chainValue = null;
        this.f1726t0 = 0;
        this.f1727t1 = 0;
        this.f1725f0 = 0;
        this.digestLength = r4;
        this.nodeOffset = j;
        init(bArr2, bArr3, bArr);
    }

    public Blake2sDigest(Blake2sDigest blake2sDigest) {
        this.digestLength = 32;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.fanout = 1;
        this.depth = 1;
        this.leafLength = 0;
        this.nodeOffset = 0L;
        this.nodeDepth = 0;
        this.innerHashLength = 0;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new int[16];
        this.chainValue = null;
        this.f1726t0 = 0;
        this.f1727t1 = 0;
        this.f1725f0 = 0;
        this.bufferPos = blake2sDigest.bufferPos;
        this.buffer = Arrays.clone(blake2sDigest.buffer);
        this.keyLength = blake2sDigest.keyLength;
        this.key = Arrays.clone(blake2sDigest.key);
        this.digestLength = blake2sDigest.digestLength;
        this.internalState = Arrays.clone(this.internalState);
        this.chainValue = Arrays.clone(blake2sDigest.chainValue);
        this.f1726t0 = blake2sDigest.f1726t0;
        this.f1727t1 = blake2sDigest.f1727t1;
        this.f1725f0 = blake2sDigest.f1725f0;
        this.salt = Arrays.clone(blake2sDigest.salt);
        this.personalization = Arrays.clone(blake2sDigest.personalization);
        this.fanout = blake2sDigest.fanout;
        this.depth = blake2sDigest.depth;
        this.leafLength = blake2sDigest.leafLength;
        this.nodeOffset = blake2sDigest.nodeOffset;
        this.nodeDepth = blake2sDigest.nodeDepth;
        this.innerHashLength = blake2sDigest.innerHashLength;
    }

    public Blake2sDigest(byte[] bArr) {
        this.digestLength = 32;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.fanout = 1;
        this.depth = 1;
        this.leafLength = 0;
        this.nodeOffset = 0L;
        this.nodeDepth = 0;
        this.innerHashLength = 0;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new int[16];
        this.chainValue = null;
        this.f1726t0 = 0;
        this.f1727t1 = 0;
        this.f1725f0 = 0;
        init(null, null, bArr);
    }

    public Blake2sDigest(byte[] bArr, int r8, byte[] bArr2, byte[] bArr3) {
        this.digestLength = 32;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.fanout = 1;
        this.depth = 1;
        this.leafLength = 0;
        this.nodeOffset = 0L;
        this.nodeDepth = 0;
        this.innerHashLength = 0;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new int[16];
        this.chainValue = null;
        this.f1726t0 = 0;
        this.f1727t1 = 0;
        this.f1725f0 = 0;
        if (r8 < 1 || r8 > 32) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 32)");
        }
        this.digestLength = r8;
        init(bArr2, bArr3, bArr);
    }

    /* renamed from: G */
    private void m124G(int r4, int r5, int r6, int r7, int r8, int r9) {
        int[] r0 = this.internalState;
        r0[r6] = r0[r6] + r0[r7] + r4;
        r0[r9] = rotr32(r0[r9] ^ r0[r6], 16);
        int[] r42 = this.internalState;
        r42[r8] = r42[r8] + r42[r9];
        r42[r7] = rotr32(r42[r7] ^ r42[r8], 12);
        int[] r43 = this.internalState;
        r43[r6] = r43[r6] + r43[r7] + r5;
        r43[r9] = rotr32(r43[r9] ^ r43[r6], 8);
        int[] r44 = this.internalState;
        r44[r8] = r44[r8] + r44[r9];
        r44[r7] = rotr32(r44[r7] ^ r44[r8], 7);
    }

    private void compress(byte[] bArr, int r13) {
        initializeInternalState();
        int[] r1 = new int[16];
        int r2 = 0;
        for (int r3 = 0; r3 < 16; r3++) {
            r1[r3] = Pack.littleEndianToInt(bArr, (r3 * 4) + r13);
        }
        for (int r12 = 0; r12 < 10; r12++) {
            byte[][] bArr2 = blake2s_sigma;
            m124G(r1[bArr2[r12][0]], r1[bArr2[r12][1]], 0, 4, 8, 12);
            m124G(r1[bArr2[r12][2]], r1[bArr2[r12][3]], 1, 5, 9, 13);
            m124G(r1[bArr2[r12][4]], r1[bArr2[r12][5]], 2, 6, 10, 14);
            m124G(r1[bArr2[r12][6]], r1[bArr2[r12][7]], 3, 7, 11, 15);
            m124G(r1[bArr2[r12][8]], r1[bArr2[r12][9]], 0, 5, 10, 15);
            m124G(r1[bArr2[r12][10]], r1[bArr2[r12][11]], 1, 6, 11, 12);
            m124G(r1[bArr2[r12][12]], r1[bArr2[r12][13]], 2, 7, 8, 13);
            m124G(r1[bArr2[r12][14]], r1[bArr2[r12][15]], 3, 4, 9, 14);
        }
        while (true) {
            int[] r122 = this.chainValue;
            if (r2 >= r122.length) {
                return;
            }
            int r132 = r122[r2];
            int[] r0 = this.internalState;
            r122[r2] = (r132 ^ r0[r2]) ^ r0[r2 + 8];
            r2++;
        }
    }

    private void init(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.buffer = new byte[64];
        if (bArr3 != null && bArr3.length > 0) {
            if (bArr3.length > 32) {
                throw new IllegalArgumentException("Keys > 32 bytes are not supported");
            }
            byte[] bArr4 = new byte[bArr3.length];
            this.key = bArr4;
            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
            this.keyLength = bArr3.length;
            System.arraycopy(bArr3, 0, this.buffer, 0, bArr3.length);
            this.bufferPos = 64;
        }
        if (this.chainValue == null) {
            this.chainValue = r0;
            int[] r3 = blake2s_IV;
            long j = this.nodeOffset;
            int[] r0 = {r3[0] ^ ((this.digestLength | (this.keyLength << 8)) | ((this.fanout << 16) | (this.depth << 24))), r3[1] ^ this.leafLength, ((int) j) ^ r3[2], ((((int) (j >> 32)) | (this.nodeDepth << 16)) | (this.innerHashLength << 24)) ^ r3[3], r3[4], r3[5]};
            if (bArr != null) {
                if (bArr.length != 8) {
                    throw new IllegalArgumentException("Salt length must be exactly 8 bytes");
                }
                byte[] bArr5 = new byte[8];
                this.salt = bArr5;
                System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
                int[] r02 = this.chainValue;
                r02[4] = r02[4] ^ Pack.littleEndianToInt(bArr, 0);
                int[] r03 = this.chainValue;
                r03[5] = Pack.littleEndianToInt(bArr, 4) ^ r03[5];
            }
            int[] r9 = this.chainValue;
            r9[6] = r3[6];
            r9[7] = r3[7];
            if (bArr2 != null) {
                if (bArr2.length != 8) {
                    throw new IllegalArgumentException("Personalization length must be exactly 8 bytes");
                }
                byte[] bArr6 = new byte[8];
                this.personalization = bArr6;
                System.arraycopy(bArr2, 0, bArr6, 0, bArr2.length);
                int[] r92 = this.chainValue;
                r92[6] = r92[6] ^ Pack.littleEndianToInt(bArr2, 0);
                int[] r93 = this.chainValue;
                r93[7] = Pack.littleEndianToInt(bArr2, 4) ^ r93[7];
            }
        }
    }

    private void initializeInternalState() {
        int[] r0 = this.chainValue;
        System.arraycopy(r0, 0, this.internalState, 0, r0.length);
        int[] r02 = blake2s_IV;
        System.arraycopy(r02, 0, this.internalState, this.chainValue.length, 4);
        int[] r1 = this.internalState;
        r1[12] = this.f1726t0 ^ r02[4];
        r1[13] = this.f1727t1 ^ r02[5];
        r1[14] = this.f1725f0 ^ r02[6];
        r1[15] = r02[7];
    }

    private int rotr32(int r2, int r3) {
        return (r2 << (32 - r3)) | (r2 >>> r3);
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
    public int doFinal(byte[] bArr, int r8) {
        int[] r2;
        int r3;
        this.f1725f0 = -1;
        int r0 = this.f1726t0;
        int r1 = this.bufferPos;
        int r02 = r0 + r1;
        this.f1726t0 = r02;
        if (r02 < 0 && r1 > (-r02)) {
            this.f1727t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.internalState, 0);
        int r03 = 0;
        while (true) {
            r2 = this.chainValue;
            if (r03 >= r2.length || (r3 = r03 * 4) >= this.digestLength) {
                break;
            }
            byte[] intToLittleEndian = Pack.intToLittleEndian(r2[r03]);
            int r4 = this.digestLength;
            if (r3 < r4 - 4) {
                System.arraycopy(intToLittleEndian, 0, bArr, r3 + r8, 4);
            } else {
                System.arraycopy(intToLittleEndian, 0, bArr, r8 + r3, r4 - r3);
            }
            r03++;
        }
        Arrays.fill(r2, 0);
        reset();
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "BLAKE2s";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.bufferPos = 0;
        this.f1725f0 = 0;
        this.f1726t0 = 0;
        this.f1727t1 = 0;
        this.chainValue = null;
        Arrays.fill(this.buffer, (byte) 0);
        byte[] bArr = this.key;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 64;
        }
        init(this.salt, this.personalization, this.key);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int r0 = this.bufferPos;
        if (64 - r0 != 0) {
            this.buffer[r0] = b;
            this.bufferPos = r0 + 1;
            return;
        }
        int r02 = this.f1726t0 + 64;
        this.f1726t0 = r02;
        if (r02 == 0) {
            this.f1727t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        this.buffer[0] = b;
        this.bufferPos = 1;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r6, int r7) {
        int r2;
        if (bArr == null || r7 == 0) {
            return;
        }
        int r0 = this.bufferPos;
        if (r0 != 0) {
            r2 = 64 - r0;
            if (r2 >= r7) {
                System.arraycopy(bArr, r6, this.buffer, r0, r7);
                this.bufferPos += r7;
            }
            System.arraycopy(bArr, r6, this.buffer, r0, r2);
            int r02 = this.f1726t0 + 64;
            this.f1726t0 = r02;
            if (r02 == 0) {
                this.f1727t1++;
            }
            compress(this.buffer, 0);
            this.bufferPos = 0;
            Arrays.fill(this.buffer, (byte) 0);
        } else {
            r2 = 0;
        }
        int r72 = r7 + r6;
        int r03 = r72 - 64;
        int r62 = r6 + r2;
        while (r62 < r03) {
            int r22 = this.f1726t0 + 64;
            this.f1726t0 = r22;
            if (r22 == 0) {
                this.f1727t1++;
            }
            compress(bArr, r62);
            r62 += 64;
        }
        r7 = r72 - r62;
        System.arraycopy(bArr, r62, this.buffer, 0, r7);
        this.bufferPos += r7;
    }
}
