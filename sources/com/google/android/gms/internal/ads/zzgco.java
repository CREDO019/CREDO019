package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgco {
    private static final ThreadLocal zza = new zzgcn();
    private final SecretKey zzb;
    private final boolean zzc;

    public zzgco(byte[] bArr, boolean z) throws GeneralSecurityException {
        if (!zzgcy.zza(2)) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        zzgmi.zza(bArr.length);
        this.zzb = new SecretKeySpec(bArr, "AES");
        this.zzc = z;
    }

    private static AlgorithmParameterSpec zzc(byte[] bArr, int r3, int r4) throws GeneralSecurityException {
        if (!zzgmh.zzb() || zzgmh.zza() > 19) {
            return new GCMParameterSpec(128, bArr, 0, 12);
        }
        return new IvParameterSpec(bArr, 0, 12);
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length == 12) {
            boolean z = this.zzc;
            int r3 = true != z ? 16 : 28;
            int length = bArr2.length;
            if (length < r3) {
                throw new GeneralSecurityException("ciphertext too short");
            }
            if (!z || ByteBuffer.wrap(bArr).equals(ByteBuffer.wrap(bArr2, 0, 12))) {
                AlgorithmParameterSpec zzc = zzc(bArr, 0, 12);
                ThreadLocal threadLocal = zza;
                ((Cipher) threadLocal.get()).init(2, this.zzb, zzc);
                if (bArr3 != null && bArr3.length != 0) {
                    ((Cipher) threadLocal.get()).updateAAD(bArr3);
                }
                boolean z2 = this.zzc;
                int r1 = true != z2 ? 0 : 12;
                if (z2) {
                    length -= 12;
                }
                return ((Cipher) threadLocal.get()).doFinal(bArr2, r1, length);
            }
            throw new GeneralSecurityException("iv does not match prepended iv");
        }
        throw new GeneralSecurityException("iv is wrong size");
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        int length = bArr2.length;
        if (length <= 2147483619) {
            boolean z = this.zzc;
            byte[] bArr4 = new byte[z ? length + 28 : length + 16];
            if (z) {
                System.arraycopy(bArr, 0, bArr4, 0, 12);
            }
            AlgorithmParameterSpec zzc = zzc(bArr, 0, 12);
            ThreadLocal threadLocal = zza;
            ((Cipher) threadLocal.get()).init(1, this.zzb, zzc);
            int doFinal = ((Cipher) threadLocal.get()).doFinal(bArr2, 0, length, bArr4, true != this.zzc ? 0 : 12);
            if (doFinal == length + 16) {
                return bArr4;
            }
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(doFinal - length)));
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
