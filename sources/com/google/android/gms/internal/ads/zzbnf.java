package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbnf extends zzarv implements zzbnh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbnf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnCustomTemplateAdLoadedListener");
    }

    @Override // com.google.android.gms.internal.ads.zzbnh
    public final void zze(zzbmu zzbmuVar) throws RemoteException {
        Parcel zza = zza();
        zzarx.zzg(zza, zzbmuVar);
        zzbl(1, zza);
    }
}
