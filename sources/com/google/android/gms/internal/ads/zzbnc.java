package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbnc extends zzarv implements zzbne {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbnc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbne
    public final void zze(zzbmu zzbmuVar, String str) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbmuVar);
        zza.writeString(str);
        zzbl(1, zza);
    }
}
