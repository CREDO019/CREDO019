package com.google.android.gms.internal.ads;

import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfxa extends zzfxb {
    public static int zza(long j) {
        int r0 = (int) j;
        if (r0 == j) {
            return r0;
        }
        throw new IllegalArgumentException(zzfsu.zzb("Out of range: %s", Long.valueOf(j)));
    }

    public static int zzb(int r0, int r1, int r2) {
        return Math.min(Math.max(r0, r1), (int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }
}
