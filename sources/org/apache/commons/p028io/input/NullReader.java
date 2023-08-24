package org.apache.commons.p028io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* renamed from: org.apache.commons.io.input.NullReader */
/* loaded from: classes5.dex */
public class NullReader extends Reader {
    private boolean eof;
    private long mark;
    private final boolean markSupported;
    private long position;
    private long readlimit;
    private final long size;
    private final boolean throwEofException;

    protected int processChar() {
        return 0;
    }

    protected void processChars(char[] cArr, int r2, int r3) {
    }

    public NullReader(long j) {
        this(j, true, false);
    }

    public NullReader(long j, boolean z, boolean z2) {
        this.mark = -1L;
        this.size = j;
        this.markSupported = z;
        this.throwEofException = z2;
    }

    public long getPosition() {
        return this.position;
    }

    public long getSize() {
        return this.size;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.eof = false;
        this.position = 0L;
        this.mark = -1L;
    }

    @Override // java.io.Reader
    public synchronized void mark(int r3) {
        if (!this.markSupported) {
            throw new UnsupportedOperationException("Mark not supported");
        }
        this.mark = this.position;
        this.readlimit = r3;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return this.markSupported;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        if (this.eof) {
            throw new IOException("Read after end of file");
        }
        long j = this.position;
        if (j == this.size) {
            return doEndOfFile();
        }
        this.position = j + 1;
        return processChar();
    }

    @Override // java.io.Reader
    public int read(char[] cArr) throws IOException {
        return read(cArr, 0, cArr.length);
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int r8, int r9) throws IOException {
        if (this.eof) {
            throw new IOException("Read after end of file");
        }
        long j = this.position;
        long j2 = this.size;
        if (j == j2) {
            return doEndOfFile();
        }
        long j3 = j + r9;
        this.position = j3;
        if (j3 > j2) {
            r9 -= (int) (j3 - j2);
            this.position = j2;
        }
        processChars(cArr, r8, r9);
        return r9;
    }

    @Override // java.io.Reader
    public synchronized void reset() throws IOException {
        if (!this.markSupported) {
            throw new UnsupportedOperationException("Mark not supported");
        }
        long j = this.mark;
        if (j < 0) {
            throw new IOException("No position has been marked");
        }
        if (this.position > this.readlimit + j) {
            throw new IOException("Marked position [" + this.mark + "] is no longer valid - passed the read limit [" + this.readlimit + "]");
        }
        this.position = j;
        this.eof = false;
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        if (this.eof) {
            throw new IOException("Skip after end of file");
        }
        long j2 = this.position;
        long j3 = this.size;
        if (j2 == j3) {
            return doEndOfFile();
        }
        long j4 = j2 + j;
        this.position = j4;
        if (j4 > j3) {
            long j5 = j - (j4 - j3);
            this.position = j3;
            return j5;
        }
        return j;
    }

    private int doEndOfFile() throws EOFException {
        this.eof = true;
        if (this.throwEofException) {
            throw new EOFException();
        }
        return -1;
    }
}
