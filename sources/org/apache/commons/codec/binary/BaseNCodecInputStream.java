package org.apache.commons.codec.binary;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.codec.binary.BaseNCodec;

/* loaded from: classes5.dex */
public class BaseNCodecInputStream extends FilterInputStream {
    private final BaseNCodec baseNCodec;
    private final BaseNCodec.Context context;
    private final boolean doEncode;
    private final byte[] singleByte;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNCodecInputStream(InputStream inputStream, BaseNCodec baseNCodec, boolean z) {
        super(inputStream);
        this.singleByte = new byte[1];
        this.context = new BaseNCodec.Context();
        this.doEncode = z;
        this.baseNCodec = baseNCodec;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return !this.context.eof ? 1 : 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int r1) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = read(this.singleByte, 0, 1);
        while (read == 0) {
            read = read(this.singleByte, 0, 1);
        }
        if (read > 0) {
            byte b = this.singleByte[0];
            return b < 0 ? b + 256 : b;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int r7, int r8) throws IOException {
        Objects.requireNonNull(bArr);
        if (r7 < 0 || r8 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (r7 > bArr.length || r7 + r8 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (r8 == 0) {
            return 0;
        }
        int r1 = 0;
        while (r1 == 0) {
            if (!this.baseNCodec.hasData(this.context)) {
                byte[] bArr2 = new byte[this.doEncode ? 4096 : 8192];
                int read = this.in.read(bArr2);
                if (this.doEncode) {
                    this.baseNCodec.encode(bArr2, 0, read, this.context);
                } else {
                    this.baseNCodec.decode(bArr2, 0, read, this.context);
                }
            }
            r1 = this.baseNCodec.readResults(bArr, r7, r8, this.context);
        }
        return r1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        int read;
        if (j < 0) {
            throw new IllegalArgumentException("Negative skip length: " + j);
        }
        byte[] bArr = new byte[512];
        long j2 = j;
        while (j2 > 0 && (read = read(bArr, 0, (int) Math.min(512, j2))) != -1) {
            j2 -= read;
        }
        return j - j2;
    }
}
