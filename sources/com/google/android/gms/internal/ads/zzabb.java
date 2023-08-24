package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.avi.AviExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabb implements zzaav {
    public final int zza;
    public final int zzb;
    public final int zzc;
    public final int zzd;
    public final int zze;

    private zzabb(int r1, int r2, int r3, int r4, int r5, int r6) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
    }

    public static zzabb zzb(zzed zzedVar) {
        int zzg = zzedVar.zzg();
        zzedVar.zzG(12);
        int zzg2 = zzedVar.zzg();
        int zzg3 = zzedVar.zzg();
        int zzg4 = zzedVar.zzg();
        zzedVar.zzG(4);
        int zzg5 = zzedVar.zzg();
        int zzg6 = zzedVar.zzg();
        zzedVar.zzG(8);
        return new zzabb(zzg, zzg2, zzg3, zzg4, zzg5, zzg6);
    }

    @Override // com.google.android.gms.internal.ads.zzaav
    public final int zza() {
        return AviExtractor.FOURCC_strh;
    }
}
