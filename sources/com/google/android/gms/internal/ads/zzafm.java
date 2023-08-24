package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafm {
    public final zzafs zza;
    public final zzafv zzb;
    public final zzaam zzc;
    public final zzaan zzd;
    public int zze;

    public zzafm(zzafs zzafsVar, zzafv zzafvVar, zzaam zzaamVar) {
        this.zza = zzafsVar;
        this.zzb = zzafvVar;
        this.zzc = zzaamVar;
        this.zzd = MimeTypes.AUDIO_TRUEHD.equals(zzafsVar.zzf.zzm) ? new zzaan() : null;
    }
}
