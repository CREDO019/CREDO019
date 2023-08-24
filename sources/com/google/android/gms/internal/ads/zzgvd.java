package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgvd implements zzgve {
    private static final Object zza = new Object();
    private volatile zzgve zzb;
    private volatile Object zzc = zza;

    private zzgvd(zzgve zzgveVar) {
        this.zzb = zzgveVar;
    }

    public static zzgve zza(zzgve zzgveVar) {
        if ((zzgveVar instanceof zzgvd) || (zzgveVar instanceof zzguq)) {
            return zzgveVar;
        }
        Objects.requireNonNull(zzgveVar);
        return new zzgvd(zzgveVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final Object zzb() {
        Object obj = this.zzc;
        if (obj == zza) {
            zzgve zzgveVar = this.zzb;
            if (zzgveVar == null) {
                return this.zzc;
            }
            Object zzb = zzgveVar.zzb();
            this.zzc = zzb;
            this.zzb = null;
            return zzb;
        }
        return obj;
    }
}
