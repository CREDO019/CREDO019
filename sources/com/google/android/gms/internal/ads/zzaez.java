package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaez implements zzaex {
    private final int zza;
    private final int zzb;
    private final zzed zzc;

    public zzaez(zzaet zzaetVar, zzaf zzafVar) {
        zzed zzedVar = zzaetVar.zza;
        this.zzc = zzedVar;
        zzedVar.zzF(12);
        int zzn = zzedVar.zzn();
        if (MimeTypes.AUDIO_RAW.equals(zzafVar.zzm)) {
            int zzo = zzel.zzo(zzafVar.zzB, zzafVar.zzz);
            if (zzn == 0 || zzn % zzo != 0) {
                Log.w("AtomParsers", "Audio sample size mismatch. stsd sample size: " + zzo + ", stsz sample size: " + zzn);
                zzn = zzo;
            }
        }
        this.zza = zzn == 0 ? -1 : zzn;
        this.zzb = zzedVar.zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzaex
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzaex
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzaex
    public final int zzc() {
        int r0 = this.zza;
        return r0 == -1 ? this.zzc.zzn() : r0;
    }
}
