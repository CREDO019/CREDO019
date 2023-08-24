package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgns extends zzgnp {
    private final OutputStream zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgns(OutputStream outputStream, int r2) {
        super(r2);
        this.zzf = outputStream;
    }

    private final void zzL() throws IOException {
        this.zzf.write(this.zza, 0, this.zzc);
        this.zzc = 0;
    }

    private final void zzM(int r3) throws IOException {
        if (this.zzb - this.zzc < r3) {
            zzL();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzN() throws IOException {
        if (this.zzc > 0) {
            zzL();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzO(byte b) throws IOException {
        if (this.zzc == this.zzb) {
            zzL();
        }
        zzc(b);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzP(int r2, boolean z) throws IOException {
        zzM(11);
        zzf(r2 << 3);
        zzc(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzQ(int r1, zzgnf zzgnfVar) throws IOException {
        zzs((r1 << 3) | 2);
        zzs(zzgnfVar.zzd());
        zzgnfVar.zzo(this);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu, com.google.android.gms.internal.ads.zzgmu
    public final void zza(byte[] bArr, int r2, int r3) throws IOException {
        zzp(bArr, r2, r3);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzh(int r2, int r3) throws IOException {
        zzM(14);
        zzf((r2 << 3) | 5);
        zzd(r3);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzi(int r2) throws IOException {
        zzM(4);
        zzd(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzj(int r2, long j) throws IOException {
        zzM(18);
        zzf((r2 << 3) | 1);
        zze(j);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzk(long j) throws IOException {
        zzM(8);
        zze(j);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzl(int r2, int r3) throws IOException {
        zzM(20);
        zzf(r2 << 3);
        if (r3 >= 0) {
            zzf(r3);
        } else {
            zzg(r3);
        }
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
        zzv(str);
    }

    public final void zzp(byte[] bArr, int r5, int r6) throws IOException {
        int r0 = this.zzb;
        int r1 = this.zzc;
        int r02 = r0 - r1;
        if (r02 >= r6) {
            System.arraycopy(bArr, r5, this.zza, r1, r6);
            this.zzc += r6;
            this.zzd += r6;
            return;
        }
        System.arraycopy(bArr, r5, this.zza, r1, r02);
        int r52 = r5 + r02;
        int r62 = r6 - r02;
        this.zzc = this.zzb;
        this.zzd += r02;
        zzL();
        if (r62 <= this.zzb) {
            System.arraycopy(bArr, r52, this.zza, 0, r62);
            this.zzc = r62;
        } else {
            this.zzf.write(bArr, r52, r62);
        }
        this.zzd += r62;
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzq(int r1, int r2) throws IOException {
        zzs((r1 << 3) | r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzr(int r2, int r3) throws IOException {
        zzM(20);
        zzf(r2 << 3);
        zzf(r3);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzs(int r2) throws IOException {
        zzM(5);
        zzf(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzt(int r2, long j) throws IOException {
        zzM(20);
        zzf(r2 << 3);
        zzg(j);
    }

    @Override // com.google.android.gms.internal.ads.zzgnu
    public final void zzu(long j) throws IOException {
        zzM(10);
        zzg(j);
    }

    public final void zzv(String str) throws IOException {
        int zze;
        try {
            int length = str.length() * 3;
            int zzE = zzE(length);
            int r2 = zzE + length;
            int r3 = this.zzb;
            if (r2 <= r3) {
                if (r2 > r3 - this.zzc) {
                    zzL();
                }
                int zzE2 = zzE(str.length());
                int r22 = this.zzc;
                try {
                    if (zzE2 == zzE) {
                        int r1 = r22 + zzE2;
                        this.zzc = r1;
                        int zzd = zzgrw.zzd(str, this.zza, r1, this.zzb - r1);
                        this.zzc = r22;
                        zze = (zzd - r22) - zzE2;
                        zzf(zze);
                        this.zzc = zzd;
                    } else {
                        zze = zzgrw.zze(str);
                        zzf(zze);
                        this.zzc = zzgrw.zzd(str, this.zza, this.zzc, zze);
                    }
                    this.zzd += zze;
                    return;
                } catch (zzgrv e) {
                    this.zzd -= this.zzc - r22;
                    this.zzc = r22;
                    throw e;
                } catch (ArrayIndexOutOfBoundsException e2) {
                    throw new zzgnr(e2);
                }
            }
            byte[] bArr = new byte[length];
            int zzd2 = zzgrw.zzd(str, bArr, 0, length);
            zzs(zzd2);
            zzp(bArr, 0, zzd2);
        } catch (zzgrv e3) {
            zzJ(str, e3);
        }
    }
}
