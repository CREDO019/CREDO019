package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zztf implements zztw {
    final /* synthetic */ zzti zza;
    private final int zzb;

    public zztf(zzti zztiVar, int r2) {
        this.zza = zztiVar;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final int zza(zzje zzjeVar, zzgg zzggVar, int r5) {
        return this.zza.zzg(this.zzb, zzjeVar, zzggVar, r5);
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final int zzb(long j) {
        return this.zza.zzi(this.zzb, j);
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final void zzd() throws IOException {
        this.zza.zzF(this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final boolean zze() {
        return this.zza.zzM(this.zzb);
    }
}
