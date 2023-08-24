package org.apache.commons.p028io.input;

import java.io.IOException;
import java.io.Reader;

/* renamed from: org.apache.commons.io.input.BoundedReader */
/* loaded from: classes5.dex */
public class BoundedReader extends Reader {
    private static final int INVALID = -1;
    private int charsRead = 0;
    private int markedAt = -1;
    private final int maxCharsFromTargetReader;
    private int readAheadLimit;
    private final Reader target;

    public BoundedReader(Reader reader, int r3) throws IOException {
        this.target = reader;
        this.maxCharsFromTargetReader = r3;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.target.close();
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.charsRead = this.markedAt;
        this.target.reset();
    }

    @Override // java.io.Reader
    public void mark(int r3) throws IOException {
        int r0 = this.charsRead;
        this.readAheadLimit = r3 - r0;
        this.markedAt = r0;
        this.target.mark(r3);
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int r0 = this.charsRead;
        if (r0 >= this.maxCharsFromTargetReader) {
            return -1;
        }
        int r1 = this.markedAt;
        if (r1 < 0 || r0 - r1 < this.readAheadLimit) {
            this.charsRead = r0 + 1;
            return this.target.read();
        }
        return -1;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int r5, int r6) throws IOException {
        for (int r0 = 0; r0 < r6; r0++) {
            int read = read();
            if (read == -1) {
                if (r0 == 0) {
                    return -1;
                }
                return r0;
            }
            cArr[r5 + r0] = (char) read;
        }
        return r6;
    }
}
