package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbyr extends zzarv implements zzbyt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbyr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.offline.IOfflineUtilsCreator");
    }

    @Override // com.google.android.gms.internal.ads.zzbyt
    public final zzbyq zze(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r4) throws RemoteException {
        zzbyq zzbyoVar;
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzbvfVar);
        zza.writeInt(ModuleDescriptor.MODULE_VERSION);
        Parcel zzbk = zzbk(1, zza);
        IBinder readStrongBinder = zzbk.readStrongBinder();
        if (readStrongBinder == null) {
            zzbyoVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.ads.internal.offline.IOfflineUtils");
            zzbyoVar = queryLocalInterface instanceof zzbyq ? (zzbyq) queryLocalInterface : new zzbyo(readStrongBinder);
        }
        zzbk.recycle();
        return zzbyoVar;
    }
}
