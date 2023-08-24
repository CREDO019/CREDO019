package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzwo extends Handler implements Runnable {
    final /* synthetic */ zzwt zza;
    private final zzwp zzb;
    private final long zzc;
    private zzwl zzd;
    private IOException zze;
    private int zzf;
    private Thread zzg;
    private boolean zzh;
    private volatile boolean zzi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzwo(zzwt zzwtVar, Looper looper, zzwp zzwpVar, zzwl zzwlVar, int r5, long j) {
        super(looper);
        this.zza = zzwtVar;
        this.zzb = zzwpVar;
        this.zzd = zzwlVar;
        this.zzc = j;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int r0;
        int r02;
        int r03;
        long j;
        if (this.zzi) {
            return;
        }
        if (message.what == 0) {
            zzd();
        } else if (message.what != 3) {
            this.zza.zzf = null;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j2 = elapsedRealtime - this.zzc;
            zzwl zzwlVar = this.zzd;
            Objects.requireNonNull(zzwlVar);
            if (this.zzh) {
                zzwlVar.zzG(this.zzb, elapsedRealtime, j2, false);
                return;
            }
            int r04 = message.what;
            if (r04 == 1) {
                try {
                    zzwlVar.zzH(this.zzb, elapsedRealtime, j2);
                } catch (RuntimeException e) {
                    zzdu.zza("LoadTask", "Unexpected exception handling load completed", e);
                    this.zza.zzg = new zzws(e);
                }
            } else if (r04 != 2) {
            } else {
                IOException iOException = (IOException) message.obj;
                this.zze = iOException;
                int r10 = this.zzf + 1;
                this.zzf = r10;
                zzwn zzt = zzwlVar.zzt(this.zzb, elapsedRealtime, j2, iOException, r10);
                r0 = zzt.zza;
                if (r0 == 3) {
                    this.zza.zzg = this.zze;
                    return;
                }
                r02 = zzt.zza;
                if (r02 != 2) {
                    r03 = zzt.zza;
                    if (r03 == 1) {
                        this.zzf = 1;
                    }
                    j = zzt.zzb;
                    zzc(j != C1856C.TIME_UNSET ? zzt.zzb : Math.min((this.zzf - 1) * 1000, 5000));
                }
            }
        } else {
            throw ((Error) message.obj);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        try {
            synchronized (this) {
                z = !this.zzh;
                this.zzg = Thread.currentThread();
            }
            if (z) {
                int r3 = zzel.zza;
                Trace.beginSection("load:" + this.zzb.getClass().getSimpleName());
                try {
                    this.zzb.zzi();
                    Trace.endSection();
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            }
            synchronized (this) {
                this.zzg = null;
                Thread.interrupted();
            }
            if (this.zzi) {
                return;
            }
            sendEmptyMessage(1);
        } catch (IOException e) {
            if (this.zzi) {
                return;
            }
            obtainMessage(2, e).sendToTarget();
        } catch (Exception e2) {
            if (this.zzi) {
                return;
            }
            zzdu.zza("LoadTask", "Unexpected exception loading stream", e2);
            obtainMessage(2, new zzws(e2)).sendToTarget();
        } catch (OutOfMemoryError e3) {
            if (this.zzi) {
                return;
            }
            zzdu.zza("LoadTask", "OutOfMemory error loading stream", e3);
            obtainMessage(2, new zzws(e3)).sendToTarget();
        } catch (Error e4) {
            if (!this.zzi) {
                zzdu.zza("LoadTask", "Unexpected error loading stream", e4);
                obtainMessage(3, e4).sendToTarget();
            }
            throw e4;
        }
    }

    public final void zza(boolean z) {
        this.zzi = z;
        this.zze = null;
        if (hasMessages(0)) {
            this.zzh = true;
            removeMessages(0);
            if (!z) {
                sendEmptyMessage(1);
            }
        } else {
            synchronized (this) {
                this.zzh = true;
                this.zzb.zzh();
                Thread thread = this.zzg;
                if (thread != null) {
                    thread.interrupt();
                }
            }
        }
        if (z) {
            this.zza.zzf = null;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzwl zzwlVar = this.zzd;
            Objects.requireNonNull(zzwlVar);
            zzwlVar.zzG(this.zzb, elapsedRealtime, elapsedRealtime - this.zzc, true);
            this.zzd = null;
        }
    }

    public final void zzb(int r3) throws IOException {
        IOException iOException = this.zze;
        if (iOException != null && this.zzf > r3) {
            throw iOException;
        }
    }

    public final void zzc(long j) {
        zzwo zzwoVar;
        zzwoVar = this.zza.zzf;
        zzdd.zzf(zzwoVar == null);
        this.zza.zzf = this;
        if (j > 0) {
            sendEmptyMessageDelayed(0, j);
        } else {
            zzd();
        }
    }

    private final void zzd() {
        ExecutorService executorService;
        zzwo zzwoVar;
        this.zze = null;
        zzwt zzwtVar = this.zza;
        executorService = zzwtVar.zze;
        zzwoVar = zzwtVar.zzf;
        Objects.requireNonNull(zzwoVar);
        executorService.execute(zzwoVar);
    }
}
