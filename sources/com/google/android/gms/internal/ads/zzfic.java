package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfic implements zzfhz {
    private final zzfhz zza;
    private final Queue zzb = new LinkedBlockingQueue();
    private final int zzc = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhq)).intValue();
    private final AtomicBoolean zzd = new AtomicBoolean(false);

    public zzfic(zzfhz zzfhzVar, ScheduledExecutorService scheduledExecutorService) {
        this.zza = zzfhzVar;
        long intValue = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhp)).intValue();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfib
            @Override // java.lang.Runnable
            public final void run() {
                zzfic.zzc(zzfic.this);
            }
        }, intValue, intValue, TimeUnit.MILLISECONDS);
    }

    public static /* synthetic */ void zzc(zzfic zzficVar) {
        while (!zzficVar.zzb.isEmpty()) {
            zzficVar.zza.zzb((zzfhy) zzficVar.zzb.remove());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhz
    public final String zza(zzfhy zzfhyVar) {
        return this.zza.zza(zzfhyVar);
    }

    @Override // com.google.android.gms.internal.ads.zzfhz
    public final void zzb(zzfhy zzfhyVar) {
        if (this.zzb.size() >= this.zzc) {
            if (this.zzd.getAndSet(true)) {
                return;
            }
            Queue queue = this.zzb;
            zzfhy zzb = zzfhy.zzb("dropped_event");
            Map zzj = zzfhyVar.zzj();
            if (zzj.containsKey("action")) {
                zzb.zza("dropped_action", (String) zzj.get("action"));
            }
            queue.offer(zzb);
            return;
        }
        this.zzb.offer(zzfhyVar);
    }
}
