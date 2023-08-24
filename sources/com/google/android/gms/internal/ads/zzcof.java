package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcof extends zzarv implements zzcoh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcof(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.measurement.IMeasurementManager");
    }

    @Override // com.google.android.gms.internal.ads.zzcoh
    public final void zze(IObjectWrapper iObjectWrapper, zzcoe zzcoeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zzarx.zzg(zza, zzcoeVar);
        zzbl(2, zza);
    }
}
