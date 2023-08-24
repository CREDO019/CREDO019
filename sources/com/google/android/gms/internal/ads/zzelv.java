package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzelv implements com.google.android.gms.ads.internal.zzf {
    final /* synthetic */ zzchf zza;
    final /* synthetic */ zzfde zzb;
    final /* synthetic */ zzfcs zzc;
    final /* synthetic */ zzemb zzd;
    final /* synthetic */ zzelw zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzelv(zzelw zzelwVar, zzchf zzchfVar, zzfde zzfdeVar, zzfcs zzfcsVar, zzemb zzembVar) {
        this.zze = zzelwVar;
        this.zza = zzchfVar;
        this.zzb = zzfdeVar;
        this.zzc = zzfcsVar;
        this.zzd = zzembVar;
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zza(View view) {
        zzemf zzemfVar;
        zzchf zzchfVar = this.zza;
        zzemfVar = this.zze.zzd;
        zzchfVar.zzd(zzemfVar.zza(this.zzb, this.zzc, view, this.zzd));
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zzb() {
    }

    @Override // com.google.android.gms.ads.internal.zzf
    public final void zzc() {
    }
}
