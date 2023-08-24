package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdol {
    private zzblx zza;

    public zzdol(zzdoa zzdoaVar) {
        this.zza = zzdoaVar;
    }

    public final synchronized zzblx zza() {
        return this.zza;
    }

    public final synchronized void zzb(zzblx zzblxVar) {
        this.zza = zzblxVar;
    }
}
