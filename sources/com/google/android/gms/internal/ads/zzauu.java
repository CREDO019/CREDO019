package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzauu {
    private static final byte[] zza = new byte[4096];
    private final zzazi zzb;
    private final long zzc;
    private long zzd;
    private byte[] zze = new byte[65536];
    private int zzf;
    private int zzg;

    public zzauu(zzazi zzaziVar, long j, long j2) {
        this.zzb = zzaziVar;
        this.zzd = j;
        this.zzc = j2;
    }

    private final int zzj(byte[] bArr, int r3, int r4, int r5, boolean z) throws InterruptedException, IOException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        int zza2 = this.zzb.zza(bArr, r3 + r5, r4 - r5);
        if (zza2 == -1) {
            if (r5 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        return r5 + zza2;
    }

    private final int zzk(byte[] bArr, int r4, int r5) {
        int r0 = this.zzg;
        if (r0 == 0) {
            return 0;
        }
        int min = Math.min(r0, r5);
        System.arraycopy(this.zze, 0, bArr, r4, min);
        zzn(min);
        return min;
    }

    private final int zzl(int r2) {
        int min = Math.min(this.zzg, r2);
        zzn(min);
        return min;
    }

    private final void zzm(int r5) {
        if (r5 != -1) {
            this.zzd += r5;
        }
    }

    private final void zzn(int r6) {
        int r0 = this.zzg - r6;
        this.zzg = r0;
        this.zzf = 0;
        byte[] bArr = this.zze;
        byte[] bArr2 = r0 < bArr.length + (-524288) ? new byte[65536 + r0] : bArr;
        System.arraycopy(bArr, r6, bArr2, 0, r0);
        this.zze = bArr2;
    }

    public final int zza(byte[] bArr, int r9, int r10) throws IOException, InterruptedException {
        int zzk = zzk(bArr, r9, r10);
        if (zzk == 0) {
            zzk = zzj(bArr, r9, r10, 0, true);
        }
        zzm(zzk);
        return zzk;
    }

    public final int zzb(int r8) throws IOException, InterruptedException {
        int zzl = zzl(r8);
        if (zzl == 0) {
            zzl = zzj(zza, 0, Math.min(r8, 4096), 0, true);
        }
        zzm(zzl);
        return zzl;
    }

    public final long zzc() {
        return this.zzc;
    }

    public final long zzd() {
        return this.zzd;
    }

    public final void zze() {
        this.zzf = 0;
    }

    public final boolean zzf(int r7, boolean z) throws IOException, InterruptedException {
        int r8 = this.zzf + r7;
        int length = this.zze.length;
        if (r8 > length) {
            this.zze = Arrays.copyOf(this.zze, zzban.zze(length + length, 65536 + r8, r8 + 524288));
        }
        int min = Math.min(this.zzg - this.zzf, r7);
        while (min < r7) {
            min = zzj(this.zze, this.zzf, r7, min, false);
            if (min == -1) {
                return false;
            }
        }
        int r82 = this.zzf + r7;
        this.zzf = r82;
        this.zzg = Math.max(this.zzg, r82);
        return true;
    }

    public final boolean zzg(byte[] bArr, int r3, int r4, boolean z) throws IOException, InterruptedException {
        if (zzf(r4, false)) {
            System.arraycopy(this.zze, this.zzf - r4, bArr, r3, r4);
            return true;
        }
        return false;
    }

    public final boolean zzh(byte[] bArr, int r9, int r10, boolean z) throws IOException, InterruptedException {
        int zzk = zzk(bArr, r9, r10);
        while (zzk < r10 && zzk != -1) {
            zzk = zzj(bArr, r9, r10, zzk, z);
        }
        zzm(zzk);
        return zzk != -1;
    }

    public final boolean zzi(int r7, boolean z) throws IOException, InterruptedException {
        int zzl = zzl(r7);
        while (zzl < r7 && zzl != -1) {
            zzl = zzj(zza, -zzl, Math.min(r7, zzl + 4096), zzl, false);
        }
        zzm(zzl);
        return zzl != -1;
    }
}
