package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class ParallelHash implements Xof, Digest {
    private static final byte[] N_PARALLEL_HASH = Strings.toByteArray("ParallelHash");

    /* renamed from: B */
    private final int f1782B;
    private final int bitLength;
    private int bufOff;
    private final byte[] buffer;
    private final CSHAKEDigest compressor;
    private final byte[] compressorBuffer;
    private final CSHAKEDigest cshake;
    private boolean firstOutput;
    private int nCount;
    private final int outputLength;

    public ParallelHash(int r2, byte[] bArr, int r4) {
        this(r2, bArr, r4, r2 * 2);
    }

    public ParallelHash(int r3, byte[] bArr, int r5, int r6) {
        this.cshake = new CSHAKEDigest(r3, N_PARALLEL_HASH, bArr);
        this.compressor = new CSHAKEDigest(r3, new byte[0], new byte[0]);
        this.bitLength = r3;
        this.f1782B = r5;
        this.outputLength = (r6 + 7) / 8;
        this.buffer = new byte[r5];
        this.compressorBuffer = new byte[(r3 * 2) / 8];
        reset();
    }

    public ParallelHash(ParallelHash parallelHash) {
        this.cshake = new CSHAKEDigest(parallelHash.cshake);
        this.compressor = new CSHAKEDigest(parallelHash.compressor);
        this.bitLength = parallelHash.bitLength;
        this.f1782B = parallelHash.f1782B;
        this.outputLength = parallelHash.outputLength;
        this.buffer = Arrays.clone(parallelHash.buffer);
        this.compressorBuffer = Arrays.clone(parallelHash.compressorBuffer);
    }

    private void compress() {
        compress(this.buffer, 0, this.bufOff);
        this.bufOff = 0;
    }

    private void compress(byte[] bArr, int r3, int r4) {
        this.compressor.update(bArr, r3, r4);
        CSHAKEDigest cSHAKEDigest = this.compressor;
        byte[] bArr2 = this.compressorBuffer;
        cSHAKEDigest.doFinal(bArr2, 0, bArr2.length);
        CSHAKEDigest cSHAKEDigest2 = this.cshake;
        byte[] bArr3 = this.compressorBuffer;
        cSHAKEDigest2.update(bArr3, 0, bArr3.length);
        this.nCount++;
    }

    private void wrapUp(int r5) {
        if (this.bufOff != 0) {
            compress();
        }
        byte[] rightEncode = XofUtils.rightEncode(this.nCount);
        byte[] rightEncode2 = XofUtils.rightEncode(r5 * 8);
        this.cshake.update(rightEncode, 0, rightEncode.length);
        this.cshake.update(rightEncode2, 0, rightEncode2.length);
        this.firstOutput = false;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) throws DataLengthException, IllegalStateException {
        if (this.firstOutput) {
            wrapUp(this.outputLength);
        }
        int doFinal = this.cshake.doFinal(bArr, r4, getDigestSize());
        reset();
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doFinal(byte[] bArr, int r3, int r4) {
        if (this.firstOutput) {
            wrapUp(this.outputLength);
        }
        int doFinal = this.cshake.doFinal(bArr, r3, r4);
        reset();
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doOutput(byte[] bArr, int r3, int r4) {
        if (this.firstOutput) {
            wrapUp(0);
        }
        return this.cshake.doOutput(bArr, r3, r4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "ParallelHash" + this.cshake.getAlgorithmName().substring(6);
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.cshake.getByteLength();
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.outputLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.cshake.reset();
        Arrays.clear(this.buffer);
        byte[] leftEncode = XofUtils.leftEncode(this.f1782B);
        this.cshake.update(leftEncode, 0, leftEncode.length);
        this.nCount = 0;
        this.bufOff = 0;
        this.firstOutput = true;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.buffer;
        int r1 = this.bufOff;
        int r2 = r1 + 1;
        this.bufOff = r2;
        bArr[r1] = b;
        if (r2 == bArr.length) {
            compress();
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r6, int r7) throws DataLengthException, IllegalStateException {
        int r0 = 0;
        int max = Math.max(0, r7);
        if (this.bufOff != 0) {
            while (r0 < max) {
                int r1 = this.bufOff;
                byte[] bArr2 = this.buffer;
                if (r1 == bArr2.length) {
                    break;
                }
                this.bufOff = r1 + 1;
                bArr2[r1] = bArr[r0 + r6];
                r0++;
            }
            if (this.bufOff == this.buffer.length) {
                compress();
            }
        }
        if (r0 < max) {
            while (true) {
                int r12 = max - r0;
                int r2 = this.f1782B;
                if (r12 <= r2) {
                    break;
                }
                compress(bArr, r6 + r0, r2);
                r0 += this.f1782B;
            }
        }
        while (r0 < max) {
            update(bArr[r0 + r6]);
            r0++;
        }
    }
}
