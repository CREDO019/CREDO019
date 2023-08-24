package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcta implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzcta(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* synthetic */ Object zzb() {
        zzbuu zzbuuVar = new zzbuu();
        String str = (String) this.zzb.zzb();
        return ((Boolean) zzbkp.zza.zze()).booleanValue() ? new zzbut(zzbuuVar, str) : new zzcgs(str);
    }
}
