package org.apache.commons.p028io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.apache.commons.io.output.ProxyOutputStream */
/* loaded from: classes5.dex */
public class ProxyOutputStream extends FilterOutputStream {
    protected void afterWrite(int r1) throws IOException {
    }

    protected void beforeWrite(int r1) throws IOException {
    }

    public ProxyOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int r3) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(r3);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        int length;
        if (bArr != null) {
            try {
                length = bArr.length;
            } catch (IOException e) {
                handleIOException(e);
                return;
            }
        } else {
            length = 0;
        }
        beforeWrite(length);
        this.out.write(bArr);
        afterWrite(length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        try {
            beforeWrite(r4);
            this.out.write(bArr, r3, r4);
            afterWrite(r4);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.out.close();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    protected void handleIOException(IOException iOException) throws IOException {
        throw iOException;
    }
}
