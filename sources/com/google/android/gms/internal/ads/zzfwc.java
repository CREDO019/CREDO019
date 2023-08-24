package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfwc {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(@CheckForNull Object obj, int r3) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] zzb(Object[] objArr, int r3) {
        for (int r0 = 0; r0 < r3; r0++) {
            zza(objArr[r0], r0);
        }
        return objArr;
    }
}
