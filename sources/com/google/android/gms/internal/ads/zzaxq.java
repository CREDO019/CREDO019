package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaxq implements Runnable {
    final /* synthetic */ IOException zza;
    final /* synthetic */ zzaxu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaxq(zzaxu zzaxuVar, IOException iOException) {
        this.zzb = zzaxuVar;
        this.zza = iOException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzaxv zzaxvVar;
        zzaxvVar = this.zzb.zze;
        zzaxvVar.zzi(this.zza);
    }
}
