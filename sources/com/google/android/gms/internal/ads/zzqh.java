package com.google.android.gms.internal.ads;

import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzqh {
    public final zzql zza;
    public final MediaFormat zzb;
    public final zzaf zzc;
    public final Surface zzd;
    public final MediaCrypto zze = null;

    private zzqh(zzql zzqlVar, MediaFormat mediaFormat, zzaf zzafVar, Surface surface, MediaCrypto mediaCrypto, int r6) {
        this.zza = zzqlVar;
        this.zzb = mediaFormat;
        this.zzc = zzafVar;
        this.zzd = surface;
    }

    public static zzqh zza(zzql zzqlVar, MediaFormat mediaFormat, zzaf zzafVar, MediaCrypto mediaCrypto) {
        return new zzqh(zzqlVar, mediaFormat, zzafVar, null, null, 0);
    }

    public static zzqh zzb(zzql zzqlVar, MediaFormat mediaFormat, zzaf zzafVar, Surface surface, MediaCrypto mediaCrypto) {
        return new zzqh(zzqlVar, mediaFormat, zzafVar, surface, null, 0);
    }
}
