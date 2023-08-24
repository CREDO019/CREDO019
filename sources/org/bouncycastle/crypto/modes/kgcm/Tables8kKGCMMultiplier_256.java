package org.bouncycastle.crypto.modes.kgcm;

import java.lang.reflect.Array;

/* loaded from: classes5.dex */
public class Tables8kKGCMMultiplier_256 implements KGCMMultiplier {

    /* renamed from: T */
    private long[][] f2091T;

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        long[][] jArr2 = this.f2091T;
        if (jArr2 == null) {
            this.f2091T = (long[][]) Array.newInstance(long.class, 256, 4);
        } else if (KGCMUtil_256.equal(jArr, jArr2[1])) {
            return;
        }
        KGCMUtil_256.copy(jArr, this.f2091T[1]);
        for (int r1 = 2; r1 < 256; r1 += 2) {
            long[][] jArr3 = this.f2091T;
            KGCMUtil_256.multiplyX(jArr3[r1 >> 1], jArr3[r1]);
            long[][] jArr4 = this.f2091T;
            KGCMUtil_256.add(jArr4[r1], jArr4[1], jArr4[r1 + 1]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        long[] jArr2 = new long[4];
        KGCMUtil_256.copy(this.f2091T[((int) (jArr[3] >>> 56)) & 255], jArr2);
        for (int r1 = 30; r1 >= 0; r1--) {
            KGCMUtil_256.multiplyX8(jArr2, jArr2);
            KGCMUtil_256.add(this.f2091T[((int) (jArr[r1 >>> 3] >>> ((r1 & 7) << 3))) & 255], jArr2, jArr2);
        }
        KGCMUtil_256.copy(jArr2, jArr);
    }
}
