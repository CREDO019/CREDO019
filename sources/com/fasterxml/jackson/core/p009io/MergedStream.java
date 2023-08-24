package com.fasterxml.jackson.core.p009io;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.fasterxml.jackson.core.io.MergedStream */
/* loaded from: classes.dex */
public final class MergedStream extends InputStream {

    /* renamed from: _b */
    private byte[] f182_b;
    private final IOContext _ctxt;
    private final int _end;
    private final InputStream _in;
    private int _ptr;

    public MergedStream(IOContext iOContext, InputStream inputStream, byte[] bArr, int r4, int r5) {
        this._ctxt = iOContext;
        this._in = inputStream;
        this.f182_b = bArr;
        this._ptr = r4;
        this._end = r5;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.f182_b != null) {
            return this._end - this._ptr;
        }
        return this._in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        _free();
        this._in.close();
    }

    @Override // java.io.InputStream
    public void mark(int r2) {
        if (this.f182_b == null) {
            this._in.mark(r2);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f182_b == null && this._in.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = this.f182_b;
        if (bArr != null) {
            int r1 = this._ptr;
            int r2 = r1 + 1;
            this._ptr = r2;
            int r0 = bArr[r1] & 255;
            if (r2 >= this._end) {
                _free();
            }
            return r0;
        }
        return this._in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int r5, int r6) throws IOException {
        byte[] bArr2 = this.f182_b;
        if (bArr2 != null) {
            int r1 = this._end;
            int r2 = this._ptr;
            int r12 = r1 - r2;
            if (r6 > r12) {
                r6 = r12;
            }
            System.arraycopy(bArr2, r2, bArr, r5, r6);
            int r4 = this._ptr + r6;
            this._ptr = r4;
            if (r4 >= this._end) {
                _free();
            }
            return r6;
        }
        return this._in.read(bArr, r5, r6);
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        if (this.f182_b == null) {
            this._in.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2;
        if (this.f182_b != null) {
            int r0 = this._end;
            int r3 = this._ptr;
            long j3 = r0 - r3;
            if (j3 > j) {
                this._ptr = r3 + ((int) j);
                return j;
            }
            _free();
            j2 = j3 + 0;
            j -= j3;
        } else {
            j2 = 0;
        }
        return j > 0 ? j2 + this._in.skip(j) : j2;
    }

    private void _free() {
        byte[] bArr = this.f182_b;
        if (bArr != null) {
            this.f182_b = null;
            IOContext iOContext = this._ctxt;
            if (iOContext != null) {
                iOContext.releaseReadIOBuffer(bArr);
            }
        }
    }
}
