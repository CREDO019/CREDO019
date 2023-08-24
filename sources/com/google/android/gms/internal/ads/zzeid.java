package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeid implements zzfyk {
    final /* synthetic */ zzeie zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeid(zzeie zzeieVar) {
        this.zza = zzeieVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzcxx zzcxxVar;
        zzdda zzddaVar;
        zzcxxVar = this.zza.zza;
        com.google.android.gms.ads.internal.client.zze zza = zzcxxVar.zzd().zza(th);
        zzddaVar = this.zza.zzd;
        zzddaVar.zza(zza);
        zzfeh.zzb(zza.zza, th, "DelayedBannerAd.onFailure");
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* synthetic */ void zzb(Object obj) {
        ((zzcxa) obj).zzW();
    }
}
