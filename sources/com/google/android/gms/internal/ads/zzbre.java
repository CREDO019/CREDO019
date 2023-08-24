package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbre extends zzarv implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbre(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.httpcache.IHttpAssetsCacheService");
    }

    public final void zze(zzbqy zzbqyVar, zzbrd zzbrdVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzbqyVar);
        zzarx.zzg(zza, zzbrdVar);
        zzbm(2, zza);
    }
}
