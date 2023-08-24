package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public abstract class zzb extends com.google.android.gms.internal.auth.zzb implements zza {
    public zzb() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean dispatchTransaction(int r1, Parcel parcel, Parcel parcel2, int r4) throws RemoteException {
        if (r1 == 1) {
            zzc((Account) com.google.android.gms.internal.auth.zzc.zza(parcel, Account.CREATOR));
        } else if (r1 != 2) {
            return false;
        } else {
            zza(com.google.android.gms.internal.auth.zzc.zza(parcel));
        }
        return true;
    }
}
