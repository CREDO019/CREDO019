package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgme implements zzgaq {
    private final zzgfd zza;
    private final int zzb;

    public zzgme(zzgfd zzgfdVar, int r3) throws GeneralSecurityException {
        this.zza = zzgfdVar;
        this.zzb = r3;
        if (r3 < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        zzgfdVar.zza(new byte[0], r3);
    }

    @Override // com.google.android.gms.internal.ads.zzgaq
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zzgle.zzb(zzb(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgaq
    public final byte[] zzb(byte[] bArr) throws GeneralSecurityException {
        return this.zza.zza(bArr, this.zzb);
    }
}
