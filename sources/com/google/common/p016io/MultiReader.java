package com.google.common.p016io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.MultiReader */
/* loaded from: classes3.dex */
class MultiReader extends Reader {
    @CheckForNull
    private Reader current;

    /* renamed from: it */
    private final Iterator<? extends CharSource> f1198it;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiReader(Iterator<? extends CharSource> it) throws IOException {
        this.f1198it = it;
        advance();
    }

    private void advance() throws IOException {
        close();
        if (this.f1198it.hasNext()) {
            this.current = this.f1198it.next().openStream();
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int r4, int r5) throws IOException {
        Preconditions.checkNotNull(cArr);
        Reader reader = this.current;
        if (reader == null) {
            return -1;
        }
        int read = reader.read(cArr, r4, r5);
        if (read == -1) {
            advance();
            return read(cArr, r4, r5);
        }
        return read;
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        Preconditions.checkArgument(r2 >= 0, "n is negative");
        if (r2 > 0) {
            while (true) {
                Reader reader = this.current;
                if (reader == null) {
                    break;
                }
                long skip = reader.skip(j);
                if (skip > 0) {
                    return skip;
                }
                advance();
            }
        }
        return 0L;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        Reader reader = this.current;
        return reader != null && reader.ready();
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Reader reader = this.current;
        if (reader != null) {
            try {
                reader.close();
            } finally {
                this.current = null;
            }
        }
    }
}
