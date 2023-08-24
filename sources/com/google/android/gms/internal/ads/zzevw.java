package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzevw implements zzeun {
    private final Context zza;
    private final zzcfw zzb;
    private final ScheduledExecutorService zzc;
    private final Executor zzd;
    private final String zze;
    private final zzcfn zzf;

    public zzevw(zzcfn zzcfnVar, int r2, Context context, zzcfw zzcfwVar, ScheduledExecutorService scheduledExecutorService, Executor executor, String str, byte[] bArr) {
        this.zzf = zzcfnVar;
        this.zza = context;
        this.zzb = zzcfwVar;
        this.zzc = scheduledExecutorService;
        this.zzd = executor;
        this.zze = str;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 44;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzf((zzfyf) zzfyo.zzo(zzfyo.zzm(zzfyf.zzv(zzfyo.zzl(new zzfxu() { // from class: com.google.android.gms.internal.ads.zzevt
            @Override // com.google.android.gms.internal.ads.zzfxu
            public final zzfyx zza() {
                return zzfyo.zzi(null);
            }
        }, this.zzd)), new zzfru() { // from class: com.google.android.gms.internal.ads.zzevu
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                String str = (String) obj;
                if (str == null) {
                    return null;
                }
                return new zzevx(str);
            }
        }, this.zzd), ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaP)).longValue(), TimeUnit.MILLISECONDS, this.zzc), Exception.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzevv
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                zzevw.this.zzc((Exception) obj);
                return null;
            }
        }, zzfze.zzb());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzevx zzc(Exception exc) {
        this.zzb.zzt(exc, "AttestationTokenSignal");
        return null;
    }
}
