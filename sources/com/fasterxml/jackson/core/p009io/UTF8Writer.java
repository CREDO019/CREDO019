package com.fasterxml.jackson.core.p009io;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* renamed from: com.fasterxml.jackson.core.io.UTF8Writer */
/* loaded from: classes.dex */
public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    private final IOContext _context;
    private OutputStream _out;
    private byte[] _outBuffer;
    private final int _outBufferEnd;
    private int _outPtr;
    private int _surrogate;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._context = iOContext;
        this._out = outputStream;
        byte[] allocWriteEncodingBuffer = iOContext.allocWriteEncodingBuffer();
        this._outBuffer = allocWriteEncodingBuffer;
        this._outBufferEnd = allocWriteEncodingBuffer.length - 4;
        this._outPtr = 0;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        write(c);
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int r1 = this._outPtr;
            if (r1 > 0) {
                outputStream.write(this._outBuffer, 0, r1);
                this._outPtr = 0;
            }
            OutputStream outputStream2 = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream2.close();
            int r0 = this._surrogate;
            this._surrogate = 0;
            if (r0 > 0) {
                illegalSurrogate(r0);
            }
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this._out;
        if (outputStream != null) {
            int r1 = this._outPtr;
            if (r1 > 0) {
                outputStream.write(this._outBuffer, 0, r1);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0025, code lost:
        continue;
     */
    @Override // java.io.Writer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(char[] r8, int r9, int r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 228
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.p009io.UTF8Writer.write(char[], int, int):void");
    }

    @Override // java.io.Writer
    public void write(int r6) throws IOException {
        int r1;
        if (this._surrogate > 0) {
            r6 = convertSurrogate(r6);
        } else if (r6 >= 55296 && r6 <= 57343) {
            if (r6 > 56319) {
                illegalSurrogate(r6);
            }
            this._surrogate = r6;
            return;
        }
        int r0 = this._outPtr;
        if (r0 >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, r0);
            this._outPtr = 0;
        }
        if (r6 < 128) {
            byte[] bArr = this._outBuffer;
            int r12 = this._outPtr;
            this._outPtr = r12 + 1;
            bArr[r12] = (byte) r6;
            return;
        }
        int r13 = this._outPtr;
        if (r6 < 2048) {
            byte[] bArr2 = this._outBuffer;
            int r3 = r13 + 1;
            bArr2[r13] = (byte) ((r6 >> 6) | 192);
            r1 = r3 + 1;
            bArr2[r3] = (byte) ((r6 & 63) | 128);
        } else if (r6 <= 65535) {
            byte[] bArr3 = this._outBuffer;
            int r32 = r13 + 1;
            bArr3[r13] = (byte) ((r6 >> 12) | 224);
            int r14 = r32 + 1;
            bArr3[r32] = (byte) (((r6 >> 6) & 63) | 128);
            bArr3[r14] = (byte) ((r6 & 63) | 128);
            r1 = r14 + 1;
        } else {
            if (r6 > 1114111) {
                illegalSurrogate(r6);
            }
            byte[] bArr4 = this._outBuffer;
            int r33 = r13 + 1;
            bArr4[r13] = (byte) ((r6 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
            int r15 = r33 + 1;
            bArr4[r33] = (byte) (((r6 >> 12) & 63) | 128);
            int r34 = r15 + 1;
            bArr4[r15] = (byte) (((r6 >> 6) & 63) | 128);
            r1 = r34 + 1;
            bArr4[r34] = (byte) ((r6 & 63) | 128);
        }
        this._outPtr = r1;
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0029, code lost:
        continue;
     */
    @Override // java.io.Writer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(java.lang.String r8, int r9, int r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.p009io.UTF8Writer.write(java.lang.String, int, int):void");
    }

    protected int convertSurrogate(int r5) throws IOException {
        int r0 = this._surrogate;
        this._surrogate = 0;
        if (r5 < 56320 || r5 > 57343) {
            throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(r0) + ", second 0x" + Integer.toHexString(r5) + "; illegal combination");
        }
        return ((r0 - 55296) << 10) + 65536 + (r5 - 56320);
    }

    protected static void illegalSurrogate(int r1) throws IOException {
        throw new IOException(illegalSurrogateDesc(r1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String illegalSurrogateDesc(int r3) {
        if (r3 > 1114111) {
            return "Illegal character point (0x" + Integer.toHexString(r3) + ") to output; max is 0x10FFFF as per RFC 4627";
        } else if (r3 < 55296) {
            return "Illegal character point (0x" + Integer.toHexString(r3) + ") to output";
        } else if (r3 <= 56319) {
            return "Unmatched first part of surrogate pair (0x" + Integer.toHexString(r3) + ")";
        } else {
            return "Unmatched second part of surrogate pair (0x" + Integer.toHexString(r3) + ")";
        }
    }
}
