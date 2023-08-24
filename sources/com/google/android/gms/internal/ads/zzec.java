package com.google.android.gms.internal.ads;

import androidx.core.view.MotionEventCompat;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzec {
    public byte[] zza;
    private int zzb;
    private int zzc;
    private int zzd;

    public zzec() {
        this.zza = zzel.zzf;
    }

    public zzec(byte[] bArr, int r2) {
        this.zza = bArr;
        this.zzd = r2;
    }

    private final void zzm() {
        int r3;
        int r0 = this.zzb;
        boolean z = true;
        if (r0 < 0 || (r0 >= (r3 = this.zzd) && (r0 != r3 || this.zzc != 0))) {
            z = false;
        }
        zzdd.zzf(z);
    }

    public final int zza() {
        return ((this.zzd - this.zzb) * 8) - this.zzc;
    }

    public final int zzb() {
        return (this.zzb * 8) + this.zzc;
    }

    public final int zzc(int r8) {
        int r2;
        if (r8 == 0) {
            return 0;
        }
        this.zzc += r8;
        int r1 = 0;
        while (true) {
            r2 = this.zzc;
            if (r2 <= 8) {
                break;
            }
            int r22 = r2 - 8;
            this.zzc = r22;
            byte[] bArr = this.zza;
            int r4 = this.zzb;
            this.zzb = r4 + 1;
            r1 |= (bArr[r4] & 255) << r22;
        }
        byte[] bArr2 = this.zza;
        int r5 = this.zzb;
        int r82 = ((-1) >>> (32 - r8)) & (r1 | ((bArr2[r5] & 255) >> (8 - r2)));
        if (r2 == 8) {
            this.zzc = 0;
            this.zzb = r5 + 1;
        }
        zzm();
        return r82;
    }

    public final void zzd() {
        if (this.zzc == 0) {
            return;
        }
        this.zzc = 0;
        this.zzb++;
        zzm();
    }

    public final void zze(int r10, int r11) {
        int r0;
        int r102 = r10 & 16383;
        int min = Math.min(8 - this.zzc, 14);
        int r2 = this.zzc;
        int r3 = (8 - r2) - min;
        byte[] bArr = this.zza;
        int r5 = this.zzb;
        byte b = (byte) (((MotionEventCompat.ACTION_POINTER_INDEX_MASK >> r2) | ((1 << r3) - 1)) & bArr[r5]);
        bArr[r5] = b;
        int r112 = 14 - min;
        bArr[r5] = (byte) (b | ((r102 >>> r112) << r3));
        int r52 = r5 + 1;
        while (r112 > 8) {
            r112 -= 8;
            this.zza[r52] = (byte) (r102 >>> r112);
            r52++;
        }
        byte[] bArr2 = this.zza;
        byte b2 = (byte) (bArr2[r52] & ((1 << r0) - 1));
        bArr2[r52] = b2;
        bArr2[r52] = (byte) (((r102 & ((1 << r112) - 1)) << (8 - r112)) | b2);
        zzj(14);
        zzm();
    }

    public final void zzf(byte[] bArr, int r10, int r11) {
        int r102 = r11 >> 3;
        for (int r1 = 0; r1 < r102; r1++) {
            byte[] bArr2 = this.zza;
            int r5 = this.zzb;
            int r6 = r5 + 1;
            this.zzb = r6;
            byte b = bArr2[r5];
            int r7 = this.zzc;
            byte b2 = (byte) (b << r7);
            bArr[r1] = b2;
            bArr[r1] = (byte) (((255 & bArr2[r6]) >> (8 - r7)) | b2);
        }
        int r112 = r11 & 7;
        if (r112 == 0) {
            return;
        }
        byte b3 = (byte) (bArr[r102] & (255 >> r112));
        bArr[r102] = b3;
        int r4 = this.zzc;
        if (r4 + r112 > 8) {
            byte[] bArr3 = this.zza;
            int r62 = this.zzb;
            this.zzb = r62 + 1;
            b3 = (byte) (b3 | ((bArr3[r62] & 255) << r4));
            bArr[r102] = b3;
            r4 -= 8;
        }
        int r42 = r4 + r112;
        this.zzc = r42;
        byte[] bArr4 = this.zza;
        int r63 = this.zzb;
        bArr[r102] = (byte) (((byte) (((255 & bArr4[r63]) >> (8 - r42)) << (8 - r112))) | b3);
        if (r42 == 8) {
            this.zzc = 0;
            this.zzb = r63 + 1;
        }
        zzm();
    }

    public final void zzg(byte[] bArr, int r2) {
        this.zza = bArr;
        this.zzb = 0;
        this.zzc = 0;
        this.zzd = r2;
    }

    public final void zzh(int r2) {
        int r0 = r2 / 8;
        this.zzb = r0;
        this.zzc = r2 - (r0 * 8);
        zzm();
    }

    public final void zzi() {
        int r0 = this.zzc + 1;
        this.zzc = r0;
        if (r0 == 8) {
            this.zzc = 0;
            this.zzb++;
        }
        zzm();
    }

    public final void zzj(int r4) {
        int r0 = r4 / 8;
        int r1 = this.zzb + r0;
        this.zzb = r1;
        int r2 = this.zzc + (r4 - (r0 * 8));
        this.zzc = r2;
        if (r2 > 7) {
            this.zzb = r1 + 1;
            this.zzc = r2 - 8;
        }
        zzm();
    }

    public final void zzk(int r2) {
        zzdd.zzf(this.zzc == 0);
        this.zzb += r2;
        zzm();
    }

    public final boolean zzl() {
        boolean z = (this.zza[this.zzb] & (128 >> this.zzc)) != 0;
        zzi();
        return z;
    }
}
