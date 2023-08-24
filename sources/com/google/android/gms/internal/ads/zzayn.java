package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzayn extends zzatd {
    private static final Object zzb = new Object();
    private final long zzc;
    private final long zzd;

    public zzayn(long j, boolean z) {
        this.zzc = j;
        this.zzd = j;
    }

    @Override // com.google.android.gms.internal.ads.zzatd
    public final int zza(Object obj) {
        return zzb.equals(obj) ? 0 : -1;
    }

    @Override // com.google.android.gms.internal.ads.zzatd
    public final int zzb() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzatd
    public final int zzc() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzatd
    public final zzatb zzd(int r3, zzatb zzatbVar, boolean z) {
        zzazy.zza(r3, 0, 1);
        Object obj = z ? zzb : null;
        long j = this.zzc;
        zzatbVar.zza = obj;
        zzatbVar.zzb = obj;
        zzatbVar.zzc = j;
        return zzatbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzatd
    public final zzatc zze(int r1, zzatc zzatcVar, boolean z, long j) {
        zzazy.zza(r1, 0, 1);
        zzatcVar.zza = this.zzd;
        return zzatcVar;
    }
}
