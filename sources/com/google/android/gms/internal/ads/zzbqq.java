package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbqq extends zzarv implements zzbqs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbqq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.h5.client.IH5AdsManagerCreator");
    }

    @Override // com.google.android.gms.internal.ads.zzbqs
    public final zzbqp zze(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3, zzbqm zzbqmVar) throws RemoteException {
        zzbqp zzbqnVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        zzarx.zzg(zza, zzbqmVar);
        Parcel zzbk = zzbk(1, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbqnVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.h5.client.IH5AdsManager");
            zzbqnVar = queryLocalInterface instanceof zzbqp ? (zzbqp) queryLocalInterface : new zzbqn(readStrongBinder);
        }
        zzbk.recycle();
        return zzbqnVar;
    }
}
