package com.google.android.gms.internal.ads;

import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgcp extends zzgcq {
    public zzgcp(byte[] bArr, int r2) throws InvalidKeyException {
        super(bArr, r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgcq
    public final int zza() {
        return 12;
    }

    @Override // com.google.android.gms.internal.ads.zzgcq
    public final int[] zzb(int[] r5, int r6) {
        int length = r5.length;
        if (length != 3) {
            throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", Integer.valueOf(length * 32)));
        }
        int[] r0 = new int[16];
        zzgcm.zzb(r0, this.zza);
        r0[12] = r6;
        System.arraycopy(r5, 0, r0, 13, 3);
        return r0;
    }
}
