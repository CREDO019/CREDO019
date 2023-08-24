package com.google.android.gms.internal.ads;

import android.view.Surface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbbe implements Runnable {
    final /* synthetic */ Surface zza;
    final /* synthetic */ zzbbg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbbe(zzbbg zzbbgVar, Surface surface) {
        this.zzb = zzbbgVar;
        this.zza = surface;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbbh zzbbhVar;
        zzbbhVar = this.zzb.zzb;
        zzbbhVar.zzm(this.zza);
    }
}
