package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbrf extends zzbrc {
    final /* synthetic */ zzchf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbrf(zzbrg zzbrgVar, zzchf zzchfVar) {
        this.zza = zzchfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbrd
    public final void zzb(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        this.zza.zzd(parcelFileDescriptor);
    }
}
