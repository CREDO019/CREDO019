package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzert implements zzeun {
    private final zzfyy zza;
    private final zzfdn zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzert(zzfyy zzfyyVar, zzfdn zzfdnVar) {
        this.zza = zzfyyVar;
        this.zzb = zzfdnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 21;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzers
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzert.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeru zzc() throws Exception {
        return new zzeru("requester_type_2".equals(com.google.android.gms.ads.nonagon.signalgeneration.zzf.zzb(this.zzb.zzd)));
    }
}
