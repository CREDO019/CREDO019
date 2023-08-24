package com.google.android.gms.internal.ads;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbrj implements BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzchf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbrj(zzbrk zzbrkVar, zzchf zzchfVar) {
        this.zza = zzchfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zza.zze(new RuntimeException("Connection failed."));
    }
}
