package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzarx;
import com.google.android.gms.internal.ads.zzbve;
import com.google.android.gms.internal.ads.zzbvf;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzch extends zzarv implements zzcj {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzch(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.ILiteSdkInfo");
    }

    @Override // com.google.android.gms.ads.internal.client.zzcj
    public final zzbvf getAdapterCreator() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        zzbvf zzf = zzbve.zzf(zzbk.readStrongBinder());
        zzbk.recycle();
        return zzf;
    }

    @Override // com.google.android.gms.ads.internal.client.zzcj
    public final zzeh getLiteSdkVersion() throws RemoteException {
        Parcel zzbk = zzbk(1, zza());
        zzeh zzehVar = (zzeh) zzarx.zza(zzbk, zzeh.CREATOR);
        zzbk.recycle();
        return zzehVar;
    }
}
