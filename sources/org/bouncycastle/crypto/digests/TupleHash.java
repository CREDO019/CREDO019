package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class TupleHash implements Xof, Digest {
    private static final byte[] N_TUPLE_HASH = Strings.toByteArray("TupleHash");
    private final int bitLength;
    private final CSHAKEDigest cshake;
    private boolean firstOutput;
    private final int outputLength;

    public TupleHash(int r2, byte[] bArr) {
        this(r2, bArr, r2 * 2);
    }

    public TupleHash(int r3, byte[] bArr, int r5) {
        this.cshake = new CSHAKEDigest(r3, N_TUPLE_HASH, bArr);
        this.bitLength = r3;
        this.outputLength = (r5 + 7) / 8;
        reset();
    }

    public TupleHash(TupleHash tupleHash) {
        CSHAKEDigest cSHAKEDigest = new CSHAKEDigest(tupleHash.cshake);
        this.cshake = cSHAKEDigest;
        int r0 = cSHAKEDigest.fixedOutputLength;
        this.bitLength = r0;
        this.outputLength = (r0 * 2) / 8;
        this.firstOutput = tupleHash.firstOutput;
    }

    private void wrapUp(int r5) {
        byte[] rightEncode = XofUtils.rightEncode(r5 * 8);
        this.cshake.update(rightEncode, 0, rightEncode.length);
        this.firstOutput = false;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int r4) throws DataLengthException, IllegalStateException {
        if (this.firstOutput) {
            wrapUp(getDigestSize());
        }
        int doFinal = this.cshake.doFinal(bArr, r4, getDigestSize());
        reset();
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Xof
    public int doFinal(byte[] bArr, int r3, int r4) {
        if (this.firstOutput) {
            wrapUp(getDigestSize());
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
        return "TupleHash" + this.cshake.getAlgorithmName().substring(6);
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
        this.firstOutput = true;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) throws IllegalStateException {
        byte[] encode = XofUtils.encode(b);
        this.cshake.update(encode, 0, encode.length);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int r3, int r4) throws DataLengthException, IllegalStateException {
        byte[] encode = XofUtils.encode(bArr, r3, r4);
        this.cshake.update(encode, 0, encode.length);
    }
}
