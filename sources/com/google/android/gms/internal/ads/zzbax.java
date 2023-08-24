package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbax implements Choreographer.FrameCallback, Handler.Callback {
    private static final zzbax zzb = new zzbax();
    public volatile long zza;
    private final Handler zzc;
    private final HandlerThread zzd;
    private Choreographer zze;
    private int zzf;

    private zzbax() {
        HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
        this.zzd = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), this);
        this.zzc = handler;
        handler.sendEmptyMessage(0);
    }

    public static zzbax zza() {
        return zzb;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        this.zza = j;
        this.zze.postFrameCallbackDelayed(this, 500L);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int r4 = message.what;
        if (r4 == 0) {
            this.zze = Choreographer.getInstance();
            return true;
        } else if (r4 == 1) {
            int r42 = this.zzf + 1;
            this.zzf = r42;
            if (r42 == 1) {
                this.zze.postFrameCallback(this);
            }
            return true;
        } else if (r4 != 2) {
            return false;
        } else {
            int r43 = this.zzf - 1;
            this.zzf = r43;
            if (r43 == 0) {
                this.zze.removeFrameCallback(this);
                this.zza = 0L;
            }
            return true;
        }
    }

    public final void zzb() {
        this.zzc.sendEmptyMessage(1);
    }

    public final void zzc() {
        this.zzc.sendEmptyMessage(2);
    }
}
