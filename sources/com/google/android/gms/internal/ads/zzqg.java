package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzqg {
    private int zza = 0;
    private int zzb = -1;
    private int zzc = 0;
    private int[] zzd;
    private int zze;

    public zzqg() {
        int[] r0 = new int[16];
        this.zzd = r0;
        this.zze = r0.length - 1;
    }

    public final int zza() {
        int r0 = this.zzc;
        if (r0 == 0) {
            throw new NoSuchElementException();
        }
        int[] r1 = this.zzd;
        int r2 = this.zza;
        int r12 = r1[r2];
        this.zza = (r2 + 1) & this.zze;
        this.zzc = r0 - 1;
        return r12;
    }

    public final void zzb(int r6) {
        int r0 = this.zzc;
        int[] r1 = this.zzd;
        int length = r1.length;
        if (r0 == length) {
            int r02 = length + length;
            if (r02 >= 0) {
                int[] r03 = new int[r02];
                int r3 = this.zza;
                int r2 = length - r3;
                System.arraycopy(r1, r3, r03, 0, r2);
                System.arraycopy(this.zzd, 0, r03, r2, r3);
                this.zza = 0;
                this.zzb = this.zzc - 1;
                this.zzd = r03;
                this.zze = r03.length - 1;
                r1 = r03;
            } else {
                throw new IllegalStateException();
            }
        }
        int r04 = (this.zzb + 1) & this.zze;
        this.zzb = r04;
        r1[r04] = r6;
        this.zzc++;
    }

    public final void zzc() {
        this.zza = 0;
        this.zzb = -1;
        this.zzc = 0;
    }

    public final boolean zzd() {
        return this.zzc == 0;
    }
}
