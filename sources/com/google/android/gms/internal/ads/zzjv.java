package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.SystemClock;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzjv {
    private final zzju zza;
    private final zzjt zzb;
    private final zzde zzc;
    private final zzcn zzd;
    private int zze;
    private Object zzf;
    private final Looper zzg;
    private final int zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;

    public zzjv(zzjt zzjtVar, zzju zzjuVar, zzcn zzcnVar, int r4, zzde zzdeVar, Looper looper) {
        this.zzb = zzjtVar;
        this.zza = zzjuVar;
        this.zzd = zzcnVar;
        this.zzg = looper;
        this.zzc = zzdeVar;
        this.zzh = r4;
    }

    public final int zza() {
        return this.zze;
    }

    public final Looper zzb() {
        return this.zzg;
    }

    public final zzju zzc() {
        return this.zza;
    }

    public final zzjv zzd() {
        zzdd.zzf(!this.zzi);
        this.zzi = true;
        this.zzb.zzm(this);
        return this;
    }

    public final zzjv zze(Object obj) {
        zzdd.zzf(!this.zzi);
        this.zzf = obj;
        return this;
    }

    public final zzjv zzf(int r2) {
        zzdd.zzf(!this.zzi);
        this.zze = r2;
        return this;
    }

    public final Object zzg() {
        return this.zzf;
    }

    public final synchronized void zzh(boolean z) {
        this.zzj = z | this.zzj;
        this.zzk = true;
        notifyAll();
    }

    public final synchronized boolean zzi(long j) throws InterruptedException, TimeoutException {
        zzdd.zzf(this.zzi);
        zzdd.zzf(this.zzg.getThread() != Thread.currentThread());
        long elapsedRealtime = SystemClock.elapsedRealtime() + j;
        while (!this.zzk) {
            if (j > 0) {
                wait(j);
                j = elapsedRealtime - SystemClock.elapsedRealtime();
            } else {
                throw new TimeoutException("Message delivery timed out.");
            }
        }
        return this.zzj;
    }

    public final synchronized boolean zzj() {
        return false;
    }
}
