package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcdu {
    private final Clock zza;
    private final zzcds zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcdu(Clock clock, zzcds zzcdsVar) {
        this.zza = clock;
        this.zzb = zzcdsVar;
    }

    public static zzcdu zza(Context context) {
        return zzcet.zzd(context).zzb();
    }

    public final void zzb(int r2, long j) {
        this.zzb.zzb(r2, j);
    }

    public final void zzc() {
        this.zzb.zza();
    }

    public final void zzd(com.google.android.gms.ads.internal.client.zzez zzezVar) {
        this.zzb.zzb(-1, this.zza.currentTimeMillis());
    }

    public final void zze() {
        this.zzb.zzb(-1, this.zza.currentTimeMillis());
    }
}
