package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzsa extends zzcn {
    private final zzbg zzc;

    public zzsa(zzbg zzbgVar) {
        this.zzc = zzbgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zza(Object obj) {
        return obj == zzrz.zzd ? 0 : -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzb() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzc() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final zzck zzd(int r12, zzck zzckVar, boolean z) {
        zzckVar.zzk(z ? 0 : null, z ? zzrz.zzd : null, 0, C1856C.TIME_UNSET, 0L, zzd.zza, true);
        return zzckVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final zzcm zze(int r22, zzcm zzcmVar, long j) {
        zzcmVar.zza(zzcm.zza, this.zzc, null, C1856C.TIME_UNSET, C1856C.TIME_UNSET, C1856C.TIME_UNSET, false, true, null, 0L, C1856C.TIME_UNSET, 0, 0, 0L);
        zzcmVar.zzl = true;
        return zzcmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final Object zzf(int r1) {
        return zzrz.zzd;
    }
}
