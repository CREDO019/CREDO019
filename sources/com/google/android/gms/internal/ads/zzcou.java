package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcou implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzcou(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Set emptySet;
        zzeeh zzeehVar = (zzeeh) this.zza.zzb();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbL)).booleanValue()) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhn)).booleanValue()) {
                emptySet = Collections.singleton(new zzdke(zzeehVar, zzfyyVar));
                zzguz.zzb(emptySet);
                return emptySet;
            }
        }
        emptySet = Collections.emptySet();
        zzguz.zzb(emptySet);
        return emptySet;
    }
}
