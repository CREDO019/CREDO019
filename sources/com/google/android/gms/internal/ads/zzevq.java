package com.google.android.gms.internal.ads;

import com.google.android.gms.appset.AppSetIdInfo;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzevq implements zzeun {
    private final zzcfw zza;
    private final String zzb;
    private final ScheduledExecutorService zzc;
    private final zzfyy zzd;
    private final zzbdo zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzevq(String str, zzbdo zzbdoVar, zzcfw zzcfwVar, ScheduledExecutorService scheduledExecutorService, zzfyy zzfyyVar, byte[] bArr) {
        this.zzb = str;
        this.zze = zzbdoVar;
        this.zza = zzcfwVar;
        this.zzc = scheduledExecutorService;
        this.zzd = zzfyyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 43;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzck)).booleanValue()) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcp)).booleanValue()) {
                zzfyx zzn = zzfyo.zzn(zzfpk.zza(Tasks.forResult(null)), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzevo
                    @Override // com.google.android.gms.internal.ads.zzfxv
                    public final zzfyx zza(Object obj) {
                        AppSetIdInfo appSetIdInfo = (AppSetIdInfo) obj;
                        if (appSetIdInfo == null) {
                            return zzfyo.zzi(new zzevr(null, -1));
                        }
                        return zzfyo.zzi(new zzevr(appSetIdInfo.getId(), appSetIdInfo.getScope()));
                    }
                }, this.zzd);
                if (((Boolean) zzbkd.zza.zze()).booleanValue()) {
                    zzn = zzfyo.zzo(zzn, ((Long) zzbkd.zzb.zze()).longValue(), TimeUnit.MILLISECONDS, this.zzc);
                }
                return zzfyo.zzf(zzn, Exception.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzevp
                    @Override // com.google.android.gms.internal.ads.zzfru
                    public final Object apply(Object obj) {
                        return zzevq.this.zzc((Exception) obj);
                    }
                }, this.zzd);
            }
        }
        return zzfyo.zzi(new zzevr(null, -1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzevr zzc(Exception exc) {
        this.zza.zzt(exc, "AppSetIdInfoGmscoreSignal");
        return new zzevr(null, -1);
    }
}
