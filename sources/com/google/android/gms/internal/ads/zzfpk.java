package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfpk {
    public static zzfyx zza(Task task) {
        final zzfpj zzfpjVar = new zzfpj(task);
        task.addOnCompleteListener(zzfze.zzb(), new OnCompleteListener() { // from class: com.google.android.gms.internal.ads.zzfpi
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                zzfpj zzfpjVar2 = zzfpj.this;
                if (task2.isCanceled()) {
                    zzfpjVar2.cancel(false);
                } else if (task2.isSuccessful()) {
                    zzfpjVar2.zzd(task2.getResult());
                } else {
                    Exception exception = task2.getException();
                    if (exception != null) {
                        zzfpjVar2.zze(exception);
                        return;
                    }
                    throw new IllegalStateException();
                }
            }
        });
        return zzfpjVar;
    }
}
