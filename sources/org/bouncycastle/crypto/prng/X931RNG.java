package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.BlockCipher;

/* loaded from: classes5.dex */
public class X931RNG {
    private static final int BLOCK128_MAX_BITS_REQUEST = 262144;
    private static final long BLOCK128_RESEED_MAX = 8388608;
    private static final int BLOCK64_MAX_BITS_REQUEST = 4096;
    private static final long BLOCK64_RESEED_MAX = 32768;

    /* renamed from: DT */
    private final byte[] f2160DT;

    /* renamed from: I */
    private final byte[] f2161I;

    /* renamed from: R */
    private final byte[] f2162R;

    /* renamed from: V */
    private byte[] f2163V;
    private final BlockCipher engine;
    private final EntropySource entropySource;
    private long reseedCounter = 1;

    public X931RNG(BlockCipher blockCipher, byte[] bArr, EntropySource entropySource) {
        this.engine = blockCipher;
        this.entropySource = entropySource;
        byte[] bArr2 = new byte[blockCipher.getBlockSize()];
        this.f2160DT = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.f2161I = new byte[blockCipher.getBlockSize()];
        this.f2162R = new byte[blockCipher.getBlockSize()];
    }

    private void increment(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte b = (byte) (bArr[length] + 1);
            bArr[length] = b;
            if (b != 0) {
                return;
            }
        }
    }

    private static boolean isTooLarge(byte[] bArr, int r1) {
        return bArr != null && bArr.length > r1;
    }

    private void process(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        for (int r1 = 0; r1 != bArr.length; r1++) {
            bArr[r1] = (byte) (bArr2[r1] ^ bArr3[r1]);
        }
        this.engine.processBlock(bArr, 0, bArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int generate(byte[] bArr, boolean z) {
        if (this.f2162R.length == 8) {
            if (this.reseedCounter > 32768) {
                return -1;
            }
            if (isTooLarge(bArr, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else if (this.reseedCounter > BLOCK128_RESEED_MAX) {
            return -1;
        } else {
            if (isTooLarge(bArr, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (z || this.f2163V == null) {
            byte[] entropy = this.entropySource.getEntropy();
            this.f2163V = entropy;
            if (entropy.length != this.engine.getBlockSize()) {
                throw new IllegalStateException("Insufficient entropy returned");
            }
        }
        int length = bArr.length / this.f2162R.length;
        for (int r1 = 0; r1 < length; r1++) {
            this.engine.processBlock(this.f2160DT, 0, this.f2161I, 0);
            process(this.f2162R, this.f2161I, this.f2163V);
            process(this.f2163V, this.f2162R, this.f2161I);
            byte[] bArr2 = this.f2162R;
            System.arraycopy(bArr2, 0, bArr, bArr2.length * r1, bArr2.length);
            increment(this.f2160DT);
        }
        int length2 = bArr.length - (this.f2162R.length * length);
        if (length2 > 0) {
            this.engine.processBlock(this.f2160DT, 0, this.f2161I, 0);
            process(this.f2162R, this.f2161I, this.f2163V);
            process(this.f2163V, this.f2162R, this.f2161I);
            byte[] bArr3 = this.f2162R;
            System.arraycopy(bArr3, 0, bArr, length * bArr3.length, length2);
            increment(this.f2160DT);
        }
        this.reseedCounter++;
        return bArr.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EntropySource getEntropySource() {
        return this.entropySource;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reseed() {
        byte[] entropy = this.entropySource.getEntropy();
        this.f2163V = entropy;
        if (entropy.length != this.engine.getBlockSize()) {
            throw new IllegalStateException("Insufficient entropy returned");
        }
        this.reseedCounter = 1L;
    }
}
