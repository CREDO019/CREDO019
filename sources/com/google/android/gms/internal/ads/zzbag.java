package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbag {
    public byte[] zza;
    private int zzb;
    private int zzc;

    public zzbag() {
    }

    public zzbag(int r2) {
        this.zza = new byte[r2];
        this.zzc = r2;
    }

    public final int zza() {
        return this.zzc - this.zzb;
    }

    public final int zzb() {
        byte[] bArr = this.zza;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public final int zzc() {
        return this.zzb;
    }

    public final int zzd() {
        return this.zzc;
    }

    public final int zze() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        int r3 = r2 + 1;
        this.zzb = r3;
        byte b2 = bArr[r2];
        int r4 = r3 + 1;
        this.zzb = r4;
        byte b3 = bArr[r3];
        this.zzb = r4 + 1;
        return (bArr[r4] & 255) | ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8);
    }

    public final int zzf() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        this.zzb = r2 + 1;
        return ((bArr[r2] & 255) << 8) | (b & 255);
    }

    public final int zzg() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        this.zzb = r1 + 1;
        return bArr[r1] & 255;
    }

    public final int zzh() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        int r3 = r2 + 1;
        this.zzb = r3;
        byte b2 = bArr[r2];
        this.zzb = r3 + 2;
        return (b2 & 255) | ((b & 255) << 8);
    }

    public final int zzi() {
        int zze = zze();
        if (zze >= 0) {
            return zze;
        }
        throw new IllegalStateException("Top bit not zero: " + zze);
    }

    public final int zzj() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        this.zzb = r2 + 1;
        return (bArr[r2] & 255) | ((b & 255) << 8);
    }

    public final long zzk() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        int r3 = r2 + 1;
        this.zzb = r3;
        byte b2 = bArr[r2];
        int r4 = r3 + 1;
        this.zzb = r4;
        byte b3 = bArr[r3];
        this.zzb = r4 + 1;
        return ((b2 & 255) << 8) | (b & 255) | ((b3 & 255) << 16) | ((bArr[r4] & 255) << 24);
    }

    public final long zzl() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        int r3 = r2 + 1;
        this.zzb = r3;
        byte b2 = bArr[r2];
        int r4 = r3 + 1;
        this.zzb = r4;
        byte b3 = bArr[r3];
        int r5 = r4 + 1;
        this.zzb = r5;
        byte b4 = bArr[r4];
        int r6 = r5 + 1;
        this.zzb = r6;
        byte b5 = bArr[r5];
        int r7 = r6 + 1;
        this.zzb = r7;
        byte b6 = bArr[r6];
        int r8 = r7 + 1;
        this.zzb = r8;
        byte b7 = bArr[r7];
        this.zzb = r8 + 1;
        return ((b2 & 255) << 48) | ((b & 255) << 56) | ((b3 & 255) << 40) | ((b4 & 255) << 32) | ((b5 & 255) << 24) | ((b6 & 255) << 16) | ((b7 & 255) << 8) | (bArr[r8] & 255);
    }

    public final long zzm() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        int r3 = r2 + 1;
        this.zzb = r3;
        byte b2 = bArr[r2];
        int r4 = r3 + 1;
        this.zzb = r4;
        byte b3 = bArr[r3];
        this.zzb = r4 + 1;
        return ((b2 & 255) << 16) | ((b & 255) << 24) | ((b3 & 255) << 8) | (bArr[r4] & 255);
    }

    public final long zzn() {
        long zzl = zzl();
        if (zzl >= 0) {
            return zzl;
        }
        throw new IllegalStateException("Top bit not zero: " + zzl);
    }

    public final String zzo(int r5) {
        if (r5 == 0) {
            return "";
        }
        int r0 = this.zzb;
        int r1 = r0 + r5;
        int r2 = r1 - 1;
        if (r2 < this.zzc && this.zza[r2] == 0) {
            r5--;
        }
        String str = new String(this.zza, r0, r5);
        this.zzb = r1;
        return str;
    }

    public final short zzp() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        this.zzb = r2 + 1;
        return (short) ((bArr[r2] & 255) | ((b & 255) << 8));
    }

    public final void zzq(byte[] bArr, int r4, int r5) {
        System.arraycopy(this.zza, this.zzb, bArr, r4, r5);
        this.zzb += r5;
    }

    public final void zzr() {
        this.zzb = 0;
        this.zzc = 0;
    }

    public final void zzs(int r2) {
        zzt(zzb() < r2 ? new byte[r2] : this.zza, r2);
    }

    public final void zzt(byte[] bArr, int r2) {
        this.zza = bArr;
        this.zzc = r2;
        this.zzb = 0;
    }

    public final void zzu(int r3) {
        boolean z = false;
        if (r3 >= 0 && r3 <= this.zza.length) {
            z = true;
        }
        zzazy.zzc(z);
        this.zzc = r3;
    }

    public final void zzv(int r3) {
        boolean z = false;
        if (r3 >= 0 && r3 <= this.zzc) {
            z = true;
        }
        zzazy.zzc(z);
        this.zzb = r3;
    }

    public final void zzw(int r2) {
        zzv(this.zzb + r2);
    }

    public zzbag(byte[] bArr) {
        this.zza = bArr;
        this.zzc = bArr.length;
    }
}
