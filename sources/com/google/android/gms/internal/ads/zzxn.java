package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import com.google.android.exoplayer2.C1856C;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzxn implements Choreographer.FrameCallback, Handler.Callback {
    private static final zzxn zzb = new zzxn();
    public volatile long zza = C1856C.TIME_UNSET;
    private final Handler zzc;
    private final HandlerThread zzd;
    private Choreographer zze;
    private int zzf;

    private zzxn() {
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:FrameReleaseChoreographer");
        this.zzd = handlerThread;
        handlerThread.start();
        Handler zzC = zzel.zzC(handlerThread.getLooper(), this);
        this.zzc = zzC;
        zzC.sendEmptyMessage(0);
    }

    public static zzxn zza() {
        return zzb;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int r4 = message.what;
        if (r4 == 0) {
            try {
                this.zze = Choreographer.getInstance();
            } catch (RuntimeException e) {
                zzdu.zzb("VideoFrameReleaseHelper", "Vsync sampling disabled due to platform error", e);
            }
            return true;
        } else if (r4 == 1) {
            Choreographer choreographer = this.zze;
            if (choreographer != null) {
                int r1 = this.zzf + 1;
                this.zzf = r1;
                if (r1 == 1) {
                    choreographer.postFrameCallback(this);
                }
            }
            return true;
        } else if (r4 != 2) {
            return false;
        } else {
            Choreographer choreographer2 = this.zze;
            if (choreographer2 != null) {
                int r12 = this.zzf - 1;
                this.zzf = r12;
                if (r12 == 0) {
                    choreographer2.removeFrameCallback(this);
                    this.zza = C1856C.TIME_UNSET;
                }
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

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        this.zza = j;
        Choreographer choreographer = this.zze;
        Objects.requireNonNull(choreographer);
        choreographer.postFrameCallbackDelayed(this, 500L);
    }
}
