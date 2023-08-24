package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcy {
    public static final zzcy zza = new zzcy(zzfuv.zzo());
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzcv
    };
    private final zzfuv zzc;

    public zzcy(List list) {
        this.zzc = zzfuv.zzm(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.zzc.equals(((zzcy) obj).zzc);
    }

    public final int hashCode() {
        return this.zzc.hashCode();
    }

    public final zzfuv zza() {
        return this.zzc;
    }

    public final boolean zzb(int r5) {
        for (int r1 = 0; r1 < this.zzc.size(); r1++) {
            zzcx zzcxVar = (zzcx) this.zzc.get(r1);
            if (zzcxVar.zzc() && zzcxVar.zza() == r5) {
                return true;
            }
        }
        return false;
    }
}
