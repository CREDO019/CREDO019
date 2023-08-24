package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbnm extends zzarv implements zzbno {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbnm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnUnifiedNativeAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbno
    public final void zze(zzbnx zzbnxVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbnxVar);
        zzbl(1, zza);
    }
}
