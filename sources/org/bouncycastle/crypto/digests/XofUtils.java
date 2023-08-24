package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class XofUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] encode(byte b) {
        return Arrays.concatenate(leftEncode(8L), new byte[]{b});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] encode(byte[] bArr, int r3, int r4) {
        return bArr.length == r4 ? Arrays.concatenate(leftEncode(r4 * 8), bArr) : Arrays.concatenate(leftEncode(r4 * 8), Arrays.copyOfRange(bArr, r3, r4 + r3));
    }

    public static byte[] leftEncode(long j) {
        byte b = 1;
        long j2 = j;
        while (true) {
            j2 >>= 8;
            if (j2 == 0) {
                break;
            }
            b = (byte) (b + 1);
        }
        byte[] bArr = new byte[b + 1];
        bArr[0] = b;
        for (int r0 = 1; r0 <= b; r0++) {
            bArr[r0] = (byte) (j >> ((b - r0) * 8));
        }
        return bArr;
    }

    public static byte[] rightEncode(long j) {
        byte b = 1;
        long j2 = j;
        while (true) {
            j2 >>= 8;
            if (j2 == 0) {
                break;
            }
            b = (byte) (b + 1);
        }
        byte[] bArr = new byte[b + 1];
        bArr[b] = b;
        for (int r2 = 0; r2 < b; r2++) {
            bArr[r2] = (byte) (j >> (((b - r2) - 1) * 8));
        }
        return bArr;
    }
}
