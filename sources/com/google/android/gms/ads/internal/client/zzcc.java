package com.google.android.gms.ads.internal.client;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzbme;
import com.google.android.gms.internal.ads.zzbmk;
import com.google.android.gms.internal.ads.zzbqm;
import com.google.android.gms.internal.ads.zzbqp;
import com.google.android.gms.internal.ads.zzbvf;
import com.google.android.gms.internal.ads.zzbyq;
import com.google.android.gms.internal.ads.zzbza;
import com.google.android.gms.internal.ads.zzcbt;
import com.google.android.gms.internal.ads.zzccj;
import com.google.android.gms.internal.ads.zzcfe;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public interface zzcc extends IInterface {
    zzbo zzb(IObjectWrapper iObjectWrapper, String str, zzbvf zzbvfVar, int r4) throws RemoteException;

    zzbs zzc(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbvf zzbvfVar, int r5) throws RemoteException;

    zzbs zzd(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbvf zzbvfVar, int r5) throws RemoteException;

    zzbs zze(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, zzbvf zzbvfVar, int r5) throws RemoteException;

    zzbs zzf(IObjectWrapper iObjectWrapper, zzq zzqVar, String str, int r4) throws RemoteException;

    zzcm zzg(IObjectWrapper iObjectWrapper, int r2) throws RemoteException;

    zzbme zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    zzbmk zzi(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    zzbqp zzj(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3, zzbqm zzbqmVar) throws RemoteException;

    zzbyq zzk(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) throws RemoteException;

    zzbza zzl(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzcbt zzm(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) throws RemoteException;

    zzccj zzn(IObjectWrapper iObjectWrapper, String str, zzbvf zzbvfVar, int r4) throws RemoteException;

    zzcfe zzo(IObjectWrapper iObjectWrapper, zzbvf zzbvfVar, int r3) throws RemoteException;
}
