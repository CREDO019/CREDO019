package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Message;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzeg implements zzdm {
    private Message zza;
    private zzeh zzb;

    private zzeg() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeg(zzef zzefVar) {
    }

    private final void zzd() {
        this.zza = null;
        this.zzb = null;
        zzeh.zzk(this);
    }

    public final zzeg zzb(Message message, zzeh zzehVar) {
        this.zza = message;
        this.zzb = zzehVar;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdm
    public final void zza() {
        Message message = this.zza;
        Objects.requireNonNull(message);
        message.sendToTarget();
        zzd();
    }

    public final boolean zzc(Handler handler) {
        Message message = this.zza;
        Objects.requireNonNull(message);
        boolean sendMessageAtFrontOfQueue = handler.sendMessageAtFrontOfQueue(message);
        zzd();
        return sendMessageAtFrontOfQueue;
    }
}
