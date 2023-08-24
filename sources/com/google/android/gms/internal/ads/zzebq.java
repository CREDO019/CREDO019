package com.google.android.gms.internal.ads;

import android.os.Binder;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzebq {
    private final ScheduledExecutorService zza;
    private final zzfyy zzb;
    private final zzech zzc;
    private final zzgul zzd;

    public zzebq(ScheduledExecutorService scheduledExecutorService, zzfyy zzfyyVar, zzech zzechVar, zzgul zzgulVar) {
        this.zza = scheduledExecutorService;
        this.zzb = zzfyyVar;
        this.zzc = zzechVar;
        this.zzd = zzgulVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zza(zzcba zzcbaVar, int r2, Throwable th) throws Exception {
        return ((zzedv) this.zzd.zzb()).zzd(zzcbaVar, r2);
    }

    public final zzfyx zzb(final zzcba zzcbaVar) {
        zzfyx zzfyxVar;
        String str = zzcbaVar.zzd;
        com.google.android.gms.ads.internal.zzt.zzq();
        if (com.google.android.gms.ads.internal.util.zzs.zzy(str)) {
            zzfyxVar = zzfyo.zzh(new zzecu(1));
        } else {
            final zzech zzechVar = this.zzc;
            synchronized (zzechVar.zzb) {
                if (zzechVar.zzc) {
                    zzfyxVar = zzechVar.zza;
                } else {
                    zzechVar.zzc = true;
                    zzechVar.zze = zzcbaVar;
                    zzechVar.zzf.checkAvailabilityAndConnect();
                    zzechVar.zza.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzecg
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzech.this.zza();
                        }
                    }, zzcha.zzf);
                    zzfyxVar = zzechVar.zza;
                }
            }
        }
        final int callingUid = Binder.getCallingUid();
        return zzfyo.zzg((zzfyf) zzfyo.zzo(zzfyf.zzv(zzfyxVar), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeB)).intValue(), TimeUnit.SECONDS, this.zza), Throwable.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzebp
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzebq.this.zza(zzcbaVar, callingUid, (Throwable) obj);
            }
        }, this.zzb);
    }
}
