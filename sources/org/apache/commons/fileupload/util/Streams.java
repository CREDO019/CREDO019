package org.apache.commons.fileupload.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.fileupload.InvalidFileNameException;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes5.dex */
public final class Streams {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private Streams() {
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        return copy(inputStream, outputStream, z, new byte[8192]);
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, boolean z, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                } else if (read > 0) {
                    j += read;
                    if (outputStream != null) {
                        outputStream.write(bArr, 0, read);
                    }
                }
            } catch (Throwable th) {
                IOUtils.closeQuietly(inputStream);
                if (z) {
                    IOUtils.closeQuietly(outputStream);
                }
                throw th;
            }
        }
        if (outputStream != null) {
            if (z) {
                outputStream.close();
            } else {
                outputStream.flush();
            }
            outputStream = null;
        }
        inputStream.close();
        IOUtils.closeQuietly((InputStream) null);
        if (z) {
            IOUtils.closeQuietly(outputStream);
        }
        return j;
    }

    public static String asString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream, true);
        return byteArrayOutputStream.toString();
    }

    public static String asString(InputStream inputStream, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream, true);
        return byteArrayOutputStream.toString(str);
    }

    public static String checkFileName(String str) {
        if (str != null) {
            if (str.indexOf(0) != -1) {
                StringBuilder sb = new StringBuilder();
                for (int r0 = 0; r0 < str.length(); r0++) {
                    char charAt = str.charAt(r0);
                    if (charAt == 0) {
                        sb.append("\\0");
                    } else {
                        sb.append(charAt);
                    }
                }
                throw new InvalidFileNameException(str, "Invalid file name: " + ((Object) sb));
            }
        }
        return str;
    }
}
