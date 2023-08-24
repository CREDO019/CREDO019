package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaao {
    private final byte[] zza;
    private final int zzb;
    private int zzc;
    private int zzd;

    public zzaao(byte[] bArr) {
        this.zza = bArr;
        this.zzb = bArr.length;
    }

    public final int zza() {
        return (this.zzc * 8) + this.zzd;
    }

    public final int zzb(int r7) {
        int r0 = this.zzc;
        int min = Math.min(r7, 8 - this.zzd);
        int r2 = r0 + 1;
        int r02 = ((this.zza[r0] & 255) >> this.zzd) & (255 >> (8 - min));
        while (min < r7) {
            r02 |= (this.zza[r2] & 255) << min;
            min += 8;
            r2++;
        }
        zzc(r7);
        return ((-1) >>> (32 - r7)) & r02;
    }

    public final void zzc(int r5) {
        int r3;
        int r0 = r5 / 8;
        int r1 = this.zzc + r0;
        this.zzc = r1;
        int r2 = this.zzd + (r5 - (r0 * 8));
        this.zzd = r2;
        if (r2 > 7) {
            r1++;
            this.zzc = r1;
            r2 -= 8;
            this.zzd = r2;
        }
        boolean z = false;
        if (r1 >= 0 && (r1 < (r3 = this.zzb) || (r1 == r3 && r2 == 0))) {
            z = true;
        }
        zzdd.zzf(z);
    }

    public final boolean zzd() {
        byte b = this.zza[this.zzc];
        int r1 = this.zzd;
        zzc(1);
        return 1 == (((b & 255) >> r1) & 1);
    }
}
