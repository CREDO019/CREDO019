package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgcu extends zzgcs {
    public zzgcu(byte[] bArr) throws GeneralSecurityException {
        super(bArr);
    }

    @Override // com.google.android.gms.internal.ads.zzgcs
    final zzgcq zza(byte[] bArr, int r3) throws InvalidKeyException {
        return new zzgct(bArr, r3);
    }
}
