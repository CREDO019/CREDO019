package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class DefaultExtractorInput implements ExtractorInput {
    private static final int PEEK_MAX_FREE_SPACE = 524288;
    private static final int PEEK_MIN_FREE_SPACE_AFTER_RESIZE = 65536;
    private static final int SCRATCH_SPACE_SIZE = 4096;
    private final DataReader dataReader;
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final long streamLength;
    private byte[] peekBuffer = new byte[65536];
    private final byte[] scratchSpace = new byte[4096];

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.extractor");
    }

    public DefaultExtractorInput(DataReader dataReader, long j, long j2) {
        this.dataReader = dataReader;
        this.position = j;
        this.streamLength = j2;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.upstream.DataReader, com.google.android.exoplayer2.upstream.HttpDataSource
    public int read(byte[] bArr, int r9, int r10) throws IOException {
        int readFromPeekBuffer = readFromPeekBuffer(bArr, r9, r10);
        if (readFromPeekBuffer == 0) {
            readFromPeekBuffer = readFromUpstream(bArr, r9, r10, 0, true);
        }
        commitBytesRead(readFromPeekBuffer);
        return readFromPeekBuffer;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean readFully(byte[] bArr, int r9, int r10, boolean z) throws IOException {
        int readFromPeekBuffer = readFromPeekBuffer(bArr, r9, r10);
        while (readFromPeekBuffer < r10 && readFromPeekBuffer != -1) {
            readFromPeekBuffer = readFromUpstream(bArr, r9, r10, readFromPeekBuffer, z);
        }
        commitBytesRead(readFromPeekBuffer);
        return readFromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void readFully(byte[] bArr, int r3, int r4) throws IOException {
        readFully(bArr, r3, r4, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int skip(int r8) throws IOException {
        int skipFromPeekBuffer = skipFromPeekBuffer(r8);
        if (skipFromPeekBuffer == 0) {
            byte[] bArr = this.scratchSpace;
            skipFromPeekBuffer = readFromUpstream(bArr, 0, Math.min(r8, bArr.length), 0, true);
        }
        commitBytesRead(skipFromPeekBuffer);
        return skipFromPeekBuffer;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean skipFully(int r8, boolean z) throws IOException {
        int skipFromPeekBuffer = skipFromPeekBuffer(r8);
        while (skipFromPeekBuffer < r8 && skipFromPeekBuffer != -1) {
            skipFromPeekBuffer = readFromUpstream(this.scratchSpace, -skipFromPeekBuffer, Math.min(r8, this.scratchSpace.length + skipFromPeekBuffer), skipFromPeekBuffer, z);
        }
        commitBytesRead(skipFromPeekBuffer);
        return skipFromPeekBuffer != -1;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void skipFully(int r2) throws IOException {
        skipFully(r2, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int peek(byte[] bArr, int r9, int r10) throws IOException {
        int min;
        ensureSpaceForPeek(r10);
        int r0 = this.peekBufferLength;
        int r3 = this.peekBufferPosition;
        int r02 = r0 - r3;
        if (r02 == 0) {
            min = readFromUpstream(this.peekBuffer, r3, r10, 0, true);
            if (min == -1) {
                return -1;
            }
            this.peekBufferLength += min;
        } else {
            min = Math.min(r10, r02);
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition, bArr, r9, min);
        this.peekBufferPosition += min;
        return min;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean peekFully(byte[] bArr, int r3, int r4, boolean z) throws IOException {
        if (advancePeekPosition(r4, z)) {
            System.arraycopy(this.peekBuffer, this.peekBufferPosition - r4, bArr, r3, r4);
            return true;
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void peekFully(byte[] bArr, int r3, int r4) throws IOException {
        peekFully(bArr, r3, r4, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean advancePeekPosition(int r8, boolean z) throws IOException {
        ensureSpaceForPeek(r8);
        int r5 = this.peekBufferLength - this.peekBufferPosition;
        while (r5 < r8) {
            r5 = readFromUpstream(this.peekBuffer, this.peekBufferPosition, r8, r5, z);
            if (r5 == -1) {
                return false;
            }
            this.peekBufferLength = this.peekBufferPosition + r5;
        }
        this.peekBufferPosition += r8;
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void advancePeekPosition(int r2) throws IOException {
        advancePeekPosition(r2, false);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.position + this.peekBufferPosition;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPosition() {
        return this.position;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getLength() {
        return this.streamLength;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public <E extends Throwable> void setRetryPosition(long j, E e) throws Throwable {
        Assertions.checkArgument(j >= 0);
        this.position = j;
        throw e;
    }

    private void ensureSpaceForPeek(int r4) {
        int r0 = this.peekBufferPosition + r4;
        byte[] bArr = this.peekBuffer;
        if (r0 > bArr.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Util.constrainValue(bArr.length * 2, 65536 + r0, r0 + 524288));
        }
    }

    private int skipFromPeekBuffer(int r2) {
        int min = Math.min(this.peekBufferLength, r2);
        updatePeekBuffer(min);
        return min;
    }

    private int readFromPeekBuffer(byte[] bArr, int r4, int r5) {
        int r0 = this.peekBufferLength;
        if (r0 == 0) {
            return 0;
        }
        int min = Math.min(r0, r5);
        System.arraycopy(this.peekBuffer, 0, bArr, r4, min);
        updatePeekBuffer(min);
        return min;
    }

    private void updatePeekBuffer(int r6) {
        int r0 = this.peekBufferLength - r6;
        this.peekBufferLength = r0;
        this.peekBufferPosition = 0;
        byte[] bArr = this.peekBuffer;
        byte[] bArr2 = r0 < bArr.length - 524288 ? new byte[65536 + r0] : bArr;
        System.arraycopy(bArr, r6, bArr2, 0, r0);
        this.peekBuffer = bArr2;
    }

    private int readFromUpstream(byte[] bArr, int r3, int r4, int r5, boolean z) throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
        int read = this.dataReader.read(bArr, r3 + r5, r4 - r5);
        if (read == -1) {
            if (r5 == 0 && z) {
                return -1;
            }
            throw new EOFException();
        }
        return r5 + read;
    }

    private void commitBytesRead(int r5) {
        if (r5 != -1) {
            this.position += r5;
        }
    }
}
