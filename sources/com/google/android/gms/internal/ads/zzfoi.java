package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfoi extends zzarv implements zzfok {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfoi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.clearcut.IGassClearcut");
    }

    @Override // com.google.android.gms.internal.ads.zzfok
    public final void zze(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, iObjectWrapper);
        zza.writeString(str);
        zza.writeString(null);
        zzbl(8, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfok
    public final void zzf() throws RemoteException {
        zzbl(3, zza());
    }

    @Override // com.google.android.gms.internal.ads.zzfok
    public final void zzg(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(7, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfok
    public final void zzh(int[] r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeIntArray(null);
        zzbl(4, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfok
    public final void zzi(int r2) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(r2);
        zzbl(6, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfok
    public final void zzj(byte[] bArr) throws RemoteException {
        Parcel zza = zza();
        zza.writeByteArray(bArr);
        zzbl(5, zza);
    }
}
