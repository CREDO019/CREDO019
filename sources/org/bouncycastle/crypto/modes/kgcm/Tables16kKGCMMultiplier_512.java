package org.bouncycastle.crypto.modes.kgcm;

import java.lang.reflect.Array;

/* loaded from: classes5.dex */
public class Tables16kKGCMMultiplier_512 implements KGCMMultiplier {

    /* renamed from: T */
    private long[][] f2089T;

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void init(long[] jArr) {
        long[][] jArr2 = this.f2089T;
        if (jArr2 == null) {
            this.f2089T = (long[][]) Array.newInstance(long.class, 256, 8);
        } else if (KGCMUtil_512.equal(jArr, jArr2[1])) {
            return;
        }
        KGCMUtil_512.copy(jArr, this.f2089T[1]);
        for (int r1 = 2; r1 < 256; r1 += 2) {
            long[][] jArr3 = this.f2089T;
            KGCMUtil_512.multiplyX(jArr3[r1 >> 1], jArr3[r1]);
            long[][] jArr4 = this.f2089T;
            KGCMUtil_512.add(jArr4[r1], jArr4[1], jArr4[r1 + 1]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier
    public void multiplyH(long[] jArr) {
        long[] jArr2 = new long[8];
        KGCMUtil_512.copy(this.f2089T[((int) (jArr[7] >>> 56)) & 255], jArr2);
        for (int r1 = 62; r1 >= 0; r1--) {
            KGCMUtil_512.multiplyX8(jArr2, jArr2);
            KGCMUtil_512.add(this.f2089T[((int) (jArr[r1 >>> 3] >>> ((r1 & 7) << 3))) & 255], jArr2, jArr2);
        }
        KGCMUtil_512.copy(jArr2, jArr);
    }
}
