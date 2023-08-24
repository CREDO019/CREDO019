package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgmb implements zzgfd {
    private final SecretKey zza;
    private final byte[] zzb;
    private final byte[] zzc;

    public zzgmb(byte[] bArr) throws GeneralSecurityException {
        zzgmi.zza(bArr.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        this.zza = secretKeySpec;
        Cipher zzb = zzb();
        zzb.init(1, secretKeySpec);
        byte[] zzb2 = zzgld.zzb(zzb.doFinal(new byte[16]));
        this.zzb = zzb2;
        this.zzc = zzgld.zzb(zzb2);
    }

    private static Cipher zzb() throws GeneralSecurityException {
        if (!zzgcy.zza(1)) {
            throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
        }
        return (Cipher) zzglp.zza.zza("AES/ECB/NoPadding");
    }

    @Override // com.google.android.gms.internal.ads.zzgfd
    public final byte[] zza(byte[] bArr, int r10) throws GeneralSecurityException {
        byte[] zzd;
        if (r10 > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher zzb = zzb();
        zzb.init(1, this.zza);
        int length = bArr.length;
        int max = Math.max(1, (int) Math.ceil(length / 16.0d));
        if (max * 16 == length) {
            zzd = zzgle.zze(bArr, (max - 1) * 16, this.zzb, 0, 16);
        } else {
            zzd = zzgle.zzd(zzgld.zza(Arrays.copyOfRange(bArr, (max - 1) * 16, length)), this.zzc);
        }
        byte[] bArr2 = new byte[16];
        for (int r6 = 0; r6 < max - 1; r6++) {
            bArr2 = zzb.doFinal(zzgle.zze(bArr2, 0, bArr, r6 * 16, 16));
        }
        return Arrays.copyOf(zzb.doFinal(zzgle.zzd(zzd, bArr2)), r10);
    }
}
