package org.apache.commons.p028io.output;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.p028io.input.ClosedInputStream;

/* renamed from: org.apache.commons.io.output.ByteArrayOutputStream */
/* loaded from: classes5.dex */
public class ByteArrayOutputStream extends OutputStream {
    static final int DEFAULT_SIZE = 1024;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private final List<byte[]> buffers;
    private int count;
    private byte[] currentBuffer;
    private int currentBufferIndex;
    private int filledBufferSum;
    private boolean reuseBuffers;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public ByteArrayOutputStream() {
        this(1024);
    }

    public ByteArrayOutputStream(int r4) {
        this.buffers = new ArrayList();
        this.reuseBuffers = true;
        if (r4 < 0) {
            throw new IllegalArgumentException("Negative initial size: " + r4);
        }
        synchronized (this) {
            needNewBuffer(r4);
        }
    }

    private void needNewBuffer(int r3) {
        if (this.currentBufferIndex < this.buffers.size() - 1) {
            this.filledBufferSum += this.currentBuffer.length;
            int r32 = this.currentBufferIndex + 1;
            this.currentBufferIndex = r32;
            this.currentBuffer = this.buffers.get(r32);
            return;
        }
        byte[] bArr = this.currentBuffer;
        if (bArr == null) {
            this.filledBufferSum = 0;
        } else {
            r3 = Math.max(bArr.length << 1, r3 - this.filledBufferSum);
            this.filledBufferSum += this.currentBuffer.length;
        }
        this.currentBufferIndex++;
        byte[] bArr2 = new byte[r3];
        this.currentBuffer = bArr2;
        this.buffers.add(bArr2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r7, int r8) {
        int r72;
        if (r7 < 0 || r7 > bArr.length || r8 < 0 || (r72 = r7 + r8) > bArr.length || r72 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (r8 == 0) {
            return;
        }
        synchronized (this) {
            int r0 = this.count;
            int r1 = r0 + r8;
            int r02 = r0 - this.filledBufferSum;
            while (r8 > 0) {
                int min = Math.min(r8, this.currentBuffer.length - r02);
                System.arraycopy(bArr, r72 - r8, this.currentBuffer, r02, min);
                r8 -= min;
                if (r8 > 0) {
                    needNewBuffer(r1);
                    r02 = 0;
                }
            }
            this.count = r1;
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int r4) {
        int r0 = this.count;
        int r1 = r0 - this.filledBufferSum;
        if (r1 == this.currentBuffer.length) {
            needNewBuffer(r0 + 1);
            r1 = 0;
        }
        this.currentBuffer[r1] = (byte) r4;
        this.count++;
    }

    public synchronized int write(InputStream inputStream) throws IOException {
        int r3;
        int r0 = this.count - this.filledBufferSum;
        byte[] bArr = this.currentBuffer;
        int read = inputStream.read(bArr, r0, bArr.length - r0);
        r3 = 0;
        while (read != -1) {
            r3 += read;
            r0 += read;
            this.count += read;
            byte[] bArr2 = this.currentBuffer;
            if (r0 == bArr2.length) {
                needNewBuffer(bArr2.length);
                r0 = 0;
            }
            byte[] bArr3 = this.currentBuffer;
            read = inputStream.read(bArr3, r0, bArr3.length - r0);
        }
        return r3;
    }

    public synchronized int size() {
        return this.count;
    }

    public synchronized void reset() {
        this.count = 0;
        this.filledBufferSum = 0;
        this.currentBufferIndex = 0;
        if (this.reuseBuffers) {
            this.currentBuffer = this.buffers.get(0);
        } else {
            this.currentBuffer = null;
            int length = this.buffers.get(0).length;
            this.buffers.clear();
            needNewBuffer(length);
            this.reuseBuffers = true;
        }
    }

    public synchronized void writeTo(OutputStream outputStream) throws IOException {
        int r0 = this.count;
        for (byte[] bArr : this.buffers) {
            int min = Math.min(bArr.length, r0);
            outputStream.write(bArr, 0, min);
            r0 -= min;
            if (r0 == 0) {
                break;
            }
        }
    }

    public static InputStream toBufferedInputStream(InputStream inputStream) throws IOException {
        return toBufferedInputStream(inputStream, 1024);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream, int r2) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(r2);
        byteArrayOutputStream.write(inputStream);
        return byteArrayOutputStream.toInputStream();
    }

    public synchronized InputStream toInputStream() {
        int r0 = this.count;
        if (r0 == 0) {
            return new ClosedInputStream();
        }
        ArrayList arrayList = new ArrayList(this.buffers.size());
        for (byte[] bArr : this.buffers) {
            int min = Math.min(bArr.length, r0);
            arrayList.add(new ByteArrayInputStream(bArr, 0, min));
            r0 -= min;
            if (r0 == 0) {
                break;
            }
        }
        this.reuseBuffers = false;
        return new SequenceInputStream(Collections.enumeration(arrayList));
    }

    public synchronized byte[] toByteArray() {
        int r0 = this.count;
        if (r0 == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[r0];
        int r4 = 0;
        for (byte[] bArr2 : this.buffers) {
            int min = Math.min(bArr2.length, r0);
            System.arraycopy(bArr2, 0, bArr, r4, min);
            r4 += min;
            r0 -= min;
            if (r0 == 0) {
                break;
            }
        }
        return bArr;
    }

    @Deprecated
    public String toString() {
        return new String(toByteArray(), Charset.defaultCharset());
    }

    public String toString(String str) throws UnsupportedEncodingException {
        return new String(toByteArray(), str);
    }

    public String toString(Charset charset) {
        return new String(toByteArray(), charset);
    }
}
