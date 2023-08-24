package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeie implements zzegk {
    private final zzcxx zza;
    private final zzehl zzb;
    private final zzfyy zzc;
    private final zzdda zzd;
    private final ScheduledExecutorService zze;

    public zzeie(zzcxx zzcxxVar, zzehl zzehlVar, zzdda zzddaVar, ScheduledExecutorService scheduledExecutorService, zzfyy zzfyyVar) {
        this.zza = zzcxxVar;
        this.zzb = zzehlVar;
        this.zzd = zzddaVar;
        this.zze = scheduledExecutorService;
        this.zzc = zzfyyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        return this.zzc.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeib
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeie.this.zzc(zzfdeVar, zzfcsVar);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        return zzfdeVar.zza.zza.zza() != null && this.zzb.zzb(zzfdeVar, zzfcsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzcxa zzc(final zzfde zzfdeVar, final zzfcs zzfcsVar) throws Exception {
        return this.zza.zzb(new zzczr(zzfdeVar, zzfcsVar, null), new zzcyk(zzfdeVar.zza.zza.zza(), new Runnable() { // from class: com.google.android.gms.internal.ads.zzeic
            @Override // java.lang.Runnable
            public final void run() {
                zzeie.this.zzf(zzfdeVar, zzfcsVar);
            }
        })).zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzfyo.zzr(zzfyo.zzo(this.zzb.zza(zzfdeVar, zzfcsVar), zzfcsVar.zzS, TimeUnit.SECONDS, this.zze), new zzeid(this), this.zzc);
    }
}
