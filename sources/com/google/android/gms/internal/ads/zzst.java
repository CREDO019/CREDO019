package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzst implements zzvq {
    private final zzvq zza;
    private final zzcp zzb;

    public zzst(zzvq zzvqVar, zzcp zzcpVar) {
        this.zza = zzvqVar;
        this.zzb = zzcpVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzst) {
            zzst zzstVar = (zzst) obj;
            return this.zza.equals(zzstVar.zza) && this.zzb.equals(zzstVar.zzb);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzb.hashCode() + 527) * 31) + this.zza.hashCode();
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final int zza(int r2) {
        return this.zza.zza(0);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final int zzb(int r2) {
        return this.zza.zzb(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final int zzc() {
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final zzaf zzd(int r2) {
        return this.zza.zzd(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final zzcp zze() {
        return this.zzb;
    }
}
