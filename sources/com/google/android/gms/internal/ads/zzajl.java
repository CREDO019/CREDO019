package com.google.android.gms.internal.ads;

import java.util.concurrent.BlockingQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzajl implements Runnable {
    final /* synthetic */ zzaka zza;
    final /* synthetic */ zzajm zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzajl(zzajm zzajmVar, zzaka zzakaVar) {
        this.zzb = zzajmVar;
        this.zza = zzakaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BlockingQueue blockingQueue;
        try {
            blockingQueue = this.zzb.zzc;
            blockingQueue.put(this.zza);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }
}
