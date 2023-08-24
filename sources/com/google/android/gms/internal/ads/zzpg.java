package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpg extends IOException {
    public final int zza;

    public zzpg(Throwable th, int r2) {
        super(th);
        this.zza = PlaybackException.ERROR_CODE_DRM_SCHEME_UNSUPPORTED;
    }
}
