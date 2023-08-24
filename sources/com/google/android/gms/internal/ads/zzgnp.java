package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgnp extends zzgnu {
    final byte[] zza;
    final int zzb;
    int zzc;
    int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgnp(int r2) {
        super(null);
        if (r2 < 0) {
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }
        byte[] bArr = new byte[Math.max(r2, 20)];
        this.zza = bArr;
        this.zzb = bArr.length;
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final int zzb() {
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(byte b) {
        byte[] bArr = this.zza;
        int r1 = this.zzc;
        this.zzc = r1 + 1;
        bArr[r1] = b;
        this.zzd++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(int r5) {
        byte[] bArr = this.zza;
        int r1 = this.zzc;
        int r2 = r1 + 1;
        this.zzc = r2;
        bArr[r1] = (byte) (r5 & 255);
        int r12 = r2 + 1;
        this.zzc = r12;
        bArr[r2] = (byte) ((r5 >> 8) & 255);
        int r22 = r12 + 1;
        this.zzc = r22;
        bArr[r12] = (byte) ((r5 >> 16) & 255);
        this.zzc = r22 + 1;
        bArr[r22] = (byte) ((r5 >> 24) & 255);
        this.zzd += 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(long j) {
        byte[] bArr = this.zza;
        int r1 = this.zzc;
        int r2 = r1 + 1;
        this.zzc = r2;
        bArr[r1] = (byte) (j & 255);
        int r12 = r2 + 1;
        this.zzc = r12;
        bArr[r2] = (byte) ((j >> 8) & 255);
        int r22 = r12 + 1;
        this.zzc = r22;
        bArr[r12] = (byte) ((j >> 16) & 255);
        int r13 = r22 + 1;
        this.zzc = r13;
        bArr[r22] = (byte) (255 & (j >> 24));
        int r23 = r13 + 1;
        this.zzc = r23;
        bArr[r13] = (byte) (((int) (j >> 32)) & 255);
        int r14 = r23 + 1;
        this.zzc = r14;
        bArr[r23] = (byte) (((int) (j >> 40)) & 255);
        int r24 = r14 + 1;
        this.zzc = r24;
        bArr[r14] = (byte) (((int) (j >> 48)) & 255);
        this.zzc = r24 + 1;
        bArr[r24] = (byte) (((int) (j >> 56)) & 255);
        this.zzd += 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf(int r7) {
        boolean z;
        z = zzgnu.zzb;
        if (!z) {
            while ((r7 & (-128)) != 0) {
                byte[] bArr = this.zza;
                int r1 = this.zzc;
                this.zzc = r1 + 1;
                bArr[r1] = (byte) ((r7 & 127) | 128);
                this.zzd++;
                r7 >>>= 7;
            }
            byte[] bArr2 = this.zza;
            int r12 = this.zzc;
            this.zzc = r12 + 1;
            bArr2[r12] = (byte) r7;
            this.zzd++;
            return;
        }
        long j = this.zzc;
        while ((r7 & (-128)) != 0) {
            byte[] bArr3 = this.zza;
            int r3 = this.zzc;
            this.zzc = r3 + 1;
            zzgrr.zzq(bArr3, r3, (byte) ((r7 & 127) | 128));
            r7 >>>= 7;
        }
        byte[] bArr4 = this.zza;
        int r32 = this.zzc;
        this.zzc = r32 + 1;
        zzgrr.zzq(bArr4, r32, (byte) r7);
        this.zzd += (int) (this.zzc - j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(long j) {
        boolean z;
        z = zzgnu.zzb;
        if (!z) {
            while ((j & (-128)) != 0) {
                byte[] bArr = this.zza;
                int r6 = this.zzc;
                this.zzc = r6 + 1;
                bArr[r6] = (byte) ((((int) j) & 127) | 128);
                this.zzd++;
                j >>>= 7;
            }
            byte[] bArr2 = this.zza;
            int r1 = this.zzc;
            this.zzc = r1 + 1;
            bArr2[r1] = (byte) j;
            this.zzd++;
            return;
        }
        long j2 = this.zzc;
        while ((j & (-128)) != 0) {
            byte[] bArr3 = this.zza;
            int r8 = this.zzc;
            this.zzc = r8 + 1;
            zzgrr.zzq(bArr3, r8, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        byte[] bArr4 = this.zza;
        int r12 = this.zzc;
        this.zzc = r12 + 1;
        zzgrr.zzq(bArr4, r12, (byte) j);
        this.zzd += (int) (this.zzc - j2);
    }
}
