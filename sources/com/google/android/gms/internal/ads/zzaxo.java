package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxo implements Runnable {
    final /* synthetic */ zzaxu zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaxo(zzaxu zzaxuVar) {
        this.zza = zzaxuVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        zzaxx zzaxxVar;
        zzaxu zzaxuVar = this.zza;
        z = zzaxuVar.zzF;
        if (z) {
            return;
        }
        zzaxxVar = zzaxuVar.zzo;
        zzaxxVar.zze(zzaxuVar);
    }
}
