package com.google.android.gms.internal.clearcut;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
final class zzfh extends zzfg {
    @Override // com.google.android.gms.internal.clearcut.zzfg
    final int zzb(int r7, byte[] bArr, int r9, int r10) {
        int zzf;
        int zzf2;
        while (r9 < r10 && bArr[r9] >= 0) {
            r9++;
        }
        if (r9 >= r10) {
            return 0;
        }
        while (r9 < r10) {
            int r0 = r9 + 1;
            byte b = bArr[r9];
            if (b < 0) {
                if (b < -32) {
                    if (r0 >= r10) {
                        return b;
                    }
                    if (b >= -62) {
                        r9 = r0 + 1;
                        if (bArr[r0] > -65) {
                        }
                    }
                    return -1;
                } else if (b >= -16) {
                    if (r0 >= r10 - 2) {
                        zzf2 = zzff.zzf(bArr, r0, r10);
                        return zzf2;
                    }
                    int r1 = r0 + 1;
                    byte b2 = bArr[r0];
                    if (b2 <= -65 && (((b << Ascii.f1122FS) + (b2 + 112)) >> 30) == 0) {
                        int r92 = r1 + 1;
                        if (bArr[r1] <= -65) {
                            r0 = r92 + 1;
                            if (bArr[r92] > -65) {
                            }
                        }
                    }
                    return -1;
                } else if (r0 >= r10 - 1) {
                    zzf = zzff.zzf(bArr, r0, r10);
                    return zzf;
                } else {
                    int r4 = r0 + 1;
                    byte b3 = bArr[r0];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        r9 = r4 + 1;
                        if (bArr[r4] > -65) {
                        }
                    }
                    return -1;
                }
            }
            r9 = r0;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r10 + r0;
     */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzb(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.zzfh.zzb(java.lang.CharSequence, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzfg
    public final void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        zzc(charSequence, byteBuffer);
    }
}
