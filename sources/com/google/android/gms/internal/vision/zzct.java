package com.google.android.gms.internal.vision;

import java.util.Objects;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzct {
    public static void checkArgument(boolean z, @NullableDecl Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @NonNullDecl
    public static <T> T checkNotNull(@NonNullDecl T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static int zzc(int r6, int r7) {
        String zza;
        if (r6 < 0 || r6 >= r7) {
            if (r6 < 0) {
                zza = zzcv.zza("%s (%s) must not be negative", "index", Integer.valueOf(r6));
            } else if (r7 < 0) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("negative size: ");
                sb.append(r7);
                throw new IllegalArgumentException(sb.toString());
            } else {
                zza = zzcv.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(r6), Integer.valueOf(r7));
            }
            throw new IndexOutOfBoundsException(zza);
        }
        return r6;
    }

    public static int zzd(int r2, int r3) {
        if (r2 < 0 || r2 > r3) {
            throw new IndexOutOfBoundsException(zza(r2, r3, "index"));
        }
        return r2;
    }

    private static String zza(int r4, int r5, @NullableDecl String str) {
        if (r4 < 0) {
            return zzcv.zza("%s (%s) must not be negative", str, Integer.valueOf(r4));
        }
        if (r5 >= 0) {
            return zzcv.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(r4), Integer.valueOf(r5));
        }
        StringBuilder sb = new StringBuilder(26);
        sb.append("negative size: ");
        sb.append(r5);
        throw new IllegalArgumentException(sb.toString());
    }

    public static void zza(int r2, int r3, int r4) {
        String zza;
        if (r2 < 0 || r3 < r2 || r3 > r4) {
            if (r2 < 0 || r2 > r4) {
                zza = zza(r2, r4, "start index");
            } else {
                zza = (r3 < 0 || r3 > r4) ? zza(r3, r4, "end index") : zzcv.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(r3), Integer.valueOf(r2));
            }
            throw new IndexOutOfBoundsException(zza);
        }
    }
}
