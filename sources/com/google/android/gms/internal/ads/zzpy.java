package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpy extends Handler {
    final /* synthetic */ zzqa zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzpy(zzqa zzqaVar, Looper looper) {
        super(looper);
        this.zza = zzqaVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        zzqa.zza(this.zza, message);
    }
}
