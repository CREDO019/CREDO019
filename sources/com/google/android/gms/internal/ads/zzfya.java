package com.google.android.gms.internal.ads;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfya extends zzfyw {
    private final Executor zza;
    final /* synthetic */ zzfyb zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfya(zzfyb zzfybVar, Executor executor) {
        this.zzb = zzfybVar;
        Objects.requireNonNull(executor);
        this.zza = executor;
    }

    abstract void zzc(Object obj);

    @Override // com.google.android.gms.internal.ads.zzfyw
    final void zzd(Throwable th) {
        zzfyb.zzG(this.zzb, (zzfya) null);
        if (th instanceof ExecutionException) {
            this.zzb.zze(((ExecutionException) th).getCause());
        } else if (th instanceof CancellationException) {
            this.zzb.cancel(false);
        } else {
            this.zzb.zze(th);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyw
    final void zze(Object obj) {
        zzfyb.zzG(this.zzb, (zzfya) null);
        zzc(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf() {
        try {
            this.zza.execute(this);
        } catch (RejectedExecutionException e) {
            this.zzb.zze(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyw
    final boolean zzg() {
        return this.zzb.isDone();
    }
}
