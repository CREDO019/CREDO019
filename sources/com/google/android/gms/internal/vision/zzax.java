package com.google.android.gms.internal.vision;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzax extends ContentObserver {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(zzav zzavVar, Handler handler) {
        super(null);
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        zzbe.zzab();
    }
}
