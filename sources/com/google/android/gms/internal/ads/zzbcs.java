package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbcs extends zzbcn {
    private MessageDigest zzb;

    @Override // com.google.android.gms.internal.ads.zzbcn
    public final byte[] zzb(String str) {
        byte[] bArr;
        byte[] bArr2;
        String[] split = str.split(" ");
        int length = split.length;
        int r1 = 4;
        if (length == 1) {
            int zza = zzbcr.zza(split[0]);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(zza);
            bArr2 = allocate.array();
        } else {
            if (length >= 5) {
                bArr = new byte[length];
                for (int r2 = 0; r2 < split.length; r2++) {
                    int zza2 = zzbcr.zza(split[r2]);
                    bArr[r2] = (byte) ((zza2 >> 24) ^ (((zza2 & 255) ^ ((zza2 >> 8) & 255)) ^ ((zza2 >> 16) & 255)));
                }
            } else {
                bArr = new byte[length + length];
                for (int r4 = 0; r4 < split.length; r4++) {
                    int zza3 = zzbcr.zza(split[r4]);
                    int r5 = (zza3 >> 16) ^ ((char) zza3);
                    byte[] bArr3 = {(byte) r5, (byte) (r5 >> 8)};
                    int r52 = r4 + r4;
                    bArr[r52] = bArr3[0];
                    bArr[r52 + 1] = bArr3[1];
                }
            }
            bArr2 = bArr;
        }
        this.zzb = zza();
        synchronized (this.zza) {
            MessageDigest messageDigest = this.zzb;
            if (messageDigest == null) {
                return new byte[0];
            }
            messageDigest.reset();
            this.zzb.update(bArr2);
            byte[] digest = this.zzb.digest();
            int length2 = digest.length;
            if (length2 <= 4) {
                r1 = length2;
            }
            byte[] bArr4 = new byte[r1];
            System.arraycopy(digest, 0, bArr4, 0, r1);
            return bArr4;
        }
    }
}
