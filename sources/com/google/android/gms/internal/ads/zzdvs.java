package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdvs implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;
    private final zzgve zze;

    public zzdvs(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4, zzgve zzgveVar5) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
        this.zze = zzgveVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Context zza = ((zzcoq) this.zza).zza();
        final String zzb = ((zzebk) this.zzb).zzb();
        zzcgt zza2 = ((zzcpa) this.zzc).zza();
        final zzbev zzbevVar = (zzbev) this.zzd.zzb();
        final String str = (String) this.zze.zzb();
        zzbel zzbelVar = new zzbel(new zzber(zza));
        zzbhk zza3 = zzbhl.zza();
        zza3.zza(zza2.zzb);
        zza3.zzc(zza2.zzc);
        zza3.zzb(true != zza2.zzd ? 2 : 0);
        final zzbhl zzbhlVar = (zzbhl) zza3.zzal();
        zzbelVar.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzdvr
            @Override // com.google.android.gms.internal.ads.zzbek
            public final void zza(zzbga zzbgaVar) {
                zzbev zzbevVar2 = zzbev.this;
                String str2 = zzb;
                zzbhl zzbhlVar2 = zzbhlVar;
                String str3 = str;
                zzbew zzbewVar = (zzbew) zzbgaVar.zza().zzaz();
                zzbewVar.zza(zzbevVar2);
                zzbgaVar.zze(zzbewVar);
                zzbfs zzbfsVar = (zzbfs) zzbgaVar.zzb().zzaz();
                zzbfsVar.zza(str2);
                zzbfsVar.zzb(zzbhlVar2);
                zzbgaVar.zzg(zzbfsVar);
                zzbgaVar.zzh(str3);
            }
        });
        return zzbelVar;
    }
}
