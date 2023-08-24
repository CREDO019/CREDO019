package com.google.android.exoplayer2.extractor.p011ts;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.p011ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* renamed from: com.google.android.exoplayer2.extractor.ts.AdtsReader */
/* loaded from: classes2.dex */
public final class AdtsReader implements ElementaryStreamReader {
    private static final int CRC_SIZE = 2;
    private static final int HEADER_SIZE = 5;
    private static final int ID3_HEADER_SIZE = 10;
    private static final byte[] ID3_IDENTIFIER = {73, 68, 51};
    private static final int ID3_SIZE_OFFSET = 6;
    private static final int MATCH_STATE_FF = 512;
    private static final int MATCH_STATE_I = 768;
    private static final int MATCH_STATE_ID = 1024;
    private static final int MATCH_STATE_START = 256;
    private static final int MATCH_STATE_VALUE_SHIFT = 8;
    private static final int STATE_CHECKING_ADTS_HEADER = 1;
    private static final int STATE_FINDING_SAMPLE = 0;
    private static final int STATE_READING_ADTS_HEADER = 3;
    private static final int STATE_READING_ID3_HEADER = 2;
    private static final int STATE_READING_SAMPLE = 4;
    private static final String TAG = "AdtsReader";
    private static final int VERSION_UNSET = -1;
    private final ParsableBitArray adtsScratch;
    private int bytesRead;
    private int currentFrameVersion;
    private TrackOutput currentOutput;
    private long currentSampleDuration;
    private final boolean exposeId3;
    private int firstFrameSampleRateIndex;
    private int firstFrameVersion;
    private String formatId;
    private boolean foundFirstFrame;
    private boolean hasCrc;
    private boolean hasOutputFormat;
    private final ParsableByteArray id3HeaderBuffer;
    private TrackOutput id3Output;
    private final String language;
    private int matchState;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state;
    private long timeUs;

