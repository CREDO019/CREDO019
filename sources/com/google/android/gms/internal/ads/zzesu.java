package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzesu implements zzeun {
    private final zzfyy zza;
    private final Context zzb;
    private final Set zzc;

    public zzesu(zzfyy zzfyyVar, Context context, Set set) {
        this.zza = zzfyyVar;
        this.zzb = context;
        this.zzc = set;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 27;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzest
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzesu.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzesv zzc() throws Exception {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue()) {
            Set set = this.zzc;
            if (set.contains("rewarded") || set.contains("interstitial") || set.contains("native") || set.contains("banner")) {
                com.google.android.gms.ads.internal.zzt.zzh();
                return new zzesv(true == ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzed)).booleanValue() ? "a.1.3.31-google_20220407" : null);
            }
        }
        return new zzesv(null);
    }
}
