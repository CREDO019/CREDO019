package org.apache.commons.codec.binary;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

/* loaded from: classes5.dex */
public class BinaryCodec implements BinaryDecoder, BinaryEncoder {
    private static final int BIT_0 = 1;
    private static final int BIT_1 = 2;
    private static final int BIT_2 = 4;
    private static final int BIT_3 = 8;
    private static final int BIT_4 = 16;
    private static final int BIT_5 = 32;
    private static final int BIT_6 = 64;
    private static final int BIT_7 = 128;
    private static final char[] EMPTY_CHAR_ARRAY = new char[0];
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final int[] BITS = {1, 2, 4, 8, 16, 32, 64, 128};

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bArr) {
        return toAsciiBytes(bArr);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof byte[])) {
            throw new EncoderException("argument not a byte array");
        }
        return toAsciiChars((byte[]) obj);
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return EMPTY_BYTE_ARRAY;
        }
        if (obj instanceof byte[]) {
            return fromAscii((byte[]) obj);
        }
        if (obj instanceof char[]) {
            return fromAscii((char[]) obj);
        }
        if (obj instanceof String) {
            return fromAscii(((String) obj).toCharArray());
        }
        throw new DecoderException("argument not a byte array");
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bArr) {
        return fromAscii(bArr);
    }

    public byte[] toByteArray(String str) {
        if (str == null) {
            return EMPTY_BYTE_ARRAY;
        }
        return fromAscii(str.toCharArray());
    }

    public static byte[] fromAscii(char[] cArr) {
        if (cArr == null || cArr.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        int length = cArr.length >> 3;
        byte[] bArr = new byte[length];
        int length2 = cArr.length - 1;
        int r4 = 0;
        while (r4 < length) {
            int r5 = 0;
            while (true) {
                int[] r6 = BITS;
                if (r5 < r6.length) {
                    if (cArr[length2 - r5] == '1') {
                        bArr[r4] = (byte) (r6[r5] | bArr[r4]);
                    }
                    r5++;
                }
            }
            r4++;
            length2 -= 8;
        }
        return bArr;
    }

    public static byte[] fromAscii(byte[] bArr) {
        if (isEmpty(bArr)) {
            return EMPTY_BYTE_ARRAY;
        }
        int length = bArr.length >> 3;
        byte[] bArr2 = new byte[length];
        int length2 = bArr.length - 1;
        int r4 = 0;
        while (r4 < length) {
            int r5 = 0;
            while (true) {
                int[] r6 = BITS;
                if (r5 < r6.length) {
                    if (bArr[length2 - r5] == 49) {
                        bArr2[r4] = (byte) (r6[r5] | bArr2[r4]);
                    }
                    r5++;
                }
            }
            r4++;
            length2 -= 8;
        }
        return bArr2;
    }

    private static boolean isEmpty(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static byte[] toAsciiBytes(byte[] bArr) {
        if (isEmpty(bArr)) {
            return EMPTY_BYTE_ARRAY;
        }
        int length = bArr.length << 3;
        byte[] bArr2 = new byte[length];
        int r0 = length - 1;
        int r3 = 0;
        while (r3 < bArr.length) {
            int r4 = 0;
            while (true) {
                int[] r5 = BITS;
                if (r4 < r5.length) {
                    if ((r5[r4] & bArr[r3]) == 0) {
                        bArr2[r0 - r4] = 48;
                    } else {
                        bArr2[r0 - r4] = 49;
                    }
                    r4++;
                }
            }
            r3++;
            r0 -= 8;
        }
        return bArr2;
    }

    public static char[] toAsciiChars(byte[] bArr) {
        if (isEmpty(bArr)) {
            return EMPTY_CHAR_ARRAY;
        }
        int length = bArr.length << 3;
        char[] cArr = new char[length];
        int r0 = length - 1;
        int r3 = 0;
        while (r3 < bArr.length) {
            int r4 = 0;
            while (true) {
                int[] r5 = BITS;
                if (r4 < r5.length) {
                    if ((r5[r4] & bArr[r3]) == 0) {
                        cArr[r0 - r4] = '0';
                    } else {
                        cArr[r0 - r4] = '1';
                    }
                    r4++;
                }
            }
            r3++;
            r0 -= 8;
        }
        return cArr;
    }

    public static String toAsciiString(byte[] bArr) {
        return new String(toAsciiChars(bArr));
    }
}
