package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzmy extends zzmx {
    @Override // com.google.android.gms.internal.measurement.zzmx
    final int zza(int r7, byte[] bArr, int r9, int r10) {
        while (r9 < r10 && bArr[r9] >= 0) {
            r9++;
        }
        if (r9 >= r10) {
            return 0;
        }
        while (r9 < r10) {
            int r1 = r9 + 1;
            byte b = bArr[r9];
            if (b < 0) {
                if (b < -32) {
                    if (r1 < r10) {
                        if (b >= -62) {
                            r9 = r1 + 1;
                            if (bArr[r1] > -65) {
                            }
                        }
                        return -1;
                    }
                    return b;
                }
                if (b < -16) {
                    if (r1 < r10 - 1) {
                        int r4 = r1 + 1;
                        byte b2 = bArr[r1];
                        if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                            r9 = r4 + 1;
                            if (bArr[r4] > -65) {
                            }
                        }
                    } else {
                        return zzna.zza(bArr, r1, r10);
                    }
                } else if (r1 < r10 - 2) {
                    int r2 = r1 + 1;
                    byte b3 = bArr[r1];
                    if (b3 <= -65 && (((b << Ascii.f1122FS) + (b3 + 112)) >> 30) == 0) {
                        int r92 = r2 + 1;
                        if (bArr[r2] <= -65) {
                            r1 = r92 + 1;
                            if (bArr[r92] > -65) {
                            }
                        }
                    }
                } else {
                    return zzna.zza(bArr, r1, r10);
                }
                return -1;
            }
            r9 = r1;
        }
        return 0;
    }
}
