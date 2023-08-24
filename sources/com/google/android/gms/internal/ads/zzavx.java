package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzavx implements zzavu {
    private final zzbag zza;
    private final int zzb;
    private final int zzc;
    private int zzd;
    private int zze;

    public zzavx(zzavr zzavrVar) {
        zzbag zzbagVar = zzavrVar.zza;
        this.zza = zzbagVar;
        zzbagVar.zzv(12);
        this.zzc = zzbagVar.zzi() & 255;
        this.zzb = zzbagVar.zzi();
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final int zzb() {
        int r0 = this.zzc;
        if (r0 == 8) {
            return this.zza.zzg();
        }
        if (r0 == 16) {
            return this.zza.zzj();
        }
        int r02 = this.zzd;
        this.zzd = r02 + 1;
        if (r02 % 2 == 0) {
            int zzg = this.zza.zzg();
            this.zze = zzg;
            return (zzg & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
        return this.zze & 15;
    }

    @Override // com.google.android.gms.internal.ads.zzavu
    public final boolean zzc() {
        return false;
    }
}
