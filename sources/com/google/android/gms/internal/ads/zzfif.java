package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfif implements zzfhz {
    private final zzfij zza;
    private final zzfih zzb;
    private final zzfhw zzc;

    public zzfif(zzfhw zzfhwVar, zzfij zzfijVar, zzfih zzfihVar, byte[] bArr) {
        this.zzc = zzfhwVar;
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
        this.zzc.zzb(zza(zzfhyVar));
    }
}
