package org.bouncycastle.util.p041io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: org.bouncycastle.util.io.Streams */
/* loaded from: classes4.dex */
public final class Streams {
    private static int BUFFER_SIZE = 4096;

    public static void drain(InputStream inputStream) throws IOException {
        int r0 = BUFFER_SIZE;
        do {
        } while (inputStream.read(new byte[r0], 0, r0) >= 0);
    }

    public static void pipeAll(InputStream inputStream, OutputStream outputStream) throws IOException {
        pipeAll(inputStream, outputStream, BUFFER_SIZE);
    }

    public static void pipeAll(InputStream inputStream, OutputStream outputStream, int r5) throws IOException {
        byte[] bArr = new byte[r5];
        while (true) {
            int read = inputStream.read(bArr, 0, r5);
            if (read < 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static long pipeAllLimited(InputStream inputStream, long j, OutputStream outputStream) throws IOException {
        int r0 = BUFFER_SIZE;
        byte[] bArr = new byte[r0];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, r0);
            if (read < 0) {
                return j2;
            }
            long j3 = read;
            if (j - j2 < j3) {
                throw new StreamOverflowException("Data Overflow");
            }
            j2 += j3;
            outputStream.write(bArr, 0, read);
        }
    }

    public static byte[] readAll(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pipeAll(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] readAllLimited(InputStream inputStream, int r4) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pipeAllLimited(inputStream, r4, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        return readFully(inputStream, bArr, 0, bArr.length);
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int r5, int r6) throws IOException {
        int r0 = 0;
        while (r0 < r6) {
            int read = inputStream.read(bArr, r5 + r0, r6 - r0);
            if (read < 0) {
                break;
            }
            r0 += read;
        }
        return r0;
    }

    public static void writeBufTo(ByteArrayOutputStream byteArrayOutputStream, OutputStream outputStream) throws IOException {
        byteArrayOutputStream.writeTo(outputStream);
    }
}
