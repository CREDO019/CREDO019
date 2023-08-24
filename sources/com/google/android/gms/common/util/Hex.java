package com.google.android.gms.common.util;

import com.google.common.base.Ascii;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public class Hex {
    private static final char[] zza = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzb = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String bytesToStringLowercase(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length + length];
        int r2 = 0;
        for (byte b : bArr) {
            int r3 = b & 255;
            int r4 = r2 + 1;
            char[] cArr2 = zzb;
            cArr[r2] = cArr2[r3 >>> 4];
            r2 = r4 + 1;
            cArr[r4] = cArr2[r3 & 15];
        }
        return new String(cArr);
    }

    public static String bytesToStringUppercase(byte[] bArr) {
        return bytesToStringUppercase(bArr, false);
    }

    public static byte[] stringToBytes(String str) throws IllegalArgumentException {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Hex string has odd number of characters");
        }
        byte[] bArr = new byte[length / 2];
        int r2 = 0;
        while (r2 < length) {
            int r3 = r2 + 2;
            bArr[r2 / 2] = (byte) Integer.parseInt(str.substring(r2, r3), 16);
            r2 = r3;
        }
        return bArr;
    }

    public static String bytesToStringUppercase(byte[] bArr, boolean z) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length + length);
        for (int r2 = 0; r2 < length && (!z || r2 != length - 1 || (bArr[r2] & 255) != 0); r2++) {
            char[] cArr = zza;
            sb.append(cArr[(bArr[r2] & 240) >>> 4]);
            sb.append(cArr[bArr[r2] & Ascii.f1128SI]);
        }
        return sb.toString();
    }
}
