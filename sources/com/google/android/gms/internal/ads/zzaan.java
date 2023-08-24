package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaan {
    private final byte[] zza = new byte[10];
    private boolean zzb;
    private int zzc;
    private long zzd;
    private int zze;
    private int zzf;
    private int zzg;

    public final void zza(zzaam zzaamVar, zzaal zzaalVar) {
        if (this.zzc > 0) {
            zzaamVar.zzs(this.zzd, this.zze, this.zzf, this.zzg, zzaalVar);
            this.zzc = 0;
        }
    }

    public final void zzb() {
        this.zzb = false;
        this.zzc = 0;
    }

    public final void zzc(zzaam zzaamVar, long j, int r6, int r7, int r8, zzaal zzaalVar) {
        if (this.zzg > r7 + r8) {
            throw new IllegalStateException("TrueHD chunk samples must be contiguous in the sample queue.");
        }
        if (this.zzb) {
            int r0 = this.zzc;
            int r1 = r0 + 1;
            this.zzc = r1;
            if (r0 == 0) {
                this.zzd = j;
                this.zze = r6;
                this.zzf = 0;
            }
            this.zzf += r7;
            this.zzg = r8;
            if (r1 >= 16) {
                zza(zzaamVar, zzaalVar);
            }
        }
    }

    public final void zzd(zzzg zzzgVar) throws IOException {
        if (this.zzb) {
            return;
        }
        zzzgVar.zzh(this.zza, 0, 10);
        zzzgVar.zzj();
        byte[] bArr = this.zza;
        int r0 = zzyg.zza;
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111 && (bArr[7] & 254) == 186) {
            this.zzb = true;
        }
    }
}
