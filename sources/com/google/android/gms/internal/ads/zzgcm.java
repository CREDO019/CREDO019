package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import org.apache.commons.fileupload.MultipartStream;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgcm {
    private static final int[] zza = zzd(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, MultipartStream.DASH, 98, 121, 116, 101, 32, 107});

    static void zza(int[] r2, int r3, int r4, int r5, int r6) {
        int r0 = r2[r3] + r2[r4];
        r2[r3] = r0;
        int r02 = r0 ^ r2[r6];
        int r03 = (r02 >>> (-16)) | (r02 << 16);
        r2[r6] = r03;
        int r1 = r2[r5] + r03;
        r2[r5] = r1;
        int r04 = r2[r4] ^ r1;
        int r05 = (r04 >>> (-12)) | (r04 << 12);
        r2[r4] = r05;
        int r12 = r2[r3] + r05;
        r2[r3] = r12;
        int r32 = r2[r6] ^ r12;
        int r33 = (r32 >>> (-8)) | (r32 << 8);
        r2[r6] = r33;
        int r62 = r2[r5] + r33;
        r2[r5] = r62;
        int r34 = r2[r4] ^ r62;
        r2[r4] = (r34 >>> (-7)) | (r34 << 7);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(int[] r3, int[] r4) {
        int[] r0 = zza;
        System.arraycopy(r0, 0, r3, 0, r0.length);
        System.arraycopy(r4, 0, r3, r0.length, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzc(int[] r16) {
        for (int r2 = 0; r2 < 10; r2++) {
            zza(r16, 0, 4, 8, 12);
            zza(r16, 1, 5, 9, 13);
            zza(r16, 2, 6, 10, 14);
            zza(r16, 3, 7, 11, 15);
            zza(r16, 0, 5, 10, 15);
            zza(r16, 1, 6, 11, 12);
            zza(r16, 2, 7, 8, 13);
            zza(r16, 3, 4, 9, 14);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] zzd(byte[] bArr) {
        IntBuffer asIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] r0 = new int[asIntBuffer.remaining()];
        asIntBuffer.get(r0);
        return r0;
    }
}
