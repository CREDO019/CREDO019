package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdt extends zzax {
    final /* synthetic */ zzdu zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdt(zzdu zzduVar) {
        this.zza = zzduVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzax, com.google.android.gms.ads.AdListener
    public final void onAdFailedToLoad(LoadAdError loadAdError) {
        VideoController videoController;
        zzdu zzduVar = this.zza;
        videoController = zzduVar.zze;
        videoController.zzb(zzduVar.zzi());
        super.onAdFailedToLoad(loadAdError);
    }

    @Override // com.google.android.gms.ads.internal.client.zzax, com.google.android.gms.ads.AdListener
    public final void onAdLoaded() {
        VideoController videoController;
        zzdu zzduVar = this.zza;
        videoController = zzduVar.zze;
        videoController.zzb(zzduVar.zzi());
        super.onAdLoaded();
    }
}
