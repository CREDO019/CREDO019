package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaae {
    private byte[] zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    public zzaae(byte[] bArr, int r2, int r3) {
        this.zza = bArr;
        this.zzc = r2;
        this.zzb = r3;
        zzh();
    }

    private final int zzg() {
        int r1 = 0;
        while (!zzf()) {
            r1++;
        }
        return ((1 << r1) - 1) + (r1 > 0 ? zza(r1) : 0);
    }

    private final void zzh() {
        int r3;
        int r0 = this.zzc;
        boolean z = true;
        if (r0 < 0 || (r0 >= (r3 = this.zzb) && (r0 != r3 || this.zzd != 0))) {
            z = false;
        }
        zzdd.zzf(z);
    }

    private final boolean zzi(int r4) {
        if (r4 < 2 || r4 >= this.zzb) {
            return false;
        }
        byte[] bArr = this.zza;
        return bArr[r4] == 3 && bArr[r4 + (-2)] == 0 && bArr[r4 + (-1)] == 0;
    }

    public final int zza(int r10) {
        int r2;
        this.zzd += r10;
        int r1 = 0;
        while (true) {
            r2 = this.zzd;
            if (r2 <= 8) {
                break;
            }
            int r22 = r2 - 8;
            this.zzd = r22;
            byte[] bArr = this.zza;
            int r6 = this.zzc;
            r1 |= (bArr[r6] & 255) << r22;
            if (true != zzi(r6 + 1)) {
                r3 = 1;
            }
            this.zzc = r6 + r3;
        }
        byte[] bArr2 = this.zza;
        int r7 = this.zzc;
        int r102 = ((-1) >>> (32 - r10)) & (r1 | ((bArr2[r7] & 255) >> (8 - r2)));
        if (r2 == 8) {
            this.zzd = 0;
            this.zzc = r7 + (true != zzi(r7 + 1) ? 1 : 2);
        }
        zzh();
        return r102;
    }

    public final int zzb() {
        int zzg = zzg();
        return (zzg % 2 == 0 ? -1 : 1) * ((zzg + 1) / 2);
    }

    public final int zzc() {
        return zzg();
    }

    public final void zzd() {
        int r0 = this.zzd + 1;
        this.zzd = r0;
        if (r0 == 8) {
            this.zzd = 0;
            int r02 = this.zzc;
            this.zzc = r02 + (true == zzi(r02 + 1) ? 2 : 1);
        }
        zzh();
    }

    public final void zze(int r5) {
        int r0 = this.zzc;
        int r1 = r5 / 8;
        int r2 = r0 + r1;
        this.zzc = r2;
        int r3 = this.zzd + (r5 - (r1 * 8));
        this.zzd = r3;
        if (r3 > 7) {
            this.zzc = r2 + 1;
            this.zzd = r3 - 8;
        }
        while (true) {
            r0++;
            if (r0 > this.zzc) {
                zzh();
                return;
            } else if (zzi(r0)) {
                this.zzc++;
                r0 += 2;
            }
        }
    }

    public final boolean zzf() {
        boolean z = (this.zza[this.zzc] & (128 >> this.zzd)) != 0;
        zzd();
        return z;
    }
}
