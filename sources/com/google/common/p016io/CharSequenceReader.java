package com.google.common.p016io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.CharSequenceReader */
/* loaded from: classes3.dex */
final class CharSequenceReader extends Reader {
    private int mark;
    private int pos;
    @CheckForNull
    private CharSequence seq;

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    public CharSequenceReader(CharSequence charSequence) {
        this.seq = (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    private void checkOpen() throws IOException {
        if (this.seq == null) {
            throw new IOException("reader closed");
        }
    }

    private boolean hasRemaining() {
        return remaining() > 0;
    }

    private int remaining() {
        Objects.requireNonNull(this.seq);
        return this.seq.length() - this.pos;
    }

    @Override // java.io.Reader, java.lang.Readable
    public synchronized int read(CharBuffer charBuffer) throws IOException {
        Preconditions.checkNotNull(charBuffer);
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (hasRemaining()) {
            int min = Math.min(charBuffer.remaining(), remaining());
            for (int r1 = 0; r1 < min; r1++) {
                CharSequence charSequence = this.seq;
                int r3 = this.pos;
                this.pos = r3 + 1;
                charBuffer.put(charSequence.charAt(r3));
            }
            return min;
        }
        return -1;
    }

    @Override // java.io.Reader
    public synchronized int read() throws IOException {
        char c;
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (hasRemaining()) {
            CharSequence charSequence = this.seq;
            int r1 = this.pos;
            this.pos = r1 + 1;
            c = charSequence.charAt(r1);
        } else {
            c = 65535;
        }
        return c;
    }

    @Override // java.io.Reader
    public synchronized int read(char[] cArr, int r7, int r8) throws IOException {
        Preconditions.checkPositionIndexes(r7, r7 + r8, cArr.length);
        checkOpen();
        Objects.requireNonNull(this.seq);
        if (hasRemaining()) {
            int min = Math.min(r8, remaining());
            for (int r0 = 0; r0 < min; r0++) {
                CharSequence charSequence = this.seq;
                int r3 = this.pos;
                this.pos = r3 + 1;
                cArr[r7 + r0] = charSequence.charAt(r3);
            }
            return min;
        }
        return -1;
    }

    @Override // java.io.Reader
    public synchronized long skip(long j) throws IOException {
        int min;
        Preconditions.checkArgument(j >= 0, "n (%s) may not be negative", j);
        checkOpen();
        min = (int) Math.min(remaining(), j);
        this.pos += min;
        return min;
    }

    @Override // java.io.Reader
    public synchronized boolean ready() throws IOException {
        checkOpen();
        return true;
    }

    @Override // java.io.Reader
    public synchronized void mark(int r3) throws IOException {
        Preconditions.checkArgument(r3 >= 0, "readAheadLimit (%s) may not be negative", r3);
        checkOpen();
        this.mark = this.pos;
    }

    @Override // java.io.Reader
    public synchronized void reset() throws IOException {
        checkOpen();
        this.pos = this.mark;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.seq = null;
    }
}
