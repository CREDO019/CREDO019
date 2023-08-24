package org.bouncycastle.crypto.prng;

/* loaded from: classes5.dex */
public interface RandomGenerator {
    void addSeedMaterial(long j);

    void addSeedMaterial(byte[] bArr);

    void nextBytes(byte[] bArr);

    void nextBytes(byte[] bArr, int r2, int r3);
}
