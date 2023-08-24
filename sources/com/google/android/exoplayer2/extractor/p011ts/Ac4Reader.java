package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.Ac4Util;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* renamed from: com.google.android.exoplayer2.extractor.ts.Ac4Reader */
/* loaded from: classes2.dex */
public final class Ac4Reader implements ElementaryStreamReader {
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private int bytesRead;
    private Format format;
    private String formatId;
    private boolean hasCRC;
    private final ParsableBitArray headerScratchBits;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private boolean lastByteWasAC;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public Ac4Reader() {
        this(null);
    }

    public Ac4Reader(String str) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(new byte[16]);
        this.headerScratchBits = parsableBitArray;
        this.headerScratchBytes = new ParsableByteArray(parsableBitArray.data);
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
        this.timeUs = C1856C.TIME_UNSET;
        this.language = str;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.lastByteWasAC = false;
        this.hasCRC = false;
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
                        int r2 = this.bytesRead + min;
                        this.bytesRead = r2;
                        int r7 = this.sampleSize;
                        if (r2 == r7) {
                            long j = this.timeUs;
                            if (j != C1856C.TIME_UNSET) {
                                this.output.sampleMetadata(j, 1, r7, 0, null);
                                this.timeUs += this.sampleDurationUs;
                            }
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), 16)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 16);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
                this.headerScratchBytes.getData()[0] = -84;
                this.headerScratchBytes.getData()[1] = (byte) (this.hasCRC ? 65 : 64);
                this.bytesRead = 2;
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
        int readUnsignedByte;
        while (true) {
            if (parsableByteArray.bytesLeft() <= 0) {
                return false;
            }
            if (!this.lastByteWasAC) {
                this.lastByteWasAC = parsableByteArray.readUnsignedByte() == 172;
            } else {
                readUnsignedByte = parsableByteArray.readUnsignedByte();
                this.lastByteWasAC = readUnsignedByte == 172;
                if (readUnsignedByte == 64 || readUnsignedByte == 65) {
                    break;
                }
            }
        }
        this.hasCRC = readUnsignedByte == 65;
        return true;
    }

    @RequiresNonNull({"output"})
    private void parseHeader() {
        this.headerScratchBits.setPosition(0);
        Ac4Util.SyncFrameInfo parseAc4SyncframeInfo = Ac4Util.parseAc4SyncframeInfo(this.headerScratchBits);
        if (this.format == null || parseAc4SyncframeInfo.channelCount != this.format.channelCount || parseAc4SyncframeInfo.sampleRate != this.format.sampleRate || !MimeTypes.AUDIO_AC4.equals(this.format.sampleMimeType)) {
            Format build = new Format.Builder().setId(this.formatId).setSampleMimeType(MimeTypes.AUDIO_AC4).setChannelCount(parseAc4SyncframeInfo.channelCount).setSampleRate(parseAc4SyncframeInfo.sampleRate).setLanguage(this.language).build();
            this.format = build;
            this.output.format(build);
        }
        this.sampleSize = parseAc4SyncframeInfo.frameSize;
        this.sampleDurationUs = (parseAc4SyncframeInfo.sampleCount * 1000000) / this.format.sampleRate;
    }
}
