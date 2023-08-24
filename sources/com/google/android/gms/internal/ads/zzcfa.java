package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcfa extends zzarw implements zzcfb {
    public zzcfa() {
        super("com.google.android.gms.ads.internal.signals.ISignalCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        if (r3 == 1) {
            parcel.readString();
            parcel.readString();
            zzarx.zzc(parcel);
        } else if (r3 == 2) {
            String readString = parcel.readString();
            zzarx.zzc(parcel);
            zzb(readString);
        } else if (r3 != 3) {
            return false;
        } else {
            zzarx.zzc(parcel);
            zzc(parcel.readString(), parcel.readString(), (Bundle) zzarx.zza(parcel, Bundle.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
