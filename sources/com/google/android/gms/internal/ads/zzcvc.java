package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcvc implements zzbpq {
    final /* synthetic */ zzcvd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcvc(zzcvd zzcvdVar) {
        this.zza = zzcvdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbpq
    public final void zza(Object obj, Map map) {
        Executor executor;
        if (zzcvd.zzg(this.zza, map)) {
            executor = this.zza.zzc;
            executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcvb
                @Override // java.lang.Runnable
                public final void run() {
                    zzcvi zzcviVar;
                    zzcviVar = zzcvc.this.zza.zzd;
                    zzcviVar.zzj();
                }
            });
        }
    }
}
