package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public abstract class zzch extends zzbn implements zzci {
    public zzch() {
        super("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzbn
    protected final boolean zza(int r7, Parcel parcel, Parcel parcel2, int r10) throws RemoteException {
        if (r7 == 1) {
            long readLong = parcel.readLong();
            zzbo.zzc(parcel);
            zze(parcel.readString(), parcel.readString(), (Bundle) zzbo.zza(parcel, Bundle.CREATOR), readLong);
            parcel2.writeNoException();
        } else if (r7 != 2) {
            return false;
        } else {
            int zzd = zzd();
            parcel2.writeNoException();
            parcel2.writeInt(zzd);
        }
        return true;
    }
}
