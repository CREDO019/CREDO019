package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* renamed from: com.google.android.exoplayer2.extractor.ts.Id3Reader */
/* loaded from: classes2.dex */
public final class Id3Reader implements ElementaryStreamReader {
    private static final String TAG = "Id3Reader";
    private TrackOutput output;
    private int sampleBytesRead;
    private int sampleSize;
    private boolean writingSample;
    private final ParsableByteArray id3Header = new ParsableByteArray(10);
    private long sampleTimeUs = C1856C.TIME_UNSET;

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void seek() {
        this.writingSample = false;
        this.sampleTimeUs = C1856C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
        this.output = track;
        track.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetStarted(long j, int r5) {
        if ((r5 & 4) == 0) {
            return;
        }
        this.writingSample = true;
        if (j != C1856C.TIME_UNSET) {
            this.sampleTimeUs = j;
        }
        this.sampleSize = 0;
        this.sampleBytesRead = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample) {
            int bytesLeft = parsableByteArray.bytesLeft();
            int r1 = this.sampleBytesRead;
            if (r1 < 10) {
                int min = Math.min(bytesLeft, 10 - r1);
                System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), this.id3Header.getData(), this.sampleBytesRead, min);
                if (this.sampleBytesRead + min == 10) {
                    this.id3Header.setPosition(0);
                    if (73 != this.id3Header.readUnsignedByte() || 68 != this.id3Header.readUnsignedByte() || 51 != this.id3Header.readUnsignedByte()) {
                        Log.m1132w(TAG, "Discarding invalid ID3 tag");
                        this.writingSample = false;
                        return;
                    }
                    this.id3Header.skipBytes(3);
                    this.sampleSize = this.id3Header.readSynchSafeInt() + 10;
                }
            }
            int min2 = Math.min(bytesLeft, this.sampleSize - this.sampleBytesRead);
            this.output.sampleData(parsableByteArray, min2);
            this.sampleBytesRead += min2;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetFinished() {
        int r5;
        Assertions.checkStateNotNull(this.output);
        if (this.writingSample && (r5 = this.sampleSize) != 0 && this.sampleBytesRead == r5) {
            long j = this.sampleTimeUs;
            if (j != C1856C.TIME_UNSET) {
                this.output.sampleMetadata(j, 1, r5, 0, null);
            }
            this.writingSample = false;
        }
    }
}
