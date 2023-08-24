package org.apache.commons.fileupload.util.mime;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
final class QuotedPrintableDecoder {
    private static final int UPPER_NIBBLE_SHIFT = 4;

    private QuotedPrintableDecoder() {
    }

    public static int decode(byte[] bArr, OutputStream outputStream) throws IOException {
        int r1 = 0;
        int length = bArr.length + 0;
        int r2 = 0;
        while (r1 < length) {
            int r3 = r1 + 1;
            int r12 = bArr[r1];
            if (r12 == 95) {
                outputStream.write(32);
            } else if (r12 == 61) {
                int r13 = r3 + 1;
                if (r13 >= length) {
                    throw new IOException("Invalid quoted printable encoding; truncated escape sequence");
                }
                byte b = bArr[r3];
                int r4 = r13 + 1;
                byte b2 = bArr[r13];
                if (b != 13) {
                    outputStream.write(hexToBinary(b2) | (hexToBinary(b) << 4));
                    r2++;
                } else if (b2 != 10) {
                    throw new IOException("Invalid quoted printable encoding; CR must be followed by LF");
                }
                r1 = r4;
            } else {
                outputStream.write(r12);
                r2++;
            }
            r1 = r3;
        }
        return r2;
    }

    private static int hexToBinary(byte b) throws IOException {
        int digit = Character.digit((char) b, 16);
        if (digit != -1) {
            return digit;
        }
        throw new IOException("Invalid quoted printable encoding: not a valid hex digit: " + ((int) b));
    }
}
