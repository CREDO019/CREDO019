package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzerp implements zzeun {
    private final zzfyy zza;

    public zzerp(zzfyy zzfyyVar) {
        this.zza = zzfyyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 20;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzero
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzerq(com.google.android.gms.ads.internal.zzt.zzt().zzb(), com.google.android.gms.ads.internal.zzt.zzt().zzm());
            }
        });
    }
}
