package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbwl extends zzarw implements zzbwm {
    public zzbwl() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzarx.zzc(parcel);
            zzg(asInterface);
        } else if (r2 == 2) {
            String readString = parcel.readString();
            zzarx.zzc(parcel);
            zze(readString);
        } else if (r2 == 3) {
            zzarx.zzc(parcel);
            zzf((com.google.android.gms.ads.internal.client.zze) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zze.CREATOR));
        } else if (r2 != 4) {
            return false;
        } else {
            zzbvo zzb = zzbvn.zzb(parcel.readStrongBinder());
            zzarx.zzc(parcel);
            zzh(zzb);
        }
        parcel2.writeNoException();
        return true;
    }
}
