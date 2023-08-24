package com.google.android.gms.internal.vision;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.common.base.Ascii;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzju {
    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzd(byte b) {
        return b >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zze(byte b) {
        return b < -32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzf(byte b) {
        return b < -16;
    }

    private static boolean zzg(byte b) {
        return b > -65;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b, char[] cArr, int r2) {
        cArr[r2] = (char) b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b, byte b2, char[] cArr, int r4) throws zzhc {
        if (b < -62 || zzg(b2)) {
            throw zzhc.zzgt();
        }
        cArr[r4] = (char) (((b & Ascii.f1131US) << 6) | (b2 & Utf8.REPLACEMENT_BYTE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b, byte b2, byte b3, char[] cArr, int r6) throws zzhc {
        if (zzg(b2) || ((b == -32 && b2 < -96) || ((b == -19 && b2 >= -96) || zzg(b3)))) {
            throw zzhc.zzgt();
        }
        cArr[r6] = (char) (((b & Ascii.f1128SI) << 12) | ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (b3 & Utf8.REPLACEMENT_BYTE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(byte b, byte b2, byte b3, byte b4, char[] cArr, int r7) throws zzhc {
        if (zzg(b2) || (((b << Ascii.f1122FS) + (b2 + 112)) >> 30) != 0 || zzg(b3) || zzg(b4)) {
            throw zzhc.zzgt();
        }
        int r2 = ((b & 7) << 18) | ((b2 & Utf8.REPLACEMENT_BYTE) << 12) | ((b3 & Utf8.REPLACEMENT_BYTE) << 6) | (b4 & Utf8.REPLACEMENT_BYTE);
        cArr[r7] = (char) ((r2 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
        cArr[r7 + 1] = (char) ((r2 & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
    }
}
