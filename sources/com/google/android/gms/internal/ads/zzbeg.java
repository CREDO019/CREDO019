package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbeg implements BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzchf zza;
    final /* synthetic */ zzbeh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbeg(zzbeh zzbehVar, zzchf zzchfVar) {
        this.zzb = zzbehVar;
        this.zza = zzchfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Object obj;
        obj = this.zzb.zzd;
        synchronized (obj) {
            this.zza.zze(new RuntimeException("Connection failed."));
        }
    }
}
