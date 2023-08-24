package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbde extends zzarw implements zzbdf {
    public zzbde() {
        super("com.google.android.gms.ads.internal.appopen.client.IAppOpenAd");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        zzbdj zzbdjVar = null;
        zzbdm zzbdmVar = null;
        switch (r3) {
            case 2:
                com.google.android.gms.ads.internal.client.zzbs zze = zze();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zze);
                return true;
            case 3:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenAdPresentationCallback");
                    zzbdjVar = queryLocalInterface instanceof zzbdj ? (zzbdj) queryLocalInterface : new zzbdj(readStrongBinder);
                }
                zzarx.zzc(parcel);
                zzj(zzbdjVar);
                parcel2.writeNoException();
                return true;
            case 4:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.ads.internal.appopen.client.IAppOpenFullScreenContentCallback");
                    zzbdmVar = queryLocalInterface2 instanceof zzbdm ? (zzbdm) queryLocalInterface2 : new zzbdk(readStrongBinder2);
                }
                zzarx.zzc(parcel);
                zzi(asInterface, zzbdmVar);
                parcel2.writeNoException();
                return true;
            case 5:
                com.google.android.gms.ads.internal.client.zzdh zzf = zzf();
                parcel2.writeNoException();
                zzarx.zzg(parcel2, zzf);
                return true;
            case 6:
                boolean zzh = zzarx.zzh(parcel);
                zzarx.zzc(parcel);
                zzg(zzh);
                parcel2.writeNoException();
                return true;
            case 7:
                com.google.android.gms.ads.internal.client.zzde zzb = com.google.android.gms.ads.internal.client.zzdd.zzb(parcel.readStrongBinder());
                zzarx.zzc(parcel);
                zzh(zzb);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
