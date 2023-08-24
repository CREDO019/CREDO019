package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzegl implements zzegk {
    public final zzegk zza;
    private final zzfru zzb;

    public zzegl(zzegk zzegkVar, zzfru zzfruVar) {
        this.zza = zzegkVar;
        this.zzb = zzfruVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(zzfde zzfdeVar, zzfcs zzfcsVar) {
        return zzfyo.zzm(this.zza.zza(zzfdeVar, zzfcsVar), this.zzb, zzcha.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        return this.zza.zzb(zzfdeVar, zzfcsVar);
    }
}
