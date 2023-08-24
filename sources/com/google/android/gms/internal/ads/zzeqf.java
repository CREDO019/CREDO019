package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.appset.AppSet;
import com.google.android.gms.appset.AppSetIdClient;
import com.google.android.gms.appset.AppSetIdInfo;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeqf implements zzeun {
    final zzcfw zza;
    AppSetIdClient zzb;
    private final ScheduledExecutorService zzc;
    private final zzfyy zzd;
    private final Context zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeqf(Context context, zzcfw zzcfwVar, ScheduledExecutorService scheduledExecutorService, zzfyy zzfyyVar) {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzco)).booleanValue()) {
            this.zzb = AppSet.getClient(context);
        }
        this.zze = context;
        this.zza = zzcfwVar;
        this.zzc = scheduledExecutorService;
        this.zzd = zzfyyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 11;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        Task<AppSetIdInfo> appSetIdInfo;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzck)).booleanValue()) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcp)).booleanValue()) {
                if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcl)).booleanValue()) {
                    return zzfyo.zzm(zzfpk.zza(this.zzb.getAppSetIdInfo()), new zzfru() { // from class: com.google.android.gms.internal.ads.zzeqc
                        @Override // com.google.android.gms.internal.ads.zzfru
                        public final Object apply(Object obj) {
                            AppSetIdInfo appSetIdInfo2 = (AppSetIdInfo) obj;
                            return new zzeqg(appSetIdInfo2.getId(), appSetIdInfo2.getScope());
                        }
                    }, zzcha.zzf);
                }
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzco)).booleanValue()) {
                    appSetIdInfo = zzfep.zza(this.zze);
                } else {
                    appSetIdInfo = this.zzb.getAppSetIdInfo();
                }
                if (appSetIdInfo == null) {
                    return zzfyo.zzi(new zzeqg(null, -1));
                }
                zzfyx zzn = zzfyo.zzn(zzfpk.zza(appSetIdInfo), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeqd
                    @Override // com.google.android.gms.internal.ads.zzfxv
                    public final zzfyx zza(Object obj) {
                        AppSetIdInfo appSetIdInfo2 = (AppSetIdInfo) obj;
                        if (appSetIdInfo2 == null) {
                            return zzfyo.zzi(new zzeqg(null, -1));
                        }
                        return zzfyo.zzi(new zzeqg(appSetIdInfo2.getId(), appSetIdInfo2.getScope()));
                    }
                }, zzcha.zzf);
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcm)).booleanValue()) {
                    zzn = zzfyo.zzo(zzn, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcn)).longValue(), TimeUnit.MILLISECONDS, this.zzc);
                }
                return zzfyo.zzf(zzn, Exception.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzeqe
                    @Override // com.google.android.gms.internal.ads.zzfru
                    public final Object apply(Object obj) {
                        zzeqf.this.zza.zzt((Exception) obj, "AppSetIdInfoSignal");
                        return new zzeqg(null, -1);
                    }
                }, this.zzd);
            }
        }
        return zzfyo.zzi(new zzeqg(null, -1));
    }
}
