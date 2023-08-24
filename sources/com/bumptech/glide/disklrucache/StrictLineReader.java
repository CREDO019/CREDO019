package com.bumptech.glide.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
class StrictLineReader implements Closeable {

    /* renamed from: CR */
    private static final byte f93CR = 13;

    /* renamed from: LF */
    private static final byte f94LF = 10;
    private byte[] buf;
    private final Charset charset;
    private int end;

    /* renamed from: in */
    private final InputStream f95in;
    private int pos;

    public StrictLineReader(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public StrictLineReader(InputStream inputStream, int r3, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (r3 < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(Util.US_ASCII)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f95in = inputStream;
        this.charset = charset;
        this.buf = new byte[r3];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.f95in) {
            if (this.buf != null) {
                this.buf = null;
                this.f95in.close();
            }
        }
    }

    public String readLine() throws IOException {
        int r2;
        byte[] bArr;
        int r4;
        synchronized (this.f95in) {
            if (this.buf == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.pos >= this.end) {
                fillBuf();
            }
            for (int r1 = this.pos; r1 != this.end; r1++) {
                byte[] bArr2 = this.buf;
                if (bArr2[r1] == 10) {
                    int r3 = this.pos;
                    if (r1 != r3) {
                        r4 = r1 - 1;
                        if (bArr2[r4] == 13) {
                            String str = new String(bArr2, r3, r4 - r3, this.charset.name());
                            this.pos = r1 + 1;
                            return str;
                        }
                    }
                    r4 = r1;
                    String str2 = new String(bArr2, r3, r4 - r3, this.charset.name());
                    this.pos = r1 + 1;
                    return str2;
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((this.end - this.pos) + 80) { // from class: com.bumptech.glide.disklrucache.StrictLineReader.1
                @Override // java.io.ByteArrayOutputStream
                public String toString() {
                    try {
                        return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1, StrictLineReader.this.charset.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1: while (true) {
                byte[] bArr3 = this.buf;
                int r42 = this.pos;
                byteArrayOutputStream.write(bArr3, r42, this.end - r42);
                this.end = -1;
                fillBuf();
                r2 = this.pos;
                while (r2 != this.end) {
                    bArr = this.buf;
                    if (bArr[r2] == 10) {
                        break loop1;
                    }
                    r2++;
                }
            }
            int r32 = this.pos;
            if (r2 != r32) {
                byteArrayOutputStream.write(bArr, r32, r2 - r32);
            }
            this.pos = r2 + 1;
            return byteArrayOutputStream.toString();
        }
    }

    public boolean hasUnterminatedLine() {
        return this.end == -1;
    }

    private void fillBuf() throws IOException {
        InputStream inputStream = this.f95in;
        byte[] bArr = this.buf;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.pos = 0;
        this.end = read;
    }
}
