package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdx {
    private static final byte[] zza = new byte[0];
    private final zzgdw zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private BigInteger zzg = BigInteger.ZERO;

    private zzgdx(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzgdw zzgdwVar) {
        this.zzf = bArr;
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzgdwVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgdx zzc(byte[] bArr, byte[] bArr2, zzgei zzgeiVar, zzgdv zzgdvVar, zzgdw zzgdwVar, byte[] bArr3) throws GeneralSecurityException {
        byte[] bArr4 = zzgeh.zzc;
        if (Arrays.equals(bArr4, bArr4)) {
            byte[] zzb = zzgeh.zzb(zzgeh.zzb, zzgeh.zzc, zzgdwVar.zzb());
            byte[] bArr5 = zzgeh.zzg;
            byte[] bArr6 = zza;
            byte[] zzc = zzgle.zzc(zzgeh.zza, zzgdvVar.zzd(bArr5, bArr6, "psk_id_hash", zzb), zzgdvVar.zzd(zzgeh.zzg, bArr3, "info_hash", zzb));
            byte[] zzd = zzgdvVar.zzd(bArr2, bArr6, "secret", zzb);
            return new zzgdx(bArr, zzgdvVar.zzc(zzd, zzc, "key", zzb, zzgdwVar.zza()), zzgdvVar.zzc(zzd, zzc, "base_nonce", zzb, 12), BigInteger.ONE.shiftLeft(96).subtract(BigInteger.ONE), zzgdwVar);
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }

    private final synchronized byte[] zzd() throws GeneralSecurityException {
        byte[] zzd;
        byte[] bArr = this.zze;
        byte[] byteArray = this.zzg.toByteArray();
        int length = byteArray.length;
        if (length != 12) {
            if (length > 13) {
                throw new GeneralSecurityException("integer too large");
            }
            if (length == 13) {
                if (byteArray[0] == 0) {
                    byteArray = Arrays.copyOfRange(byteArray, 1, 13);
                } else {
                    throw new GeneralSecurityException("integer too large");
                }
            } else {
                byte[] bArr2 = new byte[12];
                System.arraycopy(byteArray, 0, bArr2, 12 - length, length);
                byteArray = bArr2;
            }
        }
        zzd = zzgle.zzd(bArr, byteArray);
        if (this.zzg.compareTo(this.zzc) >= 0) {
            throw new GeneralSecurityException("message limit reached");
        }
        this.zzg = this.zzg.add(BigInteger.ONE);
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] zza() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zzc(this.zzd, zzd(), bArr, bArr2);
    }
}
