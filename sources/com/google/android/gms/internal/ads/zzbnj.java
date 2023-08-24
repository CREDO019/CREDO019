package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbnj extends zzarv implements zzbnl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbnj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnPublisherAdViewLoadedListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbnl
    public final void zze(com.google.android.gms.ads.internal.client.zzbs zzbsVar, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbsVar);
        zzarx.zzg(zza, iObjectWrapper);
        zzbl(1, zza);
    }
}
