package com.google.android.gms.internal.common;

import org.jspecify.nullness.NullMarked;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
@NullMarked
/* loaded from: classes.dex */
public final class zzs {
    public static int zza(int r5, int r6, String str) {
        String zza;
        if (r5 < 0 || r5 >= r6) {
            if (r5 < 0) {
                zza = zzy.zza("%s (%s) must not be negative", "index", Integer.valueOf(r5));
            } else if (r6 < 0) {
                throw new IllegalArgumentException("negative size: " + r6);
            } else {
                zza = zzy.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(r5), Integer.valueOf(r6));
            }
            throw new IndexOutOfBoundsException(zza);
        }
        return r5;
    }

    public static int zzb(int r1, int r2, String str) {
        if (r1 < 0 || r1 > r2) {
            throw new IndexOutOfBoundsException(zzd(r1, r2, "index"));
        }
        return r1;
    }

    public static void zzc(int r2, int r3, int r4) {
        String zzd;
        if (r2 < 0 || r3 < r2 || r3 > r4) {
            if (r2 < 0 || r2 > r4) {
                zzd = zzd(r2, r4, "start index");
            } else {
                zzd = (r3 < 0 || r3 > r4) ? zzd(r3, r4, "end index") : zzy.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(r3), Integer.valueOf(r2));
            }
            throw new IndexOutOfBoundsException(zzd);
        }
    }

    private static String zzd(int r4, int r5, String str) {
        if (r4 < 0) {
            return zzy.zza("%s (%s) must not be negative", str, Integer.valueOf(r4));
        }
        if (r5 >= 0) {
            return zzy.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(r4), Integer.valueOf(r5));
        }
        throw new IllegalArgumentException("negative size: " + r5);
    }
}
