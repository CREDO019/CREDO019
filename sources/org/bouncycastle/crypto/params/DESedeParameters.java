package org.bouncycastle.crypto.params;

/* loaded from: classes5.dex */
public class DESedeParameters extends DESParameters {
    public static final int DES_EDE_KEY_LENGTH = 24;

    public DESedeParameters(byte[] bArr) {
        super(bArr);
        if (isWeakKey(bArr, 0, bArr.length)) {
            throw new IllegalArgumentException("attempt to create weak DESede key");
        }
    }

    public static boolean isReal2Key(byte[] bArr, int r5) {
        boolean z = false;
        for (int r1 = r5; r1 != r5 + 8; r1++) {
            if (bArr[r1] != bArr[r1 + 8]) {
                z = true;
            }
        }
        return z;
    }

    public static boolean isReal3Key(byte[] bArr, int r11) {
        int r1 = r11;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            boolean z4 = true;
            if (r1 == r11 + 8) {
                break;
            }
            int r7 = r1 + 8;
            z |= bArr[r1] != bArr[r7];
            int r8 = r1 + 16;
            z2 |= bArr[r1] != bArr[r8];
            if (bArr[r7] == bArr[r8]) {
                z4 = false;
            }
            z3 |= z4;
            r1++;
        }
        return z && z2 && z3;
    }

    public static boolean isRealEDEKey(byte[] bArr, int r3) {
        return bArr.length == 16 ? isReal2Key(bArr, r3) : isReal3Key(bArr, r3);
    }

    public static boolean isWeakKey(byte[] bArr, int r2) {
        return isWeakKey(bArr, r2, bArr.length - r2);
    }

    public static boolean isWeakKey(byte[] bArr, int r2, int r3) {
        while (r2 < r3) {
            if (DESParameters.isWeakKey(bArr, r2)) {
                return true;
            }
            r2 += 8;
        }
        return false;
    }
}
