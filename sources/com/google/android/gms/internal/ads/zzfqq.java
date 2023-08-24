package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfqq extends zzarv implements zzfqs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfqq(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.lmd.protocol.ILmdOverlayService");
    }

    @Override // com.google.android.gms.internal.ads.zzfqs
    public final void zze(Bundle bundle, zzfqu zzfquVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, bundle);
        zzarx.zzg(zza, zzfquVar);
        zzbm(2, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfqs
    public final void zzf(String str, Bundle bundle, zzfqu zzfquVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzarx.zze(zza, bundle);
        zzarx.zzg(zza, zzfquVar);
        zzbm(1, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfqs
    public final void zzg(Bundle bundle, zzfqu zzfquVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zze(zza, bundle);
        zzarx.zzg(zza, zzfquVar);
        zzbm(3, zza);
    }
}