    public static boolean isAdtsSyncWord(int r1) {
        return (r1 & 65526) == 65520;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public AdtsReader(boolean z) {
        this(z, null);
    }

    public AdtsReader(boolean z, String str) {
        this.adtsScratch = new ParsableBitArray(new byte[7]);
        this.id3HeaderBuffer = new ParsableByteArray(Arrays.copyOf(ID3_IDENTIFIER, 10));
        setFindingSampleState();
        this.firstFrameVersion = -1;
        this.firstFrameSampleRateIndex = -1;
        this.sampleDurationUs = C1856C.TIME_UNSET;
        this.timeUs = C1856C.TIME_UNSET;
        this.exposeId3 = z;
        this.language = str;
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void seek() {
        this.timeUs = C1856C.TIME_UNSET;
        resetSync();
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        this.output = track;
        this.currentOutput = track;
        if (this.exposeId3) {
            trackIdGenerator.generateNewId();
            TrackOutput track2 = extractorOutput.track(trackIdGenerator.getTrackId(), 5);
            this.id3Output = track2;
            track2.format(new Format.Builder().setId(trackIdGenerator.getFormatId()).setSampleMimeType(MimeTypes.APPLICATION_ID3).build());
            return;
        }
        this.id3Output = new DummyTrackOutput();
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void packetStarted(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.timeUs = j;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.p011ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int r0 = this.state;
            if (r0 == 0) {
                findNextSample(parsableByteArray);
            } else if (r0 == 1) {
                checkAdtsHeader(parsableByteArray);
            } else if (r0 != 2) {
                if (r0 == 3) {
                    if (continueRead(parsableByteArray, this.adtsScratch.data, this.hasCrc ? 7 : 5)) {
                        parseAdtsHeader();
                    }
                } else if (r0 == 4) {
                    readSample(parsableByteArray);
                } else {
                    throw new IllegalStateException();
                }
            } else if (continueRead(parsableByteArray, this.id3HeaderBuffer.getData(), 10)) {
                parseId3Header();
            }
        }
    }

    public long getSampleDurationUs() {
        return this.sampleDurationUs;
    }

    private void resetSync() {
        this.foundFirstFrame = false;
        setFindingSampleState();
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int r5) {
        int min = Math.min(parsableByteArray.bytesLeft(), r5 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int r3 = this.bytesRead + min;
        this.bytesRead = r3;
        return r3 == r5;
    }

    private void setFindingSampleState() {
        this.state = 0;
        this.bytesRead = 0;
        this.matchState = 256;
    }

    private void setReadingId3HeaderState() {
        this.state = 2;
        this.bytesRead = ID3_IDENTIFIER.length;
        this.sampleSize = 0;
        this.id3HeaderBuffer.setPosition(0);
    }

    private void setReadingSampleState(TrackOutput trackOutput, long j, int r5, int r6) {
        this.state = 4;
        this.bytesRead = r5;
        this.currentOutput = trackOutput;
        this.currentSampleDuration = j;
        this.sampleSize = r6;
    }

    private void setReadingAdtsHeaderState() {
        this.state = 3;
        this.bytesRead = 0;
    }

    private void setCheckingAdtsHeaderState() {
        this.state = 1;
        this.bytesRead = 0;
    }

    private void findNextSample(ParsableByteArray parsableByteArray) {
        byte[] data = parsableByteArray.getData();
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit) {
            int r3 = position + 1;
            int r1 = data[position] & 255;
            if (this.matchState == 512 && isAdtsSyncBytes((byte) -1, (byte) r1) && (this.foundFirstFrame || checkSyncPositionValid(parsableByteArray, r3 - 2))) {
                this.currentFrameVersion = (r1 & 8) >> 3;
                this.hasCrc = (r1 & 1) == 0;
                if (!this.foundFirstFrame) {
                    setCheckingAdtsHeaderState();
                } else {
                    setReadingAdtsHeaderState();
                }
                parsableByteArray.setPosition(r3);
                return;
            }
            int r4 = this.matchState;
            int r12 = r1 | r4;
            if (r12 == 329) {
                this.matchState = MATCH_STATE_I;
            } else if (r12 == 511) {
                this.matchState = 512;
            } else if (r12 == 836) {
                this.matchState = 1024;
            } else if (r12 == 1075) {
                setReadingId3HeaderState();
                parsableByteArray.setPosition(r3);
                return;
            } else if (r4 != 256) {
                this.matchState = 256;
                r3--;
            }
            position = r3;
        }
        parsableByteArray.setPosition(position);
    }

    private void checkAdtsHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() == 0) {
            return;
        }
        this.adtsScratch.data[0] = parsableByteArray.getData()[parsableByteArray.getPosition()];
        this.adtsScratch.setPosition(2);
        int readBits = this.adtsScratch.readBits(4);
        int r0 = this.firstFrameSampleRateIndex;
        if (r0 != -1 && readBits != r0) {
            resetSync();
            return;
        }
        if (!this.foundFirstFrame) {
            this.foundFirstFrame = true;
            this.firstFrameVersion = this.currentFrameVersion;
            this.firstFrameSampleRateIndex = readBits;
        }
        setReadingAdtsHeaderState();
    }

