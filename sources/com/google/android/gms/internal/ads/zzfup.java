package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfup {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int r1, int r2) {
        int r12 = r1 + (r1 >> 1) + 1;
        if (r12 < r2) {
            int highestOneBit = Integer.highestOneBit(r2 - 1);
            r12 = highestOneBit + highestOneBit;
        }
        if (r12 < 0) {
            return Integer.MAX_VALUE;
        }
        return r12;
    }

    public abstract zzfup zzb(Object obj);
}
