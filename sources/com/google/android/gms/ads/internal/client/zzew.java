package com.google.android.gms.ads.internal.client;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzccg;
import com.google.android.gms.internal.ads.zzcci;
import com.google.android.gms.internal.ads.zzccm;
import com.google.android.gms.internal.ads.zzccq;
import com.google.android.gms.internal.ads.zzccr;
import com.google.android.gms.internal.ads.zzccx;
import com.google.android.gms.internal.ads.zzcgg;
import com.google.android.gms.internal.ads.zzcgn;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzew extends zzcci {
    private static void zzr(final zzccq zzccqVar) {
        zzcgn.zzg("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzcgg.zza.post(new Runnable() { // from class: com.google.android.gms.ads.internal.client.zzev
            @Override // java.lang.Runnable
            public final void run() {
                zzccq zzccqVar2 = zzccq.this;
                if (zzccqVar2 != null) {
                    try {
                        zzccqVar2.zze(1);
                    } catch (RemoteException e) {
                        zzcgn.zzl("#007 Could not call remote method.", e);
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final Bundle zzb() throws RemoteException {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final zzdh zzc() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final zzccg zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final String zze() throws RemoteException {
        return "";
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzf(zzl zzlVar, zzccq zzccqVar) throws RemoteException {
        zzr(zzccqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzg(zzl zzlVar, zzccq zzccqVar) throws RemoteException {
        zzr(zzccqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzh(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzi(zzdb zzdbVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzj(zzde zzdeVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzk(zzccm zzccmVar) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzl(zzccx zzccxVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzn(IObjectWrapper iObjectWrapper, boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final boolean zzo() throws RemoteException {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzccj
    public final void zzp(zzccr zzccrVar) throws RemoteException {
    }
}
