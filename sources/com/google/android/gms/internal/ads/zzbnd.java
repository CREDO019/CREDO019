package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbnd extends zzarw implements zzbne {
    public zzbnd() {
        super("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    public static zzbne zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        return queryLocalInterface instanceof zzbne ? (zzbne) queryLocalInterface : new zzbnc(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r3, Parcel parcel, Parcel parcel2, int r6) throws RemoteException {
        zzbmu zzbmsVar;
        if (r3 == 1) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                zzbmsVar = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
                zzbmsVar = queryLocalInterface instanceof zzbmu ? (zzbmu) queryLocalInterface : new zzbms(readStrongBinder);
            }
            String readString = parcel.readString();
            zzarx.zzc(parcel);
            zze(zzbmsVar, readString);
            parcel2.writeNoException();
            return true;
        }
        return false;
    }
}
