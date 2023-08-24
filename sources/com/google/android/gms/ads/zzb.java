package com.google.android.gms.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzb {
    public static int zza(AdSize adSize) {
        return adSize.zzb();
    }

    public static int zzb(AdSize adSize) {
        return adSize.zza();
    }

    public static AdSize zzc(int r1, int r2, String str) {
        return new AdSize(r1, r2, str);
    }

    public static AdSize zzd(int r1, int r2) {
        AdSize adSize = new AdSize(r1, r2);
        adSize.zze(true);
        adSize.zzc(r2);
        return adSize;
    }

    public static AdSize zze(int r1, int r2) {
        AdSize adSize = new AdSize(r1, r2);
        adSize.zzf(true);
        adSize.zzd(r2);
        return adSize;
    }

    public static boolean zzf(AdSize adSize) {
        return adSize.zzg();
    }

    public static boolean zzg(AdSize adSize) {
        return adSize.zzh();
    }

    public static boolean zzh(AdSize adSize) {
        return adSize.zzi();
    }
}
