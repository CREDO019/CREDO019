package org.apache.commons.p028io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Objects;
import org.apache.commons.p028io.FileUtils;

/* renamed from: org.apache.commons.io.output.FileWriterWithEncoding */
/* loaded from: classes5.dex */
public class FileWriterWithEncoding extends Writer {
    private final Writer out;

    public FileWriterWithEncoding(String str, String str2) throws IOException {
        this(new File(str), str2, false);
    }

    public FileWriterWithEncoding(String str, String str2, boolean z) throws IOException {
        this(new File(str), str2, z);
    }

    public FileWriterWithEncoding(String str, Charset charset) throws IOException {
        this(new File(str), charset, false);
    }

    public FileWriterWithEncoding(String str, Charset charset, boolean z) throws IOException {
        this(new File(str), charset, z);
    }

    public FileWriterWithEncoding(String str, CharsetEncoder charsetEncoder) throws IOException {
        this(new File(str), charsetEncoder, false);
    }

    public FileWriterWithEncoding(String str, CharsetEncoder charsetEncoder, boolean z) throws IOException {
        this(new File(str), charsetEncoder, z);
    }

    public FileWriterWithEncoding(File file, String str) throws IOException {
        this(file, str, false);
    }

    public FileWriterWithEncoding(File file, String str, boolean z) throws IOException {
        this.out = initWriter(file, str, z);
    }

    public FileWriterWithEncoding(File file, Charset charset) throws IOException {
        this(file, charset, false);
    }

    public FileWriterWithEncoding(File file, Charset charset, boolean z) throws IOException {
        this.out = initWriter(file, charset, z);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder) throws IOException {
        this(file, charsetEncoder, false);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder, boolean z) throws IOException {
        this.out = initWriter(file, charsetEncoder, z);
    }

    private static Writer initWriter(File file, Object obj, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        Objects.requireNonNull(file, "File is missing");
        Objects.requireNonNull(obj, "Encoding is missing");
        FileOutputStream fileOutputStream2 = null;
        boolean exists = file.exists();
        try {
            fileOutputStream = new FileOutputStream(file, z);
        } catch (IOException e) {
            e = e;
        } catch (RuntimeException e2) {
            e = e2;
        }
        try {
            if (obj instanceof Charset) {
                return new OutputStreamWriter(fileOutputStream, (Charset) obj);
            }
            if (obj instanceof CharsetEncoder) {
                return new OutputStreamWriter(fileOutputStream, (CharsetEncoder) obj);
            }
            return new OutputStreamWriter(fileOutputStream, (String) obj);
        } catch (IOException | RuntimeException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e.addSuppressed(e4);
                }
            }
            if (!exists) {
                FileUtils.deleteQuietly(file);
            }
            throw e;
        }
    }

    @Override // java.io.Writer
    public void write(int r2) throws IOException {
        this.out.write(r2);
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        this.out.write(cArr);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int r3, int r4) throws IOException {
        this.out.write(cArr, r3, r4);
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        this.out.write(str);
    }

    @Override // java.io.Writer
    public void write(String str, int r3, int r4) throws IOException {
        this.out.write(str, r3, r4);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
