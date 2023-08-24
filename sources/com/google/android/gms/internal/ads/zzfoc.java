package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfoc extends Exception {
    private final int zza;

    public zzfoc(int r1, String str) {
        super(str);
        this.zza = r1;
    }

    public final int zza() {
        return this.zza;
    }

    public zzfoc(int r1, Throwable th) {
        super(th);
        this.zza = r1;
    }
}
