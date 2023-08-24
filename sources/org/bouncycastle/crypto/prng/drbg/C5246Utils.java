package org.bouncycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.bouncycastle.util.Integers;

/* renamed from: org.bouncycastle.crypto.prng.drbg.Utils */
/* loaded from: classes5.dex */
class C5246Utils {
    static final Hashtable maxSecurityStrengths;

    static {
        Hashtable hashtable = new Hashtable();
        maxSecurityStrengths = hashtable;
        hashtable.put("SHA-1", Integers.valueOf(128));
        hashtable.put(McElieceCCA2KeyGenParameterSpec.SHA224, Integers.valueOf(192));
        hashtable.put("SHA-256", Integers.valueOf(256));
        hashtable.put("SHA-384", Integers.valueOf(256));
        hashtable.put("SHA-512", Integers.valueOf(256));
        hashtable.put("SHA-512/224", Integers.valueOf(192));
        hashtable.put(SPHINCSKeyParameters.SHA512_256, Integers.valueOf(256));
    }

    C5246Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMaxSecurityStrength(Digest digest) {
        return ((Integer) maxSecurityStrengths.get(digest.getAlgorithmName())).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMaxSecurityStrength(Mac mac) {
        String algorithmName = mac.getAlgorithmName();
        return ((Integer) maxSecurityStrengths.get(algorithmName.substring(0, algorithmName.indexOf("/")))).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] hash_df(Digest digest, byte[] bArr, int r12) {
        int r0 = (r12 + 7) / 8;
        byte[] bArr2 = new byte[r0];
        int digestSize = r0 / digest.getDigestSize();
        int digestSize2 = digest.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        int r5 = 1;
        int r6 = 0;
        for (int r7 = 0; r7 <= digestSize; r7++) {
            digest.update((byte) r5);
            digest.update((byte) (r12 >> 24));
            digest.update((byte) (r12 >> 16));
            digest.update((byte) (r12 >> 8));
            digest.update((byte) r12);
            digest.update(bArr, 0, bArr.length);
            digest.doFinal(bArr3, 0);
            int r8 = r7 * digestSize2;
            int r9 = r0 - r8;
            if (r9 > digestSize2) {
                r9 = digestSize2;
            }
            System.arraycopy(bArr3, 0, bArr2, r8, r9);
            r5++;
        }
        int r122 = r12 % 8;
        if (r122 != 0) {
            int r10 = 8 - r122;
            int r11 = 0;
            while (r6 != r0) {
                int r123 = bArr2[r6] & 255;
                bArr2[r6] = (byte) ((r11 << (8 - r10)) | (r123 >>> r10));
                r6++;
                r11 = r123;
            }
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTooLarge(byte[] bArr, int r1) {
        return bArr != null && bArr.length > r1;
    }
}
