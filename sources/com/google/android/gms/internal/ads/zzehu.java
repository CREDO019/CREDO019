package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzehu extends zzbwl {
    final /* synthetic */ zzehv zza;
    private final zzegn zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzehu(zzehv zzehvVar, zzegn zzegnVar, zzeht zzehtVar) {
        this.zza = zzehvVar;
        this.zzb = zzegnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbwm
    public final void zze(String str) throws RemoteException {
        ((zzeig) this.zzb.zzc).zzi(0, str);
    }

    @Override // com.google.android.gms.internal.ads.zzbwm
    public final void zzf(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        ((zzeig) this.zzb.zzc).zzh(zzeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbwm
    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzehv.zze(this.zza, (View) ObjectWrapper.unwrap(iObjectWrapper));
        ((zzeig) this.zzb.zzc).zzo();
    }

    @Override // com.google.android.gms.internal.ads.zzbwm
    public final void zzh(zzbvo zzbvoVar) throws RemoteException {
        zzehv.zzd(this.zza, zzbvoVar);
        ((zzeig) this.zzb.zzc).zzo();
    }
}
