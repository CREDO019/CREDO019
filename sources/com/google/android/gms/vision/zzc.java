package com.google.android.gms.vision;

import android.util.SparseIntArray;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzc {
    private static final Object lock = new Object();
    private static int zzat;
    private final SparseIntArray zzau = new SparseIntArray();
    private final SparseIntArray zzav = new SparseIntArray();

    public final int zzb(int r4) {
        synchronized (lock) {
            int r1 = this.zzau.get(r4, -1);
            if (r1 != -1) {
                return r1;
            }
            int r12 = zzat;
            zzat = r12 + 1;
            this.zzau.append(r4, r12);
            this.zzav.append(r12, r4);
            return r12;
        }
    }

    public final int zzc(int r3) {
        int r32;
        synchronized (lock) {
            r32 = this.zzav.get(r3);
        }
        return r32;
    }
}
