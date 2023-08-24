package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import java.util.Iterator;
import java.util.Stack;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.params.Blake3Parameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Blake3Digest implements ExtendedDigest, Memoable, Xof {
    private static final int BLOCKLEN = 64;
    private static final int CHAINING0 = 0;
    private static final int CHAINING1 = 1;
    private static final int CHAINING2 = 2;
    private static final int CHAINING3 = 3;
    private static final int CHAINING4 = 4;
    private static final int CHAINING5 = 5;
    private static final int CHAINING6 = 6;
    private static final int CHAINING7 = 7;
    private static final int CHUNKEND = 2;
    private static final int CHUNKLEN = 1024;
    private static final int CHUNKSTART = 1;
    private static final int COUNT0 = 12;
    private static final int COUNT1 = 13;
    private static final int DATALEN = 14;
    private static final int DERIVECONTEXT = 32;
    private static final int DERIVEKEY = 64;
    private static final String ERR_OUTPUTTING = "Already outputting";
    private static final int FLAGS = 15;
    private static final int IV0 = 8;
    private static final int IV1 = 9;
    private static final int IV2 = 10;
    private static final int IV3 = 11;
    private static final int KEYEDHASH = 16;
    private static final int NUMWORDS = 8;
    private static final int PARENT = 4;
    private static final int ROOT = 8;
    private static final int ROUNDS = 7;
    private long outputAvailable;
    private boolean outputting;
    private final byte[] theBuffer;
    private final int[] theChaining;
    private long theCounter;
    private int theCurrBytes;
    private final int theDigestLen;
    private final byte[] theIndices;
    private final int[] theK;
    private final int[] theM;
    private int theMode;
    private int theOutputDataLen;
    private int theOutputMode;
    private int thePos;
    private final Stack theStack;
    private final int[] theV;
    private static final byte[] SIGMA = {2, 6, 3, 10, 7, 0, 4, 13, 1, Ascii.f1132VT, Ascii.f1121FF, 5, 9, Ascii.f1129SO, Ascii.f1128SI, 8};
    private static final byte[] ROTATE = {16, Ascii.f1121FF, 8, 7};

    /* renamed from: IV */
    private static final int[] f1729IV = {1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225};

    public Blake3Digest() {
        this(32);
    }

    public Blake3Digest(int r3) {
        this.theBuffer = new byte[64];
        this.theK = new int[8];
        this.theChaining = new int[8];
        this.theV = new int[16];
        this.theM = new int[16];
        this.theIndices = new byte[16];
        this.theStack = new Stack();
        this.theDigestLen = r3;
        init(null);
    }

    private Blake3Digest(Blake3Digest blake3Digest) {
        this.theBuffer = new byte[64];
        this.theK = new int[8];
        this.theChaining = new int[8];
        this.theV = new int[16];
        this.theM = new int[16];
        this.theIndices = new byte[16];
        this.theStack = new Stack();
        this.theDigestLen = blake3Digest.theDigestLen;
        reset(blake3Digest);
    }

    private void adjustChaining() {
        if (!this.outputting) {
            for (int r2 = 0; r2 < 8; r2++) {
                int[] r0 = this.theChaining;
                int[] r3 = this.theV;
                r0[r2] = r3[r2 + 8] ^ r3[r2];
            }
            return;
        }
        for (int r02 = 0; r02 < 8; r02++) {
            int[] r32 = this.theV;
            int r5 = r02 + 8;
            r32[r02] = r32[r02] ^ r32[r5];
            r32[r5] = r32[r5] ^ this.theChaining[r02];
        }
        for (int r03 = 0; r03 < 16; r03++) {
            Pack.intToLittleEndian(this.theV[r03], this.theBuffer, r03 * 4);
        }
        this.thePos = 0;
    }

    private void adjustStack() {
        for (long j = this.theCounter; j > 0 && (j & 1) != 1; j >>= 1) {
            System.arraycopy((int[]) this.theStack.pop(), 0, this.theM, 0, 8);
            System.arraycopy(this.theChaining, 0, this.theM, 8, 8);
            initParentBlock();
            compress();
        }
        this.theStack.push(Arrays.copyOf(this.theChaining, 8));
    }

    private void compress() {
        initIndices();
        int r0 = 0;
        while (true) {
            performRound();
            if (r0 >= 6) {
                adjustChaining();
                return;
            } else {
                permuteIndices();
                r0++;
            }
        }
    }

    private void compressBlock(byte[] bArr, int r4) {
        initChunkBlock(64, false);
        initM(bArr, r4);
        compress();
        if (this.theCurrBytes == 0) {
            adjustStack();
        }
    }

    private void compressFinalBlock(int r2) {
        initChunkBlock(r2, true);
        initM(this.theBuffer, 0);
        compress();
        processStack();
    }

    private void incrementBlockCount() {
        this.theCounter++;
        this.theCurrBytes = 0;
    }

    private void initChunkBlock(int r7, boolean z) {
        System.arraycopy(this.theCurrBytes == 0 ? this.theK : this.theChaining, 0, this.theV, 0, 8);
        System.arraycopy(f1729IV, 0, this.theV, 8, 4);
        int[] r0 = this.theV;
        long j = this.theCounter;
        r0[12] = (int) j;
        r0[13] = (int) (j >> 32);
        r0[14] = r7;
        int r1 = this.theMode;
        int r3 = this.theCurrBytes;
        r0[15] = r1 + (r3 == 0 ? 1 : 0) + (z ? 2 : 0);
        int r32 = r3 + r7;
        this.theCurrBytes = r32;
        if (r32 >= 1024) {
            incrementBlockCount();
            int[] r72 = this.theV;
            r72[15] = r72[15] | 2;
        }
        if (z && this.theStack.isEmpty()) {
            setRoot();
        }
    }

    private void initIndices() {
        byte b = 0;
        while (true) {
            byte[] bArr = this.theIndices;
            if (b >= bArr.length) {
                return;
            }
            bArr[b] = b;
            b = (byte) (b + 1);
        }
    }

    private void initKey(byte[] bArr) {
        for (int r0 = 0; r0 < 8; r0++) {
            this.theK[r0] = Pack.littleEndianToInt(bArr, r0 * 4);
        }
        this.theMode = 16;
    }

    private void initKeyFromContext() {
        System.arraycopy(this.theV, 0, this.theK, 0, 8);
        this.theMode = 64;
    }

    private void initM(byte[] bArr, int r5) {
        for (int r0 = 0; r0 < 16; r0++) {
            this.theM[r0] = Pack.littleEndianToInt(bArr, (r0 * 4) + r5);
        }
    }

    private void initNullKey() {
        System.arraycopy(f1729IV, 0, this.theK, 0, 8);
    }

    private void initParentBlock() {
        System.arraycopy(this.theK, 0, this.theV, 0, 8);
        System.arraycopy(f1729IV, 0, this.theV, 8, 4);
        int[] r0 = this.theV;
        r0[12] = 0;
        r0[13] = 0;
        r0[14] = 64;
        r0[15] = this.theMode | 4;
    }

    private void mixG(int r8, int r9, int r10, int r11, int r12) {
        int r82 = r8 << 1;
        int[] r1 = this.theV;
        int r6 = r82 + 1;
        r1[r9] = r1[r9] + r1[r10] + this.theM[this.theIndices[r82]];
        int r83 = r1[r12] ^ r1[r9];
        byte[] bArr = ROTATE;
        r1[r12] = Integers.rotateRight(r83, bArr[0]);
        int[] r84 = this.theV;
        r84[r11] = r84[r11] + r84[r12];
        r84[r10] = Integers.rotateRight(r84[r10] ^ r84[r11], bArr[1]);
        int[] r85 = this.theV;
        r85[r9] = r85[r9] + r85[r10] + this.theM[this.theIndices[r6]];
        r85[r12] = Integers.rotateRight(r85[r9] ^ r85[r12], bArr[2]);
        int[] r86 = this.theV;
        r86[r11] = r86[r11] + r86[r12];
        r86[r10] = Integers.rotateRight(r86[r10] ^ r86[r11], bArr[3]);
    }

    private void nextOutputBlock() {
        this.theCounter++;
        System.arraycopy(this.theChaining, 0, this.theV, 0, 8);
        System.arraycopy(f1729IV, 0, this.theV, 8, 4);
        int[] r0 = this.theV;
        long j = this.theCounter;
        r0[12] = (int) j;
        r0[13] = (int) (j >> 32);
        r0[14] = this.theOutputDataLen;
        r0[15] = this.theOutputMode;
        compress();
    }

    private void performRound() {
        mixG(0, 0, 4, 8, 12);
        mixG(1, 1, 5, 9, 13);
        mixG(2, 2, 6, 10, 14);
        mixG(3, 3, 7, 11, 15);
        mixG(4, 0, 5, 10, 15);
        mixG(5, 1, 6, 11, 12);
        mixG(6, 2, 7, 8, 13);
        mixG(7, 3, 4, 9, 14);
    }

    private void permuteIndices() {
        byte b = 0;
        while (true) {
            byte[] bArr = this.theIndices;
            if (b >= bArr.length) {
                return;
            }
            bArr[b] = SIGMA[bArr[b]];
            b = (byte) (b + 1);
        }
    }

    private void processStack() {
        while (!this.theStack.isEmpty()) {
            System.arraycopy((int[]) this.theStack.pop(), 0, this.theM, 0, 8);
            System.arraycopy(this.theChaining, 0, this.theM, 8, 8);
            initParentBlock();
            if (this.theStack.isEmpty()) {
                setRoot();
            }
            compress();
        }
    }

    private void resetBlockCount() {
        this.theCounter = 0L;
        this.theCurrBytes = 0;
    }

    private void setRoot() {
        int[] r0 = this.theV;
        r0[15] = r0[15] | 8;
        this.theOutputMode = r0[15];
        this.theOutputDataLen = r0[14];
        this.theCounter = 0L;
        this.outputting = true;
        this.outputAvailable = -1L;
        System.arraycopy(r0, 0, this.theChaining, 0, 8);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new Blake3Digest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r3) {
        return doFinal(bArr, r3, getDigestSize());
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doFinal(byte[] bArr, int r3, int r4) {
        if (this.outputting) {
            throw new IllegalStateException(ERR_OUTPUTTING);
        }
        int doOutput = doOutput(bArr, r3, r4);
        reset();
        return doOutput;
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int r7, int r8) {
        int r0;
        if (!this.outputting) {
            compressFinalBlock(this.thePos);
        }
        if (r8 >= 0) {
            long j = this.outputAvailable;
            if (j < 0 || r8 <= j) {
                int r02 = this.thePos;
                if (r02 < 64) {
                    int min = Math.min(r8, 64 - r02);
                    System.arraycopy(this.theBuffer, this.thePos, bArr, r7, min);
                    this.thePos += min;
                    r7 += min;
                    r0 = r8 - min;
                } else {
                    r0 = r8;
                }
                while (r0 > 0) {
                    nextOutputBlock();
                    int min2 = Math.min(r0, 64);
                    System.arraycopy(this.theBuffer, 0, bArr, r7, min2);
                    this.thePos += min2;
                    r7 += min2;
                    r0 -= min2;
                }
                this.outputAvailable -= r8;
                return r8;
            }
        }
        throw new IllegalArgumentException("Insufficient bytes remaining");
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "BLAKE3";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.theDigestLen;
    }

    public void init(Blake3Parameters blake3Parameters) {
        byte[] key = blake3Parameters == null ? null : blake3Parameters.getKey();
        byte[] context = blake3Parameters != null ? blake3Parameters.getContext() : null;
        reset();
        if (key != null) {
            initKey(key);
            Arrays.fill(key, (byte) 0);
            return;
        }
        initNullKey();
        if (context == null) {
            this.theMode = 0;
            return;
        }
        this.theMode = 32;
        update(context, 0, context.length);
        doFinal(this.theBuffer, 0);
        initKeyFromContext();
        reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        resetBlockCount();
        this.thePos = 0;
        this.outputting = false;
        Arrays.fill(this.theBuffer, (byte) 0);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        Blake3Digest blake3Digest = (Blake3Digest) memoable;
        this.theCounter = blake3Digest.theCounter;
        this.theCurrBytes = blake3Digest.theCurrBytes;
        this.theMode = blake3Digest.theMode;
        this.outputting = blake3Digest.outputting;
        this.outputAvailable = blake3Digest.outputAvailable;
        this.theOutputMode = blake3Digest.theOutputMode;
        this.theOutputDataLen = blake3Digest.theOutputDataLen;
        int[] r0 = blake3Digest.theChaining;
        int[] r1 = this.theChaining;
        System.arraycopy(r0, 0, r1, 0, r1.length);
        int[] r02 = blake3Digest.theK;
        int[] r12 = this.theK;
        System.arraycopy(r02, 0, r12, 0, r12.length);
        int[] r03 = blake3Digest.theM;
        int[] r13 = this.theM;
        System.arraycopy(r03, 0, r13, 0, r13.length);
        this.theStack.clear();
        Iterator it = blake3Digest.theStack.iterator();
        while (it.hasNext()) {
            this.theStack.push(Arrays.clone((int[]) it.next()));
        }
        byte[] bArr = blake3Digest.theBuffer;
        byte[] bArr2 = this.theBuffer;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.thePos = blake3Digest.thePos;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        if (this.outputting) {
            throw new IllegalStateException(ERR_OUTPUTTING);
        }
        byte[] bArr = this.theBuffer;
        if (bArr.length - this.thePos == 0) {
            compressBlock(bArr, 0);
            Arrays.fill(this.theBuffer, (byte) 0);
            this.thePos = 0;
        }
        byte[] bArr2 = this.theBuffer;
        int r1 = this.thePos;
        bArr2[r1] = b;
        this.thePos = r1 + 1;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r6, int r7) {
        int r2;
        int r5;
        if (bArr == null || r7 == 0) {
            return;
        }
        if (this.outputting) {
            throw new IllegalStateException(ERR_OUTPUTTING);
        }
        int r0 = this.thePos;
        if (r0 != 0) {
            r2 = 64 - r0;
            if (r2 >= r7) {
                System.arraycopy(bArr, r6, this.theBuffer, r0, r7);
                r5 = this.thePos + r7;
                this.thePos = r5;
            }
            System.arraycopy(bArr, r6, this.theBuffer, r0, r2);
            compressBlock(this.theBuffer, 0);
            this.thePos = 0;
            Arrays.fill(this.theBuffer, (byte) 0);
        } else {
            r2 = 0;
        }
        int r02 = (r6 + r7) - 64;
        int r22 = r2 + r6;
        while (r22 < r02) {
            compressBlock(bArr, r22);
            r22 += 64;
        }
        int r62 = r6 + (r7 - r22);
        System.arraycopy(bArr, r22, this.theBuffer, 0, r62);
        r5 = this.thePos + r62;
        this.thePos = r5;
    }
}
