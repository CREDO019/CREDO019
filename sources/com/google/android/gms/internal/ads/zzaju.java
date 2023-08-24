package com.google.android.gms.internal.ads;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaju extends Thread {
    private final BlockingQueue zza;
    private final zzajt zzb;
    private final zzajk zzc;
    private volatile boolean zzd = false;
    private final zzajr zze;

    public zzaju(BlockingQueue blockingQueue, zzajt zzajtVar, zzajk zzajkVar, zzajr zzajrVar, byte[] bArr) {
        this.zza = blockingQueue;
        this.zzb = zzajtVar;
        this.zzc = zzajkVar;
        this.zze = zzajrVar;
    }

    private void zzb() throws InterruptedException {
        zzaka zzakaVar = (zzaka) this.zza.take();
        SystemClock.elapsedRealtime();
        zzakaVar.zzt(3);
        try {
            zzakaVar.zzm("network-queue-take");
            zzakaVar.zzw();
            TrafficStats.setThreadStatsTag(zzakaVar.zzc());
            zzajw zza = this.zzb.zza(zzakaVar);
            zzakaVar.zzm("network-http-complete");
            if (zza.zze && zzakaVar.zzv()) {
                zzakaVar.zzp("not-modified");
                zzakaVar.zzr();
                return;
            }
            zzakg zzh = zzakaVar.zzh(zza);
            zzakaVar.zzm("network-parse-complete");
            if (zzh.zzb != null) {
                this.zzc.zzd(zzakaVar.zzj(), zzh.zzb);
                zzakaVar.zzm("network-cache-written");
            }
            zzakaVar.zzq();
            this.zze.zzb(zzakaVar, zzh, null);
            zzakaVar.zzs(zzh);
        } catch (zzakj e) {
            SystemClock.elapsedRealtime();
            this.zze.zza(zzakaVar, e);
            zzakaVar.zzr();
        } catch (Exception e2) {
            zzakm.zzc(e2, "Unhandled exception %s", e2.toString());
            zzakj zzakjVar = new zzakj(e2);
            SystemClock.elapsedRealtime();
            this.zze.zza(zzakaVar, zzakjVar);
            zzakaVar.zzr();
        } finally {
            zzakaVar.zzt(4);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                zzb();
            } catch (InterruptedException unused) {
                if (!this.zzd) {
                    zzakm.zzb("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
                } else {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public final void zza() {
        this.zzd = true;
        interrupt();
    }
}
