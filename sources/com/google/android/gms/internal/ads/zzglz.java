package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzglz {
    public static String zza(byte[] bArr) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (byte b : bArr) {
            int r3 = b & 255;
            sb.append("0123456789abcdef".charAt(r3 >> 4));
            sb.append("0123456789abcdef".charAt(r3 & 15));
        }
        return sb.toString();
    }
}
