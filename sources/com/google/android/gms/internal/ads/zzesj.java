package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzesj implements zzeun {
    private final zzfyy zza;
    private final zzfcr zzb;

    public zzesj(zzfyy zzfyyVar, zzfcr zzfcrVar) {
        this.zza = zzfyyVar;
        this.zzb = zzfcrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 25;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzesi
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzesj.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzesk zzc() throws Exception {
        return new zzesk(this.zzb);
    }
}
