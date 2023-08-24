package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdzo implements zzfyk {
    final /* synthetic */ zzdzq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdzo(zzdzq zzdzqVar) {
        this.zza = zzdzqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        long j;
        zzchf zzchfVar;
        synchronized (this) {
            this.zza.zzc = true;
            zzdzq zzdzqVar = this.zza;
            long elapsedRealtime = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
            j = this.zza.zzd;
            zzdzqVar.zzv("com.google.android.gms.ads.MobileAds", false, "Internal Error.", (int) (elapsedRealtime - j));
            zzchfVar = this.zza.zze;
            zzchfVar.zze(new Exception());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(@Nullable Object obj) {
        long j;
        Executor executor;
        final String str = (String) obj;
        synchronized (this) {
            this.zza.zzc = true;
            zzdzq zzdzqVar = this.zza;
            long elapsedRealtime = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
            j = this.zza.zzd;
            zzdzqVar.zzv("com.google.android.gms.ads.MobileAds", true, "", (int) (elapsedRealtime - j));
            executor = this.zza.zzi;
            executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdzn
                @Override // java.lang.Runnable
                public final void run() {
                    zzdzo zzdzoVar = zzdzo.this;
                    zzdzq.zzj(zzdzoVar.zza, str);
                }
            });
        }
    }
}
