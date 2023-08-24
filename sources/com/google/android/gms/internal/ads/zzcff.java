package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcff extends zzarv implements zzcfh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcff(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.signals.ISignalGeneratorCreator");
    }

    @Override // com.google.android.gms.internal.ads.zzcfh
    public final zzcfe zze(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r4) throws RemoteException {
        zzcfe zzcfcVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(2, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzcfcVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.signals.ISignalGenerator");
            zzcfcVar = queryLocalInterface instanceof zzcfe ? (zzcfe) queryLocalInterface : new zzcfc(readStrongBinder);
        }
        zzbk.recycle();
        return zzcfcVar;
    }
}
