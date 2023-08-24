package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzguq implements zzgve, zzgul {
    private static final Object zza = new Object();
    private volatile zzgve zzb;
    private volatile Object zzc = zza;

    private zzguq(zzgve zzgveVar) {
        this.zzb = zzgveVar;
    }

    public static zzgul zza(zzgve zzgveVar) {
        if (zzgveVar instanceof zzgul) {
            return (zzgul) zzgveVar;
        }
        Objects.requireNonNull(zzgveVar);
        return new zzguq(zzgveVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final Object zzb() {
        Object obj = this.zzc;
        Object obj2 = zza;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.zzc;
                if (obj == obj2) {
                    obj = this.zzb.zzb();
                    Object obj3 = this.zzc;
                    if (obj3 != obj2 && obj3 != obj) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                    }
                    this.zzc = obj;
                    this.zzb = null;
                }
            }
        }
        return obj;
    }

    public static zzgve zzc(zzgve zzgveVar) {
        Objects.requireNonNull(zzgveVar);
        return zzgveVar instanceof zzguq ? zzgveVar : new zzguq(zzgveVar);
    }
}
