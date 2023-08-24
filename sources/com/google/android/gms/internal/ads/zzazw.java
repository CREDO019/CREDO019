package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.SystemClock;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzazw {
    private final ExecutorService zza = zzban.zzl("Loader:ExtractorMediaPeriod");
    private zzazt zzb;
    private IOException zzc;

    public zzazw(String str) {
    }

    public final long zza(zzazu zzazuVar, zzazs zzazsVar, int r14) {
        Looper myLooper = Looper.myLooper();
        zzazy.zze(myLooper != null);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        new zzazt(this, myLooper, zzazuVar, zzazsVar, r14, elapsedRealtime).zzc(0L);
        return elapsedRealtime;
    }

    public final void zzf() {
        this.zzb.zza(false);
    }

    public final void zzg(int r2) throws IOException {
        IOException iOException = this.zzc;
        if (iOException != null) {
            throw iOException;
        }
        zzazt zzaztVar = this.zzb;
        if (zzaztVar != null) {
            zzaztVar.zzb(zzaztVar.zza);
        }
    }

    public final void zzh(Runnable runnable) {
        zzazt zzaztVar = this.zzb;
        if (zzaztVar != null) {
            zzaztVar.zza(true);
        }
        this.zza.execute(runnable);
        this.zza.shutdown();
    }

    public final boolean zzi() {
        return this.zzb != null;
    }
}
