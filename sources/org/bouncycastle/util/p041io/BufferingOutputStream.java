package org.bouncycastle.util.p041io;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;

/* renamed from: org.bouncycastle.util.io.BufferingOutputStream */
/* loaded from: classes4.dex */
public class BufferingOutputStream extends OutputStream {
    private final byte[] buf;
    private int bufOff;
    private final OutputStream other;

    public BufferingOutputStream(OutputStream outputStream) {
        this.other = outputStream;
        this.buf = new byte[4096];
    }

    public BufferingOutputStream(OutputStream outputStream, int r2) {
        this.other = outputStream;
        this.buf = new byte[r2];
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        flush();
        this.other.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.other.write(this.buf, 0, this.bufOff);
        this.bufOff = 0;
        Arrays.fill(this.buf, (byte) 0);
    }

    @Override // java.io.OutputStream
    public void write(int r4) throws IOException {
        byte[] bArr = this.buf;
        int r1 = this.bufOff;
        int r2 = r1 + 1;
        this.bufOff = r2;
        bArr[r1] = (byte) r4;
        if (r2 == bArr.length) {
            flush();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r5, int r6) throws IOException {
        byte[] bArr2;
        byte[] bArr3 = this.buf;
        int length = bArr3.length;
        int r2 = this.bufOff;
        if (r6 < length - r2) {
            System.arraycopy(bArr, r5, bArr3, r2, r6);
        } else {
            int length2 = bArr3.length - r2;
            System.arraycopy(bArr, r5, bArr3, r2, length2);
            this.bufOff += length2;
            flush();
            int r52 = r5 + length2;
            r6 -= length2;
            while (true) {
                bArr2 = this.buf;
                if (r6 < bArr2.length) {
                    break;
                }
                this.other.write(bArr, r52, bArr2.length);
                byte[] bArr4 = this.buf;
                r52 += bArr4.length;
                r6 -= bArr4.length;
            }
            if (r6 <= 0) {
                return;
            }
            System.arraycopy(bArr, r52, bArr2, this.bufOff, r6);
        }
        this.bufOff += r6;
    }
}
