package com.google.android.gms.internal.ads;

import android.util.SparseBooleanArray;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaa {
    private final SparseBooleanArray zza;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzaa) {
            zzaa zzaaVar = (zzaa) obj;
            if (zzel.zza < 24) {
                if (this.zza.size() == zzaaVar.zza.size()) {
                    for (int r1 = 0; r1 < this.zza.size(); r1++) {
                        if (zza(r1) != zzaaVar.zza(r1)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return this.zza.equals(zzaaVar.zza);
        }
        return false;
    }

    public final int hashCode() {
        if (zzel.zza < 24) {
            int size = this.zza.size();
            for (int r1 = 0; r1 < this.zza.size(); r1++) {
                size = (size * 31) + zza(r1);
            }
            return size;
        }
        return this.zza.hashCode();
    }

    public final int zza(int r3) {
        zzdd.zza(r3, 0, this.zza.size());
        return this.zza.keyAt(r3);
    }

    public final int zzb() {
        return this.zza.size();
    }

    public final boolean zzc(int r2) {
        return this.zza.get(r2);
    }
}
