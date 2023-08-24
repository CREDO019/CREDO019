package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbdr implements BaseGmsClient.BaseConnectionCallbacks {
    final /* synthetic */ zzbdt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbdr(zzbdt zzbdtVar) {
        this.zza = zzbdtVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        Object obj;
        Object obj2;
        zzbdw zzbdwVar;
        zzbdw zzbdwVar2;
        obj = this.zza.zzc;
        synchronized (obj) {
            try {
                zzbdt zzbdtVar = this.zza;
                zzbdwVar = zzbdtVar.zzd;
                if (zzbdwVar != null) {
                    zzbdwVar2 = zzbdtVar.zzd;
                    zzbdtVar.zzf = zzbdwVar2.zzq();
                }
            } catch (DeadObjectException e) {
                com.google.android.gms.ads.internal.util.zze.zzh("Unable to obtain a cache service instance.", e);
                zzbdt.zzh(this.zza);
            }
            obj2 = this.zza.zzc;
            obj2.notifyAll();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int r3) {
        Object obj;
        Object obj2;
        obj = this.zza.zzc;
        synchronized (obj) {
            this.zza.zzf = null;
            obj2 = this.zza.zzc;
            obj2.notifyAll();
        }
    }
}
