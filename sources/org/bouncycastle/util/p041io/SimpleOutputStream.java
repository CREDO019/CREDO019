package org.bouncycastle.util.p041io;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.bouncycastle.util.io.SimpleOutputStream */
/* loaded from: classes4.dex */
public abstract class SimpleOutputStream extends OutputStream {
    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.OutputStream
    public void write(int r4) throws IOException {
        write(new byte[]{(byte) r4}, 0, 1);
    }
}
