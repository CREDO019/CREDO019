package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzflt {
    private final BlockingQueue zza;
    private final ThreadPoolExecutor zzb;
    private final ArrayDeque zzc = new ArrayDeque();
    private zzfls zzd = null;

    public zzflt() {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        this.zza = linkedBlockingQueue;
        this.zzb = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS, linkedBlockingQueue);
    }

    private final void zzc() {
        zzfls zzflsVar = (zzfls) this.zzc.poll();
        this.zzd = zzflsVar;
        if (zzflsVar != null) {
            zzflsVar.executeOnExecutor(this.zzb, new Object[0]);
        }
    }

    public final void zza(zzfls zzflsVar) {
        this.zzd = null;
        zzc();
    }

    public final void zzb(zzfls zzflsVar) {
        zzflsVar.zzb(this);
        this.zzc.add(zzflsVar);
        if (this.zzd == null) {
            zzc();
        }
    }
}
