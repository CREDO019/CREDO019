package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfgz {
    public static final zzfhg zza(Callable callable, Object obj, zzfhh zzfhhVar) {
        zzfyy zzfyyVar;
        zzfyyVar = zzfhhVar.zzb;
        return zzb(callable, zzfyyVar, obj, zzfhhVar);
    }

    public static final zzfhg zzb(Callable callable, zzfyy zzfyyVar, Object obj, zzfhh zzfhhVar) {
        zzfyx zzfyxVar;
        zzfyxVar = zzfhh.zza;
        return new zzfhg(zzfhhVar, obj, zzfyxVar, Collections.emptyList(), zzfyyVar.zzb(callable));
    }

    public static final zzfhg zzc(zzfyx zzfyxVar, Object obj, zzfhh zzfhhVar) {
        zzfyx zzfyxVar2;
        zzfyxVar2 = zzfhh.zza;
        return new zzfhg(zzfhhVar, obj, zzfyxVar2, Collections.emptyList(), zzfyxVar);
    }

    public static final zzfhg zzd(final zzfgt zzfgtVar, zzfyy zzfyyVar, Object obj, zzfhh zzfhhVar) {
        return zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzfgy
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzfgt.this.zza();
                return null;
            }
        }, zzfyyVar, obj, zzfhhVar);
    }
}
