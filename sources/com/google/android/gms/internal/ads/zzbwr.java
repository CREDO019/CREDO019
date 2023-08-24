package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzbwr extends zzarw implements zzbws {
    public zzbwr() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzarw
    protected final boolean zzbI(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            zzbvu zzb = zzbvt.zzb(parcel.readStrongBinder());
            zzarx.zzc(parcel);
            zzg(zzb);
        } else if (r2 == 2) {
            String readString = parcel.readString();
            zzarx.zzc(parcel);
            zze(readString);
        } else if (r2 != 3) {
            return false;
        } else {
            zzarx.zzc(parcel);
            zzf((com.google.android.gms.ads.internal.client.zze) zzarx.zza(parcel, com.google.android.gms.ads.internal.client.zze.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
