package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgi extends BroadcastReceiver implements Runnable {
    final /* synthetic */ zzgk zza;
    private final zzgj zzb;
    private final Handler zzc;

    public zzgi(zzgk zzgkVar, Handler handler, zzgj zzgjVar) {
        this.zza = zzgkVar;
        this.zzc = handler;
        this.zzb = zzgjVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            this.zzc.post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
    }
}
