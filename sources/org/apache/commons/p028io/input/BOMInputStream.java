package org.apache.commons.p028io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.p028io.ByteOrderMark;

/* renamed from: org.apache.commons.io.input.BOMInputStream */
/* loaded from: classes5.dex */
public class BOMInputStream extends ProxyInputStream {
    private static final Comparator<ByteOrderMark> ByteOrderMarkLengthComparator = new Comparator<ByteOrderMark>() { // from class: org.apache.commons.io.input.BOMInputStream.1
        @Override // java.util.Comparator
        public int compare(ByteOrderMark byteOrderMark, ByteOrderMark byteOrderMark2) {
            int length = byteOrderMark.length();
            int length2 = byteOrderMark2.length();
            if (length > length2) {
                return -1;
            }
            return length2 > length ? 1 : 0;
        }
    };
    private final List<ByteOrderMark> boms;
    private ByteOrderMark byteOrderMark;
    private int fbIndex;
    private int fbLength;
    private int[] firstBytes;
    private final boolean include;
    private int markFbIndex;
    private boolean markedAtStart;

    public BOMInputStream(InputStream inputStream) {
        this(inputStream, false, ByteOrderMark.UTF_8);
    }

    public BOMInputStream(InputStream inputStream, boolean z) {
        this(inputStream, z, ByteOrderMark.UTF_8);
    }

    public BOMInputStream(InputStream inputStream, ByteOrderMark... byteOrderMarkArr) {
        this(inputStream, false, byteOrderMarkArr);
    }

    public BOMInputStream(InputStream inputStream, boolean z, ByteOrderMark... byteOrderMarkArr) {
        super(inputStream);
        if (byteOrderMarkArr == null || byteOrderMarkArr.length == 0) {
            throw new IllegalArgumentException("No BOMs specified");
        }
        this.include = z;
        List<ByteOrderMark> asList = Arrays.asList(byteOrderMarkArr);
        Collections.sort(asList, ByteOrderMarkLengthComparator);
        this.boms = asList;
    }

    public boolean hasBOM() throws IOException {
        return getBOM() != null;
    }

    public boolean hasBOM(ByteOrderMark byteOrderMark) throws IOException {
        if (!this.boms.contains(byteOrderMark)) {
            throw new IllegalArgumentException("Stream not configure to detect " + byteOrderMark);
        }
        getBOM();
        ByteOrderMark byteOrderMark2 = this.byteOrderMark;
        return byteOrderMark2 != null && byteOrderMark2.equals(byteOrderMark);
    }

    public ByteOrderMark getBOM() throws IOException {
        if (this.firstBytes == null) {
            this.fbLength = 0;
            this.firstBytes = new int[this.boms.get(0).length()];
            int r1 = 0;
            while (true) {
                int[] r2 = this.firstBytes;
                if (r1 >= r2.length) {
                    break;
                }
                r2[r1] = this.in.read();
                this.fbLength++;
                if (this.firstBytes[r1] < 0) {
                    break;
                }
                r1++;
            }
            ByteOrderMark find = find();
            this.byteOrderMark = find;
            if (find != null && !this.include) {
                if (find.length() < this.firstBytes.length) {
                    this.fbIndex = this.byteOrderMark.length();
                } else {
                    this.fbLength = 0;
                }
            }
        }
        return this.byteOrderMark;
    }

    public String getBOMCharsetName() throws IOException {
        getBOM();
        ByteOrderMark byteOrderMark = this.byteOrderMark;
        if (byteOrderMark == null) {
            return null;
        }
        return byteOrderMark.getCharsetName();
    }

    private int readFirstBytes() throws IOException {
        getBOM();
        int r0 = this.fbIndex;
        if (r0 < this.fbLength) {
            int[] r1 = this.firstBytes;
            this.fbIndex = r0 + 1;
            return r1[r0];
        }
        return -1;
    }

    private ByteOrderMark find() {
        for (ByteOrderMark byteOrderMark : this.boms) {
            if (matches(byteOrderMark)) {
                return byteOrderMark;
            }
        }
        return null;
    }

    private boolean matches(ByteOrderMark byteOrderMark) {
        for (int r1 = 0; r1 < byteOrderMark.length(); r1++) {
            if (byteOrderMark.get(r1) != this.firstBytes[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int readFirstBytes = readFirstBytes();
        return readFirstBytes >= 0 ? readFirstBytes : this.in.read();
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int r6, int r7) throws IOException {
        int r0 = 0;
        int r1 = 0;
        while (r7 > 0 && r0 >= 0) {
            r0 = readFirstBytes();
            if (r0 >= 0) {
                bArr[r6] = (byte) (r0 & 255);
                r7--;
                r1++;
                r6++;
            }
        }
        int read = this.in.read(bArr, r6, r7);
        if (read < 0) {
            if (r1 > 0) {
                return r1;
            }
            return -1;
        }
        return r1 + read;
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int r2) {
        this.markFbIndex = this.fbIndex;
        this.markedAtStart = this.firstBytes == null;
        this.in.mark(r2);
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        this.fbIndex = this.markFbIndex;
        if (this.markedAtStart) {
            this.firstBytes = null;
        }
        this.in.reset();
    }

    @Override // org.apache.commons.p028io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long j2;
        int r0 = 0;
        while (true) {
            j2 = r0;
            if (j <= j2 || readFirstBytes() < 0) {
                break;
            }
            r0++;
        }
        return this.in.skip(j - j2) + j2;
    }
}
