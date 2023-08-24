package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzey {
    private Uri zza;
    private final int zzb = 1;
    private Map zzc = Collections.emptyMap();
    private long zzd;
    private int zze;

    public final zzey zza(int r1) {
        this.zze = 6;
        return this;
    }

    public final zzey zzb(Map map) {
        this.zzc = map;
        return this;
    }

    public final zzey zzc(long j) {
        this.zzd = j;
        return this;
    }

    public final zzey zzd(Uri uri) {
        this.zza = uri;
        return this;
    }

    public final zzfa zze() {
        if (this.zza != null) {
            return new zzfa(this.zza, this.zzc, this.zzd, this.zze);
        }
        throw new IllegalStateException("The uri must be set.");
    }
}
