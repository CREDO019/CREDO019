package org.bouncycastle.crypto.modes.gcm;

import com.google.common.base.Ascii;
import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class Tables8kGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private byte[] f2084H;

    /* renamed from: T */
    private long[][][] f2085T;

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        if (this.f2085T == null) {
            this.f2085T = (long[][][]) Array.newInstance(long.class, 32, 16, 2);
        } else if (GCMUtil.areEqual(this.f2084H, bArr) != 0) {
            return;
        }
        byte[] bArr2 = new byte[16];
        this.f2084H = bArr2;
        GCMUtil.copy(bArr, bArr2);
        for (int r8 = 0; r8 < 32; r8++) {
            long[][][] jArr = this.f2085T;
            long[][] jArr2 = jArr[r8];
            if (r8 == 0) {
                GCMUtil.asLongs(this.f2084H, jArr2[1]);
                GCMUtil.multiplyP3(jArr2[1], jArr2[1]);
            } else {
                GCMUtil.multiplyP4(jArr[r8 - 1][1], jArr2[1]);
            }
            for (int r1 = 2; r1 < 16; r1 += 2) {
                GCMUtil.divideP(jArr2[r1 >> 1], jArr2[r1]);
                GCMUtil.xor(jArr2[r1], jArr2[1], jArr2[r1 + 1]);
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        long j = 0;
        long j2 = 0;
        for (int r5 = 15; r5 >= 0; r5--) {
            long[][][] jArr = this.f2085T;
            int r8 = r5 + r5;
            long[] jArr2 = jArr[r8 + 1][bArr[r5] & Ascii.f1128SI];
            long[] jArr3 = jArr[r8][(bArr[r5] & 240) >>> 4];
            j ^= jArr2[0] ^ jArr3[0];
            j2 ^= jArr3[1] ^ jArr2[1];
        }
        Pack.longToBigEndian(j, bArr, 0);
        Pack.longToBigEndian(j2, bArr, 8);
    }
}
