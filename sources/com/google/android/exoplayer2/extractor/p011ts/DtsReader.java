package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* renamed from: com.google.android.exoplayer2.extractor.ts.DtsReader */
/* loaded from: classes2.dex */
public final class DtsReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 18;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private int bytesRead;
    private Format format;
    private String formatId;
    private final String language;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int syncBytes;
    private final ParsableByteArray headerScratchBytes = new ParsableByteArray(new byte[18]);
    private int state = 0;
    private long timeUs = C1856C.TIME_UNSET;

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public DtsReader(String str) {
        this.language = str;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.syncBytes = 0;
        this.timeUs = C1856C.TIME_UNSET;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetStarted(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.timeUs = j;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            int r0 = this.state;
            if (r0 != 0) {
                if (r0 != 1) {
                    if (r0 == 2) {
                        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, min);
                        int r1 = this.bytesRead + min;
                        this.bytesRead = r1;
                        int r7 = this.sampleSize;
                        if (r1 == r7) {
                            long j = this.timeUs;
                            if (j != C1856C.TIME_UNSET) {
                                this.output.sampleMetadata(j, 1, r7, 0, null);
                                this.timeUs += this.sampleDurationUs;
                            }
                            this.state = 0;
                        }
                    } else {
                        throw new IllegalStateException();
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), 18)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 18);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
            }
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int r5) {
        int min = Math.min(parsableByteArray.bytesLeft(), r5 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int r3 = this.bytesRead + min;
        this.bytesRead = r3;
        return r3 == r5;
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int r0 = this.syncBytes << 8;
            this.syncBytes = r0;
            int readUnsignedByte = r0 | parsableByteArray.readUnsignedByte();
            this.syncBytes = readUnsignedByte;
            if (DtsUtil.isSyncWord(readUnsignedByte)) {
                byte[] data = this.headerScratchBytes.getData();
                int r02 = this.syncBytes;
                data[0] = (byte) ((r02 >> 24) & 255);
                data[1] = (byte) ((r02 >> 16) & 255);
                data[2] = (byte) ((r02 >> 8) & 255);
                data[3] = (byte) (r02 & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    @RequiresNonNull({"output"})
    private void parseHeader() {
        byte[] data = this.headerScratchBytes.getData();
        if (this.format == null) {
            Format parseDtsFormat = DtsUtil.parseDtsFormat(data, this.formatId, this.language, null);
            this.format = parseDtsFormat;
            this.output.format(parseDtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(data);
        this.sampleDurationUs = (int) ((DtsUtil.parseDtsAudioSampleCount(data) * 1000000) / this.format.sampleRate);
    }
}
