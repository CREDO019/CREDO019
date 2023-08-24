package org.bouncycastle.crypto.prng;

/* loaded from: classes5.dex */
public class EntropyUtil {
    public static byte[] generateSeed(EntropySource entropySource, int r8) {
        byte[] bArr = new byte[r8];
        if (r8 * 8 <= entropySource.entropySize()) {
            System.arraycopy(entropySource.getEntropy(), 0, bArr, 0, r8);
        } else {
            int entropySize = entropySource.entropySize() / 8;
            for (int r2 = 0; r2 < r8; r2 += entropySize) {
                byte[] entropy = entropySource.getEntropy();
                int r6 = r8 - r2;
                if (entropy.length <= r6) {
                    System.arraycopy(entropy, 0, bArr, r2, entropy.length);
                } else {
                    System.arraycopy(entropy, 0, bArr, r2, r6);
                }
            }
        }
        return bArr;
    }
}
