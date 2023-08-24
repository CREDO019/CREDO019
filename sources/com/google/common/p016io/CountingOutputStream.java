package com.google.common.p016io;

import com.google.common.base.Preconditions;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.CountingOutputStream */
/* loaded from: classes3.dex */
public final class CountingOutputStream extends FilterOutputStream {
    private long count;

    public CountingOutputStream(OutputStream outputStream) {
        super((OutputStream) Preconditions.checkNotNull(outputStream));
    }

    public long getCount() {
        return this.count;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int r4, int r5) throws IOException {
        this.out.write(bArr, r4, r5);
        this.count += r5;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int r5) throws IOException {
        this.out.write(r5);
        this.count++;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
