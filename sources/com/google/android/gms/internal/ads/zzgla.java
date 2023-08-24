package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgla implements zzfzs {
    private static final ThreadLocal zza = new zzgky();
    private static final ThreadLocal zzb = new zzgkz();
    private final byte[] zzc;
    private final byte[] zzd;
    private final SecretKeySpec zze;
    private final int zzf;

    public zzgla(byte[] bArr, int r5) throws GeneralSecurityException {
        if (!zzgcy.zza(1)) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        }
        if (r5 == 12 || r5 == 16) {
            this.zzf = r5;
            zzgmi.zza(bArr.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            this.zze = secretKeySpec;
            Cipher cipher = (Cipher) zza.get();
            cipher.init(1, secretKeySpec);
            byte[] zzc = zzc(cipher.doFinal(new byte[16]));
            this.zzc = zzc;
            this.zzd = zzc(zzc);
            return;
        }
        throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
    }

    private static byte[] zzc(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int r2 = 0;
        while (r2 < 15) {
            byte b = bArr[r2];
            int r4 = r2 + 1;
            bArr2[r2] = (byte) (((b + b) ^ ((bArr[r4] & 255) >>> 7)) & 255);
            r2 = r4;
        }
        byte b2 = bArr[15];
        bArr2[15] = (byte) (((bArr[0] >> 7) & TsExtractor.TS_STREAM_TYPE_E_AC3) ^ (b2 + b2));
        return bArr2;
    }

    private final byte[] zzd(Cipher cipher, int r8, byte[] bArr, int r10, int r11) throws IllegalBlockSizeException, BadPaddingException {
        int length;
        byte[] bArr2;
        byte[] bArr3 = new byte[16];
        bArr3[15] = (byte) r8;
        if (r11 == 0) {
            return cipher.doFinal(zze(bArr3, this.zzc));
        }
        byte[] doFinal = cipher.doFinal(bArr3);
        int r1 = 0;
        int r2 = 0;
        while (r11 - r2 > 16) {
            for (int r3 = 0; r3 < 16; r3++) {
                doFinal[r3] = (byte) (doFinal[r3] ^ bArr[(r10 + r2) + r3]);
            }
            doFinal = cipher.doFinal(doFinal);
            r2 += 16;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, r2 + r10, r10 + r11);
        if (copyOfRange.length == 16) {
            bArr2 = zze(copyOfRange, this.zzc);
        } else {
            byte[] copyOf = Arrays.copyOf(this.zzd, 16);
            while (true) {
                length = copyOfRange.length;
                if (r1 >= length) {
                    break;
                }
                copyOf[r1] = (byte) (copyOf[r1] ^ copyOfRange[r1]);
                r1++;
            }
            copyOf[length] = (byte) (copyOf[length] ^ 128);
            bArr2 = copyOf;
        }
        return cipher.doFinal(zze(doFinal, bArr2));
    }

    private static byte[] zze(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int r2 = 0; r2 < length; r2++) {
            bArr3[r2] = (byte) (bArr[r2] ^ bArr2[r2]);
        }
        return bArr3;
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int r1 = (length - this.zzf) - 16;
        if (r1 < 0) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        Cipher cipher = (Cipher) zza.get();
        cipher.init(1, this.zze);
        byte[] zzd = zzd(cipher, 0, bArr, 0, this.zzf);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] bArr3 = bArr2;
        byte[] zzd2 = zzd(cipher, 1, bArr3, 0, bArr3.length);
        byte[] zzd3 = zzd(cipher, 2, bArr, this.zzf, r1);
        int r0 = length - 16;
        byte b = 0;
        for (int r12 = 0; r12 < 16; r12++) {
            b = (byte) (b | (((bArr[r0 + r12] ^ zzd2[r12]) ^ zzd[r12]) ^ zzd3[r12]));
        }
        if (b != 0) {
            throw new AEADBadTagException("tag mismatch");
        }
        Cipher cipher2 = (Cipher) zzb.get();
        cipher2.init(1, this.zze, new IvParameterSpec(zzd));
        return cipher2.doFinal(bArr, this.zzf, r1);
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int r0 = this.zzf;
        if (length > (Integer.MAX_VALUE - r0) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr3 = new byte[r0 + length + 16];
        byte[] zza2 = zzgmg.zza(r0);
        System.arraycopy(zza2, 0, bArr3, 0, this.zzf);
        Cipher cipher = (Cipher) zza.get();
        cipher.init(1, this.zze);
        byte[] zzd = zzd(cipher, 0, zza2, 0, zza2.length);
        byte[] zzd2 = zzd(cipher, 1, bArr2, 0, 0);
        Cipher cipher2 = (Cipher) zzb.get();
        cipher2.init(1, this.zze, new IvParameterSpec(zzd));
        cipher2.doFinal(bArr, 0, length, bArr3, this.zzf);
        byte[] zzd3 = zzd(cipher, 2, bArr3, this.zzf, length);
        int r8 = length + this.zzf;
        for (int r11 = 0; r11 < 16; r11++) {
            bArr3[r8 + r11] = (byte) ((zzd2[r11] ^ zzd[r11]) ^ zzd3[r11]);
        }
        return bArr3;
    }
}
