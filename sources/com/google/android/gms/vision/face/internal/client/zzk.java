package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-vision@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzk extends com.google.android.gms.internal.vision.zzb implements zzi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    @Override // com.google.android.gms.vision.face.internal.client.zzi
    public final zzh newFaceDetector(IObjectWrapper iObjectWrapper, zzf zzfVar) throws RemoteException {
        zzh zzjVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.vision.zzd.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.vision.zzd.zza(obtainAndWriteInterfaceToken, zzfVar);
        Parcel zza = zza(1, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzjVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            if (queryLocalInterface instanceof zzh) {
                zzjVar = (zzh) queryLocalInterface;
            } else {
                zzjVar = new zzj(readStrongBinder);
            }
        }
        zza.recycle();
        return zzjVar;
    }
}
