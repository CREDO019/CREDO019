package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfwx {
    private static final OutputStream zza = new zzfww();

    private static byte[] zzb(Queue queue, int r7) {
        if (queue.isEmpty()) {
            return new byte[0];
        }
        byte[] bArr = (byte[]) queue.remove();
        int length = bArr.length;
        if (length == r7) {
            return bArr;
        }
        int r2 = r7 - length;
        byte[] copyOf = Arrays.copyOf(bArr, r7);
        while (r2 > 0) {
            byte[] bArr2 = (byte[]) queue.remove();
            int min = Math.min(r2, bArr2.length);
            System.arraycopy(bArr2, 0, copyOf, r7 - r2, min);
            r2 -= min;
        }
        return copyOf;
    }

    public static byte[] zza(InputStream inputStream) throws IOException {
        Objects.requireNonNull(inputStream);
        ArrayDeque arrayDeque = new ArrayDeque(20);
        int highestOneBit = Integer.highestOneBit(0);
        int min = Math.min(8192, Math.max(128, highestOneBit + highestOneBit));
        int r3 = 0;
        while (r3 < 2147483639) {
            int min2 = Math.min(min, 2147483639 - r3);
            byte[] bArr = new byte[min2];
            arrayDeque.add(bArr);
            int r7 = 0;
            while (r7 < min2) {
                int read = inputStream.read(bArr, r7, min2 - r7);
                if (read == -1) {
                    return zzb(arrayDeque, r3);
                }
                r7 += read;
                r3 += read;
            }
            long j = min * (min < 4096 ? 4 : 2);
            min = j > 2147483647L ? Integer.MAX_VALUE : j < -2147483648L ? Integer.MIN_VALUE : (int) j;
        }
        if (inputStream.read() == -1) {
            return zzb(arrayDeque, 2147483639);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }
}
