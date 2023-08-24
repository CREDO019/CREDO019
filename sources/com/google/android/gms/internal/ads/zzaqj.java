package com.google.android.gms.internal.ads;

import android.app.AppOpsManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqj implements AppOpsManager.OnOpActiveChangedListener {
    final /* synthetic */ zzaqk zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaqj(zzaqk zzaqkVar) {
        this.zza = zzaqkVar;
    }

    @Override // android.app.AppOpsManager.OnOpActiveChangedListener
    public final void onOpActiveChanged(String str, int r6, String str2, boolean z) {
        long j;
        long j2;
        long j3;
        if (z) {
            this.zza.zzb = System.currentTimeMillis();
            this.zza.zze = true;
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        zzaqk zzaqkVar = this.zza;
        j = zzaqkVar.zzc;
        if (j > 0) {
            j2 = zzaqkVar.zzc;
            if (currentTimeMillis >= j2) {
                j3 = zzaqkVar.zzc;
                zzaqkVar.zzd = currentTimeMillis - j3;
            }
        }
        this.zza.zze = false;
    }
}
