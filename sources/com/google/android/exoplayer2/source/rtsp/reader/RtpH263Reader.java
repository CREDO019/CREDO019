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
final class RtpH263Reader implements RtpPayloadReader {
    private static final int I_VOP = 0;
    private static final long MEDIA_CLOCK_FREQUENCY = 90000;
    private static final int PICTURE_START_CODE = 128;
    private static final String TAG = "RtpH263Reader";
    private int fragmentedSampleSizeBytes;
    private int height;
    private boolean isKeyFrame;
    private boolean isOutputFormatSet;
    private final RtpPayloadFormat payloadFormat;
    private long startTimeOffsetUs;
    private TrackOutput trackOutput;
    private int width;
    private long firstReceivedTimestamp = C1856C.TIME_UNSET;
    private int previousSequenceNumber = -1;

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void onReceivingFirstPacket(long j, int r3) {
    }

    public RtpH263Reader(RtpPayloadFormat rtpPayloadFormat) {
        this.payloadFormat = rtpPayloadFormat;
    }

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void createTracks(ExtractorOutput extractorOutput, int r3) {
        TrackOutput track = extractorOutput.track(r3, 2);
        this.trackOutput = track;
        track.format(this.payloadFormat.format);
    }

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void consume(ParsableByteArray parsableByteArray, long j, int r25, boolean z) {
        Assertions.checkStateNotNull(this.trackOutput);
        int position = parsableByteArray.getPosition();
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        boolean z2 = (readUnsignedShort & 1024) > 0;
        if ((readUnsignedShort & 512) != 0 || (readUnsignedShort & 504) != 0 || (readUnsignedShort & 7) != 0) {
            Log.m1132w(TAG, "Dropping packet: video reduncancy coding is not supported, packet header VRC, or PLEN or PEBIT is non-zero");
            return;
        }
        if (z2) {
            if ((parsableByteArray.peekUnsignedByte() & 252) < 128) {
                Log.m1132w(TAG, "Picture start Code (PSC) missing, dropping packet.");
                return;
            }
            parsableByteArray.getData()[position] = 0;
            parsableByteArray.getData()[position + 1] = 0;
            parsableByteArray.setPosition(position);
        } else {
            int nextSequenceNumber = RtpPacket.getNextSequenceNumber(this.previousSequenceNumber);
            if (r25 != nextSequenceNumber) {
                Log.m1132w(TAG, Util.formatInvariant("Received RTP packet with unexpected sequence number. Expected: %d; received: %d. Dropping packet.", Integer.valueOf(nextSequenceNumber), Integer.valueOf(r25)));
                return;
            }
        }
        if (this.fragmentedSampleSizeBytes == 0) {
            parseVopHeader(parsableByteArray, this.isOutputFormatSet);
            if (!this.isOutputFormatSet && this.isKeyFrame) {
                if (this.width != this.payloadFormat.format.width || this.height != this.payloadFormat.format.height) {
                    this.trackOutput.format(this.payloadFormat.format.buildUpon().setWidth(this.width).setHeight(this.height).build());
                }
                this.isOutputFormatSet = true;
            }
        }
        int bytesLeft = parsableByteArray.bytesLeft();
        this.trackOutput.sampleData(parsableByteArray, bytesLeft);
        this.fragmentedSampleSizeBytes += bytesLeft;
        if (z) {
            if (this.firstReceivedTimestamp == C1856C.TIME_UNSET) {
                this.firstReceivedTimestamp = j;
            }
            this.trackOutput.sampleMetadata(toSampleUs(this.startTimeOffsetUs, j, this.firstReceivedTimestamp), this.isKeyFrame ? 1 : 0, this.fragmentedSampleSizeBytes, 0, null);
            this.fragmentedSampleSizeBytes = 0;
            this.isKeyFrame = false;
        }
        this.previousSequenceNumber = r25;
    }

    @Override // com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader
    public void seek(long j, long j2) {
        this.firstReceivedTimestamp = j;
        this.fragmentedSampleSizeBytes = 0;
        this.startTimeOffsetUs = j2;
    }

    private void parseVopHeader(ParsableByteArray parsableByteArray, boolean z) {
        int position = parsableByteArray.getPosition();
        if (((parsableByteArray.readUnsignedInt() >> 10) & 63) == 32) {
            int peekUnsignedByte = parsableByteArray.peekUnsignedByte();
            int r2 = (peekUnsignedByte >> 1) & 1;
            if (!z && r2 == 0) {
                int r9 = (peekUnsignedByte >> 2) & 7;
                if (r9 == 1) {
                    this.width = 128;
                    this.height = 96;
                } else {
                    int r92 = r9 - 2;
                    this.width = 176 << r92;
                    this.height = 144 << r92;
                }
            }
            parsableByteArray.setPosition(position);
            this.isKeyFrame = r2 == 0;
            return;
        }
        parsableByteArray.setPosition(position);
        this.isKeyFrame = false;
    }

    private static long toSampleUs(long j, long j2, long j3) {
        return j + Util.scaleLargeTimestamp(j2 - j3, 1000000L, MEDIA_CLOCK_FREQUENCY);
    }
}
