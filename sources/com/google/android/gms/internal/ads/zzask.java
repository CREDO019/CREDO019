package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzask extends Handler {
    final /* synthetic */ zzasl zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzask(zzasl zzaslVar, Looper looper) {
        super(looper);
        this.zza = zzaslVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zza.zzt(message);
    }
}
