package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbyy extends zzarv implements zzbza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbyy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final boolean zzE() throws RemoteException {
        Parcel zzbk = zzbk(11, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzg(int r2, int r3, Intent intent) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zza.writeInt(r3);
        zzarx.zze(zza, intent);
        zzbl(12, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzh() throws RemoteException {
        zzbl(10, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(13, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzk(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, bundle);
        zzbl(1, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzl() throws RemoteException {
        zzbl(8, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzn() throws RemoteException {
        zzbl(5, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzo() throws RemoteException {
        zzbl(2, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzp() throws RemoteException {
        zzbl(4, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzq(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, bundle);
        Parcel zzbk = zzbk(6, zza);
        if (zzbk.readInt() != 0) {
            bundle.readFromParcel(zzbk);
        }
        zzbk.recycle();
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzr() throws RemoteException {
        zzbl(3, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzs() throws RemoteException {
        zzbl(7, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzt() throws RemoteException {
        zzbl(14, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzbza
    public final void zzv() throws RemoteException {
        zzbl(9, zza());
    }
}
