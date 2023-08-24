package org.bouncycastle.crypto.modes;

import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
class GOST3413CipherUtil {
    GOST3413CipherUtil() {
    }

    public static byte[] LSB(byte[] bArr, int r4) {
        byte[] bArr2 = new byte[r4];
        System.arraycopy(bArr, bArr.length - r4, bArr2, 0, r4);
        return bArr2;
    }

    public static byte[] MSB(byte[] bArr, int r1) {
        return Arrays.copyOf(bArr, r1);
    }

    public static byte[] copyFromInput(byte[] bArr, int r3, int r4) {
        if (bArr.length < r3 + r4) {
            r3 = bArr.length - r4;
        }
        byte[] bArr2 = new byte[r3];
        System.arraycopy(bArr, r4, bArr2, 0, r3);
        return bArr2;
    }

    public static byte[] sum(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            bArr3[r1] = (byte) (bArr[r1] ^ bArr2[r1]);
        }
        return bArr3;
    }
}
