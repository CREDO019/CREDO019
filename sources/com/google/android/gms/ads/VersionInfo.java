package com.google.android.gms.ads;

import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public class VersionInfo {
    protected int zza;
    protected int zzb;
    protected int zzc;

    public VersionInfo(int r1, int r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public int getMajorVersion() {
        return this.zza;
    }

    public int getMicroVersion() {
        return this.zzc;
    }

    public int getMinorVersion() {
        return this.zzb;
    }

    public String toString() {
        return String.format(Locale.US, "%d.%d.%d", Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc));
    }
}
