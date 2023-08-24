package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzakn implements zzajz {
    private final Map zza = new HashMap();
    private final zzajm zzb;
    private final BlockingQueue zzc;
    private final zzajr zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzakn(zzajm zzajmVar, BlockingQueue blockingQueue, zzajr zzajrVar, byte[] bArr) {
        this.zzd = zzajrVar;
        this.zzb = zzajmVar;
        this.zzc = blockingQueue;
    }

    @Override // com.google.android.gms.internal.ads.zzajz
    public final synchronized void zza(zzaka zzakaVar) {
        String zzj = zzakaVar.zzj();
        List list = (List) this.zza.remove(zzj);
        if (list == null || list.isEmpty()) {
            return;
        }
        if (zzakm.zzb) {
            zzakm.zzd("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(list.size()), zzj);
        }
        zzaka zzakaVar2 = (zzaka) list.remove(0);
        this.zza.put(zzj, list);
        zzakaVar2.zzu(this);
        try {
            this.zzc.put(zzakaVar2);
        } catch (InterruptedException e) {
            zzakm.zzb("Couldn't add request to queue. %s", e.toString());
            Thread.currentThread().interrupt();
            this.zzb.zzb();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzajz
    public final void zzb(zzaka zzakaVar, zzakg zzakgVar) {
        List<zzaka> list;
        zzajj zzajjVar = zzakgVar.zzb;
        if (zzajjVar == null || zzajjVar.zza(System.currentTimeMillis())) {
            zza(zzakaVar);
            return;
        }
        String zzj = zzakaVar.zzj();
        synchronized (this) {
            list = (List) this.zza.remove(zzj);
        }
        if (list != null) {
            if (zzakm.zzb) {
                zzakm.zzd("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(list.size()), zzj);
            }
            for (zzaka zzakaVar2 : list) {
                this.zzd.zzb(zzakaVar2, zzakgVar, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zzc(zzaka zzakaVar) {
        String zzj = zzakaVar.zzj();
        if (this.zza.containsKey(zzj)) {
            List list = (List) this.zza.get(zzj);
            if (list == null) {
                list = new ArrayList();
            }
            zzakaVar.zzm("waiting-for-response");
            list.add(zzakaVar);
            this.zza.put(zzj, list);
            if (zzakm.zzb) {
                zzakm.zza("Request for cacheKey=%s is in flight, putting on hold.", zzj);
            }
            return true;
        }
        this.zza.put(zzj, null);
        zzakaVar.zzu(this);
        if (zzakm.zzb) {
            zzakm.zza("new request, sending to network %s", zzj);
        }
        return false;
    }
}
