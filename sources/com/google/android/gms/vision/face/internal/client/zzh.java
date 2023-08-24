package com.google.android.gms.vision.face.internal.client;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzp;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public interface zzh extends IInterface {
    FaceParcel[] zzc(IObjectWrapper iObjectWrapper, zzp zzpVar) throws RemoteException;

    boolean zzd(int r1) throws RemoteException;

    void zzm() throws RemoteException;
}
