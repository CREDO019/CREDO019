package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbli extends zzarv implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbli(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.flags.IFlagRetrieverSupplierProxy");
    }

    public final void zze(zzcak zzcakVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzcakVar);
        zzbl(1, zza);
    }
}
