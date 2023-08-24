package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdwd implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzdwd(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Set emptySet;
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        zzdwv zzb = ((zzdww) this.zzb).zzb();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdX)).booleanValue()) {
            emptySet = Collections.singleton(new zzdke(zzb, zzfyyVar));
        } else {
            emptySet = Collections.emptySet();
        }
        zzguz.zzb(emptySet);
        return emptySet;
    }
}
