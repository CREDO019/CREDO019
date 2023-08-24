package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbk extends zzarw implements zzbl {
    public zzbk() {
        super("com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            zzarx.zzc(parcel);
            zzg((zzl) zzarx.zza(parcel, zzl.CREATOR));
            parcel2.writeNoException();
        } else if (r2 == 2) {
            String zze = zze();
            parcel2.writeNoException();
            parcel2.writeString(zze);
        } else if (r2 == 3) {
            boolean zzi = zzi();
            parcel2.writeNoException();
            zzarx.zzd(parcel2, zzi);
        } else if (r2 == 4) {
            String zzf = zzf();
            parcel2.writeNoException();
            parcel2.writeString(zzf);
        } else if (r2 != 5) {
            return false;
        } else {
            int readInt = parcel.readInt();
            zzarx.zzc(parcel);
            zzh((zzl) zzarx.zza(parcel, zzl.CREATOR), readInt);
            parcel2.writeNoException();
        }
        return true;
    }
}
