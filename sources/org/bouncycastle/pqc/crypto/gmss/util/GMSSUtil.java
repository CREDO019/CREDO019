package org.bouncycastle.pqc.crypto.gmss.util;

import java.io.PrintStream;

/* loaded from: classes3.dex */
public class GMSSUtil {
    public int bytesToIntLittleEndian(byte[] bArr) {
        return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public int bytesToIntLittleEndian(byte[] bArr, int r4) {
        int r0 = r4 + 1;
        int r1 = r0 + 1;
        int r42 = (bArr[r4] & 255) | ((bArr[r0] & 255) << 8);
        return ((bArr[r1 + 1] & 255) << 24) | r42 | ((bArr[r1] & 255) << 16);
    }

    public byte[] concatenateArray(byte[][] bArr) {
        byte[] bArr2 = new byte[bArr.length * bArr[0].length];
        int r3 = 0;
        for (int r2 = 0; r2 < bArr.length; r2++) {
            System.arraycopy(bArr[r2], 0, bArr2, r3, bArr[r2].length);
            r3 += bArr[r2].length;
        }
        return bArr2;
    }

    public int getLog(int r3) {
        int r0 = 1;
        int r1 = 2;
        while (r1 < r3) {
            r1 <<= 1;
            r0++;
        }
        return r0;
    }

    public byte[] intToBytesLittleEndian(int r4) {
        return new byte[]{(byte) (r4 & 255), (byte) ((r4 >> 8) & 255), (byte) ((r4 >> 16) & 255), (byte) ((r4 >> 24) & 255)};
    }

    public void printArray(String str, byte[] bArr) {
        System.out.println(str);
        int r0 = 0;
        for (int r5 = 0; r5 < bArr.length; r5++) {
            PrintStream printStream = System.out;
            printStream.println(r0 + "; " + ((int) bArr[r5]));
            r0++;
        }
    }

    public void printArray(String str, byte[][] bArr) {
        System.out.println(str);
        int r1 = 0;
        for (int r0 = 0; r0 < bArr.length; r0++) {
            for (int r2 = 0; r2 < bArr[0].length; r2++) {
                PrintStream printStream = System.out;
                printStream.println(r1 + "; " + ((int) bArr[r0][r2]));
                r1++;
            }
        }
    }

    public boolean testPowerOfTwo(int r3) {
        int r1 = 1;
        while (r1 < r3) {
            r1 <<= 1;
        }
        return r3 == r1;
    }
}
