package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.Xof;

/* loaded from: classes5.dex */
public class SHAKEDigest extends KeccakDigest implements Xof {
    public SHAKEDigest() {
        this(128);
    }

    public SHAKEDigest(int r1) {
        super(checkBitLength(r1));
    }

    public SHAKEDigest(SHAKEDigest sHAKEDigest) {
        super(sHAKEDigest);
    }

    private static int checkBitLength(int r3) {
        if (r3 == 128 || r3 == 256) {
            return r3;
        }
        throw new IllegalArgumentException("'bitLength' " + r3 + " not supported for SHAKE");
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r3) {
        return doFinal(bArr, r3, getDigestSize());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.crypto.digests.KeccakDigest
    public int doFinal(byte[] bArr, int r8, byte b, int r10) {
        return doFinal(bArr, r8, getDigestSize(), b, r10);
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doFinal(byte[] bArr, int r2, int r3) {
        int doOutput = doOutput(bArr, r2, r3);
        reset();
        return doOutput;
    }

    protected int doFinal(byte[] bArr, int r4, int r5, byte b, int r7) {
        if (r7 < 0 || r7 > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int r6 = (b & ((1 << r7) - 1)) | (15 << r7);
        int r72 = r7 + 4;
        if (r72 >= 8) {
            absorb((byte) r6);
            r72 -= 8;
            r6 >>>= 8;
        }
        if (r72 > 0) {
            absorbBits(r6, r72);
        }
        squeeze(bArr, r4, r5 * 8);
        reset();
        return r5;
    }

    public int doOutput(byte[] bArr, int r6, int r7) {
        if (!this.squeezing) {
            absorbBits(15, 4);
        }
        squeeze(bArr, r6, r7 * 8);
        return r7;
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHAKE" + this.fixedOutputLength;
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.fixedOutputLength / 4;
    }
}
