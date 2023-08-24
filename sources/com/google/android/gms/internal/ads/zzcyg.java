package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzcyg implements Runnable {
    public final /* synthetic */ AtomicReference zza;

    public /* synthetic */ zzcyg(AtomicReference atomicReference) {
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcyi.zzi(this.zza);
    }
}
