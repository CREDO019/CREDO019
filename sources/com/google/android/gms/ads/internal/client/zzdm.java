package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzdm extends zzarw implements zzdn {
    public zzdm() {
        super("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            zzi();
        } else if (r2 == 2) {
            zzh();
        } else if (r2 == 3) {
            zzg();
        } else if (r2 == 4) {
            zze();
        } else if (r2 != 5) {
            return false;
        } else {
            boolean zzh = zzarx.zzh(parcel);
            zzarx.zzc(parcel);
            zzf(zzh);
        }
        parcel2.writeNoException();
        return true;
    }
}
