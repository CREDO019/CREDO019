package com.google.android.gms.internal.ads;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfxl extends zzfye implements Runnable {
    public static final /* synthetic */ int zzc = 0;
    @CheckForNull
    zzfyx zza;
    @CheckForNull
    Object zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfxl(zzfyx zzfyxVar, Object obj) {
        Objects.requireNonNull(zzfyxVar);
        this.zza = zzfyxVar;
        Objects.requireNonNull(obj);
        this.zzb = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfyx zzfyxVar = this.zza;
        Object obj = this.zzb;
        if ((isCancelled() | (zzfyxVar == null)) || (obj == null)) {
            return;
        }
        this.zza = null;
        if (zzfyxVar.isCancelled()) {
            zzt(zzfyxVar);
            return;
        }
        try {
            try {
                Object zzf = zzf(obj, zzfyo.zzp(zzfyxVar));
                this.zzb = null;
                zzg(zzf);
            } catch (Throwable th) {
                try {
                    zzfzf.zza(th);
                    zze(th);
                } finally {
                    this.zzb = null;
                }
            }
        } catch (Error e) {
            zze(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e2) {
            zze(e2);
        } catch (ExecutionException e3) {
            zze(e3.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzfxf
    @CheckForNull
    public final String zza() {
        String str;
        zzfyx zzfyxVar = this.zza;
        Object obj = this.zzb;
        String zza = super.zza();
        if (zzfyxVar != null) {
            str = "inputFuture=[" + zzfyxVar + "], ";
        } else {
            str = "";
        }
        if (obj == null) {
            if (zza != null) {
                return str.concat(zza);
            }
            return null;
        }
        return str + "function=[" + obj + "]";
    }

    @Override // com.google.android.gms.internal.ads.zzfxf
    protected final void zzb() {
        zzs(this.zza);
        this.zza = null;
        this.zzb = null;
    }

    abstract Object zzf(Object obj, Object obj2) throws Exception;

    abstract void zzg(Object obj);
}
