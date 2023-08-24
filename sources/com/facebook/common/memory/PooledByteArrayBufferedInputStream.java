package com.facebook.common.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.ResourceReleaser;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class PooledByteArrayBufferedInputStream extends InputStream {
    private static final String TAG = "PooledByteInputStream";
    private final byte[] mByteArray;
    private final InputStream mInputStream;
    private final ResourceReleaser<byte[]> mResourceReleaser;
    private int mBufferedSize = 0;
    private int mBufferOffset = 0;
    private boolean mClosed = false;

    public PooledByteArrayBufferedInputStream(InputStream inputStream, byte[] byteArray, ResourceReleaser<byte[]> resourceReleaser) {
        this.mInputStream = (InputStream) Preconditions.checkNotNull(inputStream);
        this.mByteArray = (byte[]) Preconditions.checkNotNull(byteArray);
        this.mResourceReleaser = (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        if (ensureDataInBuffer()) {
            byte[] bArr = this.mByteArray;
            int r1 = this.mBufferOffset;
            this.mBufferOffset = r1 + 1;
            return bArr[r1] & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int offset, int length) throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        if (ensureDataInBuffer()) {
            int min = Math.min(this.mBufferedSize - this.mBufferOffset, length);
            System.arraycopy(this.mByteArray, this.mBufferOffset, buffer, offset, min);
            this.mBufferOffset += min;
            return min;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        return (this.mBufferedSize - this.mBufferOffset) + this.mInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.mClosed) {
            return;
        }
        this.mClosed = true;
        this.mResourceReleaser.release(this.mByteArray);
        super.close();
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        int r0 = this.mBufferedSize;
        int r1 = this.mBufferOffset;
        long j = r0 - r1;
        if (j >= byteCount) {
            this.mBufferOffset = (int) (r1 + byteCount);
            return byteCount;
        }
        this.mBufferOffset = r0;
        return j + this.mInputStream.skip(byteCount - j);
    }

    private boolean ensureDataInBuffer() throws IOException {
        if (this.mBufferOffset < this.mBufferedSize) {
            return true;
        }
        int read = this.mInputStream.read(this.mByteArray);
        if (read <= 0) {
            return false;
        }
        this.mBufferedSize = read;
        this.mBufferOffset = 0;
        return true;
    }

    private void ensureNotClosed() throws IOException {
        if (this.mClosed) {
            throw new IOException("stream already closed");
        }
    }

    protected void finalize() throws Throwable {
        if (!this.mClosed) {
            FLog.m1328e(TAG, "Finalized without closing");
            close();
        }
        super.finalize();
    }
}
