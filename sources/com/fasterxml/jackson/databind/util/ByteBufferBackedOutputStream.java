package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class ByteBufferBackedOutputStream extends OutputStream {

    /* renamed from: _b */
    protected final ByteBuffer f201_b;

    public ByteBufferBackedOutputStream(ByteBuffer byteBuffer) {
        this.f201_b = byteBuffer;
    }

    @Override // java.io.OutputStream
    public void write(int r2) throws IOException {
        this.f201_b.put((byte) r2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        this.f201_b.put(bArr, r3, r4);
    }
}
