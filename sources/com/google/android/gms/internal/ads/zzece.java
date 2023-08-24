package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzece extends zzcav {
    final /* synthetic */ zzecf zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzece(zzecf zzecfVar) {
        this.zza = zzecfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcaw
    public final void zze(com.google.android.gms.ads.internal.util.zzaz zzazVar) {
        this.zza.zza.zze(new com.google.android.gms.ads.internal.util.zzay(zzazVar.zza, zzazVar.zzb));
    }

    @Override // com.google.android.gms.internal.ads.zzcaw
    public final void zzf(ParcelFileDescriptor parcelFileDescriptor) {
        this.zza.zza.zzd(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor));
    }
}
