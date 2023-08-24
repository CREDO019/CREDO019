package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbnn extends zzarw implements zzbno {
    public zzbnn() {
        super("com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
    }

    public static zzbno zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
        return queryLocalInterface instanceof zzbno ? (zzbno) queryLocalInterface : new zzbnm(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        zzbnx zzbnvVar;
        if (r3 == 1) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                zzbnvVar = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IUnifiedNativeAd");
                zzbnvVar = queryLocalInterface instanceof zzbnx ? (zzbnx) queryLocalInterface : new zzbnv(readStrongBinder);
            }
            zzarx.zzc(parcel);
            zze(zzbnvVar);
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
