package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.MurmurHash3;
import java.io.UnsupportedEncodingException;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbcr {
    public static int zza(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        return MurmurHash3.murmurhash3_x86_32(bytes, 0, bytes.length, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00b6, code lost:
        if (true != r4) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00c6, code lost:
        if (true != r4) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00c8, code lost:
        r5 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00c9, code lost:
        r4 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] zzb(java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcr.zzb(java.lang.String, boolean):java.lang.String[]");
    }
}
