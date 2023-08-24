package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbh extends zzarw implements zzbi {
    public zzbh() {
        super("com.google.android.gms.ads.internal.client.IAdLoadCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            zzc();
        } else if (r2 != 2) {
            return false;
        } else {
            zzarx.zzc(parcel);
            zzb((zze) zzarx.zza(parcel, zze.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
