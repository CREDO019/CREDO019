package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzblq extends zzarv implements zzbls {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzblq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    @Override // com.google.android.gms.internal.ads.zzbls
    public final String zzg() throws RemoteException {
        Parcel zzbk = zzbk(2, zza());
        String readString = zzbk.readString();
        zzbk.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.ads.zzbls
    public final List zzh() throws RemoteException {
        Parcel zzbk = zzbk(3, zza());
        ArrayList zzb = zzarx.zzb(zzbk);
        zzbk.recycle();
        return zzb;
    }
}
