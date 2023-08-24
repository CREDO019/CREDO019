package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* renamed from: com.google.android.exoplayer2.extractor.ts.TsUtil */
/* loaded from: classes2.dex */
public final class TsUtil {
    public static boolean isStartOfTsPacket(byte[] bArr, int r6, int r7, int r8) {
        int r2 = 0;
        for (int r1 = -4; r1 <= 4; r1++) {
            int r3 = (r1 * 188) + r8;
            if (r3 < r6 || r3 >= r7 || bArr[r3] != 71) {
                r2 = 0;
            } else {
                r2++;
                if (r2 == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int findSyncBytePosition(byte[] bArr, int r3, int r4) {
        while (r3 < r4 && bArr[r3] != 71) {
            r3++;
        }
        return r3;
    }

    public static long readPcrFromPacket(ParsableByteArray parsableByteArray, int r5, int r6) {
        parsableByteArray.setPosition(r5);
        if (parsableByteArray.bytesLeft() < 5) {
            return C1856C.TIME_UNSET;
        }
        int readInt = parsableByteArray.readInt();
        if ((8388608 & readInt) == 0 && ((2096896 & readInt) >> 8) == r6) {
            if (((readInt & 32) != 0) && parsableByteArray.readUnsignedByte() >= 7 && parsableByteArray.bytesLeft() >= 7) {
                if ((parsableByteArray.readUnsignedByte() & 16) == 16) {
                    byte[] bArr = new byte[6];
                    parsableByteArray.readBytes(bArr, 0, 6);
                    return readPcrValueFromPcrBytes(bArr);
                }
            }
            return C1856C.TIME_UNSET;
        }
        return C1856C.TIME_UNSET;
    }

    private static long readPcrValueFromPcrBytes(byte[] bArr) {
        return ((bArr[0] & 255) << 25) | ((bArr[1] & 255) << 17) | ((bArr[2] & 255) << 9) | ((bArr[3] & 255) << 1) | ((255 & bArr[4]) >> 7);
    }

    private TsUtil() {
    }
}
