package com.google.android.exoplayer2.source.rtsp.reader;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.rtsp.RtpPacket;
import com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
final class RtpVp9Reader implements RtpPayloadReader {
    private static final long MEDIA_CLOCK_FREQUENCY = 90000;
    private static final int SCALABILITY_STRUCTURE_SIZE = 4;
    private static final String TAG = "RtpVp9Reader";
    private int fragmentedSampleSizeBytes;
    private final RtpPayloadFormat payloadFormat;
    private TrackOutput trackOutput;
    private long firstReceivedTimestamp = C1856C.TIME_UNSET;
    private long startTimeOffsetUs = 0;
    private int previousSequenceNumber = -1;
    private int width = -1;
    private int height = -1;
    private boolean gotFirstPacketOfVP9Frame = false;
    private boolean reportedOutputFormat = false;

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void onReceivingFirstPacket(long j, int r3) {
    }

    public RtpVp9Reader(RtpPayloadFormat rtpPayloadFormat) {
        this.payloadFormat = rtpPayloadFormat;
    }

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void createTracks(ExtractorOutput extractorOutput, int r3) {
        TrackOutput track = extractorOutput.track(r3, 2);
        this.trackOutput = track;
        track.format(this.payloadFormat.format);
    }

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void consume(ParsableByteArray parsableByteArray, long j, int r20, boolean z) {
        int r3;
        Assertions.checkStateNotNull(this.trackOutput);
        if (validateVp9Descriptor(parsableByteArray, r20)) {
            int r9 = (this.fragmentedSampleSizeBytes == 0 && this.gotFirstPacketOfVP9Frame && (parsableByteArray.peekUnsignedByte() & 4) == 0) ? 1 : 0;
            if (!this.reportedOutputFormat && (r3 = this.width) != -1 && this.height != -1) {
                if (r3 != this.payloadFormat.format.width || this.height != this.payloadFormat.format.height) {
                    this.trackOutput.format(this.payloadFormat.format.buildUpon().setWidth(this.width).setHeight(this.height).build());
                }
                this.reportedOutputFormat = true;
            }
            int bytesLeft = parsableByteArray.bytesLeft();
            this.trackOutput.sampleData(parsableByteArray, bytesLeft);
            this.fragmentedSampleSizeBytes += bytesLeft;
            if (z) {
                if (this.firstReceivedTimestamp == C1856C.TIME_UNSET) {
                    this.firstReceivedTimestamp = j;
                }
                this.trackOutput.sampleMetadata(toSampleUs(this.startTimeOffsetUs, j, this.firstReceivedTimestamp), r9, this.fragmentedSampleSizeBytes, 0, null);
                this.fragmentedSampleSizeBytes = 0;
                this.gotFirstPacketOfVP9Frame = false;
            }
            this.previousSequenceNumber = r20;
        }
    }

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void seek(long j, long j2) {
        this.firstReceivedTimestamp = j;
        this.fragmentedSampleSizeBytes = 0;
        this.startTimeOffsetUs = j2;
    }

    private boolean validateVp9Descriptor(ParsableByteArray parsableByteArray, int r8) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if (this.gotFirstPacketOfVP9Frame) {
            int nextSequenceNumber = RtpPacket.getNextSequenceNumber(this.previousSequenceNumber);
            if (r8 != nextSequenceNumber) {
                Log.m1132w(TAG, Util.formatInvariant("Received RTP packet with unexpected sequence number. Expected: %d; received: %d. Dropping packet.", Integer.valueOf(nextSequenceNumber), Integer.valueOf(r8)));
                return false;
            }
        } else if ((readUnsignedByte & 8) == 0) {
            Log.m1132w(TAG, "First payload octet of the RTP packet is not the beginning of a new VP9 partition, Dropping current packet.");
            return false;
        } else {
            this.gotFirstPacketOfVP9Frame = true;
        }
        if ((readUnsignedByte & 128) == 0 || (parsableByteArray.readUnsignedByte() & 128) == 0 || parsableByteArray.bytesLeft() >= 1) {
            int r82 = readUnsignedByte & 16;
            Assertions.checkArgument(r82 == 0, "VP9 flexible mode is not supported.");
            if ((readUnsignedByte & 32) != 0) {
                parsableByteArray.skipBytes(1);
                if (parsableByteArray.bytesLeft() < 1) {
                    return false;
                }
                if (r82 == 0) {
                    parsableByteArray.skipBytes(1);
                }
            }
            if ((readUnsignedByte & 2) != 0) {
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                int r0 = (readUnsignedByte2 >> 5) & 7;
                if ((readUnsignedByte2 & 16) != 0) {
                    int r02 = r0 + 1;
                    if (parsableByteArray.bytesLeft() < r02 * 4) {
                        return false;
                    }
                    for (int r1 = 0; r1 < r02; r1++) {
                        this.width = parsableByteArray.readUnsignedShort();
                        this.height = parsableByteArray.readUnsignedShort();
                    }
                }
                if ((readUnsignedByte2 & 8) != 0) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    if (parsableByteArray.bytesLeft() < readUnsignedByte3) {
                        return false;
                    }
                    for (int r03 = 0; r03 < readUnsignedByte3; r03++) {
                        int readUnsignedShort = (parsableByteArray.readUnsignedShort() & 12) >> 2;
                        if (parsableByteArray.bytesLeft() < readUnsignedShort) {
                            return false;
                        }
                        parsableByteArray.skipBytes(readUnsignedShort);
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static long toSampleUs(long j, long j2, long j3) {
        return j + Util.scaleLargeTimestamp(j2 - j3, 1000000L, MEDIA_CLOCK_FREQUENCY);
    }
}
