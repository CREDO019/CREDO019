package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzgcq {
    int[] zza;
    private final int zzb;

    public zzgcq(byte[] bArr, int r4) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.zza = zzgcm.zzd(bArr);
        this.zzb = r4;
    }

    private final void zzf(byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws GeneralSecurityException {
        if (bArr.length != zza()) {
            throw new GeneralSecurityException("The nonce length (in bytes) must be " + zza());
        }
        int remaining = byteBuffer2.remaining();
        int r1 = (remaining / 64) + 1;
        for (int r2 = 0; r2 < r1; r2++) {
            ByteBuffer zzc = zzc(bArr, this.zzb + r2);
            if (r2 == r1 - 1) {
                zzgle.zza(byteBuffer, byteBuffer2, zzc, remaining % 64);
            } else {
                zzgle.zza(byteBuffer, byteBuffer2, zzc, 64);
            }
        }
    }

    abstract int zza();

    abstract int[] zzb(int[] r1, int r2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ByteBuffer zzc(byte[] bArr, int r6) {
        int[] zzb = zzb(zzgcm.zzd(bArr), r6);
        int[] r62 = (int[]) zzb.clone();
        zzgcm.zzc(r62);
        for (int r1 = 0; r1 < 16; r1++) {
            zzb[r1] = zzb[r1] + r62[r1];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(zzb, 0, 16);
        return order;
    }

    public final void zzd(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() < bArr2.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        zzf(bArr, byteBuffer, ByteBuffer.wrap(bArr2));
    }

    public final byte[] zze(byte[] bArr, ByteBuffer byteBuffer) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        zzf(bArr, allocate, byteBuffer);
        return allocate.array();
    }
}
