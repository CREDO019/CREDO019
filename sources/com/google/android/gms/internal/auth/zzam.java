package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* loaded from: classes2.dex */
public abstract class zzam extends zzb implements zzal {
    public zzam() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean dispatchTransaction(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            zza((ProxyResponse) zzc.zza(parcel, ProxyResponse.CREATOR));
        } else if (r2 != 2) {
            return false;
        } else {
            zzb(parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}
