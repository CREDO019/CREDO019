package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfkq {
    private final zzflx zza;
    private final String zzb;
    private final zzfkf zzc;
    private final String zzd = "Ad overlay";

    public zzfkq(View view, zzfkf zzfkfVar, String str) {
        this.zza = new zzflx(view);
        this.zzb = view.getClass().getCanonicalName();
        this.zzc = zzfkfVar;
    }

    public final zzfkf zza() {
        return this.zzc;
    }

    public final zzflx zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzd;
    }

    public final String zzd() {
        return this.zzb;
    }
}
