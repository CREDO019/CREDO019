package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzp;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzg extends com.google.android.gms.internal.vision.zza implements zzh {
    public zzg() {
        super("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    @Override // com.google.android.gms.internal.vision.zza
    protected final boolean dispatchTransaction(int r2, Parcel parcel, Parcel parcel2, int r5) throws RemoteException {
        if (r2 == 1) {
            FaceParcel[] zzc = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzp) com.google.android.gms.internal.vision.zzd.zza(parcel, zzp.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedArray(zzc, 1);
        } else if (r2 == 2) {
            boolean zzd = zzd(parcel.readInt());
            parcel2.writeNoException();
            com.google.android.gms.internal.vision.zzd.writeBoolean(parcel2, zzd);
        } else if (r2 != 3) {
            return false;
        } else {
            zzm();
            parcel2.writeNoException();
        }
        return true;
    }
}
