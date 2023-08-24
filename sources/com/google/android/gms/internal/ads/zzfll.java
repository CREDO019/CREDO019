package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfll implements Runnable {
    final /* synthetic */ zzflq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfll(zzflq zzflqVar) {
        this.zza = zzflqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzflk zzflkVar;
        zzflkVar = this.zza.zzl;
        zzflkVar.zzb();
    }
}
