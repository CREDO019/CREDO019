package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzbvf;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzci extends zzarw implements zzcj {
    public zzci() {
        super("com.google.android.gms.ads.internal.client.ILiteSdkInfo");
    }

    public static zzcj asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.ILiteSdkInfo");
        return queryLocalInterface instanceof zzcj ? (zzcj) queryLocalInterface : new zzch(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        if (r1 == 1) {
            zzeh liteSdkVersion = getLiteSdkVersion();
            parcel2.writeNoException();
            zzarx.zzf(parcel2, liteSdkVersion);
        } else if (r1 != 2) {
            return false;
        } else {
            zzbvf adapterCreator = getAdapterCreator();
            parcel2.writeNoException();
            zzarx.zzg(parcel2, adapterCreator);
        }
        return true;
    }
}
