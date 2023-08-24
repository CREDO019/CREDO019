package org.apache.commons.codec.binary;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import org.apache.commons.codec.binary.BaseNCodec;

/* loaded from: classes5.dex */
public class BaseNCodecOutputStream extends FilterOutputStream {
    private final BaseNCodec baseNCodec;
    private final BaseNCodec.Context context;
    private final boolean doEncode;
    private final byte[] singleByte;

    public BaseNCodecOutputStream(OutputStream outputStream, BaseNCodec baseNCodec, boolean z) {
        super(outputStream);
        this.singleByte = new byte[1];
        this.context = new BaseNCodec.Context();
        this.baseNCodec = baseNCodec;
        this.doEncode = z;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int r3) throws IOException {
        byte[] bArr = this.singleByte;
        bArr[0] = (byte) r3;
        write(bArr, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int r4, int r5) throws IOException {
        Objects.requireNonNull(bArr);
        if (r4 < 0 || r5 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (r4 > bArr.length || r4 + r5 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (r5 > 0) {
            if (this.doEncode) {
                this.baseNCodec.encode(bArr, r4, r5, this.context);
            } else {
                this.baseNCodec.decode(bArr, r4, r5, this.context);
            }
            flush(false);
        }
    }

    private void flush(boolean z) throws IOException {
        byte[] bArr;
        int readResults;
        int available = this.baseNCodec.available(this.context);
        if (available > 0 && (readResults = this.baseNCodec.readResults((bArr = new byte[available]), 0, available, this.context)) > 0) {
            this.out.write(bArr, 0, readResults);
        }
        if (z) {
            this.out.flush();
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        flush(true);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.doEncode) {
            this.baseNCodec.encode(this.singleByte, 0, -1, this.context);
        } else {
            this.baseNCodec.decode(this.singleByte, 0, -1, this.context);
        }
        flush();
        this.out.close();
    }
}
