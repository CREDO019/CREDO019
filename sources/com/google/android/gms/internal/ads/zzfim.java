package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfim implements zzfhz {
    private final zzfij zza;
    private final zzfih zzb;

    public zzfim(zzfij zzfijVar, zzfih zzfihVar) {
        this.zza = zzfijVar;
        this.zzb = zzfihVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfhz
    public final String zza(zzfhy zzfhyVar) {
        zzfij zzfijVar = this.zza;
        Map zzj = zzfhyVar.zzj();
        this.zzb.zza(zzj);
        return zzfijVar.zza(zzj);
    }

    @Override // com.google.android.gms.internal.ads.zzfhz
    public final void zzb(zzfhy zzfhyVar) {
    }
}
