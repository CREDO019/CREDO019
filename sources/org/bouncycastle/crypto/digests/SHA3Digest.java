package org.bouncycastle.crypto.digests;

/* loaded from: classes5.dex */
public class SHA3Digest extends KeccakDigest {
    public SHA3Digest() {
        this(256);
    }

    public SHA3Digest(int r1) {
        super(checkBitLength(r1));
    }

    public SHA3Digest(SHA3Digest sHA3Digest) {
        super(sHA3Digest);
    }

    private static int checkBitLength(int r3) {
        if (r3 == 224 || r3 == 256 || r3 == 384 || r3 == 512) {
            return r3;
        }
        throw new IllegalArgumentException("'bitLength' " + r3 + " not supported for SHA-3");
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r3) {
        absorbBits(2, 2);
        return super.doFinal(bArr, r3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.crypto.digests.KeccakDigest
    public int doFinal(byte[] bArr, int r4, byte b, int r6) {
        if (r6 < 0 || r6 > 7) {
            throw new IllegalArgumentException("'partialBits' must be in the range [0,7]");
        }
        int r5 = (b & ((1 << r6) - 1)) | (2 << r6);
        int r62 = r6 + 2;
        if (r62 >= 8) {
            absorb((byte) r5);
            r62 -= 8;
            r5 >>>= 8;
        }
        return super.doFinal(bArr, r4, (byte) r5, r62);
    }

    @Override // org.bouncycastle.crypto.digests.KeccakDigest, org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA3-" + this.fixedOutputLength;
    }
}
