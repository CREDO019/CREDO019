package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaxt implements zzayk {
    final /* synthetic */ zzaxu zza;
    private final int zzb;

    public zzaxt(zzaxu zzaxuVar, int r2) {
        this.zza = zzaxuVar;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.ads.zzayk
    public final int zzb(zzast zzastVar, zzaun zzaunVar, boolean z) {
        return this.zza.zze(this.zzb, zzastVar, zzaunVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzayk
    public final void zzc() throws IOException {
        this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.ads.zzayk
    public final void zzd(long j) {
        this.zza.zzy(this.zzb, j);
    }

    @Override // com.google.android.gms.internal.ads.zzayk
    public final boolean zze() {
        return this.zza.zzA(this.zzb);
    }
}
