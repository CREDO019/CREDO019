package com.google.android.exoplayer2.extractor;

import java.io.IOException;

/* loaded from: classes2.dex */
public class ForwardingExtractorInput implements ExtractorInput {
    private final ExtractorInput input;

    public ForwardingExtractorInput(ExtractorInput extractorInput) {
        this.input = extractorInput;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.upstream.DataReader, com.google.android.exoplayer2.upstream.HttpDataSource
    public int read(byte[] bArr, int r3, int r4) throws IOException {
        return this.input.read(bArr, r3, r4);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean readFully(byte[] bArr, int r3, int r4, boolean z) throws IOException {
        return this.input.readFully(bArr, r3, r4, z);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void readFully(byte[] bArr, int r3, int r4) throws IOException {
        this.input.readFully(bArr, r3, r4);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int skip(int r2) throws IOException {
        return this.input.skip(r2);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean skipFully(int r2, boolean z) throws IOException {
        return this.input.skipFully(r2, z);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void skipFully(int r2) throws IOException {
        this.input.skipFully(r2);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public int peek(byte[] bArr, int r3, int r4) throws IOException {
        return this.input.peek(bArr, r3, r4);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean peekFully(byte[] bArr, int r3, int r4, boolean z) throws IOException {
        return this.input.peekFully(bArr, r3, r4, z);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void peekFully(byte[] bArr, int r3, int r4) throws IOException {
        this.input.peekFully(bArr, r3, r4);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public boolean advancePeekPosition(int r2, boolean z) throws IOException {
        return this.input.advancePeekPosition(r2, z);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void advancePeekPosition(int r2) throws IOException {
        this.input.advancePeekPosition(r2);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.input.resetPeekPosition();
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.input.getPeekPosition();
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getPosition() {
        return this.input.getPosition();
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public long getLength() {
        return this.input.getLength();
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorInput
    public <E extends Throwable> void setRetryPosition(long j, E e) throws Throwable {
        this.input.setRetryPosition(j, e);
    }
}
