package com.google.android.gms.internal.ads;

import android.os.IBinder;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfrc extends zzfqw {
    final /* synthetic */ IBinder zza;
    final /* synthetic */ zzfrf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfrc(zzfrf zzfrfVar, IBinder iBinder) {
        this.zzb = zzfrfVar;
        this.zza = iBinder;
    }

    @Override // com.google.android.gms.internal.ads.zzfqw
    public final void zza() {
        List<Runnable> list;
        List list2;
        this.zzb.zza.zzn = zzfqr.zzb(this.zza);
        zzfrg.zzn(this.zzb.zza);
        this.zzb.zza.zzh = false;
        list = this.zzb.zza.zze;
        for (Runnable runnable : list) {
            runnable.run();
        }
        list2 = this.zzb.zza.zze;
        list2.clear();
    }
}
