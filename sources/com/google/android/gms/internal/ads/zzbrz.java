package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbrz extends zzarw implements zzbsa {
    public zzbrz() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        zzbsd zzbsbVar;
        if (r3 == 3) {
            com.google.android.gms.ads.internal.client.zzdk zzb = zzb();
            parcel2.writeNoException();
            zzarx.zzg(parcel2, zzb);
            return true;
        } else if (r3 == 4) {
            zzd();
            parcel2.writeNoException();
            return true;
        } else if (r3 == 5) {
            IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                zzbsbVar = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
                zzbsbVar = queryLocalInterface instanceof zzbsd ? (zzbsd) queryLocalInterface : new zzbsb(readStrongBinder);
            }
            zzarx.zzc(parcel);
            zzf(asInterface, zzbsbVar);
            parcel2.writeNoException();
            return true;
        } else if (r3 == 6) {
            IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzarx.zzc(parcel);
            zze(asInterface2);
            parcel2.writeNoException();
            return true;
        } else if (r3 != 7) {
            return false;
        } else {
            zzblx zzc = zzc();
            parcel2.writeNoException();
            zzarx.zzg(parcel2, zzc);
            return true;
        }
    }
}
