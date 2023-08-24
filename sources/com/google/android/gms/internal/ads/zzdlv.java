package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdlv implements zzgur {
    private final zzdli zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzdlv(zzdli zzdliVar, zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzdliVar;
        this.zzb = zzgveVar;
        this.zzc = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzcea zzb() {
        return new zzcea(((zzcoq) this.zzb).zza(), ((zzdcp) this.zzc).zza().zzf);
    }
}
