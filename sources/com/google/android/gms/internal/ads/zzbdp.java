package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbdp implements Runnable {
    final /* synthetic */ zzbdt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbdp(zzbdt zzbdtVar) {
        this.zza = zzbdtVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbdt.zzh(this.zza);
    }
}
