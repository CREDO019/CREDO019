package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.ConditionVariable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzatq extends Thread {
    final /* synthetic */ AudioTrack zza;
    final /* synthetic */ zzaua zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzatq(zzaua zzauaVar, AudioTrack audioTrack) {
        this.zzb = zzauaVar;
        this.zza = audioTrack;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        ConditionVariable conditionVariable;
        try {
            this.zza.flush();
            this.zza.release();
        } finally {
            conditionVariable = this.zzb.zze;
            conditionVariable.open();
        }
    }
}
