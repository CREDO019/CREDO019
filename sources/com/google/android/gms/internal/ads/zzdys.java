package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdys {
    private final zzbqm zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdys(zzbqm zzbqmVar) {
        this.zza = zzbqmVar;
    }

    private final void zzs(zzdyr zzdyrVar) throws RemoteException {
        String zza = zzdyr.zza(zzdyrVar);
        com.google.android.gms.ads.internal.util.zze.zzi("Dispatching AFMA event on publisher webview: ".concat(zza));
        this.zza.zzb(zza);
    }

    public final void zza() throws RemoteException {
        zzs(new zzdyr("initialize", null));
    }

    public final void zzb(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("interstitial", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onAdClicked";
        this.zza.zzb(zzdyr.zza(zzdyrVar));
    }

    public final void zzc(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("interstitial", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onAdClosed";
        zzs(zzdyrVar);
    }

    public final void zzd(long j, int r6) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("interstitial", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onAdFailedToLoad";
        zzdyrVar.zzd = Integer.valueOf(r6);
        zzs(zzdyrVar);
    }

    public final void zze(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("interstitial", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onAdLoaded";
        zzs(zzdyrVar);
    }

    public final void zzf(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("interstitial", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onNativeAdObjectNotAvailable";
        zzs(zzdyrVar);
    }

    public final void zzg(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("interstitial", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onAdOpened";
        zzs(zzdyrVar);
    }

    public final void zzh(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("creation", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "nativeObjectCreated";
        zzs(zzdyrVar);
    }

    public final void zzi(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("creation", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "nativeObjectNotCreated";
        zzs(zzdyrVar);
    }

    public final void zzj(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onAdClicked";
        zzs(zzdyrVar);
    }

    public final void zzk(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onRewardedAdClosed";
        zzs(zzdyrVar);
    }

    public final void zzl(long j, zzccg zzccgVar) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onUserEarnedReward";
        zzdyrVar.zze = zzccgVar.zzf();
        zzdyrVar.zzf = Integer.valueOf(zzccgVar.zze());
        zzs(zzdyrVar);
    }

    public final void zzm(long j, int r6) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onRewardedAdFailedToLoad";
        zzdyrVar.zzd = Integer.valueOf(r6);
        zzs(zzdyrVar);
    }

    public final void zzn(long j, int r6) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onRewardedAdFailedToShow";
        zzdyrVar.zzd = Integer.valueOf(r6);
        zzs(zzdyrVar);
    }

    public final void zzo(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onAdImpression";
        zzs(zzdyrVar);
    }

    public final void zzp(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onRewardedAdLoaded";
        zzs(zzdyrVar);
    }

    public final void zzq(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onNativeAdObjectNotAvailable";
        zzs(zzdyrVar);
    }

    public final void zzr(long j) throws RemoteException {
        zzdyr zzdyrVar = new zzdyr("rewarded", null);
        zzdyrVar.zza = Long.valueOf(j);
        zzdyrVar.zzc = "onRewardedAdOpened";
        zzs(zzdyrVar);
    }
}
