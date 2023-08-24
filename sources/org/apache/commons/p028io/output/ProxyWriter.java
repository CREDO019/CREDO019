package org.apache.commons.p028io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* renamed from: org.apache.commons.io.output.ProxyWriter */
/* loaded from: classes5.dex */
public class ProxyWriter extends FilterWriter {
    protected void afterWrite(int r1) throws IOException {
    }

    protected void beforeWrite(int r1) throws IOException {
    }

    public ProxyWriter(Writer writer) {
        super(writer);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        try {
            beforeWrite(1);
            this.out.append(c);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int r4, int r5) throws IOException {
        int r0 = r5 - r4;
        try {
            beforeWrite(r0);
            this.out.append(charSequence, r4, r5);
            afterWrite(r0);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) throws IOException {
        int r0 = 0;
        if (charSequence != null) {
            try {
                r0 = charSequence.length();
            } catch (IOException e) {
                handleIOException(e);
            }
        }
        beforeWrite(r0);
        this.out.append(charSequence);
        afterWrite(r0);
        return this;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int r3) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(r3);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        int r0 = 0;
        if (cArr != null) {
            try {
                r0 = cArr.length;
            } catch (IOException e) {
                handleIOException(e);
                return;
            }
        }
        beforeWrite(r0);
        this.out.write(cArr);
        afterWrite(r0);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int r3, int r4) throws IOException {
        try {
            beforeWrite(r4);
            this.out.write(cArr, r3, r4);
            afterWrite(r4);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        int r0 = 0;
        if (str != null) {
            try {
                r0 = str.length();
            } catch (IOException e) {
                handleIOException(e);
                return;
            }
        }
        beforeWrite(r0);
        this.out.write(str);
        afterWrite(r0);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int r3, int r4) throws IOException {
        try {
            beforeWrite(r4);
            this.out.write(str, r3, r4);
            afterWrite(r4);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
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
