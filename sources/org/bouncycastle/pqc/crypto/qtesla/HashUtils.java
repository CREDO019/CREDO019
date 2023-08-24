package org.bouncycastle.pqc.crypto.qtesla;

import org.bouncycastle.crypto.digests.CSHAKEDigest;
import org.bouncycastle.crypto.digests.SHAKEDigest;

/* loaded from: classes3.dex */
class HashUtils {
    static final int SECURE_HASH_ALGORITHM_KECCAK_128_RATE = 168;
    static final int SECURE_HASH_ALGORITHM_KECCAK_256_RATE = 136;

    HashUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void customizableSecureHashAlgorithmKECCAK128Simple(byte[] bArr, int r5, int r6, short s, byte[] bArr2, int r9, int r10) {
        CSHAKEDigest cSHAKEDigest = new CSHAKEDigest(128, null, new byte[]{(byte) s, (byte) (s >> 8)});
        cSHAKEDigest.update(bArr2, r9, r10);
        cSHAKEDigest.doFinal(bArr, r5, r6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void customizableSecureHashAlgorithmKECCAK256Simple(byte[] bArr, int r5, int r6, short s, byte[] bArr2, int r9, int r10) {
        CSHAKEDigest cSHAKEDigest = new CSHAKEDigest(256, null, new byte[]{(byte) s, (byte) (s >> 8)});
        cSHAKEDigest.update(bArr2, r9, r10);
        cSHAKEDigest.doFinal(bArr, r5, r6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void secureHashAlgorithmKECCAK128(byte[] bArr, int r3, int r4, byte[] bArr2, int r6, int r7) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
        sHAKEDigest.update(bArr2, r6, r7);
        sHAKEDigest.doFinal(bArr, r3, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void secureHashAlgorithmKECCAK256(byte[] bArr, int r3, int r4, byte[] bArr2, int r6, int r7) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update(bArr2, r6, r7);
        sHAKEDigest.doFinal(bArr, r3, r4);
    }
}
