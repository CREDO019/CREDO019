package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcau extends zzarv implements zzcaw {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcau(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.INonagonStreamingResponseListener");
    }

    @Override // com.google.android.gms.internal.ads.zzcaw
    public final void zze(com.google.android.gms.ads.internal.util.zzaz zzazVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzazVar);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcaw
    public final void zzf(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, parcelFileDescriptor);
        zzbl(1, zza);
    }
}
