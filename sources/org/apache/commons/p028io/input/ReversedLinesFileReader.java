package org.apache.commons.p028io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p028io.Charsets;
import org.apache.commons.p028io.IOUtils;

/* renamed from: org.apache.commons.io.input.ReversedLinesFileReader */
/* loaded from: classes5.dex */
public class ReversedLinesFileReader implements Closeable {
    private final int avoidNewlineSplitBufferSize;
    private final int blockSize;
    private final int byteDecrement;
    private FilePart currentFilePart;
    private final Charset encoding;
    private final byte[][] newLineSequences;
    private final RandomAccessFile randomAccessFile;
    private final long totalBlockCount;
    private final long totalByteLength;
    private boolean trailingNewlineOfFileSkipped;

    @Deprecated
    public ReversedLinesFileReader(File file) throws IOException {
        this(file, 4096, Charset.defaultCharset());
    }

    public ReversedLinesFileReader(File file, Charset charset) throws IOException {
        this(file, 4096, charset);
    }

    public ReversedLinesFileReader(File file, int r9, Charset charset) throws IOException {
        int r4;
        this.trailingNewlineOfFileSkipped = false;
        this.blockSize = r9;
        this.encoding = charset;
        Charset charset2 = Charsets.toCharset(charset);
        if (charset2.newEncoder().maxBytesPerChar() == 1.0f) {
            this.byteDecrement = 1;
        } else if (charset2 == StandardCharsets.UTF_8) {
            this.byteDecrement = 1;
        } else if (charset2 == Charset.forName("Shift_JIS") || charset2 == Charset.forName("windows-31j") || charset2 == Charset.forName("x-windows-949") || charset2 == Charset.forName("gbk") || charset2 == Charset.forName("x-windows-950")) {
            this.byteDecrement = 1;
        } else if (charset2 == StandardCharsets.UTF_16BE || charset2 == StandardCharsets.UTF_16LE) {
            this.byteDecrement = 2;
        } else if (charset2 == StandardCharsets.UTF_16) {
            throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
        } else {
            throw new UnsupportedEncodingException("Encoding " + charset + " is not supported yet (feel free to submit a patch)");
        }
        byte[][] bArr = {IOUtils.LINE_SEPARATOR_WINDOWS.getBytes(charset), "\n".getBytes(charset), StringUtils.f1569CR.getBytes(charset)};
        this.newLineSequences = bArr;
        this.avoidNewlineSplitBufferSize = bArr[0].length;
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        this.randomAccessFile = randomAccessFile;
        long length = randomAccessFile.length();
        this.totalByteLength = length;
        long j = r9;
        int r8 = (int) (length % j);
        if (r8 > 0) {
            this.totalBlockCount = (length / j) + 1;
        } else {
            this.totalBlockCount = length / j;
            if (length > 0) {
                r4 = r9;
                this.currentFilePart = new FilePart(this.totalBlockCount, r4, null);
            }
        }
        r4 = r8;
        this.currentFilePart = new FilePart(this.totalBlockCount, r4, null);
    }

    public ReversedLinesFileReader(File file, int r2, String str) throws IOException {
        this(file, r2, Charsets.toCharset(str));
    }

    public String readLine() throws IOException {
        String readLine = this.currentFilePart.readLine();
        while (readLine == null) {
            FilePart rollOver = this.currentFilePart.rollOver();
            this.currentFilePart = rollOver;
            if (rollOver == null) {
                break;
            }
            readLine = rollOver.readLine();
        }
        if (!"".equals(readLine) || this.trailingNewlineOfFileSkipped) {
            return readLine;
        }
        this.trailingNewlineOfFileSkipped = true;
        return readLine();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    /* renamed from: org.apache.commons.io.input.ReversedLinesFileReader$FilePart */
    /* loaded from: classes5.dex */
    private class FilePart {
        private int currentLastBytePos;
        private final byte[] data;
        private byte[] leftOver;

        /* renamed from: no */
        private final long f1566no;

        private FilePart(long j, int r11, byte[] bArr) throws IOException {
            this.f1566no = j;
            byte[] bArr2 = new byte[(bArr != null ? bArr.length : 0) + r11];
            this.data = bArr2;
            long j2 = (j - 1) * ReversedLinesFileReader.this.blockSize;
            if (j > 0) {
                ReversedLinesFileReader.this.randomAccessFile.seek(j2);
                if (ReversedLinesFileReader.this.randomAccessFile.read(bArr2, 0, r11) != r11) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (bArr != null) {
                System.arraycopy(bArr, 0, bArr2, r11, bArr.length);
            }
            this.currentLastBytePos = bArr2.length - 1;
            this.leftOver = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FilePart rollOver() throws IOException {
            if (this.currentLastBytePos > -1) {
                throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
            }
            long j = this.f1566no;
            if (j > 1) {
                ReversedLinesFileReader reversedLinesFileReader = ReversedLinesFileReader.this;
                return new FilePart(j - 1, reversedLinesFileReader.blockSize, this.leftOver);
            } else if (this.leftOver == null) {
                return null;
            } else {
                throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.encoding));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String readLine() throws IOException {
            String str;
            byte[] bArr;
            boolean z = this.f1566no == 1;
            int r1 = this.currentLastBytePos;
            while (true) {
                if (r1 > -1) {
                    if (!z && r1 < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, r1);
                    if (newLineMatchByteCount <= 0) {
                        r1 -= ReversedLinesFileReader.this.byteDecrement;
                        if (r1 < 0) {
                            createLeftOver();
                            break;
                        }
                    } else {
                        int r6 = r1 + 1;
                        int r7 = (this.currentLastBytePos - r6) + 1;
                        if (r7 < 0) {
                            throw new IllegalStateException("Unexpected negative line length=" + r7);
                        }
                        byte[] bArr2 = new byte[r7];
                        System.arraycopy(this.data, r6, bArr2, 0, r7);
                        str = new String(bArr2, ReversedLinesFileReader.this.encoding);
                        this.currentLastBytePos = r1 - newLineMatchByteCount;
                    }
                } else {
                    break;
                }
            }
            str = null;
            if (!z || (bArr = this.leftOver) == null) {
                return str;
            }
            String str2 = new String(bArr, ReversedLinesFileReader.this.encoding);
            this.leftOver = null;
            return str2;
        }

        private void createLeftOver() {
            int r0 = this.currentLastBytePos + 1;
            if (r0 > 0) {
                byte[] bArr = new byte[r0];
                this.leftOver = bArr;
                System.arraycopy(this.data, 0, bArr, 0, r0);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] bArr, int r12) {
            byte[][] bArr2;
            for (byte[] bArr3 : ReversedLinesFileReader.this.newLineSequences) {
                boolean z = true;
                for (int length = bArr3.length - 1; length >= 0; length--) {
                    int length2 = (r12 + length) - (bArr3.length - 1);
                    z &= length2 >= 0 && bArr[length2] == bArr3[length];
                }
                if (z) {
                    return bArr3.length;
                }
            }
            return 0;
        }
    }
}
