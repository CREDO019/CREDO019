package com.google.common.p016io;

import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.AppendableWriter */
/* loaded from: classes3.dex */
class AppendableWriter extends Writer {
    private boolean closed;
    private final Appendable target;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppendableWriter(Appendable appendable) {
        this.target = (Appendable) Preconditions.checkNotNull(appendable);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int r4, int r5) throws IOException {
        checkNotClosed();
        this.target.append(new String(cArr, r4, r5));
    }

    @Override // java.io.Writer
    public void write(int r2) throws IOException {
        checkNotClosed();
        this.target.append((char) r2);
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        Preconditions.checkNotNull(str);
        checkNotClosed();
        this.target.append(str);
    }

    @Override // java.io.Writer
    public void write(String str, int r3, int r4) throws IOException {
        Preconditions.checkNotNull(str);
        checkNotClosed();
        this.target.append(str, r3, r4 + r3);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        checkNotClosed();
        Appendable appendable = this.target;
        if (appendable instanceof Flushable) {
            ((Flushable) appendable).flush();
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        Appendable appendable = this.target;
        if (appendable instanceof Closeable) {
            ((Closeable) appendable).close();
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        checkNotClosed();
        this.target.append(c);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(@CheckForNull CharSequence charSequence) throws IOException {
        checkNotClosed();
        this.target.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(@CheckForNull CharSequence charSequence, int r3, int r4) throws IOException {
        checkNotClosed();
        this.target.append(charSequence, r3, r4);
        return this;
    }

    private void checkNotClosed() throws IOException {
        if (this.closed) {
            throw new IOException("Cannot write to a closed writer.");
        }
    }
}
