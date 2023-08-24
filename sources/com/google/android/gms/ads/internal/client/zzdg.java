package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzdg extends zzarw implements zzdh {
    public zzdg() {
        super("com.google.android.gms.ads.internal.client.IResponseInfo");
    }

    public static zzdh zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IResponseInfo");
        return queryLocalInterface instanceof zzdh ? (zzdh) queryLocalInterface : new zzdf(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        if (r1 == 1) {
            String zzg = zzg();
            parcel2.writeNoException();
            parcel2.writeString(zzg);
        } else if (r1 == 2) {
            String zzh = zzh();
            parcel2.writeNoException();
            parcel2.writeString(zzh);
        } else if (r1 == 3) {
            List zzi = zzi();
            parcel2.writeNoException();
            parcel2.writeTypedList(zzi);
        } else if (r1 == 4) {
            zzu zzf = zzf();
            parcel2.writeNoException();
            zzarx.zzf(parcel2, zzf);
        } else if (r1 != 5) {
            return false;
        } else {
            Bundle zze = zze();
            parcel2.writeNoException();
            zzarx.zzf(parcel2, zze);
        }
        return true;
    }
}
