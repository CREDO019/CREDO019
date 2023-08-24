package com.google.android.gms.ads.internal.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzarv;
import com.google.android.gms.internal.ads.zzarx;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbg extends zzarv implements zzbi {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoadCallback");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbi
    public final void zzb(zze zzeVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, zzeVar);
        zzbl(2, zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbi
    public final void zzc() throws RemoteException {
        zzbl(1, zza());
    }
}
