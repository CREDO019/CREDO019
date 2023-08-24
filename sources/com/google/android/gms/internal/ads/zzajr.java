package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzajr {
    private final Executor zza;

    public zzajr(Handler handler) {
        this.zza = new zzajp(this, handler);
    }

    public final void zza(zzaka zzakaVar, zzakj zzakjVar) {
        zzakaVar.zzm("post-error");
        zzakg zza = zzakg.zza(zzakjVar);
        Executor executor = this.zza;
        ((zzajp) executor).zza.post(new zzajq(zzakaVar, zza, null));
    }

    public final void zzb(zzaka zzakaVar, zzakg zzakgVar, Runnable runnable) {
        zzakaVar.zzq();
        zzakaVar.zzm("post-response");
        Executor executor = this.zza;
        ((zzajp) executor).zza.post(new zzajq(zzakaVar, zzakgVar, runnable));
    }
}
