package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfsf {
    public static int zza(int r5, int r6, String str) {
        String zzb;
        if (r5 < 0 || r5 >= r6) {
            if (r5 < 0) {
                zzb = zzfsu.zzb("%s (%s) must not be negative", "index", Integer.valueOf(r5));
            } else if (r6 < 0) {
                throw new IllegalArgumentException("negative size: " + r6);
            } else {
                zzb = zzfsu.zzb("%s (%s) must be less than size (%s)", "index", Integer.valueOf(r5), Integer.valueOf(r6));
            }
            throw new IndexOutOfBoundsException(zzb);
        }
        return r5;
    }

    public static int zzb(int r1, int r2, String str) {
        if (r1 < 0 || r1 > r2) {
            throw new IndexOutOfBoundsException(zzj(r1, r2, "index"));
        }
        return r1;
    }

    public static Object zzc(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    public static Object zzd(@CheckForNull Object obj, String str, @CheckForNull Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(zzfsu.zzb(str, obj2));
    }

    public static void zze(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzf(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static void zzg(int r2, int r3, int r4) {
        String zzj;
        if (r2 < 0 || r3 < r2 || r3 > r4) {
            if (r2 < 0 || r2 > r4) {
                zzj = zzj(r2, r4, "start index");
            } else {
                zzj = (r3 < 0 || r3 > r4) ? zzj(r3, r4, "end index") : zzfsu.zzb("end index (%s) must not be less than start index (%s)", Integer.valueOf(r3), Integer.valueOf(r2));
            }
            throw new IndexOutOfBoundsException(zzj);
        }
    }

    public static void zzh(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void zzi(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }

    private static String zzj(int r4, int r5, String str) {
        if (r4 < 0) {
            return zzfsu.zzb("%s (%s) must not be negative", str, Integer.valueOf(r4));
        }
        if (r5 >= 0) {
            return zzfsu.zzb("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(r4), Integer.valueOf(r5));
        }
        throw new IllegalArgumentException("negative size: " + r5);
    }
}
