package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzsv implements zztw {
    private final zztw zza;
    private final long zzb;

    public zzsv(zztw zztwVar, long j) {
        this.zza = zztwVar;
        this.zzb = j;
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final int zza(zzje zzjeVar, zzgg zzggVar, int r9) {
        int zza = this.zza.zza(zzjeVar, zzggVar, r9);
        if (zza == -4) {
            zzggVar.zzd = Math.max(0L, zzggVar.zzd + this.zzb);
            return -4;
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final int zzb(long j) {
        return this.zza.zzb(j - this.zzb);
    }

    public final zztw zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final void zzd() throws IOException {
        this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final boolean zze() {
        return this.zza.zze();
    }
}
