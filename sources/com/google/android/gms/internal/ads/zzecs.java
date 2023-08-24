package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzecs implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;
    private final zzgve zzc;

    public zzecs(zzgve zzgveVar, zzgve zzgveVar2, zzgve zzgveVar3) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
        this.zzc = zzgveVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zza */
    public final zzecr zzb() {
        Map zzd = ((zzguy) this.zza).zzd();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        return new zzecr(zzd, zzfyyVar, ((zzdfs) this.zzc).zzb());
    }
}
