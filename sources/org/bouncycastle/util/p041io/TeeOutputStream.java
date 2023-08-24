package org.bouncycastle.util.p041io;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.bouncycastle.util.io.TeeOutputStream */
/* loaded from: classes4.dex */
public class TeeOutputStream extends OutputStream {
    private OutputStream output1;
    private OutputStream output2;

    public TeeOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        this.output1 = outputStream;
        this.output2 = outputStream2;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.output1.close();
        this.output2.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.output1.flush();
        this.output2.flush();
    }

    @Override // java.io.OutputStream
    public void write(int r2) throws IOException {
        this.output1.write(r2);
        this.output2.write(r2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.output1.write(bArr);
        this.output2.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        this.output1.write(bArr, r3, r4);
        this.output2.write(bArr, r3, r4);
    }
}
