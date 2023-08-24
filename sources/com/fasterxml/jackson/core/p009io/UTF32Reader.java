package com.fasterxml.jackson.core.p009io;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* renamed from: com.fasterxml.jackson.core.io.UTF32Reader */
/* loaded from: classes.dex */
public class UTF32Reader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;

    /* renamed from: NC */
    protected static final char f183NC = 0;
    protected final boolean _bigEndian;
    protected byte[] _buffer;
    protected int _byteCount;
    protected int _charCount;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected final boolean _managedBuffers;
    protected int _ptr;
    protected char _surrogate = 0;
    protected char[] _tmpBuf;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int r5, int r6, boolean z) {
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = r5;
        this._length = r6;
        this._bigEndian = z;
        this._managedBuffers = inputStream != null;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            this._in = null;
            freeBuffers();
            inputStream.close();
        }
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int r12, int r13) throws IOException {
        int r1;
        int r6;
        int r5;
        int r4;
        if (this._buffer == null) {
            return -1;
        }
        if (r13 < 1) {
            return r13;
        }
        if (r12 < 0 || r12 + r13 > cArr.length) {
            reportBounds(cArr, r12, r13);
        }
        int r132 = r13 + r12;
        char c = this._surrogate;
        if (c != 0) {
            r1 = r12 + 1;
            cArr[r12] = c;
            this._surrogate = (char) 0;
        } else {
            int r2 = this._length - this._ptr;
            if (r2 < 4 && !loadMore(r2)) {
                if (r2 == 0) {
                    return -1;
                }
                reportUnexpectedEOF(this._length - this._ptr, 4);
            }
            r1 = r12;
        }
        int r22 = this._length - 3;
        while (r1 < r132) {
            int r42 = this._ptr;
            if (this._bigEndian) {
                byte[] bArr = this._buffer;
                r6 = (bArr[r42] << 8) | (bArr[r42 + 1] & 255);
                r5 = (bArr[r42 + 3] & 255) | ((bArr[r42 + 2] & 255) << 8);
            } else {
                byte[] bArr2 = this._buffer;
                int r62 = (bArr2[r42] & 255) | ((bArr2[r42 + 1] & 255) << 8);
                r6 = (bArr2[r42 + 3] << 8) | (bArr2[r42 + 2] & 255);
                r5 = r62;
            }
            this._ptr = r42 + 4;
            if (r6 != 0) {
                int r43 = 65535 & r6;
                int r52 = r5 | ((r43 - 1) << 16);
                if (r43 > 16) {
                    reportInvalid(r52, r1 - r12, String.format(" (above 0x%08x)", Integer.valueOf((int) LAST_VALID_UNICODE_CHAR)));
                }
                r4 = r1 + 1;
                cArr[r1] = (char) ((r52 >> 10) + GeneratorBase.SURR1_FIRST);
                int r14 = 56320 | (r52 & AnalyticsListener.EVENT_DRM_KEYS_LOADED);
                if (r4 >= r132) {
                    this._surrogate = (char) r52;
                    r1 = r4;
                    break;
                }
                r5 = r14;
                r1 = r4;
            }
            r4 = r1 + 1;
            cArr[r1] = (char) r5;
            if (this._ptr > r22) {
                r1 = r4;
                break;
            }
            r1 = r4;
        }
        int r15 = r1 - r12;
        this._charCount += r15;
        return r15;
    }

    private void reportUnexpectedEOF(int r6, int r7) throws IOException {
        int r1 = this._charCount;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + r6 + ", needed " + r7 + ", at char #" + r1 + ", byte #" + (this._byteCount + r6) + ")");
    }

    private void reportInvalid(int r5, int r6, String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid UTF-32 character 0x");
        sb.append(Integer.toHexString(r5));
        sb.append(str);
        sb.append(" at char #");
        sb.append(this._charCount + r6);
        sb.append(", byte #");
        sb.append((this._byteCount + this._ptr) - 1);
        sb.append(")");
        throw new CharConversionException(sb.toString());
    }

    private boolean loadMore(int r7) throws IOException {
        int read;
        this._byteCount += this._length - r7;
        if (r7 > 0) {
            int r3 = this._ptr;
            if (r3 > 0) {
                byte[] bArr = this._buffer;
                System.arraycopy(bArr, r3, bArr, 0, r7);
                this._ptr = 0;
            }
            this._length = r7;
        } else {
            this._ptr = 0;
            InputStream inputStream = this._in;
            int read2 = inputStream == null ? -1 : inputStream.read(this._buffer);
            if (read2 < 1) {
                this._length = 0;
                if (read2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read2;
        }
        while (true) {
            int r72 = this._length;
            if (r72 >= 4) {
                return true;
            }
            InputStream inputStream2 = this._in;
            if (inputStream2 == null) {
                read = -1;
            } else {
                byte[] bArr2 = this._buffer;
                read = inputStream2.read(bArr2, r72, bArr2.length - r72);
            }
            if (read < 1) {
                if (read < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += read;
        }
    }

    private void freeBuffers() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(bArr);
        }
    }

    private void reportBounds(char[] cArr, int r5, int r6) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + r5 + "," + r6 + "), cbuf[" + cArr.length + "]");
    }

    private void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
