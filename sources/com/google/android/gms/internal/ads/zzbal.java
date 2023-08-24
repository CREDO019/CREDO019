package com.google.android.gms.internal.ads;

import android.os.Trace;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbal {
    public static void zza(String str) {
        if (zzban.zza >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void zzb() {
        if (zzban.zza >= 18) {
            Trace.endSection();
        }
    }
}
