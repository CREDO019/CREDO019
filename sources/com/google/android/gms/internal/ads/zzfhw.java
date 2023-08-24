package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfhw {
    private final Executor zza;
    private final zzcgs zzb;

    public zzfhw(Executor executor, zzcgs zzcgsVar) {
        this.zza = executor;
        this.zzb = zzcgsVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str) {
        this.zzb.zza(str);
    }

    public final void zzb(final String str) {
        this.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfhv
            @Override // java.lang.Runnable
            public final void run() {
                zzfhw.this.zza(str);
            }
        });
    }
}
