package org.apache.commons.p028io.input;

import java.io.Reader;
import java.io.Serializable;
import java.util.Objects;

/* renamed from: org.apache.commons.io.input.CharSequenceReader */
/* loaded from: classes5.dex */
public class CharSequenceReader extends Reader implements Serializable {
    private static final long serialVersionUID = 3724187752191401220L;
    private final CharSequence charSequence;
    private int idx;
    private int mark;

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    public CharSequenceReader(String str) {
        this.charSequence = str == null ? "" : str;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.idx = 0;
        this.mark = 0;
    }

    @Override // java.io.Reader
    public void mark(int r1) {
        this.mark = this.idx;
    }

    @Override // java.io.Reader
    public int read() {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        CharSequence charSequence = this.charSequence;
        int r1 = this.idx;
        this.idx = r1 + 1;
        return charSequence.charAt(r1);
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int r7, int r8) {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        Objects.requireNonNull(cArr, "Character array is missing");
        if (r8 < 0 || r7 < 0 || r7 + r8 > cArr.length) {
            throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + r7 + ", length=" + r8);
        }
        int r1 = 0;
        for (int r0 = 0; r0 < r8; r0++) {
            int read = read();
            if (read == -1) {
                return r1;
            }
            cArr[r7 + r0] = (char) read;
            r1++;
        }
        return r1;
    }

    @Override // java.io.Reader
    public void reset() {
        this.idx = this.mark;
    }

    @Override // java.io.Reader
    public long skip(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Number of characters to skip is less than zero: " + j);
        } else if (this.idx >= this.charSequence.length()) {
            return -1L;
        } else {
            int min = (int) Math.min(this.charSequence.length(), this.idx + j);
            this.idx = min;
            return min - this.idx;
        }
    }

    public String toString() {
        return this.charSequence.toString();
    }
}
