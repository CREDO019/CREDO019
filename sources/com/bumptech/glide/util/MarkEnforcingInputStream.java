package com.bumptech.glide.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class MarkEnforcingInputStream extends FilterInputStream {
    private static final int END_OF_STREAM = -1;
    private static final int UNSET = Integer.MIN_VALUE;
    private int availableBytes;

    public MarkEnforcingInputStream(InputStream inputStream) {
        super(inputStream);
        this.availableBytes = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int r1) {
        super.mark(r1);
        this.availableBytes = r1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (getBytesToRead(1L) == -1) {
            return -1;
        }
        int read = super.read();
        updateAvailableBytesAfterRead(1L);
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int r4, int r5) throws IOException {
        int bytesToRead = (int) getBytesToRead(r5);
        if (bytesToRead == -1) {
            return -1;
        }
        int read = super.read(bArr, r4, bytesToRead);
        updateAvailableBytesAfterRead(read);
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        this.availableBytes = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long bytesToRead = getBytesToRead(j);
        if (bytesToRead == -1) {
            return 0L;
        }
        long skip = super.skip(bytesToRead);
        updateAvailableBytesAfterRead(skip);
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int r0 = this.availableBytes;
        return r0 == Integer.MIN_VALUE ? super.available() : Math.min(r0, super.available());
    }

    private long getBytesToRead(long j) {
        int r0 = this.availableBytes;
        if (r0 == 0) {
            return -1L;
        }
        return (r0 == Integer.MIN_VALUE || j <= ((long) r0)) ? j : r0;
    }

    private void updateAvailableBytesAfterRead(long j) {
        int r0 = this.availableBytes;
        if (r0 == Integer.MIN_VALUE || j == -1) {
            return;
        }
        this.availableBytes = (int) (r0 - j);
    }
}
