package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfym implements Runnable {
    final Future zza;
    final zzfyk zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfym(Future future, zzfyk zzfykVar) {
        this.zza = future;
        this.zzb = zzfykVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Throwable zza;
        Future future = this.zza;
        if (!(future instanceof zzfzq) || (zza = zzfzr.zza((zzfzq) future)) == null) {
            try {
                this.zzb.zzb(zzfyo.zzp(this.zza));
                return;
            } catch (Error e) {
                e = e;
                this.zzb.zza(e);
                return;
            } catch (RuntimeException e2) {
                e = e2;
                this.zzb.zza(e);
                return;
            } catch (ExecutionException e3) {
                this.zzb.zza(e3.getCause());
                return;
            }
        }
        this.zzb.zza(zza);
    }

    public final String toString() {
        zzfry zza = zzfrz.zza(this);
        zza.zza(this.zzb);
        return zza.toString();
    }
}
