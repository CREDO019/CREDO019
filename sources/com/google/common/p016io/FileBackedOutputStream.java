package com.google.common.p016io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* renamed from: com.google.common.io.FileBackedOutputStream */
/* loaded from: classes3.dex */
public final class FileBackedOutputStream extends OutputStream {
    @CheckForNull
    private File file;
    private final int fileThreshold;
    @CheckForNull
    private MemoryOutput memory;
    private OutputStream out;
    @CheckForNull
    private final File parentDirectory;
    private final boolean resetOnFinalize;
    private final ByteSource source;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.common.io.FileBackedOutputStream$MemoryOutput */
    /* loaded from: classes3.dex */
    public static class MemoryOutput extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        byte[] getBuffer() {
            return this.buf;
        }

        int getCount() {
            return this.count;
        }
    }

    @CheckForNull
    synchronized File getFile() {
        return this.file;
    }

    public FileBackedOutputStream(int r2) {
        this(r2, false);
    }

    public FileBackedOutputStream(int r2, boolean z) {
        this(r2, z, null);
    }

    private FileBackedOutputStream(int r1, boolean z, @CheckForNull File file) {
        this.fileThreshold = r1;
        this.resetOnFinalize = z;
        this.parentDirectory = file;
        MemoryOutput memoryOutput = new MemoryOutput();
        this.memory = memoryOutput;
        this.out = memoryOutput;
        if (z) {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.1
                @Override // com.google.common.p016io.ByteSource
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }

                protected void finalize() {
                    try {
                        FileBackedOutputStream.this.reset();
                    } catch (Throwable th) {
                        th.printStackTrace(System.err);
                    }
                }
            };
        } else {
            this.source = new ByteSource() { // from class: com.google.common.io.FileBackedOutputStream.2
                @Override // com.google.common.p016io.ByteSource
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        }
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized InputStream openInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        Objects.requireNonNull(this.memory);
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    public synchronized void reset() throws IOException {
        close();
        MemoryOutput memoryOutput = this.memory;
        if (memoryOutput == null) {
            this.memory = new MemoryOutput();
        } else {
            memoryOutput.reset();
        }
        this.out = this.memory;
        File file = this.file;
        if (file != null) {
            this.file = null;
            if (!file.delete()) {
                String valueOf = String.valueOf(file);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append("Could not delete: ");
                sb.append(valueOf);
                throw new IOException(sb.toString());
            }
        }
    }

    @Override // java.io.OutputStream
    public synchronized void write(int r2) throws IOException {
        update(1);
        this.out.write(r2);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public synchronized void write(byte[] bArr, int r3, int r4) throws IOException {
        update(r4);
        this.out.write(bArr, r3, r4);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public synchronized void flush() throws IOException {
        this.out.flush();
    }

    private void update(int r6) throws IOException {
        MemoryOutput memoryOutput = this.memory;
        if (memoryOutput == null || memoryOutput.getCount() + r6 <= this.fileThreshold) {
            return;
        }
        File createTempFile = File.createTempFile("FileBackedOutputStream", null, this.parentDirectory);
        if (this.resetOnFinalize) {
            createTempFile.deleteOnExit();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            fileOutputStream.write(this.memory.getBuffer(), 0, this.memory.getCount());
            fileOutputStream.flush();
            this.out = fileOutputStream;
            this.file = createTempFile;
            this.memory = null;
        } catch (IOException e) {
            createTempFile.delete();
            throw e;
        }
    }
}
