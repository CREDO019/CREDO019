package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgkx implements zzgma {
    private static final ThreadLocal zza = new zzgkw();
    private final SecretKeySpec zzb;
    private final int zzc;
    private final int zzd;

    public zzgkx(byte[] bArr, int r4) throws GeneralSecurityException {
        if (!zzgcy.zza(2)) {
            throw new GeneralSecurityException("Can not use AES-CTR in FIPS-mode, as BoringCrypto module is not available.");
        }
        zzgmi.zza(bArr.length);
        this.zzb = new SecretKeySpec(bArr, "AES");
        int blockSize = ((Cipher) zza.get()).getBlockSize();
        this.zzd = blockSize;
        if (r4 < 12 || r4 > blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzc = r4;
    }

    private final void zzc(byte[] bArr, int r9, int r10, byte[] bArr2, int r12, byte[] bArr3, boolean z) throws GeneralSecurityException {
        Cipher cipher = (Cipher) zza.get();
        byte[] bArr4 = new byte[this.zzd];
        System.arraycopy(bArr3, 0, bArr4, 0, this.zzc);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr4);
        if (z) {
            cipher.init(1, this.zzb, ivParameterSpec);
        } else {
            cipher.init(2, this.zzb, ivParameterSpec);
        }
        if (cipher.doFinal(bArr, r9, r10, bArr2, r12) != r10) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgma
    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int r1 = this.zzc;
        if (length >= r1) {
            byte[] bArr2 = new byte[r1];
            System.arraycopy(bArr, 0, bArr2, 0, r1);
            int r4 = this.zzc;
            int r5 = length - r4;
            byte[] bArr3 = new byte[r5];
            zzc(bArr, r4, r5, bArr3, 0, bArr2, false);
            return bArr3;
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    @Override // com.google.android.gms.internal.ads.zzgma
    public final byte[] zzb(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int r0 = this.zzc;
        if (length > Integer.MAX_VALUE - r0) {
            int r02 = this.zzc;
            throw new GeneralSecurityException("plaintext length can not exceed " + (Integer.MAX_VALUE - r02));
        }
        byte[] bArr2 = new byte[r0 + length];
        byte[] zza2 = zzgmg.zza(r0);
        System.arraycopy(zza2, 0, bArr2, 0, this.zzc);
        zzc(bArr, 0, length, bArr2, this.zzc, zza2, true);
        return bArr2;
    }
}
