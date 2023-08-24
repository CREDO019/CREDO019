package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzew extends IOException {
    public final int zza;

    public zzew(int r1) {
        this.zza = r1;
    }

    public zzew(String str, int r2) {
        super(str);
        this.zza = r2;
    }

    public zzew(String str, Throwable th, int r3) {
        super(str, th);
        this.zza = r3;
    }

    public zzew(Throwable th, int r2) {
        super(th);
        this.zza = r2;
    }
}
