package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzary extends zzarv implements zzasa {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzary(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.clearcut.IClearcut");
    }

    @Override // com.google.android.gms.internal.ads.zzasa
    public final void zze(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zza.writeString("GMA_SDK");
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzasa
    public final void zzf() throws RemoteException {
        zzbl(3, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzasa
    public final void zzg(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(7, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzasa
    public final void zzh(int[] r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeIntArray(null);
        zzbl(4, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzasa
    public final void zzi(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(0);
        zzbl(6, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzasa
    public final void zzj(byte[] bArr) throws RemoteException {
        Parcel zza = zza();
        zza.writeByteArray(bArr);
        zzbl(5, zza);
    }
}
