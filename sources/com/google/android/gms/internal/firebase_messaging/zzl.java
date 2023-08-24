package com.google.android.gms.internal.firebase_messaging;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

/* compiled from: com.google.firebase:firebase-messaging@@22.0.0 */
/* loaded from: classes.dex */
public final class zzl {
    private static final OutputStream zza = new zzj();

    public static byte[] zza(InputStream inputStream) throws IOException {
        ArrayDeque arrayDeque = new ArrayDeque(20);
        int r2 = 8192;
        int r3 = 0;
        while (r3 < 2147483639) {
            int min = Math.min(r2, 2147483639 - r3);
            byte[] bArr = new byte[min];
            arrayDeque.add(bArr);
            int r7 = 0;
            while (r7 < min) {
                int read = inputStream.read(bArr, r7, min - r7);
                if (read == -1) {
                    return zzc(arrayDeque, r3);
                }
                r7 += read;
                r3 += read;
            }
            long j = r2;
            long j2 = j + j;
            r2 = j2 > 2147483647L ? Integer.MAX_VALUE : j2 < -2147483648L ? Integer.MIN_VALUE : (int) j2;
        }
        if (inputStream.read() == -1) {
            return zzc(arrayDeque, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    public static InputStream zzb(InputStream inputStream, long j) {
        return new zzk(inputStream, 1048577L);
    }

    private static byte[] zzc(Queue<byte[]> queue, int r7) {
        byte[] bArr = new byte[r7];
        int r1 = r7;
        while (r1 > 0) {
            byte[] remove = queue.remove();
            int min = Math.min(r1, remove.length);
            System.arraycopy(remove, 0, bArr, r7 - r1, min);
            r1 -= min;
        }
        return bArr;
    }
}
