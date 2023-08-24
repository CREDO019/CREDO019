package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzjg extends zzjj {
    private final byte[] zzb;
    private final int zzc;
    private int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjg(byte[] bArr, int r4, int r5) {
        super(null);
        Objects.requireNonNull(bArr, "buffer");
        int length = bArr.length;
        if (((length - r5) | r5) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(r5)));
        }
        this.zzb = bArr;
        this.zzd = 0;
        this.zzc = r5;
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final int zza() {
        return this.zzc - this.zzd;
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzd(int r1, boolean z) throws IOException {
        zzq(r1 << 3);
        zzb(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zze(int r1, zzjb zzjbVar) throws IOException {
        zzq((r1 << 3) | 2);
        zzq(zzjbVar.zzd());
        zzjbVar.zzh(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzf(int r1, int r2) throws IOException {
        zzq((r1 << 3) | 5);
        zzg(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzg(int r5) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int r1 = this.zzd;
            int r2 = r1 + 1;
            this.zzd = r2;
            bArr[r1] = (byte) (r5 & 255);
            int r12 = r2 + 1;
            this.zzd = r12;
            bArr[r2] = (byte) ((r5 >> 8) & 255);
            int r22 = r12 + 1;
            this.zzd = r22;
            bArr[r12] = (byte) ((r5 >> 16) & 255);
            this.zzd = r22 + 1;
            bArr[r22] = (byte) ((r5 >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzjh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzh(int r1, long j) throws IOException {
        zzq((r1 << 3) | 1);
        zzi(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzi(long j) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int r1 = this.zzd;
            int r2 = r1 + 1;
            this.zzd = r2;
            bArr[r1] = (byte) (((int) j) & 255);
            int r12 = r2 + 1;
            this.zzd = r12;
            bArr[r2] = (byte) (((int) (j >> 8)) & 255);
            int r22 = r12 + 1;
            this.zzd = r22;
            bArr[r12] = (byte) (((int) (j >> 16)) & 255);
            int r13 = r22 + 1;
            this.zzd = r13;
            bArr[r22] = (byte) (((int) (j >> 24)) & 255);
            int r23 = r13 + 1;
            this.zzd = r23;
            bArr[r13] = (byte) (((int) (j >> 32)) & 255);
            int r14 = r23 + 1;
            this.zzd = r14;
            bArr[r23] = (byte) (((int) (j >> 40)) & 255);
            int r24 = r14 + 1;
            this.zzd = r24;
            bArr[r14] = (byte) (((int) (j >> 48)) & 255);
            this.zzd = r24 + 1;
            bArr[r24] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzjh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzj(int r1, int r2) throws IOException {
        zzq(r1 << 3);
        zzk(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzk(int r3) throws IOException {
        if (r3 >= 0) {
            zzq(r3);
        } else {
            zzs(r3);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzl(byte[] bArr, int r2, int r3) throws IOException {
        zzc(bArr, 0, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzm(int r1, String str) throws IOException {
        zzq((r1 << 3) | 2);
        zzn(str);
    }

    public final void zzn(String str) throws IOException {
        int r0 = this.zzd;
        try {
            int zzA = zzA(str.length() * 3);
            int zzA2 = zzA(str.length());
            if (zzA2 == zzA) {
                int r1 = r0 + zzA2;
                this.zzd = r1;
                int zzb = zzna.zzb(str, this.zzb, r1, this.zzc - r1);
                this.zzd = r0;
                zzq((zzb - r0) - zzA2);
                this.zzd = zzb;
                return;
            }
            zzq(zzna.zzc(str));
            byte[] bArr = this.zzb;
            int r2 = this.zzd;
            this.zzd = zzna.zzb(str, bArr, r2, this.zzc - r2);
        } catch (zzmz e) {
            this.zzd = r0;
            zzE(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzjh(e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzo(int r1, int r2) throws IOException {
        zzq((r1 << 3) | r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzp(int r1, int r2) throws IOException {
        zzq(r1 << 3);
        zzq(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzr(int r1, long j) throws IOException {
        zzq(r1 << 3);
        zzs(j);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzb(byte b) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int r1 = this.zzd;
            this.zzd = r1 + 1;
            bArr[r1] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new zzjh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
        }
    }

    public final void zzc(byte[] bArr, int r5, int r6) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zzb, this.zzd, r6);
            this.zzd += r6;
        } catch (IndexOutOfBoundsException e) {
            throw new zzjh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(r6)), e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzq(int r5) throws IOException {
        while ((r5 & (-128)) != 0) {
            try {
                byte[] bArr = this.zzb;
                int r1 = this.zzd;
                this.zzd = r1 + 1;
                bArr[r1] = (byte) ((r5 & 127) | 128);
                r5 >>>= 7;
            } catch (IndexOutOfBoundsException e) {
                throw new zzjh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
            }
        }
        byte[] bArr2 = this.zzb;
        int r12 = this.zzd;
        this.zzd = r12 + 1;
        bArr2[r12] = (byte) r5;
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final void zzs(long j) throws IOException {
        boolean z;
        z = zzjj.zzc;
        if (z && this.zzc - this.zzd >= 10) {
            while ((j & (-128)) != 0) {
                byte[] bArr = this.zzb;
                int r6 = this.zzd;
                this.zzd = r6 + 1;
                zzmv.zzn(bArr, r6, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr2 = this.zzb;
            int r1 = this.zzd;
            this.zzd = r1 + 1;
            zzmv.zzn(bArr2, r1, (byte) j);
            return;
        }
        while ((j & (-128)) != 0) {
            try {
                byte[] bArr3 = this.zzb;
                int r62 = this.zzd;
                this.zzd = r62 + 1;
                bArr3[r62] = (byte) ((((int) j) & 127) | 128);
                j >>>= 7;
            } catch (IndexOutOfBoundsException e) {
                throw new zzjh(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e);
            }
        }
        byte[] bArr4 = this.zzb;
        int r12 = this.zzd;
        this.zzd = r12 + 1;
        bArr4[r12] = (byte) j;
    }
}
