package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbah {
    private final byte[] zza;
    private final int zzb;
    private int zzc;
    private int zzd = 0;

    public zzbah(byte[] bArr, int r2, int r3) {
        this.zza = bArr;
        this.zzc = r2;
        this.zzb = r3;
        zzg();
    }

    private final int zzf() {
        int r1 = 0;
        while (!zze()) {
            r1++;
        }
        return ((1 << r1) - 1) + (r1 > 0 ? zza(r1) : 0);
    }

    private final void zzg() {
        int r3;
        int r4;
        int r0 = this.zzc;
        boolean z = true;
        if (r0 < 0 || (r3 = this.zzd) < 0 || r3 >= 8 || (r0 >= (r4 = this.zzb) && (r0 != r4 || r3 != 0))) {
            z = false;
        }
        zzazy.zze(z);
    }

    private final boolean zzh(int r4) {
        if (r4 < 2 || r4 >= this.zzb) {
            return false;
        }
        byte[] bArr = this.zza;
        return bArr[r4] == 3 && bArr[r4 + (-2)] == 0 && bArr[r4 + (-1)] == 0;
    }

    public final int zza(int r9) {
        int r92;
        int r5;
        int r0 = r9 >> 3;
        int r2 = 0;
        for (int r1 = 0; r1 < r0; r1++) {
            int r4 = zzh(this.zzc + 1) ? this.zzc + 2 : this.zzc + 1;
            int r52 = this.zzd;
            if (r52 != 0) {
                byte[] bArr = this.zza;
                r5 = ((bArr[r4] & 255) >>> (8 - r52)) | ((bArr[this.zzc] & 255) << r52);
            } else {
                r5 = this.zza[this.zzc];
            }
            r9 -= 8;
            r2 |= (255 & r5) << r9;
            this.zzc = r4;
        }
        if (r9 > 0) {
            int r02 = this.zzd + r9;
            byte b = (byte) (255 >> (8 - r9));
            int r42 = zzh(this.zzc + 1) ? this.zzc + 2 : this.zzc + 1;
            if (r02 > 8) {
                byte[] bArr2 = this.zza;
                r92 = (b & (((255 & bArr2[r42]) >> (16 - r02)) | ((bArr2[this.zzc] & 255) << (r02 - 8)))) | r2;
                this.zzc = r42;
            } else {
                r92 = (b & ((255 & this.zza[this.zzc]) >> (8 - r02))) | r2;
                if (r02 == 8) {
                    this.zzc = r42;
                }
            }
            r2 = r92;
            this.zzd = r02 % 8;
        }
        zzg();
        return r2;
    }

    public final int zzb() {
        int zzf = zzf();
        return (zzf % 2 == 0 ? -1 : 1) * ((zzf + 1) / 2);
    }

    public final int zzc() {
        return zzf();
    }

    public final void zzd(int r5) {
        int r0 = this.zzc;
        int r1 = (r5 >> 3) + r0;
        this.zzc = r1;
        int r2 = this.zzd + (r5 & 7);
        this.zzd = r2;
        if (r2 > 7) {
            this.zzc = r1 + 1;
            this.zzd = r2 - 8;
        }
        while (true) {
            r0++;
            if (r0 > this.zzc) {
                zzg();
                return;
            } else if (zzh(r0)) {
                this.zzc++;
                r0 += 2;
            }
        }
    }

    public final boolean zze() {
        return zza(1) == 1;
    }
}
