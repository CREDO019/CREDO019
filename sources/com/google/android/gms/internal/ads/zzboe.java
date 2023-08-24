package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzboe extends zzbng {
    final /* synthetic */ zzbof zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzboe(zzbof zzbofVar, zzbod zzbodVar) {
        this.zza = zzbofVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnh
    public final void zze(zzbmu zzbmuVar) {
        NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener;
        NativeCustomTemplateAd zzf;
        zzbof zzbofVar = this.zza;
        onCustomTemplateAdLoadedListener = zzbofVar.zza;
        zzf = zzbofVar.zzf(zzbmuVar);
        onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded(zzf);
    }
}
