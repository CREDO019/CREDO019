package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzesb implements zzeun {
    private final zzfyy zza;
    private final zzeai zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzesb(zzfyy zzfyyVar, zzeai zzeaiVar) {
        this.zza = zzfyyVar;
        this.zzb = zzeaiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 23;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzesa
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzesb.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzesc zzc() throws Exception {
        return new zzesc(this.zzb.zzb(), this.zzb.zzn(), com.google.android.gms.ads.internal.zzt.zzt().zzl());
    }
}
