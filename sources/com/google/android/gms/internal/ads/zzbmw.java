package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbmw extends zzarv implements zzbmy {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbmw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnAppInstallAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbmy
    public final void zze(zzbmp zzbmpVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbmpVar);
        zzbl(1, zza);
    }
}
