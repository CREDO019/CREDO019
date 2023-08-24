package com.google.common.p016io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.MultiInputStream */
/* loaded from: classes3.dex */
final class MultiInputStream extends InputStream {
    @CheckForNull

    /* renamed from: in */
    private InputStream f1196in;

    /* renamed from: it */
    private Iterator<? extends ByteSource> f1197it;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public MultiInputStream(Iterator<? extends ByteSource> it) throws IOException {
        this.f1197it = (Iterator) Preconditions.checkNotNull(it);
        advance();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.f1196in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.f1196in = null;
            }
        }
    }

    private void advance() throws IOException {
        close();
        if (this.f1197it.hasNext()) {
            this.f1196in = this.f1197it.next().openStream();
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        InputStream inputStream = this.f1196in;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (true) {
            InputStream inputStream = this.f1196in;
            if (inputStream == null) {
                return -1;
            }
            int read = inputStream.read();
            if (read != -1) {
                return read;
            }
            advance();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int r4, int r5) throws IOException {
        Preconditions.checkNotNull(bArr);
        while (true) {
            InputStream inputStream = this.f1196in;
            if (inputStream == null) {
                return -1;
            }
            int read = inputStream.read(bArr, r4, r5);
            if (read != -1) {
                return read;
            }
            advance();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        InputStream inputStream = this.f1196in;
        if (inputStream == null || j <= 0) {
            return 0L;
        }
        long skip = inputStream.skip(j);
        if (skip != 0) {
            return skip;
        }
        if (read() == -1) {
            return 0L;
        }
        return this.f1196in.skip(j - 1) + 1;
    }
}
