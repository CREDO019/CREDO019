package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbeb extends zzchf {
    final /* synthetic */ zzbeh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbeb(zzbeh zzbehVar) {
        this.zza = zzbehVar;
    }

    @Override // com.google.android.gms.internal.ads.zzchf, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        zzbeh.zze(this.zza);
        return super.cancel(z);
    }
}
