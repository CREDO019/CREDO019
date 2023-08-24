package org.apache.commons.p028io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.apache.commons.io.output.ChunkedOutputStream */
/* loaded from: classes5.dex */
public class ChunkedOutputStream extends FilterOutputStream {
    private static final int DEFAULT_CHUNK_SIZE = 4096;
    private final int chunkSize;

    public ChunkedOutputStream(OutputStream outputStream, int r2) {
        super(outputStream);
        if (r2 <= 0) {
            throw new IllegalArgumentException();
        }
        this.chunkSize = r2;
    }

    public ChunkedOutputStream(OutputStream outputStream) {
        this(outputStream, 4096);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int r4, int r5) throws IOException {
        while (r5 > 0) {
            int min = Math.min(r5, this.chunkSize);
            this.out.write(bArr, r4, min);
            r5 -= min;
            r4 += min;
        }
    }
}
