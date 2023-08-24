package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaib implements zzyq {
    private final zzej zza;
    private final zzed zzb = new zzed();
    private final int zzc;

    public zzaib(int r1, zzej zzejVar, int r3) {
        this.zzc = r1;
        this.zza = zzejVar;
    }

    @Override // com.google.android.gms.internal.ads.zzyq
    public final zzyp zza(zzzg zzzgVar, long j) throws IOException {
        int zza;
        int zza2;
        long j2;
        long zzf = zzzgVar.zzf();
        int min = (int) Math.min(112800L, zzzgVar.zzd() - zzf);
        this.zzb.zzC(min);
        ((zzyv) zzzgVar).zzm(this.zzb.zzH(), 0, min, false);
        zzed zzedVar = this.zzb;
        int zzd = zzedVar.zzd();
        long j3 = -1;
        long j4 = -1;
        long j5 = -9223372036854775807L;
        while (zzedVar.zza() >= 188 && (zza2 = (zza = zzain.zza(zzedVar.zzH(), zzedVar.zzc(), zzd)) + 188) <= zzd) {
            long zzb = zzain.zzb(zzedVar, zza, this.zzc);
            if (zzb != C1856C.TIME_UNSET) {
                long zzb2 = this.zza.zzb(zzb);
                if (zzb2 > j) {
                    if (j5 == C1856C.TIME_UNSET) {
                        return zzyp.zzd(zzb2, zzf);
                    }
                    j2 = zzf + j4;
                } else if (100000 + zzb2 > j) {
                    j2 = zzf + zza;
                } else {
                    j4 = zza;
                    j5 = zzb2;
                }
                return zzyp.zze(j2);
            }
            zzedVar.zzF(zza2);
            j3 = zza2;
        }
        return j5 != C1856C.TIME_UNSET ? zzyp.zzf(j5, zzf + j3) : zzyp.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzyq
    public final void zzb() {
        zzed zzedVar = this.zzb;
        byte[] bArr = zzel.zzf;
        int length = bArr.length;
        zzedVar.zzD(bArr, 0);
    }
}
