package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfyz implements Executor {
    final /* synthetic */ Executor zza;
    final /* synthetic */ zzfxf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfyz(Executor executor, zzfxf zzfxfVar) {
        this.zza = executor;
        this.zzb = zzfxfVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        try {
            this.zza.execute(runnable);
        } catch (RejectedExecutionException e) {
            this.zzb.zze(e);
        }
    }
}
