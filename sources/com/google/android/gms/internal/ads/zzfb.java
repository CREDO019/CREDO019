package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfb implements zzeu {
    private final Context zza;
    private final zzeu zzb;

    public zzfb(Context context) {
        zzfd zzfdVar = new zzfd();
        this.zza = context.getApplicationContext();
        this.zzb = zzfdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeu
    public final /* bridge */ /* synthetic */ zzev zza() {
        return new zzfc(this.zza, ((zzfd) this.zzb).zza());
    }
}
