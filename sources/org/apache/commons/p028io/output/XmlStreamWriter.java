package org.apache.commons.p028io.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.p028io.input.XmlStreamReader;

/* renamed from: org.apache.commons.io.output.XmlStreamWriter */
/* loaded from: classes5.dex */
public class XmlStreamWriter extends Writer {
    private static final int BUFFER_SIZE = 4096;
    static final Pattern ENCODING_PATTERN = XmlStreamReader.ENCODING_PATTERN;
    private final String defaultEncoding;
    private String encoding;
    private final OutputStream out;
    private Writer writer;
    private StringWriter xmlPrologWriter;

    public XmlStreamWriter(OutputStream outputStream) {
        this(outputStream, (String) null);
    }

    public XmlStreamWriter(OutputStream outputStream, String str) {
        this.xmlPrologWriter = new StringWriter(4096);
        this.out = outputStream;
        this.defaultEncoding = str == null ? "UTF-8" : str;
    }

    public XmlStreamWriter(File file) throws FileNotFoundException {
        this(file, (String) null);
    }

    public XmlStreamWriter(File file, String str) throws FileNotFoundException {
        this(new FileOutputStream(file), str);
    }

    public String getEncoding() {
        return this.encoding;
    }

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.writer == null) {
            this.encoding = this.defaultEncoding;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.out, this.encoding);
            this.writer = outputStreamWriter;
            outputStreamWriter.write(this.xmlPrologWriter.toString());
        }
        this.writer.close();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        Writer writer = this.writer;
        if (writer != null) {
            writer.flush();
        }
    }

    private void detectEncoding(char[] cArr, int r8, int r9) throws IOException {
        StringBuffer buffer = this.xmlPrologWriter.getBuffer();
        int length = buffer.length() + r9 > 4096 ? 4096 - buffer.length() : r9;
        this.xmlPrologWriter.write(cArr, r8, length);
        if (buffer.length() >= 5) {
            if (buffer.substring(0, 5).equals("<?xml")) {
                int indexOf = buffer.indexOf("?>");
                if (indexOf > 0) {
                    Matcher matcher = ENCODING_PATTERN.matcher(buffer.substring(0, indexOf));
                    if (matcher.find()) {
                        String upperCase = matcher.group(1).toUpperCase();
                        this.encoding = upperCase;
                        this.encoding = upperCase.substring(1, upperCase.length() - 1);
                    } else {
                        this.encoding = this.defaultEncoding;
                    }
                } else if (buffer.length() >= 4096) {
                    this.encoding = this.defaultEncoding;
                }
            } else {
                this.encoding = this.defaultEncoding;
            }
            if (this.encoding != null) {
                this.xmlPrologWriter = null;
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.out, this.encoding);
                this.writer = outputStreamWriter;
                outputStreamWriter.write(buffer.toString());
                if (r9 > length) {
                    this.writer.write(cArr, r8 + length, r9 - length);
                }
            }
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int r3, int r4) throws IOException {
        if (this.xmlPrologWriter != null) {
            detectEncoding(cArr, r3, r4);
        } else {
            this.writer.write(cArr, r3, r4);
        }
    }
}
