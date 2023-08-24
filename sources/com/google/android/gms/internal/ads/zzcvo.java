package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcvo implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzcvo(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        Set singleton;
        zzcvi zzcviVar = (zzcvi) this.zza.zzb();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        if (((JSONObject) this.zzc.zzb()) == null) {
            singleton = Collections.emptySet();
        } else {
            singleton = Collections.singleton(new zzdke(zzcviVar, zzfyyVar));
        }
        zzguz.zzb(singleton);
        return singleton;
    }
}
