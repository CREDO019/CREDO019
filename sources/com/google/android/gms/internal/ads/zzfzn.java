package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfzn extends zzfye implements RunnableFuture {
    @CheckForNull
    private volatile zzfyw zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfzn(zzfxu zzfxuVar) {
        this.zza = new zzfzl(this, zzfxuVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfzn zzf(Runnable runnable, Object obj) {
        return new zzfzn(Executors.callable(runnable, obj));
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        zzfyw zzfywVar = this.zza;
        if (zzfywVar != null) {
            zzfywVar.run();
        }
        this.zza = null;
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    @CheckForNull
    protected final String zza() {
        zzfyw zzfywVar = this.zza;
        if (zzfywVar != null) {
            return "task=[" + zzfywVar + "]";
        }
        return super.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    protected final void zzb() {
        zzfyw zzfywVar;
        if (zzu() && (zzfywVar = this.zza) != null) {
            zzfywVar.zzh();
        }
        this.zza = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfzn(Callable callable) {
        this.zza = new zzfzm(this, callable);
    }
}
