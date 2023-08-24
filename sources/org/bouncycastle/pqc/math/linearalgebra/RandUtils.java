package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;

/* loaded from: classes4.dex */
public class RandUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int nextInt(SecureRandom secureRandom, int r4) {
        int nextInt;
        int r1;
        if (((-r4) & r4) == r4) {
            return (int) ((r4 * (secureRandom.nextInt() >>> 1)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() >>> 1;
            r1 = nextInt % r4;
        } while ((nextInt - r1) + (r4 - 1) < 0);
        return r1;
    }
}
