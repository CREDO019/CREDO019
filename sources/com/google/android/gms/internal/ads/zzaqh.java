package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqh extends BroadcastReceiver {
    final /* synthetic */ zzaqi zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaqh(zzaqi zzaqiVar) {
        this.zza = zzaqiVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.zza.zzf();
    }
}
