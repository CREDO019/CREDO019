package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes3.dex */
public final class zzbc {
    public static int zza(int r4) {
        boolean z;
        if (r4 != 0 && r4 != 1) {
            if (r4 != 2) {
                z = false;
                Preconditions.checkArgument(z, "granularity %d must be a Granularity.GRANULARITY_* constants", Integer.valueOf(r4));
                return r4;
            }
            r4 = 2;
        }
        z = true;
        Preconditions.checkArgument(z, "granularity %d must be a Granularity.GRANULARITY_* constants", Integer.valueOf(r4));
        return r4;
    }

    public static String zzb(int r1) {
        if (r1 != 0) {
            if (r1 != 1) {
                if (r1 == 2) {
                    return "GRANULARITY_FINE";
                }
                throw new IllegalArgumentException();
            }
            return "GRANULARITY_COARSE";
        }
        return "GRANULARITY_PERMISSION_LEVEL";
    }
}
