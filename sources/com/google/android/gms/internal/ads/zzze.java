package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzze implements zzaam {
    private final byte[] zza = new byte[4096];

    @Override // com.google.android.gms.internal.ads.zzaam
    public final /* synthetic */ int zze(zzr zzrVar, int r2, boolean z) {
        return zzaak.zza(this, zzrVar, r2, z);
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final int zzf(zzr zzrVar, int r3, boolean z, int r5) throws IOException {
        int zza = zzrVar.zza(this.zza, 0, Math.min(4096, r3));
        if (zza == -1) {
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final void zzk(zzaf zzafVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final /* synthetic */ void zzq(zzed zzedVar, int r2) {
        zzaak.zzb(this, zzedVar, r2);
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final void zzr(zzed zzedVar, int r2, int r3) {
        zzedVar.zzG(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final void zzs(long j, int r3, int r4, int r5, zzaal zzaalVar) {
    }
}
