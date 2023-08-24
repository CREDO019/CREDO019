package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzyv implements zzzg {
    private final zzr zzb;
    private final long zzc;
    private long zzd;
    private int zzf;
    private int zzg;
    private byte[] zze = new byte[65536];
    private final byte[] zza = new byte[4096];

    static {
        zzbh.zzb("media3.extractor");
    }

    public zzyv(zzr zzrVar, long j, long j2) {
        this.zzb = zzrVar;
        this.zzd = j;
        this.zzc = j2;
    }

    private final int zzp(byte[] bArr, int r4, int r5) {
        int r0 = this.zzg;
        if (r0 == 0) {
            return 0;
        }
        int min = Math.min(r0, r5);
        System.arraycopy(this.zze, 0, bArr, r4, min);
        zzu(min);
        return min;
    }

    private final int zzq(byte[] bArr, int r3, int r4, int r5, boolean z) throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
        int zza = this.zzb.zza(bArr, r3 + r5, r4 - r5);
        if (zza == -1) {
            if (r5 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        return r5 + zza;
    }

    private final int zzr(int r2) {
        int min = Math.min(this.zzg, r2);
        zzu(min);
        return min;
    }

    private final void zzs(int r5) {
        if (r5 != -1) {
            this.zzd += r5;
        }
    }

    private final void zzt(int r4) {
        int r0 = this.zzf + r4;
        int length = this.zze.length;
        if (r0 > length) {
            this.zze = Arrays.copyOf(this.zze, zzel.zzf(length + length, 65536 + r0, r0 + 524288));
        }
    }

    private final void zzu(int r6) {
        int r0 = this.zzg - r6;
        this.zzg = r0;
        this.zzf = 0;
        byte[] bArr = this.zze;
        byte[] bArr2 = r0 < bArr.length + (-524288) ? new byte[65536 + r0] : bArr;
        System.arraycopy(bArr, r6, bArr2, 0, r0);
        this.zze = bArr2;
    }

    @Override // com.google.android.gms.internal.ads.zzzg, com.google.android.gms.internal.ads.zzr
    public final int zza(byte[] bArr, int r9, int r10) throws IOException {
        int zzp = zzp(bArr, r9, r10);
        if (zzp == 0) {
            zzp = zzq(bArr, r9, r10, 0, true);
        }
        zzs(zzp);
        return zzp;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final int zzb(byte[] bArr, int r9, int r10) throws IOException {
        int min;
        zzt(r10);
        int r0 = this.zzg;
        int r3 = this.zzf;
        int r02 = r0 - r3;
        if (r02 == 0) {
            min = zzq(this.zze, r3, r10, 0, true);
            if (min == -1) {
                return -1;
            }
            this.zzg += min;
        } else {
            min = Math.min(r10, r02);
        }
        System.arraycopy(this.zze, this.zzf, bArr, r9, min);
        this.zzf += min;
        return min;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final int zzc(int r8) throws IOException {
        int zzr = zzr(1);
        if (zzr == 0) {
            zzr = zzq(this.zza, 0, Math.min(1, 4096), 0, true);
        }
        zzs(zzr);
        return zzr;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final long zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final long zze() {
        return this.zzd + this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final long zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final void zzg(int r2) throws IOException {
        zzl(r2, false);
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final void zzh(byte[] bArr, int r3, int r4) throws IOException {
        zzm(bArr, r3, r4, false);
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final void zzi(byte[] bArr, int r3, int r4) throws IOException {
        zzn(bArr, r3, r4, false);
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final void zzj() {
        this.zzf = 0;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final void zzk(int r2) throws IOException {
        zzo(r2, false);
    }

    public final boolean zzl(int r8, boolean z) throws IOException {
        zzt(r8);
        int r5 = this.zzg - this.zzf;
        while (r5 < r8) {
            r5 = zzq(this.zze, this.zzf, r8, r5, z);
            if (r5 == -1) {
                return false;
            }
            this.zzg = this.zzf + r5;
        }
        this.zzf += r8;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final boolean zzm(byte[] bArr, int r3, int r4, boolean z) throws IOException {
        if (zzl(r4, z)) {
            System.arraycopy(this.zze, this.zzf - r4, bArr, r3, r4);
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzzg
    public final boolean zzn(byte[] bArr, int r9, int r10, boolean z) throws IOException {
        int zzp = zzp(bArr, r9, r10);
        while (zzp < r10 && zzp != -1) {
            zzp = zzq(bArr, r9, r10, zzp, z);
        }
        zzs(zzp);
        return zzp != -1;
    }

    public final boolean zzo(int r7, boolean z) throws IOException {
        int zzr = zzr(r7);
        while (zzr < r7 && zzr != -1) {
            zzr = zzq(this.zza, -zzr, Math.min(r7, zzr + 4096), zzr, false);
        }
        zzs(zzr);
        return zzr != -1;
    }
}
