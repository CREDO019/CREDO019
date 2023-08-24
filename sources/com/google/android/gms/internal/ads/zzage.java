package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzage {
    public int zza;
    public long zzb;
    public int zzc;
    public int zzd;
    public int zze;
    public final int[] zzf = new int[255];
    private final zzed zzg = new zzed(255);

    public final void zza() {
        this.zza = 0;
        this.zzb = 0L;
        this.zzc = 0;
        this.zzd = 0;
        this.zze = 0;
    }

    public final boolean zzb(zzzg zzzgVar, boolean z) throws IOException {
        zza();
        this.zzg.zzC(27);
        if (zzzj.zzc(zzzgVar, this.zzg.zzH(), 0, 27, z) && this.zzg.zzs() == 1332176723) {
            if (this.zzg.zzk() != 0) {
                if (z) {
                    return false;
                }
                throw zzbu.zzc("unsupported bit stream revision");
            }
            this.zza = this.zzg.zzk();
            this.zzb = this.zzg.zzp();
            this.zzg.zzq();
            this.zzg.zzq();
            this.zzg.zzq();
            int zzk = this.zzg.zzk();
            this.zzc = zzk;
            this.zzd = zzk + 27;
            this.zzg.zzC(zzk);
            if (zzzj.zzc(zzzgVar, this.zzg.zzH(), 0, this.zzc, z)) {
                for (int r2 = 0; r2 < this.zzc; r2++) {
                    this.zzf[r2] = this.zzg.zzk();
                    this.zze += this.zzf[r2];
                }
                return true;
            }
        }
        return false;
    }

    public final boolean zzc(zzzg zzzgVar, long j) throws IOException {
        int r0;
        zzdd.zzd(zzzgVar.zzf() == zzzgVar.zze());
        this.zzg.zzC(4);
        while (true) {
            r0 = (j > (-1L) ? 1 : (j == (-1L) ? 0 : -1));
            if ((r0 == 0 || zzzgVar.zzf() + 4 < j) && zzzj.zzc(zzzgVar, this.zzg.zzH(), 0, 4, true)) {
                this.zzg.zzF(0);
                if (this.zzg.zzs() != 1332176723) {
                    ((zzyv) zzzgVar).zzo(1, false);
                } else {
                    zzzgVar.zzj();
                    return true;
                }
            }
        }
        do {
            if (r0 != 0 && zzzgVar.zzf() >= j) {
                break;
            }
        } while (zzzgVar.zzc(1) != -1);
        return false;
    }
}
