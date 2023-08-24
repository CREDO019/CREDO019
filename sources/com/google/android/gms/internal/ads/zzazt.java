package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzazt extends Handler implements Runnable {
    public final int zza;
    final /* synthetic */ zzazw zzb;
    private final zzazu zzc;
    private final zzazs zzd;
    private final long zze;
    private IOException zzf;
    private int zzg;
    private volatile Thread zzh;
    private volatile boolean zzi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzazt(zzazw zzazwVar, Looper looper, zzazu zzazuVar, zzazs zzazsVar, int r5, long j) {
        super(looper);
        this.zzb = zzazwVar;
        this.zzc = zzazuVar;
        this.zzd = zzazsVar;
        this.zza = r5;
        this.zze = j;
    }

    private final void zzd() {
        ExecutorService executorService;
        zzazt zzaztVar;
        this.zzf = null;
        zzazw zzazwVar = this.zzb;
        executorService = zzazwVar.zza;
        zzaztVar = zzazwVar.zzb;
        executorService.execute(zzaztVar);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (this.zzi) {
            return;
        }
        if (message.what == 0) {
            zzd();
        } else if (message.what != 4) {
            this.zzb.zzb = null;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = elapsedRealtime - this.zze;
            if (this.zzc.zze()) {
                this.zzd.zzt(this.zzc, elapsedRealtime, j, false);
                return;
            }
            int r0 = message.what;
            if (r0 == 1) {
                this.zzd.zzt(this.zzc, elapsedRealtime, j, false);
            } else if (r0 == 2) {
                this.zzd.zzu(this.zzc, elapsedRealtime, j);
            } else if (r0 != 3) {
            } else {
                IOException iOException = (IOException) message.obj;
                this.zzf = iOException;
                int zzd = this.zzd.zzd(this.zzc, elapsedRealtime, j, iOException);
                if (zzd == 3) {
                    this.zzb.zzc = this.zzf;
                } else if (zzd != 2) {
                    int r1 = zzd != 1 ? 1 + this.zzg : 1;
                    this.zzg = r1;
                    zzc(Math.min((r1 - 1) * 1000, 5000));
                }
            }
        } else {
            throw ((Error) message.obj);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzh = Thread.currentThread();
            if (!this.zzc.zze()) {
                String simpleName = this.zzc.getClass().getSimpleName();
                zzbal.zza("load:" + simpleName);
                try {
                    this.zzc.zzc();
                    zzbal.zzb();
                } catch (Throwable th) {
                    zzbal.zzb();
                    throw th;
                }
            }
            if (this.zzi) {
                return;
            }
            sendEmptyMessage(2);
        } catch (IOException e) {
            if (this.zzi) {
                return;
            }
            obtainMessage(3, e).sendToTarget();
        } catch (OutOfMemoryError e2) {
            Log.e("LoadTask", "OutOfMemory error loading stream", e2);
            if (this.zzi) {
                return;
            }
            obtainMessage(3, new zzazv(e2)).sendToTarget();
        } catch (Error e3) {
            Log.e("LoadTask", "Unexpected error loading stream", e3);
            if (!this.zzi) {
                obtainMessage(4, e3).sendToTarget();
            }
            throw e3;
        } catch (InterruptedException unused) {
            zzazy.zze(this.zzc.zze());
            if (this.zzi) {
                return;
            }
            sendEmptyMessage(2);
        } catch (Exception e4) {
            Log.e("LoadTask", "Unexpected exception loading stream", e4);
            if (this.zzi) {
                return;
            }
            obtainMessage(3, new zzazv(e4)).sendToTarget();
        }
    }

    public final void zza(boolean z) {
        this.zzi = z;
        this.zzf = null;
        if (hasMessages(0)) {
            removeMessages(0);
            if (!z) {
                sendEmptyMessage(1);
                return;
            }
        } else {
            this.zzc.zzb();
            if (this.zzh != null) {
                this.zzh.interrupt();
            }
            if (!z) {
                return;
            }
        }
        this.zzb.zzb = null;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.zzd.zzt(this.zzc, elapsedRealtime, elapsedRealtime - this.zze, true);
    }

    public final void zzb(int r3) throws IOException {
        IOException iOException = this.zzf;
        if (iOException != null && this.zzg > r3) {
            throw iOException;
        }
    }

    public final void zzc(long j) {
        zzazt zzaztVar;
        zzaztVar = this.zzb.zzb;
        zzazy.zze(zzaztVar == null);
        this.zzb.zzb = this;
        if (j > 0) {
            sendEmptyMessageDelayed(0, j);
        } else {
            zzd();
        }
    }
}
