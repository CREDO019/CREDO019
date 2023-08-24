package org.apache.commons.p028io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/* renamed from: org.apache.commons.io.HexDump */
/* loaded from: classes5.dex */
public class HexDump {
    public static final String EOL = System.getProperty("line.separator");
    private static final char[] _hexcodes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int[] _shifts = {28, 24, 20, 16, 12, 8, 4, 0};

    public static void dump(byte[] bArr, long j, OutputStream outputStream, int r12) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (r12 < 0 || r12 >= bArr.length) {
            throw new ArrayIndexOutOfBoundsException("illegal index: " + r12 + " into array of length " + bArr.length);
        } else if (outputStream == null) {
            throw new IllegalArgumentException("cannot write to nullstream");
        } else {
            long j2 = j + r12;
            StringBuilder sb = new StringBuilder(74);
            while (r12 < bArr.length) {
                int length = bArr.length - r12;
                if (length > 16) {
                    length = 16;
                }
                dump(sb, j2).append(' ');
                for (int r5 = 0; r5 < 16; r5++) {
                    if (r5 < length) {
                        dump(sb, bArr[r5 + r12]);
                    } else {
                        sb.append("  ");
                    }
                    sb.append(' ');
                }
                for (int r2 = 0; r2 < length; r2++) {
                    int r52 = r2 + r12;
                    if (bArr[r52] >= 32 && bArr[r52] < Byte.MAX_VALUE) {
                        sb.append((char) bArr[r52]);
                    } else {
                        sb.append('.');
                    }
                }
                sb.append(EOL);
                outputStream.write(sb.toString().getBytes(Charset.defaultCharset()));
                outputStream.flush();
                sb.setLength(0);
                j2 += length;
                r12 += 16;
            }
        }
    }

    private static StringBuilder dump(StringBuilder sb, long j) {
        for (int r0 = 0; r0 < 8; r0++) {
            sb.append(_hexcodes[((int) (j >> _shifts[r0])) & 15]);
        }
        return sb;
    }

    private static StringBuilder dump(StringBuilder sb, byte b) {
        for (int r0 = 0; r0 < 2; r0++) {
            sb.append(_hexcodes[(b >> _shifts[r0 + 6]) & 15]);
        }
        return sb;
    }
}
