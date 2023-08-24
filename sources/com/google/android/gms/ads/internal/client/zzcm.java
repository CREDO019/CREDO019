package com.google.android.gms.ads.internal.client;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzbrs;
import com.google.android.gms.internal.ads.zzbvf;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public interface zzcm extends IInterface {
    float zze() throws RemoteException;

    String zzf() throws RemoteException;

    List zzg() throws RemoteException;

    void zzh(String str) throws RemoteException;

    void zzi() throws RemoteException;

    void zzj() throws RemoteException;

    void zzk(String str, IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzl(zzcy zzcyVar) throws RemoteException;

    void zzm(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zzn(zzbvf zzbvfVar) throws RemoteException;

    void zzo(boolean z) throws RemoteException;

    void zzp(float f) throws RemoteException;

    void zzq(String str) throws RemoteException;

    void zzr(zzbrs zzbrsVar) throws RemoteException;

    void zzs(zzez zzezVar) throws RemoteException;

    boolean zzt() throws RemoteException;
}
