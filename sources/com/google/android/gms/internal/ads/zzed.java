package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.util.Arrays;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzed {
    private byte[] zza;
    private int zzb;
    private int zzc;

    public zzed() {
        this.zza = zzel.zzf;
    }

    public zzed(byte[] bArr, int r2) {
        this.zza = bArr;
        this.zzc = r2;
    }

    public final void zzA(zzec zzecVar, int r4) {
        zzB(zzecVar.zza, 0, r4);
        zzecVar.zzh(0);
    }

    public final void zzB(byte[] bArr, int r4, int r5) {
        System.arraycopy(this.zza, this.zzb, bArr, r4, r5);
        this.zzb += r5;
    }

    public final void zzC(int r3) {
        byte[] bArr = this.zza;
        if (bArr.length < r3) {
            bArr = new byte[r3];
        }
        zzD(bArr, r3);
    }

    public final void zzD(byte[] bArr, int r2) {
        this.zza = bArr;
        this.zzc = r2;
        this.zzb = 0;
    }

    public final void zzE(int r3) {
        boolean z = false;
        if (r3 >= 0 && r3 <= this.zza.length) {
            z = true;
        }
        zzdd.zzd(z);
        this.zzc = r3;
    }

    public final void zzF(int r3) {
        boolean z = false;
        if (r3 >= 0 && r3 <= this.zzc) {
            z = true;
        }
        zzdd.zzd(z);
        this.zzb = r3;
    }

    public final void zzG(int r2) {
        zzF(this.zzb + r2);
    }

    public final byte[] zzH() {
        return this.zza;
    }

    public final int zza() {
        return this.zzc - this.zzb;
    }

    public final int zzb() {
        return this.zza.length;
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
        int r3 = r2 + 1;
        this.zzb = r3;
        byte b2 = bArr[r2];
        this.zzb = r3 + 1;
        return (bArr[r3] & 255) | (((b & 255) << 24) >> 8) | ((b2 & 255) << 8);
    }

    public final int zzg() {
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
        return ((bArr[r4] & 255) << 24) | (b & 255) | ((b2 & 255) << 8) | ((b3 & 255) << 16);
    }

    public final int zzh() {
        int zzg = zzg();
        if (zzg >= 0) {
            return zzg;
        }
        throw new IllegalStateException("Top bit not zero: " + zzg);
    }

    public final int zzi() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        this.zzb = r2 + 1;
        return ((bArr[r2] & 255) << 8) | (b & 255);
    }

    public final int zzj() {
        return (zzk() << 21) | (zzk() << 14) | (zzk() << 7) | zzk();
    }

    public final int zzk() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        this.zzb = r1 + 1;
        return bArr[r1] & 255;
    }

    public final int zzl() {
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

    public final int zzm() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        int r3 = r2 + 1;
        this.zzb = r3;
        byte b2 = bArr[r2];
        this.zzb = r3 + 1;
        return (bArr[r3] & 255) | ((b & 255) << 16) | ((b2 & 255) << 8);
    }

    public final int zzn() {
        int zze = zze();
        if (zze >= 0) {
            return zze;
        }
        throw new IllegalStateException("Top bit not zero: " + zze);
    }

    public final int zzo() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        this.zzb = r2 + 1;
        return (bArr[r2] & 255) | ((b & 255) << 8);
    }

    public final long zzp() {
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
        return ((b2 & 255) << 8) | (b & 255) | ((b3 & 255) << 16) | ((b4 & 255) << 24) | ((b5 & 255) << 32) | ((b6 & 255) << 40) | ((b7 & 255) << 48) | ((bArr[r8] & 255) << 56);
    }

    public final long zzq() {
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

    public final long zzr() {
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

    public final long zzs() {
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

    public final long zzt() {
        long zzr = zzr();
        if (zzr >= 0) {
            return zzr;
        }
        throw new IllegalStateException("Top bit not zero: " + zzr);
    }

    public final long zzu() {
        int r5;
        int r6;
        byte b;
        int r7;
        long j = this.zza[this.zzb];
        int r3 = 7;
        while (true) {
            r5 = 0;
            if (r3 < 0) {
                break;
            }
            if (((1 << r3) & j) != 0) {
                r3--;
            } else if (r3 < 6) {
                j &= r7 - 1;
                r5 = 7 - r3;
            } else if (r3 == 7) {
                r5 = 1;
            }
        }
        if (r5 == 0) {
            throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
        }
        for (r6 = 1; r6 < r5; r6++) {
            if ((this.zza[this.zzb + r6] & 192) != 128) {
                throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
            }
            j = (j << 6) | (b & Utf8.REPLACEMENT_BYTE);
        }
        this.zzb += r5;
        return j;
    }

    public final String zzv(char c) {
        int r4 = this.zzc;
        int r0 = this.zzb;
        if (r4 - r0 != 0) {
            while (r0 < this.zzc && this.zza[r0] != 0) {
                r0++;
            }
            byte[] bArr = this.zza;
            int r1 = this.zzb;
            String zzJ = zzel.zzJ(bArr, r1, r0 - r1);
            this.zzb = r0;
            if (r0 < this.zzc) {
                this.zzb = r0 + 1;
            }
            return zzJ;
        }
        return null;
    }

    public final String zzw(int r4) {
        if (r4 == 0) {
            return "";
        }
        int r0 = this.zzb;
        int r1 = (r0 + r4) - 1;
        String zzJ = zzel.zzJ(this.zza, r0, (r1 >= this.zzc || this.zza[r1] != 0) ? r4 : r4 - 1);
        this.zzb += r4;
        return zzJ;
    }

    public final String zzx(int r4, Charset charset) {
        byte[] bArr = this.zza;
        int r2 = this.zzb;
        String str = new String(bArr, r2, r4, charset);
        this.zzb = r2 + r4;
        return str;
    }

    public final short zzy() {
        byte[] bArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + 1;
        this.zzb = r2;
        byte b = bArr[r1];
        this.zzb = r2 + 1;
        return (short) ((bArr[r2] & 255) | ((b & 255) << 8));
    }

    public final void zzz(int r3) {
        byte[] bArr = this.zza;
        if (r3 > bArr.length) {
            this.zza = Arrays.copyOf(bArr, r3);
        }
    }

    public zzed(int r2) {
        this.zza = new byte[r2];
        this.zzc = r2;
    }

    public zzed(byte[] bArr) {
        this.zza = bArr;
        this.zzc = bArr.length;
    }
}
