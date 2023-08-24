package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfwd implements Comparator {
    public static zzfwd zzb(Comparator comparator) {
        if (comparator instanceof zzfwd) {
            return (zzfwd) comparator;
        }
        return new zzfuh(comparator);
    }

    public static zzfwd zzc() {
        return zzfwb.zza;
    }

    @Override // java.util.Comparator
    public abstract int compare(Object obj, Object obj2);

    public zzfwd zza() {
        return new zzfwm(this);
    }
}
