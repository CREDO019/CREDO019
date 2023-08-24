package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzblz extends zzarw implements zzbma {
    public zzblz() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    public static zzbma zzg(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        return queryLocalInterface instanceof zzbma ? (zzbma) queryLocalInterface : new zzbly(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        if (r3 == 1) {
            IObjectWrapper zzf = zzf();
            parcel2.writeNoException();
            zzarx.zzg(parcel2, zzf);
        } else if (r3 == 2) {
            Uri zze = zze();
            parcel2.writeNoException();
            zzarx.zzf(parcel2, zze);
        } else if (r3 == 3) {
            double zzb = zzb();
            parcel2.writeNoException();
            parcel2.writeDouble(zzb);
        } else if (r3 == 4) {
            int zzd = zzd();
            parcel2.writeNoException();
            parcel2.writeInt(zzd);
        } else if (r3 != 5) {
            return false;
        } else {
            int zzc = zzc();
            parcel2.writeNoException();
            parcel2.writeInt(zzc);
        }
        return true;
    }
}
