package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzetc implements zzeun {
    private final zzfyy zza;
    private final Bundle zzb;

    public zzetc(zzfyy zzfyyVar, Bundle bundle) {
        this.zza = zzfyyVar;
        this.zzb = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 30;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzetb
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzetc.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzetd zzc() throws Exception {
        return new zzetd(this.zzb);
    }
}
