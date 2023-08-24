package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.IInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfra extends zzfqw {
    final /* synthetic */ zzfrg zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfra(zzfrg zzfrgVar) {
        this.zza = zzfrgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfqw
    public final void zza() {
        IInterface iInterface;
        zzfqv zzfqvVar;
        Context context;
        ServiceConnection serviceConnection;
        zzfrg zzfrgVar = this.zza;
        iInterface = zzfrgVar.zzn;
        if (iInterface != null) {
            zzfqvVar = zzfrgVar.zzc;
            zzfqvVar.zzd("Unbind from service.", new Object[0]);
            zzfrg zzfrgVar2 = this.zza;
            context = zzfrgVar2.zzb;
            serviceConnection = zzfrgVar2.zzm;
            context.unbindService(serviceConnection);
            this.zza.zzh = false;
            this.zza.zzn = null;
            this.zza.zzm = null;
        }
        this.zza.zzt();
    }
}
