package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzca {
    private final zzy zza = new zzy();

    public final zzca zza(int r2) {
        this.zza.zza(r2);
        return this;
    }

    public final zzca zzb(zzcc zzccVar) {
        zzaa zzaaVar;
        zzy zzyVar = this.zza;
        zzaaVar = zzccVar.zzc;
        for (int r1 = 0; r1 < zzaaVar.zzb(); r1++) {
            zzyVar.zza(zzaaVar.zza(r1));
        }
        return this;
    }

    public final zzca zzc(int... r4) {
        zzy zzyVar = this.zza;
        for (int r1 = 0; r1 < 21; r1++) {
            zzyVar.zza(r4[r1]);
        }
        return this;
    }

    public final zzca zzd(int r2, boolean z) {
        zzy zzyVar = this.zza;
        if (z) {
            zzyVar.zza(r2);
        }
        return this;
    }

    public final zzcc zze() {
        return new zzcc(this.zza.zzb(), null);
    }
}
