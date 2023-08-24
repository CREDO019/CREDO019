package org.bouncycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.p041io.Streams;

/* loaded from: classes5.dex */
class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefiniteLengthInputStream(InputStream inputStream, int r2, int r3) {
        super(inputStream, r3);
        if (r2 <= 0) {
            if (r2 < 0) {
                throw new IllegalArgumentException("negative lengths not allowed");
            }
            setParentEofDetect(true);
        }
        this._originalLength = r2;
        this._remaining = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRemaining() {
        return this._remaining;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int read = this._in.read();
        if (read >= 0) {
            int r1 = this._remaining - 1;
            this._remaining = r1;
            if (r1 == 0) {
                setParentEofDetect(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int r3, int r4) throws IOException {
        int r0 = this._remaining;
        if (r0 == 0) {
            return -1;
        }
        int read = this._in.read(bArr, r3, Math.min(r4, r0));
        if (read >= 0) {
            int r32 = this._remaining - read;
            this._remaining = r32;
            if (r32 == 0) {
                setParentEofDetect(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readAllIntoByteArray(byte[] bArr) throws IOException {
        int r0 = this._remaining;
        if (r0 != bArr.length) {
            throw new IllegalArgumentException("buffer length not right for data");
        }
        if (r0 == 0) {
            return;
        }
        int limit = getLimit();
        int r1 = this._remaining;
        if (r1 >= limit) {
            throw new IOException("corrupted stream - out of bounds length found: " + this._remaining + " >= " + limit);
        }
        int readFully = r1 - Streams.readFully(this._in, bArr, 0, bArr.length);
        this._remaining = readFully;
        if (readFully == 0) {
            setParentEofDetect(true);
            return;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] toByteArray() throws IOException {
        if (this._remaining == 0) {
            return EMPTY_BYTES;
        }
        int limit = getLimit();
        int r1 = this._remaining;
        if (r1 >= limit) {
            throw new IOException("corrupted stream - out of bounds length found: " + this._remaining + " >= " + limit);
        }
        byte[] bArr = new byte[r1];
        int readFully = r1 - Streams.readFully(this._in, bArr, 0, r1);
        this._remaining = readFully;
        if (readFully == 0) {
            setParentEofDetect(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
    }
}
