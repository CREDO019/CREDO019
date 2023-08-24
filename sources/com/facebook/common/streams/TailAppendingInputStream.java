package com.facebook.common.streams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* loaded from: classes.dex */
public class TailAppendingInputStream extends FilterInputStream {
    private int mMarkedTailOffset;
    private final byte[] mTail;
    private int mTailOffset;

    public TailAppendingInputStream(InputStream inputStream, byte[] tail) {
        super(inputStream);
        Objects.requireNonNull(inputStream);
        Objects.requireNonNull(tail);
        this.mTail = tail;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = this.in.read();
        return read != -1 ? read : readNextTailByte();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] buffer, int offset, int count) throws IOException {
        int read = this.in.read(buffer, offset, count);
        if (read != -1) {
            return read;
        }
        int r0 = 0;
        if (count == 0) {
            return 0;
        }
        while (r0 < count) {
            int readNextTailByte = readNextTailByte();
            if (readNextTailByte == -1) {
                break;
            }
            buffer[offset + r0] = (byte) readNextTailByte;
            r0++;
        }
        if (r0 > 0) {
            return r0;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        if (this.in.markSupported()) {
            this.in.reset();
            this.mTailOffset = this.mMarkedTailOffset;
            return;
        }
        throw new IOException("mark is not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int readLimit) {
        if (this.in.markSupported()) {
            super.mark(readLimit);
            this.mMarkedTailOffset = this.mTailOffset;
        }
    }

    private int readNextTailByte() {
        int r0 = this.mTailOffset;
        byte[] bArr = this.mTail;
        if (r0 >= bArr.length) {
            return -1;
        }
        this.mTailOffset = r0 + 1;
        return bArr[r0] & 255;
    }
}
