package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcf extends zzarw implements zzcg {
    public zzcf() {
        super("com.google.android.gms.ads.internal.client.IFullScreenContentCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        if (r1 == 1) {
            zzarx.zzc(parcel);
            zzd((zze) zzarx.zza(parcel, zze.CREATOR));
        } else if (r1 == 2) {
            zzf();
        } else if (r1 == 3) {
            zzc();
        } else if (r1 == 4) {
            zze();
        } else if (r1 != 5) {
            return false;
        } else {
            zzb();
        }
        parcel2.writeNoException();
        return true;
    }
}
