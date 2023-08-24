package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaiv {
    public final int zza;
    public final long zzb;

    private zzaiv(int r1, long j) {
        this.zza = r1;
        this.zzb = j;
    }

    public static zzaiv zza(zzzg zzzgVar, zzed zzedVar) throws IOException {
        ((zzyv) zzzgVar).zzm(zzedVar.zzH(), 0, 8, false);
        zzedVar.zzF(0);
        return new zzaiv(zzedVar.zze(), zzedVar.zzq());
    }
}
