package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdt implements zzgdw {
    private final int zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgdt(int r4) throws InvalidAlgorithmParameterException {
        if (r4 == 16 || r4 == 32) {
            this.zza = r4;
            return;
        }
        throw new InvalidAlgorithmParameterException("Unsupported key length: " + r4);
    }

    @Override // com.google.android.gms.internal.ads.zzgdw
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzgdw
    public final byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        int length = bArr.length;
        if (length != this.zza) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + length);
        }
        return new zzgco(bArr, false).zzb(bArr2, bArr3, bArr4);
    }

    @Override // com.google.android.gms.internal.ads.zzgdw
    public final byte[] zzb() throws GeneralSecurityException {
        int r0 = this.zza;
        if (r0 != 16) {
            if (r0 == 32) {
                return zzgeh.zze;
            }
            throw new GeneralSecurityException("Could not determine HPKE AEAD ID");
        }
        return zzgeh.zzd;
    }
}
