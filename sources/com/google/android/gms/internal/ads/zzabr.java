package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzabr {
    protected final zzaam zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzabr(zzaam zzaamVar) {
        this.zza = zzaamVar;
    }

    protected abstract boolean zza(zzed zzedVar) throws zzbu;

    protected abstract boolean zzb(zzed zzedVar, long j) throws zzbu;

    public final boolean zzf(zzed zzedVar, long j) throws zzbu {
        return zza(zzedVar) && zzb(zzedVar, j);
    }
}
