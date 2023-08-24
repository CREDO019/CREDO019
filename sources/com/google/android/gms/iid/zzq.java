package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
final class zzq implements Parcelable.Creator<MessengerCompat> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MessengerCompat[] newArray(int r1) {
        return new MessengerCompat[r1];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MessengerCompat createFromParcel(Parcel parcel) {
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder != null) {
            return new MessengerCompat(readStrongBinder);
        }
        return null;
    }
}
