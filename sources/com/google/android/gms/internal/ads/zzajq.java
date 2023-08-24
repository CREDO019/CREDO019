package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzajq implements Runnable {
    private final zzaka zza;
    private final zzakg zzb;
    private final Runnable zzc;

    public zzajq(zzaka zzakaVar, zzakg zzakgVar, Runnable runnable) {
        this.zza = zzakaVar;
        this.zzb = zzakgVar;
        this.zzc = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzw();
        zzakg zzakgVar = this.zzb;
        if (zzakgVar.zzc()) {
            this.zza.zzo(zzakgVar.zza);
        } else {
            this.zza.zzn(zzakgVar.zzc);
        }
        if (this.zzb.zzd) {
            this.zza.zzm("intermediate-response");
        } else {
            this.zza.zzp("done");
        }
        Runnable runnable = this.zzc;
        if (runnable != null) {
            runnable.run();
        }
    }
}
