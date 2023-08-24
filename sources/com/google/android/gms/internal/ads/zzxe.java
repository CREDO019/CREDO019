package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzxe extends HandlerThread implements Handler.Callback {
    private zzdj zza;
    private Handler zzb;
    private Error zzc;
    private RuntimeException zzd;
    private zzxg zze;

    public zzxe() {
        super("ExoPlayer:PlaceholderSurface");
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int r0 = message.what;
        try {
            if (r0 != 1) {
                if (r0 != 2) {
                    return true;
                }
                try {
                    zzdj zzdjVar = this.zza;
                    Objects.requireNonNull(zzdjVar);
                    zzdjVar.zzc();
                } finally {
                    try {
                        return true;
                    } finally {
                    }
                }
                return true;
            }
            try {
                int r5 = message.arg1;
                zzdj zzdjVar2 = this.zza;
                Objects.requireNonNull(zzdjVar2);
                zzdjVar2.zzb(r5);
                this.zze = new zzxg(this, this.zza.zza(), r5 != 0, null);
                synchronized (this) {
                    notify();
                }
            } catch (zzdk e) {
                zzdu.zza("PlaceholderSurface", "Failed to initialize placeholder surface", e);
                this.zzd = new IllegalStateException(e);
                synchronized (this) {
                    notify();
                }
            } catch (Error e2) {
                zzdu.zza("PlaceholderSurface", "Failed to initialize placeholder surface", e2);
                this.zzc = e2;
                synchronized (this) {
                    notify();
                }
            } catch (RuntimeException e3) {
                zzdu.zza("PlaceholderSurface", "Failed to initialize placeholder surface", e3);
                this.zzd = e3;
                synchronized (this) {
                    notify();
                }
            }
            return true;
        } catch (Throwable th) {
            synchronized (this) {
                notify();
                throw th;
            }
        }
    }

    public final zzxg zza(int r4) {
        boolean z;
        start();
        this.zzb = new Handler(getLooper(), this);
        this.zza = new zzdj(this.zzb, null);
        synchronized (this) {
            z = false;
            this.zzb.obtainMessage(1, r4, 0).sendToTarget();
            while (this.zze == null && this.zzd == null && this.zzc == null) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    z = true;
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        RuntimeException runtimeException = this.zzd;
        if (runtimeException == null) {
            Error error = this.zzc;
            if (error == null) {
                zzxg zzxgVar = this.zze;
                Objects.requireNonNull(zzxgVar);
                return zzxgVar;
            }
            throw error;
        }
        throw runtimeException;
    }

    public final void zzb() {
        Handler handler = this.zzb;
        Objects.requireNonNull(handler);
        handler.sendEmptyMessage(2);
    }
}
