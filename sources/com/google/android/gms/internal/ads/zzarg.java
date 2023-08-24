package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarg extends zzarm {
    private final zzaqi zzi;
    private long zzj;

    public zzarg(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13, zzaqi zzaqiVar) {
        super(zzaqbVar, "U3RL9mJLLQMse/MZqcg0oM/eA70gzw1xT+w0micdYsejvCU5HnK+5K0knH1wagPk", "3AJSAiPIa+BfzMb1UB6dOa0g/yKIYU+RvBwyuDyaJrc=", zzamhVar, r12, 53);
        this.zzi = zzaqiVar;
        if (zzaqiVar != null) {
            this.zzj = zzaqiVar.zza();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzi != null) {
            this.zze.zzO(((Long) this.zzf.invoke(null, Long.valueOf(this.zzj))).longValue());
        }
    }
}
