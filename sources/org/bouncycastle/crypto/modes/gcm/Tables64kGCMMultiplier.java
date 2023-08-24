package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Tables64kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f2082H;

    /* renamed from: T */
    private long[][][] f2083T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.f2083T == null) {
            this.f2083T = (long[][][]) Array.newInstance(long.class, 16, 256, 2);
        } else if (GCMUtil.areEqual(this.f2082H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.f2082H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        for (int r8 = 0; r8 < 16; r8++) {
            long[][][] jArr = this.f2083T;
            long[][] jArr2 = jArr[r8];
            if (r8 == 0) {
                GCMUtil.asLongs(this.f2082H, jArr2[1]);
                GCMUtil.multiplyP7(jArr2[1], jArr2[1]);
            } else {
                GCMUtil.multiplyP8(jArr[r8 - 1][1], jArr2[1]);
            }
            for (int r1 = 2; r1 < 256; r1 += 2) {
                GCMUtil.divideP(jArr2[r1 >> 1], jArr2[r1]);
                GCMUtil.xor(jArr2[r1], jArr2[1], jArr2[r1 + 1]);
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] jArr = this.f2083T[15][bArr[15] & 255];
        long j = jArr[0];
        long j2 = jArr[1];
        for (int r0 = 14; r0 >= 0; r0--) {
            long[] jArr2 = this.f2083T[r0][bArr[r0] & 255];
            j ^= jArr2[0];
            j2 ^= jArr2[1];
        }
        Pack.longToBigEndian(j, bArr, 0);
        Pack.longToBigEndian(j2, bArr, 8);
    }
}
