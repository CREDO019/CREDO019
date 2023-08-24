package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaho {
    public byte[] zza;
    public int zzb;
    private final int zzc;
    private boolean zzd;
    private boolean zze;

    public zzaho(int r2, int r3) {
        this.zzc = r2;
        byte[] bArr = new byte[131];
        this.zza = bArr;
        bArr[2] = 1;
    }

    public final void zza(byte[] bArr, int r5, int r6) {
        if (this.zzd) {
            int r62 = r6 - r5;
            byte[] bArr2 = this.zza;
            int length = bArr2.length;
            int r2 = this.zzb + r62;
            if (length < r2) {
                this.zza = Arrays.copyOf(bArr2, r2 + r2);
            }
            System.arraycopy(bArr, r5, this.zza, this.zzb, r62);
            this.zzb += r62;
        }
    }

    public final void zzb() {
        this.zzd = false;
        this.zze = false;
    }

    public final void zzc(int r4) {
        zzdd.zzf(!this.zzd);
        boolean z = r4 == this.zzc;
        this.zzd = z;
        if (z) {
            this.zzb = 3;
            this.zze = false;
        }
    }

    public final boolean zzd(int r3) {
        if (this.zzd) {
            this.zzb -= r3;
            this.zzd = false;
            this.zze = true;
            return true;
        }
        return false;
    }

    public final boolean zze() {
        return this.zze;
    }
}
