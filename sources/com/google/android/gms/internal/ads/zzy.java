package com.google.android.gms.internal.ads;

import android.util.SparseBooleanArray;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzy {
    private final SparseBooleanArray zza = new SparseBooleanArray();
    private boolean zzb;

    public final zzy zza(int r3) {
        zzdd.zzf(!this.zzb);
        this.zza.append(r3, true);
        return this;
    }

    public final zzaa zzb() {
        zzdd.zzf(!this.zzb);
        this.zzb = true;
        return new zzaa(this.zza, null);
    }
}
