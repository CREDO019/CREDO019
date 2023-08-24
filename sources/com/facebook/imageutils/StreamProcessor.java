package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
class StreamProcessor {
    StreamProcessor() {
    }

    public static int readPackedInt(InputStream is, int numBytes, boolean isLittleEndian) throws IOException {
        int r2;
        int r1 = 0;
        for (int r0 = 0; r0 < numBytes; r0++) {
            int read = is.read();
            if (read == -1) {
                throw new IOException("no more bytes");
            }
            if (isLittleEndian) {
                r2 = (read & 255) << (r0 * 8);
            } else {
                r1 <<= 8;
                r2 = read & 255;
            }
            r1 |= r2;
        }
        return r1;
    }
}
