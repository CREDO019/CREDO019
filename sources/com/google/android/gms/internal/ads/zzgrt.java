package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzgrt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String zzd(ByteBuffer byteBuffer, int r11, int r12) throws zzgoz {
        if ((r11 | r12 | ((byteBuffer.limit() - r11) - r12)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(r11), Integer.valueOf(r12)));
        }
        int r0 = r11 + r12;
        char[] cArr = new char[r12];
        int r2 = 0;
        while (r11 < r0) {
            byte b = byteBuffer.get(r11);
            if (!zzgrs.zzd(b)) {
                break;
            }
            r11++;
            cArr[r2] = (char) b;
            r2++;
        }
        int r8 = r2;
        while (r11 < r0) {
            int r22 = r11 + 1;
            byte b2 = byteBuffer.get(r11);
            if (zzgrs.zzd(b2)) {
                int r3 = r8 + 1;
                cArr[r8] = (char) b2;
                r11 = r22;
                while (true) {
                    r8 = r3;
                    if (r11 < r0) {
                        byte b3 = byteBuffer.get(r11);
                        if (!zzgrs.zzd(b3)) {
                            break;
                        }
                        r11++;
                        r3 = r8 + 1;
                        cArr[r8] = (char) b3;
                    }
                }
            } else if (zzgrs.zzf(b2)) {
                if (r22 < r0) {
                    zzgrs.zzc(b2, byteBuffer.get(r22), cArr, r8);
                    r11 = r22 + 1;
                    r8++;
                } else {
                    throw zzgoz.zzd();
                }
            } else if (zzgrs.zze(b2)) {
                if (r22 < r0 - 1) {
                    int r32 = r22 + 1;
                    zzgrs.zzb(b2, byteBuffer.get(r22), byteBuffer.get(r32), cArr, r8);
                    r11 = r32 + 1;
                    r8++;
                } else {
                    throw zzgoz.zzd();
                }
            } else if (r22 < r0 - 2) {
                int r33 = r22 + 1;
                int r4 = r33 + 1;
                zzgrs.zza(b2, byteBuffer.get(r22), byteBuffer.get(r33), byteBuffer.get(r4), cArr, r8);
                r8 += 2;
                r11 = r4 + 1;
            } else {
                throw zzgoz.zzd();
            }
        }
        return new String(cArr, 0, r8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(int r1, byte[] bArr, int r3, int r4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String zzb(byte[] bArr, int r2, int r3) throws zzgoz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzc(byte[] bArr, int r3, int r4) {
        return zza(0, bArr, r3, r4) == 0;
    }
}
