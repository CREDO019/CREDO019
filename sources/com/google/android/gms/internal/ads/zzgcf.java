package com.google.android.gms.internal.ads;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgcf implements zzfzs {
    private static final byte[] zza = new byte[0];
    private final zzgjl zzb;
    private final zzfzs zzc;

    public zzgcf(zzgjl zzgjlVar, zzfzs zzfzsVar) {
        this.zzb = zzgjlVar;
        this.zzc = zzfzsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int r2 = wrap.getInt();
            if (r2 <= 0 || r2 > bArr.length - 4) {
                throw new GeneralSecurityException("invalid ciphertext");
            }
            byte[] bArr3 = new byte[r2];
            wrap.get(bArr3, 0, r2);
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4, 0, wrap.remaining());
            return ((zzfzs) zzgbe.zzi(this.zzb.zzf(), this.zzc.zza(bArr3, zza), zzfzs.class)).zza(bArr4, bArr2);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e) {
            throw new GeneralSecurityException("invalid ciphertext", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzaw = zzgbe.zzd(this.zzb).zzaw();
        byte[] zzb = this.zzc.zzb(zzaw, zza);
        byte[] zzb2 = ((zzfzs) zzgbe.zzi(this.zzb.zzf(), zzaw, zzfzs.class)).zzb(bArr, bArr2);
        int length = zzb.length;
        return ByteBuffer.allocate(length + 4 + zzb2.length).putInt(length).put(zzb).put(zzb2).array();
    }
}
