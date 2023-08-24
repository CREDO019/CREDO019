package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdws implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;
    private final zzgve zzd;

    public zzdws(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3, zzgve zzgveVar4) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
        this.zzd = zzgveVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Set emptySet;
        final String zza = ((zzewv) this.zza).zza();
        Context zza2 = ((zzcoq) this.zzb).zza();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        Map zzb = ((zzguv) this.zzd).zzb();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdX)).booleanValue()) {
            zzbel zzbelVar = new zzbel(new zzber(zza2));
            zzbelVar.zzb(new zzbek() { // from class: com.google.android.gms.internal.ads.zzdwt
                @Override // com.google.android.gms.internal.ads.zzbek
                public final void zza(zzbga zzbgaVar) {
                    zzbgaVar.zzh(zza);
                }
            });
            emptySet = Collections.singleton(new zzdke(new zzdwv(zzbelVar, zzb), zzfyyVar));
        } else {
            emptySet = Collections.emptySet();
        }
        zzguz.zzb(emptySet);
        return emptySet;
    }
}
