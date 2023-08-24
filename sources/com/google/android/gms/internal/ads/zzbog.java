package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.formats.OnAdManagerAdViewLoadedListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbog implements Runnable {
    final /* synthetic */ AdManagerAdView zza;
    final /* synthetic */ com.google.android.gms.ads.internal.client.zzbs zzb;
    final /* synthetic */ zzboh zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbog(zzboh zzbohVar, AdManagerAdView adManagerAdView, com.google.android.gms.ads.internal.client.zzbs zzbsVar) {
        this.zzc = zzbohVar;
        this.zza = adManagerAdView;
        this.zzb = zzbsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        OnAdManagerAdViewLoadedListener onAdManagerAdViewLoadedListener;
        if (this.zza.zzb(this.zzb)) {
            onAdManagerAdViewLoadedListener = this.zzc.zza;
            onAdManagerAdViewLoadedListener.onAdManagerAdViewLoaded(this.zza);
            return;
        }
        zzcgn.zzj("Could not bind.");
    }
}
