package org.bouncycastle.pqc.crypto.newhope;

import kotlin.UShort;

/* loaded from: classes3.dex */
class Reduce {
    static final int QInv = 12287;
    static final int RLog = 18;
    static final int RMask = 262143;

    Reduce() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short barrett(short s) {
        int r1 = s & UShort.MAX_VALUE;
        return (short) (r1 - (((r1 * 5) >>> 16) * 12289));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short montgomery(int r2) {
        return (short) (((((r2 * QInv) & RMask) * 12289) + r2) >>> 18);
    }
}
