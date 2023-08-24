package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbaf {
    public byte[] zza;
    private int zzb;
    private int zzc;
    private int zzd;

    public zzbaf() {
    }

    public zzbaf(byte[] bArr, int r2) {
        this.zza = bArr;
        this.zzd = r2;
    }

    public final int zza(int r11) {
        int r0;
        int r2;
        int r112;
        int r6;
        int r02 = r11 >> 3;
        boolean z = false;
        int r3 = 0;
        for (int r22 = 0; r22 < r02; r22++) {
            int r62 = this.zzc;
            if (r62 != 0) {
                byte[] bArr = this.zza;
                int r8 = this.zzb;
                r6 = ((bArr[r8 + 1] & 255) >>> (8 - r62)) | ((bArr[r8] & 255) << r62);
            } else {
                r6 = this.zza[this.zzb];
            }
            r11 -= 8;
            r3 |= (255 & r6) << r11;
            this.zzb++;
        }
        if (r11 > 0) {
            int r03 = this.zzc + r11;
            byte b = (byte) (255 >> (8 - r11));
            if (r03 > 8) {
                byte[] bArr2 = this.zza;
                int r7 = this.zzb;
                int r82 = r7 + 1;
                r112 = (b & (((255 & bArr2[r82]) >> (16 - r03)) | ((bArr2[r7] & 255) << (r03 - 8)))) | r3;
                this.zzb = r82;
            } else {
                byte[] bArr3 = this.zza;
                int r72 = this.zzb;
                r112 = (b & ((255 & bArr3[r72]) >> (8 - r03))) | r3;
                if (r03 == 8) {
                    this.zzb = r72 + 1;
                }
            }
            r3 = r112;
            this.zzc = r03 % 8;
        }
        int r113 = this.zzb;
        if (r113 >= 0 && (r0 = this.zzc) >= 0 && (r113 < (r2 = this.zzd) || (r113 == r2 && r0 == 0))) {
            z = true;
        }
        zzazy.zze(z);
        return r3;
    }
}
