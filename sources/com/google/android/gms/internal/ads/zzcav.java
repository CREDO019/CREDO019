package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcav extends zzarw implements zzcaw {
    public zzcav() {
        super("com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            zzarx.zzc(parcel);
            zzf((ParcelFileDescriptor) zzarx.zza(parcel, ParcelFileDescriptor.CREATOR));
        } else if (r2 != 2) {
            return false;
        } else {
            zzarx.zzc(parcel);
            zze((com.google.android.gms.ads.internal.util.zzaz) zzarx.zza(parcel, com.google.android.gms.ads.internal.util.zzaz.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
