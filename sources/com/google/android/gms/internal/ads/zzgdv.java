package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdv {
    private final String zza = "HmacSha256";

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgdv(String str) {
    }

    private final byte[] zze(byte[] bArr, byte[] bArr2, int r10) throws GeneralSecurityException {
        Mac mac = (Mac) zzglp.zzb.zza(this.zza);
        if (r10 > mac.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        byte[] bArr3 = new byte[r10];
        mac.init(new SecretKeySpec(bArr, this.zza));
        byte[] bArr4 = new byte[0];
        int r3 = 1;
        int r4 = 0;
        while (true) {
            mac.update(bArr4);
            mac.update(bArr2);
            mac.update((byte) r3);
            bArr4 = mac.doFinal();
            int length = bArr4.length;
            int r6 = r4 + length;
            if (r6 < r10) {
                System.arraycopy(bArr4, 0, bArr3, r4, length);
                r3++;
                r4 = r6;
            } else {
                System.arraycopy(bArr4, 0, bArr3, r4, r10 - r4);
                return bArr3;
            }
        }
    }

    private final byte[] zzf(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Mac mac = (Mac) zzglp.zzb.zza(this.zza);
        if (bArr2 == null || bArr2.length == 0) {
            mac.init(new SecretKeySpec(new byte[mac.getMacLength()], this.zza));
        } else {
            mac.init(new SecretKeySpec(bArr2, this.zza));
        }
        return mac.doFinal(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zza() throws GeneralSecurityException {
        return Mac.getInstance(this.zza).getMacLength();
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, String str2, byte[] bArr4, int r7) throws GeneralSecurityException {
        return zze(zzf(zzgeh.zze("eae_prk", bArr2, bArr4), null), zzgeh.zzf("shared_secret", bArr3, bArr4, r7), r7);
    }

    public final byte[] zzc(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int r5) throws GeneralSecurityException {
        return zze(bArr, zzgeh.zzf(str, bArr2, bArr3, r5), r5);
    }

    public final byte[] zzd(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) throws GeneralSecurityException {
        return zzf(zzgeh.zze(str, bArr2, bArr3), bArr);
    }
}
