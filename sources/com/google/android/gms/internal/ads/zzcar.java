package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcar extends zzarw implements zzcas {
    public zzcar() {
        super("com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r4, Parcel parcel, Parcel parcel2, int r7) throws RemoteException {
        zzcaw zzcawVar = null;
        if (r4 == 1) {
            zzcam zzcamVar = (zzcam) zzarx.zza(parcel, zzcam.CREATOR);
            zzarx.zzc(parcel);
            parcel2.writeNoException();
            zzarx.zzf(parcel2, null);
        } else if (r4 == 2) {
            zzcam zzcamVar2 = (zzcam) zzarx.zza(parcel, zzcam.CREATOR);
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdResponseListener");
                if (queryLocalInterface instanceof zzcat) {
                    zzcat zzcatVar = (zzcat) queryLocalInterface;
                }
            }
            zzarx.zzc(parcel);
            parcel2.writeNoException();
        } else if (r4 == 4) {
            zzcba zzcbaVar = (zzcba) zzarx.zza(parcel, zzcba.CREATOR);
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            if (readStrongBinder2 != null) {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzcawVar = queryLocalInterface2 instanceof zzcaw ? (zzcaw) queryLocalInterface2 : new zzcau(readStrongBinder2);
            }
            zzarx.zzc(parcel);
            zzg(zzcbaVar, zzcawVar);
            parcel2.writeNoException();
        } else if (r4 == 5) {
            zzcba zzcbaVar2 = (zzcba) zzarx.zza(parcel, zzcba.CREATOR);
            IBinder readStrongBinder3 = parcel.readStrongBinder();
            if (readStrongBinder3 != null) {
                IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzcawVar = queryLocalInterface3 instanceof zzcaw ? (zzcaw) queryLocalInterface3 : new zzcau(readStrongBinder3);
            }
            zzarx.zzc(parcel);
            zzf(zzcbaVar2, zzcawVar);
            parcel2.writeNoException();
        } else if (r4 == 6) {
            zzcba zzcbaVar3 = (zzcba) zzarx.zza(parcel, zzcba.CREATOR);
            IBinder readStrongBinder4 = parcel.readStrongBinder();
            if (readStrongBinder4 != null) {
                IInterface queryLocalInterface4 = readStrongBinder4.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzcawVar = queryLocalInterface4 instanceof zzcaw ? (zzcaw) queryLocalInterface4 : new zzcau(readStrongBinder4);
            }
            zzarx.zzc(parcel);
            zze(zzcbaVar3, zzcawVar);
            parcel2.writeNoException();
        } else if (r4 != 7) {
            return false;
        } else {
            String readString = parcel.readString();
            IBinder readStrongBinder5 = parcel.readStrongBinder();
            if (readStrongBinder5 != null) {
                IInterface queryLocalInterface5 = readStrongBinder5.queryLocalInterface("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
                zzcawVar = queryLocalInterface5 instanceof zzcaw ? (zzcaw) queryLocalInterface5 : new zzcau(readStrongBinder5);
            }
            zzarx.zzc(parcel);
            zzh(readString, zzcawVar);
            parcel2.writeNoException();
        }
        return true;
    }
}
