package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzko {
    private final zzaa zza;
    private final SparseArray zzb;

    public zzko(zzaa zzaaVar, SparseArray sparseArray) {
        this.zza = zzaaVar;
        SparseArray sparseArray2 = new SparseArray(zzaaVar.zzb());
        for (int r1 = 0; r1 < zzaaVar.zzb(); r1++) {
            int zza = zzaaVar.zza(r1);
            zzkn zzknVar = (zzkn) sparseArray.get(zza);
            Objects.requireNonNull(zzknVar);
            sparseArray2.append(zza, zzknVar);
        }
        this.zzb = sparseArray2;
    }

    public final int zza(int r2) {
        return this.zza.zza(r2);
    }

    public final int zzb() {
        return this.zza.zzb();
    }

    public final zzkn zzc(int r2) {
        zzkn zzknVar = (zzkn) this.zzb.get(r2);
        Objects.requireNonNull(zzknVar);
        return zzknVar;
    }

    public final boolean zzd(int r2) {
        return this.zza.zzc(r2);
    }
}
