package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdbv implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzdbv(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        final Context context = (Context) this.zza.zzb();
        final zzcgt zza = ((zzcpa) this.zzb).zza();
        final zzfdn zza2 = ((zzdcp) this.zzc).zza();
        return new zzfru() { // from class: com.google.android.gms.internal.ads.zzdbu
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                Context context2 = context;
                zzcgt zzcgtVar = zza;
                zzfdn zzfdnVar = zza2;
                zzfcs zzfcsVar = (zzfcs) obj;
                com.google.android.gms.ads.internal.util.zzas zzasVar = new com.google.android.gms.ads.internal.util.zzas(context2);
                zzasVar.zzp(zzfcsVar.zzC);
                zzasVar.zzq(zzfcsVar.zzD.toString());
                zzasVar.zzo(zzcgtVar.zza);
                zzasVar.zzn(zzfdnVar.zzf);
                return zzasVar;
            }
        };
    }
}
