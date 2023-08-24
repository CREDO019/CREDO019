package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbds implements BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzbdt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbds(zzbdt zzbdtVar) {
        this.zza = zzbdtVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Object obj;
        zzbdw zzbdwVar;
        Object obj2;
        obj = this.zza.zzc;
        synchronized (obj) {
            this.zza.zzf = null;
            zzbdt zzbdtVar = this.zza;
            zzbdwVar = zzbdtVar.zzd;
            if (zzbdwVar != null) {
                zzbdtVar.zzd = null;
            }
            obj2 = this.zza.zzc;
            obj2.notifyAll();
        }
    }
}
