package org.bouncycastle.crypto.prng;

/* loaded from: classes5.dex */
public class ReversedWindowGenerator implements RandomGenerator {
    private final RandomGenerator generator;
    private byte[] window;
    private int windowCount;

    public ReversedWindowGenerator(RandomGenerator randomGenerator, int r3) {
        if (randomGenerator == null) {
            throw new IllegalArgumentException("generator cannot be null");
        }
        if (r3 < 2) {
            throw new IllegalArgumentException("windowSize must be at least 2");
        }
        this.generator = randomGenerator;
        this.window = new byte[r3];
    }

    private void doNextBytes(byte[] bArr, int r8, int r9) {
        synchronized (this) {
            for (int r1 = 0; r1 < r9; r1++) {
                if (this.windowCount < 1) {
                    RandomGenerator randomGenerator = this.generator;
                    byte[] bArr2 = this.window;
                    randomGenerator.nextBytes(bArr2, 0, bArr2.length);
                    this.windowCount = this.window.length;
                }
                byte[] bArr3 = this.window;
                int r5 = this.windowCount - 1;
                this.windowCount = r5;
                bArr[r1 + r8] = bArr3[r5];
            }
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(long j) {
        synchronized (this) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(j);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void addSeedMaterial(byte[] bArr) {
        synchronized (this) {
            this.windowCount = 0;
            this.generator.addSeedMaterial(bArr);
        }
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr) {
        doNextBytes(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.crypto.prng.RandomGenerator
    public void nextBytes(byte[] bArr, int r2, int r3) {
        doNextBytes(bArr, r2, r3);
    }
}
