package com.google.android.gms.internal.ads;

import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbji extends zzbjj {
    private static final String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        int r1 = 0;
        int r2 = 0;
        while (r2 < str.length() && str.charAt(r2) == ',') {
            r2++;
        }
        while (length > 0) {
            int r3 = length - 1;
            if (str.charAt(r3) != ',') {
                break;
            }
            length = r3;
        }
        if (length < r2) {
            return null;
        }
        if (r2 != 0) {
            r1 = r2;
        } else if (length == str.length()) {
            return str;
        }
        return str.substring(r1, length);
    }

    @Override // com.google.android.gms.internal.ads.zzbjj
    public final String zza(String str, String str2) {
        String zzb = zzb(str);
        String zzb2 = zzb(str2);
        if (TextUtils.isEmpty(zzb)) {
            return zzb2;
        }
        if (TextUtils.isEmpty(zzb2)) {
            return zzb;
        }
        return zzb + "," + zzb2;
    }
}
