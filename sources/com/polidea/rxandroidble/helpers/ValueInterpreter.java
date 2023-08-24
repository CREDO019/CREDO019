package com.polidea.rxandroidble.helpers;

import com.polidea.rxandroidble.internal.RxBleLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes3.dex */
public class ValueInterpreter {
    public static final int FORMAT_FLOAT = 52;
    public static final int FORMAT_SFLOAT = 50;
    public static final int FORMAT_SINT16 = 34;
    public static final int FORMAT_SINT32 = 36;
    public static final int FORMAT_SINT8 = 33;
    public static final int FORMAT_UINT16 = 18;
    public static final int FORMAT_UINT32 = 20;
    public static final int FORMAT_UINT8 = 17;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface FloatFormatType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface IntFormatType {
    }

    private static int getTypeLen(int r0) {
        return r0 & 15;
    }

    private static int unsignedByteToInt(byte b) {
        return b & 255;
    }

    private static int unsignedToSigned(int r1, int r2) {
        int r22 = 1 << (r2 - 1);
        return (r1 & r22) != 0 ? (r22 - (r1 & (r22 - 1))) * (-1) : r1;
    }

    private ValueInterpreter() {
    }

    public static Integer getIntValue(byte[] bArr, int r6, int r7) {
        if (getTypeLen(r6) + r7 > bArr.length) {
            RxBleLog.m235w("Int formatType (0x%x) is longer than remaining bytes (%d) - returning null", Integer.valueOf(r6), Integer.valueOf(bArr.length - r7));
            return null;
        } else if (r6 != 17) {
            if (r6 != 18) {
                if (r6 != 20) {
                    if (r6 != 36) {
                        if (r6 != 33) {
                            if (r6 != 34) {
                                RxBleLog.m235w("Passed an invalid integer formatType (0x%x) - returning null", Integer.valueOf(r6));
                                return null;
                            }
                            return Integer.valueOf(unsignedToSigned(unsignedBytesToInt(bArr[r7], bArr[r7 + 1]), 16));
                        }
                        return Integer.valueOf(unsignedToSigned(unsignedByteToInt(bArr[r7]), 8));
                    }
                    return Integer.valueOf(unsignedToSigned(unsignedBytesToInt(bArr[r7], bArr[r7 + 1], bArr[r7 + 2], bArr[r7 + 3]), 32));
                }
                return Integer.valueOf(unsignedBytesToInt(bArr[r7], bArr[r7 + 1], bArr[r7 + 2], bArr[r7 + 3]));
            }
            return Integer.valueOf(unsignedBytesToInt(bArr[r7], bArr[r7 + 1]));
        } else {
            return Integer.valueOf(unsignedByteToInt(bArr[r7]));
        }
    }

    public static Float getFloatValue(byte[] bArr, int r6, int r7) {
        if (getTypeLen(r6) + r7 > bArr.length) {
            RxBleLog.m235w("Float formatType (0x%x) is longer than remaining bytes (%d) - returning null", Integer.valueOf(r6), Integer.valueOf(bArr.length - r7));
            return null;
        } else if (r6 != 50) {
            if (r6 != 52) {
                RxBleLog.m235w("Passed an invalid float formatType (0x%x) - returning null", Integer.valueOf(r6));
                return null;
            }
            return Float.valueOf(bytesToFloat(bArr[r7], bArr[r7 + 1], bArr[r7 + 2], bArr[r7 + 3]));
        } else {
            return Float.valueOf(bytesToFloat(bArr[r7], bArr[r7 + 1]));
        }
    }

    public static String getStringValue(byte[] bArr, int r4) {
        if (r4 > bArr.length) {
            RxBleLog.m235w("Passed offset that exceeds the length of the byte array - returning null", new Object[0]);
            return null;
        }
        byte[] bArr2 = new byte[bArr.length - r4];
        for (int r1 = 0; r1 != bArr.length - r4; r1++) {
            bArr2[r1] = bArr[r4 + r1];
        }
        return new String(bArr2);
    }

    private static int unsignedBytesToInt(byte b, byte b2) {
        return unsignedByteToInt(b) + (unsignedByteToInt(b2) << 8);
    }

    private static int unsignedBytesToInt(byte b, byte b2, byte b3, byte b4) {
        return unsignedByteToInt(b) + (unsignedByteToInt(b2) << 8) + (unsignedByteToInt(b3) << 16) + (unsignedByteToInt(b4) << 24);
    }

    private static float bytesToFloat(byte b, byte b2) {
        return (float) (unsignedToSigned(unsignedByteToInt(b) + ((unsignedByteToInt(b2) & 15) << 8), 12) * Math.pow(10.0d, unsignedToSigned(unsignedByteToInt(b2) >> 4, 4)));
    }

    private static float bytesToFloat(byte b, byte b2, byte b3, byte b4) {
        return (float) (unsignedToSigned(unsignedByteToInt(b) + (unsignedByteToInt(b2) << 8) + (unsignedByteToInt(b3) << 16), 24) * Math.pow(10.0d, b4));
    }
}
