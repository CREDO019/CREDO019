package org.apache.commons.fileupload.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public abstract class LimitedInputStream extends FilterInputStream implements Closeable {
    private boolean closed;
    private long count;
    private final long sizeMax;

    protected abstract void raiseError(long j, long j2) throws IOException;

    public LimitedInputStream(InputStream inputStream, long j) {
        super(inputStream);
        this.sizeMax = j;
    }

    private void checkLimit() throws IOException {
        long j = this.count;
        long j2 = this.sizeMax;
        if (j > j2) {
            raiseError(j2, j);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.count++;
            checkLimit();
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int r4, int r5) throws IOException {
        int read = super.read(bArr, r4, r5);
        if (read > 0) {
            this.count += read;
            checkLimit();
        }
        return read;
    }

    @Override // org.apache.commons.fileupload.util.Closeable
    public boolean isClosed() throws IOException {
        return this.closed;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable, org.apache.commons.fileupload.util.Closeable
    public void close() throws IOException {
        this.closed = true;
        super.close();
    }
}
