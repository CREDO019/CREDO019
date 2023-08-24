package com.google.android.gms.internal.ads;

import android.os.Handler;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzwb {
    private final Handler zza;
    private final zzwd zzb;
    private boolean zzc;

    public zzwb(Handler handler, zzwd zzwdVar) {
        this.zza = handler;
        this.zzb = zzwdVar;
    }

    public final void zzc() {
        this.zzc = true;
    }
}
