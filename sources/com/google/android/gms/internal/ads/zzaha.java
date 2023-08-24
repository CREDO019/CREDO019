package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaha {
    private static final byte[] zzd = {0, 0, 1};
    public int zza;
    public int zzb;
    public byte[] zzc = new byte[128];
    private boolean zze;

    public zzaha(int r1) {
    }

    public final void zza(byte[] bArr, int r5, int r6) {
        if (this.zze) {
            int r62 = r6 - r5;
            byte[] bArr2 = this.zzc;
            int length = bArr2.length;
            int r2 = this.zza + r62;
            if (length < r2) {
                this.zzc = Arrays.copyOf(bArr2, r2 + r2);
            }
            System.arraycopy(bArr, r5, this.zzc, this.zza, r62);
            this.zza += r62;
        }
    }

    public final void zzb() {
        this.zze = false;
        this.zza = 0;
        this.zzb = 0;
    }

    public final boolean zzc(int r4, int r5) {
        if (this.zze) {
            int r0 = this.zza - r5;
            this.zza = r0;
            if (this.zzb != 0 || r4 != 181) {
                this.zze = false;
                return true;
            }
            this.zzb = r0;
        } else if (r4 == 179) {
            this.zze = true;
        }
        zza(zzd, 0, 3);
        return false;
    }
}
