package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfc {
    public static final zzgfc zza = new zzgfa().zza();
    private final Map zzb;

    public final boolean equals(Object obj) {
        if (obj instanceof zzgfc) {
            return this.zzb.equals(((zzgfc) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    public final String toString() {
        return this.zzb.toString();
    }
}
