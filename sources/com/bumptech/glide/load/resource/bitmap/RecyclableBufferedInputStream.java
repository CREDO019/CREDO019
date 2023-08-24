package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class RecyclableBufferedInputStream extends FilterInputStream {
    private volatile byte[] buf;
    private final ArrayPool byteArrayPool;
    private int count;
    private int marklimit;
    private int markpos;
    private int pos;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    public RecyclableBufferedInputStream(InputStream inputStream, ArrayPool arrayPool) {
        this(inputStream, arrayPool, 65536);
    }

    RecyclableBufferedInputStream(InputStream inputStream, ArrayPool arrayPool, int r3) {
        super(inputStream);
        this.markpos = -1;
        this.byteArrayPool = arrayPool;
        this.buf = (byte[]) arrayPool.get(r3, byte[].class);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.buf == null || inputStream == null) {
            throw streamClosed();
        }
        return (this.count - this.pos) + inputStream.available();
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void fixMarkLimit() {
        this.marklimit = this.buf.length;
    }

    public synchronized void release() {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.buf != null) {
            this.byteArrayPool.put(this.buf);
            this.buf = null;
        }
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private int fillbuf(InputStream inputStream, byte[] bArr) throws IOException {
        int r0 = this.markpos;
        if (r0 != -1) {
            int r3 = this.pos - r0;
            int r4 = this.marklimit;
            if (r3 < r4) {
                if (r0 == 0 && r4 > bArr.length && this.count == bArr.length) {
                    int length = bArr.length * 2;
                    if (length <= r4) {
                        r4 = length;
                    }
                    byte[] bArr2 = (byte[]) this.byteArrayPool.get(r4, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    this.buf = bArr2;
                    this.byteArrayPool.put(bArr);
                    bArr = bArr2;
                } else if (r0 > 0) {
                    System.arraycopy(bArr, r0, bArr, 0, bArr.length - r0);
                }
                int r02 = this.pos - this.markpos;
                this.pos = r02;
                this.markpos = 0;
                this.count = 0;
                int read = inputStream.read(bArr, r02, bArr.length - r02);
                int r7 = this.pos;
                if (read > 0) {
                    r7 += read;
                }
                this.count = r7;
                return read;
            }
        }
        int read2 = inputStream.read(bArr);
        if (read2 > 0) {
            this.markpos = -1;
            this.pos = 0;
            this.count = read2;
        }
        return read2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int r2) {
        this.marklimit = Math.max(this.marklimit, r2);
        this.markpos = this.pos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        byte[] bArr = this.buf;
        InputStream inputStream = this.in;
        if (bArr == null || inputStream == null) {
            throw streamClosed();
        }
        if (this.pos < this.count || fillbuf(inputStream, bArr) != -1) {
            if (bArr != this.buf && (bArr = this.buf) == null) {
                throw streamClosed();
            }
            int r1 = this.count;
            int r2 = this.pos;
            if (r1 - r2 > 0) {
                this.pos = r2 + 1;
                return bArr[r2] & 255;
            }
            return -1;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int r8, int r9) throws IOException {
        int r2;
        int r3;
        byte[] bArr2 = this.buf;
        if (bArr2 == null) {
            throw streamClosed();
        }
        if (r9 == 0) {
            return 0;
        }
        InputStream inputStream = this.in;
        if (inputStream == null) {
            throw streamClosed();
        }
        int r22 = this.pos;
        int r32 = this.count;
        if (r22 < r32) {
            int r33 = r32 - r22 >= r9 ? r9 : r32 - r22;
            System.arraycopy(bArr2, r22, bArr, r8, r33);
            this.pos += r33;
            if (r33 == r9 || inputStream.available() == 0) {
                return r33;
            }
            r8 += r33;
            r2 = r9 - r33;
        } else {
            r2 = r9;
        }
        while (true) {
            if (this.markpos == -1 && r2 >= bArr2.length) {
                r3 = inputStream.read(bArr, r8, r2);
                if (r3 == -1) {
                    return r2 != r9 ? r9 - r2 : -1;
                }
            } else if (fillbuf(inputStream, bArr2) == -1) {
                return r2 != r9 ? r9 - r2 : -1;
            } else {
                if (bArr2 != this.buf && (bArr2 = this.buf) == null) {
                    throw streamClosed();
                }
                int r34 = this.count;
                int r4 = this.pos;
                r3 = r34 - r4 >= r2 ? r2 : r34 - r4;
                System.arraycopy(bArr2, r4, bArr, r8, r3);
                this.pos += r3;
            }
            r2 -= r3;
            if (r2 == 0) {
                return r9;
            }
            if (inputStream.available() == 0) {
                return r9 - r2;
            }
            r8 += r3;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        }
        int r1 = this.markpos;
        if (-1 == r1) {
            throw new InvalidMarkException("Mark has been invalidated, pos: " + this.pos + " markLimit: " + this.marklimit);
        }
        this.pos = r1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long j) throws IOException {
        if (j < 1) {
            return 0L;
        }
        byte[] bArr = this.buf;
        if (bArr == null) {
            throw streamClosed();
        }
        InputStream inputStream = this.in;
        if (inputStream == null) {
            throw streamClosed();
        }
        int r2 = this.count;
        int r3 = this.pos;
        if (r2 - r3 >= j) {
            this.pos = (int) (r3 + j);
            return j;
        }
        long j2 = r2 - r3;
        this.pos = r2;
        if (this.markpos != -1 && j <= this.marklimit) {
            if (fillbuf(inputStream, bArr) == -1) {
                return j2;
            }
            int r0 = this.count;
            int r1 = this.pos;
            if (r0 - r1 >= j - j2) {
                this.pos = (int) ((r1 + j) - j2);
                return j;
            }
            long j3 = (j2 + r0) - r1;
            this.pos = r0;
            return j3;
        }
        return j2 + inputStream.skip(j - j2);
    }

    /* loaded from: classes.dex */
    static class InvalidMarkException extends IOException {
        private static final long serialVersionUID = -4338378848813561757L;

        InvalidMarkException(String str) {
            super(str);
        }
    }
}
