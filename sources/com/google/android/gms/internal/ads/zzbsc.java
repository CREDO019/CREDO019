package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbsc extends zzarw implements zzbsd {
    public zzbsc() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 != 1) {
            if (r2 != 2) {
                return false;
            }
            parcel.readInt();
            zzarx.zzc(parcel);
        }
        parcel2.writeNoException();
        return true;
    }
}
