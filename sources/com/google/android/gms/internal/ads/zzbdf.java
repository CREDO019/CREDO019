package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public interface zzbdf extends IInterface {
    com.google.android.gms.ads.internal.client.zzbs zze() throws RemoteException;

    com.google.android.gms.ads.internal.client.zzdh zzf() throws RemoteException;

    void zzg(boolean z) throws RemoteException;

    void zzh(com.google.android.gms.ads.internal.client.zzde zzdeVar) throws RemoteException;

    void zzi(IObjectWrapper iObjectWrapper, zzbdm zzbdmVar) throws RemoteException;

    void zzj(zzbdj zzbdjVar) throws RemoteException;
}
