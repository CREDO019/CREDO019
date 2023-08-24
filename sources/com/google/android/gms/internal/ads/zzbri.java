package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbri implements BaseGmsClient.BaseConnectionCallbacks {
    final /* synthetic */ zzchf zza;
    final /* synthetic */ zzbrk zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbri(zzbrk zzbrkVar, zzchf zzchfVar) {
        this.zzb = zzbrkVar;
        this.zza = zzchfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        zzbqx zzbqxVar;
        try {
            zzchf zzchfVar = this.zza;
            zzbqxVar = this.zzb.zza;
            zzchfVar.zzd(zzbqxVar.zzp());
        } catch (DeadObjectException e) {
            this.zza.zze(e);
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int r5) {
        zzchf zzchfVar = this.zza;
        zzchfVar.zze(new RuntimeException("onConnectionSuspended: " + r5));
    }
}
