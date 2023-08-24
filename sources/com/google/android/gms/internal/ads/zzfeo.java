package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfeo implements zzgur {
    private final zzfen zza;

    public zzfeo(zzfen zzfenVar) {
        this.zza = zzfenVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* synthetic */ Object zzb() {
        Clock defaultClock = DefaultClock.getInstance();
        zzguz.zzb(defaultClock);
        return defaultClock;
    }
}
