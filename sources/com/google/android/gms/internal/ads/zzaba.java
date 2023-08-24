package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.avi.AviExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaba implements zzaav {
    public final int zza;
    public final int zzb;
    public final int zzc;

    private zzaba(int r1, int r2, int r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static zzaba zzb(zzed zzedVar) {
        int zzg = zzedVar.zzg();
        zzedVar.zzG(8);
        int zzg2 = zzedVar.zzg();
        int zzg3 = zzedVar.zzg();
        zzedVar.zzG(4);
        int zzg4 = zzedVar.zzg();
        zzedVar.zzG(12);
        return new zzaba(zzg, zzg2, zzg3, zzg4);
    }

    @Override // com.google.android.gms.internal.ads.zzaav
    public final int zza() {
        return AviExtractor.FOURCC_avih;
    }
}
