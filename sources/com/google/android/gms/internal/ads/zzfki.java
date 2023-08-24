package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfki {
    private final String zza;
    private final String zzb;

    private zzfki(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public static zzfki zza(String str, String str2) {
        zzflg.zza(str, "Name is null or empty");
        zzflg.zza(str2, "Version is null or empty");
        return new zzfki(str, str2);
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }
}
