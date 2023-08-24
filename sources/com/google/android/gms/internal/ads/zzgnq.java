package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgnq extends zzgnu {
    private final byte[] zza;
    private final int zzb;
    private int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgnq(byte[] bArr, int r4, int r5) {
        super(null);
        Objects.requireNonNull(bArr, "buffer");
        int length = bArr.length;
        if (((length - r5) | r5) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(r5)));
        }
        this.zza = bArr;
        this.zzc = 0;
        this.zzb = r5;
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzN() {
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzP(int r1, boolean z) throws IOException {
        zzs(r1 << 3);
        zzO(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzQ(int r1, zzgnf zzgnfVar) throws IOException {
        zzs((r1 << 3) | 2);
        zzs(zzgnfVar.zzd());
        zzgnfVar.zzo(this);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu, com.google.android.gms.internal.ads.zzgmu
    public final void zza(byte[] bArr, int r2, int r3) throws IOException {
        zze(bArr, r2, r3);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final int zzb() {
        return this.zzb - this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzh(int r1, int r2) throws IOException {
        zzs((r1 << 3) | 5);
        zzi(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzi(int r5) throws IOException {
        try {
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
        } catch (IndexOutOfBoundsException e) {
            throw new zzgnr(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzj(int r1, long j) throws IOException {
        zzs((r1 << 3) | 1);
        zzk(j);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzk(long j) throws IOException {
        try {
            byte[] bArr = this.zza;
            int r1 = this.zzc;
            int r2 = r1 + 1;
            this.zzc = r2;
            bArr[r1] = (byte) (((int) j) & 255);
            int r12 = r2 + 1;
            this.zzc = r12;
            bArr[r2] = (byte) (((int) (j >> 8)) & 255);
            int r22 = r12 + 1;
            this.zzc = r22;
            bArr[r12] = (byte) (((int) (j >> 16)) & 255);
            int r13 = r22 + 1;
            this.zzc = r13;
            bArr[r22] = (byte) (((int) (j >> 24)) & 255);
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
        } catch (IndexOutOfBoundsException e) {
            throw new zzgnr(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzl(int r1, int r2) throws IOException {
        zzs(r1 << 3);
        zzm(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzm(int r3) throws IOException {
        if (r3 >= 0) {
            zzs(r3);
        } else {
            zzu(r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzn(int r3, zzgpx zzgpxVar, zzgqq zzgqqVar) throws IOException {
        zzs((r3 << 3) | 2);
        zzgmo zzgmoVar = (zzgmo) zzgpxVar;
        int zzar = zzgmoVar.zzar();
        if (zzar == -1) {
            zzar = zzgqqVar.zza(zzgmoVar);
            zzgmoVar.zzau(zzar);
        }
        zzs(zzar);
        zzgqqVar.zzn(zzgpxVar, this.zze);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzo(int r1, String str) throws IOException {
        zzs((r1 << 3) | 2);
        zzp(str);
    }

    public final void zzp(String str) throws IOException {
        int r0 = this.zzc;
        try {
            int zzE = zzE(str.length() * 3);
            int zzE2 = zzE(str.length());
            if (zzE2 == zzE) {
                int r1 = r0 + zzE2;
                this.zzc = r1;
                int zzd = zzgrw.zzd(str, this.zza, r1, this.zzb - r1);
                this.zzc = r0;
                zzs((zzd - r0) - zzE2);
                this.zzc = zzd;
                return;
            }
            zzs(zzgrw.zze(str));
            byte[] bArr = this.zza;
            int r2 = this.zzc;
            this.zzc = zzgrw.zzd(str, bArr, r2, this.zzb - r2);
        } catch (zzgrv e) {
            this.zzc = r0;
            zzJ(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzgnr(e2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzq(int r1, int r2) throws IOException {
        zzs((r1 << 3) | r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzr(int r1, int r2) throws IOException {
        zzs(r1 << 3);
        zzs(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzt(int r1, long j) throws IOException {
        zzs(r1 << 3);
        zzu(j);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzO(byte b) throws IOException {
        try {
            byte[] bArr = this.zza;
            int r1 = this.zzc;
            this.zzc = r1 + 1;
            bArr[r1] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new zzgnr(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
        }
    }

    public final void zze(byte[] bArr, int r5, int r6) throws IOException {
        try {
            System.arraycopy(bArr, r5, this.zza, this.zzc, r6);
            this.zzc += r6;
        } catch (IndexOutOfBoundsException e) {
            throw new zzgnr(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(r6)), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzs(int r5) throws IOException {
        while ((r5 & (-128)) != 0) {
            try {
                byte[] bArr = this.zza;
                int r1 = this.zzc;
                this.zzc = r1 + 1;
                bArr[r1] = (byte) ((r5 & 127) | 128);
                r5 >>>= 7;
            } catch (IndexOutOfBoundsException e) {
                throw new zzgnr(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
            }
        }
        byte[] bArr2 = this.zza;
        int r12 = this.zzc;
        this.zzc = r12 + 1;
        bArr2[r12] = (byte) r5;
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzu(long j) throws IOException {
        boolean z;
        z = zzgnu.zzb;
        if (z && this.zzb - this.zzc >= 10) {
            while ((j & (-128)) != 0) {
                byte[] bArr = this.zza;
                int r6 = this.zzc;
                this.zzc = r6 + 1;
                zzgrr.zzq(bArr, r6, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr2 = this.zza;
            int r1 = this.zzc;
            this.zzc = r1 + 1;
            zzgrr.zzq(bArr2, r1, (byte) j);
            return;
        }
        while ((j & (-128)) != 0) {
            try {
                byte[] bArr3 = this.zza;
                int r62 = this.zzc;
                this.zzc = r62 + 1;
                bArr3[r62] = (byte) ((((int) j) & 127) | 128);
                j >>>= 7;
            } catch (IndexOutOfBoundsException e) {
                throw new zzgnr(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), 1), e);
            }
        }
        byte[] bArr4 = this.zza;
        int r12 = this.zzc;
        this.zzc = r12 + 1;
        bArr4[r12] = (byte) j;
    }
}
