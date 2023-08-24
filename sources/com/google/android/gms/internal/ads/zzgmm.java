package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgmm {
    private final byte[] zza = new byte[256];
    private int zzb;
    private int zzc;

    public zzgmm(byte[] bArr) {
        for (int r2 = 0; r2 < 256; r2++) {
            this.zza[r2] = (byte) r2;
        }
        int r3 = 0;
        for (int r22 = 0; r22 < 256; r22++) {
            byte[] bArr2 = this.zza;
            byte b = bArr2[r22];
            r3 = (r3 + b + bArr[r22 % bArr.length]) & 255;
            bArr2[r22] = bArr2[r3];
            bArr2[r3] = b;
        }
        this.zzb = 0;
        this.zzc = 0;
    }

    public final void zza(byte[] bArr) {
        int r0 = this.zzb;
        int r1 = this.zzc;
        for (int r2 = 0; r2 < 256; r2++) {
            r0 = (r0 + 1) & 255;
            byte[] bArr2 = this.zza;
            byte b = bArr2[r0];
            r1 = (r1 + b) & 255;
            bArr2[r0] = bArr2[r1];
            bArr2[r1] = b;
            bArr[r2] = (byte) (bArr2[(bArr2[r0] + b) & 255] ^ bArr[r2]);
        }
        this.zzb = r0;
        this.zzc = r1;
    }
}
