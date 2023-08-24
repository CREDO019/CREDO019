package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbdq implements zzbcf {
    final /* synthetic */ zzbdt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbdq(zzbdt zzbdtVar) {
        this.zza = zzbdtVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbcf
    public final void zza(boolean z) {
        if (z) {
            this.zza.zzl();
        } else {
            zzbdt.zzh(this.zza);
        }
    }
}
