package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.upstream.DataReader;
import java.io.IOException;

/* loaded from: classes2.dex */
public interface ExtractorInput extends DataReader {
    void advancePeekPosition(int r1) throws IOException;

    boolean advancePeekPosition(int r1, boolean z) throws IOException;

    long getLength();

    long getPeekPosition();

    long getPosition();

    int peek(byte[] bArr, int r2, int r3) throws IOException;

    void peekFully(byte[] bArr, int r2, int r3) throws IOException;

    boolean peekFully(byte[] bArr, int r2, int r3, boolean z) throws IOException;

    @Override // com.google.android.exoplayer2.upstream.DataReader, com.google.android.exoplayer2.upstream.HttpDataSource
    int read(byte[] bArr, int r2, int r3) throws IOException;

    void readFully(byte[] bArr, int r2, int r3) throws IOException;

    boolean readFully(byte[] bArr, int r2, int r3, boolean z) throws IOException;

    void resetPeekPosition();

    <E extends Throwable> void setRetryPosition(long j, E e) throws Throwable;

    int skip(int r1) throws IOException;

    void skipFully(int r1) throws IOException;

    boolean skipFully(int r1, boolean z) throws IOException;
}
