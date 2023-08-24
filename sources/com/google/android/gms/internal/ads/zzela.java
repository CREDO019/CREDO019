package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzela extends zzcca {
    final /* synthetic */ zzdfe zza;
    final /* synthetic */ zzdcw zzb;
    final /* synthetic */ zzdef zzc;
    final /* synthetic */ zzdku zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzela(zzelb zzelbVar, zzdfe zzdfeVar, zzdcw zzdcwVar, zzdef zzdefVar, zzdku zzdkuVar) {
        this.zza = zzdfeVar;
        this.zzb = zzdcwVar;
        this.zzc = zzdefVar;
        this.zzd = zzdkuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zze(IObjectWrapper iObjectWrapper) {
        this.zzb.onAdClicked();
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzf(IObjectWrapper iObjectWrapper) {
        this.zza.zzf(4);
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzg(IObjectWrapper iObjectWrapper, int r2) {
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzh(IObjectWrapper iObjectWrapper) {
        this.zzc.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzi(IObjectWrapper iObjectWrapper) {
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzk(IObjectWrapper iObjectWrapper, int r2) {
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzl(IObjectWrapper iObjectWrapper) {
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzm(IObjectWrapper iObjectWrapper, zzccc zzcccVar) {
        this.zzd.zza(zzcccVar);
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzn(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final void zzo(IObjectWrapper iObjectWrapper) {
        this.zzd.zzc();
    }
}
