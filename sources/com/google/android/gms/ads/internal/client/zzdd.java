package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzdd extends zzarw implements zzde {
    public zzdd() {
        super("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
    }

    public static zzde zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
        return queryLocalInterface instanceof zzde ? (zzde) queryLocalInterface : new zzdc(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        if (r1 == 1) {
            zzarx.zzc(parcel);
            zze((zzs) zzarx.zza(parcel, zzs.CREATOR));
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
