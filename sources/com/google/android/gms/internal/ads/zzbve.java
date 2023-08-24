package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbve extends zzarw implements zzbvf {
    public zzbve() {
        super("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    public static zzbvf zzf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        return queryLocalInterface instanceof zzbvf ? (zzbvf) queryLocalInterface : new zzbvd(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            String readString = parcel.readString();
            zzarx.zzc(parcel);
            zzbvi zzb = zzb(readString);
            parcel2.writeNoException();
            zzarx.zzg(parcel2, zzb);
        } else if (r2 == 2) {
            String readString2 = parcel.readString();
            zzarx.zzc(parcel);
            boolean zze = zze(readString2);
            parcel2.writeNoException();
            zzarx.zzd(parcel2, zze);
        } else if (r2 == 3) {
            String readString3 = parcel.readString();
            zzarx.zzc(parcel);
            zzbwy zzc = zzc(readString3);
            parcel2.writeNoException();
            zzarx.zzg(parcel2, zzc);
        } else if (r2 != 4) {
            return false;
        } else {
            String readString4 = parcel.readString();
            zzarx.zzc(parcel);
            boolean zzd = zzd(readString4);
            parcel2.writeNoException();
            zzarx.zzd(parcel2, zzd);
        }
        return true;
    }
}
