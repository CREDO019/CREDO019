package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfmf {
    public static final /* synthetic */ int zza = 0;
    private static volatile int zzf = 1;
    private final Context zzb;
    private final Executor zzc;
    private final Task zzd;
    private final boolean zze;

    zzfmf(Context context, Executor executor, Task task, boolean z) {
        this.zzb = context;
        this.zzc = executor;
        this.zzd = task;
        this.zze = z;
    }

    public static zzfmf zza(final Context context, Executor executor, boolean z) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (z) {
            executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfmb
                @Override // java.lang.Runnable
                public final void run() {
                    taskCompletionSource.setResult(zzfoh.zzb(context, "GLAS", null));
                }
            });
        } else {
            executor.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfmc
                @Override // java.lang.Runnable
                public final void run() {
                    TaskCompletionSource.this.setResult(zzfoh.zzc());
                }
            });
        }
        return new zzfmf(context, executor, taskCompletionSource.getTask(), z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzg(int r0) {
        zzf = r0;
    }

    private final Task zzh(final int r2, long j, Exception exc, String str, Map map, String str2) {
        if (this.zze) {
            final zzalt zza2 = zzalx.zza();
            zza2.zza(this.zzb.getPackageName());
            zza2.zze(j);
            zza2.zzg(zzf);
            if (exc != null) {
                zza2.zzf(zzfsw.zza(exc));
                zza2.zzd(exc.getClass().getName());
            }
            if (str2 != null) {
                zza2.zzb(str2);
            }
            if (str != null) {
                zza2.zzc(str);
            }
            return this.zzd.continueWith(this.zzc, new Continuation() { // from class: com.google.android.gms.internal.ads.zzfme
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    zzalt zzaltVar = zzalt.this;
                    int r1 = r2;
                    int r22 = zzfmf.zza;
                    if (task.isSuccessful()) {
                        zzfog zza3 = ((zzfoh) task.getResult()).zza(((zzalx) zzaltVar.zzal()).zzaw());
                        zza3.zza(r1);
                        zza3.zzc();
                        return true;
                    }
                    return false;
                }
            });
        }
        return this.zzd.continueWith(this.zzc, new Continuation() { // from class: com.google.android.gms.internal.ads.zzfmd
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return Boolean.valueOf(task.isSuccessful());
            }
        });
    }

    public final Task zzb(int r9, String str) {
        return zzh(r9, 0L, null, null, null, str);
    }

    public final Task zzc(int r9, long j, Exception exc) {
        return zzh(r9, j, exc, null, null, null);
    }

    public final Task zzd(int r9, long j) {
        return zzh(r9, j, null, null, null, null);
    }

    public final Task zze(int r9, long j, String str) {
        return zzh(r9, j, null, null, null, str);
    }

    public final Task zzf(int r9, long j, String str, Map map) {
        return zzh(r9, j, null, str, null, null);
    }
}