    private boolean checkSyncPositionValid(ParsableByteArray parsableByteArray, int r10) {
        parsableByteArray.setPosition(r10 + 1);
        if (tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
            this.adtsScratch.setPosition(4);
            int readBits = this.adtsScratch.readBits(1);
            int r4 = this.firstFrameVersion;
            if (r4 == -1 || readBits == r4) {
                if (this.firstFrameSampleRateIndex != -1) {
                    if (!tryRead(parsableByteArray, this.adtsScratch.data, 1)) {
                        return true;
                    }
                    this.adtsScratch.setPosition(2);
                    if (this.adtsScratch.readBits(4) != this.firstFrameSampleRateIndex) {
                        return false;
                    }
                    parsableByteArray.setPosition(r10 + 2);
                }
                if (tryRead(parsableByteArray, this.adtsScratch.data, 4)) {
                    this.adtsScratch.setPosition(14);
                    int readBits2 = this.adtsScratch.readBits(13);
                    if (readBits2 < 7) {
                        return false;
                    }
                    byte[] data = parsableByteArray.getData();
                    int limit = parsableByteArray.limit();
                    int r102 = r10 + readBits2;
                    if (r102 >= limit) {
                        return true;
                    }
                    if (data[r102] == -1) {
                        int r103 = r102 + 1;
                        if (r103 == limit) {
                            return true;
                        }
                        return isAdtsSyncBytes((byte) -1, data[r103]) && ((data[r103] & 8) >> 3) == readBits;
                    } else if (data[r102] != 73) {
                        return false;
                    } else {
                        int r0 = r102 + 1;
                        if (r0 == limit) {
                            return true;
                        }
                        if (data[r0] != 68) {
                            return false;
                        }
                        int r104 = r102 + 2;
                        return r104 == limit || data[r104] == 51;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isAdtsSyncBytes(byte b, byte b2) {
        return isAdtsSyncWord(((b & 255) << 8) | (b2 & 255));
    }

    private boolean tryRead(ParsableByteArray parsableByteArray, byte[] bArr, int r5) {
        if (parsableByteArray.bytesLeft() < r5) {
            return false;
        }
        parsableByteArray.readBytes(bArr, 0, r5);
        return true;
    }

    @RequiresNonNull({"id3Output"})
    private void parseId3Header() {
        this.id3Output.sampleData(this.id3HeaderBuffer, 10);
        this.id3HeaderBuffer.setPosition(6);
        setReadingSampleState(this.id3Output, 0L, 10, this.id3HeaderBuffer.readSynchSafeInt() + 10);
    }

    @RequiresNonNull({"output"})
    private void parseAdtsHeader() throws ParserException {
        this.adtsScratch.setPosition(0);
        if (!this.hasOutputFormat) {
            int readBits = this.adtsScratch.readBits(2) + 1;
            if (readBits != 2) {
                Log.m1132w(TAG, "Detected audio object type: " + readBits + ", but assuming AAC LC.");
                readBits = 2;
            }
            this.adtsScratch.skipBits(5);
            byte[] buildAudioSpecificConfig = AacUtil.buildAudioSpecificConfig(readBits, this.firstFrameSampleRateIndex, this.adtsScratch.readBits(3));
            AacUtil.Config parseAudioSpecificConfig = AacUtil.parseAudioSpecificConfig(buildAudioSpecificConfig);
            Format build = new Format.Builder().setId(this.formatId).setSampleMimeType(MimeTypes.AUDIO_AAC).setCodecs(parseAudioSpecificConfig.codecs).setChannelCount(parseAudioSpecificConfig.channelCount).setSampleRate(parseAudioSpecificConfig.sampleRateHz).setInitializationData(Collections.singletonList(buildAudioSpecificConfig)).setLanguage(this.language).build();
            this.sampleDurationUs = 1024000000 / build.sampleRate;
            this.output.format(build);
            this.hasOutputFormat = true;
        } else {
            this.adtsScratch.skipBits(10);
        }
        this.adtsScratch.skipBits(4);
        int readBits2 = (this.adtsScratch.readBits(13) - 2) - 5;
        if (this.hasCrc) {
            readBits2 -= 2;
        }
        setReadingSampleState(this.output, this.sampleDurationUs, 0, readBits2);
    }

    @RequiresNonNull({"currentOutput"})
    private void readSample(ParsableByteArray parsableByteArray) {
        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
        this.currentOutput.sampleData(parsableByteArray, min);
        int r8 = this.bytesRead + min;
        this.bytesRead = r8;
        int r4 = this.sampleSize;
        if (r8 == r4) {
            long j = this.timeUs;
            if (j != C1856C.TIME_UNSET) {
                this.currentOutput.sampleMetadata(j, 1, r4, 0, null);
                this.timeUs += this.currentSampleDuration;
            }
            setFindingSampleState();
        }
    }

    @EnsuresNonNull({"output", "currentOutput", "id3Output"})
    private void assertTracksCreated() {
        Assertions.checkNotNull(this.output);
        Util.castNonNull(this.currentOutput);
        Util.castNonNull(this.id3Output);
    }
}
