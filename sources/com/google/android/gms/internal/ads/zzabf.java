package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.avi.AviExtractor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabf implements zzaav {
    public final String zza;

    private zzabf(String str) {
        this.zza = str;
    }

    public static zzabf zzb(zzed zzedVar) {
        return new zzabf(zzedVar.zzx(zzedVar.zza(), zzfrs.zzc));
    }

    @Override // com.google.android.gms.internal.ads.zzaav
    public final int zza() {
        return AviExtractor.FOURCC_strn;
    }
}
