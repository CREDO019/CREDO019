package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdu implements zzgdw {
    @Override // com.google.android.gms.internal.ads.zzgdw
    public final int zza() {
        return 32;
    }

    @Override // com.google.android.gms.internal.ads.zzgdw
    public final byte[] zzb() {
        return zzgeh.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzgdw
    public final byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length != 32) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: 32");
        }
        return new zzgcr(bArr).zzd(bArr2, bArr3, bArr4);
    }
}
