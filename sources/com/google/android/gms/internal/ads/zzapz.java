package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzapz implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ zzaqb zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzapz(zzaqb zzaqbVar, int r2, boolean z) {
        this.zzb = zzaqbVar;
        this.zza = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzamx zzamxVar;
        zzaqb zzaqbVar = this.zzb;
        int r1 = this.zza;
        if (r1 > 0) {
            try {
                Thread.sleep(r1 * 1000);
            } catch (InterruptedException unused) {
            }
        }
        try {
            PackageInfo packageInfo = zzaqbVar.zza.getPackageManager().getPackageInfo(zzaqbVar.zza.getPackageName(), 0);
            Context context = zzaqbVar.zza;
            zzamxVar = zzfmn.zza(context, context.getPackageName(), Integer.toString(packageInfo.versionCode));
        } catch (Throwable unused2) {
            zzamxVar = null;
        }
        this.zzb.zzm = zzamxVar;
        if (this.zza < 4) {
            if (zzamxVar != null && zzamxVar.zzai() && !zzamxVar.zzh().equals("0000000000000000000000000000000000000000000000000000000000000000") && zzamxVar.zzaj() && zzamxVar.zzf().zze() && zzamxVar.zzf().zza() != -2) {
                return;
            }
            this.zzb.zzo(this.zza + 1, true);
        }
    }
}
