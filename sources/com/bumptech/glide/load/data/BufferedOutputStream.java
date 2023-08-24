package com.bumptech.glide.load.data;

import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes.dex */
public final class BufferedOutputStream extends OutputStream {
    private ArrayPool arrayPool;
    private byte[] buffer;
    private int index;
    private final OutputStream out;

    public BufferedOutputStream(OutputStream outputStream, ArrayPool arrayPool) {
        this(outputStream, arrayPool, 65536);
    }

    BufferedOutputStream(OutputStream outputStream, ArrayPool arrayPool, int r3) {
        this.out = outputStream;
        this.arrayPool = arrayPool;
        this.buffer = (byte[]) arrayPool.get(r3, byte[].class);
    }

    @Override // java.io.OutputStream
    public void write(int r4) throws IOException {
        byte[] bArr = this.buffer;
        int r1 = this.index;
        this.index = r1 + 1;
        bArr[r1] = (byte) r4;
        maybeFlushBuffer();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r7, int r8) throws IOException {
        int r0 = 0;
        do {
            int r1 = r8 - r0;
            int r2 = r7 + r0;
            int r3 = this.index;
            if (r3 == 0 && r1 >= this.buffer.length) {
                this.out.write(bArr, r2, r1);
                return;
            }
            int min = Math.min(r1, this.buffer.length - r3);
            System.arraycopy(bArr, r2, this.buffer, this.index, min);
            this.index += min;
            r0 += min;
            maybeFlushBuffer();
        } while (r0 < r8);
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        flushBuffer();
        this.out.flush();
    }

    private void flushBuffer() throws IOException {
        int r0 = this.index;
        if (r0 > 0) {
            this.out.write(this.buffer, 0, r0);
            this.index = 0;
        }
    }

    private void maybeFlushBuffer() throws IOException {
        if (this.index == this.buffer.length) {
            flushBuffer();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.out.close();
            release();
        } catch (Throwable th) {
            this.out.close();
            throw th;
        }
    }

    private void release() {
        byte[] bArr = this.buffer;
        if (bArr != null) {
            this.arrayPool.put(bArr);
            this.buffer = null;
        }
    }
}
