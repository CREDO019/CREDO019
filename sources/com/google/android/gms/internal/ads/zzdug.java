package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdug implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzdug(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* synthetic */ Object zzb() {
        zzgve zzgveVar = this.zza;
        zzgve zzgveVar2 = this.zzb;
        int r2 = ((zzdcp) this.zzc).zza().zzo.zza;
        int r3 = r2 - 1;
        if (r2 != 0) {
            if (r3 != 0) {
                return ((zzell) zzgveVar2).zzb();
            }
            return ((zzell) zzgveVar).zzb();
        }
        throw null;
    }
}
