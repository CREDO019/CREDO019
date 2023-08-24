package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzglo implements zzfzs {
    private final zzgma zza;
    private final zzgaq zzb;
    private final int zzc;

    public zzglo(zzgma zzgmaVar, zzgaq zzgaqVar, int r3) {
        this.zza = zzgmaVar;
        this.zzb = zzgaqVar;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int r1 = this.zzc;
        if (length < r1) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, length - r1);
        byte[] copyOfRange2 = Arrays.copyOfRange(bArr, length - this.zzc, length);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        this.zzb.zza(copyOfRange2, zzgle.zzc(bArr2, copyOfRange, Arrays.copyOf(ByteBuffer.allocate(8).putLong(bArr2.length * 8).array(), 8)));
        return this.zza.zza(copyOfRange);
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzb = this.zza.zzb(bArr);
        return zzgle.zzc(zzb, this.zzb.zzb(zzgle.zzc(bArr2, zzb, Arrays.copyOf(ByteBuffer.allocate(8).putLong(0L).array(), 8))));
    }
}
