package com.fasterxml.jackson.core.util;

/* loaded from: classes.dex */
public class BufferRecycler {
    public static final int BYTE_BASE64_CODEC_BUFFER = 3;
    public static final int BYTE_READ_IO_BUFFER = 0;
    public static final int BYTE_WRITE_CONCAT_BUFFER = 2;
    public static final int BYTE_WRITE_ENCODING_BUFFER = 1;
    public static final int CHAR_CONCAT_BUFFER = 1;
    public static final int CHAR_NAME_COPY_BUFFER = 3;
    public static final int CHAR_TEXT_BUFFER = 2;
    public static final int CHAR_TOKEN_BUFFER = 0;
    protected final byte[][] _byteBuffers;
    protected final char[][] _charBuffers;
    private static final int[] BYTE_BUFFER_LENGTHS = {8000, 8000, 2000, 2000};
    private static final int[] CHAR_BUFFER_LENGTHS = {4000, 4000, 200, 200};

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(int r1, int r2) {
        this._byteBuffers = new byte[r1];
        this._charBuffers = new char[r2];
    }

    public final byte[] allocByteBuffer(int r2) {
        return allocByteBuffer(r2, 0);
    }

    public byte[] allocByteBuffer(int r4, int r5) {
        int byteBufferLength = byteBufferLength(r4);
        if (r5 < byteBufferLength) {
            r5 = byteBufferLength;
        }
        byte[][] bArr = this._byteBuffers;
        byte[] bArr2 = bArr[r4];
        if (bArr2 == null || bArr2.length < r5) {
            return balloc(r5);
        }
        bArr[r4] = null;
        return bArr2;
    }

    public final void releaseByteBuffer(int r2, byte[] bArr) {
        this._byteBuffers[r2] = bArr;
    }

    public final char[] allocCharBuffer(int r2) {
        return allocCharBuffer(r2, 0);
    }

    public char[] allocCharBuffer(int r4, int r5) {
        int charBufferLength = charBufferLength(r4);
        if (r5 < charBufferLength) {
            r5 = charBufferLength;
        }
        char[][] cArr = this._charBuffers;
        char[] cArr2 = cArr[r4];
        if (cArr2 == null || cArr2.length < r5) {
            return calloc(r5);
        }
        cArr[r4] = null;
        return cArr2;
    }

    public void releaseCharBuffer(int r2, char[] cArr) {
        this._charBuffers[r2] = cArr;
    }

    protected int byteBufferLength(int r2) {
        return BYTE_BUFFER_LENGTHS[r2];
    }

    protected int charBufferLength(int r2) {
        return CHAR_BUFFER_LENGTHS[r2];
    }

    protected byte[] balloc(int r1) {
        return new byte[r1];
    }

    protected char[] calloc(int r1) {
        return new char[r1];
    }
}
