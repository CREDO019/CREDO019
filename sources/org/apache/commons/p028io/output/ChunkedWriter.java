package org.apache.commons.p028io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* renamed from: org.apache.commons.io.output.ChunkedWriter */
/* loaded from: classes5.dex */
public class ChunkedWriter extends FilterWriter {
    private static final int DEFAULT_CHUNK_SIZE = 4096;
    private final int chunkSize;

    public ChunkedWriter(Writer writer, int r2) {
        super(writer);
        if (r2 <= 0) {
            throw new IllegalArgumentException();
        }
        this.chunkSize = r2;
    }

    public ChunkedWriter(Writer writer) {
        this(writer, 4096);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int r4, int r5) throws IOException {
        while (r5 > 0) {
            int min = Math.min(r5, this.chunkSize);
            this.out.write(cArr, r4, min);
            r5 -= min;
            r4 += min;
        }
    }
}
