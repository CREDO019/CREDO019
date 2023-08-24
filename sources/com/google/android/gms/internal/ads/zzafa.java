package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafa implements zzaex {
    private final zzed zza;
    private final int zzb;
    private final int zzc;
    private int zzd;
    private int zze;

    public zzafa(zzaet zzaetVar) {
        zzed zzedVar = zzaetVar.zza;
        this.zza = zzedVar;
        zzedVar.zzF(12);
        this.zzc = zzedVar.zzn() & 255;
        this.zzb = zzedVar.zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzaex
    public final int zza() {
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzaex
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzaex
    public final int zzc() {
        int r0 = this.zzc;
        if (r0 == 8) {
            return this.zza.zzk();
        }
        if (r0 == 16) {
            return this.zza.zzo();
        }
        int r02 = this.zzd;
        this.zzd = r02 + 1;
        if (r02 % 2 == 0) {
            int zzk = this.zza.zzk();
            this.zze = zzk;
            return (zzk & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
        return this.zze & 15;
    }
}
