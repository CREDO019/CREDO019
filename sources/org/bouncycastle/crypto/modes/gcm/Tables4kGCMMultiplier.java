package org.bouncycastle.crypto.modes.gcm;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Tables4kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f2080H;

    /* renamed from: T */
    private long[][] f2081T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.f2081T == null) {
            this.f2081T = (long[][]) Array.newInstance(long.class, 256, 2);
        } else if (GCMUtil.areEqual(this.f2080H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.f2080H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        GCMUtil.asLongs(this.f2080H, this.f2081T[1]);
        long[][] jArr = this.f2081T;
        GCMUtil.multiplyP7(jArr[1], jArr[1]);
        for (int r1 = 2; r1 < 256; r1 += 2) {
            long[][] jArr2 = this.f2081T;
            GCMUtil.divideP(jArr2[r1 >> 1], jArr2[r1]);
            long[][] jArr3 = this.f2081T;
            GCMUtil.xor(jArr3[r1], jArr3[1], jArr3[r1 + 1]);
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long[] jArr = this.f2081T[bArr[15] & 255];
        long j = jArr[0];
        long j2 = jArr[1];
        for (int r2 = 14; r2 >= 0; r2--) {
            long[] jArr2 = this.f2081T[bArr[r2] & 255];
            long j3 = j2 << 56;
            j2 = ((j2 >>> 8) | (j << 56)) ^ jArr2[1];
            j = (((((j >>> 8) ^ jArr2[0]) ^ j3) ^ (j3 >>> 1)) ^ (j3 >>> 2)) ^ (j3 >>> 7);
        }
        Pack.longToBigEndian(j, bArr, 0);
        Pack.longToBigEndian(j2, bArr, 8);
    }
}
