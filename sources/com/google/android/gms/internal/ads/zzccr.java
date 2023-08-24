package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzccr extends zzarv implements IInterface {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzccr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.rewarded.client.IRewardedAdSkuListener");
    }

    public final void zze(zzccg zzccgVar, String str, String str2) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzccgVar);
        zza.writeString(str);
        zza.writeString(str2);
        zzbl(2, zza);
    }
}
