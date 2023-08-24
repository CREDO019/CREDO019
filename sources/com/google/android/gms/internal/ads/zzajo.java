package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.DefaultLoadControl;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzajo {
    private int zza = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS;
    private int zzb;

    public final int zza() {
        return this.zzb;
    }

    public final int zzb() {
        return this.zza;
    }

    public final void zzc(zzakj zzakjVar) throws zzakj {
        int r0 = this.zzb + 1;
        this.zzb = r0;
        int r2 = this.zza;
        this.zza = r2 + r2;
        if (r0 > 1) {
            throw zzakjVar;
        }
    }
}
