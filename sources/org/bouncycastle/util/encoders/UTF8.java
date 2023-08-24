package org.bouncycastle.util.encoders;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.common.base.Ascii;
import okio.Utf8;

/* loaded from: classes4.dex */
public class UTF8 {
    private static final byte C_CR1 = 1;
    private static final byte C_CR2 = 2;
    private static final byte C_CR3 = 3;
    private static final byte C_ILL = 0;
    private static final byte C_L2A = 4;
    private static final byte C_L3A = 5;
    private static final byte C_L3B = 6;
    private static final byte C_L3C = 7;
    private static final byte C_L4A = 8;
    private static final byte C_L4B = 9;
    private static final byte C_L4C = 10;
    private static final byte S_CS1 = 0;
    private static final byte S_CS2 = 16;
    private static final byte S_CS3 = 32;
    private static final byte S_END = -1;
    private static final byte S_ERR = -2;
    private static final byte S_P3A = 48;
    private static final byte S_P3B = 64;
    private static final byte S_P4A = 80;
    private static final byte S_P4B = 96;
    private static final short[] firstUnitTable = new short[128];
    private static final byte[] transitionTable;

    static {
        byte[] bArr = new byte[112];
        transitionTable = bArr;
        byte[] bArr2 = new byte[128];
        fill(bArr2, 0, 15, (byte) 1);
        fill(bArr2, 16, 31, (byte) 2);
        fill(bArr2, 32, 63, (byte) 3);
        fill(bArr2, 64, 65, (byte) 0);
        fill(bArr2, 66, 95, (byte) 4);
        fill(bArr2, 96, 96, (byte) 5);
        fill(bArr2, 97, 108, (byte) 6);
        fill(bArr2, 109, 109, (byte) 7);
        fill(bArr2, 110, 111, (byte) 6);
        fill(bArr2, 112, 112, (byte) 8);
        fill(bArr2, 113, 115, (byte) 9);
        fill(bArr2, 116, 116, (byte) 10);
        fill(bArr2, 117, 127, (byte) 0);
        fill(bArr, 0, bArr.length - 1, S_ERR);
        fill(bArr, 8, 11, (byte) -1);
        fill(bArr, 24, 27, (byte) 0);
        fill(bArr, 40, 43, (byte) 16);
        fill(bArr, 58, 59, (byte) 0);
        fill(bArr, 72, 73, (byte) 0);
        fill(bArr, 89, 91, (byte) 16);
        fill(bArr, 104, 104, (byte) 16);
        byte[] bArr3 = {0, 0, 0, 0, Ascii.f1131US, Ascii.f1128SI, Ascii.f1128SI, Ascii.f1128SI, 7, 7, 7};
        byte[] bArr4 = {S_ERR, S_ERR, S_ERR, S_ERR, 0, S_P3A, 16, 64, S_P4A, 32, S_P4B};
        for (int r4 = 0; r4 < 128; r4++) {
            byte b = bArr2[r4];
            firstUnitTable[r4] = (short) (bArr4[b] | ((bArr3[b] & r4) << 8));
        }
    }

    private static void fill(byte[] bArr, int r1, int r2, byte b) {
        while (r1 <= r2) {
            bArr[r1] = b;
            r1++;
        }
    }

    public static int transcodeToUTF16(byte[] bArr, char[] cArr) {
        int r0 = 0;
        int r1 = 0;
        while (r0 < bArr.length) {
            int r2 = r0 + 1;
            byte b = bArr[r0];
            if (b < 0) {
                short s = firstUnitTable[b & Byte.MAX_VALUE];
                int r4 = s >>> 8;
                byte b2 = (byte) s;
                while (b2 >= 0) {
                    if (r2 >= bArr.length) {
                        return -1;
                    }
                    int r5 = r2 + 1;
                    byte b3 = bArr[r2];
                    r4 = (r4 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
                    b2 = transitionTable[b2 + ((b3 & 255) >>> 4)];
                    r2 = r5;
                }
                if (b2 == -2) {
                    return -1;
                }
                if (r4 <= 65535) {
                    if (r1 >= cArr.length) {
                        return -1;
                    }
                    cArr[r1] = (char) r4;
                    r1++;
                } else if (r1 >= cArr.length - 1) {
                    return -1;
                } else {
                    int r02 = r1 + 1;
                    cArr[r1] = (char) ((r4 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                    r1 = r02 + 1;
                    cArr[r02] = (char) (56320 | (r4 & AnalyticsListener.EVENT_DRM_KEYS_LOADED));
                }
                r0 = r2;
            } else if (r1 >= cArr.length) {
                return -1;
            } else {
                cArr[r1] = (char) b;
                r0 = r2;
                r1++;
            }
        }
        return r1;
    }
}
