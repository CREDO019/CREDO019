package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class ByteBufferBackedInputStream extends InputStream {

    /* renamed from: _b */
    protected final ByteBuffer f200_b;

    public ByteBufferBackedInputStream(ByteBuffer byteBuffer) {
        this.f200_b = byteBuffer;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f200_b.remaining();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.f200_b.hasRemaining()) {
            return this.f200_b.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int r3, int r4) throws IOException {
        if (this.f200_b.hasRemaining()) {
            int min = Math.min(r4, this.f200_b.remaining());
            this.f200_b.get(bArr, r3, min);
            return min;
        }
        return -1;
    }
}
