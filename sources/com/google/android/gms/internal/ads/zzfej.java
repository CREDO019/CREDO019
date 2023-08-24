package com.google.android.gms.internal.ads;

import java.util.Deque;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfej {
    private final Deque zza = new LinkedBlockingDeque();
    private final Callable zzb;
    private final zzfyy zzc;

    public zzfej(Callable callable, zzfyy zzfyyVar) {
        this.zzb = callable;
        this.zzc = zzfyyVar;
    }

    public final synchronized zzfyx zza() {
        zzc(1);
        return (zzfyx) this.zza.poll();
    }

    public final synchronized void zzb(zzfyx zzfyxVar) {
        this.zza.addFirst(zzfyxVar);
    }

    public final synchronized void zzc(int r5) {
        int size = r5 - this.zza.size();
        for (int r0 = 0; r0 < size; r0++) {
            this.zza.add(this.zzc.zzb(this.zzb));
        }
    }
}
