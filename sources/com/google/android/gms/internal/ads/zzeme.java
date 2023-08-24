package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzeme implements com.google.android.gms.ads.internal.zzf {
    final /* synthetic */ zzdlf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeme(zzemf zzemfVar, zzdlf zzdlfVar) {
        this.zza = zzdlfVar;
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zza(View view) {
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zzb() {
        this.zza.zzb().onAdClicked();
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zzc() {
        this.zza.zzc().zza();
        this.zza.zzf().zza();
    }
}
