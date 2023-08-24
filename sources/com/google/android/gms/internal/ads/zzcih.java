package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcih implements Runnable {
    final /* synthetic */ zzcik zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcih(zzcik zzcikVar) {
        this.zza = zzcikVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzJ("surfaceCreated", new String[0]);
    }
}
