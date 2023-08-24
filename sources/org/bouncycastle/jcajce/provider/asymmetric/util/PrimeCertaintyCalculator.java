package org.bouncycastle.jcajce.provider.asymmetric.util;

/* loaded from: classes5.dex */
public class PrimeCertaintyCalculator {
    private PrimeCertaintyCalculator() {
    }

    public static int getDefaultCertainty(int r1) {
        if (r1 <= 1024) {
            return 80;
        }
        return (((r1 - 1) / 1024) * 16) + 96;
    }
}
