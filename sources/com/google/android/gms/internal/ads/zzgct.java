package com.google.android.gms.internal.ads;

import java.security.InvalidKeyException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgct extends zzgcq {
    public zzgct(byte[] bArr, int r2) throws InvalidKeyException {
        super(bArr, r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgcq
    final int zza() {
        return 24;
    }

    @Override // com.google.android.gms.internal.ads.zzgcq
    final int[] zzb(int[] r12, int r13) {
        int length = r12.length;
        if (length != 6) {
            throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(length * 32)));
        }
        int[] r4 = new int[16];
        zzgcm.zzb(r0, this.zza);
        int[] r0 = {0, 0, 0, 0, r0[12], r0[13], r0[14], r0[15], 0, 0, 0, 0, r12[0], r12[1], r12[2], r12[3]};
        zzgcm.zzc(r0);
        zzgcm.zzb(r4, Arrays.copyOf(r0, 8));
        r4[12] = r13;
        r4[13] = 0;
        r4[14] = r12[4];
        r4[15] = r12[5];
        return r4;
    }
}
