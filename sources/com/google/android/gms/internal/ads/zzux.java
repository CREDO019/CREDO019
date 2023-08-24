package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzux implements Comparable {
    private final boolean zza;
    private final boolean zzb;

    public zzux(zzaf zzafVar, int r4) {
        this.zza = 1 == (zzafVar.zze & 1);
        this.zzb = zzvo.zzm(r4, false);
    }

    @Override // java.lang.Comparable
    /* renamed from: zza */
    public final int compareTo(zzux zzuxVar) {
        return zzfuk.zzj().zzd(this.zzb, zzuxVar.zzb).zzd(this.zza, zzuxVar.zza).zza();
    }
}
