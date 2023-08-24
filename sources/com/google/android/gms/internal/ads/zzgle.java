package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgle {
    public static final void zza(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int r6) {
        if (r6 < 0 || byteBuffer2.remaining() < r6 || byteBuffer3.remaining() < r6 || byteBuffer.remaining() < r6) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for (int r0 = 0; r0 < r6; r0++) {
            byteBuffer.put((byte) (byteBuffer2.get() ^ byteBuffer3.get()));
        }
    }

    public static final boolean zzb(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && bArr.length == bArr2.length) {
            int r2 = 0;
            for (int r1 = 0; r1 < bArr.length; r1++) {
                r2 |= bArr[r1] ^ bArr2[r1];
            }
            if (r2 == 0) {
                return true;
            }
        }
        return false;
    }

    public static byte[] zzc(byte[]... bArr) throws GeneralSecurityException {
        int r3 = 0;
        for (byte[] bArr2 : bArr) {
            int length = bArr2.length;
            if (r3 > Integer.MAX_VALUE - length) {
                throw new GeneralSecurityException("exceeded size limit");
            }
            r3 += length;
        }
        byte[] bArr3 = new byte[r3];
        int r4 = 0;
        for (byte[] bArr4 : bArr) {
            int length2 = bArr4.length;
            System.arraycopy(bArr4, 0, bArr3, r4, length2);
            r4 += length2;
        }
        return bArr3;
    }

    public static final byte[] zzd(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (length != bArr2.length) {
            throw new IllegalArgumentException("The lengths of x and y should match.");
        }
        return zze(bArr, 0, bArr2, 0, length);
    }

    public static final byte[] zze(byte[] bArr, int r5, byte[] bArr2, int r7, int r8) {
        if (bArr.length - r8 < r5 || bArr2.length - r8 < r7) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        byte[] bArr3 = new byte[r8];
        for (int r1 = 0; r1 < r8; r1++) {
            bArr3[r1] = (byte) (bArr[r1 + r5] ^ bArr2[r1 + r7]);
        }
        return bArr3;
    }
}
