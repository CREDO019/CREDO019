package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzase extends Exception {
    private zzase(int r1, String str, Throwable th, int r4) {
        super(null, th);
    }

    public static zzase zza(Exception exc, int r4) {
        return new zzase(1, null, exc, r4);
    }

    public static zzase zzb(IOException iOException) {
        return new zzase(0, null, iOException, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzase zzc(RuntimeException runtimeException) {
        return new zzase(2, null, runtimeException, -1);
    }
}
