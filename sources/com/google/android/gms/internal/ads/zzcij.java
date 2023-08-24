package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcij implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzcik zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcij(zzcik zzcikVar, boolean z) {
        this.zzb = zzcikVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzJ("windowVisibilityChanged", "isVisible", String.valueOf(this.zza));
    }
}
