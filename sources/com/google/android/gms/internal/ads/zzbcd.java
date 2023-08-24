package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbcd implements Runnable {
    final /* synthetic */ zzbce zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbcd(zzbce zzbceVar) {
        this.zza = zzbceVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        boolean z;
        boolean z2;
        List<zzbcf> list;
        obj = this.zza.zzc;
        synchronized (obj) {
            zzbce zzbceVar = this.zza;
            z = zzbceVar.zzd;
            if (z) {
                z2 = zzbceVar.zze;
                if (z2) {
                    zzbceVar.zzd = false;
                    com.google.android.gms.ads.internal.util.zze.zze("App went background");
                    list = this.zza.zzf;
                    for (zzbcf zzbcfVar : list) {
                        try {
                            zzbcfVar.zza(false);
                        } catch (Exception e) {
                            zzcgn.zzh("", e);
                        }
                    }
                }
            }
            com.google.android.gms.ads.internal.util.zze.zze("App is still foreground");
        }
    }
}
