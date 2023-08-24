package com.polidea.multiplatformbleadapter.utils;

/* loaded from: classes3.dex */
public class ByteUtils {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            int r2 = bArr[r1] & 255;
            int r3 = r1 * 2;
            char[] cArr2 = hexArray;
            cArr[r3] = cArr2[r2 >>> 4];
            cArr[r3 + 1] = cArr2[r2 & 15];
        }
        return new String(cArr);
    }
}
