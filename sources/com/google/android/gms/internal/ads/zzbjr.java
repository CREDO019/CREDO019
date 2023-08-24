package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbjr extends zzarv implements zzbjt {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbjr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.customrenderedad.client.IOnCustomRenderedAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbjt
    public final void zze(zzbjq zzbjqVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbjqVar);
        zzbl(1, zza);
    }
}
