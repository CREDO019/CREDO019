package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfhg {
    final /* synthetic */ zzfhh zza;
    private final Object zzb;
    private final String zzc;
    private final zzfyx zzd;
    private final List zze;
    private final zzfyx zzf;

    private zzfhg(zzfhh zzfhhVar, Object obj, String str, zzfyx zzfyxVar, List list, zzfyx zzfyxVar2) {
        this.zza = zzfhhVar;
        this.zzb = obj;
        this.zzc = str;
        this.zzd = zzfyxVar;
        this.zze = list;
        this.zzf = zzfyxVar2;
    }

    public final zzfgu zza() {
        zzfhi zzfhiVar;
        Object obj = this.zzb;
        String str = this.zzc;
        if (str == null) {
            str = this.zza.zzf(obj);
        }
        final zzfgu zzfguVar = new zzfgu(obj, str, this.zzf);
        zzfhiVar = this.zza.zzd;
        zzfhiVar.zza(zzfguVar);
        this.zzd.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfha
            @Override // java.lang.Runnable
            public final void run() {
                zzfhi zzfhiVar2;
                zzfhg zzfhgVar = zzfhg.this;
                zzfgu zzfguVar2 = zzfguVar;
                zzfhiVar2 = zzfhgVar.zza.zzd;
                zzfhiVar2.zzc(zzfguVar2);
            }
        }, zzcha.zzf);
        zzfyo.zzr(zzfguVar, new zzfhe(this, zzfguVar), zzcha.zzf);
        return zzfguVar;
    }

    public final zzfhg zzb(Object obj) {
        return this.zza.zzb(obj, zza());
    }

    public final zzfhg zzc(Class cls, zzfxv zzfxvVar) {
        zzfyy zzfyyVar;
        zzfhh zzfhhVar = this.zza;
        Object obj = this.zzb;
        String str = this.zzc;
        zzfyx zzfyxVar = this.zzd;
        List list = this.zze;
        zzfyx zzfyxVar2 = this.zzf;
        zzfyyVar = zzfhhVar.zzb;
        return new zzfhg(zzfhhVar, obj, str, zzfyxVar, list, zzfyo.zzg(zzfyxVar2, cls, zzfxvVar, zzfyyVar));
    }

    public final zzfhg zzd(final zzfyx zzfyxVar) {
        return zzg(new zzfxv() { // from class: com.google.android.gms.internal.ads.zzfhb
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzfyx.this;
            }
        }, zzcha.zzf);
    }

    public final zzfhg zze(final zzfgs zzfgsVar) {
        return zzf(new zzfxv() { // from class: com.google.android.gms.internal.ads.zzfhd
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzfyo.zzi(zzfgs.this.zza(obj));
            }
        });
    }

    public final zzfhg zzf(zzfxv zzfxvVar) {
        zzfyy zzfyyVar;
        zzfyyVar = this.zza.zzb;
        return zzg(zzfxvVar, zzfyyVar);
    }

    public final zzfhg zzg(zzfxv zzfxvVar, Executor executor) {
        return new zzfhg(this.zza, this.zzb, this.zzc, this.zzd, this.zze, zzfyo.zzn(this.zzf, zzfxvVar, executor));
    }

    public final zzfhg zzh(String str) {
        return new zzfhg(this.zza, this.zzb, str, this.zzd, this.zze, this.zzf);
    }

    public final zzfhg zzi(long j, TimeUnit timeUnit) {
        ScheduledExecutorService scheduledExecutorService;
        zzfhh zzfhhVar = this.zza;
        Object obj = this.zzb;
        String str = this.zzc;
        zzfyx zzfyxVar = this.zzd;
        List list = this.zze;
        zzfyx zzfyxVar2 = this.zzf;
        scheduledExecutorService = zzfhhVar.zzc;
        return new zzfhg(zzfhhVar, obj, str, zzfyxVar, list, zzfyo.zzo(zzfyxVar2, j, timeUnit, scheduledExecutorService));
    }
}
