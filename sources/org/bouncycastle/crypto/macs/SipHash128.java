package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SipHash128 extends SipHash {
    public SipHash128() {
    }

    public SipHash128(int r1, int r2) {
        super(r1, r2);
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r9) throws DataLengthException, IllegalStateException {
        this.f2030m >>>= (7 - this.wordPos) << 3;
        this.f2030m >>>= 8;
        this.f2030m |= (((this.wordCount << 3) + this.wordPos) & 255) << 56;
        processMessageWord();
        this.f2033v2 ^= 238;
        applySipRounds(this.f2027d);
        long j = ((this.f2031v0 ^ this.f2032v1) ^ this.f2033v2) ^ this.f2034v3;
        this.f2032v1 ^= 221;
        applySipRounds(this.f2027d);
        reset();
        Pack.longToLittleEndian(j, bArr, r9);
        Pack.longToLittleEndian(((this.f2031v0 ^ this.f2032v1) ^ this.f2033v2) ^ this.f2034v3, bArr, r9 + 8);
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash
    public long doFinal() throws DataLengthException, IllegalStateException {
        throw new UnsupportedOperationException("doFinal() is not supported");
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash128-" + this.f2026c + "-" + this.f2027d;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public void reset() {
        super.reset();
        this.f2032v1 ^= 238;
    }
}
