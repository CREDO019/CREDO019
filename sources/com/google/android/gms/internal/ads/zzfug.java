package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfug {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r1) {
        return (r1 < 32 ? 4 : 2) * (r1 + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(@CheckForNull Object obj, @CheckForNull Object obj2, int r11, Object obj3, int[] r13, Object[] objArr, @CheckForNull Object[] objArr2) {
        int r2;
        int r6;
        int zzb = zzfun.zzb(obj);
        int r1 = zzb & r11;
        int zzc = zzc(obj3, r1);
        if (zzc != 0) {
            int r4 = ~r11;
            int r0 = zzb & r4;
            int r5 = -1;
            while (true) {
                r2 = zzc - 1;
                r6 = r13[r2];
                if ((r6 & r4) != r0 || !zzfsa.zza(obj, objArr[r2]) || (objArr2 != null && !zzfsa.zza(obj2, objArr2[r2]))) {
                    int r52 = r6 & r11;
                    if (r52 == 0) {
                        break;
                    }
                    r5 = r2;
                    zzc = r52;
                }
            }
            int r9 = r6 & r11;
            if (r5 == -1) {
                zze(obj3, r1, r9);
            } else {
                r13[r5] = (r9 & r11) | (r13[r5] & r4);
            }
            return r2;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(Object obj, int r2) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[r2] & 255;
        }
        if (obj instanceof short[]) {
            return (char) ((short[]) obj)[r2];
        }
        return ((int[]) obj)[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzd(int r3) {
        if (r3 >= 2 && r3 <= 1073741824 && Integer.highestOneBit(r3) == r3) {
            if (r3 <= 256) {
                return new byte[r3];
            }
            return r3 <= 65536 ? new short[r3] : new int[r3];
        }
        throw new IllegalArgumentException("must be power of 2 between 2^1 and 2^30: " + r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zze(Object obj, int r2, int r3) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[r2] = (byte) r3;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[r2] = (short) r3;
        } else {
            ((int[]) obj)[r2] = r3;
        }
    }
}
