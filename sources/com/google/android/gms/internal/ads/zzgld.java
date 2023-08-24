package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgld {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zza(byte[] bArr) {
        int length = bArr.length;
        if (length >= 16) {
            throw new IllegalArgumentException("x must be smaller than a block.");
        }
        byte[] copyOf = Arrays.copyOf(bArr, 16);
        copyOf[length] = Byte.MIN_VALUE;
        return copyOf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzb(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("value must be a block.");
        }
        byte[] bArr2 = new byte[16];
        for (int r3 = 0; r3 < 16; r3++) {
            byte b = bArr[r3];
            byte b2 = (byte) ((b + b) & 254);
            bArr2[r3] = b2;
            if (r3 < 15) {
                bArr2[r3] = (byte) (((bArr[r3 + 1] >> 7) & 1) | b2);
            }
        }
        bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & TsExtractor.TS_STREAM_TYPE_E_AC3)) ^ bArr2[15]);
        return bArr2;
    }
}
