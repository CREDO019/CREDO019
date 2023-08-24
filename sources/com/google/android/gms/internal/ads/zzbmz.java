package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbmz extends zzarv implements zzbnb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbmz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnContentAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbnb
    public final void zze(zzbmr zzbmrVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbmrVar);
        zzbl(1, zza);
    }
}
