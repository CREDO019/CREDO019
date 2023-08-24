package com.google.android.gms.internal.ads;

import android.media.metrics.LogSessionId;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzmz {
    public static final zzmz zza;
    private final zzmy zzb;

    static {
        zza = zzel.zza < 31 ? new zzmz() : new zzmz(zzmy.zza);
    }

    public zzmz() {
        this.zzb = null;
        zzdd.zzf(zzel.zza < 31);
    }

    private zzmz(zzmy zzmyVar) {
        this.zzb = zzmyVar;
    }

    public final LogSessionId zza() {
        zzmy zzmyVar = this.zzb;
        Objects.requireNonNull(zzmyVar);
        return zzmyVar.zzb;
    }

    public zzmz(LogSessionId logSessionId) {
        this.zzb = new zzmy(logSessionId);
    }
}
