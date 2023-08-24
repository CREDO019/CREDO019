package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbsb extends zzarv implements zzbsd {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbsb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzbsd
    public final void zze(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzbsd
    public final void zzf() throws RemoteException {
        zzbl(1, zza());
    }
}
