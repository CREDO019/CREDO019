package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgkm {
    private static final zzgoq zza = new zzgkl();

    public static int zza(int r1) {
        if (r1 != 1) {
            return r1 - 2;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static int zzb(int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 != 2) {
                    if (r3 != 3) {
                        return r3 != 4 ? 0 : 6;
                    }
                    return 5;
                }
                return 4;
            }
            return 3;
        }
        return 2;
    }
}
