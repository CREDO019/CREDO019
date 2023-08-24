package com.google.android.gms.internal.ads;

import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfzk extends zzfye {
    @CheckForNull
    private zzfyx zza;
    @CheckForNull
    private ScheduledFuture zzb;

    private zzfzk(zzfyx zzfyxVar) {
        Objects.requireNonNull(zzfyxVar);
        this.zza = zzfyxVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfyx zzg(zzfyx zzfyxVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzfzk zzfzkVar = new zzfzk(zzfyxVar);
        zzfzi zzfziVar = new zzfzi(zzfzkVar);
        zzfzkVar.zzb = scheduledExecutorService.schedule(zzfziVar, j, timeUnit);
        zzfyxVar.zzc(zzfziVar, zzfyc.INSTANCE);
        return zzfzkVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ScheduledFuture zzx(zzfzk zzfzkVar, ScheduledFuture scheduledFuture) {
        zzfzkVar.zzb = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfxf
    @CheckForNull
    public final String zza() {
        zzfyx zzfyxVar = this.zza;
        ScheduledFuture scheduledFuture = this.zzb;
        if (zzfyxVar != null) {
            String str = "inputFuture=[" + zzfyxVar + "]";
            if (scheduledFuture != null) {
                long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
                if (delay > 0) {
                    return str + ", remaining delay=[" + delay + " ms]";
                }
                return str;
            }
            return str;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    protected final void zzb() {
        zzs(this.zza);
        ScheduledFuture scheduledFuture = this.zzb;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zza = null;
        this.zzb = null;
    }
}
