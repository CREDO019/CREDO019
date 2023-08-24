package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.mediation.MediationInterstitialListener;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbxn implements com.google.android.gms.ads.internal.overlay.zzo {
    final /* synthetic */ zzbxp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxn(zzbxp zzbxpVar) {
        this.zza = zzbxpVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzb() {
        MediationInterstitialListener mediationInterstitialListener;
        zzcgn.zze("Opening AdMobCustomTabsAdapter overlay.");
        zzbxp zzbxpVar = this.zza;
        mediationInterstitialListener = zzbxpVar.zzb;
        mediationInterstitialListener.onAdOpened(zzbxpVar);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbC() {
        zzcgn.zze("Delay close AdMobCustomTabsAdapter overlay.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbK() {
        zzcgn.zze("AdMobCustomTabsAdapter overlay is resumed.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbr() {
        zzcgn.zze("AdMobCustomTabsAdapter overlay is paused.");
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zze() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzf(int r2) {
        MediationInterstitialListener mediationInterstitialListener;
        zzcgn.zze("AdMobCustomTabsAdapter overlay is closed.");
        zzbxp zzbxpVar = this.zza;
        mediationInterstitialListener = zzbxpVar.zzb;
        mediationInterstitialListener.onAdClosed(zzbxpVar);
    }
}
