package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzexb implements zzeun {
    private final zzcfw zza;
    private final boolean zzb;
    private final ScheduledExecutorService zzc;
    private final zzfyy zzd;
    private final String zze;
    private final zzcfl zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzexb(zzcfw zzcfwVar, boolean z, zzcfl zzcflVar, zzfyy zzfyyVar, String str, ScheduledExecutorService scheduledExecutorService, byte[] bArr) {
        this.zza = zzcfwVar;
        this.zzb = z;
        this.zzf = zzcflVar;
        this.zzd = zzfyyVar;
        this.zze = str;
        this.zzc = scheduledExecutorService;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 50;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzexc zzc(Exception exc) {
        this.zza.zzt(exc, "TrustlessTokenSignal");
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return !this.zzb ? zzfyo.zzi(null) : zzfyo.zzf(zzfyo.zzo(zzfyo.zzm(zzfyo.zzi(null), new zzfru() { // from class: com.google.android.gms.internal.ads.zzewz
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                String str = (String) obj;
                if (str == null) {
                    return null;
                }
                return new zzexc(str);
            }
        }, this.zzd), ((Long) zzblb.zzc.zze()).longValue(), TimeUnit.MILLISECONDS, this.zzc), Exception.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzexa
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                zzexb.this.zzc((Exception) obj);
                return null;
            }
        }, this.zzd);
    }
}
