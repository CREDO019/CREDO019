package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public final class zabh extends com.google.android.gms.internal.base.zau {
    final /* synthetic */ zabi zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zabh(zabi zabiVar, Looper looper) {
        super(looper);
        this.zaa = zabiVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int r0 = message.what;
        if (r0 == 1) {
            ((zabg) message.obj).zab(this.zaa);
        } else if (r0 == 2) {
            throw ((RuntimeException) message.obj);
        } else {
            int r3 = message.what;
            Log.w("GACStateManager", "Unknown message id: " + r3);
        }
    }
}
