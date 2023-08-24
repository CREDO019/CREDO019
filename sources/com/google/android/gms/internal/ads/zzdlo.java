package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdlo implements zzgur {
    private final zzdli zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;

    public zzdlo(zzdli zzdliVar, zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4) {
        this.zza = zzdliVar;
        this.zzb = zzgveVar;
        this.zzc = zzgveVar2;
        this.zzd = zzgveVar3;
        this.zze = zzgveVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        final Context context = (Context) this.zzb.zzb();
        final zzcgt zza = ((zzcpa) this.zzc).zza();
        final zzfcs zza2 = ((zzczs) this.zzd).zza();
        final zzfdn zza3 = ((zzdcp) this.zze).zza();
        return new zzdke(new zzdem() { // from class: com.google.android.gms.internal.ads.zzdlh
            @Override // com.google.android.gms.internal.ads.zzdem
            public final void zzn() {
                com.google.android.gms.ads.internal.zzt.zzt().zzn(context, zza.zza, zza2.zzD.toString(), zza3.zzf);
            }
        }, zzcha.zzf);
    }
}
