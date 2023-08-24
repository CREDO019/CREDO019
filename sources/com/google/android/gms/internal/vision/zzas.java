package com.google.android.gms.internal.vision;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzas extends ContentObserver {
    private final /* synthetic */ zzaq zzfq;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzas(zzaq zzaqVar, Handler handler) {
        super(null);
        this.zzfq = zzaqVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zzfq.zzv();
    }
}
