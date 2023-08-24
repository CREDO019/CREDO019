package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes3.dex */
class SeedDerive {

    /* renamed from: I */
    private final byte[] f2440I;
    private final Digest digest;

    /* renamed from: j */
    private int f2441j;
    private final byte[] masterSeed;

    /* renamed from: q */
    private int f2442q;

    public SeedDerive(byte[] bArr, byte[] bArr2, Digest digest) {
        this.f2440I = bArr;
        this.masterSeed = bArr2;
        this.digest = digest;
    }

    public void deriveSeed(byte[] bArr, boolean z) {
        deriveSeed(bArr, z, 0);
    }

    public void deriveSeed(byte[] bArr, boolean z, int r3) {
        deriveSeed(bArr, r3);
        if (z) {
            this.f2441j++;
        }
    }

    public byte[] deriveSeed(byte[] bArr, int r6) {
        if (bArr.length >= this.digest.getDigestSize()) {
            Digest digest = this.digest;
            byte[] bArr2 = this.f2440I;
            digest.update(bArr2, 0, bArr2.length);
            this.digest.update((byte) (this.f2442q >>> 24));
            this.digest.update((byte) (this.f2442q >>> 16));
            this.digest.update((byte) (this.f2442q >>> 8));
            this.digest.update((byte) this.f2442q);
            this.digest.update((byte) (this.f2441j >>> 8));
            this.digest.update((byte) this.f2441j);
            this.digest.update((byte) -1);
            Digest digest2 = this.digest;
            byte[] bArr3 = this.masterSeed;
            digest2.update(bArr3, 0, bArr3.length);
            this.digest.doFinal(bArr, r6);
            return bArr;
        }
        throw new IllegalArgumentException("target length is less than digest size.");
    }

    public byte[] getI() {
        return this.f2440I;
    }

    public int getJ() {
        return this.f2441j;
    }

    public byte[] getMasterSeed() {
        return this.masterSeed;
    }

    public int getQ() {
        return this.f2442q;
    }

    public void setJ(int r1) {
        this.f2441j = r1;
    }

    public void setQ(int r1) {
        this.f2442q = r1;
    }
}
