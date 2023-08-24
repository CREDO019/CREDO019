package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.nativead.NativeCustomFormatAd;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbyj extends zzbng {
    final /* synthetic */ zzbyk zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbyj(zzbyk zzbykVar, zzbyi zzbyiVar) {
        this.zza = zzbykVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnh
    public final void zze(zzbmu zzbmuVar) {
        NativeCustomFormatAd.OnCustomFormatAdLoadedListener onCustomFormatAdLoadedListener;
        NativeCustomFormatAd zzf;
        zzbyk zzbykVar = this.zza;
        onCustomFormatAdLoadedListener = zzbykVar.zza;
        zzf = zzbykVar.zzf(zzbmuVar);
        onCustomFormatAdLoadedListener.onCustomFormatAdLoaded(zzf);
    }
}
