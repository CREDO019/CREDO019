package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzeas extends Exception {
    private final int zza;

    public zzeas(int r1) {
        this.zza = r1;
    }

    public final int zza() {
        return this.zza;
    }

    public zzeas(int r1, String str) {
        super(str);
        this.zza = r1;
    }

    public zzeas(int r1, String str, Throwable th) {
        super(str, th);
        this.zza = 1;
    }
}
