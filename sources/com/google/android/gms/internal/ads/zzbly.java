package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbly extends zzarv implements zzbma {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbly(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    @Override // com.google.android.gms.internal.ads.zzbma
    public final double zzb() throws RemoteException {
        Parcel zzbk = zzbk(3, zza());
        double readDouble = zzbk.readDouble();
        zzbk.recycle();
        return readDouble;
    }

    @Override // com.google.android.gms.internal.ads.zzbma
    public final int zzc() throws RemoteException {
        Parcel zzbk = zzbk(5, zza());
        int readInt = zzbk.readInt();
        zzbk.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.ads.zzbma
    public final int zzd() throws RemoteException {
        Parcel zzbk = zzbk(4, zza());
        int readInt = zzbk.readInt();
        zzbk.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.ads.zzbma
    public final Uri zze() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        Uri uri = (Uri) zzarx.zza(zzbk, Uri.CREATOR);
        zzbk.recycle();
        return uri;
    }

    @Override // com.google.android.gms.internal.ads.zzbma
    public final IObjectWrapper zzf() throws RemoteException {
        Parcel zzbk = zzbk(1, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzbk.readStrongBinder());
        zzbk.recycle();
        return asInterface;
    }
}
