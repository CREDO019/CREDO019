package com.facebook.common.util;

/* loaded from: classes.dex */
public class Hex {
    private static final byte[] DIGITS;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] FIRST_CHAR = new char[256];
    private static final char[] SECOND_CHAR = new char[256];

    static {
        for (int r2 = 0; r2 < 256; r2++) {
            char[] cArr = FIRST_CHAR;
            char[] cArr2 = HEX_DIGITS;
            cArr[r2] = cArr2[(r2 >> 4) & 15];
            SECOND_CHAR[r2] = cArr2[r2 & 15];
        }
        DIGITS = new byte[103];
        for (int r0 = 0; r0 <= 70; r0++) {
            DIGITS[r0] = -1;
        }
        for (byte b = 0; b < 10; b = (byte) (b + 1)) {
            DIGITS[b + 48] = b;
        }
        for (byte b2 = 0; b2 < 6; b2 = (byte) (b2 + 1)) {
            byte[] bArr = DIGITS;
            byte b3 = (byte) (b2 + 10);
            bArr[b2 + 65] = b3;
            bArr[b2 + 97] = b3;
        }
    }

    public static String byte2Hex(int value) {
        if (value > 255 || value < 0) {
            throw new IllegalArgumentException("The int converting to hex should be in range 0~255");
        }
        return String.valueOf(FIRST_CHAR[value]) + String.valueOf(SECOND_CHAR[value]);
    }

    public static String encodeHex(byte[] array, boolean zeroTerminated) {
        int r4;
        char[] cArr = new char[array.length * 2];
        int r3 = 0;
        for (int r2 = 0; r2 < array.length && ((r4 = array[r2] & 255) != 0 || !zeroTerminated); r2++) {
            int r5 = r3 + 1;
            cArr[r3] = FIRST_CHAR[r4];
            r3 = r5 + 1;
            cArr[r5] = SECOND_CHAR[r4];
        }
        return new String(cArr, 0, r3);
    }

    public static byte[] decodeHex(String hexString) {
        byte[] bArr;
        byte b;
        byte b2;
        int length = hexString.length();
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }
        byte[] bArr2 = new byte[length >> 1];
        boolean z = false;
        int r3 = 0;
        int r4 = 0;
        while (r3 < length) {
            int r6 = r3 + 1;
            char charAt = hexString.charAt(r3);
            if (charAt <= 'f' && (b = (bArr = DIGITS)[charAt]) != -1) {
                int r10 = r6 + 1;
                char charAt2 = hexString.charAt(r6);
                if (charAt2 <= 'f' && (b2 = bArr[charAt2]) != -1) {
                    bArr2[r4] = (byte) ((b << 4) | b2);
                    r4++;
                    r3 = r10;
                }
            }
            z = true;
        }
        if (z) {
            throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexString);
        }
        return bArr2;
    }

    public static byte[] hexStringToByteArray(String s) {
        return decodeHex(s.replaceAll(" ", ""));
    }
}
