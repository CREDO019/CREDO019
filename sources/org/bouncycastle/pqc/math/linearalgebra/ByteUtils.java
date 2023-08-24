package org.bouncycastle.pqc.math.linearalgebra;

import com.google.common.base.Ascii;

/* loaded from: classes4.dex */
public final class ByteUtils {
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private ByteUtils() {
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static byte[] concatenate(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] concatenate(byte[][] bArr) {
        int length = bArr[0].length;
        byte[] bArr2 = new byte[bArr.length * length];
        int r4 = 0;
        for (byte[] bArr3 : bArr) {
            System.arraycopy(bArr3, 0, bArr2, r4, length);
            r4 += length;
        }
        return bArr2;
    }

    public static int deepHashCode(byte[] bArr) {
        int r0 = 1;
        for (byte b : bArr) {
            r0 = (r0 * 31) + b;
        }
        return r0;
    }

    public static int deepHashCode(byte[][] bArr) {
        int r0 = 1;
        for (byte[] bArr2 : bArr) {
            r0 = (r0 * 31) + deepHashCode(bArr2);
        }
        return r0;
    }

    public static int deepHashCode(byte[][][] bArr) {
        int r0 = 1;
        for (byte[][] bArr2 : bArr) {
            r0 = (r0 * 31) + deepHashCode(bArr2);
        }
        return r0;
    }

    public static boolean equals(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2 == null;
        } else if (bArr2 != null && bArr.length == bArr2.length) {
            boolean z = true;
            for (int length = bArr.length - 1; length >= 0; length--) {
                z &= bArr[length] == bArr2[length];
            }
            return z;
        } else {
            return false;
        }
    }

    public static boolean equals(byte[][] bArr, byte[][] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = bArr.length - 1; length >= 0; length--) {
            z &= equals(bArr[length], bArr2[length]);
        }
        return z;
    }

    public static boolean equals(byte[][][] bArr, byte[][][] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        boolean z = true;
        for (int length = bArr.length - 1; length >= 0; length--) {
            if (bArr[length].length != bArr2[length].length) {
                return false;
            }
            for (int length2 = bArr[length].length - 1; length2 >= 0; length2--) {
                z &= equals(bArr[length][length2], bArr2[length][length2]);
            }
        }
        return z;
    }

    public static byte[] fromHexString(String str) {
        char[] charArray = str.toUpperCase().toCharArray();
        int r2 = 0;
        for (int r1 = 0; r1 < charArray.length; r1++) {
            if ((charArray[r1] >= '0' && charArray[r1] <= '9') || (charArray[r1] >= 'A' && charArray[r1] <= 'F')) {
                r2++;
            }
        }
        byte[] bArr = new byte[(r2 + 1) >> 1];
        int r22 = r2 & 1;
        for (int r0 = 0; r0 < charArray.length; r0++) {
            if (charArray[r0] < '0' || charArray[r0] > '9') {
                if (charArray[r0] >= 'A' && charArray[r0] <= 'F') {
                    int r3 = r22 >> 1;
                    bArr[r3] = (byte) (bArr[r3] << 4);
                    bArr[r3] = (byte) (bArr[r3] | ((charArray[r0] - 'A') + 10));
                }
            } else {
                int r32 = r22 >> 1;
                bArr[r32] = (byte) (bArr[r32] << 4);
                bArr[r32] = (byte) (bArr[r32] | (charArray[r0] - '0'));
            }
            r22++;
        }
        return bArr;
    }

    public static byte[][] split(byte[] bArr, int r5) throws ArrayIndexOutOfBoundsException {
        if (r5 <= bArr.length) {
            byte[][] bArr2 = {new byte[r5], new byte[bArr.length - r5]};
            System.arraycopy(bArr, 0, bArr2[0], 0, r5);
            System.arraycopy(bArr, r5, bArr2[1], 0, bArr.length - r5);
            return bArr2;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public static byte[] subArray(byte[] bArr, int r2) {
        return subArray(bArr, r2, bArr.length);
    }

    public static byte[] subArray(byte[] bArr, int r3, int r4) {
        int r42 = r4 - r3;
        byte[] bArr2 = new byte[r42];
        System.arraycopy(bArr, r3, bArr2, 0, r42);
        return bArr2;
    }

    public static String toBinaryString(byte[] bArr) {
        String str = "";
        for (int r2 = 0; r2 < bArr.length; r2++) {
            byte b = bArr[r2];
            for (int r4 = 0; r4 < 8; r4++) {
                str = str + ((b >>> r4) & 1);
            }
            if (r2 != bArr.length - 1) {
                str = str + " ";
            }
        }
        return str;
    }

    public static char[] toCharArray(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            cArr[r1] = (char) bArr[r1];
        }
        return cArr;
    }

    public static String toHexString(byte[] bArr) {
        char[] cArr;
        String str = "";
        for (int r1 = 0; r1 < bArr.length; r1++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(HEX_CHARS[(bArr[r1] >>> 4) & 15]);
            str = sb.toString() + cArr[bArr[r1] & Ascii.f1128SI];
        }
        return str;
    }

    public static String toHexString(byte[] bArr, String str, String str2) {
        char[] cArr;
        String str3 = new String(str);
        for (int r4 = 0; r4 < bArr.length; r4++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(HEX_CHARS[(bArr[r4] >>> 4) & 15]);
            str3 = sb.toString() + cArr[bArr[r4] & Ascii.f1128SI];
            if (r4 < bArr.length - 1) {
                str3 = str3 + str2;
            }
        }
        return str3;
    }

    public static byte[] xor(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int length = bArr.length - 1; length >= 0; length--) {
            bArr3[length] = (byte) (bArr[length] ^ bArr2[length]);
        }
        return bArr3;
    }
}
