package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzki extends BroadcastReceiver {
    final /* synthetic */ zzkj zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzki(zzkj zzkjVar, zzkh zzkhVar) {
        this.zza = zzkjVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        final zzkj zzkjVar = this.zza;
        zzkj.zzc(zzkjVar).post(new Runnable() { // from class: com.google.android.gms.internal.ads.zzkg
            @Override // java.lang.Runnable
            public final void run() {
                zzkj.zzd(zzkj.this);
            }
        });
    }
}
