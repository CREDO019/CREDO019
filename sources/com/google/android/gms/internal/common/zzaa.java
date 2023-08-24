package com.google.android.gms.internal.common;

import java.util.Arrays;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes.dex */
class zzaa extends zzab {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(int r1) {
    }

    private final void zzb(int r5) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length >= r5) {
            if (this.zzc) {
                this.zza = (Object[]) objArr.clone();
                this.zzc = false;
                return;
            }
            return;
        }
        int r1 = length + (length >> 1) + 1;
        if (r1 < r5) {
            int highestOneBit = Integer.highestOneBit(r5 - 1);
            r1 = highestOneBit + highestOneBit;
        }
        if (r1 < 0) {
            r1 = Integer.MAX_VALUE;
        }
        this.zza = Arrays.copyOf(objArr, r1);
        this.zzc = false;
    }

    public final zzaa zza(Object obj) {
        Objects.requireNonNull(obj);
        zzb(this.zzb + 1);
        Object[] objArr = this.zza;
        int r1 = this.zzb;
        this.zzb = r1 + 1;
        objArr[r1] = obj;
        return this;
    }
}
