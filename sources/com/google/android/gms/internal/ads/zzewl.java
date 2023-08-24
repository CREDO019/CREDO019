package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzewl implements zzeun {
    final zzfyy zza;
    final List zzb;
    final zzbij zzc;

    public zzewl(zzbij zzbijVar, zzfyy zzfyyVar, List list, byte[] bArr) {
        this.zzc = zzbijVar;
        this.zza = zzfyyVar;
        this.zzb = list;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 48;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzewk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzewm(zzewl.this.zzb);
            }
        });
    }
}
