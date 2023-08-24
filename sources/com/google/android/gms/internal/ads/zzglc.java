package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzglc implements zzfzy {
    private static final Collection zza = Arrays.asList(64);
    private static final byte[] zzb = new byte[16];
    private final zzgmb zzc;
    private final byte[] zzd;

    public zzglc(byte[] bArr) throws GeneralSecurityException {
        if (zzgcy.zza(1)) {
            Collection collection = zza;
            int length = bArr.length;
            if (!collection.contains(Integer.valueOf(length))) {
                throw new InvalidKeyException("invalid key size: " + length + " bytes; key must have 64 bytes");
            }
            int r0 = length >> 1;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, r0);
            this.zzd = Arrays.copyOfRange(bArr, r0, length);
            this.zzc = new zzgmb(copyOfRange);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
    }

    @Override // com.google.android.gms.internal.ads.zzfzy
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzd;
        if (bArr.length > 2147483631) {
            throw new GeneralSecurityException("plaintext too long");
        }
        Cipher cipher = (Cipher) zzglp.zza.zza("AES/CTR/NoPadding");
        byte[][] bArr3 = {bArr2, bArr};
        byte[] zza2 = this.zzc.zza(zzb, 16);
        for (int r5 = 0; r5 <= 0; r5++) {
            byte[] bArr4 = bArr3[r5];
            if (bArr4 == null) {
                bArr4 = new byte[0];
            }
            zza2 = zzgle.zzd(zzgld.zzb(zza2), this.zzc.zza(bArr4, 16));
        }
        byte[] bArr5 = bArr3[1];
        int length = bArr5.length;
        if (length >= 16) {
            int length2 = zza2.length;
            if (length < length2) {
                throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
            }
            int r7 = length - length2;
            zzd = Arrays.copyOf(bArr5, length);
            for (int r52 = 0; r52 < zza2.length; r52++) {
                int r8 = r7 + r52;
                zzd[r8] = (byte) (zzd[r8] ^ zza2[r52]);
            }
        } else {
            zzd = zzgle.zzd(zzgld.zza(bArr5), zzgld.zzb(zza2));
        }
        byte[] zza3 = this.zzc.zza(zzd, 16);
        byte[] bArr6 = (byte[]) zza3.clone();
        bArr6[8] = (byte) (bArr6[8] & Byte.MAX_VALUE);
        bArr6[12] = (byte) (bArr6[12] & Byte.MAX_VALUE);
        cipher.init(1, new SecretKeySpec(this.zzd, "AES"), new IvParameterSpec(bArr6));
        return zzgle.zzc(zza3, cipher.doFinal(bArr));
    }
}
