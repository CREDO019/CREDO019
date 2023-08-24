package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfyn {
    private final boolean zza;
    private final zzfuv zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfyn(boolean z, zzfuv zzfuvVar, zzfyl zzfylVar) {
        this.zza = z;
        this.zzb = zzfuvVar;
    }

    public final zzfyx zza(Callable callable, Executor executor) {
        return new zzfyb(this.zzb, this.zza, executor, callable);
    }
}
