package com.google.android.gms.internal.ads;

import java.util.UUID;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdnx implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzdnx(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        zzcgt zza = ((zzcpa) this.zza).zza();
        com.google.android.gms.ads.internal.zzt.zzq();
        return new zzbbi(UUID.randomUUID().toString(), zza, "native", new JSONObject(), false, true);
    }
}
