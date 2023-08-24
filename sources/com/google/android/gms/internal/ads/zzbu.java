package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzbu extends IOException {
    public final boolean zza;
    public final int zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzbu(String str, Throwable th, boolean z, int r4) {
        super(str, th);
        this.zza = z;
        this.zzb = r4;
    }

    public static zzbu zza(String str, Throwable th) {
        return new zzbu(str, th, true, 1);
    }

    public static zzbu zzb(String str, Throwable th) {
        return new zzbu(str, th, true, 0);
    }

    public static zzbu zzc(String str) {
        return new zzbu(str, null, false, 1);
    }
}
