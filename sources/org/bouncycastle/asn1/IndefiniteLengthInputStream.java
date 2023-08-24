package org.bouncycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class IndefiniteLengthInputStream extends LimitedInputStream {
    private int _b1;
    private int _b2;
    private boolean _eofOn00;
    private boolean _eofReached;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IndefiniteLengthInputStream(InputStream inputStream, int r2) throws IOException {
        super(inputStream, r2);
        this._eofReached = false;
        this._eofOn00 = true;
        this._b1 = inputStream.read();
        int read = inputStream.read();
        this._b2 = read;
        if (read < 0) {
            throw new EOFException();
        }
        checkForEof();
    }

    private boolean checkForEof() {
        if (!this._eofReached && this._eofOn00 && this._b1 == 0 && this._b2 == 0) {
            this._eofReached = true;
            setParentEofDetect(true);
        }
        return this._eofReached;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (checkForEof()) {
            return -1;
        }
        int read = this._in.read();
        if (read >= 0) {
            int r1 = this._b1;
            this._b1 = this._b2;
            this._b2 = read;
            return r1;
        }
        throw new EOFException();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int r4, int r5) throws IOException {
        if (this._eofOn00 || r5 < 3) {
            return super.read(bArr, r4, r5);
        }
        if (this._eofReached) {
            return -1;
        }
        int read = this._in.read(bArr, r4 + 2, r5 - 2);
        if (read >= 0) {
            bArr[r4] = (byte) this._b1;
            bArr[r4 + 1] = (byte) this._b2;
            this._b1 = this._in.read();
            int read2 = this._in.read();
            this._b2 = read2;
            if (read2 >= 0) {
                return read + 2;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEofOn00(boolean z) {
        this._eofOn00 = z;
        checkForEof();
    }
}
