package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfui extends zzfuk {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfui() {
        super(null);
    }

    static final zzfuk zzf(int r0) {
        zzfuk zzfukVar;
        zzfuk zzfukVar2;
        zzfuk zzfukVar3;
        if (r0 < 0) {
            zzfukVar3 = zzfuk.zzb;
            return zzfukVar3;
        } else if (r0 > 0) {
            zzfukVar2 = zzfuk.zzc;
            return zzfukVar2;
        } else {
            zzfukVar = zzfuk.zza;
            return zzfukVar;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfuk
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzfuk
    public final zzfuk zzb(int r1, int r2) {
        return zzf(r1 < r2 ? -1 : r1 > r2 ? 1 : 0);
    }

    @Override // com.google.android.gms.internal.ads.zzfuk
    public final zzfuk zzc(Object obj, Object obj2, Comparator comparator) {
        return zzf(comparator.compare(obj, obj2));
    }

    @Override // com.google.android.gms.internal.ads.zzfuk
    public final zzfuk zzd(boolean z, boolean z2) {
        return zzf(zzfwz.zza(z, z2));
    }

    @Override // com.google.android.gms.internal.ads.zzfuk
    public final zzfuk zze(boolean z, boolean z2) {
        return zzf(zzfwz.zza(false, false));
    }
}
