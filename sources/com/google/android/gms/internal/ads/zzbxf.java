package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbxf implements MediationAdLoadCallback {
    final /* synthetic */ zzbwp zza;
    final /* synthetic */ zzbvl zzb;
    final /* synthetic */ zzbxj zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxf(zzbxj zzbxjVar, zzbwp zzbwpVar, zzbvl zzbvlVar) {
        this.zzc = zzbxjVar;
        this.zza = zzbwpVar;
        this.zzb = zzbvlVar;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(AdError adError) {
        try {
            this.zza.zzf(adError.zza());
        } catch (RemoteException e) {
            zzcgn.zzh("", e);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final /* bridge */ /* synthetic */ Object onSuccess(Object obj) {
        MediationInterstitialAd mediationInterstitialAd = (MediationInterstitialAd) obj;
        if (mediationInterstitialAd == null) {
            zzcgn.zzj("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zza.zze("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzcgn.zzh("", e);
                return null;
            }
        }
        try {
            this.zzc.zzb = mediationInterstitialAd;
            this.zza.zzg();
        } catch (RemoteException e2) {
            zzcgn.zzh("", e2);
        }
        return new zzbxk(this.zzb);
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdLoadCallback
    public final void onFailure(String str) {
        onFailure(new AdError(0, str, AdError.UNDEFINED_DOMAIN));
    }
}
