package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxm implements zzaym {
    private final zzaym[] zza;

    public zzaxm(zzaym[] zzaymVarArr) {
        this.zza = zzaymVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzaym
    public final long zza() {
        long j = Long.MAX_VALUE;
        for (zzaym zzaymVar : this.zza) {
            long zza = zzaymVar.zza();
            if (zza != Long.MIN_VALUE) {
                j = Math.min(j, zza);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzaym
    public final boolean zzbj(long j) {
        zzaym[] zzaymVarArr;
        boolean z;
        boolean z2 = false;
        do {
            long zza = zza();
            if (zza == Long.MIN_VALUE) {
                break;
            }
            z = false;
            for (zzaym zzaymVar : this.zza) {
                if (zzaymVar.zza() == zza) {
                    z |= zzaymVar.zzbj(j);
                }
            }
            z2 |= z;
        } while (z);
        return z2;
    }
}
