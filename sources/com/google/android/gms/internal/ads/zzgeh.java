package com.google.android.gms.internal.ads;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgeh {
    public static final byte[] zza = zzc(1, 0);
    public static final byte[] zzb = zzc(2, 32);
    public static final byte[] zzc = zzc(2, 1);
    public static final byte[] zzd = zzc(2, 1);
    public static final byte[] zze = zzc(2, 2);
    public static final byte[] zzf = zzc(2, 3);
    public static final byte[] zzg = new byte[0];
    private static final byte[] zzh = "KEM".getBytes(StandardCharsets.UTF_8);
    private static final byte[] zzi = "HPKE".getBytes(StandardCharsets.UTF_8);
    private static final byte[] zzj = "HPKE-v1".getBytes(StandardCharsets.UTF_8);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(zzgix zzgixVar) throws GeneralSecurityException {
        if (zzgixVar.zzg() == 2 || zzgixVar.zzg() == 1) {
            throw new GeneralSecurityException("Invalid KEM param: ".concat(zzgir.zza(zzgixVar.zzg())));
        }
        String str = "UNRECOGNIZED";
        if (zzgixVar.zzf() == 2 || zzgixVar.zzf() == 1) {
            int zzf2 = zzgixVar.zzf();
            if (zzf2 == 2) {
                str = "KDF_UNKNOWN";
            } else if (zzf2 == 3) {
                str = "HKDF_SHA256";
            }
            throw new GeneralSecurityException("Invalid KDF param: ".concat(str));
        } else if (zzgixVar.zze() == 2 || zzgixVar.zze() == 1) {
            int zze2 = zzgixVar.zze();
            if (zze2 == 2) {
                str = "AEAD_UNKNOWN";
            } else if (zze2 == 3) {
                str = "AES_128_GCM";
            } else if (zze2 == 4) {
                str = "AES_256_GCM";
            } else if (zze2 == 5) {
                str = "CHACHA20_POLY1305";
            }
            throw new GeneralSecurityException("Invalid AEAD param: ".concat(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzb(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return zzgle.zzc(zzi, bArr, bArr2, bArr3);
    }

    public static byte[] zzc(int r3, int r4) {
        byte[] bArr = new byte[r3];
        for (int r1 = 0; r1 < r3; r1++) {
            bArr[r1] = (byte) ((r4 >> (((r3 - r1) - 1) * 8)) & 255);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzd(byte[] bArr) throws GeneralSecurityException {
        return zzgle.zzc(zzh, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zze(String str, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzgle.zzc(zzj, bArr2, str.getBytes(StandardCharsets.UTF_8), bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzf(String str, byte[] bArr, byte[] bArr2, int r6) throws GeneralSecurityException {
        return zzgle.zzc(zzc(2, r6), zzj, bArr2, str.getBytes(StandardCharsets.UTF_8), bArr);
    }
}
