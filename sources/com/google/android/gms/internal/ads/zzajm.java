package com.google.android.gms.internal.ads;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzajm extends Thread {
    private static final boolean zza = zzakm.zzb;
    private final BlockingQueue zzb;
    private final BlockingQueue zzc;
    private final zzajk zzd;
    private volatile boolean zze = false;
    private final zzakn zzf;
    private final zzajr zzg;

    public zzajm(BlockingQueue blockingQueue, BlockingQueue blockingQueue2, zzajk zzajkVar, zzajr zzajrVar, byte[] bArr) {
        this.zzb = blockingQueue;
        this.zzc = blockingQueue2;
        this.zzd = zzajkVar;
        this.zzg = zzajrVar;
        this.zzf = new zzakn(this, blockingQueue2, zzajrVar, null);
    }

    private void zzc() throws InterruptedException {
        zzaka zzakaVar = (zzaka) this.zzb.take();
        zzakaVar.zzm("cache-queue-take");
        zzakaVar.zzt(1);
        try {
            zzakaVar.zzw();
            zzajj zza2 = this.zzd.zza(zzakaVar.zzj());
            if (zza2 == null) {
                zzakaVar.zzm("cache-miss");
                if (!this.zzf.zzc(zzakaVar)) {
                    this.zzc.put(zzakaVar);
                }
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (zza2.zza(currentTimeMillis)) {
                zzakaVar.zzm("cache-hit-expired");
                zzakaVar.zze(zza2);
                if (!this.zzf.zzc(zzakaVar)) {
                    this.zzc.put(zzakaVar);
                }
                return;
            }
            zzakaVar.zzm("cache-hit");
            zzakg zzh = zzakaVar.zzh(new zzajw(zza2.zza, zza2.zzg));
            zzakaVar.zzm("cache-hit-parsed");
            if (!zzh.zzc()) {
                zzakaVar.zzm("cache-parsing-failed");
                this.zzd.zzc(zzakaVar.zzj(), true);
                zzakaVar.zze(null);
                if (!this.zzf.zzc(zzakaVar)) {
                    this.zzc.put(zzakaVar);
                }
                return;
            }
            if (zza2.zzf < currentTimeMillis) {
                zzakaVar.zzm("cache-hit-refresh-needed");
                zzakaVar.zze(zza2);
                zzh.zzd = true;
                if (this.zzf.zzc(zzakaVar)) {
                    this.zzg.zzb(zzakaVar, zzh, null);
                } else {
                    this.zzg.zzb(zzakaVar, zzh, new zzajl(this, zzakaVar));
                }
            } else {
                this.zzg.zzb(zzakaVar, zzh, null);
            }
        } finally {
            zzakaVar.zzt(2);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (zza) {
            zzakm.zzd("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzd.zzb();
        while (true) {
            try {
                zzc();
            } catch (InterruptedException unused) {
                if (!this.zze) {
                    zzakm.zzb("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
                } else {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public final void zzb() {
        this.zze = true;
        interrupt();
    }
}
