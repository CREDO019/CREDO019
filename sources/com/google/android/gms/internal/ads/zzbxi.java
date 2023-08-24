package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbxi implements MediationAdLoadCallback {
    final /* synthetic */ zzbwv zza;
    final /* synthetic */ zzbvl zzb;
    final /* synthetic */ zzbxj zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxi(zzbxj zzbxjVar, zzbwv zzbwvVar, zzbvl zzbvlVar) {
        this.zzc = zzbxjVar;
        this.zza = zzbwvVar;
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
        MediationRewardedAd mediationRewardedAd = (MediationRewardedAd) obj;
        if (mediationRewardedAd == null) {
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
            this.zzc.zzc = mediationRewardedAd;
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
