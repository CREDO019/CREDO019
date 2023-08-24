package org.apache.commons.p028io.input;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.apache.commons.io.input.WindowsLineEndingInputStream */
/* loaded from: classes5.dex */
public class WindowsLineEndingInputStream extends InputStream {
    private final boolean ensureLineFeedAtEndOfFile;
    private final InputStream target;
    private boolean slashRSeen = false;
    private boolean slashNSeen = false;
    private boolean injectSlashN = false;
    private boolean eofSeen = false;

    public WindowsLineEndingInputStream(InputStream inputStream, boolean z) {
        this.target = inputStream;
        this.ensureLineFeedAtEndOfFile = z;
    }

    private int readWithUpdate() throws IOException {
        int read = this.target.read();
        boolean z = read == -1;
        this.eofSeen = z;
        if (z) {
            return read;
        }
        this.slashRSeen = read == 13;
        this.slashNSeen = read == 10;
        return read;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.eofSeen) {
            return eofGame();
        }
        if (this.injectSlashN) {
            this.injectSlashN = false;
            return 10;
        }
        boolean z = this.slashRSeen;
        int readWithUpdate = readWithUpdate();
        if (this.eofSeen) {
            return eofGame();
        }
        if (readWithUpdate != 10 || z) {
            return readWithUpdate;
        }
        this.injectSlashN = true;
        return 13;
    }

    private int eofGame() {
        if (this.ensureLineFeedAtEndOfFile) {
            boolean z = this.slashNSeen;
            if (!z && !this.slashRSeen) {
                this.slashRSeen = true;
                return 13;
            } else if (z) {
                return -1;
            } else {
                this.slashRSeen = false;
                this.slashNSeen = true;
                return 10;
            }
        }
        return -1;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.target.close();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int r2) {
        throw new UnsupportedOperationException("Mark not supported");
    }
}
