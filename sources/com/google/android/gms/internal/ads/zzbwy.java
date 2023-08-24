package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public interface zzbwy extends IInterface {
    com.google.android.gms.ads.internal.client.zzdk zze() throws RemoteException;

    zzbxl zzf() throws RemoteException;

    zzbxl zzg() throws RemoteException;

    void zzh(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, com.google.android.gms.ads.internal.client.zzq zzqVar, zzbxb zzbxbVar) throws RemoteException;

    void zzi(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwm zzbwmVar, zzbvl zzbvlVar, com.google.android.gms.ads.internal.client.zzq zzqVar) throws RemoteException;

    void zzj(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwm zzbwmVar, zzbvl zzbvlVar, com.google.android.gms.ads.internal.client.zzq zzqVar) throws RemoteException;

    void zzk(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwp zzbwpVar, zzbvl zzbvlVar) throws RemoteException;

    void zzl(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbws zzbwsVar, zzbvl zzbvlVar) throws RemoteException;

    void zzm(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbws zzbwsVar, zzbvl zzbvlVar, zzblo zzbloVar) throws RemoteException;

    void zzn(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwv zzbwvVar, zzbvl zzbvlVar) throws RemoteException;

    void zzo(String str, String str2, com.google.android.gms.ads.internal.client.zzl zzlVar, IObjectWrapper iObjectWrapper, zzbwv zzbwvVar, zzbvl zzbvlVar) throws RemoteException;

    void zzp(String str) throws RemoteException;

    boolean zzq(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzr(IObjectWrapper iObjectWrapper) throws RemoteException;
}
