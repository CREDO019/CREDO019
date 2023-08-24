package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfhh {
    private static final zzfyx zza = zzfyo.zzi(null);
    private final zzfyy zzb;
    private final ScheduledExecutorService zzc;
    private final zzfhi zzd;

    public zzfhh(zzfyy zzfyyVar, ScheduledExecutorService scheduledExecutorService, zzfhi zzfhiVar) {
        this.zzb = zzfyyVar;
        this.zzc = scheduledExecutorService;
        this.zzd = zzfhiVar;
    }

    public final zzfgx zza(Object obj, zzfyx... zzfyxVarArr) {
        return new zzfgx(this, obj, Arrays.asList(zzfyxVarArr), null);
    }

    public final zzfhg zzb(Object obj, zzfyx zzfyxVar) {
        return new zzfhg(this, obj, zzfyxVar, Collections.singletonList(zzfyxVar), zzfyxVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract String zzf(Object obj);
}
