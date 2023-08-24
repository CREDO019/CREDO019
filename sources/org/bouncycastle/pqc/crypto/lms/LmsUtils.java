package org.bouncycastle.pqc.crypto.lms;

import java.util.Objects;
import org.bouncycastle.crypto.Digest;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class LmsUtils {
    LmsUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void byteArray(byte[] bArr, int r1, int r2, Digest digest) {
        digest.update(bArr, r1, r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void byteArray(byte[] bArr, Digest digest) {
        digest.update(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int calculateStrength(LMSParameters lMSParameters) {
        Objects.requireNonNull(lMSParameters, "lmsParameters cannot be null");
        LMSigParameters lMSigParam = lMSParameters.getLMSigParam();
        return (1 << lMSigParam.getH()) * lMSigParam.getM();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void u16str(short s, Digest digest) {
        digest.update((byte) (s >>> 8));
        digest.update((byte) s);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void u32str(int r1, Digest digest) {
        digest.update((byte) (r1 >>> 24));
        digest.update((byte) (r1 >>> 16));
        digest.update((byte) (r1 >>> 8));
        digest.update((byte) r1);
    }
}
