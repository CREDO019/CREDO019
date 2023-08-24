package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbj extends zzarv implements zzbl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoader");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final String zze() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final String zzf() throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final void zzg(zzl zzlVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzlVar);
        zzbl(1, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final void zzh(zzl zzlVar, int r3) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzlVar);
        zza.writeInt(r3);
        zzbl(5, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbl
    public final boolean zzi() throws RemoteException {
        Parcel zzbk = zzbk(3, zza());
        boolean zzh = zzarx.zzh(zzbk);
        zzbk.recycle();
        return zzh;
    }
}
