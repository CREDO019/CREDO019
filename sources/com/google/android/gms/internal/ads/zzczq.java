package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzczq {
    private final Executor zza;
    private final ScheduledExecutorService zzb;
    private final zzfyx zzc;
    private volatile boolean zzd = true;

    public zzczq(Executor executor, ScheduledExecutorService scheduledExecutorService, zzfyx zzfyxVar) {
        this.zza = executor;
        this.zzb = scheduledExecutorService;
        this.zzc = zzfyxVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzb(final zzczq zzczqVar, List list, final zzfyk zzfykVar) {
        if (list == null || list.isEmpty()) {
            zzczqVar.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzczk
                @Override // java.lang.Runnable
                public final void run() {
                    zzfyk.this.zza(new zzeas(3));
                }
            });
            return;
        }
        zzfyx zzi = zzfyo.zzi(null);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final zzfyx zzfyxVar = (zzfyx) it.next();
            zzi = zzfyo.zzn(zzfyo.zzg(zzi, Throwable.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzczl
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    zzfyk.this.zza((Throwable) obj);
                    return zzfyo.zzi(null);
                }
            }, zzczqVar.zza), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzczm
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return zzczq.this.zza(zzfykVar, zzfyxVar, (zzczc) obj);
                }
            }, zzczqVar.zza);
        }
        zzfyo.zzr(zzi, new zzczp(zzczqVar, zzfykVar), zzczqVar.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zza(zzfyk zzfykVar, zzfyx zzfyxVar, zzczc zzczcVar) throws Exception {
        if (zzczcVar != null) {
            zzfykVar.zzb(zzczcVar);
        }
        return zzfyo.zzo(zzfyxVar, ((Long) zzbla.zzb.zze()).longValue(), TimeUnit.MILLISECONDS, this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd() {
        this.zzd = false;
    }

    public final void zze(zzfyk zzfykVar) {
        zzfyo.zzr(this.zzc, new zzczo(this, zzfykVar), this.zza);
    }

    public final boolean zzf() {
        return this.zzd;
    }
}
